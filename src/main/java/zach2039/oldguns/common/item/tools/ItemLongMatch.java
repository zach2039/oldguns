package zach2039.oldguns.common.item.tools;

import net.minecraft.item.Item;
import zach2039.oldguns.common.OldGuns;

public class ItemLongMatch extends Item
{
	public ItemLongMatch()
	{
		setRegistryName(OldGuns.MODID, "long_match");
		//setUnlocalizedName("long_match");
		setMaxStackSize(1);
		setCreativeTab(OldGuns.OLDGUNS_CREATIVE_TAB);
	}
}
