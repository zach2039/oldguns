package com.zach2039.oldguns.data;

import javax.annotation.Nullable;

import com.zach2039.oldguns.OldGuns;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * Taken from TestMod3 on Github
 * @author griled-salmon
 * @author Choonster
 */
public class OldGunsBlockTagsProvider extends BlockTagsProvider {
	
	public OldGunsBlockTagsProvider(final DataGenerator generatorIn, @Nullable final ExistingFileHelper existingFileHelper) {
		super(generatorIn, OldGuns.MODID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		
	}
}
