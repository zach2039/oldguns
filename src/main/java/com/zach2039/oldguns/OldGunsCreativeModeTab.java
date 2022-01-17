package com.zach2039.oldguns;

import com.zach2039.oldguns.init.ModItems;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class OldGunsCreativeModeTab extends ItemGroup {
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
