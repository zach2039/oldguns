package com.zach2039.oldguns.init;

import javax.annotation.Nullable;

import com.zach2039.oldguns.OldGuns;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

/**
 * Generates this mod's fluid tags.
 *
 * @author Choonster
 * 
 * With additions by:
 * @author zach2039
 */
public class ModFluidTagsProvider extends FluidTagsProvider {
	
	public ModFluidTagsProvider(final PackOutput output,
								final CompletableFuture<HolderLookup.Provider> holderLookup,
								@Nullable final ExistingFileHelper existingFileHelper) {
		super(output, holderLookup, OldGuns.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {

	}
}