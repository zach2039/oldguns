package com.zach2039.oldguns.data;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.init.ModLootTables;
import com.zach2039.oldguns.world.level.storage.loot.modifiers.*;
import com.zach2039.oldguns.world.level.storage.loot.predicates.LootSpawnDesignNotesLootCondition;
import com.zach2039.oldguns.world.level.storage.loot.predicates.LootSpawnFirearmsLootCondition;
import com.zach2039.oldguns.world.level.storage.loot.predicates.LootSpawnMechanismsLootCondition;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.lang.reflect.Field;


/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author zach2039
 */
public class OldGunsLootModifierProvider extends GlobalLootModifierProvider {
	private static final Field TO_SERIALIZE = ObfuscationReflectionHelper.findField(GlobalLootModifierProvider.class, /* toSerialize */ "toSerialize");

	public OldGunsLootModifierProvider(final PackOutput output) {
		super(output, OldGuns.MODID);
	}

	@Override
	protected void start() {
		
		add("modifier_matchlock", new MatchlockLootModifier(
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
		
		
		add("modifier_mechanism", new MechanismLootModifier(
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
		
		
		add("modifier_design_notes_mechanism", new DesignNotesMechanismLootModifier(
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
		
		
		add("modifier_design_notes_matchlock", new DesignNotesMatchlockLootModifier(
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
		
		
		add("modifier_design_notes_wheellock", new DesignNotesWheellockLootModifier(
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
		
		
		add("modifier_design_notes_flintlock", new DesignNotesFlintlockLootModifier(
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
