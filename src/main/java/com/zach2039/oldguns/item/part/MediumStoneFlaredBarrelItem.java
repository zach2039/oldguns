package com.zach2039.oldguns.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class MediumStoneFlaredBarrelItem extends FirearmPartItem {

	public MediumStoneFlaredBarrelItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.MEDIUM_ROCK_FLARED_BARREL)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
}
