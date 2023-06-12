package com.zach2039.oldguns.world.item.part;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmPart;

import net.minecraft.world.item.Item;

public class FirearmPartItem extends Item {

	private FirearmPartItem(FirearmPartProperties builder) {
		super((Properties) builder);
		this.partType = builder.partType;
	}
	
	public FirearmPartItem(FirearmPart part) {
		this((FirearmPartProperties) new FirearmPartProperties()				
				.partType(part));
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
	
	FirearmPart partType = FirearmPart.SMALL_HANDLE;
}
