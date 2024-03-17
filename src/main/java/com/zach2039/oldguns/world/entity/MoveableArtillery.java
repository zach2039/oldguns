package com.zach2039.oldguns.world.entity;

import com.zach2039.oldguns.api.ammo.Ammo;
import com.zach2039.oldguns.api.artillery.AmmoFiringState;
import com.zach2039.oldguns.api.artillery.Artillery;
import com.zach2039.oldguns.api.artillery.ArtilleryType;
import com.zach2039.oldguns.init.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public abstract class MoveableArtillery extends Entity implements Artillery {

	private static final EntityDataAccessor<Integer> TIME_SINCE_HIT = SynchedEntityData.<Integer>defineId(MoveableArtillery.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> FORWARD_DIRECTION = SynchedEntityData.<Integer>defineId(MoveableArtillery.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Float> DAMAGE_TAKEN = SynchedEntityData.<Float>defineId(MoveableArtillery.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<Integer> HURT_DIRECTION = SynchedEntityData.defineId(MoveableArtillery.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Float> WHEEL_SPIN = SynchedEntityData.<Float>defineId(MoveableArtillery.class, EntityDataSerializers.FLOAT);

	private static final EntityDataAccessor<Integer> ARTILLERY_TYPE = SynchedEntityData.<Integer>defineId(MoveableArtillery.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> FIRING_STATE = SynchedEntityData.<Integer>defineId(MoveableArtillery.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<CompoundTag> DATA_TAG = SynchedEntityData.<CompoundTag>defineId(MoveableArtillery.class, EntityDataSerializers.COMPOUND_TAG);
	private static final EntityDataAccessor<Integer> FIRING_COOLDOWN = SynchedEntityData.<Integer>defineId(MoveableArtillery.class, EntityDataSerializers.INT);

	private float invFriction;
	private float deltaRotation;
	private int lerpSteps;
	private double lerpX;
	private double lerpY;
	private double lerpZ;
	private double lerpYRot;
	private double lerpXRot;
	private boolean inputLeft;
	private boolean inputRight;
	private boolean inputUp;
	private boolean inputDown;
	private double lastYd;

	public Entity pullingEntity;
	public boolean fellLastTick = false;

	public MoveableArtillery(EntityType<? extends MoveableArtillery> entity, Level level, ArtilleryProperties builder) {
		super(entity, level);
		this.blocksBuilding = true;
		this.artilleryType = builder.artilleryType;
		this.ammoSlots = builder.ammoSlots;	
		this.baseProjectileDeviation = builder.baseProjectileDeviation;
	}

	public MoveableArtillery(EntityType<? extends MoveableArtillery> entity, Level level, double x, double y, double z, ArtilleryProperties builder) {
		this(entity, level, builder);
		this.setPos(x, y, z);
		this.xo = x;
		this.yo = y;
		this.zo = z;
	}

	@Override
	protected void defineSynchedData() {
		
		this.entityData.define(ARTILLERY_TYPE, Integer.valueOf(ArtilleryType.CANNON.ordinal()));

		this.entityData.define(TIME_SINCE_HIT, Integer.valueOf(0));
		this.entityData.define(FORWARD_DIRECTION, Integer.valueOf(1));
		this.entityData.define(HURT_DIRECTION, Integer.valueOf(1));
		this.entityData.define(DAMAGE_TAKEN, Float.valueOf(0.0F));

		this.entityData.define(WHEEL_SPIN, Float.valueOf(0.0F));
		this.entityData.define(FIRING_STATE, Integer.valueOf(AmmoFiringState.UNLOADED.ordinal()));
		this.entityData.define(FIRING_COOLDOWN, Integer.valueOf(0));

		this.entityData.define(DATA_TAG, new CompoundTag());
	}

	public static boolean canVehicleCollide(Entity entityA, Entity entityB) {
		return (entityB.canBeCollidedWith() || entityB.isPushable()) && !entityA.isPassengerOfSameVehicle(entityB);
	}
	
	@Override
	public boolean canCollideWith(Entity pEntity) {
		return canVehicleCollide(this, pEntity);
	}

	@Override
	public boolean canBeCollidedWith() {
		return true;
	}

	@Override
	public boolean isPushable() {
		return true;
	}

	private void spawnDrops() {
		this.spawnAtLocation(this.getDropItem());
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (this.isInvulnerableTo(source)) {
			return false;
		} else if (!this.level().isClientSide && !this.isRemoved()) {
			// Reduce damage taken if source was not player
			float dmgMod = 1.0f;
			//if (!(source.getEntity() instanceof Player) || source..isProjectile()) {
			if (!(source.getEntity() instanceof Player)) {
				dmgMod = 0.1f;
			}

			this.setHurtDir(-this.getHurtDir());
			this.setHurtTime(10);
			this.setDamage(this.getDamage() + (amount * 10.0F) * dmgMod);
			this.markHurt();
			this.gameEvent(GameEvent.ENTITY_DAMAGE, source.getEntity());
			boolean flag = source.getEntity() instanceof Player && ((Player)source.getEntity()).getAbilities().instabuild;
			if (flag || this.getDamage() > 40.0F) {
				if (!flag && this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
					this.spawnDrops();
				}

				this.discard();
			}

			return true;
		} else {
			return true;
		}
	}

	public ItemStack getDropItem() {
		return ItemStack.EMPTY;
	};

	@Override
	public void animateHurt(float delta) {
		this.setHurtDir(-this.getHurtDir());
		this.setHurtTime(10);
		this.setDamage(this.getDamage() * 11.0F);
	}

	@Override
	public void lerpTo(double p_38299_, double p_38300_, double p_38301_, float p_38302_, float p_38303_, int p_38304_, boolean p_38305_) {
		this.lerpX = p_38299_;
		this.lerpY = p_38300_;
		this.lerpZ = p_38301_;
		this.lerpYRot = (double)p_38302_;
		this.lerpXRot = (double)p_38303_;
		this.lerpSteps = 10;
	}

	@Override
	public Direction getMotionDirection() {
		return this.getDirection().getClockWise();
	}

	@Override 
	public boolean mayInteract(Level level, BlockPos blockPos) {
		return true;
	}
	
	@Override
	public InteractionResult interactAt(Player player, Vec3 vec3, InteractionHand hand) {
		return interact(player, hand);
	}
	
	@Override
	public InteractionResult interact(Player player, InteractionHand hand) {
		InteractionResult ret = super.interact(player, hand);
		if (ret.consumesAction()) return ret;
		
		if (player.isSecondaryUseActive()) {
			if (!this.level().isClientSide) {
				return player.startRiding(this) ? InteractionResult.CONSUME : InteractionResult.PASS;
			} else {
				return InteractionResult.SUCCESS;
			}
		} else if (this.isVehicle()) {
			return InteractionResult.PASS;
		} else {
			return processInteraction(level(), blockPosition(), player, hand);
		}
	}

	@Override
	public void positionRider(Entity entity, Entity.MoveFunction moveFunction) {
		// FIXME : Need to use moveFunction maybe
		if (this.hasPassenger(entity)) {
			float f = 0.0F;
			float f1 = (float)((this.isRemoved() ? (double)0.01F : this.getPassengersRidingOffset()) + entity.getMyRidingOffset());
			if (this.getPassengers().size() > 1) {
				int i = this.getPassengers().indexOf(entity);
				if (i == 0) {
					f = 0.2F;
				} else {
					f = -0.6F;
				}

				if (entity instanceof Animal) {
					f = (float)((double)f + 0.2D);
				}
			}

			Vec3 vec3 = (new Vec3((double)f, 0.0D, 0.0D)).yRot(-this.getYRot() * ((float)Math.PI / 180F) - ((float)Math.PI / 2F));
			entity.setPos(this.getX() + vec3.x, this.getY() + (double)f1, this.getZ() + vec3.z);
			entity.setYRot(entity.getYRot() + this.deltaRotation);
			entity.setYHeadRot(entity.getYHeadRot() + this.deltaRotation);
			this.clampRotation(entity);
			if (entity instanceof Animal && this.getPassengers().size() > 1) {
				int j = entity.getId() % 2 == 0 ? 90 : 270;
				entity.setYBodyRot(((Animal)entity).yBodyRot + (float)j);
				entity.setYHeadRot(entity.getYHeadRot() + (float)j);
			}
		}
	}

	protected void clampRotation(Entity entity) {
		entity.setYBodyRot(this.getYRot());
		float f = Mth.wrapDegrees(entity.getYRot() - this.getYRot());
		float f1 = Mth.clamp(f, -105.0F, 105.0F);
		entity.yRotO += f1 - f;
		entity.setYRot(entity.getYRot() + f1 - f);
		entity.setYHeadRot(entity.getYRot());
	}

	@Override
	public void tick() {
		if (this.getHurtTime() > 0) {
			this.setHurtTime(this.getHurtTime() - 1);
		}

		if (this.getDamage() > 0.0F) {
			this.setDamage(this.getDamage() - 1.0F);
		}

		super.tick();
		this.tickLerp();
		if (this.isControlledByLocalInstance()) {
			this.move(MoverType.SELF, this.getDeltaMovement());
		} else {
			this.setDeltaMovement(Vec3.ZERO);
		}

		double d1 = this.isNoGravity() ? 0.0D : (double)-0.04F;
		double d2 = 0.0D;
		Vec3 vec3 = this.getDeltaMovement();
		this.setDeltaMovement(vec3.x * (double)this.invFriction, vec3.y + d1, vec3.z * (double)this.invFriction);
		this.deltaRotation *= this.invFriction;
		if (d2 > 0.0D) {
			Vec3 vec31 = this.getDeltaMovement();
			this.setDeltaMovement(vec31.x, (vec31.y + d2 * 0.06153846016296973D) * 0.75D, vec31.z);
		}

		this.checkInsideBlocks();
		List<Entity> list = this.level().getEntities(this, this.getBoundingBox().inflate((double)0.2F, (double)-0.01F, (double)0.2F), EntitySelector.pushableBy(this));
		if (!list.isEmpty()) {
			//boolean flag = !this.level.isClientSide && !(this.getControllingPassenger() instanceof Player);

			for(int j = 0; j < list.size(); ++j) {
				Entity entity = list.get(j);
				this.push(entity);
			}
		}
	}

	private void tickLerp() {
		if (this.isControlledByLocalInstance()) {
			this.lerpSteps = 0;
			this.move(MoverType.SELF, this.getDeltaMovement());
		} else {
			this.setDeltaMovement(Vec3.ZERO);
		}

		if (this.lerpSteps > 0) {
			double d0 = this.getX() + (this.lerpX - this.getX()) / (double)this.lerpSteps;
			double d1 = this.getY() + (this.lerpY - this.getY()) / (double)this.lerpSteps;
			double d2 = this.getZ() + (this.lerpZ - this.getZ()) / (double)this.lerpSteps;
			double d3 = Mth.wrapDegrees(this.lerpYRot - (double)this.getYRot());
			this.setYRot(this.getYRot() + (float)d3 / (float)this.lerpSteps);
			this.setXRot(this.getXRot() + (float)(this.lerpXRot - (double)this.getXRot()) / (float)this.lerpSteps);
			--this.lerpSteps;
			this.setPos(d0, d1, d2);
			this.setRot(this.getYRot(), this.getXRot());
		}
	}

	public float getGroundFriction() {
		AABB aabb = this.getBoundingBox();
		AABB aabb1 = new AABB(aabb.minX, aabb.minY - 0.001D, aabb.minZ, aabb.maxX, aabb.minY, aabb.maxZ);
		int i = Mth.floor(aabb1.minX) - 1;
		int j = Mth.ceil(aabb1.maxX) + 1;
		int k = Mth.floor(aabb1.minY) - 1;
		int l = Mth.ceil(aabb1.maxY) + 1;
		int i1 = Mth.floor(aabb1.minZ) - 1;
		int j1 = Mth.ceil(aabb1.maxZ) + 1;
		VoxelShape voxelshape = Shapes.create(aabb1);
		float f = 0.0F;
		int k1 = 0;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

		for(int l1 = i; l1 < j; ++l1) {
			for(int i2 = i1; i2 < j1; ++i2) {
				int j2 = (l1 != i && l1 != j - 1 ? 0 : 1) + (i2 != i1 && i2 != j1 - 1 ? 0 : 1);
				if (j2 != 2) {
					for(int k2 = k; k2 < l; ++k2) {
						if (j2 <= 0 || k2 != k && k2 != l - 1) {
							blockpos$mutableblockpos.set(l1, k2, i2);
							BlockState blockstate = this.level().getBlockState(blockpos$mutableblockpos);
							if (!(blockstate.getBlock() instanceof WaterlilyBlock) && Shapes.joinIsNotEmpty(blockstate.getCollisionShape(this.level(), blockpos$mutableblockpos).move((double)l1, (double)k2, (double)i2), voxelshape, BooleanOp.AND)) {
								f += blockstate.getFriction(this.level(), blockpos$mutableblockpos, this);
								++k1;
							}
						}
					}
				}
			}
		}

		return f / (float)k1;
	}

//	@Override
//	protected void addAdditionalSaveData(CompoundTag compound) {
//		compound.putInt("artillerytype", getArtilleryType().ordinal());
//		compound.putInt("firingstate", getFiringState().ordinal());
//		compound.put("ammoProjectiles", getLoadedAmmoProjectiles());
//		compound.put("ammoCharges", getLoadedAmmoCharges());
//		compound.putFloat("barrelpitch", getShotPitch());
//		compound.putFloat("barrelyaw", getShotYaw());
//	}
//
//	@Override
//	protected void readAdditionalSaveData(CompoundTag compound) {
//		setArtilleryType(ArtilleryType.values()[compound.getInt("artillerytype")]);
//		setFiringState(AmmoFiringState.values()[compound.getInt("firingstate")]);
//		setLoadedAmmoProjectiles(compound.getCompound("ammoProjectiles"));
//		setLoadedAmmoCharges(compound.getCompound("ammoCharges"));
//		setBarrelPitch(compound.getFloat("barrelpitch"));
//		setBarrelYaw(compound.getFloat("barrelyaw"));
//	}

	@Override
	protected void checkFallDamage(double par, boolean falling, BlockState state, BlockPos blockpos) {
		this.lastYd = this.getDeltaMovement().y;
		if (!this.isPassenger()) {
			if (falling) {
				if (this.fallDistance > 15.0F) {

					state.getBlock().fallOn(this.level(), state, blockpos, this, this.fallDistance);
					this.level().gameEvent(GameEvent.HIT_GROUND, blockpos, GameEvent.Context.of(this, this.getBlockStateOn()));
					if (!this.level().isClientSide && !this.isRemoved()) {
						this.kill();
						if (this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
							this.spawnDrops();
						}
					}
				}

				this.resetFallDistance();
			} else if (!this.level().getFluidState(this.blockPosition().below()).is(FluidTags.WATER) && par < 0.0D) {
				this.fallDistance = (float)((double)this.fallDistance - par);
			}

		}
	}

	public void setDamage(float damage) {
		this.entityData.set(DAMAGE_TAKEN, damage);
	}

	public float getDamage() {
		return this.entityData.get(DAMAGE_TAKEN);
	}

	public void setHurtTime(int ticks) {
		this.entityData.set(TIME_SINCE_HIT, ticks);
	}

	public int getHurtTime() {
		return this.entityData.get(TIME_SINCE_HIT);
	}

	public void setHurtDir(int direction) {
		this.entityData.set(HURT_DIRECTION, direction);
	}

	public int getHurtDir() {
		return this.entityData.get(HURT_DIRECTION);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return new ClientboundAddEntityPacket(this);
	}

	public static MoveableArtillery create(Level level, double x, double y, double z, ArtilleryType typeIn) {
		switch (typeIn) {
		case BOMBARD:
			return new Bombard(ModEntities.BOMBARD.get(), level, x, y, z);
		default:
			return null;
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
		float facingYaw = 0f; 
		return facingYaw - 20f;
	}

	@Override
	public float getMaxShotYaw() {
		float facingYaw = 0f; 
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
