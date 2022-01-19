package com.zach2039.oldguns.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class MatchlockMechanismItem extends FirearmPartItem {

	public MatchlockMechanismItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.MATCHLOCK_MECHANISM)
				.tab(OldGuns.ITEM_GROUP));
	}
}
