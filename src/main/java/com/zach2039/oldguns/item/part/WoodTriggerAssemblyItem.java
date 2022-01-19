package com.zach2039.oldguns.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class WoodTriggerAssemblyItem extends FirearmPartItem {

	public WoodTriggerAssemblyItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.WOOD_TRIGGER_ASSEMBLY)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
}
