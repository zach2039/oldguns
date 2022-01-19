package com.zach2039.oldguns.item.tools;

import com.zach2039.oldguns.OldGuns;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MortarAndPestleItem extends Item {

	public MortarAndPestleItem() {
		super(new Item.Properties()
				.defaultDurability(512)
				.tab(OldGuns.CREATIVE_MODE_TAB)
				);
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return new ItemStack(this);
	}
}
