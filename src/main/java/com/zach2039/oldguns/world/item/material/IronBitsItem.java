package com.zach2039.oldguns.world.item.material;

import com.zach2039.oldguns.OldGuns;

public class IronBitsItem extends MaterialItem {
	
	public IronBitsItem() {
		super(new Properties()
				.stacksTo(64)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}

}
