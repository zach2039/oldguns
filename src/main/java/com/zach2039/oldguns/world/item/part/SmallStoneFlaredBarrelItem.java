package com.zach2039.oldguns.world.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmPart;

public class SmallStoneFlaredBarrelItem extends FirearmPartItem {

	public SmallStoneFlaredBarrelItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.SMALL_ROCK_FLARED_BARREL)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
}
