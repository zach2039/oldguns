package com.zach2039.oldguns.init;

import com.google.common.collect.Sets;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.data.*;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.RegistryDataLoader;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DataPackRegistriesHooks;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Set;
import java.util.stream.Collectors;

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
		final var dataGenerator = event.getGenerator();
		final var output = dataGenerator.getPackOutput();
		final var existingFileHelper = event.getExistingFileHelper();
		final var lookupProvider = event.getLookupProvider().thenApply(ModDataProviders::createLookup);

		dataGenerator.addProvider(event.includeClient(), new OldGunsLanguageProvider(output));

		final OldGunsItemModelProvider itemModelProvider = new OldGunsItemModelProvider(output, existingFileHelper);
		dataGenerator.addProvider(event.includeClient(), itemModelProvider);

		dataGenerator.addProvider(event.includeClient(), new OldGunsBlockStateProvider(output, itemModelProvider.existingFileHelper));

		dataGenerator.addProvider(event.includeServer(), new OldGunsRecipeProvider(output));
		dataGenerator.addProvider(event.includeServer(), OldGunsLootTableProvider.create(output));
		dataGenerator.addProvider(event.includeServer(), new OldGunsLootModifierProvider(output));

		final OldGunsBlockTagsProvider blockTagsProvider = new OldGunsBlockTagsProvider(output, lookupProvider, existingFileHelper);
		dataGenerator.addProvider(event.includeServer(), blockTagsProvider);
		dataGenerator.addProvider(event.includeServer(), new OldGunsItemTagsProvider(output, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));
		dataGenerator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(output, lookupProvider, Set.of(OldGuns.MODID)));
	}

	private static HolderLookup.Provider createLookup(final HolderLookup.Provider vanillaLookupProvider) {
		final var registryAccess = RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY);

		//final var builder = new RegistrySetBuilder()
		//		.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
		//		.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
		//		.add(Registries.BIOME, ModBiomes::bootstrap)
		//		.add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModBiomeModifiers::bootstrap);

		final var builder = new RegistrySetBuilder()
				.add(Registries.DAMAGE_TYPE, OldGunsDamageTypesProvider::bootstrap);

		@SuppressWarnings("UnstableApiUsage")
		final var allKeys = DataPackRegistriesHooks.getDataPackRegistries()
				.stream()
				.map(RegistryDataLoader.RegistryData::key)
				.collect(Collectors.toSet());

		final var modKeys = Set.copyOf(builder.getEntryKeys());

		final var missingKeys = Sets.difference(allKeys, modKeys);

		missingKeys.forEach(key -> builder.add(
				ResourceKey.create(ResourceKey.createRegistryKey(key.registry()), key.location()),
				context -> {
				}
		));

		return builder.buildPatch(registryAccess, vanillaLookupProvider);
	}
}
