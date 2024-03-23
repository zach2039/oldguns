package com.zach2039.oldguns.world.level.block.entity;

import com.zach2039.oldguns.api.ammo.Ammo;
import com.zach2039.oldguns.api.ammo.ArtilleryAmmo;
import com.zach2039.oldguns.api.ammo.ArtilleryCharge;
import com.zach2039.oldguns.api.artillery.AmmoFiringState;
import com.zach2039.oldguns.api.artillery.Artillery;
import com.zach2039.oldguns.api.artillery.ArtilleryFiringState;
import com.zach2039.oldguns.api.artillery.CannonArtillery;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.network.ArtilleryBlockEntityUpdateMessage;
import com.zach2039.oldguns.util.ModRegistryUtil;
import com.zach2039.oldguns.world.entity.BulletProjectile;
import com.zach2039.oldguns.world.item.ammo.artillery.ArtilleryAmmoItem;
import com.zach2039.oldguns.world.item.tools.LongMatchItem;
import com.zach2039.oldguns.world.item.tools.RamRodItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class StationaryCannonBlockEntity extends StationaryArtilleryBlockEntity implements Artillery, CannonArtillery {

	public StationaryCannonBlockEntity(BlockEntityType<?> type, BlockPos blockpos, BlockState state, ArtilleryProperties builder) {
		super(type, blockpos, state, builder);
		this.effectiveRangeModifier = builder.effectiveRangeModifier;
		this.maxChargePerAmmo = builder.maxChargePerAmmo;
		this.baseProjectileSpeed = builder.baseProjectileSpeed;
		this.effectiveRangeModifier = builder.effectiveRangeModifier;
		this.projectileDamageModifier = builder.projectileDamageModifier;
	}

	@Override
	public CompoundTag writeToTag(CompoundTag tag) {
		super.writeToTag(tag);
		
		tag.putByteArray("chargeRamStatus", this.chargeRamStatus);
		tag.putByteArray("projectileRamStatus", this.projectileRamStatus);
		
		ListTag ammoChargesList = new ListTag();
		this.ammoCharges.forEach((i) -> {
			ammoChargesList.add(i.getTag());
		});
		tag.put("ammoCharges", ammoChargesList);
		
		ListTag ammoProjectileList = new ListTag();
		this.ammoProjectiles.forEach((i) -> {
			ammoProjectileList.add(i.getTag());
		});
		tag.put("ammoProjectiles", ammoProjectileList);
		
		return tag;
	}

	@Override
	public void readFromTag(CompoundTag tag) {
		super.readFromTag(tag);
		
		this.chargeRamStatus = tag.getByteArray("chargeRamStatus");
		this.projectileRamStatus = tag.getByteArray("projectileRamStatus");
		
		this.ammoCharges = tag.getList("ammoCharges", Tag.TAG_COMPOUND).stream()
				.map(nbt -> ItemStack.of((CompoundTag) nbt))
				.collect(Collectors.toList());
		
		this.ammoProjectiles = (List<ItemStack>) tag.getList("ammoProjectiles", Tag.TAG_COMPOUND).stream()
				.map(nbt -> ItemStack.of((CompoundTag) nbt))
				.collect(Collectors.toList());
	}
	
	private boolean processLoadedState(Level level, BlockPos blockpos, Player player, InteractionHand hand) {
		ItemStack handItem = player.getItemInHand(hand);
		boolean wasFired = false;
		
		if (handItem.getItem() instanceof LongMatchItem) {
			// Try to fire all loaded slots
			for (int slot = 0; slot < this.ammoSlots; slot++) {
				if (determineFiringStateOfSlot(slot) == AmmoFiringState.PROJECTILE_RAMMED) {
					player.swing(hand);
					
					// Get slot charge and projectile
					ArtilleryCharge chargeStack = getAmmoCharge(slot);
					ArtilleryAmmoItem projectileStack = (ArtilleryAmmoItem) getAmmoProjectile(slot);
					
					// Need to offset spawn location of projectiles, since block pos will end up aligned to block edge
	        		double pX = blockpos.getX() + 0.5D;
	        		double pY = blockpos.getY() + getShotHeight();
	        		double pZ = blockpos.getZ() + 0.5D;
					
	        		float finalVelocity = this.baseProjectileSpeed * chargeStack.getChargeAmount();
					float finalEffectiveRange = projectileStack.getProjectileEffectiveRange() * this.effectiveRangeModifier;
					float finalDeviation = this.baseProjectileDeviation * projectileStack.getProjectileDeviationModifier();
					
					setFiringCooldown(5);
					
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
		            	
		            	level.setBlock(blockpos, this.getBlockState().setValue(BlockStateProperties.LIT, true), 2);
					}

					wasFired = true;			
				}
			}
			
		}
		
		return wasFired;
	}
	
	private boolean processUnloadedState(Level level, BlockPos blockpos, Player player, InteractionHand hand) {
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
					if (handItem.getItem() instanceof ArtilleryAmmo) {
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
					if (handItem.getItem() instanceof ArtilleryCharge) {
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
	
	@Override
	public InteractionResult processInteraction(Level level, BlockPos blockpos, Player player, InteractionHand hand) {
		InteractionResult result = InteractionResult.PASS;
		ItemStack handItem = player.getItemInHand(hand);
		
		if (handItem == ItemStack.EMPTY && !level.isClientSide()) {
			this.shotPitch = Mth.clamp(this.shotPitch + (!player.isCrouching() ? -2f : 2f), getMinShotPitch(), getMaxShotPitch());
		} else if (handItem.getItem() == ModItems.GUNNERS_QUADRANT.get() && !level.isClientSide()) {
			player.sendSystemMessage(Component.translatable("text.oldguns.artillery_name.message", Component.translatable(ModRegistryUtil.getKey(getType()).toString().replace(':', '.'))));
			player.sendSystemMessage(Component.translatable("text.oldguns.artillery_max_slots.message", this.ammoSlots));
			for (int slot = 0; slot < this.ammoSlots; slot++) {
				player.sendSystemMessage(Component.translatable("text.oldguns.artillery_slot_state.message", slot, determineFiringStateOfSlot(slot).name()));
			}
		} else if (!player.isCrouching()) {
			// Interaction behavior is determined by overall load state, player item, and slot load state
			
			switch(determineOverallFiringState()) {
				case LOADED:
					if (processLoadedState(level, blockpos, player, hand)) {
						break;
					}
				case UNLOADED:
					processUnloadedState(level, blockpos, player, hand);
				default:
					break;
			}
		}
		
		if (!level.isClientSide()) {
			PacketDistributor.TRACKING_CHUNK.with(this.level.getChunkAt(this.worldPosition)).send(
					new ArtilleryBlockEntityUpdateMessage.Data(this.worldPosition, this.writeToTag(new CompoundTag()))
			);
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
	public Ammo getAmmoProjectile(int slot) {
		Ammo projectileItem = super.getAmmoProjectile(slot);
		projectileRamStatus[slot] = 0;
		
		return projectileItem;
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
	public ArtilleryCharge getAmmoCharge(int slot) {
		ArtilleryCharge chargeItem = (ArtilleryCharge) ammoCharges.get(slot).getItem();
		
		ammoCharges.remove(slot);
		chargeRamStatus[slot] = 0;
		
		return chargeItem;
	}

	@Override
	public ArtilleryCharge peekAmmoCharge(int slot) {
		if (ammoCharges.isEmpty())
			return null;
		
		ArtilleryCharge chargeItem = (ArtilleryCharge) ammoCharges.get(slot).getItem();
		
		return chargeItem;
	}
	
	@Override
	public float getBaseProjectileSpeed() {
		return this.baseProjectileSpeed;
	}

	@Override
	public float getEffectiveRangeModifier() {
		return this.effectiveRangeModifier;
	}

	@Override
	public float getProjectileDamageModifier() {
		return this.projectileDamageModifier;
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

	/**
	 * NBT that contains ammo and charge information
	 */
	protected List<ItemStack> ammoCharges = new ArrayList<ItemStack>();
	protected byte[] chargeRamStatus = new byte[8];
	protected byte[] projectileRamStatus = new byte[8];
	
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
	 * Projectile damage modifier of this artillery
	 */
	protected float projectileDamageModifier = 1f;
}
