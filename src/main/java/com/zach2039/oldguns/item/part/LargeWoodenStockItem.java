package com.zach2039.oldguns.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public class LargeWoodenStockItem extends FirearmPartItem {

	public LargeWoodenStockItem() {
		super((FirearmPartProperties) new FirearmPartProperties()				
				.partType(FirearmPart.LARGE_STOCK)
				.tab(OldGuns.ITEM_GROUP));
	}
}
