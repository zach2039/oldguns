package com.zach2039.oldguns.init;

import javax.annotation.Nullable;

import com.zach2039.oldguns.OldGuns;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * Generates this mod's fluid tags.
 *
 * @author Choonster
 * 
 * With additions by:
 * @author zach2039
 */
public class ModFluidTagsProvider extends FluidTagsProvider {
	
	public ModFluidTagsProvider(final DataGenerator dataGenerator, @Nullable final ExistingFileHelper existingFileHelper) {
		super(dataGenerator, OldGuns.MODID, existingFileHelper);
	}

	@Override
	protected void addTags() {

	}
}