package com.zach2039.oldguns.world.level.storage.loot.predicates;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.init.ModLootConditionTypes;

import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class LootSpawnExoticsLootCondition implements LootItemCondition {
	private static final LootSpawnExoticsLootCondition INSTANCE = new LootSpawnExoticsLootCondition();
	
	@Override
	public LootItemConditionType getType() {
		return ModLootConditionTypes.SPAWN_EXOTICS_LOOT;
	}
	
	@Override
	public boolean test(LootContext p_81930_) {
		return OldGunsConfig.COMMON.lootSettings.allowExoticFirearmsInLoot.get();
	}

	public static LootItemCondition.Builder builder() {
		return () -> INSTANCE;
	}

	public static class ConditionSerializer implements Serializer<LootSpawnExoticsLootCondition> {
		@Override
		public void serialize(final JsonObject object, final LootSpawnExoticsLootCondition instance, final JsonSerializationContext context) {}

		@Override	
		public LootSpawnExoticsLootCondition deserialize(final JsonObject object, final JsonDeserializationContext context) {
			return LootSpawnExoticsLootCondition.INSTANCE;
		}
	}
}
