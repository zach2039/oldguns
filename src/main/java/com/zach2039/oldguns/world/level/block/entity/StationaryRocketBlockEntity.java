package com.zach2039.oldguns.world.level.block.entity;

import java.util.List;
import java.util.stream.Collectors;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.ammo.RocketArtilleryAmmo;
import com.zach2039.oldguns.api.artillery.Artillery;
import com.zach2039.oldguns.api.artillery.ArtilleryFiringState;
import com.zach2039.oldguns.api.artillery.RocketArtillery;
import com.zach2039.oldguns.api.artillery.RocketFiringState;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.network.ArtilleryBlockEntityUpdateMessage;
import com.zach2039.oldguns.world.entity.RocketProjectile;
import com.zach2039.oldguns.world.item.ammo.artillery.ArtilleryRocketAmmoItem;
import com.zach2039.oldguns.world.item.tools.LongMatchItem;
import com.zach2039.oldguns.world.level.block.CongreveRocketStandBlock;
import com.zach2039.oldguns.world.level.block.MediumNavalCannonBlock;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.TranslatableComponent;
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
import net.minecraftforge.network.PacketDistributor;

public abstract class StationaryRocketBlockEntity extends StationaryArtilleryBlockEntity implements Artillery, RocketArtillery {

	public StationaryRocketBlockEntity(BlockEntityType<?> type, BlockPos blockpos, BlockState state, ArtilleryProperties builder) {
		super(type, blockpos, state, builder);
	}
	
	public CompoundTag writeToTag(CompoundTag tag) {
		super.writeToTag(tag);
		
		ListTag ammoProjectileList = new ListTag();
		this.ammoProjectiles.forEach((i) -> {
			ammoProjectileList.add(i.serializeNBT());
		});
		tag.put("ammoProjectiles", ammoProjectileList);
		
		return tag;
	}
	
	public void readFromTag(CompoundTag tag) {
		super.readFromTag(tag);
		
		this.ammoProjectiles = (List<ItemStack>) tag.getList("ammoProjectiles", Tag.TAG_COMPOUND).stream()
				.map(nbt -> ItemStack.of((CompoundTag) nbt))
				.collect(Collectors.toList());
	}
	
	private boolean processLoadedState(Level level, BlockPos blockpos, BlockState state, Player player, InteractionHand hand) {
		ItemStack handItem = player.getItemInHand(hand);
		boolean wasFired = false;
		
		if (handItem.getItem() instanceof LongMatchItem) {
			if (getFiringCooldown() > 0) {
				if (!level.isClientSide()) {
					player.sendMessage(new TranslatableComponent("text.oldguns.artillery_not_ready.message").withStyle(ChatFormatting.RED), player.getUUID());
				}
				return false;
			}
			// Try to fire all loaded slots
			for (int slot = 0; slot < this.ammoSlots; slot++) {
				if (determineFiringStateOfSlot(slot) == RocketFiringState.ROCKET_LOADED) {
					player.swing(hand);
					
					// Get slot charge and projectile
					ArtilleryRocketAmmoItem projectileStack = (ArtilleryRocketAmmoItem) getAmmoProjectile(slot);
					
					// Need to offset spawn location of projectiles, since block pos will end up aligned to block edge
	        		double pX = blockpos.getX() + 0.5D;
	        		double pY = blockpos.getY() + getShotHeight();
	        		double pZ = blockpos.getZ() + 0.5D;
					
	        		//float finalVelocity = this.baseProjectileSpeed;
	        		float finalVelocity = 0.5f;
					float finalEffectiveRange = projectileStack.getProjectileEffectiveRange() * this.effectiveRangeModifier;
					float finalDeviation = this.baseProjectileDeviation * projectileStack.getProjectileDeviationModifier() * 2f;
					
					if (!level.isClientSide()){
		            	List<RocketProjectile> entityProjectiles = projectileStack.createProjectiles(level, pX, pY, pZ, projectileStack, this, player);
						
		            	entityProjectiles.forEach((t) ->
		            	{            		
		            		// Set location-based data
							t.setEffectiveRange(finalEffectiveRange);
							t.setLaunchLocation(t.blockPosition());
							t.setDamage(t.getDamage() * this.projectileDamageModifier);
									
							// Launch projectile
							t.setXRot(this.shotYaw);
							t.setYRot(this.shotPitch - 40f);
							t.shoot(pX, pY, pZ, this.shotPitch - 40f, this.shotYaw, 0.0F, finalVelocity, finalDeviation);
		            		
		                	level.addFreshEntity(t);
		            	});
		            	
		            	doFiringEffect(level, player, pX, pY, pZ);
		            	
		            	level.setBlock(blockpos, state.setValue(BlockStateProperties.LIT, true), 2);
					}

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
				case ROCKET_LOADED:
					// Skip loaded slots. Firing these is done in processLoadedState
					continue;
				case UNLOADED:
					if (handItem.getItem() instanceof RocketArtilleryAmmo) {
						if (!level.isClientSide()) {
							player.swing(hand);
							putAmmoProjectile(slot, handItem.copy());
							if (!player.isCreative()) {
								handItem.shrink(1);
							}
						}
						setFiringCooldown(10);
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
		ItemStack handItem = player.getItemInHand(hand);
		
		if (handItem == ItemStack.EMPTY) {
			if (!level.isClientSide()) {
				this.shotPitch = Mth.clamp(this.shotPitch + (!player.isCrouching() ? -2f : 2f), getMinShotPitch(), getMaxShotPitch());
			}
			level.playSound(player, blockpos, SoundEvents.WOOD_PLACE, SoundSource.PLAYERS, 0.5f, 0.5f);
		} else if (handItem.getItem() == ModItems.GUNNERS_QUADRANT.get() && !level.isClientSide()) {
			player.sendMessage(new TranslatableComponent("text.oldguns.artillery_name.message", new TranslatableComponent(getType().getRegistryName().toString().replace(':', '.'))), player.getUUID());
			player.sendMessage(new TranslatableComponent("text.oldguns.artillery_max_slots.message", this.ammoSlots), player.getUUID());
			for (int slot = 0; slot < this.ammoSlots; slot++) {
				player.sendMessage(new TranslatableComponent("text.oldguns.artillery_slot_state.message", slot, determineFiringStateOfSlot(slot).name()), player.getUUID());
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
	
	public static void tick(final Level level, final BlockPos blockpos, final BlockState state, final StationaryArtilleryBlockEntity blockEntity) {
		if (!blockEntity.hasLevel())
			return;
		
		if (blockEntity.firingCooldown > 0) {
			blockEntity.firingCooldown = blockEntity.firingCooldown - 1;
		} else {
			level.setBlock(blockpos, state.setValue(BlockStateProperties.LIT, false), 2);
		}
		
		blockEntity.facing = state.getValue(CongreveRocketStandBlock.HORIZONTAL_ROTATION);
		
		blockEntity.shotYaw = blockEntity.getYawFromFacing();
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
			if (determineFiringStateOfSlot(slot) == RocketFiringState.ROCKET_LOADED) {
				return ArtilleryFiringState.LOADED; 
			}
		}
		return ArtilleryFiringState.UNLOADED;
	}
	
	@Override 
	public RocketFiringState determineFiringStateOfSlot(int slot) {
		if (peekAmmoProjectile(slot) != null) {
					
			return RocketFiringState.ROCKET_LOADED;
		}
		
		return RocketFiringState.UNLOADED;
	}

	@Override
	public float getShotHeight() {
		float yM = -Mth.sin((float) ((this.shotPitch / 180F) * 3.141593F)) * 1.5f - 0.1F;
		float y = 1.8f - (Mth.abs(yM) * 1.7f);
		OldGuns.LOGGER.info("h " + y);
		return y;
	}

	@Override
	public float getMinShotPitch() {
		return -4f;
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
