package com.zach2039.oldguns.world.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class FlintlockMechanismItem extends FirearmPartItem {

	public FlintlockMechanismItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.FLINTLOCK_MECHANISM)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
}
