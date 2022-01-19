package com.zach2039.oldguns.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class MediumBrassFlaredBarrelItem extends FirearmPartItem {

	public MediumBrassFlaredBarrelItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.MEDIUM_METAL_FLARED_BARREL)
				.tab(OldGuns.ITEM_GROUP));
	}
}
