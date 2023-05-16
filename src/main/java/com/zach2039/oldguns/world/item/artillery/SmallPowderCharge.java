package com.zach2039.oldguns.world.item.artillery;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.ammo.ArtilleryCharge;

import net.minecraft.world.item.Item;

public class SmallPowderCharge extends Item implements ArtilleryCharge {
	
	public SmallPowderCharge() {
		super(new Properties()
				.stacksTo(16)
		);
	}

	@Override
	public float getChargeAmount() {
		return 1f;
	}
}
