package com.zach2039.oldguns.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class SmallWoodenStockItem extends FirearmPartItem {

	public SmallWoodenStockItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.SMALL_STOCK)
				.tab(OldGuns.ITEM_GROUP));
	}
}
