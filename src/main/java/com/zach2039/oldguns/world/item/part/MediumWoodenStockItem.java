package com.zach2039.oldguns.world.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class MediumWoodenStockItem extends FirearmPartItem {

	public MediumWoodenStockItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.MEDIUM_STOCK)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
}
