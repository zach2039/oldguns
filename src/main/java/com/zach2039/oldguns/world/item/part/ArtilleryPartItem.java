package com.zach2039.oldguns.world.item.part;

import com.zach2039.oldguns.api.artillery.ArtilleryPart;

import net.minecraft.world.item.Item;

public abstract class ArtilleryPartItem extends Item {

	private ArtilleryPart partType = ArtilleryPart.SMALL_METAL_CANNON_BARREL;
	
	public ArtilleryPartItem(ArtilleryPartProperties builder) {
		super((Properties) builder);
		this.partType = builder.partType;
	}
	
	public ArtilleryPart getPartType() {
		return this.partType;
	}
	
	public static class ArtilleryPartProperties extends Properties {
		/**
		 * Part type of this artillery part.
		 */
		ArtilleryPart partType = ArtilleryPart.SMALL_METAL_CANNON_BARREL;
		
		public ArtilleryPartProperties partType(ArtilleryPart partType) {
			this.partType = partType;
			return this;
		}
	}
}
