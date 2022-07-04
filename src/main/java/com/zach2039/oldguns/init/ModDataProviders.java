package com.zach2039.oldguns.init;

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
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author zach2039
 */
@Mod.EventBusSubscriber(modid = OldGuns.MODID, bus = Bus.MOD)
public class ModDataProviders {
	@SubscribeEvent
	public static void registerDataProviders(final GatherDataEvent event) {
		final DataGenerator dataGenerator = event.getGenerator();
		final ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		if (event.includeClient()) {
			dataGenerator.addProvider(true, new OldGunsLanguageProvider(dataGenerator));
			
			final OldGunsItemModelProvider itemModelProvider = new OldGunsItemModelProvider(dataGenerator, existingFileHelper);
			dataGenerator.addProvider(true, itemModelProvider);
			
			dataGenerator.addProvider(true, new OldGunsBlockStateProvider(dataGenerator, itemModelProvider.existingFileHelper));
		}

		if (event.includeServer()) {
			dataGenerator.addProvider(true, new OldGunsRecipeProvider(dataGenerator));
			dataGenerator.addProvider(true, new OldGunsLootTableProvider(dataGenerator));
			dataGenerator.addProvider(true, new OldGunsLootModifierProvider(dataGenerator));
			
			final OldGunsBlockTagsProvider blockTagsProvider = new OldGunsBlockTagsProvider(dataGenerator, existingFileHelper);
			dataGenerator.addProvider(true, blockTagsProvider);
			dataGenerator.addProvider(true, new OldGunsItemTagsProvider(dataGenerator, blockTagsProvider, existingFileHelper));
		}
	}
}
