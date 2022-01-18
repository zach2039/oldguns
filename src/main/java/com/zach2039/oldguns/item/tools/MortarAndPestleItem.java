package com.zach2039.oldguns.world.item.tools;

import com.zach2039.oldguns.OldGuns;

import net.minecraft.item.ItemStack;

public class MortarAndPestleItem extends Item {

	public MortarAndPestleItem() {
		super(new Properties()
				.defaultDurability(512)
				.tab(OldGuns.CREATIVE_MODE_TAB)
				);
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return new ItemStack(this);
	}
}
