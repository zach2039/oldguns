package com.zach2039.oldguns.world.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.artillery.ArtilleryPart;

public class SmallWoodenNavalCarriageItem extends ArtilleryPartItem {

	public SmallWoodenNavalCarriageItem() {
		super((ArtilleryPartProperties) new ArtilleryPartProperties()				
				.partType(ArtilleryPart.SMALL_NAVAL_CARRIAGE)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
}