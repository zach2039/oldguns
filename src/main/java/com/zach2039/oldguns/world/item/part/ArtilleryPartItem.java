package com.zach2039.oldguns.world.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.artillery.ArtilleryPart;

import net.minecraft.world.item.Item;

public class ArtilleryPartItem extends Item {

	private ArtilleryPartItem(ArtilleryPartProperties builder) {
		super((Properties) builder);
		this.partType = builder.partType;
	}
	
	public ArtilleryPartItem(ArtilleryPart part) {
		this((ArtilleryPartProperties) new ArtilleryPartProperties()				
				.partType(part));
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
	
	private ArtilleryPart partType = ArtilleryPart.SMALL_METAL_CANNON_BARREL;
}
