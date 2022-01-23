package com.zach2039.oldguns.world.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.artillery.ArtilleryPart;

public class LargeWoodenCarriageWheelItem extends ArtilleryPartItem {

	public LargeWoodenCarriageWheelItem() {
		super((ArtilleryPartProperties) new ArtilleryPartProperties()				
				.partType(ArtilleryPart.LARGE_CARRIAGE_WHEEL)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
}