package zach2039.oldguns.common.entity;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zach2039.oldguns.api.artillery.ArtilleryType;
import zach2039.oldguns.api.artillery.FiringState;
import zach2039.oldguns.api.artillery.impl.IArtillery;
import zach2039.oldguns.api.artillery.impl.IArtilleryPowderable;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.entity.util.EntityHelpers;
import zach2039.oldguns.common.init.ModItems;
import zach2039.oldguns.common.item.ammo.ItemArtilleryAmmo;
import zach2039.oldguns.common.item.tools.ItemGunnersQuadrant;
import zach2039.oldguns.common.item.tools.ItemLongMatch;
import zach2039.oldguns.common.item.tools.ItemPowderCharge;
import zach2039.oldguns.common.item.tools.ItemRamRod;

public abstract class EntityArtillery extends Entity implements IArtillery, IArtilleryPowderable  
{
	private static final DataParameter<Integer> TIME_SINCE_HIT = EntityDataManager.<Integer>createKey(EntityArtillery.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> FORWARD_DIRECTION = EntityDataManager.<Integer>createKey(EntityArtillery.class, DataSerializers.VARINT);
	private static final DataParameter<Float> DAMAGE_TAKEN = EntityDataManager.<Float>createKey(EntityArtillery.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> WHEEL_SPIN = EntityDataManager.<Float>createKey(EntityArtillery.class, DataSerializers.FLOAT);
	
	private static final DataParameter<Boolean> UNPACKED = EntityDataManager.<Boolean>createKey(EntityArtillery.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> ARTILLERY_TYPE = EntityDataManager.<Integer>createKey(EntityArtillery.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> FIRING_STATE = EntityDataManager.<Integer>createKey(EntityArtillery.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> POWDER_CHARGE = EntityDataManager.<Integer>createKey(EntityArtillery.class, DataSerializers.VARINT);
	private static final DataParameter<NBTTagCompound> LOADED_PROJECTILE = EntityDataManager.<NBTTagCompound>createKey(EntityArtillery.class, DataSerializers.COMPOUND_TAG);
	private static final DataParameter<Integer> FIRING_COOLDOWN = EntityDataManager.<Integer>createKey(EntityArtillery.class, DataSerializers.VARINT);
	
	private boolean isInReverse;
	private float momentum;
	private int lerpSteps;
    private double lerpX;
    private double lerpY;
    private double lerpZ;
    private double lerpYaw;
    private double lerpPitch;
	@SideOnly(Side.CLIENT)
	private double velocityX;
	@SideOnly(Side.CLIENT)
	private double velocityY;
	@SideOnly(Side.CLIENT)
	private double velocityZ;
	@SideOnly(Side.CLIENT)
	private double velocityForward;
	
	protected ArtilleryType type = ArtilleryType.CANNON;
	
	/**
	 * Ammo capacity of this artillery instance.
	 */
	protected int ammoCapacity = 1;
	
	/**
	 * Projectile speed of this artillery instance.
	 */
	protected float projectileSpeed = 2.5f;
	
	/**
	 * Effective range of this artillery instance.
	 */
	protected float effectiveRange = 500f;
	
	/**
	 * Deviation modifier of this artillery instance.
	 */
	protected float deviationModifier = 1f;
	
	/**
	 * Damage modifier of this artillery instance.
	 */
	protected float damageModifier = 1f;
	
	public Entity pullingEntity;
	public boolean fellLastTick = false;
	
	/* Stole this from Forge minecart code. */
    public static float defaultMaxSpeedAirLateral = 0.4f;
    public static float defaultMaxSpeedAirVertical = -1f;
    public static double defaultDragAir = 0.94999998807907104D;
	
    protected float maxSpeedAirLateral = defaultMaxSpeedAirLateral;
    protected float maxSpeedAirVertical = defaultMaxSpeedAirVertical;
    protected double dragAir = defaultDragAir;
    
	public EntityArtillery(World worldIn)
	{
		super(worldIn);
		this.preventEntitySpawning = true;
		this.setSize(1.2F, 1.2F);
		this.pullingEntity = null;
		this.stepHeight = 1.2F;
	}

    public EntityArtillery(World worldIn, double x, double y, double z)
    {
        this(worldIn);
        this.setPosition(x, y, z);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
    }

	public abstract void initArtilleryConfiguration();
    
    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

	@Override
	protected void entityInit()
	{
		this.dataManager.register(ARTILLERY_TYPE, Integer.valueOf(ArtilleryType.CANNON.ordinal()));
		
		this.dataManager.register(TIME_SINCE_HIT, Integer.valueOf(0));
        this.dataManager.register(FORWARD_DIRECTION, Integer.valueOf(1));
        this.dataManager.register(DAMAGE_TAKEN, Float.valueOf(0.0F));
                
        this.dataManager.register(WHEEL_SPIN, Float.valueOf(0.0F));
        this.dataManager.register(UNPACKED, Boolean.valueOf(true));
        this.dataManager.register(FIRING_STATE, Integer.valueOf(FiringState.UNLOADED.ordinal()));
        this.dataManager.register(FIRING_COOLDOWN, Integer.valueOf(0));
        
        this.dataManager.register(POWDER_CHARGE, Integer.valueOf(0));
        this.dataManager.register(LOADED_PROJECTILE, ItemStack.EMPTY.serializeNBT());
	}

    public void setWheelSpin(float wheelSpin)
    {
        this.dataManager.set(WHEEL_SPIN, Float.valueOf(wheelSpin));
    }

    public float getWheelSpin()
    {
        return ((Float)this.dataManager.get(WHEEL_SPIN)).floatValue();
    }
	
    public void setUnpacked(boolean unpacked)
    {
        this.dataManager.set(UNPACKED, Boolean.valueOf(unpacked));
    }

    public boolean getUnpacked()
    {
        return ((Boolean)this.dataManager.get(UNPACKED)).booleanValue();
    }
    
    public void setDamageTaken(float damageTaken)
    {
        this.dataManager.set(DAMAGE_TAKEN, Float.valueOf(damageTaken));
    }

    public float getDamageTaken()
    {
        return ((Float)this.dataManager.get(DAMAGE_TAKEN)).floatValue();
    }
	
    public void setTimeSinceHit(int timeSinceHit)
    {
        this.dataManager.set(TIME_SINCE_HIT, Integer.valueOf(timeSinceHit));
    }

    public int getTimeSinceHit()
    {
        return ((Integer)this.dataManager.get(TIME_SINCE_HIT)).intValue();
    }
	
    public void setForwardDirection(int forwardDirection)
    {
        this.dataManager.set(FORWARD_DIRECTION, Integer.valueOf(forwardDirection));
    }

    public int getForwardDirection()
    {
        return ((Integer)this.dataManager.get(FORWARD_DIRECTION)).intValue();
    }

    public void setArtilleryType(ArtilleryType artilleryType)
    {
        this.dataManager.set(ARTILLERY_TYPE, Integer.valueOf(artilleryType.ordinal()));
    }
    
    public ArtilleryType getArtilleryType()
    {
        return ArtilleryType.values()[((Integer)this.dataManager.get(ARTILLERY_TYPE)).intValue()];
    }
    
    public void setPowderCharge(int charge)
    {
    	this.dataManager.set(POWDER_CHARGE, Integer.valueOf(charge));
    }
    
    public int getPowderCharge()
    {
    	return ((Integer)this.dataManager.get(POWDER_CHARGE)).intValue();
    }
    
    public void setLoadedProjectile(ItemStack stackIn)
    {
    	this.dataManager.set(LOADED_PROJECTILE, stackIn.serializeNBT());
    }
    
    public ItemStack getLoadedProjectile()
    {
    	return new ItemStack((NBTTagCompound)this.dataManager.get(LOADED_PROJECTILE));
    }
    
    public void setFiringState(FiringState firingState)
    {
        this.dataManager.set(FIRING_STATE, Integer.valueOf(firingState.ordinal()));
    }
    
    public FiringState getFiringState()
    {
        return FiringState.values()[((Integer)this.dataManager.get(FIRING_STATE)).intValue()];
    }
    
    public void setFiringCooldown(int cooldown)
    {
        this.dataManager.set(FIRING_COOLDOWN, Integer.valueOf(cooldown));
    }

    public int getFiringCooldown()
    {
        return ((Integer)this.dataManager.get(FIRING_COOLDOWN)).intValue();
    }
    
    @Override
	public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }

    @Override
    @Nullable
    public AxisAlignedBB getCollisionBox(Entity entityIn)
    {
        return entityIn.canBePushed() ? entityIn.getEntityBoundingBox() : null;
    }
    
    @Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox()
    {
        return this.getEntityBoundingBox();
    }
 
	@Override
	public boolean canBePushed()
    {
        return !this.getUnpacked();
    }
    
	@Override
    @SideOnly(Side.CLIENT)
    public void performHurtAnimation()
    {
        this.setForwardDirection(-this.getForwardDirection());
        this.setTimeSinceHit(10);
        this.setDamageTaken(this.getDamageTaken() * 11.0F);
    }
	
	@Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else if (!this.world.isRemote && !this.isDead)
        {
            if (source instanceof EntityDamageSourceIndirect && source.getTrueSource() != null && this.isPassenger(source.getTrueSource()))
            {
                return false;
            }
            else
            {
                this.setForwardDirection(-this.getForwardDirection());
                this.setTimeSinceHit(10);
                
                /* Reduce damage taken if source was not a player with a tool. */
                float amountModifier = 1.0f;
                if (!(source.getTrueSource() instanceof EntityPlayer && source.getDamageType() == "player")) {                		
                	amountModifier = 0.1f;
                }
                
                //OldGuns.logger.info("source : " + source.getTrueSource());
                //OldGuns.logger.info("type : " + source.getDamageType());
                //OldGuns.logger.info("amount : " + amount);
                
                this.setDamageTaken(this.getDamageTaken() + ((amount * 10.0F) * amountModifier));
                this.markVelocityChanged();
                boolean flag = source.getTrueSource() instanceof EntityPlayer && ((EntityPlayer)source.getTrueSource()).capabilities.isCreativeMode;

                if (flag || this.getDamageTaken() > 40.0F)
                {
                    if (!flag && this.world.getGameRules().getBoolean("doEntityDrops"))
                    {
                        this.dropItemWithOffset(ModItems.LARGE_IRON_CANNON_BARREL, 1, 0.0F);
                        this.dropItemWithOffset(ModItems.LARGE_WOODEN_CANNON_CARRIAGE, 1, 0.0F);
                        this.dropItemWithOffset(ModItems.LARGE_WOODEN_CANNON_WHEEL, 1, 0.0F);
                        this.dropItemWithOffset(ModItems.LARGE_WOODEN_CANNON_WHEEL, 1, 0.0F);
                    }

                    this.setDead();
                }

                return true;
            }
        }
        else
        {
            return true;
        }
    }
	
	@Override
    public void applyEntityCollision(Entity entityIn)
    {
        if (entityIn instanceof EntityArtillery)
        {
            if (entityIn.getEntityBoundingBox().minY < this.getEntityBoundingBox().maxY)
            {
                super.applyEntityCollision(entityIn);
            }
        }
        else if (entityIn.getEntityBoundingBox().minY <= this.getEntityBoundingBox().minY)
        {
            super.applyEntityCollision(entityIn);
        }
    }
	
    public abstract Item getItemArtillery();
	
    @Override
    public ItemStack getPickedResult(RayTraceResult target)
    {
        return new ItemStack(getItemArtillery());
    }
    
	@Override
	protected void readEntityFromNBT(NBTTagCompound compound)
	{
		setArtilleryType(ArtilleryType.values()[compound.getInteger("artillerytype")]);
		setUnpacked(compound.getBoolean("unpacked"));
		setFiringState(FiringState.values()[compound.getInteger("firingstate")]);
		setPowderCharge(compound.getInteger("powdercharge"));
		setLoadedProjectile(new ItemStack(compound.getCompoundTag("loadedprojectile")));
		setBarrelPitch(compound.getFloat("barrelpitch"));
		setBarrelYaw(compound.getFloat("barrelyaw"));
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
		compound.setInteger("artillerytype", getArtilleryType().ordinal());
		compound.setBoolean("unpacked", getUnpacked());
		compound.setInteger("firingstate", getFiringState().ordinal());
		compound.setInteger("powdercharge", getPowderCharge());
		compound.setTag("loadedprojectile", getLoadedProjectile().serializeNBT());
		compound.setFloat("barrelpitch", getBarrelPitch());
		compound.setFloat("barrelyaw", getBarrelYaw());
	}
    
	protected double getMaximumSpeed()
	{
		return 0.4D;
	}

    @Override
    public void setPosition(double x, double y, double z)
    {
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        float f = this.width / 2.0F;
        float f1 = this.height;
        this.setEntityBoundingBox(new AxisAlignedBB(x - (double)f, y, z - (double)f, x + (double)f, y + (double)f1, z + (double)f));
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void setPositionAndRotationDirect(double x, double y, double z, float yaw, float pitch, int posRotationIncrements, boolean teleport)
    {
        this.lerpX = x;
        this.lerpY = y;
        this.lerpZ = z;
        this.lerpYaw = (double)yaw;
        this.lerpPitch = (double)pitch;
        this.lerpSteps = 10;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void setVelocity(double x, double y, double z)
    {
        this.motionX = x;
        this.motionY = y;
        this.motionZ = z;
        this.velocityX = this.motionX;
        this.velocityY = this.motionY;
        this.velocityZ = this.motionZ;
    }
    
    @Override
    public EnumFacing getAdjustedHorizontalFacing()
    {
        return this.isInReverse ? this.getHorizontalFacing().getOpposite().rotateY() : this.getHorizontalFacing().rotateY();
    }
    
    public abstract float getMinBarrelPitch();
    
    public abstract float getMaxBarrelPitch();
    
    public float getBarrelPitch()
    {
    	return this.rotationPitch;
    }
    
    public float getBarrelYaw()
    {
    	return this.rotationYaw;
    }
    
    public void setBarrelPitch(float pitch)
    {
    	this.rotationPitch = MathHelper.clamp(pitch, getMinBarrelPitch(), getMaxBarrelPitch());
    }
    
    public void setBarrelYaw(float yaw)
    {
    	this.rotationYaw = yaw;
    }
    
    @Override
    public void onUpdate()
    {
        if (this.getTimeSinceHit() > 0)
        {
            this.setTimeSinceHit(this.getTimeSinceHit() - 1);
        }

        if (this.getDamageTaken() > 0.0F)
        {
            this.setDamageTaken(this.getDamageTaken() - 1.0F);
        }
        
        if (this.getFiringCooldown() > 0) {
        	this.setFiringCooldown(this.getFiringCooldown() - 1);
        }

        if (this.pullingEntity != null)
        {
        	this.posY = this.pullingEntity.posY;
        }
        
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        super.onUpdate();
        this.tickLerp();

        if (this.pullingEntity != null) 
        {
        	double dRotation = (double) (this.prevRotationYaw - this.rotationYaw);
            if (dRotation < -180.0D)
            {
                this.prevRotationYaw += 360.0F;
            }
            else if (dRotation >= 180.0D)
            {
                this.prevRotationYaw -= 360.0F;
            }
            
        	double lookX = MathHelper.sin(-this.rotationYaw * 0.017453292F - (float) Math.PI);
            double lookZ = MathHelper.cos(-this.rotationYaw * 0.017453292F - (float) Math.PI);
        	this.motionX = (this.pullingEntity.posX - this.posX + lookX * 2.5D) * 5D;
        	this.motionZ = (this.pullingEntity.posZ - this.posZ + lookZ * 2.5D) * 5D;
        	if (!this.pullingEntity.onGround && this.pullingEntity.fallDistance == 0.0F)
            {
                this.motionY = this.pullingEntity.posY - this.posY;
                this.fallDistance = 0.0F;
                this.fellLastTick = false;
            }
            else if (!fellLastTick)
            {
                this.motionY = 0.0D;
                this.fellLastTick = true;
            }
        }
        else
        {
        	this.motionX = 0.0D;
            this.motionZ = 0.0D;
        }
        
        this.updateMotion();
        this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
        
        if (this.pullingEntity == null) this.doBlockCollisions();
        //this.rotationPitch = 0.0F;
        double d0 = this.prevPosX - this.posX;
        double d2 = this.prevPosZ - this.posZ;

        if (d0 * d0 + d2 * d2 > 0.001D)
        {
            this.rotationYaw = (float)(MathHelper.atan2(d2, d0) * 180.0D / Math.PI) + 90.0F;
            if (this.isInReverse)
            {
                this.rotationYaw += 180.0F;
            }
        }

        double d3 = (double)MathHelper.wrapDegrees(this.rotationYaw - this.prevRotationYaw);

        if (d3 < -170.0D || d3 >= 170.0D)
        {
            this.rotationYaw += 180.0F;
            this.isInReverse = !this.isInReverse;
        }

        // Allow override of cannon yaw, if entity is near with gunner's quadrant.
        EntityPlayer controllingEntity = this.world.getClosestPlayerToEntity(this, 2D);
        if (controllingEntity != null)
        {
        	if (controllingEntity.getHeldItemMainhand().getItem() instanceof ItemGunnersQuadrant && controllingEntity.isSneaking())
        	{
        		// FIXME: This crashes on server-side
        		setBarrelYaw(controllingEntity.getRotationYawHead());
        		setBarrelPitch(controllingEntity.rotationPitch);
        	}
        }
        
        /* Override rotation if carriage is being pulled by an entity. */
        if (this.pullingEntity != null) {
        	this.rotationYaw = (float) Math.toDegrees(-Math.atan2(this.pullingEntity.posX - this.posX, this.pullingEntity.posZ - this.posZ));
        }
        
        this.setRotation(this.rotationYaw, this.rotationPitch);
        
        List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().grow(0.20000000298023224D, -0.009999999776482582D, 0.20000000298023224D), EntitySelectors.getTeamCollisionPredicate(this));

//        if (!list.isEmpty() && !this.getUnpacked())
//        {
//            boolean flag = !this.world.isRemote && !(this.getControllingPassenger() instanceof EntityPlayer);
//
//            for (int j = 0; j < list.size(); ++j)
//            {
//                Entity entity = list.get(j);
//
//                if (!entity.isPassenger(this))
//                {
//                    if (flag && this.getPassengers().size() < 2 && !entity.isRiding() && entity.width < this.width && entity instanceof EntityLivingBase && !(entity instanceof EntityWaterMob) && !(entity instanceof EntityPlayer))
//                    {
//                        entity.startRiding(this);
//                    }
//                    else
//                    {
//                        this.applyEntityCollision(entity);
//                    }
//                }
//            }
//        }
        
        /* Jank for cool firing effect. */
        if (this.getFiringCooldown() == 1) {
        	this.world.checkLightFor(EnumSkyBlock.BLOCK, getPosition());
			this.world.checkLightFor(EnumSkyBlock.BLOCK, getPosition().up());
			this.world.checkLightFor(EnumSkyBlock.BLOCK, getPosition().down());
			this.world.checkLightFor(EnumSkyBlock.BLOCK, getPosition().north());
			this.world.checkLightFor(EnumSkyBlock.BLOCK, getPosition().south());
			this.world.checkLightFor(EnumSkyBlock.BLOCK, getPosition().east());
			this.world.checkLightFor(EnumSkyBlock.BLOCK, getPosition().west());
        }
        
        if (this.pullingEntity != null) {
        	if (this.pullingEntity.isDead)
        	{
        		this.pullingEntity = null;
        		setUnpacked(true);
        	}
        }
    }
    
    private void tickLerp()
    {
        if (this.lerpSteps > 0)
        {
            double d0 = this.posX + (this.lerpX - this.posX) / (double)this.lerpSteps;
            double d1 = this.posY + (this.lerpY - this.posY) / (double)this.lerpSteps;
            double d2 = this.posZ + (this.lerpZ - this.posZ) / (double)this.lerpSteps;
            /* FIXME: Need to find a better way of synchronizing client/server artillery rotation. */
            double d3 = MathHelper.wrapDegrees(this.lerpYaw - (double)this.rotationYaw);
            this.rotationYaw = (float)((double)this.rotationYaw + d3 / (double)this.lerpSteps);
            //this.rotationYaw = (float) this.lerpYaw;
            this.rotationPitch = (float)((double)this.rotationPitch + (this.lerpPitch - (double)this.rotationPitch) / (double)this.lerpSteps);
            //this.rotationPitch = (float) this.lerpPitch;
            --this.lerpSteps;
            this.setPosition(d0, d1, d2);
            this.setRotation(this.rotationYaw, this.rotationPitch);
            
            double spin = this.getWheelSpin();
            
            if (EntityHelpers.getHeading(this).lengthSquared() > 0.001D)
            {
            	if (EntityHelpers.getHeading(this).dotProduct(this.getForward()) > 0)
            	{
            		// Entity is facing the same way it is moving.
            		if (this.pullingEntity != null)
            		{
            			spin -= Math.max(EntityHelpers.getHeading(this).lengthSquared() * 1000f, 0.000000001d);
            		}
            		else
            		{
            			spin += Math.max(EntityHelpers.getHeading(this).lengthSquared() * 1000f, 0.000000001d);            			
            		}           		
            	}
            	else
            	{
            		// Entity is facing opposite the way it is moving.
            		if (this.pullingEntity != null)
            		{       
            			spin += Math.max(EntityHelpers.getHeading(this).lengthSquared() * 1000f, 0.000000001d);            			
            		}
            		else
            		{            		
            			spin -= Math.max(EntityHelpers.getHeading(this).lengthSquared() * 1000f, 0.000000001d);            			
            		}        
            		
            	}
            	if (spin > 360f)
            		spin = 0;
            	if (spin < 0f)
            		spin = 360f;
            }
            
            this.setWheelSpin((float) spin);
        }
    }
    
    private void updateMotion()
    {
        double d1 = (this.hasNoGravity() || (this.pullingEntity == null)) ? 0.0D : -0.03999999910593033D;
        this.momentum = 0.10F;
        
        this.motionX *= (double)this.momentum;
        this.motionZ *= (double)this.momentum;
        this.motionY += d1;
    }
    
    public abstract float getBarrelHeight();
    
    public abstract int getMaxPowderCharge();
    
    public abstract void doFiringEffect(World worldIn, EntityPlayer player, double posX, double posY, double posZ);        
    
    @Override
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand)
    {
        if (!player.isSneaking())
        {       		
        	/* Change behavior based on current firing state. */
        	FiringState firingState = getFiringState();
        	int currentPowderCharge = getPowderCharge();
        	ItemStack currentProjectile = getLoadedProjectile();
        	ItemStack currentPlayerItem = player.inventory.getCurrentItem();
        	
        	/* Debug client/server rotation differences. */
        	if (this.world.isRemote)
        	{
        		OldGuns.LOGGER.debug(String.format("CLIENT:"));
        		OldGuns.LOGGER.debug(String.format("   firingState         : %s", firingState));
        		OldGuns.LOGGER.debug(String.format("   currentPowderCharge : %d", currentPowderCharge));
        		OldGuns.LOGGER.debug(String.format("   currentProjectile   : %s", currentProjectile.getItem().getRegistryName()));
        		
        	}
        	else
        	{
        		OldGuns.LOGGER.debug(String.format("SERVER:"));
        		OldGuns.LOGGER.debug(String.format("   firingState         : %s", firingState));
        		OldGuns.LOGGER.debug(String.format("   currentPowderCharge : %d", currentPowderCharge));
        		OldGuns.LOGGER.debug(String.format("   currentProjectile   : %s", currentProjectile.getItem().getRegistryName()));
        	}
        	
        	switch(firingState)
        	{
        		case UNLOADED:
        			if (!currentPlayerItem.isEmpty())
        			{
        				/* Player has an item. */        				 
        				if (currentPlayerItem.getItem() instanceof ItemPowderCharge)
        				{
        					if (currentPowderCharge >= getMaxPowderCharge())
        					{
        						if (!world.isRemote) player.sendMessage(new TextComponentTranslation("text.oldguns.too_many_powder_charges.message"));
        					}
        					else
        					{
        						player.swingArm(hand);
        						setPowderCharge(currentPowderCharge + 1);
        						currentPlayerItem.shrink(1);        						
        						setFiringState(FiringState.POWDERED);
        						this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 0.5f, 0.5f);
        					}
        				}
        			}
        			break;
        		case POWDERED:
        			if (!currentPlayerItem.isEmpty())
        			{
        				/* Player has an item. */        				 
        				if (currentPlayerItem.getItem() instanceof ItemPowderCharge)
        				{
        					if (currentPowderCharge >= getMaxPowderCharge())
        					{
        						if (!world.isRemote) player.sendMessage(new TextComponentTranslation("text.oldguns.too_many_powder_charges.message"));
        					}
        					else
        					{
        						player.swingArm(hand);
        						setPowderCharge(currentPowderCharge + 1);
        						currentPlayerItem.shrink(1);        						
        						setFiringState(FiringState.POWDERED);
        						this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 0.5f, 0.5f);
        					}
        				}
        				else if (currentPlayerItem.getItem() instanceof ItemRamRod)
        				{        			
        					player.swingArm(hand);
    						setFiringState(FiringState.POWDERED_RAMMED);
    						this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 0.5f, 0.5f);
        				}
        			}
        			break;
        		case POWDERED_RAMMED:
        			if (!currentPlayerItem.isEmpty())
        			{
        				/* Player has an item. */        				 
        				if (currentPlayerItem.getItem() instanceof ItemArtilleryAmmo)
        				{
        					player.swingArm(hand);
        					setLoadedProjectile(currentPlayerItem);
        					currentPlayerItem.shrink(1);        					
        					setFiringState(FiringState.PROJECTILE);
    						this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 0.5f, 0.5f);
        				}
        			}
        			break;
        		case PROJECTILE:
        			if (!currentPlayerItem.isEmpty())
        			{
        				/* Player has an item. */        				 
        				if (currentPlayerItem.getItem() instanceof ItemRamRod)
        				{        					
        					player.swingArm(hand);
    						setFiringState(FiringState.PROJECTILE_RAMMED);
    						this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 0.5f, 0.5f);
        				}
        			}
        			break;
        		case PROJECTILE_RAMMED:
        			if (!currentPlayerItem.isEmpty())
        			{
        				/* Player has an item. */        				 
        				if (currentPlayerItem.getItem() instanceof ItemLongMatch)
        				{        		
        					player.swingArm(hand);
        		        	if (!this.world.isRemote)
        		        	{	            
        		        		float f = getProjectileSpeed() * currentPowderCharge;          
        		            
        		            	ItemArtilleryAmmo itemArtilleryAmmo = (ItemArtilleryAmmo)(currentProjectile.getItem());
        		            	List<EntityProjectile> entityProjectiles = itemArtilleryAmmo.createProjectiles(this.world, this.posX, this.posY, this.posZ, ItemStack.EMPTY, this, player);
        		            
        		            	/* Fire all projectiles from ammo item. */
        		            	entityProjectiles.forEach((t) ->
        		            	{            		
        		            		/* Set location-based data. */
        		            		t.setEffectiveRange(getEffectiveRange());
        		            		t.setLaunchLocation(t.getPosition());
        		            		t.setDamage(t.getDamage() * getDamageModifier());
        		            		t.setShootingEntity(player);
        		            	
        		            		/* Launch projectile. */
        		            		t.shoot(this, getBarrelPitch(), getBarrelYaw(), 0.0F, f, getDeviationModifier());
        		            		
        		                	this.world.spawnEntity(t);
        		            	});
        		            	
        		            	/* Do firing effects. */
        		                doFiringEffect(this.world, player, this.posX, this.posY, this.posZ);        		                
        		        	}	       	
        		        	setPowderCharge(0);
        		        	setLoadedProjectile(ItemStack.EMPTY);
    						setFiringState(FiringState.UNLOADED);   
    						this.world.setLightFor(EnumSkyBlock.BLOCK, getPosition(), 15);
    						this.world.markBlockRangeForRenderUpdate(getPosition(), getPosition().offset(getHorizontalFacing(), 12));
    						this.world.checkLightFor(EnumSkyBlock.BLOCK, getPosition().up());
    						this.world.checkLightFor(EnumSkyBlock.BLOCK, getPosition().down());
    						this.world.checkLightFor(EnumSkyBlock.BLOCK, getPosition().north());
    						this.world.checkLightFor(EnumSkyBlock.BLOCK, getPosition().south());
    						this.world.checkLightFor(EnumSkyBlock.BLOCK, getPosition().east());
    						this.world.checkLightFor(EnumSkyBlock.BLOCK, getPosition().west());
    						setFiringCooldown(5);	
        				}
        			}
        			break;
        		default:
        			break;
        	}
        	
        	if (player.getHeldItemMainhand().isEmpty())
        	{
            	// Allow player to pack and unpack artillery.
            	if (getUnpacked() && player.getRidingEntity() != null)
            	{
            		setUnpacked(false);
            		playSound(SoundEvents.BLOCK_PISTON_CONTRACT, 0.5f, 2f);
            		pullingEntity = player.getRidingEntity();
            	}
            	else if (!getUnpacked())
            	{
            		setUnpacked(true);
            		playSound(SoundEvents.BLOCK_PISTON_EXTEND, 0.5f, 2f);
            		pullingEntity = null;
            		setBarrelYaw(player.rotationYaw);
            		for (int i = 0; i < (5 + this.rand.nextInt(5)); i++)
            			this.world.spawnParticle(EnumParticleTypes.CLOUD, this.posX + (this.rand.nextFloat() - 0.5f), this.posY + 0.25f, this.posZ + (this.rand.nextFloat() - 0.5f), 0f, this.rand.nextDouble() * 0.1f, 0f, 0);
            	}
            	player.swingArm(hand);
        	}
        	
            return true;
        }
        return true;
    }
    
    public void setProjectileSpeed(float projectileSpeed)
	{
		this.projectileSpeed = projectileSpeed;
	}
	
	public float getProjectileSpeed()
	{
		return this.projectileSpeed;
	}

    public void setEffectiveRange(float effectiveRange)
    {
    	this.effectiveRange = effectiveRange;
    }
    
    public float getEffectiveRange()
    {
    	return this.effectiveRange;
    }
    
    public void setDeviationModifier(float deviationModifier)
	{
		this.deviationModifier = deviationModifier;
	}
	
	public float getDeviationModifier()
	{
		return this.deviationModifier;
	}
	
	public void setDamageModifier(float damageModifier)
	{
		this.damageModifier = damageModifier;
	}
	
	public float getDamageModifier()
	{
		return this.damageModifier;
	}

	public float getMaxSpeedAirLateral()
    {
        return maxSpeedAirLateral;
    }

    public void setMaxSpeedAirLateral(float value)
    {
        maxSpeedAirLateral = value;
    }

    public float getMaxSpeedAirVertical()
    {
        return maxSpeedAirVertical;
    }

    public void setMaxSpeedAirVertical(float value)
    {
        maxSpeedAirVertical = value;
    }

    public double getDragAir()
    {
        return dragAir;
    }

    public void setDragAir(double value)
    {
        dragAir = value;
    }
    
	public static EntityArtillery create(World worldIn, double x, double y, double z, ArtilleryType typeIn)
	{
		switch (typeIn)
		{
			case CANNON:
					return new EntityArtilleryCannon(worldIn, x, y, z);
			default:
				return null;
		}
	}
}
