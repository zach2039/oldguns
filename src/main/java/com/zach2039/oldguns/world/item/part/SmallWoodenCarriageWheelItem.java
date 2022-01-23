package com.zach2039.oldguns.world.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.artillery.ArtilleryPart;

public class SmallWoodenCarriageWheelItem extends ArtilleryPartItem {

	public SmallWoodenCarriageWheelItem() {
		super((ArtilleryPartProperties) new ArtilleryPartProperties()				
				.partType(ArtilleryPart.SMALL_CARRIAGE_WHEEL)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
}