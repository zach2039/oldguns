package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.level.storage.loot.predicates.LootSpawnAmmoLootCondition;
import com.zach2039.oldguns.world.level.storage.loot.predicates.LootSpawnExoticsLootCondition;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.Serializer;
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
	public static final LootItemConditionType SPAWN_EXOTICS_LOOT = register("can_spawn_exotics_in_loot", new LootSpawnExoticsLootCondition.ConditionSerializer());
	public static final LootItemConditionType SPAWN_AMMO_LOOT = register("can_spawn_ammo_in_loot", new LootSpawnAmmoLootCondition.ConditionSerializer());

	public static void register() {
		// No-op method to ensure that this class is loaded and its static initialisers are run
	}

	private static LootItemConditionType register(final String name, final Serializer<? extends LootItemCondition> serializer) {
		return Registry.register(Registry.LOOT_CONDITION_TYPE, new ResourceLocation(OldGuns.MODID, name), new LootItemConditionType(serializer));
	}
}
