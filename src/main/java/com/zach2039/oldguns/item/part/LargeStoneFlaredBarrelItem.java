package com.zach2039.oldguns.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class LargeStoneFlaredBarrelItem extends FirearmPartItem {

	public LargeStoneFlaredBarrelItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.LARGE_ROCK_FLARED_BARREL)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
}
