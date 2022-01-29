package com.zach2039.oldguns.world.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmPart;

public class TinyStoneBarrelItem extends FirearmPartItem {

	public TinyStoneBarrelItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.TINY_ROCK_BARREL)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
}
