package zach2039.oldguns.common.tile;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
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
import zach2039.oldguns.common.tile.util.TileEntityHelpers;

public abstract class TileEntityStationaryArtillery extends TileEntity implements ITickable, IArtillery, IArtilleryPowderable {

	protected EnumFacing facing = EnumFacing.NORTH;
	
	protected ArtilleryType artilleryType = ArtilleryType.STATIONARY_CANNON;
	protected FiringState firingState = FiringState.UNLOADED;
	protected int firingCooldown = 0;
	protected float barrelPitch = 0f;
	protected float barrelYaw = 0f;
	protected int powderCharge = 0;
	protected ItemStack loadedProjectile = ItemStack.EMPTY;
	protected ItemStackHandler inventory = new ItemStackHandler(1);
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		compound.setInteger("artillerytype", getArtilleryType().ordinal());
		compound.setInteger("firingstate", getFiringState().ordinal());
		compound.setInteger("powdercharge", getPowderCharge());
		compound.setTag("loadedprojectile", getLoadedProjectile().serializeNBT());
		compound.setFloat("barrelpitch", getBarrelPitch());
		compound.setFloat("barrelyaw", getBarrelYaw());
		return compound;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		setArtilleryType(ArtilleryType.values()[compound.getInteger("artillerytype")]);
		setFiringState(FiringState.values()[compound.getInteger("firingstate")]);
		setPowderCharge(compound.getInteger("powdercharge"));
		setLoadedProjectile(new ItemStack(compound.getCompoundTag("loadedprojectile")));
		setBarrelPitch(compound.getFloat("barrelpitch"));
		setBarrelYaw(compound.getFloat("barrelyaw"));
	}

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}
	
	@Nullable
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T)inventory : super.getCapability(capability, facing);
	}
	
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

	public abstract float getProjectileBaseSpeed();

	public abstract float getEffectiveRange();

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
		this.barrelYaw = MathHelper.clamp(yaw, getMinBarrelYaw(), getMaxBarrelYaw());
	}

	@Override
	public float getBarrelYaw() {
		return this.barrelPitch;
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
	public void onDataPacket(net.minecraft.network.NetworkManager net, net.minecraft.network.play.server.SPacketUpdateTileEntity pkt) {
		handleUpdateTag(pkt.getNbtCompound());
	}
	
	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		super.handleUpdateTag(tag);
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbtTagCompound = getUpdateTag();
		
		return new SPacketUpdateTileEntity(this.pos, 0, nbtTagCompound);
	}
	
	@Override
	public NBTTagCompound getUpdateTag() { 
		NBTTagCompound nbtTagCompound = super.getUpdateTag();
		
		nbtTagCompound.setInteger("artillerytype", getArtilleryType().ordinal());
		nbtTagCompound.setInteger("firingstate", getFiringState().ordinal());
		nbtTagCompound.setInteger("powdercharge", getPowderCharge());
		nbtTagCompound.setTag("loadedprojectile", getLoadedProjectile().serializeNBT());
		nbtTagCompound.setFloat("barrelpitch", getBarrelPitch());
		nbtTagCompound.setFloat("barrelyaw", getBarrelYaw());
		
		return nbtTagCompound;
	}

	@Override
	public void update() {
		// Allow override of cannon yaw, if entity is near with gunner's quadrant.
        EntityPlayer controllingEntity = this.world.getClosestPlayer(getPos().getX(), getPos().getY(), getPos().getZ(), 2D, false);
        if (controllingEntity != null)
        {
        	if (controllingEntity.getHeldItemMainhand().getItem() instanceof ItemGunnersQuadrant && controllingEntity.isSneaking())
        	{
        		setBarrelYaw(controllingEntity.getRotationYawHead());
        		setBarrelPitch(controllingEntity.rotationPitch);
        		OldGuns.logger.info("rot: " + getBarrelPitch() + ", yaw: " + getBarrelYaw());
        	}
        }
	}
	
	public static boolean handleInteraction(World world, IArtillery artillery, IArtilleryPowderable artilleryPowder, 
			BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		boolean successfulInteract = false;
		boolean isDirty = false;
		
		if (!player.isSneaking())
        {       		
        	/* Change behavior based on current firing state. */
        	FiringState firingState = artillery.getFiringState();
        	int currentPowderCharge = artilleryPowder.getPowderCharge();
        	ItemStack currentProjectile = artillery.getLoadedProjectile();
        	ItemStack currentPlayerItem = player.getHeldItemMainhand();
        	
        	switch(firingState)
        	{
        		case UNLOADED:
        			if (!currentPlayerItem.isEmpty())
        			{
        				/* Player has an item. */        				 
        				if (currentPlayerItem.getItem() instanceof ItemPowderCharge)
        				{
        					if (currentPowderCharge >= artilleryPowder.getMaxPowderCharge())
        					{
        						player.sendMessage(new TextComponentString(I18n.format("text.oldguns.too_many_powder_charges.message")));
        					}
        					else
        					{
        						player.swingArm(hand);
        						artilleryPowder.setPowderCharge(currentPowderCharge + 1);
        						currentPlayerItem.shrink(1);        						
        						artillery.setFiringState(FiringState.POWDERED);
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
        					if (currentPowderCharge >= artilleryPowder.getMaxPowderCharge())
        					{
        						player.sendMessage(new TextComponentString(I18n.format("text.oldguns.too_many_powder_charges.message")));
        					}
        					else
        					{
        						player.swingArm(hand);
        						artilleryPowder.setPowderCharge(currentPowderCharge + 1);
        						currentPlayerItem.shrink(1);        						
        						artillery.setFiringState(FiringState.POWDERED);
        						world.playSound(player, pos, SoundEvents.ENTITY_CHICKEN_EGG, SoundCategory.BLOCKS, 0.5f, 0.5f);
        						successfulInteract = true;
        					}
        				}
        				else if (currentPlayerItem.getItem() instanceof ItemRamRod)
        				{        			
        					player.swingArm(hand);
        					artillery.setFiringState(FiringState.POWDERED_RAMMED);
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
        					player.swingArm(hand);
        					artillery.setLoadedProjectile(currentPlayerItem);
        					currentPlayerItem.shrink(1);        					
        					artillery.setFiringState(FiringState.PROJECTILE);
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
        					player.swingArm(hand);
        					artillery.setFiringState(FiringState.PROJECTILE_RAMMED);
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
        					player.swingArm(hand);
        		        	if (!world.isRemote)
        		        	{
        		        		/* Calculate deviation multiplier for shot, based on charge time. */
        		        		float deviationMulti = 1.0f; 
        		            
        		        		float f = artillery.getProjectileBaseSpeed() * currentPowderCharge;          
        		            
        		            	ItemArtilleryAmmo itemArtilleryAmmo = (ItemArtilleryAmmo)(currentProjectile.getItem());
        		            	List<EntityProjectile> entityProjectiles = itemArtilleryAmmo.createProjectiles(world, pos.getX(), pos.getY(), pos.getZ(), 
        		            			currentProjectile, artillery, player);
        		            
        		            	/* Fire all projectiles from ammo item. */
        		            	entityProjectiles.forEach((t) ->
        		            	{            		
        		            		/* Set location-based data. */
        		            		t.setEffectiveRange(artillery.getEffectiveRange());
        		            		t.setLaunchLocation(t.getPosition());
        		            	
        		            		/* Launch projectile. */
        		            		t.shoot(pos.getX(), pos.getY(), pos.getZ(), artillery.getBarrelPitch(), artillery.getBarrelYaw(), 0.0F, f, deviationMulti);
        		            		
        		                	world.spawnEntity(t);
        		            	});
        		            	
        		            	/* Do firing effects. */
        		            	artillery.doFiringEffect(world, player, pos.getX(), pos.getY(), pos.getZ());        		                
        		        	}	       	
        		        	artilleryPowder.setPowderCharge(0);
        		        	artillery.setLoadedProjectile(ItemStack.EMPTY);
        		        	artillery.setFiringState(FiringState.UNLOADED);   
    						world.setLightFor(EnumSkyBlock.BLOCK, pos, 15);
    						world.markBlockRangeForRenderUpdate(pos, pos.up(12));
    						world.checkLightFor(EnumSkyBlock.BLOCK, pos.up());
    						world.checkLightFor(EnumSkyBlock.BLOCK, pos.down());
    						world.checkLightFor(EnumSkyBlock.BLOCK, pos.north());
    						world.checkLightFor(EnumSkyBlock.BLOCK, pos.south());
    						world.checkLightFor(EnumSkyBlock.BLOCK, pos.east());
    						world.checkLightFor(EnumSkyBlock.BLOCK, pos.west());
    						artillery.setFiringCooldown(5);	
    						successfulInteract = true;
        				}
        			}
        			break;
        		default:
        			break;
        	}
        }
		
		if (successfulInteract)
		{
			/* Debug client/server rotation differences. */
    		OldGuns.logger.info(String.format("SERVER:"));
    		OldGuns.logger.info(String.format("   firingState         : %s", artillery.getFiringState()));
    		OldGuns.logger.info(String.format("   currentPowderCharge : %d", artilleryPowder.getPowderCharge()));
    		OldGuns.logger.info(String.format("   currentProjectile   : %s", artillery.getLoadedProjectile().getUnlocalizedName()));
		}
		
		TileEntityHelpers.setState(world, pos);
			
		return (successfulInteract) ? true : false;
	}
}
