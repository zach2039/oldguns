package com.zach2039.oldguns.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class WheellockMechanismItem extends FirearmPartItem {

	public WheellockMechanismItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.WHEELLOCK_MECHANISM)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
}
