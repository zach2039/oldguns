package com.zach2039.oldguns.world.item.tools;

import com.zach2039.oldguns.OldGuns;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class HacksawItem extends Item {

	public HacksawItem() {
		super(new Properties()
				.defaultDurability(256)
				.tab(OldGuns.CREATIVE_MODE_TAB)
				);
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return new ItemStack(this);
	}
}
