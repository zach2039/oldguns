package com.zach2039.oldguns.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class SmallStoneBarrelItem extends FirearmPartItem {

	public SmallStoneBarrelItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.SMALL_ROCK_BARREL)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
}
