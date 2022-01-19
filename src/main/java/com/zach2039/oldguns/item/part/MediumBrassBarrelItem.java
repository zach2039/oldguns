package com.zach2039.oldguns.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class MediumBrassBarrelItem extends FirearmPartItem {

	public MediumBrassBarrelItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.MEDIUM_METAL_BARREL)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
}
