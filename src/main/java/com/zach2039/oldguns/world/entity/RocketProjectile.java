package com.zach2039.oldguns.world.entity;

import com.zach2039.oldguns.init.ModEntities;
import com.zach2039.oldguns.world.damagesource.OldGunsDamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;

public class RocketProjectile extends BulletProjectile implements IEntityAdditionalSpawnData {

	protected static final EntityDataAccessor<Byte> TRAJECTORY_BIAS_X = SynchedEntityData.defineId(RocketProjectile.class, EntityDataSerializers.BYTE);
	protected static final EntityDataAccessor<Byte> TRAJECTORY_BIAS_Y = SynchedEntityData.defineId(RocketProjectile.class, EntityDataSerializers.BYTE);
	
	private static double[] thrust = new double[] { 0.01, -0.02, -0.03, -0.04, 0.05, -0.06, 0.07, -0.08, 0.09 };
	
	public RocketProjectile(EntityType<? extends RocketProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public RocketProjectile(Level worldIn, LivingEntity shooter, double x, double y, double z) {
		super(worldIn, shooter, x, y, z);
	}

	@Override
	public EntityType<?> getType() {
		return ModEntities.ROCKET_PROJECTILE.get();
	}

	@Override
	protected void defineSynchedData() {	
		super.defineSynchedData();
		
		this.entityData.define(TRAJECTORY_BIAS_X, (byte)-1);
		this.entityData.define(TRAJECTORY_BIAS_Y, (byte)-1);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);

		compound.putByte("trajectoryBiasX", getTrajectoryBiasX());
		compound.putByte("trajectoryBiasY", getTrajectoryBiasY());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		setTrajectoryBiasX(compound.getByte("trajectoryBiasX"));
		setTrajectoryBiasY(compound.getByte("trajectoryBiasY"));
	}

	@Override
	protected boolean canCollideWithBlockState(BlockState blockstate) {
		if (!blockstate.isAir())
			return true;

		return false;
	}
	
	@Override
	public void tick() {
		if (!this.hasBeenShot) {
			this.gameEvent(GameEvent.PROJECTILE_SHOOT, this.getOwner());
			this.hasBeenShot = true;
		}

		if (!this.leftOwner) {
			this.leftOwner = this.checkLeftOwner();
		}

		super.baseTick();

		// Trajectory bias is applied after launch and during flight, and will affect rocket vectoring randomly
		if (this.tickCount > 5 && this.getTrajectoryBiasX() == (byte)-1) {
			this.setTrajectoryBiasX((byte)this.random.nextInt(9));
			this.setTrajectoryBiasY((byte)this.random.nextInt(9));
		} else if (this.tickCount % (this.random.nextInt(40) + 1) == 0) {
			this.setTrajectoryBiasX((byte)this.random.nextInt(9));
			this.setTrajectoryBiasY((byte)this.random.nextInt(9));
		} else if (this.tickCount % (this.random.nextInt(20) + 1) == 0) {
			this.setTrajectoryBiasX((byte)this.random.nextInt(9));
			this.setTrajectoryBiasY((byte)this.random.nextInt(9));
		}
		
		double thrustX;
		double thrustZ;
		
		if (this.horizontalCollision) {
			thrustX = 1.0D;
			thrustZ = 1.0D;
		} else {
			thrustX = (this.getTrajectoryBiasX() == (byte)-1) ? 1.01D : 1D + thrust[this.getTrajectoryBiasX()];
			thrustZ = (this.getTrajectoryBiasY() == (byte)-1) ? 1.01D : 1D + thrust[this.getTrajectoryBiasY()];
		}
		
		this.setDeltaMovement(this.getDeltaMovement().multiply(thrustX, 1.0D, thrustZ));
		Vec3 vecMov = this.getDeltaMovement();
		this.move(MoverType.SELF, vecMov);
		this.setDeltaMovement(vecMov);

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
		if (!this.canCollideWithBlockState(blockstate) && !flag) {
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

		if (this.isInWaterOrRain() || blockstate.is(Blocks.POWDER_SNOW)) {	         
			this.clearFire();
		}
		
		if (this.isInWater()) {
			doEffectOnBlockHit(this.blockPosition());
		}

		if (this.inGround && !flag) {
			if (this.lastState != blockstate && this.shouldFall()) {
				this.startFalling();
			}

			++this.inGroundTime;
			++this.totalInGroundTime;
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
			boolean noFriction = (this.tickCount < 10);
			float f = (noFriction) ? 1.15f : 0.98F;
			if (!this.isSimulated && this.isInWater()) {
				for(int j = 0; j < 4; ++j) {
					this.level.addParticle(ParticleTypes.BUBBLE, d7 - d5 * 0.25D, d2 - d6 * 0.25D, d3 - d1 * 0.25D, d5, d6, d1);
				}

				f = this.getWaterInertia();
			}

			this.setDeltaMovement(vec3.scale((double)f));
			if (!this.isNoGravity() && !flag) {
				Vec3 vec34 = this.getDeltaMovement();
				double gravityForce = (noFriction && this.totalInGroundTime == 0) ? 0.0D : 0.05D;
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

		if (this.life == 0 && !this.isSilent()) {
			this.level.playSound((Player)null, this.getX(), this.getY(), this.getZ(), SoundEvents.FIREWORK_ROCKET_LAUNCH, SoundSource.AMBIENT, 4.0F, 0.2F);
		}

		if (this.level.isClientSide && this.life % 2 < 2) {
			this.level.addParticle(ParticleTypes.CLOUD, this.getX(), this.getY() - 0.3D, this.getZ(), this.random.nextGaussian() * 0.05D, -this.getDeltaMovement().y * 0.5D, this.random.nextGaussian() * 0.05D);
		}
		
		doEffectOnTick();

		tickDespawn();

		if (!this.isSimulated && this.totalInGroundTime == 0) {
			for (int i = 0; i < 3; i++) {
				double pX = this.getX() + ((this.random.nextDouble()/2f) - 0.25f);
				double pY = this.getY() + ((this.random.nextDouble()/2f) - 0.25f);
				double pZ = this.getZ() + ((this.random.nextDouble()/2f) - 0.25f);
				this.level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pX, pY, pZ, 0d, 0d, 0d);
			}
		}
	}

	@Override
	protected ResourceKey<DamageType> getDamageType() {
		return OldGunsDamageTypes.ARTILLERY;
	}
	
	public void setTrajectoryBiasX(byte bias) {
		this.entityData.set(TRAJECTORY_BIAS_X, bias);
	}
	
	public void setTrajectoryBiasY(byte bias) {
		this.entityData.set(TRAJECTORY_BIAS_Y, bias);
	}

	public byte getTrajectoryBiasX() {
		return this.entityData.get(TRAJECTORY_BIAS_X);
	}
	
	public byte getTrajectoryBiasY() {
		return this.entityData.get(TRAJECTORY_BIAS_Y);
	}
}

