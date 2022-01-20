package com.zach2039.oldguns.world.level.block.entity;

import java.util.List;

import javax.annotation.Nullable;

import com.zach2039.oldguns.api.ammo.IArtilleryAmmo;
import com.zach2039.oldguns.api.ammo.IArtilleryCharge;
import com.zach2039.oldguns.api.artillery.AmmoFiringState;
import com.zach2039.oldguns.api.artillery.ArtilleryFiringState;
import com.zach2039.oldguns.api.artillery.ArtilleryType;
import com.zach2039.oldguns.api.artillery.IArtillery;
import com.zach2039.oldguns.world.entity.BulletProjectile;
import com.zach2039.oldguns.world.item.tools.LongMatchItem;
import com.zach2039.oldguns.world.item.tools.RamRodItem;

import net.minecraft.client.renderer.texture.Tickable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.datafix.fixes.ChunkPalettedStorageFix.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.INBTSerializable;

public class StationaryArtilleryEntity extends BlockEntity implements  INBTSerializable<CompoundTag>, Tickable, IArtillery {

	public StationaryArtilleryEntity(BlockEntityType<?> type, BlockPos blockpos, BlockState state, ArtilleryProperties builder) {
		super(type, blockpos, state);
		this.effectiveRangeModifier = builder.effectiveRangeModifier;
		this.artilleryType = builder.artilleryType;
		this.ammoSlots = builder.ammoSlots;
		this.maxChargePerAmmo = builder.maxChargePerAmmo;
		this.baseProjectileSpeed = builder.baseProjectileSpeed;
		this.effectiveRangeModifier = builder.effectiveRangeModifier;
		this.baseProjectileDeviation = builder.baseProjectileDeviation;
		this.projectileDamageModifier = builder.projectileDamageModifier;
	}
	
	@Nullable
	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	private CompoundTag saveMetadata(final CompoundTag tag) {
		tag.putInt("artilleryType", getArtilleryType().ordinal());
		tag.putFloat("shotYaw", getShotYaw());
		tag.putInt("facing", this.facing.ordinal());
		tag.putInt("firingCooldown", this.getFiringCooldown());
		return tag;
	}
	
	private void loadMetadata(final CompoundTag tag) {
		this.artilleryType = ArtilleryType.values()[tag.getInt("artilleryType")];
		this.shotYaw = tag.getFloat("shotYaw");
		this.facing = Direction.values()[tag.getInt("facing")];
		this.firingCooldown = tag.getInt("firingCooldown");
	}
	
	@Override
	public void load(final CompoundTag tag) {
		super.load(tag);
		loadMetadata(tag);
	}

	@Override
	public CompoundTag save(final CompoundTag tag) {
		super.save(tag);
		return saveMetadata(tag);
	}
	
	@Override
	public CompoundTag serializeNBT() {
		CompoundTag tag = new CompoundTag();
		return saveMetadata(tag);
	}

	@Override
	public void deserializeNBT(CompoundTag tag) {
		loadMetadata(tag);
	}
	
	@Override
	public void tick() {
		if (!this.hasLevel())
			return;
		
		if (this.firingCooldown > 0) {
			this.firingCooldown--;
		} else if (this.firingCooldown == 0) {
			this.level.getLightEmission(this.getBlockPos());
			this.level.getLightEmission(this.getBlockPos().above());
			this.level.getLightEmission(this.getBlockPos().below());
			this.level.getLightEmission(this.getBlockPos().north());
			this.level.getLightEmission(this.getBlockPos().south());
			this.level.getLightEmission(this.getBlockPos().east());
			this.level.getLightEmission(this.getBlockPos().west());
		}
	}
	
	private boolean processLoadedState(Level level, BlockPos blockpos, BlockState state, Player player, InteractionHand hand) {
		ItemStack handItem = player.getItemInHand(hand);
		boolean wasFired = false;
		
		if (handItem.getItem() instanceof LongMatchItem) {
			// Try to fire all loaded slots
			for (int slot = 0; slot < this.ammoSlots; slot++) {
				if (determineFiringStateOfSlot(slot) == AmmoFiringState.PROJECTILE_RAMMED) {
					if (!level.isClientSide()) {
						player.swing(hand);
						
						// Get slot charge and projectile
						IArtilleryCharge chargeStack = popAmmoCharge(slot);
						IArtilleryAmmo projectileStack = popAmmoProjectile(slot);
						
						// Need to offset spawn location of projectiles, since block pos will end up aligned to block edge
		        		double pX = blockpos.getX() + 0.5D;
		        		double pY = blockpos.getY() - 0.2D;
		        		double pZ = blockpos.getZ() + 0.5D;
						
		        		float finalVelocity = this.baseProjectileSpeed * chargeStack.getChargeAmount();
						float finalEffectiveRange = projectileStack.getProjectileEffectiveRange() * this.effectiveRangeModifier;
						float finalDeviation = this.baseProjectileDeviation * projectileStack.getProjectileDeviationModifier();
						
		            	List<BulletProjectile> entityProjectiles = projectileStack.createProjectiles(level, pX, pY, pZ, 
		            			projectileStack, this, player);
						
		            	entityProjectiles.forEach((t) ->
		            	{            		
		            		// Set location-based data
							t.setEffectiveRange(finalEffectiveRange);
							t.setLaunchLocation(t.blockPosition());
							t.setDamage(t.getDamage() * this.projectileDamageModifier);
									
							// Launch projectile
							t.shoot(pX, pY, pZ, this.shotPitch, this.shotYaw, 0.0F, finalVelocity, finalDeviation);
		            		
		                	level.addFreshEntity(t);
		            	});
		            	
		            	doFiringEffect(level, player, pX, pY, pZ);
		            	
		            	// TODO: Add lighting effects here
		            	
						setFiringCooldown(5);	 
						
						wasFired = true;
					}				
				}
			}
			
		}
		
		return wasFired;
	}
	
	private boolean processUnloadedState(Level level, BlockPos blockpos, BlockState state, Player player, InteractionHand hand) {
		ItemStack handItem = player.getItemInHand(hand);
		
		// Each slot must be loaded one at a time, if multiple
		for (int slot = 0; slot < this.ammoSlots; slot++) {
			switch(determineFiringStateOfSlot(slot)) {
				case PROJECTILE_RAMMED:
					// Skip loaded slots. Firing these is done in processLoadedState
					continue;
				case PROJECTILE_UNRAMMED:
					if (handItem.getItem() instanceof RamRodItem) {
						if (!level.isClientSide()) {
							player.swing(hand);
							ramAmmoProjectile(slot);
							level.playSound(player, blockpos, SoundEvents.CHICKEN_EGG, SoundSource.PLAYERS, 0.5f, 0.5f);
							return true;
						}
					}
					break;
				case POWDER_RAMMED:
					if (handItem.getItem() instanceof IArtilleryAmmo) {
						if (!level.isClientSide()) {
							player.swing(hand);
							pushAmmoProjectile(slot, handItem);
							if (!player.isCreative()) {
								handItem.shrink(1);
							}
							level.playSound(player, blockpos, SoundEvents.CHICKEN_EGG, SoundSource.PLAYERS, 0.5f, 0.5f);
							return true;
						}
					}
					break;
				case POWDER_UNRAMMED:
					if (handItem.getItem() instanceof RamRodItem) {
						if (!level.isClientSide()) {
							player.swing(hand);
							ramAmmoCharge(slot);
							level.playSound(player, blockpos, SoundEvents.CHICKEN_EGG, SoundSource.PLAYERS, 0.5f, 0.5f);
							return true;
						}
					}
					break;
				case UNLOADED:
					if (handItem.getItem() instanceof IArtilleryCharge) {
						if (!level.isClientSide()) {
							player.swing(hand);
							pushAmmoCharge(slot, handItem);
							if (!player.isCreative()) {
								handItem.shrink(1);
							}
							level.playSound(player, blockpos, SoundEvents.CHICKEN_EGG, SoundSource.PLAYERS, 0.5f, 0.5f);
							return true;
						}
					}
					break;
				default:
					break;
			}
		}
		
		
		return false;
	}
	
	protected boolean processInteraction(Level level, BlockPos blockpos, BlockState state, Player player, InteractionHand hand) {
		boolean interactResult = false;

		if (!player.isCrouching()) {
			// Interaction behavior is determined by overall load state, player item, and slot load state
			
			if (player.getItemInHand(hand) == ItemStack.EMPTY) {
				return false;
			}
			
			switch(determineOverallFiringState()) {
				case LOADED:
					if (processLoadedState(level, blockpos, state, player, hand)) {
						break;
					}
				case UNLOADED:
					processUnloadedState(level, blockpos, state, player, hand);
					break;
				default:
					break;
			}
		}
		
		return interactResult;
	}
	
	protected float getYawFromFacing() {
		switch(this.facing) {
			case SOUTH:
				return 0f;
			case WEST:
				return 90f;
			case NORTH:
				return 180f;
			case EAST:
				return -90f;
			default:
				return 0f;
		}
	}

	@Override
	public ArtilleryType getArtilleryType() {
		return this.artilleryType;
	}

	@Override 
	public ArtilleryFiringState determineOverallFiringState() {
		for (int slot = 0; slot < this.ammoSlots; slot++) {
			if (determineFiringStateOfSlot(slot) == AmmoFiringState.PROJECTILE_RAMMED) {
				return ArtilleryFiringState.LOADED; 
			}
		}
		return ArtilleryFiringState.UNLOADED;
	}
	
	@Override 
	public AmmoFiringState determineFiringStateOfSlot(int slot) {
		if (peekAmmoCharge(slot) != ItemStack.EMPTY) {
			
			if (isAmmoChargeRammed(slot)) {
				
				if (peekAmmoProjectile(slot) != ItemStack.EMPTY) {
					
					if (isAmmoProjectileRammed(slot)) {
						
						return AmmoFiringState.PROJECTILE_RAMMED;
					}
					
					return AmmoFiringState.PROJECTILE_UNRAMMED;
				}
				
				return AmmoFiringState.POWDER_RAMMED;
			}
			
			return AmmoFiringState.POWDER_UNRAMMED;
		}
		
		return AmmoFiringState.UNLOADED;
	}

	@Override
	public void setFiringCooldown(int firingCooldown) {
		this.firingCooldown = firingCooldown;
	}

	@Override
	public int getFiringCooldown() {
		return this.firingCooldown;
	}
	
	@Override
	public boolean isAmmoProjectileRammed(int slot) {
		ListTag ramStatusList = this.ammoTag.getList("ramStatus", Co);
	}

	@Override
	public boolean isAmmoChargeRammed(int slot) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void pushAmmoProjectile(int slot, ItemStack stackIn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pushAmmoCharge(int slot, ItemStack stackIn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ramAmmoProjectile(int slot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ramAmmoCharge(int slot) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IArtilleryAmmo popAmmoProjectile(int slot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IArtilleryCharge popAmmoCharge(int slot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack peekAmmoProjectile(int slot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack peekAmmoCharge(int slot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAmmoSlots() {
		return this.ammoSlots;
	}
	
	@Override
	public float getBaseProjectileSpeed() {
		return this.baseProjectileSpeed;
	}

	@Override
	public float getBaseProjectileDeviation() {
		return this.baseProjectileDeviation;
	}

	@Override
	public float getEffectiveRangeModifier() {
		return this.effectiveRangeModifier;
	}

	@Override
	public float getShotHeight() {
		return 0.5f;
	}

	@Override
	public float getMinShotPitch() {
		return 10f;
	}

	@Override
	public float getMaxShotPitch() {
		return 50f;
	}

	@Override
	public float getMinShotYaw() {
		float facingYaw = this.getYawFromFacing(); 
		return facingYaw - 20f;
	}

	@Override
	public float getMaxShotYaw() {
		float facingYaw = this.getYawFromFacing(); 
		return facingYaw + 20f;
	}

	@Override
	public void setShotPitch(float shotPitch) {
		this.shotPitch = shotPitch;
	}

	@Override
	public float getShotPitch() {
		return this.shotPitch;
	}

	@Override
	public void setShotYaw(float shotYaw) {
		this.shotYaw = shotYaw;
	}

	@Override
	public float getShotYaw() {
		return this.shotYaw;
	}

	@Override
	public void doFiringEffect(Level level, Player player, double posX, double posY, double posZ) {}

	@Override
	public float getProjectileDamageModifier() {
		return this.projectileDamageModifier;
	}

	/**
	 * Current artillery direction
	 */
	protected Direction facing = Direction.NORTH;
	
	/**
	 * Current yaw of this artillery for projectile launching
	 */
	protected float shotYaw = 0f;
	
	/**
	 * Current ptich of this artillery for projectile launching
	 */
	protected float shotPitch = 0f;
	
	/**
	 * Cooldown ticks before this artillery can fire again
	 */
	protected int firingCooldown = 0;
	
	/**
	 * NBT that contains ammo and charge information
	 */
	protected CompoundTag ammoTag = new CompoundTag();
	
	/**
	 * Type of this artillery
	 */
	protected ArtilleryType artilleryType = ArtilleryType.STATIONARY_CANNON;
	
	/**
	 * Total ammo slots of this artillery
	 */
	protected int ammoSlots = 1;
	
	/**
	 * Total charges per ammo loaded of this artillery
	 */
	protected int maxChargePerAmmo = 1;
	
	/**
	 * Base projectile speed of this artillery
	 */
	protected float baseProjectileSpeed = 1.5f;
	
	/**
	 * Effective range modifier of this artillery
	 */
	protected float effectiveRangeModifier = 1f;
	
	/**
	 * Base projectile deviation of this artillery
	 */
	protected float baseProjectileDeviation = 3.0f;
	
	/**
	 * Projectile damage modifier of this artillery
	 */
	protected float projectileDamageModifier = 1f;

	public static class ArtilleryProperties {

		/**
		 * Type of this artillery
		 */
		ArtilleryType artilleryType = ArtilleryType.STATIONARY_CANNON;
		
		/**
		 * Total ammo slots of this artillery
		 */
		protected int ammoSlots = 1;
		
		/**
		 * Total charges per ammo loaded of this artillery
		 */
		int maxChargePerAmmo = 1;
		
		/**
		 * Base projectile speed of this artillery
		 */
		float baseProjectileSpeed = 1.5f;
		
		/**
		 * Effective range modifier of this artillery
		 */
		float effectiveRangeModifier = 1f;
		
		/**
		 * Base projectile deviation of this artillery
		 */
		float baseProjectileDeviation = 3.0f;
		
		/**
		 * Projectile damage modifier of this artillery
		 */
		float projectileDamageModifier = 1f;

		public ArtilleryProperties artilleryType(ArtilleryType artilleryType) {
			this.artilleryType = artilleryType;
			return this;
		}

		public ArtilleryProperties ammoSlots(int ammoSlots) {
			this.ammoSlots = ammoSlots;
			return this;
		}
		
		public ArtilleryProperties maxChargePerAmmo(int maxChargePerAmmo) {
			this.maxChargePerAmmo = maxChargePerAmmo;
			return this;
		}

		public ArtilleryProperties baseProjectileSpeed(float baseProjectileSpeed) {
			this.baseProjectileSpeed = baseProjectileSpeed;
			return this;
		}

		public ArtilleryProperties effectiveRangeModifier(float effectiveRangeModifier) {
			this.effectiveRangeModifier = effectiveRangeModifier;
			return this;
		}

		public ArtilleryProperties baseProjectileDeviation(float baseProjectileDeviation) {
			this.baseProjectileDeviation = baseProjectileDeviation;
			return this;
		}

		public ArtilleryProperties projectileDamageModifier(float projectileDamageModifier) {
			this.projectileDamageModifier = projectileDamageModifier;
			return this;
		}
	}

	

	
}
