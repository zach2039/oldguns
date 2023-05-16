package com.zach2039.oldguns.world.item.material;

import com.zach2039.oldguns.OldGuns;

import net.minecraft.world.item.Item;

public class MaterialItem extends Item {
	
	public MaterialItem(int maxStackSize) {
		super(new Properties()
				.stacksTo(maxStackSize)
				);
	}
	
	public MaterialItem() {
		super(new Properties()
				.stacksTo(64)
				);
	}

}
