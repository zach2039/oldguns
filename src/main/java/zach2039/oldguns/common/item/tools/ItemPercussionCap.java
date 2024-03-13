package zach2039.oldguns.common.item.tools;

import net.minecraft.item.Item;
import zach2039.oldguns.common.OldGuns;

public class ItemPercussionCap extends Item
{
	public ItemPercussionCap()
	{
		setRegistryName(OldGuns.MODID, "percussion_cap");
		//setUnlocalizedName("percussion_cap");
		setMaxStackSize(4);
		setCreativeTab(OldGuns.OLDGUNS_CREATIVE_TAB);
	}
}
