package com.zach2039.oldguns.world.item.equipment;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.init.ModBlocks;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.StandingAndWallBlockItem;

public class BlastingPowderStickBlockItem extends StandingAndWallBlockItem {

	public BlastingPowderStickBlockItem() {
		super(ModBlocks.BLASTING_POWDER_STICK_BLOCK.get(), ModBlocks.WALL_BLASTING_POWDER_STICK_BLOCK.get(), new Item.Properties().tab(OldGuns.CREATIVE_MODE_TAB));
	}	
}
