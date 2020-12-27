package zach2039.oldguns.common.tile;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.oredict.OreDictionary;
import zach2039.oldguns.api.capability.casting.CapabilityCast;
import zach2039.oldguns.api.capability.casting.ICast;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.inventory.ContainerMelter;
import zach2039.oldguns.common.item.crafting.MelterRecipes;
import zach2039.oldguns.common.tile.util.TileEntityHelpers;

public class TileEntityMelter extends TileEntityLockable implements ITickable, ISidedInventory
{
	/** Slots enum for melter. */
	public static enum EnumMelterSlot
	{
		INPUT_SLOT,
		OUTPUT_SLOT,
		CAST_SLOT,
		FUEL_SLOT
	}
	
	/** Slot sides for melter. */
	private static final int[] SLOTS_TOP = new int[] {
			EnumMelterSlot.INPUT_SLOT.ordinal()
	};
	private static final int[] SLOTS_SIDES = new int[] {
			EnumMelterSlot.CAST_SLOT.ordinal(),
			EnumMelterSlot.FUEL_SLOT.ordinal()
	};
	private static final int[] SLOTS_BOTTOM = new int[] {
			EnumMelterSlot.OUTPUT_SLOT.ordinal()
	};
	
	/** ItemStack array for items in melter. */
	private NonNullList<ItemStack> melterItemStacks = NonNullList.<ItemStack>withSize(EnumMelterSlot.values().length, ItemStack.EMPTY);
	
	/** Number of ticks melter will burn for. */
	private int melterBurnTime;
	
	/** Number of ticks next meltable item will contribute to burn time. */
	private int currentMelterBurnTime;
	
	/** Current time spent cooking an item. */
	private int cookTime;
	
	/** Time required to cook item. */
	private int totalCookTime; 
	
	/** Custom name of the melter. */
	private String melterCustomName;
	
	/** Returns number of slots in melter inventory. */
	@Override
	public int getSizeInventory()
	{
		return EnumMelterSlot.values().length;
	}

	/** Returns if the melter is completely empty. */
	@Override
	public boolean isEmpty()
	{
		for (ItemStack itemstack : this.melterItemStacks)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
	}

	/** Returns the stack in the melter itemstack list, by slot. */
	@Override
	public ItemStack getStackInSlot(int index)
	{
		return this.melterItemStacks.get(index);
	}

	/** Removes specified number of items from an inventory slot, and returns them as new itemstack. */
	@Override
	public ItemStack decrStackSize(int index, int count)
	{
		return ItemStackHelper.getAndSplit(this.melterItemStacks, index, count);
	}

	/** Removes a stack from an inventory slot, and returns it. */
	@Override
	public ItemStack removeStackFromSlot(int index)
	{
		return ItemStackHelper.getAndRemove(this.melterItemStacks, index);
	}

	/** Get cook time of input stack */
	private int getCookTime(ItemStack stack)
	{
		
		if (!stack.isEmpty())
		{
			for (int id : OreDictionary.getOreIDs(stack))
			{
				if (OreDictionary.getOreName(id) == "oreIron")
					return 100;
			}
		}
	
		return 100;
	}
	
	/** Sets given item stack to slot in inventory. */
	@Override
	public void setInventorySlotContents(int index, ItemStack stack)
	{
		ItemStack stackInSlot = this.melterItemStacks.get(index);
		
		boolean doesStackMatch = !stack.isEmpty() && stack.isItemEqual(stackInSlot) && ItemStack.areItemStackTagsEqual(stack, stackInSlot);
		
		this.melterItemStacks.set(index, stack);
		
		if (stack.getCount() > this.getInventoryStackLimit())
		{
			stack.setCount(this.getInventoryStackLimit());				
		}
		
		if (index == 0 && !doesStackMatch)
		{
			this.totalCookTime = this.getCookTime(stack);
			this.cookTime = 0;
			this.markDirty();
		}
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player)
	{
		if (this.world.getTileEntity(this.pos) != this)
        {
            return false;
        }
        else
        {
            return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
        }
	}

	@Override
	public void openInventory(EntityPlayer player)
	{
	}

	@Override
	public void closeInventory(EntityPlayer player)
	{
	}

	public static int getItemBurnTime(ItemStack stack)
    {
		// just use the vanilla values for furnace.
		return TileEntityFurnace.getItemBurnTime(stack);
    }
	
	public static boolean isItemFuel(ItemStack stack)
    {
        return getItemBurnTime(stack) > 0;
    }
	
	/**
	 * Returns true if the melter can smelt an item, i.e. has a source item, destination stack isn't full, etc.
	 */
	private boolean canMelt()
	{
		ItemStack inputStack = this.melterItemStacks.get(EnumMelterSlot.INPUT_SLOT.ordinal());
		ItemStack castStack = this.melterItemStacks.get(EnumMelterSlot.CAST_SLOT.ordinal());
		
		if (inputStack.isEmpty())
        {
            return false;
        }
        else
        {
            ItemStack resultFromInputStack = MelterRecipes.instance().getMeltingResult(inputStack, castStack);

            if (resultFromInputStack.isEmpty())
            {
            	// No recipe found for input and cast.
                return false;
            }
            else
            {
                ItemStack currentResultStack = this.melterItemStacks.get(EnumMelterSlot.OUTPUT_SLOT.ordinal());

                if (currentResultStack.isEmpty())
                {
                	// Output is empty, so we can smelt an item.
                    return true;
                }
                else if (!currentResultStack.isItemEqual(resultFromInputStack))
                {
                	// Output item and result of melter recipe aren't equal, so we cannot smelt an item.
                    return false;
                }
                else if (currentResultStack.getCount() + resultFromInputStack.getCount() <= this.getInventoryStackLimit() && currentResultStack.getCount() + resultFromInputStack.getCount() <= currentResultStack.getMaxStackSize())  // Forge fix: make furnace respect stack sizes in furnace recipes
                {
                	// Output items match, and result of recipe can fit into stack size of other without overflowing.
                    return true;
                }
                else
                {
                	// Output items match, and result of recipe can fit into stack size of other without overflowing.
                    return currentResultStack.getCount() + resultFromInputStack.getCount() <= resultFromInputStack.getMaxStackSize(); // Forge fix: make furnace respect stack sizes in furnace recipes
                }
            }
        }
	}
	
	private void damageCast(final ItemStack castStack)
	{
		if (castStack.attemptDamageItem(1, this.world.rand, null))
		{
			OldGuns.logger.debug("Consume cast item.", castStack);
			castStack.shrink(1);
		}
	}
	
	/**
     * Turn one item from the melter source stack into the appropriate melted item in the melter result stack
     */
    public void meltItem()
    {
        if (this.canMelt())
        {
        	ItemStack inputStack = this.melterItemStacks.get(EnumMelterSlot.INPUT_SLOT.ordinal());
    		ItemStack castStack = this.melterItemStacks.get(EnumMelterSlot.CAST_SLOT.ordinal());
            ItemStack currentResultStack = this.melterItemStacks.get(EnumMelterSlot.OUTPUT_SLOT.ordinal());
            ItemStack resultFromInputStack = MelterRecipes.instance().getMeltingResult(inputStack, castStack);
            
            if (currentResultStack.isEmpty())
            {
                this.melterItemStacks.set(EnumMelterSlot.OUTPUT_SLOT.ordinal(), resultFromInputStack.copy());
            }
            else if (currentResultStack.getItem() == resultFromInputStack.getItem())
            {
            	currentResultStack.grow(resultFromInputStack.getCount());
            }

            // Shrink input stack on completion of melt.
            inputStack.shrink(1);
            
            // Damage cast stack by 1, consuming if necessary.
            damageCast(castStack);
        }
    }
	
	@Override
	public void update()
	{
		boolean wasBurning = this.isBurning();
		boolean isDirty = false;
		
		// Decrease burn time counter, if the melter was burning.
		if (wasBurning)
		{
			--this.melterBurnTime;
		}
		
		// On server side, process tick counters.
		if (!this.world.isRemote)
		{
			ItemStack inputStack = this.melterItemStacks.get(EnumMelterSlot.INPUT_SLOT.ordinal());
			ItemStack fuelStack = this.melterItemStacks.get(EnumMelterSlot.FUEL_SLOT.ordinal());
			
			if (this.isBurning() || (!inputStack.isEmpty() && !fuelStack.isEmpty()))
			{
				// Melter is burning, or has items in fuel and input slots.
				if (!this.isBurning() && this.canMelt())
				{
					// Melter is not burning, and can melt an item.
					
					// Restart burn item from fuel item.
					this.melterBurnTime = getItemBurnTime(fuelStack);
					this.currentMelterBurnTime = this.melterBurnTime;
					
					// Check again if burning, since we restarted burn timer using fuel.
					if (this.isBurning())
					{
						// Set as updatable once finished, as things have changed and must be synced between client and server.
						isDirty = true;
						
						if (!fuelStack.isEmpty())
						{
							// Have fuel to consume, so shrink fuel stack.
							Item fuelItem = fuelStack.getItem();
							fuelStack.shrink(1);
							
							if (fuelStack.isEmpty())
							{
								// If fuel is consumed completely, make sure we replace with a container item if applicable (like buckets of lava).
								ItemStack fuelContainerStack = fuelItem.getContainerItem(fuelStack);
								this.melterItemStacks.set(EnumMelterSlot.FUEL_SLOT.ordinal(), fuelContainerStack);
							}
						}
					}
				}
				
				if (this.isBurning() && this.canMelt())
				{
					// Melter is burning, and can melt an item.
					++this.cookTime;
					
					if (this.cookTime == this.totalCookTime)
					{
						// Currently melting item is complete.
						this.cookTime = 0;
                        this.totalCookTime = this.getCookTime(this.melterItemStacks.get(EnumMelterSlot.INPUT_SLOT.ordinal()));
                        this.meltItem();
                        isDirty = true;
					}
				}
				else
				{
					// Stop cooking process, as melter is not burning or cannot melt an item.
					this.cookTime = 0;
				}
			}
			else if (!this.isBurning() && this.cookTime > 0)
			{
				// Melter is not burning, but cooktime is not zero. Reduce cooktime by 2 per tick.
				this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
			}
			
			if (wasBurning != this.isBurning())
			{
				// Burn state changed during process. Mark as needing update/sync.
				isDirty = true;
				TileEntityHelpers.setState(this.world, this.pos);
			}
		}
		
		if (isDirty)
		{
			// Update/sync needed.
			this.markDirty();
		}
	}
	
	

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		if (index == EnumMelterSlot.CAST_SLOT.ordinal())
		{
			// Return true if stack in CAST_SLOT has the ICast capability.
			ICast cap = stack.getCapability(CapabilityCast.CAST_CAPABILITY, null);
			return (cap instanceof ICast) ? true : false;
		}
		else if (index == EnumMelterSlot.OUTPUT_SLOT.ordinal())
		{
			// Don't allow items to be placed in output slot.
			return false;
		}
		else if (index == EnumMelterSlot.INPUT_SLOT.ordinal())
		{
			// Allow items in input slot.
			return true;
		}
		else
		{
			// Allow items in fuel slot, if they are actually fuel.
			ItemStack itemstack = this.melterItemStacks.get(EnumMelterSlot.FUEL_SLOT.ordinal());
			return isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && itemstack.getItem() != Items.BUCKET;
		}
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side)
	{
		if (side == EnumFacing.DOWN)
        {
            return SLOTS_BOTTOM;
        }
        else
        {
            return side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES;
        }
	}
	
	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
	{
		return this.isItemValidForSlot(index, itemStackIn);
	}
	
	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
	{
		if (direction == EnumFacing.DOWN && index == 1)
        {
            Item item = stack.getItem();

            if (item != Items.WATER_BUCKET && item != Items.BUCKET)
            {
                return false;
            }
        }

        return true;
	}
	
	@Override
	public String getGuiID()
	{
		return "oldguns:melter";
	}
	
	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
	{
		return new ContainerMelter(playerInventory, this);
	}
	
	@Override
	public int getField(int id)
	{
		switch (id)
        {
            case 0:
                return this.melterBurnTime;
            case 1:
                return this.currentMelterBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.totalCookTime;
            default:
                return 0;
        }
	}

	@Override
	public void setField(int id, int value)
	{
		switch (id)
        {
            case 0:
                this.melterBurnTime = value;
                break;
            case 1:
                this.currentMelterBurnTime = value;
                break;
            case 2:
                this.cookTime = value;
                break;
            case 3:
                this.totalCookTime = value;
                break;
        }
	}

	@Override
	public int getFieldCount()
	{
		return 4;
	}

	@Override
	public void clear()
	{
		this.melterItemStacks.clear();
	}

	@Override
	public String getName()
	{
		return this.hasCustomName() ? this.melterCustomName : "container.melter";
	}

	@Override
	public boolean hasCustomName()
	{
		return this.melterCustomName != null && !this.melterCustomName.isEmpty();
	}
	
	@Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        if (compound.hasKey("CustomName", 8))
        {
            this.melterCustomName = compound.getString("CustomName");
        }
        this.melterItemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        
        ItemStackHelper.loadAllItems(compound, this.melterItemStacks);
        
        this.melterBurnTime = compound.getInteger("BurnTime");
        this.cookTime = compound.getInteger("CookTime");
        this.totalCookTime = compound.getInteger("CookTimeTotal");
        this.currentMelterBurnTime = getItemBurnTime(this.melterItemStacks.get(EnumMelterSlot.FUEL_SLOT.ordinal()));
    }

	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setInteger("BurnTime", (short)this.melterBurnTime);
        compound.setInteger("CookTime", (short)this.cookTime);
        compound.setInteger("CookTimeTotal", (short)this.totalCookTime);
        
        ItemStackHelper.saveAllItems(compound, this.melterItemStacks);

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.melterCustomName);
        }

        return compound;
    }
	
	public boolean isBurning()
	{
		return this.melterBurnTime > 0;
	}
	
	@Override
	@Nullable
	public SPacketUpdateTileEntity getUpdatePacket()
	{
		NBTTagCompound updateTagDescribingTileEntityState = new NBTTagCompound();
		writeToNBT(updateTagDescribingTileEntityState);
		final int METADATA = this.getBlockMetadata();
		return new SPacketUpdateTileEntity(this.pos, METADATA, updateTagDescribingTileEntityState);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) 
	{
		NBTTagCompound updateTagDescribingTileEntityState = pkt.getNbtCompound();
		handleUpdateTag(updateTagDescribingTileEntityState);
	}
	
	@Override
	public NBTTagCompound getUpdateTag()
	{
		 NBTTagCompound nbtTagCompound = new NBTTagCompound();
		 writeToNBT(nbtTagCompound);
		 return nbtTagCompound;
	}
	
	@Override
	public void handleUpdateTag(NBTTagCompound tag)
	{
		this.readFromNBT(tag);
	}
	
	net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.WEST);

    @SuppressWarnings("unchecked")
    @Override
    @javax.annotation.Nullable
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @javax.annotation.Nullable net.minecraft.util.EnumFacing facing)
    {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }
}
