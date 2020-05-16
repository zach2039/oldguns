package zach2039.oldguns.common.inventory;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import zach2039.oldguns.common.inventory.impl.IMelter;

public class InventoryCraftingMelter extends InventoryCrafting implements IMelter
{
	public InventoryCraftingMelter(Container eventHandlerIn, int width, int height)
	{
		super(eventHandlerIn, width, height);
	}

	public InventoryCraftingMelter(ContainerGunsmithsBench eventHandlerIn, int width, int height)
	{
		super(eventHandlerIn, width, height);
	}
}
