package com.zach2039.oldguns.world.entity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.zach2039.oldguns.init.ModDamageSources;
import com.zach2039.oldguns.init.ModDamageSources.DamageType;
import com.zach2039.oldguns.init.ModEntities;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.init.ModSoundEvents;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;

public class BulletProjectile extends Arrow implements IEntityAdditionalSpawnData {
	
	private static final EntityDataAccessor<Float> PROJECTILE_SIZE = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<BlockPos> START_POS = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.BLOCK_POS);
	private static final EntityDataAccessor<Float> EFFECTIVE_RANGE = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<CompoundTag> SHOOTING_ENTITY = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.COMPOUND_TAG);
	private static final EntityDataAccessor<Float> DAMAGE = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<Byte> CRITICAL = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Integer> ID_EFFECT_COLOR = SynchedEntityData.defineId(Arrow.class, EntityDataSerializers.INT);

	@Nullable
	private UUID ownerUUID;
	@Nullable
	private Entity cachedOwner;
	private boolean leftOwner;
	private boolean hasBeenShot;
	
	@Nullable
	private BlockState lastState;
	public AbstractArrow.Pickup pickup = AbstractArrow.Pickup.DISALLOWED;
	private int life;
	private double baseDamage = 2.0D;
	private int knockback;
	private SoundEvent soundEvent = ModSoundEvents.BULLET_HIT_BLOCK.get();
	@Nullable
	private IntOpenHashSet piercingIgnoreEntityIds;
	@Nullable
	private List<Entity> piercedAndKilledEntities;
	
	//private static final byte EVENT_POTION_PUFF = 0;
	private Potion potion = Potions.EMPTY;
	private final Set<MobEffectInstance> effects = Sets.newHashSet();
	//private boolean fixedColor;
	
	
	public BulletProjectile(EntityType<? extends BulletProjectile> entityType, Level world) {
		super(entityType, world);
		this.pickup = Pickup.DISALLOWED;
	}

	public BulletProjectile(Level world, double x, double y, double z) {
		this(ModEntities.BULLET_PROJECTILE.get(), world);
		this.pickup = Pickup.DISALLOWED;
		this.moveTo(x, y, z, this.xRotO, this.yRotO);
		this.setPos(x, y, z);
	}
	
	public BulletProjectile(Level world, LivingEntity shooter) {
		this(world, shooter.getX(), shooter.getEyeY() - (double)0.1F, shooter.getZ());		
		this.setOwner(shooter);
	}
	
	@Override
	public EntityType<?> getType() {
		return ModEntities.BULLET_PROJECTILE.get();
	}
	
	@Override
	public void setEffectsFromItem(final ItemStack stackIn) {
		super.setEffectsFromItem(stackIn);
	}
	
	@Override
	protected ItemStack getPickupItem() {
		return new ItemStack(ModItems.SMALL_IRON_MUSKET_BALL.get());
	}
	
	@Override
	protected boolean tryPickup(Player playerIn) {
		return false;
	}
	
	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	@Override
	public void writeSpawnData(FriendlyByteBuf buf) {
		final Entity shooter = getOwner();
		buf.writeVarInt(shooter == null ? 0 : shooter.getId());
	}

	@Override
	public void readSpawnData(FriendlyByteBuf buf) {
		final Entity shooter = level.getEntity(buf.readVarInt());
		if (shooter != null)
			setOwner(shooter);
	}
	
	@Override
	public boolean isCritArrow() {
		byte b0 = this.entityData.get(CRITICAL);
		return (b0 & 1) != 0;
	}
	
	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		
		/* Register spawn coordinates to datamanager for later damage falloff calculation. */
		this.entityData.define(START_POS, this.blockPosition());
		
		/* Register effective range storage for damage calculation. */
		this.entityData.define(EFFECTIVE_RANGE, 1f);
		
		/* Register storage for shooting entity reference. */
		this.entityData.define(SHOOTING_ENTITY, new CompoundTag());
		
		/* Register storage for damage. */
		this.entityData.define(DAMAGE, 0.0f);
		
		/* Register projectile size for rendering. */
		this.entityData.define(PROJECTILE_SIZE, 1f);
		
		/* Register CRITICAL flag for critical damage application. */
		this.entityData.define(CRITICAL, (byte)0);
	}
	
	private boolean checkLeftOwner() {
		Entity entity = this.getOwner();
		if (entity != null) {
			for(Entity entity1 : this.level.getEntities(this, this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0D), (p_37272_) -> {
				return !p_37272_.isSpectator() && p_37272_.isPickable();
				})) {
				if (entity1.getRootVehicle() == entity.getRootVehicle()) {
					return false;
				}
			}
		}
		
		return true;
	}

	private boolean shouldFall() {
		return this.inGround && this.level.noCollision((new AABB(this.position(), this.position())).inflate(0.06D));
	}

	private void startFalling() {
		this.inGround = false;
		Vec3 vec3 = this.getDeltaMovement();
		this.setDeltaMovement(vec3.multiply((double)(this.random.nextFloat() * 0.2F), (double)(this.random.nextFloat() * 0.2F), (double)(this.random.nextFloat() * 0.2F)));
		this.life = 0;
	}
	
	/**
	 * Returns true if projectile's current location is within the specified range.
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	protected boolean isInsideEffectiveRange()
	{
		/* Get effective range. */
		float effectiveRange = getEffectiveRange();
		
		/* Get launch position. */
		BlockPos launch = getLaunchLocation();
		
		/* Get current position. */
		BlockPos current = blockPosition();
		
		/* Get distance between launch and current position. */
		double distance = current.distSqr(launch);
		
		/* Return based on distance value. */
		return (distance < effectiveRange) ? true : false;
	}
	
	protected DamageType getDamageType() {
		return DamageType.FIREARM;
	}

	@Override
	protected void onHitEntity(EntityHitResult p_36757_) {
		super.onHitEntity(p_36757_);
		Entity entity = p_36757_.getEntity();
		float f = (float)this.getDeltaMovement().length();
		int i = Mth.ceil(Mth.clamp((double)f * this.baseDamage, 0.0D, 2.147483647E9D));
		if (this.getPierceLevel() > 0) {
			if (this.piercingIgnoreEntityIds == null) {
				this.piercingIgnoreEntityIds = new IntOpenHashSet(5);
			}

			if (this.piercedAndKilledEntities == null) {
				this.piercedAndKilledEntities = Lists.newArrayListWithCapacity(5);
			}
	
			if (this.piercingIgnoreEntityIds.size() >= this.getPierceLevel() + 1) {
				this.discard();
				return;
			}
	
			this.piercingIgnoreEntityIds.add(entity.getId());
		}
		
		if (this.isCritArrow()) {
			long j = (long)this.random.nextInt(i / 2 + 2);
			i = (int)Math.min(j + (long)i, 2147483647L);
		}
		
		Entity entity1 = this.getOwner();
		DamageSource damagesource;
		if (entity1 == null) {
			damagesource = ModDamageSources.causeBulletDamage(getDamageType(), this, this);
		} else {
			damagesource = ModDamageSources.causeBulletDamage(getDamageType(), this, entity1);
			if (entity1 instanceof LivingEntity) {
				((LivingEntity)entity1).setLastHurtMob(entity);
			}
		}
		
		boolean flag = entity.getType() == EntityType.ENDERMAN;
		int k = entity.getRemainingFireTicks();
		if (this.isOnFire() && !flag) {
			entity.setSecondsOnFire(5);
		}


		if (entity.hurt(damagesource, (float)i)) {
			if (flag) {
				return;
			}

			if (entity instanceof LivingEntity) {
				LivingEntity livingentity = (LivingEntity)entity;
				if (!this.level.isClientSide && this.getPierceLevel() <= 0) {
					//livingentity.setArrowCount(livingentity.getArrowCount() + 1);
				}
				
				if (this.knockback > 0) {
					Vec3 vec3 = this.getDeltaMovement().multiply(1.0D, 0.0D, 1.0D).normalize().scale((double)this.knockback * 0.6D);
					if (vec3.lengthSqr() > 0.0D) {
						livingentity.push(vec3.x, 0.1D, vec3.z);
					}
				}
				
				if (!this.level.isClientSide && entity1 instanceof LivingEntity) {
					EnchantmentHelper.doPostHurtEffects(livingentity, entity1);
					EnchantmentHelper.doPostDamageEffects((LivingEntity)entity1, livingentity);
				}
				
				this.doPostHurtEffects(livingentity);
				if (entity1 != null && livingentity != entity1 && livingentity instanceof Player && entity1 instanceof ServerPlayer && !this.isSilent()) {
					((ServerPlayer)entity1).connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.ARROW_HIT_PLAYER, 0.0F));
				}
				
				if (!entity.isAlive() && this.piercedAndKilledEntities != null) {
					this.piercedAndKilledEntities.add(livingentity);
				}
				
				if (!this.level.isClientSide && entity1 instanceof ServerPlayer) {
					//ServerPlayer serverplayer = (ServerPlayer)entity1;
					if (this.piercedAndKilledEntities != null && this.shotFromCrossbow()) {
						//CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverplayer, this.piercedAndKilledEntities);
					} else if (!entity.isAlive() && this.shotFromCrossbow()) {
						//CriteriaTriggers.KILLED_BY_CROSSBOW.trigger(serverplayer, Arrays.asList(entity));
					}
				}
				
				// Reset inv time to allow multiple pellets to damage entities
				entity.invulnerableTime = 0;
			}
			
			this.playSound(ModSoundEvents.BULLET_HIT_MOB.get(), 0.5F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
			if (this.getPierceLevel() <= 0) {
				this.discard();
			}
		} else {
			entity.setRemainingFireTicks(k);
			this.setDeltaMovement(this.getDeltaMovement().scale(-0.1D));
			this.setYRot(this.getYRot() + 180.0F);
			this.yRotO += 180.0F;
			if (!this.level.isClientSide && this.getDeltaMovement().lengthSqr() < 1.0E-7D) {
				if (this.pickup == AbstractArrow.Pickup.ALLOWED) {
					this.spawnAtLocation(this.getPickupItem(), 0.1F);
				}

				this.discard();
			}
		}
	}

	private void resetPiercedEntities() {
		if (this.piercedAndKilledEntities != null) {
			this.piercedAndKilledEntities.clear();
		}

		if (this.piercingIgnoreEntityIds != null) {
			this.piercingIgnoreEntityIds.clear();
		}
	}

	/**
	 * Gets the magnitude of the current velocity.
	 * @return
	 */
	public double getVelocityMagnitude()
	{
		return Math.sqrt(Math.pow(this.getDeltaMovement().x, 2) + Math.pow(this.getDeltaMovement().y, 2) + Math.pow(this.getDeltaMovement().z, 2));
	}

	@Override
	protected void onHitBlock(BlockHitResult result) {
		// Allow ricochet for projectiles
		
		boolean ricochet = result.getDirection().getAxis().isVertical() && (Mth.abs(this.yRotO) < 10f) && (this.getm); 
		
		this.lastState = this.level.getBlockState(result.getBlockPos());
		BlockState blockstate = this.level.getBlockState(result.getBlockPos());
		blockstate.onProjectileHit(this.level, blockstate, result, this);
		Vec3 vec3 = result.getLocation().subtract(this.getX(), this.getY(), this.getZ());
		this.setDeltaMovement(vec3);
		Vec3 vec31 = vec3.normalize().scale((double)0.05F);
		this.setPosRaw(this.getX() - vec31.x, this.getY() - vec31.y, this.getZ() - vec31.z);
		this.playSound(ModSoundEvents.BULLET_HIT_BLOCK.get(), 0.9F, 1.0F / (this.random.nextFloat() * 0.2F + 0.9F));
		this.inGround = true;
		this.shakeTime = 7;
		this.setCritArrow(false);
		this.setPierceLevel((byte)0);
		this.setShotFromCrossbow(false);
		this.resetPiercedEntities();
	}
	
	@Override
	protected void onHit(HitResult result) {
		HitResult.Type hitresult$type = result.getType();
		if (hitresult$type == HitResult.Type.ENTITY) {
			this.onHitEntity((EntityHitResult)result);
		} else if (hitresult$type == HitResult.Type.BLOCK) {
			this.onHitBlock((BlockHitResult)result);
		}

		if (hitresult$type != HitResult.Type.MISS) {
			this.gameEvent(GameEvent.PROJECTILE_LAND, this.getOwner());
		}
	}
	
	
	@Override
	protected void tickDespawn() {
		++this.life;
		if (this.life >= 1200) {
			this.discard();
		}
	}
	
	@Override
	public void tick() {
		if (!this.hasBeenShot) {
			this.gameEvent(GameEvent.PROJECTILE_SHOOT, this.getOwner(), this.blockPosition());
			this.hasBeenShot = true;
		}

		if (!this.leftOwner) {
			this.leftOwner = this.checkLeftOwner();
		}

		super.baseTick();
		
		boolean flag = this.isNoPhysics();
		Vec3 vec3 = this.getDeltaMovement();
		if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
			double d0 = vec3.horizontalDistance();
			this.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * (double)(180F / (float)Math.PI)));
			this.setXRot((float)(Mth.atan2(vec3.y, d0) * (double)(180F / (float)Math.PI)));
			this.yRotO = this.getYRot();
			this.xRotO = this.getXRot();
		}
		
		BlockPos blockpos = this.blockPosition();
		BlockState blockstate = this.level.getBlockState(blockpos);
		if (!blockstate.isAir() && !flag) {
			VoxelShape voxelshape = blockstate.getCollisionShape(this.level, blockpos);
			if (!voxelshape.isEmpty()) {
				Vec3 vec31 = this.position();

				for(AABB aabb : voxelshape.toAabbs()) {
					if (aabb.move(blockpos).contains(vec31)) {
						this.inGround = true;
						break;
					}
				}
			}
		}

		if (this.shakeTime > 0) {
			--this.shakeTime;
		}

		if (this.isInWaterOrRain() || blockstate.is(Blocks.POWDER_SNOW)) {	         
			this.clearFire();
		}

		if (this.inGround && !flag) {
			if (this.lastState != blockstate && this.shouldFall()) {
				this.startFalling();
			} else if (!this.level.isClientSide) {
				this.tickDespawn();
			}

			++this.inGroundTime;
		} else {
			this.inGroundTime = 0;
			Vec3 vec32 = this.position();
			Vec3 vec33 = vec32.add(vec3);
			HitResult hitresult = this.level.clip(new ClipContext(vec32, vec33, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
			if (hitresult.getType() != HitResult.Type.MISS) {
				vec33 = hitresult.getLocation();
			}
			
			while(!this.isRemoved()) {
				EntityHitResult entityhitresult = this.findHitEntity(vec32, vec33);
				if (entityhitresult != null) {
					hitresult = entityhitresult;
				}

				if (hitresult != null && hitresult.getType() == HitResult.Type.ENTITY) {
					Entity entity = ((EntityHitResult)hitresult).getEntity();
					Entity entity1 = this.getOwner();
					if (entity instanceof Player && entity1 instanceof Player && !((Player)entity1).canHarmPlayer((Player)entity)) {
						hitresult = null;
						entityhitresult = null;
					}
				}
				
				if (hitresult != null && hitresult.getType() != HitResult.Type.MISS && !flag && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult)) {
					this.onHit(hitresult);
					this.hasImpulse = true;
				}

				if (entityhitresult == null || this.getPierceLevel() <= 0) {
					break;
				}

				hitresult = null;
			}

			
			vec3 = this.getDeltaMovement();
			double d5 = vec3.x;
			double d6 = vec3.y;
			double d1 = vec3.z;
			if (this.isCritArrow()) {
				for(int i = 0; i < 4; ++i) {
					this.level.addParticle(ParticleTypes.CRIT, this.getX() + d5 * (double)i / 4.0D, this.getY() + d6 * (double)i / 4.0D, this.getZ() + d1 * (double)i / 4.0D, -d5, -d6 + 0.2D, -d1);
				}
			}
			
			if (this.inGroundTime < 100 && (this.life % (20 + this.random.nextInt(10) - 5)) == 0) {
				this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0d, 0d, 0d);
			}
	         
			double d7 = this.getX() + d5;
			double d2 = this.getY() + d6;
			double d3 = this.getZ() + d1;
			double d4 = vec3.horizontalDistance();
			if (flag) {
				this.setYRot((float)(Mth.atan2(-d5, -d1) * (double)(180F / (float)Math.PI)));
			} else {
				this.setYRot((float)(Mth.atan2(d5, d1) * (double)(180F / (float)Math.PI)));
			}

			this.setXRot((float)(Mth.atan2(d6, d4) * (double)(180F / (float)Math.PI)));
			this.setXRot(lerpRotation(this.xRotO, this.getXRot()));
			this.setYRot(lerpRotation(this.yRotO, this.getYRot()));
			float f = (this.isInsideEffectiveRange()) ? 1.0f : 0.98F;
			//float f1 = 0.05F;
			if (this.isInWater()) {
				for(int j = 0; j < 4; ++j) {
					//float f2 = 0.25F;
					this.level.addParticle(ParticleTypes.BUBBLE, d7 - d5 * 0.25D, d2 - d6 * 0.25D, d3 - d1 * 0.25D, d5, d6, d1);
				}
			
				f = this.getWaterInertia();
			}

			this.setDeltaMovement(vec3.scale((double)f));
			if (!this.isNoGravity() && !flag) {
				Vec3 vec34 = this.getDeltaMovement();
				double gravityForce = this.isInsideEffectiveRange() ? 0.0d : 0.05d;
				this.setDeltaMovement(vec34.x, vec34.y - gravityForce, vec34.z);
			}

			this.setPos(d7, d2, d3);
			this.checkInsideBlocks();
		}
		
		if (this.level.isClientSide) {
			if (this.inGround) {
				if (this.inGroundTime % 5 == 0) {
					this.makeParticle(1);
				}
			} else {
				this.makeParticle(2);
			}
		} else if (this.inGround && this.inGroundTime != 0 && !this.effects.isEmpty() && this.inGroundTime >= 600) {
			this.level.broadcastEntityEvent(this, (byte)0);
			this.potion = Potions.EMPTY;
			this.effects.clear();
			this.entityData.set(ID_EFFECT_COLOR, -1);
		}
	}
	
	private void makeParticle(int particleCount) {
		int i = this.getColor();
		if (i != -1 && particleCount > 0) {
			double d0 = (double)(i >> 16 & 255) / 255.0D;
			double d1 = (double)(i >> 8 & 255) / 255.0D;
			double d2 = (double)(i >> 0 & 255) / 255.0D;

			for(int j = 0; j < particleCount; ++j) {
				this.level.addParticle(ParticleTypes.ENTITY_EFFECT, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), d0, d1, d2);
			}	
		}
	}	
	
	public void shoot(double x, double y, double z, float vel, float acc) {
		Vec3 vec3 = (new Vec3(x, y, z)).normalize().add(this.random.nextGaussian() * (double)0.0075F * (double)acc, this.random.nextGaussian() * (double)0.0075F * (double)acc, this.random.nextGaussian() * (double)0.0075F * (double)acc).scale((double)vel);
		this.setDeltaMovement(vec3);
		double d0 = vec3.horizontalDistance();
		this.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * (double)(180F / (float)Math.PI)));
		this.setXRot((float)(Mth.atan2(vec3.y, d0) * (double)(180F / (float)Math.PI)));
		this.yRotO = this.getYRot();
		this.xRotO = this.getXRot();
		this.inGroundTime = 0;
		this.life = 0;
		this.baseDamage = this.entityData.get(DAMAGE);
	}
	
    public void shoot(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy)
    {
        float f = -Mth.sin(yaw * 0.017453292F) * Mth.cos(pitch * 0.017453292F);
        float f1 = -Mth.sin(pitch * 0.017453292F);
        float f2 = Mth.cos(yaw * 0.017453292F) * Mth.cos(pitch * 0.017453292F);
        this.shoot((double)f, (double)f1, (double)f2, velocity, inaccuracy);
        this.setDeltaMovement(this.getDeltaMovement().add(shooter.getDeltaMovement()));
    }
    
    public void shoot(double posX, double posY, double posZ, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy)
    {
        float f = -Mth.sin(yaw * 0.017453292F) * Mth.cos(pitch * 0.017453292F);
        float f1 = -Mth.sin(pitch * 0.017453292F);
        float f2 = Mth.cos(yaw * 0.017453292F) * Mth.cos(pitch * 0.017453292F);
        this.shoot((double)f, (double)f1, (double)f2, velocity, inaccuracy);
    }
	
//	/**
//	 * Set the shooting entity for this entity.
//	 * @param range
//	 */
//	public void setShootingEntity(LivingEntity shootingEntity)
//	{
//		this.entityData.set(SHOOTING_ENTITY, shootingEntity.serializeNBT());
//	}
	
	public void setDamage(double damageIn)
    {
		this.entityData.set(DAMAGE, (float) damageIn);
    }

    public double getDamage()
    {
        return this.entityData.get(DAMAGE);
    }
    
	/**
	 * Set the start location for this entity. Will be used to calculate damage falloff.
	 * @param x
	 * @param y
	 * @param z
	 */
	public void setLaunchLocation(Vec3 pos)
	{
		this.entityData.set(START_POS, new BlockPos(pos.x, pos.y, pos.z));
	} 
   
	/**
	 * Set the start location for this entity. Will be used to calculate damage falloff.
	 * @param x
	 * @param y
	 * @param z
	 */
	public void setLaunchLocation(double x, double y, double z)
	{
		this.entityData.set(START_POS, new BlockPos(x, y, z));
	}
    
	/**
	 * Set the start location for this entity. Will be used to calculate damage falloff.
	 * @param position
	 */
	public void setLaunchLocation(BlockPos position)
    {
        this.entityData.set(START_POS, position);
    }
	
	/**
	 * Get the start location for this entity. Will be used to calculate damage falloff.
	 * @param position
	 */
	public BlockPos getLaunchLocation()
    {
        return ((BlockPos)this.entityData.get(START_POS));
    }

	
	/**
	 * Set the size of this projectile.
	 * @param range
	 */
	public void setProjectileSize(float size)
	{
		this.entityData.set(PROJECTILE_SIZE, size);
	}
	
	/**
	 * Get the shooting entity for this entity.
	 * @param range
	 */
	public float getProjectileSize()
    {
        return this.entityData.get(PROJECTILE_SIZE).floatValue();
    }

	/**
	 * Set the effective range of this projectile. Damage to entities is halved when outside this range.
	 * @param range
	 */
	public void setEffectiveRange(float range)
    {
        this.entityData.set(EFFECTIVE_RANGE, Float.valueOf(range));
    }
	
	/**
	 * Get the effective range of this projectile. Damage to entities is halved when outside this range.
	 */
	public float getEffectiveRange()
    {
        return ((Float)this.entityData.get(EFFECTIVE_RANGE)).floatValue();
    }

	
}
