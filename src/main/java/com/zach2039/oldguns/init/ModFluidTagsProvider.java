package com.zach2039.oldguns.init;

import javax.annotation.Nullable;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.data.OldGunsBlockStateProvider;
import com.zach2039.oldguns.data.OldGunsBlockTagsProvider;
import com.zach2039.oldguns.data.OldGunsItemModelProvider;
import com.zach2039.oldguns.data.OldGunsItemTagsProvider;
import com.zach2039.oldguns.data.OldGunsLanguageProvider;
import com.zach2039.oldguns.data.OldGunsLootModifierProvider;
import com.zach2039.oldguns.data.OldGunsLootTableProvider;
import com.zach2039.oldguns.data.OldGunsRecipeProvider;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

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