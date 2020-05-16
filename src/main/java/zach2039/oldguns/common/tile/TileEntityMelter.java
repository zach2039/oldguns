package zach2039.oldguns.common.tile;

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
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import zach2039.oldguns.api.capability.casting.CapabilityCast;
import zach2039.oldguns.api.capability.casting.ICast;
import zach2039.oldguns.common.inventory.ContainerMelter;

public class TileEntityMelter extends TileEntityLockable implements ISidedInventory
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
	private NonNullList<ItemStack> melterItemStacks = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
	
	/** Number of ticks melter will burn for. */
	private int melterBurnTime;
	
	/** Number of ticks next meltable item will contribute to burn time. */
	private int currentItemBurnTime;
	
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
		return this.melterItemStacks.size();
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

	/** Removes a stac from an inventory slot, and returns it. */
	@Override
	public ItemStack removeStackFromSlot(int index)
	{
		return ItemStackHelper.getAndRemove(this.melterItemStacks, index);
	}

	/** Get cook time of input stack */
	private int getCookTime(ItemStack stack)
	{
		return 0;
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
                return this.currentItemBurnTime;
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
                this.currentItemBurnTime = value;
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
        this.melterItemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.melterItemStacks);
        this.melterBurnTime = compound.getInteger("BurnTime");
        this.cookTime = compound.getInteger("CookTime");
        this.totalCookTime = compound.getInteger("CookTimeTotal");
        this.currentItemBurnTime = getItemBurnTime(this.melterItemStacks.get(1));

        if (compound.hasKey("CustomName", 8))
        {
            this.melterCustomName = compound.getString("CustomName");
        }
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
