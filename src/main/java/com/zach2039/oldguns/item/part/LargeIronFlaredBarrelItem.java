package com.zach2039.oldguns.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class LargeIronFlaredBarrelItem extends FirearmPartItem {

	public LargeIronFlaredBarrelItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.LARGE_METAL_FLARED_BARREL)
				.tab(OldGuns.ITEM_GROUP));
	}
}
