package com.zach2039.oldguns.world.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmPart;

public class CaplockMechanismItem extends FirearmPartItem {

	public CaplockMechanismItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.CAPLOCK_MECHANISM)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
}
