package com.zach2039.oldguns.data;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.init.ModLootModifierSerializers;
import com.zach2039.oldguns.init.ModLootTables;
import com.zach2039.oldguns.world.level.storage.loot.LootTableLootModifier;
import com.zach2039.oldguns.world.level.storage.loot.predicates.LootSpawnAmmoLootCondition;
import com.zach2039.oldguns.world.level.storage.loot.predicates.LootSpawnExoticsLootCondition;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;


/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author grilled-salmon
 */
public class OldGunsLootModifierProvider extends GlobalLootModifierProvider {

	public OldGunsLootModifierProvider(final DataGenerator gen) {
		super(gen, OldGuns.MODID);
	}

	@Override
	protected void start() {
		add("loot_table_exotic_firearms", ModLootModifierSerializers.LOOT_TABLE.get(), new LootTableLootModifier(
				new LootItemCondition[]{
						LootSpawnExoticsLootCondition.builder().build(),
						LootItemRandomChanceCondition.randomChance(0.3f).build(),
						LootTableIdCondition.builder(BuiltInLootTables.SIMPLE_DUNGEON).build(),
				},
				ModLootTables.LOOT_TABLE_EXOTIC_FIREARMS
		));
		add("loot_table_ammo", ModLootModifierSerializers.LOOT_TABLE.get(), new LootTableLootModifier(
				new LootItemCondition[]{
						LootSpawnAmmoLootCondition.builder().build(),
						LootItemRandomChanceCondition.randomChance(0.4f).build(),
						LootTableIdCondition.builder(BuiltInLootTables.SIMPLE_DUNGEON).build(),
				},
				ModLootTables.LOOT_TABLE_AMMO
		));
	}

	@Override
	public String getName() {
		return "OldGunsLootModifiers";
	}
}
