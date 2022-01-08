package com.zach2039.oldguns.data.loot;

import com.zach2039.oldguns.init.ModBlocks;
import com.zach2039.oldguns.util.ModRegistryUtil;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class OldGunsBlockLootTables extends BlockLoot {
	@Override
	protected void addTables() {
		dropSelf(ModBlocks.GUNSMITHS_BENCH.get());
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return ModRegistryUtil.getModRegistryEntries(ForgeRegistries.BLOCKS);
	}
}
