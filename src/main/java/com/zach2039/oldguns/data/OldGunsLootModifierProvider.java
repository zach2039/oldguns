package com.zach2039.oldguns.data;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.init.ModLootModifierSerializers;
import com.zach2039.oldguns.init.ModLootTables;
import com.zach2039.oldguns.loot.conditions.LootSpawnDesignNotesLootCondition;
import com.zach2039.oldguns.loot.conditions.LootSpawnFirearmsLootCondition;
import com.zach2039.oldguns.loot.conditions.LootSpawnMechanismsLootCondition;
import com.zach2039.oldguns.loot.modifiers.LootTableLootModifier;

import net.minecraft.data.DataGenerator;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.RandomChance;
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
		
		add("loot_table_matchlock_firearm", ModLootModifierSerializers.LOOT_TABLE.get(), new LootTableLootModifier(
				new ILootCondition[]{
						LootSpawnFirearmsLootCondition.builder().build(),
						RandomChance.randomChance(0.5f).build(),
						LootTableIdCondition.builder(LootTables.SIMPLE_DUNGEON)
						.or(LootTableIdCondition.builder(LootTables.SHIPWRECK_SUPPLY))
						.or(LootTableIdCondition.builder(LootTables.SHIPWRECK_TREASURE))
						.build(),
				},
				ModLootTables.LOOT_TABLE_MATCHLOCK_FIREARM
		));
		
		
		add("loot_table_mechanism", ModLootModifierSerializers.LOOT_TABLE.get(), new LootTableLootModifier(
				new ILootCondition[]{
						LootSpawnMechanismsLootCondition.builder().build(),
						RandomChance.randomChance(0.5f).build(),
						LootTableIdCondition.builder(LootTables.SIMPLE_DUNGEON)
						.or(LootTableIdCondition.builder(LootTables.SHIPWRECK_SUPPLY))
						.or(LootTableIdCondition.builder(LootTables.SHIPWRECK_TREASURE))
						.build(),
				},
				ModLootTables.LOOT_TABLE_MECHANISM
		));
		
		
		add("loot_table_design_notes_mechanism", ModLootModifierSerializers.LOOT_TABLE.get(), new LootTableLootModifier(
				new ILootCondition[]{
						LootSpawnDesignNotesLootCondition.builder().build(),
						RandomChance.randomChance(0.5f).build(),
						LootTableIdCondition.builder(LootTables.VILLAGE_WEAPONSMITH)
						.or(LootTableIdCondition.builder(LootTables.SIMPLE_DUNGEON))
						.or(LootTableIdCondition.builder(LootTables.SHIPWRECK_SUPPLY))
						.or(LootTableIdCondition.builder(LootTables.SHIPWRECK_TREASURE))
						.build(),
				},
				ModLootTables.LOOT_TABLE_DESIGN_NOTES_MECHANISM
		));
		
		
		add("loot_table_design_notes_matchlock", ModLootModifierSerializers.LOOT_TABLE.get(), new LootTableLootModifier(
				new ILootCondition[]{
						LootSpawnDesignNotesLootCondition.builder().build(),
						RandomChance.randomChance(0.4f).build(),
						LootTableIdCondition.builder(LootTables.SIMPLE_DUNGEON)
						.or(LootTableIdCondition.builder(LootTables.SHIPWRECK_SUPPLY))
						.or(LootTableIdCondition.builder(LootTables.SHIPWRECK_TREASURE))
						.build(),
				},
				ModLootTables.LOOT_TABLE_DESIGN_NOTES_MATCHLOCK
		));
		
		
		add("loot_table_design_notes_wheellock", ModLootModifierSerializers.LOOT_TABLE.get(), new LootTableLootModifier(
				new ILootCondition[]{
						LootSpawnDesignNotesLootCondition.builder().build(),
						RandomChance.randomChance(0.3f).build(),
						LootTableIdCondition.builder(LootTables.SIMPLE_DUNGEON)
						.or(LootTableIdCondition.builder(LootTables.SHIPWRECK_SUPPLY))
						.or(LootTableIdCondition.builder(LootTables.SHIPWRECK_TREASURE))
						.build(),
				},
				ModLootTables.LOOT_TABLE_DESIGN_NOTES_WHEELLOCK
		));
		
		
		add("loot_table_design_notes_flintlock", ModLootModifierSerializers.LOOT_TABLE.get(), new LootTableLootModifier(
				new ILootCondition[]{
						LootSpawnDesignNotesLootCondition.builder().build(),
						RandomChance.randomChance(0.2f).build(),
						LootTableIdCondition.builder(LootTables.SIMPLE_DUNGEON)
						.or(LootTableIdCondition.builder(LootTables.SHIPWRECK_SUPPLY))
						.or(LootTableIdCondition.builder(LootTables.SHIPWRECK_TREASURE))
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
