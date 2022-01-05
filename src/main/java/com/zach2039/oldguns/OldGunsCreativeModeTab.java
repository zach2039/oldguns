package com.zach2039.oldguns;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class OldGunsCreativeModeTab extends CreativeModeTab {
	private final ItemStack itemstackIcon;
	
	public OldGunsCreativeModeTab()	{
		super(OldGuns.MODID);
		itemstackIcon = new ItemStack(Items.STONE_SWORD);
	}

	@Override
	public ItemStack makeIcon()	{
		return this.itemstackIcon;
	}
	
	@Override
	public void fillItemList(final NonNullList<ItemStack> items) {
		items.add(itemstackIcon.copy());
		super.fillItemList(items);
	}
}
