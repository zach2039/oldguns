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
	private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> lootTableGenerators = ImmutableList.of(
			Pair.of(OldGunsBlockLootTables::new, LootContextParamSets.BLOCK),
			Pair.of(OldGunsGenericLootTables::new, LootContextParamSets.ALL_PARAMS)
	);

	public OldGunsLootTableProvider(final DataGenerator dataGeneratorIn) {
		super(dataGeneratorIn);
	}

	@Override
	protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
		return lootTableGenerators;
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

	/**
	 * Gets a name for this provider, to use in logging.
	 */
	@Override
	public String getName() {
		return "OldGunsLootTables";
	}
}
