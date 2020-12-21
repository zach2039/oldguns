package zach2039.oldguns.common.item.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import zach2039.oldguns.common.OldGuns;

public class ItemPowderCharge extends Item
{
	public ItemPowderCharge()
	{
		setRegistryName(OldGuns.MODID, "powder_charge");
		setUnlocalizedName("powder_charge");
		setMaxStackSize(1);
		setCreativeTab(OldGuns.OLDGUNS_CREATIVE_TAB);
	}
}
