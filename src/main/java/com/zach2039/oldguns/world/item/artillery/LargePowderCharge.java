package com.zach2039.oldguns.world.item.artillery;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.ammo.IArtilleryCharge;

import net.minecraft.world.item.Item;

public class LargePowderCharge extends Item implements IArtilleryCharge {
	
	public LargePowderCharge() {
		super(new Properties()
				.stacksTo(8)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}

	@Override
	public float getChargeAmount() {
		return 5f;
	}
}
