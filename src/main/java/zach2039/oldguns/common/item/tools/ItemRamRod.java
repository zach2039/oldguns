package zach2039.oldguns.common.item.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import zach2039.oldguns.common.OldGuns;

public class ItemRamRod extends Item
{
	public ItemRamRod()
	{
		setRegistryName(OldGuns.MODID, "ram_rod");
		setUnlocalizedName("ram_rod");
		setMaxStackSize(1);
		setCreativeTab(OldGuns.OLDGUNS_CREATIVE_TAB);
	}
}
