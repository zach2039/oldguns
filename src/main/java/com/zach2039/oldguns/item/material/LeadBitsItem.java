package com.zach2039.oldguns.item.material;

import com.zach2039.oldguns.OldGuns;

import net.minecraft.item.Item;

public class LeadBitsItem extends MaterialItem {
	
	public LeadBitsItem() {
		super(new Item.Properties()
				.stacksTo(64)
				.tab(OldGuns.ITEM_GROUP));
	}

}
