package com.zach2039.oldguns.world.level.block.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.ammo.Ammo;
import com.zach2039.oldguns.api.ammo.ArtilleryAmmo;
import com.zach2039.oldguns.api.ammo.ArtilleryCharge;
import com.zach2039.oldguns.api.artillery.AmmoFiringState;
import com.zach2039.oldguns.api.artillery.ArtilleryFiringState;
import com.zach2039.oldguns.api.artillery.ArtilleryType;
import com.zach2039.oldguns.api.artillery.Artillery;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.network.ArtilleryBlockEntityUpdateMessage;
import com.zach2039.oldguns.world.entity.BulletProjectile;
import com.zach2039.oldguns.world.item.ammo.artillery.ArtilleryAmmoItem;
import com.zach2039.oldguns.world.item.tools.LongMatchItem;
import com.zach2039.oldguns.world.item.tools.RamRodItem;
import com.zach2039.oldguns.world.level.block.MediumNavalCannonBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.TranslatableComponent;
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
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.network.PacketDistributor;

public abstract class StationaryArtilleryBlockEntity extends BlockEntity implements Artillery {

	public StationaryArtilleryBlockEntity(BlockEntityType<?> type, BlockPos blockpos, BlockState state, ArtilleryProperties builder) {
		super(type, blockpos, state);	
		this.artilleryType = builder.artilleryType;
		this.ammoSlots = builder.ammoSlots;	
		this.baseProjectileDeviation = builder.baseProjectileDeviation;
	}
	
	@Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

	public CompoundTag writeToTag(CompoundTag tag) {
		tag.putInt("artilleryType", this.artilleryType.ordinal());
		tag.putFloat("shotPitch", this.shotPitch);
		tag.putFloat("shotYaw", this.shotYaw);
		tag.putInt("firingCooldown", this.firingCooldown);
		
		return tag;
	}
	
	public void readFromTag(CompoundTag tag) {
		this.artilleryType = ArtilleryType.values()[tag.getInt("artilleryType")];
		this.shotPitch = tag.getFloat("shotPitch");
		this.shotYaw = tag.getFloat("shotYaw");
		this.firingCooldown = tag.getInt("firingCooldown");
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
	
	public abstract InteractionResult processInteraction(Level level, BlockPos blockpos, BlockState state, Player player, InteractionHand hand);
	
	public static void tick(final Level level, final BlockPos blockpos, final BlockState state, final StationaryArtilleryBlockEntity blockEntity) {
		if (!blockEntity.hasLevel())
			return;
		
		if (blockEntity.firingCooldown > 0) {
			blockEntity.firingCooldown = blockEntity.firingCooldown - 1;
		} else {
			if (!level.isClientSide()) {
				level.setBlock(blockpos, state.setValue(BlockStateProperties.LIT, false), 2);
			}
		}
		
		blockEntity.facing = state.getValue(MediumNavalCannonBlock.HORIZONTAL_ROTATION);
		
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
	public ArtilleryType getArtilleryType() {
		return this.artilleryType;
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
	public void putAmmoProjectile(int slot, ItemStack stackIn) {
		ammoProjectiles.add(slot, stackIn);
	}
	
	@Override
	public Ammo getAmmoProjectile(int slot) {
		Ammo projectileItem = (Ammo) ammoProjectiles.get(slot).getItem();
		
		ammoProjectiles.remove(slot);
		
		return projectileItem;
	}

	@Override
	public Ammo peekAmmoProjectile(int slot) {
		if (ammoProjectiles.isEmpty())
			return null;
		
		Ammo projectileItem = (Ammo) ammoProjectiles.get(slot).getItem();
		
		return projectileItem;
	}

	@Override
	public int getAmmoSlots() {
		return this.ammoSlots;
	}

	@Override
	public float getBaseProjectileDeviation() {
		return this.baseProjectileDeviation;
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
	protected List<ItemStack> ammoProjectiles = new ArrayList<ItemStack>();
	
	/**
	 * Type of this artillery
	 */
	protected ArtilleryType artilleryType = ArtilleryType.NAVAL_CANNON;
	
	/**
	 * Total ammo slots of this artillery
	 */
	protected int ammoSlots = 1;
	
	/**
	 * Base projectile deviation of this artillery
	 */
	protected float baseProjectileDeviation = 3.0f;

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
