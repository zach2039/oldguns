package com.zach2039.oldguns.world.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.artillery.ArtilleryPart;

public class LargeIronCannonBarrelItem extends ArtilleryPartItem {

	public LargeIronCannonBarrelItem() {
		super((ArtilleryPartProperties) new ArtilleryPartProperties()				
				.partType(ArtilleryPart.LARGE_METAL_CANNON_BARREL)
				.tab(OldGuns.CREATIVE_MODE_TAB));
	}
}
