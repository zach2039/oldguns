package zach2039.oldguns.common.item.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import zach2039.oldguns.common.OldGuns;

public class ItemGunnersQuadrant extends Item
{
	public ItemGunnersQuadrant()
	{
		setRegistryName(OldGuns.MODID, "gunners_quadrant");
		setUnlocalizedName("gunners_quadrant");
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.COMBAT);
	}
}
