package com.zach2039.oldguns.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class CaplockMechanismItem extends FirearmPartItem {

	public CaplockMechanismItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.CAPLOCK_MECHANISM)
				.tab(OldGuns.ITEM_GROUP));
	}
}
