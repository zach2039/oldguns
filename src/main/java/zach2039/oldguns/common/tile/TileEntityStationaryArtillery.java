package zach2039.oldguns.common.tile;

import java.util.List;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import zach2039.oldguns.api.artillery.ArtilleryType;
import zach2039.oldguns.api.artillery.FiringState;
import zach2039.oldguns.api.artillery.impl.IArtillery;
import zach2039.oldguns.api.artillery.impl.IArtilleryPowderable;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.entity.EntityProjectile;
import zach2039.oldguns.common.item.ammo.ItemArtilleryAmmo;
import zach2039.oldguns.common.item.tools.ItemGunnersQuadrant;
import zach2039.oldguns.common.item.tools.ItemLongMatch;
import zach2039.oldguns.common.item.tools.ItemPowderCharge;
import zach2039.oldguns.common.item.tools.ItemRamRod;
import zach2039.oldguns.common.network.MessageSyncTileEntityCannonRotation;
import zach2039.oldguns.common.network.MessageSyncTileEntityCannonState;

public abstract class TileEntityStationaryArtillery extends TileEntity implements ITickable, IArtillery, IArtilleryPowderable {

	protected EnumFacing facing = EnumFacing.NORTH;
	
	protected ArtilleryType artilleryType = ArtilleryType.STATIONARY_CANNON;
	protected FiringState firingState = FiringState.UNLOADED;
	protected int firingCooldown = 0;
	protected float barrelPitch = 0f;
	protected float barrelYaw = 0f;
	protected int powderCharge = 0;
	protected ItemStack loadedProjectile = ItemStack.EMPTY;
	
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
	
	public float getDefaultYaw()
	{
		switch (facing) 
		{
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
	
	public float getDefaultPitch()
	{
		return -( getMaxBarrelPitch() * 0.5f);
	}
	
	@Override
	public boolean receiveClientEvent(int id, int type) {
        return false;
    }
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("artillerytype", getArtilleryType().ordinal());
		compound.setInteger("firingstate", getFiringState().ordinal());
		compound.setInteger("powdercharge", getPowderCharge());
		compound.setTag("loadedProjectile", getLoadedProjectile().serializeNBT());
		compound.setFloat("barrelpitch", getBarrelPitch());
		compound.setFloat("barrelyaw", getBarrelYaw());
		compound.setInteger("facing", this.facing.getHorizontalIndex());
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		setArtilleryType(ArtilleryType.values()[compound.getInteger("artillerytype")]);
		setFiringState(FiringState.values()[compound.getInteger("firingstate")]);
		setPowderCharge(compound.getInteger("powdercharge"));
		setLoadedProjectile(new ItemStack(compound.getCompoundTag("loadedProjectile")));
		setBarrelPitch(compound.getFloat("barrelpitch"));
		setBarrelYaw(compound.getFloat("barrelyaw"));
		this.facing = EnumFacing.HORIZONTALS[compound.getInteger("facing")];
	}
	
	@Override
	public abstract void initArtilleryConfiguration();
	
	@Override
	public void setLoadedProjectile(ItemStack stackIn) {
		this.loadedProjectile = stackIn;
	}

	@Override
	public ItemStack getLoadedProjectile() {
		return this.loadedProjectile;
	}

	@Override
	public void setArtilleryType(ArtilleryType artilleryType) {
		this.artilleryType = artilleryType;
	}

	@Override
	public ArtilleryType getArtilleryType() {
		return this.artilleryType;
	}

	@Override
	public void setFiringState(FiringState firingState) {
		this.firingState = firingState;
	}

	@Override
	public FiringState getFiringState() {
		return this.firingState;
	}

	@Override
	public abstract int getMaxPowderCharge();

	@Override
	public int getPowderCharge() {
		return this.powderCharge;
	}

	@Override
	public void setPowderCharge(int charge) {
		this.powderCharge = charge;
	}

	@Override
	public void setProjectileSpeed(float projectileSpeed)
	{
		this.projectileSpeed = projectileSpeed;
	}
	
	@Override
	public float getProjectileSpeed()
	{
		return this.projectileSpeed;
	}

	@Override
    public void setEffectiveRange(float effectiveRange)
    {
    	this.effectiveRange = effectiveRange;
    }
    
	@Override
    public float getEffectiveRange()
    {
    	return this.effectiveRange;
    }

	@Override
    public void setDeviationModifier(float deviationModifier)
    {
    	this.deviationModifier = deviationModifier;
    }
    
	@Override
    public float getDeviationModifier()
    {
    	return this.deviationModifier;
    }
	
	@Override
    public void setDamageModifier(float damageModifier)
    {
    	this.damageModifier = damageModifier;
    }
    
	@Override
    public float getDamageModifier()
    {
    	return this.damageModifier;
    }
	
	@Override
	public abstract float getBarrelHeight();
	
	@Override
	public abstract float getMinBarrelPitch();

	@Override
	public abstract float getMaxBarrelPitch();
	
	@Override
	public abstract float getMinBarrelYaw();

	@Override
	public abstract float getMaxBarrelYaw();

	@Override
	public void setBarrelPitch(float pitch) {
		this.barrelPitch = MathHelper.clamp(pitch, getMinBarrelPitch(), getMaxBarrelPitch());
	}

	@Override
	public float getBarrelPitch() {
		return this.barrelPitch;
	}

	@Override
	public void setBarrelYaw(float yaw) {
		/* Set yaw limits from block facing. */
		float facingYaw = 0f;
		float clampedYaw = yaw % 360f;
		if (clampedYaw > 180f)
			clampedYaw -= 360f;
		if (clampedYaw <= -180f)
			clampedYaw += 360f;
		float trueMinBarrelYaw = getMinBarrelYaw();
		float trueMaxBarrelYaw = getMaxBarrelYaw();
		float mod = 1f;
		switch (this.facing) 
		{
			case SOUTH:
				facingYaw = 0f;
				break;
			case WEST:
				facingYaw = 90f;
				break;
			case NORTH:
				facingYaw = 180f;
				if (clampedYaw < 0f)
					mod *= -1;
				break;
			case EAST:
				facingYaw = -90f;
				break;
			default:
				break;
		}
		
		float trueYawMin = mod * facingYaw + trueMinBarrelYaw;
		float trueYawMax = mod * facingYaw + trueMaxBarrelYaw;

		OldGuns.logger.debug("minBarrelYaw: " + getMinBarrelYaw() + ", maxBarrelYaw: " + getMaxBarrelYaw());
		OldGuns.logger.debug("facing: " + facing + ", " + "yaw: " + clampedYaw + ", " + "yawMin: " + trueYawMin + ", yawMax: " + trueYawMax);
		
		this.barrelYaw = MathHelper.clamp(clampedYaw, trueYawMin, trueYawMax);
	}

	@Override
	public float getBarrelYaw() {
		return this.barrelYaw;
	}
	
	@Override
	public void setFiringCooldown(int cooldown) {
		this.firingCooldown = cooldown;
	}

	@Override
	public int getFiringCooldown() {
		return this.firingCooldown;
	}

	@Override
	public abstract void doFiringEffect(World worldIn, EntityPlayer player, double posX, double posY, double posZ);
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		handleUpdateTag(pkt.getNbtCompound());
		world.markChunkDirty(getPos(), this);
	}
	
	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		readFromNBT(tag);
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		writeToNBT(nbtTagCompound);
		int metadata = getBlockMetadata();
		return new SPacketUpdateTileEntity(this.pos, metadata, nbtTagCompound);
	}
	
	@Override
	public NBTTagCompound getUpdateTag() { 
		NBTTagCompound nbtTagCompound = super.getUpdateTag();
		writeToNBT(nbtTagCompound);
		return nbtTagCompound;
	}
	
	@Override
	public void update() 
	{		
		if (!this.hasWorld()) 
			return;  // prevent crash
		
		if (getFiringCooldown() > 0) {
			setFiringCooldown(getFiringCooldown() - 1);
		}
		
		// Allow override of cannon yaw, if entity is near with gunner's quadrant.
        EntityPlayer controllingEntity = this.world.getClosestPlayer(getPos().getX(), getPos().getY(), getPos().getZ(), 2D, false);
        if (controllingEntity != null)
        {
        	if (controllingEntity.getHeldItemMainhand().getItem() instanceof ItemGunnersQuadrant && controllingEntity.isSneaking())
        	{
        		setBarrelYaw(controllingEntity.rotationYaw);
        		setBarrelPitch(controllingEntity.rotationPitch);
        		if (!this.world.isRemote)
	        		OldGuns.network.sendToAllAround(
	        				new MessageSyncTileEntityCannonRotation(this.pos, getBarrelPitch(), getBarrelYaw()),
	        				new TargetPoint(controllingEntity.dimension, this.pos.getX(), this.pos.getY(), this.pos.getZ(), 1000));
        	}
        }
        
        /* Jank for cool firing effect. */
        if (getFiringCooldown() == 1) {
        	this.world.checkLightFor(EnumSkyBlock.BLOCK, this.pos);
			this.world.checkLightFor(EnumSkyBlock.BLOCK, this.pos.up());
			this.world.checkLightFor(EnumSkyBlock.BLOCK, this.pos.down());
			this.world.checkLightFor(EnumSkyBlock.BLOCK, this.pos.north());
			this.world.checkLightFor(EnumSkyBlock.BLOCK, this.pos.east());
			this.world.checkLightFor(EnumSkyBlock.BLOCK, this.pos.west());
        }
	}
	
	public boolean processPlayerInteraction(World world, BlockPos pos, IBlockState state, EntityPlayer player,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		
		boolean successfulInteract = false;
		boolean isCreativeMode = player.capabilities.isCreativeMode;
		
		if (!player.isSneaking())
        {       		
        	/* Change behavior based on current firing state. */
        	FiringState firingState = getFiringState();
        	int currentPowderCharge = getPowderCharge();
        	ItemStack currentProjectile = getLoadedProjectile();
        	ItemStack currentPlayerItem = player.getHeldItemMainhand();
        	
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
        						if (!world.isRemote) 
        						{
        							//OldGuns.logger.info("A world.isRemote : " + world.isRemote);
        							player.sendMessage(new TextComponentTranslation("text.oldguns.too_many_powder_charges.message"));
        						}
        					}
        					else
        					{
        						if (!world.isRemote) {
        							player.swingArm(player.getActiveHand());
        							setPowderCharge(currentPowderCharge + 1);
        							if (!isCreativeMode) currentPlayerItem.shrink(1);        						
        							setFiringState(FiringState.POWDERED);
        						}
        						world.playSound(player, pos, SoundEvents.ENTITY_CHICKEN_EGG, SoundCategory.BLOCKS, 0.5f, 0.5f);
        						successfulInteract = true;
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
        						if (!world.isRemote) 
        						{
        							//OldGuns.logger.info("B world.isRemote : " + world.isRemote);
        							player.sendMessage(new TextComponentTranslation("text.oldguns.too_many_powder_charges.message"));
        						}
        					}
        					else
        					{
        						if (!world.isRemote) {
        							player.swingArm(player.getActiveHand());
            						setPowderCharge(currentPowderCharge + 1);
            						if (!isCreativeMode) currentPlayerItem.shrink(1);        									
            						setFiringState(FiringState.POWDERED);
        						}
        						world.playSound(player, pos, SoundEvents.ENTITY_CHICKEN_EGG, SoundCategory.BLOCKS, 0.5f, 0.5f);
        						successfulInteract = true;
        					}
        				}
        				else if (currentPlayerItem.getItem() instanceof ItemRamRod)
        				{        			
        					if (!world.isRemote) {
        						player.swingArm(player.getActiveHand());
        						setFiringState(FiringState.POWDERED_RAMMED);
        					}
    						world.playSound(player, pos, SoundEvents.ENTITY_CHICKEN_EGG, SoundCategory.BLOCKS, 0.5f, 0.5f);
    						successfulInteract = true;
        				}
        			}
        			break;
        		case POWDERED_RAMMED:
        			if (!currentPlayerItem.isEmpty())
        			{
        				/* Player has an item. */        				 
        				if (currentPlayerItem.getItem() instanceof ItemArtilleryAmmo)
        				{
        					if (!world.isRemote) {
	        					player.swingArm(player.getActiveHand());
	        					setLoadedProjectile(currentPlayerItem.copy());
	        					if (!isCreativeMode) currentPlayerItem.shrink(1);        							
	        					setFiringState(FiringState.PROJECTILE);
        					}
        					world.playSound(player, pos, SoundEvents.ENTITY_CHICKEN_EGG, SoundCategory.BLOCKS, 0.5f, 0.5f);
        					successfulInteract = true;
        				}
        			}
        			break;
        		case PROJECTILE:
        			if (!currentPlayerItem.isEmpty())
        			{
        				/* Player has an item. */        				 
        				if (currentPlayerItem.getItem() instanceof ItemRamRod)
        				{        	
        					if (!world.isRemote) {
        						player.swingArm(player.getActiveHand());
        						setFiringState(FiringState.PROJECTILE_RAMMED);
        					}
        					world.playSound(player, pos, SoundEvents.ENTITY_CHICKEN_EGG, SoundCategory.BLOCKS, 0.5f, 0.5f);
    						successfulInteract = true;
        				}
        			}
        			break;
        		case PROJECTILE_RAMMED:
        			if (!currentPlayerItem.isEmpty())
        			{
        				/* Player has an item. */        				 
        				if (currentPlayerItem.getItem() instanceof ItemLongMatch)
        				{        		
        		        	if (!world.isRemote)
        		        	{
        		        		player.swingArm(player.getActiveHand());
        		        		
        		        		/* Need to offset spawn location of projectiles, since block pos will end up aligned to block edge. */
        		        		double pX = pos.getX() + 0.5D;
        		        		double pY = pos.getY() - 0.2D;
        		        		double pZ = pos.getZ() + 0.5D;
        		            
        		        		float f = getProjectileSpeed() * currentPowderCharge;          
        		            
        		            	ItemArtilleryAmmo itemArtilleryAmmo = (ItemArtilleryAmmo)(currentProjectile.getItem());
        		            	List<EntityProjectile> entityProjectiles = itemArtilleryAmmo.createProjectiles(world, pX, pY, pZ, 
        		            			currentProjectile, this, player);
        		            
        		            	/* Fire all projectiles from ammo item. */
        		            	entityProjectiles.forEach((t) ->
        		            	{            		
        		            		/* Set location-based data. */
        		            		t.setEffectiveRange(getEffectiveRange());
        		            		t.setLaunchLocation(t.getPosition());
        		            		t.setDamage(t.getDamage() * getDamageModifier());
        		            		
        		            		/* Launch projectile. */
        		            		t.shoot(pX, pY, pZ, getBarrelPitch(), getBarrelYaw(), 0.0F, f, getDeviationModifier());
        		            		
        		                	world.spawnEntity(t);
        		            	});
        		            	
        		            	/* Do firing effects. */
        		            	doFiringEffect(world, player, pX, pY, pZ);        		                  	
	        		        	setPowderCharge(0);
	        		        	setLoadedProjectile(ItemStack.EMPTY);
	        		        	setFiringState(FiringState.UNLOADED);   
        		        	}
        		        	world.setLightFor(EnumSkyBlock.BLOCK, pos, 15);
    						world.markBlockRangeForRenderUpdate(pos, pos.offset(facing, 12));
    						world.checkLightFor(EnumSkyBlock.BLOCK, pos.up());
    						world.checkLightFor(EnumSkyBlock.BLOCK, pos.down());
    						world.checkLightFor(EnumSkyBlock.BLOCK, pos.north());
    						world.checkLightFor(EnumSkyBlock.BLOCK, pos.south());
    						world.checkLightFor(EnumSkyBlock.BLOCK, pos.east());
    						world.checkLightFor(EnumSkyBlock.BLOCK, pos.west());
    						setFiringCooldown(5);	     
    						successfulInteract = true;
        				}
        			}
        			break;
        		default:
        			break;
        	}
        }
		
		if (!world.isRemote)
			OldGuns.network.sendToAllAround(
					new MessageSyncTileEntityCannonState(this.pos, getArtilleryType(), getFiringState(), getPowderCharge(), getLoadedProjectile(), getFiringCooldown()),
					new TargetPoint(player.dimension, this.pos.getX(), this.pos.getY(), this.pos.getZ(), 1000));
		
		return successfulInteract;
	}
	
	public void setFacing(EnumFacing facing) {
		this.facing = facing;		
	}
}
