package com.zach2039.oldguns.world.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class MediumIronBarrelItem extends FirearmPartItem {

	public MediumIronBarrelItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.MEDIUM_METAL_BARREL)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
}
