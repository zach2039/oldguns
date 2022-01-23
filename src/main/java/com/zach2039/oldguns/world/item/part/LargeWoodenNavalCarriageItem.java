package com.zach2039.oldguns.world.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.artillery.ArtilleryPart;

public class LargeWoodenNavalCarriageItem extends ArtilleryPartItem {

	public LargeWoodenNavalCarriageItem() {
		super((ArtilleryPartProperties) new ArtilleryPartProperties()				
				.partType(ArtilleryPart.LARGE_NAVAL_CARRIAGE)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
}