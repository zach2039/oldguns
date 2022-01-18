package com.zach2039.oldguns.world.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class TinyBrassBarrelItem extends FirearmPartItem {

	public TinyBrassBarrelItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.TINY_METAL_BARREL)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
}
