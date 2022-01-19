package com.zach2039.oldguns.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class LargeWoodenHandleItem extends FirearmPartItem {

	public LargeWoodenHandleItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.LARGE_HANDLE)
				.tab(OldGuns.ITEM_GROUP));
	}
}
