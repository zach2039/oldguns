package zach2039.oldguns.common.item.tools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import zach2039.oldguns.common.OldGuns;

public class ItemSmallMusketBallCast extends Item
{
	public ItemSmallMusketBallCast()
	{
		setRegistryName(OldGuns.MODID, "small_musket_ball_cast");
		setUnlocalizedName("small_musket_ball_cast");
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.COMBAT);
	}
	
	@Override
	public Item getContainerItem()
	{
		return this;
	}
}
