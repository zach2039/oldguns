package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.loot.conditions.LootSpawnDesignNotesLootCondition;
import com.zach2039.oldguns.loot.conditions.LootSpawnFirearmsLootCondition;
import com.zach2039.oldguns.loot.conditions.LootSpawnMechanismsLootCondition;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author grilled-salmon
 */
public class ModLootConditionTypes {
	public static final LootItemConditionType ALLOW_DESIGN_NOTES_LOOT = register("can_spawn_design_notes_in_loot", new LootSpawnDesignNotesLootCondition.ConditionSerializer());
	public static final LootItemConditionType ALLOW_MECHANISMS_LOOT = register("can_spawn_mechansims_in_loot", new LootSpawnMechanismsLootCondition.ConditionSerializer());
	public static final LootItemConditionType ALLOW_FIREARMS_LOOT = register("can_spawn_firearms_in_loot", new LootSpawnFirearmsLootCondition.ConditionSerializer());

	public static void register() {
		// No-op method to ensure that this class is loaded and its static initialisers are run
	}

	private static LootItemConditionType register(final String name, final Serializer<? extends LootItemCondition> serializer) {
		return Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(OldGuns.MODID, name), new LootItemConditionType(serializer));
	}
}
