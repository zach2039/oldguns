package com.zach2039.oldguns.world.item.artillery;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.ammo.IArtilleryCharge;

import net.minecraft.world.item.Item;

public class SmallPowderCharge extends Item implements IArtilleryCharge {
	
	public SmallPowderCharge() {
		super(new Properties()
				.stacksTo(16)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}

	@Override
	public float getChargeAmount() {
		return 1f;
	}
}
