package com.zach2039.oldguns.world.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class HugeIronBarrelItem extends FirearmPartItem {

	public HugeIronBarrelItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.HUGE_METAL_BARREL)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
}
