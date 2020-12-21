package zach2039.oldguns.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zach2039.oldguns.api.capability.casting.CapabilityCast;
import zach2039.oldguns.api.capability.casting.ICast;
import zach2039.oldguns.common.inventory.util.InventoryContainerHelper;
import zach2039.oldguns.common.tile.TileEntityMelter;
import zach2039.oldguns.common.tile.TileEntityMelter.EnumMelterSlot;

public class ContainerMelter extends Container
{
	/** Tile entity storage. */
	private TileEntityMelter tileEntityMelter;
	
	/** Crafting result inventory. */
	public InventoryCraftResult craftResult = new InventoryCraftResult();
	
	/** Number of ticks melter will burn for. */
	private int melterBurnTime;
	
	/** Number of ticks next meltable item will contribute to burn time. */
	private int currentItemBurnTime;
	
	/** Current time spent cooking an item. */
	private int cookTime;
	
	/** Time required to cook item. */
	private int totalCookTime; 
	
	public ContainerMelter(InventoryPlayer playerInventory, TileEntityMelter tileEntityMelter)
	{
		this.tileEntityMelter = tileEntityMelter;
		
		// Add output slot to this container.
		this.addSlotToContainer(new SlotMelterOutput(this.tileEntityMelter, TileEntityMelter.EnumMelterSlot.OUTPUT_SLOT.ordinal(), 116, 35));
		
		// Add cast slot to this container.
		this.addSlotToContainer(new SlotMelterCast(this.tileEntityMelter, TileEntityMelter.EnumMelterSlot.CAST_SLOT.ordinal(), 35, 35));
		
		// Add input slot to this container.
		this.addSlotToContainer(new SlotMelterInput(this.tileEntityMelter, TileEntityMelter.EnumMelterSlot.INPUT_SLOT.ordinal(), 56, 17));

		// Add fuel slot to this container.
		this.addSlotToContainer(new SlotMelterFuel(this.tileEntityMelter, TileEntityMelter.EnumMelterSlot.FUEL_SLOT.ordinal(), 56, 53));
		
		// Add player inventory to this container.
		InventoryContainerHelper.addPlayerInventoryToContainer(this, playerInventory);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return this.tileEntityMelter.isUsableByPlayer(player);
	}
	
	@Override
	public void detectAndSendChanges() 
	{
		super.detectAndSendChanges();
		
		for(int i = 0; i < this.listeners.size(); ++i) 
		{
			IContainerListener listener = (IContainerListener)this.listeners.get(i);
			
			if(this.cookTime != this.tileEntityMelter.getField(2)) listener.sendWindowProperty(this, 2, this.tileEntityMelter.getField(2));
			if(this.melterBurnTime != this.tileEntityMelter.getField(0)) listener.sendWindowProperty(this, 0, this.tileEntityMelter.getField(0));
			if(this.currentItemBurnTime != this.tileEntityMelter.getField(1)) listener.sendWindowProperty(this, 1, this.tileEntityMelter.getField(1));
			if(this.totalCookTime != this.tileEntityMelter.getField(3)) listener.sendWindowProperty(this, 3, this.tileEntityMelter.getField(3));
		}
		
		this.cookTime = this.tileEntityMelter.getField(2);
		this.melterBurnTime = this.tileEntityMelter.getField(0);
		this.currentItemBurnTime = this.tileEntityMelter.getField(1);
		this.totalCookTime = this.tileEntityMelter.getField(3);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int data) 
	{
		this.tileEntityMelter.setField(id, data);
	}

	@Override
	public void addListener(IContainerListener listener)
	{
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.tileEntityMelter);
	}
		
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack stackToMove = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack stackToMoveFromSlot = slot.getStack();
            stackToMove = stackToMoveFromSlot.copy();

            if (index < EnumMelterSlot.values().length)
            {
                if (!this.mergeItemStack(stackToMoveFromSlot, EnumMelterSlot.values().length, 39, true))
                	return ItemStack.EMPTY;
            }
            else
            {
            	if (!this.mergeItemStack(stackToMoveFromSlot, 0, EnumMelterSlot.values().length, false))
                	return ItemStack.EMPTY;	
            }
            
            if (stackToMoveFromSlot.getCount() == 0)
            {
            	slot.putStack(ItemStack.EMPTY);
            }
            else
            {
            	slot.onSlotChanged();
            }
            
            if (stackToMoveFromSlot.getCount() == stackToMove.getCount())
            {
            	return ItemStack.EMPTY;
            }
            
            slot.onTake(playerIn, stackToMoveFromSlot);
        }
            
        return stackToMove;
            
//            if (index == EnumMelterSlot.OUTPUT_SLOT.ordinal())
//            {
//                if (!this.mergeItemStack(stackToMove, EnumMelterSlot.OUTPUT_SLOT.ordinal(), 39, true))
//                {
//                    return ItemStack.EMPTY;
//                }
//
//                slot.onSlotChange(stackToMoveFromSlot, stackToMove);
//            }
//            else if (index != EnumMelterSlot.FUEL_SLOT.ordinal() && index != EnumMelterSlot.INPUT_SLOT.ordinal())
//            {
//                if (!FurnaceRecipes.instance().getSmeltingResult(stackToMoveFromSlot).isEmpty())
//                {
//                    if (!this.mergeItemStack(stackToMoveFromSlot, 0, 1, false))
//                    {
//                        return ItemStack.EMPTY;
//                    }
//                }
//                else if (TileEntityFurnace.isItemFuel(stackToMoveFromSlot))
//                {
//                    if (!this.mergeItemStack(stackToMoveFromSlot, 1, 2, false))
//                    {
//                        return ItemStack.EMPTY;
//                    }
//                }
//                else if (index >= 3 && index < 30)
//                {
//                    if (!this.mergeItemStack(stackToMoveFromSlot, 30, 39, false))
//                    {
//                        return ItemStack.EMPTY;
//                    }
//                }
//                else if (index >= 30 && index < 39 && !this.mergeItemStack(stackToMoveFromSlot, 3, 30, false))
//                {
//                    return ItemStack.EMPTY;
//                }
//            }
//            else if (!this.mergeItemStack(stackToMoveFromSlot, 3, 39, false))
//            {
//                return ItemStack.EMPTY;
//            }
//
//            if (stackToMoveFromSlot.isEmpty())
//            {
//                slot.putStack(ItemStack.EMPTY);
//            }
//            else
//            {
//                slot.onSlotChanged();
//            }
//
//            if (stackToMoveFromSlot.getCount() == stackToMove.getCount())
//            {
//                return ItemStack.EMPTY;
//            }
//
//            slot.onTake(playerIn, stackToMoveFromSlot);
//        }
//
//        return stackToMove;
    }
	
	@Override
	public boolean canMergeSlot(ItemStack stack, Slot slotIn)
    {
        return slotIn.inventory != this.craftResult && super.canMergeSlot(stack, slotIn);
    }
	
	
	/** Nested slot classes for melter below. */
	
	public class SlotMelterCast extends Slot
	{
		public SlotMelterCast(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
	    {
	        super(inventoryIn, slotIndex, xPosition, yPosition);
	    }

		@Override
	    public boolean isItemValid(ItemStack stack)
	    {
			// Return true if stack in CAST_SLOT has the ICast capability.
			ICast cap = stack.getCapability(CapabilityCast.CAST_CAPABILITY, null);
			return (cap instanceof ICast) ? true : false;
	    }
	}

	public class SlotMelterFuel extends Slot
	{
		public SlotMelterFuel(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
	    {
	        super(inventoryIn, slotIndex, xPosition, yPosition);
	    }

		@Override
	    public boolean isItemValid(ItemStack stack)
	    {
			return TileEntityMelter.isItemFuel(stack) || isBucket(stack);
	    }
		
		private boolean isBucket(ItemStack stack)
	    {
	        return stack.getItem() == Items.BUCKET;
	    }
	}

	public class SlotMelterInput extends Slot
	{
		public SlotMelterInput(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
	    {
	        super(inventoryIn, slotIndex, xPosition, yPosition);
	    }

		@Override
	    public boolean isItemValid(ItemStack stack)
	    {
			return true;
	    }
	}
	
	public class SlotMelterOutput extends Slot
	{
		public SlotMelterOutput(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
	    {
	        super(inventoryIn, slotIndex, xPosition, yPosition);
	    }

		@Override
	    public boolean isItemValid(ItemStack stack)
	    {
			// Don't allow items to be placed in output slot.
			return false;
	    }
	}
}
