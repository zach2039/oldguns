package zach2039.oldguns.common.inventory.util;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class InventoryContainerHelper
{
	/**
	 * Adds player inventory slots to inventory container.
	 * @param stackIn
	 * @param ammoCount
	 */
	public static void addPlayerInventoryToContainer(Container containerInventory, InventoryPlayer playerInventory)
	{
		// Add player inventory slots to this container.
        for (int k = 0; k < 3; ++k)
        {
            for (int i1 = 0; i1 < 9; ++i1)
            {
            	addSlotToContainer(containerInventory, new Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }

        // Add hotbar slots to this container.
        for (int l = 0; l < 9; ++l)
        {
        	addSlotToContainer(containerInventory, new Slot(playerInventory, l, 8 + l * 18, 142));
        }
	}
	
	private static Slot addSlotToContainer(Container containerInventory, Slot slotIn)
	{
		slotIn.slotNumber = containerInventory.inventorySlots.size();
		containerInventory.inventorySlots.add(slotIn);
		containerInventory.inventoryItemStacks.add(ItemStack.EMPTY);
	    return slotIn;
	}
}
