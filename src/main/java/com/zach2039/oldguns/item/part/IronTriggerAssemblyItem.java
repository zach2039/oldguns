package com.zach2039.oldguns.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class IronTriggerAssemblyItem extends FirearmPartItem {

	public IronTriggerAssemblyItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.IRON_TRIGGER_ASSEMBLY)
				.tab(OldGuns.ITEM_GROUP));
	}
}
