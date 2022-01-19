package com.zach2039.oldguns.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class SmallWoodenHandleItem extends FirearmPartItem {

	public SmallWoodenHandleItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.SMALL_HANDLE)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
}
