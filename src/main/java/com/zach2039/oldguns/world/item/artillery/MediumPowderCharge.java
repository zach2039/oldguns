package com.zach2039.oldguns.world.item.artillery;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.ammo.BlackPowderType;
import com.zach2039.oldguns.api.ammo.IArtilleryCharge;

import net.minecraft.world.item.Item;

public class MediumPowderCharge extends Item implements IArtilleryCharge {
	
	public MediumPowderCharge() {
		super(new Properties()
				.stacksTo(12)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}

	@Override
	public float getChargeAmount() {
		return 3f;
	}
}
