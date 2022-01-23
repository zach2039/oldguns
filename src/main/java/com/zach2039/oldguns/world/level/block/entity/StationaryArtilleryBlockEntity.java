package com.zach2039.oldguns.world.level.block.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.ammo.IArtilleryAmmo;
import com.zach2039.oldguns.api.ammo.IArtilleryCharge;
import com.zach2039.oldguns.api.artillery.AmmoFiringState;
import com.zach2039.oldguns.api.artillery.ArtilleryFiringState;
import com.zach2039.oldguns.api.artillery.ArtilleryType;
import com.zach2039.oldguns.api.artillery.IArtillery;
import com.zach2039.oldguns.network.ArtilleryBlockEntityUpdateMessage;
import com.zach2039.oldguns.world.entity.BulletProjectile;
import com.zach2039.oldguns.world.item.tools.LongMatchItem;
import com.zach2039.oldguns.world.item.tools.RamRodItem;
import com.zach2039.oldguns.world.level.block.NavalCannonBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.PacketDistributor;

public class StationaryArtilleryBlockEntity extends BlockEntity implements IArtillery {

	public StationaryArtilleryBlockEntity(BlockEntityType<?> type, BlockPos blockpos, BlockState state, ArtilleryProperties builder) {
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
	
	@Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

	public CompoundTag writeToTag(CompoundTag tag) {
		tag.putInt("artilleryType", this.artilleryType.ordinal());
		tag.putFloat("shotPitch", this.shotPitch);
		tag.putInt("firingCooldown", this.firingCooldown);
		tag.putByteArray("chargeRamStatus", this.chargeRamStatus);
		tag.putByteArray("projectileRamStatus", this.projectileRamStatus);
		
		ListTag ammoChargesList = new ListTag();
		this.ammoCharges.forEach((i) -> {
			ammoChargesList.add(i.serializeNBT());
		});
		tag.put("ammoCharges", ammoChargesList);
		
		ListTag ammoProjectileList = new ListTag();
		this.ammoProjectiles.forEach((i) -> {
			ammoProjectileList.add(i.serializeNBT());
		});
		tag.put("ammoProjectiles", ammoProjectileList);
		
		return tag;
	}
	
	public void readFromTag(CompoundTag tag) {
		this.artilleryType = ArtilleryType.values()[tag.getInt("artilleryType")];
		this.shotPitch = tag.getFloat("shotPitch");
		this.firingCooldown = tag.getInt("firingCooldown");
		this.chargeRamStatus = tag.getByteArray("chargeRamStatus");
		this.projectileRamStatus = tag.getByteArray("projectileRamStatus");
		
		this.ammoCharges = tag.getList("ammoCharges", Tag.TAG_COMPOUND).stream()
				.map(nbt -> ItemStack.of((CompoundTag) nbt))
				.collect(Collectors.toList());
		
		this.ammoProjectiles = (List<ItemStack>) tag.getList("ammoProjectiles", Tag.TAG_COMPOUND).stream()
				.map(nbt -> ItemStack.of((CompoundTag) nbt))
				.collect(Collectors.toList());
	}
	
	@Override
    public CompoundTag getUpdateTag() {
        return writeToTag(new CompoundTag());
    }

	@Override
    public void handleUpdateTag(CompoundTag tag) {
        load(tag);
    }
	
	@Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        load(pkt.getTag());
    }
	
	@Override
	public void load(final CompoundTag tag) {
		super.load(tag);
		
		readFromTag(tag);
	}

	@Override
    public void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        
        writeToTag(nbt);
    }
	
	public static void tick(final Level level, final BlockPos pos, final BlockState state, final StationaryArtilleryBlockEntity blockEntity) {
		if (!blockEntity.hasLevel())
			return;
		
		if (blockEntity.firingCooldown > 0) {
			blockEntity.firingCooldown = blockEntity.firingCooldown - 1;
		}
		
		blockEntity.facing = state.getValue(NavalCannonBlock.HORIZONTAL_ROTATION);
		
		blockEntity.shotYaw = blockEntity.getYawFromFacing();
	}
	
	private boolean processLoadedState(Level level, BlockPos blockpos, BlockState state, Player player, InteractionHand hand) {
		ItemStack handItem = player.getItemInHand(hand);
		boolean wasFired = false;
		
		if (handItem.getItem() instanceof LongMatchItem) {
			// Try to fire all loaded slots
			for (int slot = 0; slot < this.ammoSlots; slot++) {
				if (determineFiringStateOfSlot(slot) == AmmoFiringState.PROJECTILE_RAMMED) {
					player.swing(hand);
					
					// Get slot charge and projectile
					IArtilleryCharge chargeStack = getAmmoCharge(slot);
					IArtilleryAmmo projectileStack = getAmmoProjectile(slot);
					
					// Need to offset spawn location of projectiles, since block pos will end up aligned to block edge
	        		double pX = blockpos.getX() + 0.5D;
	        		double pY = blockpos.getY() + getShotHeight();
	        		double pZ = blockpos.getZ() + 0.5D;
					
	        		float finalVelocity = this.baseProjectileSpeed * chargeStack.getChargeAmount();
					float finalEffectiveRange = projectileStack.getProjectileEffectiveRange() * this.effectiveRangeModifier;
					float finalDeviation = this.baseProjectileDeviation * projectileStack.getProjectileDeviationModifier();
					
					if (!level.isClientSide()){
		            	List<BulletProjectile> entityProjectiles = projectileStack.createProjectiles(level, pX, pY, pZ, projectileStack, this, player);
						
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
					}
					
					setFiringCooldown(5);
					
					wasFired = true;			
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
						}
						level.playSound(player, blockpos, SoundEvents.CHICKEN_EGG, SoundSource.PLAYERS, 0.5f, 0.5f);
						return true;
					}
					break;
				case POWDER_RAMMED:
					if (handItem.getItem() instanceof IArtilleryAmmo) {
						if (!level.isClientSide()) {
							player.swing(hand);
							putAmmoProjectile(slot, handItem.copy());
							if (!player.isCreative()) {
								handItem.shrink(1);
							}
						}
						level.playSound(player, blockpos, SoundEvents.CHICKEN_EGG, SoundSource.PLAYERS, 0.5f, 0.5f);
						return true;
					}
					break;
				case POWDER_UNRAMMED:
					if (handItem.getItem() instanceof RamRodItem) {
						if (!level.isClientSide()) {
							player.swing(hand);
							ramAmmoCharge(slot);
						}
						level.playSound(player, blockpos, SoundEvents.CHICKEN_EGG, SoundSource.PLAYERS, 0.5f, 0.5f);
						return true;
					}
					break;
				case UNLOADED:
					if (handItem.getItem() instanceof IArtilleryCharge) {
						if (!level.isClientSide()) {
							player.swing(hand);
							putAmmoCharge(slot, handItem.copy());
							if (!player.isCreative()) {
								handItem.shrink(1);
							}
						}
						level.playSound(player, blockpos, SoundEvents.CHICKEN_EGG, SoundSource.PLAYERS, 0.5f, 0.5f);
						return true;
					}
					break;
				default:
					break;
			}
		}
		
		
		return false;
	}
	
	public InteractionResult processInteraction(Level level, BlockPos blockpos, BlockState state, Player player, InteractionHand hand) {
		InteractionResult result = InteractionResult.PASS;
		
		if (player.getItemInHand(hand) == ItemStack.EMPTY) {
			if (!level.isClientSide()) {
				this.shotPitch = Mth.clamp(this.shotPitch + (!player.isCrouching() ? -2f : 2f), getMinShotPitch(), getMaxShotPitch());
			}
		} else if (!player.isCrouching()) {
			// Interaction behavior is determined by overall load state, player item, and slot load state
			
			switch(determineOverallFiringState()) {
				case LOADED:
					if (processLoadedState(level, blockpos, state, player, hand)) {
						break;
					}
				case UNLOADED:
					processUnloadedState(level, blockpos, state, player, hand);
				default:
					break;
			}
		}
		
		if (!level.isClientSide()) {
			OldGuns.NETWORK.send(
	                PacketDistributor.TRACKING_CHUNK.with(() -> this.level.getChunkAt(this.worldPosition)),
	                new ArtilleryBlockEntityUpdateMessage(this.worldPosition, this.writeToTag(new CompoundTag())));
		}
		
		return result;
	}
	
	public void setFacing(Direction facing) {
		this.facing = facing;
	}
	
	public float getYawFromFacing() {
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
		if (peekAmmoCharge(slot) != null) {
			
			if (isAmmoChargeRammed(slot)) {
				
				if (peekAmmoProjectile(slot) != null) {
					
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
		return (projectileRamStatus[slot] != 0);
	}

	@Override
	public boolean isAmmoChargeRammed(int slot) {
		return (chargeRamStatus[slot] != 0);
	}
	
	@Override
	public void putAmmoProjectile(int slot, ItemStack stackIn) {
		ammoProjectiles.add(slot, stackIn);
	}

	@Override
	public void putAmmoCharge(int slot, ItemStack stackIn) {		
		ammoCharges.add(slot, stackIn);
	}

	@Override
	public void ramAmmoProjectile(int slot) {
		projectileRamStatus[slot] = 1;
	}

	@Override
	public void ramAmmoCharge(int slot) {		
		chargeRamStatus[slot] = 1;
	}

	@Override
	public IArtilleryAmmo getAmmoProjectile(int slot) {
		IArtilleryAmmo projectileItem = (IArtilleryAmmo) ammoProjectiles.get(slot).getItem();
		
		ammoProjectiles.remove(slot);
		projectileRamStatus[slot] = 0;
		
		return projectileItem;
	}

	@Override
	public IArtilleryCharge getAmmoCharge(int slot) {
		IArtilleryCharge chargeItem = (IArtilleryCharge) ammoCharges.get(slot).getItem();
		
		ammoCharges.remove(slot);
		chargeRamStatus[slot] = 0;
		
		return chargeItem;
	}

	@Override
	public IArtilleryAmmo peekAmmoProjectile(int slot) {
		if (ammoProjectiles.isEmpty())
			return null;
		
		IArtilleryAmmo projectileItem = (IArtilleryAmmo) ammoProjectiles.get(slot).getItem();
		
		return projectileItem;
	}

	@Override
	public IArtilleryCharge peekAmmoCharge(int slot) {
		if (ammoCharges.isEmpty())
			return null;
		
		IArtilleryCharge chargeItem = (IArtilleryCharge) ammoCharges.get(slot).getItem();
		
		return chargeItem;
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
		return -16f;
	}

	@Override
	public float getMaxShotPitch() {
		return 20f;
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
	 * Current pitch of this artillery for projectile launching
	 */
	protected float shotPitch = 0f;
	
	/**
	 * Cooldown ticks before this artillery can fire again
	 */
	protected int firingCooldown = 0;
	
	/**
	 * NBT that contains ammo and charge information
	 */
	protected List<ItemStack> ammoCharges = new ArrayList<ItemStack>();
	protected List<ItemStack> ammoProjectiles = new ArrayList<ItemStack>();
	protected byte[] chargeRamStatus = new byte[8];
	protected byte[] projectileRamStatus = new byte[8];
	
	/**
	 * Type of this artillery
	 */
	protected ArtilleryType artilleryType = ArtilleryType.NAVAL_CANNON;
	
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
		ArtilleryType artilleryType = ArtilleryType.NAVAL_CANNON;
		
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
