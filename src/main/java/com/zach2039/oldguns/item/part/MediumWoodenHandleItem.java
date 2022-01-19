package com.zach2039.oldguns.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class MediumWoodenHandleItem extends FirearmPartItem {

	public MediumWoodenHandleItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.MEDIUM_HANDLE)
				.tab(OldGuns.ITEM_GROUP));
	}
}
