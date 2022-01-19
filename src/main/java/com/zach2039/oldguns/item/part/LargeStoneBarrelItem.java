package com.zach2039.oldguns.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class LargeStoneBarrelItem extends FirearmPartItem {

	public LargeStoneBarrelItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.LARGE_ROCK_BARREL)
				.tab(OldGuns.ITEM_GROUP));
	}
}
