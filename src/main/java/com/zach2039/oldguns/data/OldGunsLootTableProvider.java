package com.zach2039.oldguns.data;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Pair;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.data.loot.OldGunsBlockLootTables;
import com.zach2039.oldguns.data.loot.OldGunsGenericLootTables;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 *
 * @author Choonster
 *
 * With additions by:
 * @author zach2039
 */
public class OldGunsLootTableProvider extends LootTableProvider {
	private OldGunsLootTableProvider(final PackOutput output, final List<SubProviderEntry> subProviders) {
		super(output, Set.of(), subProviders);
	}

	public static OldGunsLootTableProvider create(final PackOutput output) {
		return new OldGunsLootTableProvider(output, ImmutableList.of(
				new SubProviderEntry(OldGunsBlockLootTables::new, LootContextParamSets.BLOCK),
				new SubProviderEntry(OldGunsGenericLootTables::new, LootContextParamSets.ALL_PARAMS)
		));
	}

	@Override
	protected void validate(final Map<ResourceLocation, LootTable> map, final ValidationContext validationContext) {
		final Set<ResourceLocation> modLootTableIds = BuiltInLootTables
				.all()
				.stream()
				.filter(lootTable -> lootTable.getNamespace().equals(OldGuns.MODID))
				.collect(Collectors.toSet());

		for (final ResourceLocation id : Sets.difference(modLootTableIds, map.keySet())) {
			validationContext.reportProblem("Missing mod loot table: " + id);
		}

		map.forEach((id, lootTable) -> LootTables.validate(validationContext, id, lootTable));
	}
}