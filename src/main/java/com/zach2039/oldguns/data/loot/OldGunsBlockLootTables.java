package com.zach2039.oldguns.data.loot;

import com.zach2039.oldguns.init.ModBlocks;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.util.ModRegistryUtil;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.ForgeRegistries;

public class OldGunsBlockLootTables extends BlockLoot {
	@Override
	protected void addTables() {
		dropSelf(ModBlocks.GUNSMITHS_BENCH.get());
		
		dropSelf(ModBlocks.NITER_BEDDING.get());
		
		dropOther(ModBlocks.LIQUID_NITER_CAULDRON.get(), Blocks.CAULDRON);
		
		dropSelf(ModBlocks.HIGH_GRADE_BLACK_POWDER_CAKE.get());
		dropSelf(ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_CAKE.get());
		
		add(ModBlocks.HIGH_GRADE_BLACK_POWDER_BLOCK.get(), (p_124235_) -> {
	         return createSingleItemTableWithSilkTouch(p_124235_, ModItems.HIGH_GRADE_BLACK_POWDER.get(), ConstantValue.exactly(9.0F));
	      });
		dropSelf(ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_BLOCK.get());
		
		add(ModBlocks.MEDIUM_GRADE_BLACK_POWDER_BLOCK.get(), (p_124235_) -> {
	         return createSingleItemTableWithSilkTouch(p_124235_, ModItems.MEDIUM_GRADE_BLACK_POWDER.get(), ConstantValue.exactly(9.0F));
	      });
		dropSelf(ModBlocks.WET_MEDIUM_GRADE_BLACK_POWDER_BLOCK.get());
		
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return ModRegistryUtil.getModRegistryEntries(ForgeRegistries.BLOCKS);
	}
}
