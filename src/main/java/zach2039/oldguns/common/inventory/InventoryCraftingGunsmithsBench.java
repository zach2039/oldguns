package zach2039.oldguns.common.inventory;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import zach2039.oldguns.common.inventory.impl.IGunsmithsBench;

public class InventoryCraftingGunsmithsBench extends InventoryCrafting implements IGunsmithsBench
{
	public InventoryCraftingGunsmithsBench(Container eventHandlerIn, int width, int height)
	{
		super(eventHandlerIn, width, height);
	}

	public InventoryCraftingGunsmithsBench(ContainerGunsmithsBench eventHandlerIn, int width, int height)
	{
		super(eventHandlerIn, width, height);
	}
}
