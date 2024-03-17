package com.zach2039.oldguns.data;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.init.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on GitHub
 * 
 * @author Choonster
 *
 * With additions by:
 * @author zach2039
 */
public class OldGunsBlockTagsProvider extends BlockTagsProvider {
	
	public OldGunsBlockTagsProvider(final PackOutput output, final CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable final ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, OldGuns.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(final HolderLookup.Provider provider) {
		
		tag(BlockTags.MINEABLE_WITH_PICKAXE)
				.add(ModBlocks.MEDIUM_NAVAL_CANNON.get())
				.add(ModBlocks.LIQUID_NITER_CAULDRON.get())
				;
		
		tag(BlockTags.MINEABLE_WITH_AXE)
				.add(ModBlocks.GUNSMITHS_BENCH.get())
				;

		tag(BlockTags.MINEABLE_WITH_SHOVEL)
				.add(ModBlocks.MEDIUM_GRADE_BLACK_POWDER_BLOCK.get())
				.add(ModBlocks.WET_MEDIUM_GRADE_BLACK_POWDER_BLOCK.get())
				.add(ModBlocks.HIGH_GRADE_BLACK_POWDER_BLOCK.get())
				.add(ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_BLOCK.get())
				.add(ModBlocks.HIGH_GRADE_BLACK_POWDER_CAKE.get())
				.add(ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_CAKE.get())
				.add(ModBlocks.NITER_BEDDING.get())
				;		
	}
}
