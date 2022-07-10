package com.zach2039.oldguns.data;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.init.ModLootModifierSerializers;
import com.zach2039.oldguns.init.ModLootTables;
import com.zach2039.oldguns.world.level.storage.loot.modifiers.LootTableLootModifier;
import com.zach2039.oldguns.world.level.storage.loot.predicates.LootSpawnDesignNotesLootCondition;
import com.zach2039.oldguns.world.level.storage.loot.predicates.LootSpawnFirearmsLootCondition;
import com.zach2039.oldguns.world.level.storage.loot.predicates.LootSpawnMechanismsLootCondition;

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
 * @author zach2039
 */
public class OldGunsLootModifierProvider extends GlobalLootModifierProvider {

	public OldGunsLootModifierProvider(final DataGenerator gen) {
		super(gen, OldGuns.MODID);
	}

	@Override
	protected void start() {
		
		add("loot_table_matchlock_firearm", new LootTableLootModifier(
				new LootItemCondition[]{
						LootSpawnFirearmsLootCondition.builder().build(),
						LootItemRandomChanceCondition.randomChance(0.5f).build(),
						LootTableIdCondition.builder(BuiltInLootTables.SIMPLE_DUNGEON)
						.or(LootTableIdCondition.builder(BuiltInLootTables.SHIPWRECK_SUPPLY))
						.or(LootTableIdCondition.builder(BuiltInLootTables.SHIPWRECK_TREASURE))
						.build(),
				},
				ModLootTables.LOOT_TABLE_MATCHLOCK_FIREARM
		));
		
		
		add("loot_table_mechanism", new LootTableLootModifier(
				new LootItemCondition[]{
						LootSpawnMechanismsLootCondition.builder().build(),
						LootItemRandomChanceCondition.randomChance(0.5f).build(),
						LootTableIdCondition.builder(BuiltInLootTables.SIMPLE_DUNGEON)
						.or(LootTableIdCondition.builder(BuiltInLootTables.SHIPWRECK_SUPPLY))
						.or(LootTableIdCondition.builder(BuiltInLootTables.SHIPWRECK_TREASURE))
						.build(),
				},
				ModLootTables.LOOT_TABLE_MECHANISM
		));
		
		
		add("loot_table_design_notes_mechanism", new LootTableLootModifier(
				new LootItemCondition[]{
						LootSpawnDesignNotesLootCondition.builder().build(),
						LootItemRandomChanceCondition.randomChance(0.5f).build(),
						LootTableIdCondition.builder(BuiltInLootTables.VILLAGE_WEAPONSMITH)
						.or(LootTableIdCondition.builder(BuiltInLootTables.SIMPLE_DUNGEON))
						.or(LootTableIdCondition.builder(BuiltInLootTables.SHIPWRECK_SUPPLY))
						.or(LootTableIdCondition.builder(BuiltInLootTables.SHIPWRECK_TREASURE))
						.build(),
				},
				ModLootTables.LOOT_TABLE_DESIGN_NOTES_MECHANISM
		));
		
		
		add("loot_table_design_notes_matchlock", new LootTableLootModifier(
				new LootItemCondition[]{
						LootSpawnDesignNotesLootCondition.builder().build(),
						LootItemRandomChanceCondition.randomChance(0.4f).build(),
						LootTableIdCondition.builder(BuiltInLootTables.SIMPLE_DUNGEON)
						.or(LootTableIdCondition.builder(BuiltInLootTables.SHIPWRECK_SUPPLY))
						.or(LootTableIdCondition.builder(BuiltInLootTables.SHIPWRECK_TREASURE))
						.build(),
				},
				ModLootTables.LOOT_TABLE_DESIGN_NOTES_MATCHLOCK
		));
		
		
		add("loot_table_design_notes_wheellock", new LootTableLootModifier(
				new LootItemCondition[]{
						LootSpawnDesignNotesLootCondition.builder().build(),
						LootItemRandomChanceCondition.randomChance(0.3f).build(),
						LootTableIdCondition.builder(BuiltInLootTables.SIMPLE_DUNGEON)
						.or(LootTableIdCondition.builder(BuiltInLootTables.SHIPWRECK_SUPPLY))
						.or(LootTableIdCondition.builder(BuiltInLootTables.SHIPWRECK_TREASURE))
						.build(),
				},
				ModLootTables.LOOT_TABLE_DESIGN_NOTES_WHEELLOCK
		));
		
		
		add("loot_table_design_notes_flintlock", new LootTableLootModifier(
				new LootItemCondition[]{
						LootSpawnDesignNotesLootCondition.builder().build(),
						LootItemRandomChanceCondition.randomChance(0.2f).build(),
						LootTableIdCondition.builder(BuiltInLootTables.SIMPLE_DUNGEON)
						.or(LootTableIdCondition.builder(BuiltInLootTables.SHIPWRECK_SUPPLY))
						.or(LootTableIdCondition.builder(BuiltInLootTables.SHIPWRECK_TREASURE))
						.build(),
				},
				ModLootTables.LOOT_TABLE_DESIGN_NOTES_FLINTLOCK
		));
		
		
	}

	@Override
	public String getName() {
		return "OldGunsLootModifiers";
	}
}
