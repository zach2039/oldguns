package zach2039.oldguns.common.entity;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.init.ModDamageSources;
import zach2039.oldguns.common.init.ModSoundEvents;

public class EntityProjectile extends EntityArrow
{	
	@SuppressWarnings("unchecked")
	private static final Predicate<Entity> ARROW_TARGETS = Predicates.and(EntitySelectors.NOT_SPECTATING, EntitySelectors.IS_ALIVE, new Predicate<Entity>()
	    {
	        public boolean apply(@Nullable Entity p_apply_1_)
	        {
	            return p_apply_1_.canBeCollidedWith();
	        }
	    });
	 
	private static final DataParameter<Float> PROJECTILE_SIZE = EntityDataManager.<Float>createKey(EntityProjectile.class, DataSerializers.FLOAT);
	private static final DataParameter<BlockPos> START_POS = EntityDataManager.<BlockPos>createKey(EntityProjectile.class, DataSerializers.BLOCK_POS);
	private static final DataParameter<Float> EFFECTIVE_RANGE = EntityDataManager.<Float>createKey(EntityProjectile.class, DataSerializers.FLOAT);
	private static final DataParameter<String> SHOOTING_ENTITY = EntityDataManager.<String>createKey(EntityProjectile.class, DataSerializers.STRING);
	private static final DataParameter<Float> DAMAGE = EntityDataManager.<Float>createKey(EntityProjectile.class, DataSerializers.FLOAT);
	
	protected int xTile = -1;
	protected int yTile = -1;
	protected int zTile = -1;
	protected Block inTile;
	protected int inData;
	protected boolean inGround;
	protected int ticksInAir;
	protected int ticksInGround;
	protected int timeInGround;
	protected int knockbackStrength;
	protected EntityLivingBase shootingEntity;
	protected double damage;
	
	public EntityProjectile(World worldIn)
	{
		super(worldIn);
		xTile = -1;
		yTile = -1;
		zTile = -1;
		inTile = null;
		inData = 0;
		inGround = false;
		ticksInAir = 0;
		ticksInGround = 0;
		timeInGround = 0;
		knockbackStrength = 0;
		this.setSize(0.5F, 0.5F);
	}
	
	public EntityProjectile(World worldIn, double x, double y, double z)
	{
		this(worldIn);
		this.setLocationAndAngles(x, y, z, this.rotationYaw, this.rotationPitch);
	}
	
	public EntityProjectile(World worldIn, EntityLivingBase shooter)
	{
		this(worldIn, shooter.posX, shooter.posY + (double)shooter.getEyeHeight() - 0.10000000149011612D, shooter.posZ);
		this.setShootingEntity(shooter);
	}

	@Override
	public void entityInit()
	{		
		/* Register spawn coordinates to datamanager for later damage falloff calculation. */
		dataManager.register(START_POS, getPosition());
		
		/* Register effective range storage for damage calculation. */
		dataManager.register(EFFECTIVE_RANGE, 1f);
		
		/* Register storage for shooting entity reference. */
		dataManager.register(SHOOTING_ENTITY, "");
		
		/* Register storage for damage. */
		dataManager.register(DAMAGE, 0.0f);
		
		/* Register projectile size for rendering. */
		dataManager.register(PROJECTILE_SIZE, 1f);
	}
	
	/**
	 * Set the start location for this entity. Will be used to calculate damage falloff.
	 * @param x
	 * @param y
	 * @param z
	 */
	public void setLaunchLocation(double x, double y, double z)
	{
		dataManager.set(START_POS, new BlockPos(x, y, z));
	}
	
	/**
	 * Set the start location for this entity. Will be used to calculate damage falloff.
	 * @param position
	 */
	public void setLaunchLocation(BlockPos position)
	{
		dataManager.set(START_POS, position);
	}
	
	/**
	 * Set the effective range of this projectile. Damage to entities is halved when outside this range.
	 * @param range
	 */
	public void setEffectiveRange(float range)
	{
		dataManager.set(EFFECTIVE_RANGE, range);
	}
	
	/**
	 * Set the shooting entity for this entity.
	 * @param range
	 */
	public void setShootingEntity(EntityLivingBase shootingEntity)
	{
		dataManager.set(SHOOTING_ENTITY, shootingEntity.getName());
	}
	
	
	/**
	 * Set the size of this projectile.
	 * @param range
	 */
	public void setProjectileSize(float size)
	{
		dataManager.set(PROJECTILE_SIZE, size);
	}
	
	/**
	 * Get the shooting entity for this entity.
	 * @param range
	 */
	public float getProjectileSize()
    {
        return dataManager.get(PROJECTILE_SIZE).floatValue();
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
		float effectiveRange = dataManager.get(EFFECTIVE_RANGE);
		
		/* Get launch position. */
		BlockPos launch = dataManager.get(START_POS);
		
		/* Get current position. */
		BlockPos current = getPosition();
		
		/* Get distance between launch and current position. */
		double distance = current.getDistance(launch.getX(), launch.getY(), launch.getZ());
		
		/* Return based on distance value. */
		return (distance < effectiveRange) ? true : false;
	}
	
	/**
	 * Gets the magnitude of the current velocity.
	 * @return
	 */
	public double getVelocityMagnitude()
	{
		return Math.sqrt(Math.pow(this.motionX, 2) + Math.pow(this.motionY, 2) + Math.pow(this.motionZ, 2));
	}
	
	/**
	 * Returns the decay or resistance to movement, based on entity state. Used for motion calculations.
	 * @return
	 */
	protected float getMovementMultiplier()
	{
		if (this.isInWater())
			return 0.8f;
		
		if (this.isInsideEffectiveRange())
			return 1.0f;
		
		return 0.98f;
	}
	
	/**
	 * Gets the motion to impart on the entity if affected by gravity.
	 * @return
	 */
	protected double getGravity()
	{
		/* Set no gravity when bullet is within effective range and hasn't been in the ground yet. */
		if (this.isInsideEffectiveRange() && this.timeInGround == 0 && !this.isInWater())
			return 0.0D;
		
		return 0.05000000074505806D;
	}
	
	public void setDamage(double damageIn)
    {
		dataManager.set(DAMAGE, (float) damageIn);
    }

    public double getDamage()
    {
        return dataManager.get(DAMAGE);
    }
    
    /**
     * Sets the amount of knockback the arrow applies when it hits a mob.
     */
    public void setKnockbackStrength(int knockbackStrengthIn)
    {
        this.knockbackStrength = knockbackStrengthIn;
    }
    
    protected boolean canCollideWithBlockState(IBlockState iblockstate)
    {
    	if (iblockstate.getMaterial() != Material.AIR)
    		return true;
    	
    	return false;
    }
	
    public void shoot(double x, double y, double z, float velocity, float inaccuracy)
    {
        float f = MathHelper.sqrt(x * x + y * y + z * z);
        x = x / (double)f;
        y = y / (double)f;
        z = z / (double)f;
        x = x + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        y = y + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        z = z + this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
        x = x * (double)velocity;
        y = y * (double)velocity;
        z = z * (double)velocity;
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        float f1 = MathHelper.sqrt(x * x + z * z);
        this.rotationYaw = (float)(MathHelper.atan2(x, z) * (180D / Math.PI));
        this.rotationPitch = (float)(MathHelper.atan2(y, (double)f1) * (180D / Math.PI));
        this.prevRotationYaw = this.rotationYaw;
        this.prevRotationPitch = this.rotationPitch;
        this.ticksInGround = 0;
    }
    
    public void shoot(Entity shooter, float pitch, float yaw, float p_184547_4_, float velocity, float inaccuracy)
    {
        float f = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        float f1 = -MathHelper.sin(pitch * 0.017453292F);
        float f2 = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        this.shoot((double)f, (double)f1, (double)f2, velocity, inaccuracy);
        this.motionX += shooter.motionX;
        this.motionZ += shooter.motionZ;

        if (!shooter.onGround)
        {
            this.motionY += shooter.motionY;
        }
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport)
    {
        this.setPosition(x, y, z);
        this.setRotation(yaw, pitch);
    }

    /**
     * Updates the entity motion clientside, called by packets from the server
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void setVelocity(double x, double y, double z)
    {
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;

        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt(x * x + z * z);
            this.rotationPitch = (float)(MathHelper.atan2(y, (double)f) * (180D / Math.PI));
            this.rotationYaw = (float)(MathHelper.atan2(x, z) * (180D / Math.PI));
            this.prevRotationPitch = this.rotationPitch;
            this.prevRotationYaw = this.rotationYaw;
            this.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            this.ticksInGround = 0;
        }
    }
    
	protected boolean allowBlockHitSlowdown() {
		return true;
	}
    
	@Override
	public void onUpdate()
	{
		/* Perform other entity updates. */
        super.onEntityUpdate();
        
        /* Set rotations. */
        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float)(MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));
            this.rotationPitch = (float)(MathHelper.atan2(this.motionY, (double)f) * (180D / Math.PI));
            this.prevRotationYaw = this.rotationYaw;
            this.prevRotationPitch = this.rotationPitch;
        }
        
		/* Fetch entity information from dataManager on both client and server. */
		this.shootingEntity = this.world.getPlayerEntityByName(dataManager.get(SHOOTING_ENTITY));
		this.damage = (double) dataManager.get(DAMAGE);
		
		/* Get block position at current entity position and check for state. */
		BlockPos blockPos = new BlockPos(this.xTile, this.yTile, this.zTile);
		IBlockState iblockstate = this.world.getBlockState(blockPos);
		Block block = iblockstate.getBlock();
		
		/* Check for collision with ground (Not in air or tall grass). */
		if (canCollideWithBlockState(iblockstate))
		{
			/* Get collision bounding box of block at position. */
			AxisAlignedBB axisalignedbb = iblockstate.getCollisionBoundingBox(this.world, blockPos);
			
			/* If collision box contains this entity, set entity as inGround. */			
			if (axisalignedbb != Block.NULL_AABB && axisalignedbb.contains(new Vec3d(this.posX, this.posY, this.posZ)))
            {
                this.inGround = true;
            }
		}
	
		/* Perform actions if entity is inGround. */
		if (this.inGround)
		{
			int j = block.getMetaFromState(iblockstate);

			/* Check for collision with block of matching data. */
            if ((block != this.inTile || j != this.inData) && !this.world.collidesWithAnyBlock(this.getEntityBoundingBox()))
            {
            	/* Not in a block anymore, so fall randomly and reset tick counters. */
                this.inGround = false;
                this.motionX *= (double)(this.rand.nextFloat() * 0.2F);
                this.motionY *= (double)(this.rand.nextFloat() * 0.2F);
                this.motionZ *= (double)(this.rand.nextFloat() * 0.2F);
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            }
            else
            {
            	/* Stuck in a block. */
                ++this.ticksInGround;
                
                /* Keep track of total time stuck in a block as well. */
                ++this.timeInGround;
                
                /* Smoke particles on bullet, just like balkon's weapon mod. */
                if (this.timeInGround < 100 && this.ticksExisted % (20 + (this.rand.nextInt(10) - 5)) == 0)
    			{
    				this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY, this.posZ, 0, 0, 0);
    			}
                
                /* Kill entity if total time stuck in ground is +500 ticks (25 seconds). */
                if (this.timeInGround >= 500)
                {
                    this.setDead();
                }
            }
            
            
		}
		else
		{
			/* Entity is in the air. Look for things to hit. */
			this.ticksInGround = 0;
            ++this.ticksInAir;
            Vec3d vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
            Vec3d vec3d = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec3d1, vec3d, false, true, false);
            vec3d1 = new Vec3d(this.posX, this.posY, this.posZ);
            vec3d = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

            if (raytraceresult != null)
            {
                vec3d = new Vec3d(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);
            }

            /* Find entity hit, if any. */
            Entity entity = this.findEntityOnPath(vec3d1, vec3d);

            if (entity != null)
            {
                raytraceresult = new RayTraceResult(entity);
            }

            /* Check if player can damage other hit player. */
            if (raytraceresult != null && raytraceresult.entityHit instanceof EntityPlayer)
            {
                EntityPlayer entityplayer = (EntityPlayer)raytraceresult.entityHit;

                if (this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(entityplayer))
                {
                    raytraceresult = null;
                }
            }

            /* Call onHit if entity struck something. */
            if (raytraceresult != null && !net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, raytraceresult))
            {
                this.onProjectileHit(raytraceresult);
            }
            
            /* Move entity to next position and rotation. */
            this.posX += this.motionX;
			this.posY += this.motionY;
			this.posZ += this.motionZ;
			float f1 = MathHelper.sqrt(this.motionX*this.motionX+this.motionZ*this.motionZ);
			this.rotationYaw = (float)(Math.atan2(this.motionZ, this.motionX)*180.0D/Math.PI)+90.0F;

			for(this.rotationPitch = (float)(Math.atan2((double)f1, this.motionY)*180.0D/Math.PI)-90.0F; this.rotationPitch-this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
				;

			while(this.rotationPitch-this.prevRotationPitch >= 180.0F)
				this.prevRotationPitch += 360.0F;
			while(this.rotationYaw-this.prevRotationYaw < -180.0F)
				this.prevRotationYaw -= 360.0F;
			while(this.rotationYaw-this.prevRotationYaw >= 180.0F)
				this.prevRotationYaw += 360.0F;

			this.rotationPitch = this.prevRotationPitch+(this.rotationPitch-this.prevRotationPitch)*0.2F;
			this.rotationYaw = this.prevRotationYaw+(this.rotationYaw-this.prevRotationYaw)*0.2F;
			
			/* Change rate of entity slowdown based on location and effective range. */
			float modifier = this.getMovementMultiplier();
			
			/* Apply non-zero resistance. */
			if (modifier != 1.0f)
			{
				this.motionX *= (double)modifier;
				this.motionY *= (double)modifier;
				this.motionZ *= (double)modifier;
			}
			
			/* Apply gravity. */
			if (!this.hasNoGravity())
            {
				this.motionY -= this.getGravity();
            }
			
			/* Spawn particles, based on state of entity. */
			if (this.isInWater())
			{
				if (this.getVelocityMagnitude() > 0.5f)
				{
					for (int i = 0; i < 4; i++)
						this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * 0.25D, this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D, this.motionX, this.motionY, this.motionZ);
				}
				else
				{
					if (this.ticksExisted % 4 == 0)
	                {
						this.world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, this.posX - this.motionX * 0.25D, this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D, this.motionX, this.motionY, this.motionZ);
	                }
				}
			}

			/* Set new position of entity and do collisions. */
			this.setPosition(this.posX, this.posY, this.posZ);
			this.doBlockCollisions();
		}
    }
	
	protected void projectileHit(Entity entityLiving)
	{

	}
	
	protected void projectileBlockHit(BlockPos blockPos)
	{

	}
	
	protected void onProjectileHit(RayTraceResult raytraceResultIn)
    {		
        Entity entity = raytraceResultIn.entityHit;

        if (entity != null)
        {
            int i = MathHelper.ceil(this.damage);
            
            /* Cut damage in half if outside effective range or low speed. */
            if ((this.getVelocityMagnitude() < 0.5f) || (this.timeInGround > 0))
            {
            	i = i / 4;
            }
            else if (!this.isInsideEffectiveRange())
            {
            	i = i / 2;
            }
            
            DamageSource damagesource;

            if (this.shootingEntity == null)
            {
                damagesource = ModDamageSources.causeBulletDamage(this, null);
            }
            else
            {
                damagesource = ModDamageSources.causeBulletDamage(this, this.shootingEntity);
            }

            if (this.isBurning() && !(entity instanceof EntityEnderman))
            {
                entity.setFire(5);
            }

            if (entity.attackEntityFrom(damagesource, (float)i))
            {
                if (entity instanceof EntityLivingBase)
                {
                    EntityLivingBase entitylivingbase = (EntityLivingBase)entity;

                    /* Reset hurt time to allow multiple pellets to do damage. */
                    entitylivingbase.hurtResistantTime = 0;

                    if (this.knockbackStrength > 0)
                    {
                        float f1 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

                        if (f1 > 0.0F)
                        {
                            entitylivingbase.addVelocity(this.motionX * (double)this.knockbackStrength * 0.6000000238418579D / (double)f1, 0.1D, this.motionZ * (double)knockbackStrength * 0.6000000238418579D / (double)f1);
                        }
                    }

                    if (this.shootingEntity instanceof EntityLivingBase)
                    {
                        EnchantmentHelper.applyThornEnchantments(entitylivingbase, this.shootingEntity);
                        EnchantmentHelper.applyArthropodEnchantments((EntityLivingBase)this.shootingEntity, entitylivingbase);
                    }

                    this.projectileHit(entitylivingbase);

                    if (this.shootingEntity != null && entitylivingbase != this.shootingEntity && entitylivingbase instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP)
                    {
                        ((EntityPlayerMP)this.shootingEntity).connection.sendPacket(new SPacketChangeGameState(6, 0.0F));
                    }
                }
                else
                {
                	this.projectileHit(entity);
                }

                /* Play hit sound. */
                this.playSound(ModSoundEvents.BULLET_HIT_MOB, 0.5F, 1.0F / (this.rand.nextFloat() * 0.3F + 0.9F));

                this.setDead();
            }
        }
        else
        {
        	/* Get angle between face normal and projectile angle. */
        	EnumFacing blockFace = raytraceResultIn.sideHit;
        	
        	/* Get hit info. */
        	BlockPos blockpos = raytraceResultIn.getBlockPos();
        	IBlockState iblockstate = this.world.getBlockState(blockpos);
            this.xTile = blockpos.getX();
            this.yTile = blockpos.getY();
            this.zTile = blockpos.getZ();
            this.inTile = iblockstate.getBlock();
            this.inData = this.inTile.getMetaFromState(iblockstate);
        	
            /* Call onBlockHit. */
        	projectileBlockHit(blockpos);
            
        	/* Do ricochet if angle of attack to surface is low. */
        	boolean ricochet = blockFace.getAxis().isVertical() && (Math.abs(this.rotationPitch) < 10f)
        			&& (this.getVelocityMagnitude() > 1.0f);        
        	
        	if (!ricochet)
        	{              			
        		if (allowBlockHitSlowdown())
        		{
        			this.motionX = (double)((float)(raytraceResultIn.hitVec.x - this.posX));
                    this.motionY = (double)((float)(raytraceResultIn.hitVec.y - this.posY));
                    this.motionZ = (double)((float)(raytraceResultIn.hitVec.z - this.posZ));
            		float f2 = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
        			this.posX -= this.motionX / (double)f2 * 0.05000000074505806D;
	            	this.posY -= this.motionY / (double)f2 * 0.05000000074505806D;
	            	this.posZ -= this.motionZ / (double)f2 * 0.05000000074505806D;
        		}
        		else
        		{
        			this.motionX *= 0.5f;
        			this.motionY *= 0.5f;    
        			this.motionZ *= 0.5f;
        		}
	            
	            /* Play hit sound. */
	            if (!this.isInWater() && (this.getVelocityMagnitude() > 1.0f))
	            {
	            	this.playSound(ModSoundEvents.BULLET_HIT_BLOCK, 0.9F, 1.0F / (this.rand.nextFloat() * 0.3F + 0.9F));
	            }
	            else
	            {
	            	this.playSound(SoundEvents.BLOCK_ANVIL_HIT, 0.9F, 1.0F / (this.rand.nextFloat() * 0.3F + 0.9F));
	            }
                
	            
	            this.inGround = (allowBlockHitSlowdown()) ? true : false;
	            
	            if (this.canCollideWithBlockState(iblockstate))
	            {
	                this.inTile.onEntityCollidedWithBlock(this.world, blockpos, iblockstate, this);
	                
	                for (int i = 0; i < 3; i++)
	                {
	                    this.world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.posX - this.motionX * 0.25D, this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D,
	                    		this.motionX * 0.2f, this.motionY * 0.2f, this.motionZ * 0.2f, Block.getStateId(iblockstate));
	                }
	            }
        	}
        	else
        	{
	            this.motionX *= 0.70f + (this.rand.nextFloat() * 0.15f);
                this.motionY *= -0.70f;
                this.motionZ *= 0.70f + (this.rand.nextFloat() * 0.15f);
                
                for (int i = 0; i < 3; i++)
                {
                    this.world.spawnParticle(EnumParticleTypes.BLOCK_CRACK, this.posX - this.motionX * 0.25D, this.posY - this.motionY * 0.25D, this.posZ - this.motionZ * 0.25D,
                    		0, 0, 0, Block.getStateId(iblockstate));
                }
                
                /* Play ricochet sound. */
                this.playSound(ModSoundEvents.BULLET_RICOCHET, 0.9F, 1.0F / (this.rand.nextFloat() * 0.3F + 0.9F));
                
                /* Add a tick to the total time spent in ground. This will make the ricochet do less damage. */
                ++this.timeInGround;
        	}
        }
    }

	@Override
	public void move(MoverType type, double x, double y, double z)
    {
        super.move(type, x, y, z);

        if (this.inGround)
        {
            this.xTile = MathHelper.floor(this.posX);
            this.yTile = MathHelper.floor(this.posY);
            this.zTile = MathHelper.floor(this.posZ);
        }
    }
	
	@Nullable
    protected Entity findEntityOnPath(Vec3d start, Vec3d end)
    {
        Entity entity = null;
		List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1.0D), ARROW_TARGETS);
        double d0 = 0.0D;

        for (int i = 0; i < list.size(); ++i)
        {
            Entity entity1 = list.get(i);

            if (entity1 != this.shootingEntity || this.ticksInAir >= 5)
            {
                AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow(0.30000001192092896D);
                RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(start, end);

                if (raytraceresult != null)
                {
                    double d1 = start.squareDistanceTo(raytraceresult.hitVec);

                    if (d1 < d0 || d0 == 0.0D)
                    {
                        entity = entity1;
                        d0 = d1;
                    }
                }
            }
        }

        return entity;
    }
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) 
	{
		this.xTile = compound.getInteger("xTile");
	    this.yTile = compound.getInteger("yTile");
	    this.zTile = compound.getInteger("zTile");
	    this.ticksInGround = compound.getShort("ticksInGround");
	    this.timeInGround = compound.getShort("timeInGround");
	    
	    if (compound.hasKey("inTile", 8)) {
	        this.inTile = Block.getBlockFromName(compound.getString("inTile"));
	    } else {
	        this.inTile = Block.getBlockById(compound.getByte("inTile") & 255);
	    }
	
	    this.inData = compound.getByte("inData") & 255;
	    this.inGround = compound.getByte("inGround") == 1;
	
	    if (compound.hasKey("damage", 99)) {
	        this.damage = compound.getDouble("damage");
	    }
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound) 
	{
		compound.setInteger("xTile", this.xTile);
        compound.setInteger("yTile", this.yTile);
        compound.setInteger("zTile", this.zTile);
        compound.setShort("ticksInGround", (short)this.ticksInGround);
        compound.setShort("timeInGround", (short)this.timeInGround);
        ResourceLocation resourcelocation = Block.REGISTRY.getNameForObject(this.inTile);
        compound.setString("inTile", resourcelocation == null ? "" : resourcelocation.toString());
        compound.setByte("inData", (byte)this.inData);
        compound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
        compound.setDouble("damage", this.damage);
	}

	@Override
	protected ItemStack getArrowStack()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
