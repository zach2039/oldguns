package com.zach2039.oldguns.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class WoodGearSetItem extends FirearmPartItem {

	public WoodGearSetItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.WOOD_GEAR_SET)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
}
