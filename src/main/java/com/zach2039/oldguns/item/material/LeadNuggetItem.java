package com.zach2039.oldguns.item.material;

import com.zach2039.oldguns.OldGuns;

public class LeadNuggetItem extends MaterialItem {
	
	public LeadNuggetItem() {
		super(new Properties()
				.stacksTo(64)
				.tab(OldGuns.ITEM_GROUP));
	}

}
