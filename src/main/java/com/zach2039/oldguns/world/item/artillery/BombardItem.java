package com.zach2039.oldguns.world.item.artillery;

import com.zach2039.oldguns.api.artillery.ArtilleryType;

public class BombardItem extends ArtilleryItem {

	public BombardItem(Properties builder) {
		super(builder);
		this.artilleryType = ArtilleryType.BOMBARD;
	}

}
