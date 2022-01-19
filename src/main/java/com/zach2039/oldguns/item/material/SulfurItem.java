package com.zach2039.oldguns.item.material;

import com.zach2039.oldguns.OldGuns;

public class SulfurItem extends MaterialItem {
	
	public SulfurItem() {
		super(new Properties()
				.stacksTo(64)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}

}
