package zach2039.oldguns.common.entity;

import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityArtillery extends Entity
{

	private static final DataParameter<Float> DAMAGE = EntityDataManager.<Float>createKey(EntityArtillery.class, DataSerializers.FLOAT);
	
	private double artilleryX;
	private double artilleryY;
	private double artilleryZ;
	private double artilleryYaw;
	private double artilleryPitch;
	@SideOnly(Side.CLIENT)
	private double velocityX;
	@SideOnly(Side.CLIENT)
	private double velocityY;
	@SideOnly(Side.CLIENT)
	private double velocityZ;
	
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
	public boolean canBeCollidedWith()
    {
        return !this.isDead;
    }
	
	@Override
	protected void entityInit()
	{
		this.dataManager.register(DAMAGE, Float.valueOf(0.0F));
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound compound)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound compound)
	{
		// TODO Auto-generated method stub
		
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
        return true;
    }
	
	@Override
	public void onUpdate()
    {
        if (this.getDamage() > 0.0F)
        {
            this.setDamage(this.getDamage() - 1.0F);
        }

        if (this.posY < -64.0D)
        {
            this.outOfWorld();
        }

        if (!this.world.isRemote && this.world instanceof WorldServer)
        {
            this.world.profiler.startSection("portal");
            MinecraftServer minecraftserver = this.world.getMinecraftServer();
            int i = this.getMaxInPortalTime();

            if (this.inPortal)
            {
                if (minecraftserver.getAllowNether())
                {
                    if (!this.isRiding() && this.portalCounter++ >= i)
                    {
                        this.portalCounter = i;
                        this.timeUntilPortal = this.getPortalCooldown();
                        int j;

                        if (this.world.provider.getDimensionType().getId() == -1)
                        {
                            j = 0;
                        }
                        else
                        {
                            j = -1;
                        }

                        this.changeDimension(j);
                    }

                    this.inPortal = false;
                }
            }
            else
            {
                if (this.portalCounter > 0)
                {
                    this.portalCounter -= 4;
                }

                if (this.portalCounter < 0)
                {
                    this.portalCounter = 0;
                }
            }

            if (this.timeUntilPortal > 0)
            {
                --this.timeUntilPortal;
            }

            this.world.profiler.endSection();
        }

        if (this.world.isRemote)
        {
        	this.setPosition(this.posX, this.posY, this.posZ);
        	this.setRotation(this.rotationYaw, this.rotationPitch);        	
        }
        else
        {
            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;

            if (!this.hasNoGravity())
            {
                this.motionY -= 0.03999999910593033D;
            }

            int k = MathHelper.floor(this.posX);
            int l = MathHelper.floor(this.posY);
            int i1 = MathHelper.floor(this.posZ);

            BlockPos blockpos = new BlockPos(k, l, i1);
            IBlockState iblockstate = this.world.getBlockState(blockpos);

            this.move();

            this.doBlockCollisions();
            this.rotationPitch = 0.0F;
            double d0 = this.prevPosX - this.posX;
            double d2 = this.prevPosZ - this.posZ;

            if (d0 * d0 + d2 * d2 > 0.001D)
            {
                this.rotationYaw = (float)(MathHelper.atan2(d2, d0) * 180.0D / Math.PI);
            }

            double d3 = (double)MathHelper.wrapDegrees(this.rotationYaw - this.prevRotationYaw);

            if (d3 < -170.0D || d3 >= 170.0D)
            {
                this.rotationYaw += 180.0F;
            }

            this.setRotation(this.rotationYaw, this.rotationPitch);

            AxisAlignedBB box;
            box = this.getEntityBoundingBox().grow(0.20000000298023224D, 0.0D, 0.20000000298023224D);

            for (Entity entity : this.world.getEntitiesWithinAABBExcludingEntity(this, box))
            {
            	entity.applyEntityCollision(this);       
            }

            this.handleWaterMovement();
            //net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.event.entity.minecart.MinecartUpdateEvent(this, this.getCurrentRailPosition()));
        }
    }
	
    @Nullable
    public AxisAlignedBB getCollisionBox(Entity entityIn)
    {
        return entityIn.canBePushed() ? entityIn.getEntityBoundingBox() : null;
    }
    
	protected double getMaximumSpeed()
	{
		return 0.4D;
	}
	
    protected void move()
    {
        double d0 = onGround ? this.getMaximumSpeed() : getMaxSpeedAirLateral();
        this.motionX = MathHelper.clamp(this.motionX, -d0, d0);
        this.motionZ = MathHelper.clamp(this.motionZ, -d0, d0);

        double moveY = motionY;
        if(getMaxSpeedAirVertical() > 0 && motionY > getMaxSpeedAirVertical())
        {
            moveY = getMaxSpeedAirVertical();
            if(Math.abs(motionX) < 0.3f && Math.abs(motionZ) < 0.3f)
            {
                moveY = 0.15f;
                motionY = moveY;
            }
        }

        if (this.onGround)
        {
            this.motionX *= 0.5D;
            this.motionY *= 0.5D;
            this.motionZ *= 0.5D;
        }

        this.move(MoverType.SELF, this.motionX, moveY, this.motionZ);

        if (!this.onGround)
        {
            this.motionX *= getDragAir();
            this.motionY *= getDragAir();
            this.motionZ *= getDragAir();
        }
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
        this.artilleryX = x;
        this.artilleryY = y;
        this.artilleryZ = z;
        this.artilleryYaw = (double)yaw;
        this.artilleryPitch = (double)pitch;
        this.motionX = this.velocityX;
        this.motionY = this.velocityY;
        this.motionZ = this.velocityZ;
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
    
    public void setDamage(float damage)
    {
        this.dataManager.set(DAMAGE, Float.valueOf(damage));
    }
    
    public float getDamage()
    {
        return ((Float)this.dataManager.get(DAMAGE)).floatValue();
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
    }
	
	

}
