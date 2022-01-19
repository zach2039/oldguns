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
import net.minecraft.data.LootTableProvider;
import net.minecraft.loot.LootParameterSet;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTable.Builder;
import net.minecraft.loot.LootTableManager;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.ValidationTracker;
import net.minecraft.util.ResourceLocation;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author grilled-salmon
 */
public class OldGunsLootTableProvider extends LootTableProvider {
	private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> lootTableGenerators = ImmutableList.of(
			Pair.of(OldGunsBlockLootTables::new, LootParameterSets.BLOCK),
			Pair.of(OldGunsGenericLootTables::new, LootParameterSets.ALL_PARAMS)
	);

	public OldGunsLootTableProvider(final DataGenerator dataGeneratorIn) {
		super(dataGeneratorIn);
	}

	@Override
	protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, Builder>>>, LootParameterSet>> getTables() {
		return lootTableGenerators;
	}
	
	@Override
	protected void validate(final Map<ResourceLocation, LootTable> map, final ValidationTracker validationContext) {
		final Set<ResourceLocation> modLootTableIds = LootTables
				.all()
				.stream()
				.filter(lootTable -> lootTable.getNamespace().equals(OldGuns.MODID))
				.collect(Collectors.toSet());

		for (final ResourceLocation id : Sets.difference(modLootTableIds, map.keySet())) {
			validationContext.reportProblem("Missing mod loot table: " + id);
		}

		map.forEach((id, lootTable) -> LootTableManager.validate(validationContext, id, lootTable));
	}

	/**
	 * Gets a name for this provider, to use in logging.
	 */
	@Override
	public String getName() {
		return "OldGunsLootTables";
	}
}
