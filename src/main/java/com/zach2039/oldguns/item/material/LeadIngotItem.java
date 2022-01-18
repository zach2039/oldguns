package com.zach2039.oldguns.world.item.material;

import com.zach2039.oldguns.OldGuns;

public class LeadIngotItem extends MaterialItem {
	
	public LeadIngotItem() {
		super(new Properties()
				.stacksTo(64)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}

}
