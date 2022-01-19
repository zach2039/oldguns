package com.zach2039.oldguns.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class GoldTriggerAssemblyItem extends FirearmPartItem {

	public GoldTriggerAssemblyItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.GOLD_TRIGGER_ASSEMBLY)
				.tab(OldGuns.ITEM_GROUP));
	}
}
