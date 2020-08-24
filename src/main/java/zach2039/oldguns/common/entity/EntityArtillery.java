package zach2039.oldguns.common.entity;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.passive.EntityWaterMob;
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
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zach2039.oldguns.common.entity.util.EntityHelpers;
import zach2039.oldguns.common.init.ModItems;
import zach2039.oldguns.common.item.ammo.ItemArtilleryAmmo;
import zach2039.oldguns.common.item.ammo.ItemFirearmAmmo;
import zach2039.oldguns.common.item.tools.ItemGunnersQuadrant;

public abstract class EntityArtillery extends Entity
{
	private static final DataParameter<Integer> TIME_SINCE_HIT = EntityDataManager.<Integer>createKey(EntityArtillery.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> FORWARD_DIRECTION = EntityDataManager.<Integer>createKey(EntityArtillery.class, DataSerializers.VARINT);
	private static final DataParameter<Float> DAMAGE_TAKEN = EntityDataManager.<Float>createKey(EntityArtillery.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> WHEEL_SPIN = EntityDataManager.<Float>createKey(EntityArtillery.class, DataSerializers.FLOAT);
	private static final DataParameter<Integer> ARTILLERY_TYPE = EntityDataManager.<Integer>createKey(EntityArtillery.class, DataSerializers.VARINT);
	private static final DataParameter<Boolean> UNPACKED = EntityDataManager.<Boolean>createKey(EntityArtillery.class, DataSerializers.BOOLEAN);
	
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
	
	protected EntityArtillery.Type ArtilleryType = Type.CANNON;
	
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

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

	@Override
	protected void entityInit()
	{
        this.dataManager.register(TIME_SINCE_HIT, Integer.valueOf(0));
        this.dataManager.register(FORWARD_DIRECTION, Integer.valueOf(1));
        this.dataManager.register(DAMAGE_TAKEN, Float.valueOf(0.0F));
        this.dataManager.register(WHEEL_SPIN, Float.valueOf(0.0F));
        
        this.dataManager.register(UNPACKED, Boolean.valueOf(true));
        this.dataManager.register(ARTILLERY_TYPE, Integer.valueOf(EntityArtillery.Type.CANNON.ordinal()));
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

    public void setArtilleryType(EntityArtillery.Type artilleryType)
    {
        this.dataManager.set(ARTILLERY_TYPE, Integer.valueOf(artilleryType.ordinal()));
    }
    
    public EntityArtillery.Type getArtilleryType()
    {
        return EntityArtillery.Type.getById(((Integer)this.dataManager.get(ARTILLERY_TYPE)).intValue());
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
                this.setDamageTaken(this.getDamageTaken() + amount * 10.0F);
                this.markVelocityChanged();
                boolean flag = source.getTrueSource() instanceof EntityPlayer && ((EntityPlayer)source.getTrueSource()).capabilities.isCreativeMode;

                if (flag || this.getDamageTaken() > 40.0F)
                {
                    if (!flag && this.world.getGameRules().getBoolean("doEntityDrops"))
                    {
                        this.dropItemWithOffset(this.getItemArtillery(), 1, 0.0F);
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
	protected void readEntityFromNBT(NBTTagCompound compound)
	{
		compound.setString("Type", this.getArtilleryType().getName());
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
		if (compound.hasKey("Type", 8))
        {
            this.setArtilleryType(EntityArtillery.Type.getTypeFromString(compound.getString("Type")));
        }
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

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;
        super.onUpdate();
        this.tickLerp();

        if (!this.world.isRemote)
        {
            this.updateMotion();

            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
        }
        else
        {
            this.motionX = 0.0D;
            this.motionY = 0.0D;
            this.motionZ = 0.0D;
        }

        this.doBlockCollisions();
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
        	if (controllingEntity.getHeldItemMainhand().getItem() instanceof ItemGunnersQuadrant)
        	{
        		this.rotationYaw = controllingEntity.getRotationYawHead();
        		this.rotationPitch = MathHelper.clamp(controllingEntity.getPitchYaw().x, -15, 15);
        	}
        }
        
        this.setRotation(this.rotationYaw, this.rotationPitch);
        
        List<Entity> list = this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().grow(0.20000000298023224D, -0.009999999776482582D, 0.20000000298023224D), EntitySelectors.getTeamCollisionPredicate(this));

        if (!list.isEmpty() && !this.getUnpacked())
        {
            boolean flag = !this.world.isRemote && !(this.getControllingPassenger() instanceof EntityPlayer);

            for (int j = 0; j < list.size(); ++j)
            {
                Entity entity = list.get(j);

                if (!entity.isPassenger(this))
                {
                    if (flag && this.getPassengers().size() < 2 && !entity.isRiding() && entity.width < this.width && entity instanceof EntityLivingBase && !(entity instanceof EntityWaterMob) && !(entity instanceof EntityPlayer))
                    {
                        entity.startRiding(this);
                    }
                    else
                    {
                        this.applyEntityCollision(entity);
                    }
                }
            }
        }
    }
    
    private void tickLerp()
    {
        if (this.lerpSteps > 0 && this.world.isRemote)
        {
            double d0 = this.posX + (this.lerpX - this.posX) / (double)this.lerpSteps;
            double d1 = this.posY + (this.lerpY - this.posY) / (double)this.lerpSteps;
            double d2 = this.posZ + (this.lerpZ - this.posZ) / (double)this.lerpSteps;
            double d3 = MathHelper.wrapDegrees(this.lerpYaw - (double)this.rotationYaw);
            this.rotationYaw = (float)((double)this.rotationYaw + d3 / (double)this.lerpSteps);
            this.rotationPitch = (float)((double)this.rotationPitch + (this.lerpPitch - (double)this.rotationPitch) / (double)this.lerpSteps);
            --this.lerpSteps;
            this.setPosition(d0, d1, d2);
            this.setRotation(this.rotationYaw, this.rotationPitch);
            
            float spin = this.getWheelSpin();
            
            if (EntityHelpers.getHeading(this).lengthSquared() > 0.001D)
            {
            	if (EntityHelpers.getHeading(this).dotProduct(this.getForward()) > 0)
            	{
            		// Entity is facing the same way it is moving.
            		spin += EntityHelpers.getHeading(this).lengthSquared() * 1000f;
            	}
            	else
            	{
            		// Entity is facing opposite the way it is moving.
            		spin -= EntityHelpers.getHeading(this).lengthSquared() * 1000f;
            	}
            	if (spin > 360f)
            		spin = 0;
            	if (spin < 0f)
            		spin = 360f;
            }
            
            this.setWheelSpin(spin);
        }
    }
    
    private void updateMotion()
    {
        double d1 = this.hasNoGravity() ? 0.0D : -0.03999999910593033D;
        this.momentum = 0.75F;
        
        this.motionX *= (double)this.momentum;
        this.motionZ *= (double)this.momentum;
        this.motionY += d1;
    }
    
    public abstract float getBarrelHeight();
    
    protected abstract void doFiringEffect(World worldIn, Entity entity);
    
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand)
    {
        if (player.isSneaking())
        {
        	if (!this.world.isRemote)
        	{
        		/* Calculate deviation multiplier for shot, based on charge time. */
        		float deviationMulti = 1.0f; 
            
        		float f = getProjectileSpeed();          
            
            	//ItemFirearmAmmo itemFirearmAmmo = (ItemFirearmAmmo)(itemstack.getItem() instanceof ItemFirearmAmmo ? itemstack.getItem() : ModItems.SMALL_IRON_MUSKET_BALL);
            	ItemArtilleryAmmo itemArtilleryAmmo = (ItemArtilleryAmmo)(ModItems.MEDIUM_IRON_CANNONBALL);
            	List<EntityProjectile> entityProjectiles = itemArtilleryAmmo.createProjectiles(this.world, ItemStack.EMPTY, this, player);
            
            	/* Fire all projectiles from ammo item. */
            	entityProjectiles.forEach((t) ->
            	{            		
            		/* Set location-based data. */
            		t.setEffectiveRange(getEffectiveRange());
            		t.setLaunchLocation(t.getPosition());
            	
            		/* Launch projectile. */
            		t.shoot(this, this.rotationPitch, this.rotationYaw, 0.0F, f, deviationMulti);
            	
//            	int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
//
//                if (j > 0)
//                {
//                    t.setDamage(t.getDamage() + (double)j * 0.5D + 0.5D);
//                }
//
//                int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
//
//                if (k > 0)
//                {
//                    t.setKnockbackStrength(k);
//                }
//
//                if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0)
//                {
//                    t.setFire(100);
//                }
                
                	this.world.spawnEntity(t);
            	});
            	
            	/* Do firing effects. */
                doFiringEffect(this.world, this);     
        	}
            return true;
        }
        else
        {
        	if (player.getHeldItemMainhand().isEmpty())
        	{
	        	// Allow player to pack and unpack artillery.
	        	if (this.getUnpacked())
	        	{
	        		this.setUnpacked(false);
	        		this.playSound(SoundEvents.BLOCK_PISTON_CONTRACT, 0.5f, 2f);
	        	}
	        	else
	        	{
	        		this.setUnpacked(true);
	        		this.playSound(SoundEvents.BLOCK_PISTON_EXTEND, 0.5f, 2f);
	        		for (int i = 0; i < (5 + this.rand.nextInt(5)); i++)
	        			this.world.spawnParticle(EnumParticleTypes.CLOUD, this.posX + (this.rand.nextFloat() - 0.5f), this.posY + 0.25f, this.posZ + (this.rand.nextFloat() - 0.5f), 0f, this.rand.nextDouble() * 0.1f, 0f, 0);
	        	}
	        	player.swingArm(hand);
        	}
            return true;
        }
    }
    
    protected abstract float getProjectileSpeed();

	protected abstract float getEffectiveRange();

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
    
	public static EntityArtillery create(World worldIn, double x, double y, double z, EntityArtillery.Type typeIn)
	{
		switch (typeIn)
		{
			case BOMBARD:
					//return new EntityArtilleryBombard(worldIn, x, y, z);
			case MORTAR:
					//return new EntityArtilleryMortar(worldIn, x, y, z);
			case HWACHA:
					//return new EntityArtilleryHwacha(worldIn, x, y, z);
			case CANNON:
					return new EntityArtilleryCannon(worldIn, x, y, z);
			case FIELD_GUN:
					//return new EntityFieldGun(worldIn, x, y, z);
			case HOWITZER:
					//return new EntityHowitzer(worldIn, x, y, z);
			default:
				return null;
		}
	}
	
	public static enum Type
    {
        BOMBARD(0, "ArtilleryBombard"),
        MORTAR(1, "ArtilleryMortar"),
        HWACHA(2, "ArtilleryHwacha"),
        CANNON(3, "ArtilleryCannon"),                
        FIELD_GUN(4, "ArtilleryFieldGun"),        
        HOWITZER(5, "ArtilleryHowitzer");
        
        private static final Map<Integer, EntityArtillery.Type> BY_ID = Maps.<Integer, EntityArtillery.Type>newHashMap();
        private final int id;
        private final String name;

        private Type(int idIn, String nameIn)
        {
            this.id = idIn;
            this.name = nameIn;
        }

        public int getId()
        {
            return this.id;
        }

        public String getName()
        {
            return this.name;
        }

        @SideOnly(Side.CLIENT)
        public static EntityArtillery.Type getById(int idIn)
        {
        	EntityArtillery.Type entityartillery$type = BY_ID.get(Integer.valueOf(idIn));
            return entityartillery$type == null ? CANNON : entityartillery$type;
        }

        static
        {
            for (EntityArtillery.Type entityartillery$type : values())
            {
                BY_ID.put(Integer.valueOf(entityartillery$type.getId()), entityartillery$type);
            }
        }
        
        public static EntityArtillery.Type getTypeFromString(String nameIn)
        {
            for (int i = 0; i < values().length; ++i)
            {
                if (values()[i].getName().equals(nameIn))
                {
                    return values()[i];
                }
            }

            return values()[0];
        }
    }
	
	

}
