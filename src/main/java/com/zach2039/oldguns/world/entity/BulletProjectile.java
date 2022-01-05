package com.zach2039.oldguns.world.entity;

import com.zach2039.oldguns.init.ModEntities;
import com.zach2039.oldguns.init.ModItems;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;

public class BulletProjectile extends Arrow implements IEntityAdditionalSpawnData {
	
	private static final EntityDataAccessor<Float> PROJECTILE_SIZE = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<BlockPos> START_POS = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.BLOCK_POS);
	private static final EntityDataAccessor<Float> EFFECTIVE_RANGE = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<CompoundTag> SHOOTING_ENTITY = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.COMPOUND_TAG);
	private static final EntityDataAccessor<Float> DAMAGE = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.FLOAT);
	private static final EntityDataAccessor<Byte> CRITICAL = SynchedEntityData.defineId(BulletProjectile.class, EntityDataSerializers.BYTE);

	public BulletProjectile(final EntityType<? extends BulletProjectile> entityType, final Level world) {
		super(entityType, world);
	}
	
	public BulletProjectile(final Level world, double x, double y, double z) {
		super(world, x, y, z);
		this.setLocationAndAngles(x, y, z, this.xRotO, this.yRotO);
	}

	public BulletProjectile(final Level world, final LivingEntity shooter) {
		this(world, shooter.xo, shooter.yo + (double)shooter.getEyeHeight() - 0.10000000149011612d, shooter.zo);
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
		/* Register spawn coordinates to datamanager for later damage falloff calculation. */
		this.entityData.define(START_POS, this.blockPosition());
		
		/* Register effective range storage for damage calculation. */
		this.entityData.define(EFFECTIVE_RANGE, 1f);
		
		/* Register storage for shooting entity reference. */
		this.entityData.define(SHOOTING_ENTITY, null);
		
		/* Register storage for damage. */
		this.entityData.define(DAMAGE, 0.0f);
		
		/* Register projectile size for rendering. */
		this.entityData.define(PROJECTILE_SIZE, 1f);
		
		/* Register CRITICAL flag for critical damage application. */
		this.entityData.define(CRITICAL, (byte)0);
	}
	
	public void setLocationAndAngles(double x, double y, double z, float xRot, float yRot) {
		this.setPos(x, y, z);
		this.setRot(xRot, yRot);
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
