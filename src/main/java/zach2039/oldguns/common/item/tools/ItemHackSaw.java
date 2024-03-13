package zach2039.oldguns.common.item.tools;

import net.minecraft.item.Item;
import zach2039.oldguns.common.OldGuns;

public class ItemHackSaw extends Item
{
	public ItemHackSaw()
	{
		setRegistryName(OldGuns.MODID, "hack_saw");
		//setUnlocalizedName("hack_saw");
		setMaxStackSize(1);
		setMaxDamage(16);
		setCreativeTab(OldGuns.OLDGUNS_CREATIVE_TAB);
	}
	
	@Override
	public Item getContainerItem()
	{
		return this;
	}
}
