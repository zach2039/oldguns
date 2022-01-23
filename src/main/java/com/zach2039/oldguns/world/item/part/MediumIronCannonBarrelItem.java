package com.zach2039.oldguns.world.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.artillery.ArtilleryPart;

public class MediumIronCannonBarrelItem extends ArtilleryPartItem {

	public MediumIronCannonBarrelItem() {
		super((ArtilleryPartProperties) new ArtilleryPartProperties()				
				.partType(ArtilleryPart.MEDIUM_METAL_CANNON_BARREL)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
}