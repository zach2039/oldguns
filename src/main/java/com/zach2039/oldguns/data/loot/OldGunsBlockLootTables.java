package com.zach2039.oldguns.data.loot;

import com.zach2039.oldguns.init.ModBlocks;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.util.ModRegistryUtil;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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
		
		add(ModBlocks.HIGH_GRADE_BLACK_POWDER_BLOCK.get(), (block) -> {
	         return createSingleItemTableWithSilkTouch(block, ModItems.HIGH_GRADE_BLACK_POWDER.get(), ConstantValue.exactly(9.0F));
	      });
		dropSelf(ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_BLOCK.get());
		
		add(ModBlocks.MEDIUM_GRADE_BLACK_POWDER_BLOCK.get(), (block) -> {
	         return createSingleItemTableWithSilkTouch(block, ModItems.MEDIUM_GRADE_BLACK_POWDER.get(), ConstantValue.exactly(9.0F));
	      });
		dropSelf(ModBlocks.WET_MEDIUM_GRADE_BLACK_POWDER_BLOCK.get());
		
		add(ModBlocks.LOW_GRADE_BLACK_POWDER_BLOCK.get(), (block) -> {
	         return createSingleItemTableWithSilkTouch(block, Items.GUNPOWDER, ConstantValue.exactly(9.0F));
	      });
		dropSelf(ModBlocks.WET_LOW_GRADE_BLACK_POWDER_BLOCK.get());
		
		dropSelf(ModBlocks.MEDIUM_NAVAL_CANNON.get());
		
		dropSelf(ModBlocks.CONGREVE_ROCKET_STAND.get());
		
		dropOther(ModBlocks.BLASTING_POWDER_STICK_BLOCK.get(), ModItems.BLASTING_POWDER_STICK.get());
		dropOther(ModBlocks.WALL_BLASTING_POWDER_STICK_BLOCK.get(), ModItems.BLASTING_POWDER_STICK.get());
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return ModRegistryUtil.getModRegistryEntries(ForgeRegistries.BLOCKS);
	}
}
