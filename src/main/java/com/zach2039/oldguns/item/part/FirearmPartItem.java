package com.zach2039.oldguns.world.item.part;

import com.zach2039.oldguns.api.firearm.FirearmType.FirearmPart;

public abstract class FirearmPartItem extends Item {

	private FirearmPart partType = FirearmPart.SMALL_HANDLE;
	
	public FirearmPartItem(FirearmPartProperties builder) {
		super((Properties) builder);
		this.partType = builder.partType;
	}
	
	public FirearmPart getPartType() {
		return this.partType;
	}
	
	public static class FirearmPartProperties extends Properties {
		/**
		 * Part type of this firearm part.
		 */
		FirearmPart partType = FirearmPart.SMALL_HANDLE;
		
		public FirearmPartProperties partType(FirearmPart partType) {
			this.partType = partType;
			return this;
		}
	}
}
