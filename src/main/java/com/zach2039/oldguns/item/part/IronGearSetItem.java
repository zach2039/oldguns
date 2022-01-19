package com.zach2039.oldguns.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class IronGearSetItem extends FirearmPartItem {

	public IronGearSetItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.IRON_GEAR_SET)
				.tab(OldGuns.ITEM_GROUP));
	}
}
