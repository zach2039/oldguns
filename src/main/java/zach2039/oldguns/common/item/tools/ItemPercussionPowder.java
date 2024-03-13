package zach2039.oldguns.common.item.tools;

import net.minecraft.item.Item;
import zach2039.oldguns.common.OldGuns;

public class ItemPercussionPowder extends Item
{
	public ItemPercussionPowder()
	{
		setRegistryName(OldGuns.MODID, "percussion_powder");
		setTranslationKey("percussion_powder");
		setMaxStackSize(64);
		setCreativeTab(OldGuns.OLDGUNS_CREATIVE_TAB);
	}
}
