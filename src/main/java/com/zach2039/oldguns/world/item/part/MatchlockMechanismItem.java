package com.zach2039.oldguns.world.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmPart;

public class MatchlockMechanismItem extends FirearmPartItem {

	public MatchlockMechanismItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.MATCHLOCK_MECHANISM)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
}
