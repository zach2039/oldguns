package com.zach2039.oldguns.item.material;

import com.zach2039.oldguns.OldGuns;

public class IronBitsItem extends MaterialItem {
	
	public IronBitsItem() {
		super(new Properties()
				.stacksTo(64)
				.tab(OldGuns.ITEM_GROUP));
	}

}
