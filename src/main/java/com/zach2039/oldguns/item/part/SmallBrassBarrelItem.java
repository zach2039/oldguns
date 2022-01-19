package com.zach2039.oldguns.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class SmallBrassBarrelItem extends FirearmPartItem {

	public SmallBrassBarrelItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.SMALL_METAL_BARREL)
				.tab(OldGuns.ITEM_GROUP));
	}
}
