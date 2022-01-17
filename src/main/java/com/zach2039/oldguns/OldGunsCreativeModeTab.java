package com.zach2039.oldguns;

import com.zach2039.oldguns.init.ModItems;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.item.CreativeModeTab;

public class OldGunsCreativeModeTab extends CreativeModeTab {
	private final ItemStack itemstackIcon;
	
	public OldGunsCreativeModeTab()	{
		super(OldGuns.MODID);
		itemstackIcon = new ItemStack(ModItems.FLINTLOCK_PISTOL.get());
	}

	@Override
	public ItemStack makeIcon()	{
		return this.itemstackIcon;
	}
	
	@Override
	public void fillItemList(final NonNullList<ItemStack> items) {
		super.fillItemList(items);
	}
}
