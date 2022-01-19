package com.zach2039.oldguns.item.tools;

import com.zach2039.oldguns.OldGuns;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RepairKitItem extends Item {

	public RepairKitItem() {
		super(new Item.Properties()
				.defaultDurability(4)
				.tab(OldGuns.ITEM_GROUP)
				);
	}
	
	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return new ItemStack(this);
	}
}
