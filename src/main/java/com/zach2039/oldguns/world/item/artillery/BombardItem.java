package com.zach2039.oldguns.world.item.artillery;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.artillery.ArtilleryType;

public class BombardItem extends ArtilleryItem {
	
	public BombardItem() {
		super(new Properties().stacksTo(1).tab(OldGuns.CREATIVE_MODE_TAB));
		this.artilleryType = ArtilleryType.BOMBARD;
	}
}
