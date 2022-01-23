package com.zach2039.oldguns.world.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.artillery.ArtilleryPart;

public class MediumWoodenCarriageWheelItem extends ArtilleryPartItem {

	public MediumWoodenCarriageWheelItem() {
		super((ArtilleryPartProperties) new ArtilleryPartProperties()				
				.partType(ArtilleryPart.MEDIUM_CARRIAGE_WHEEL)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
}