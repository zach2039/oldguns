package com.zach2039.oldguns.world.level.storage.loot.predicates;

import org.apache.logging.log4j.core.layout.AbstractStringLayout.Serializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.init.ModLootConditionTypes;

import net.minecraft.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class LootSpawnFirearmsLootCondition implements LootItemCondition {
	private static final LootSpawnFirearmsLootCondition INSTANCE = new LootSpawnFirearmsLootCondition();
	
	@Override
	public LootItemConditionType getType() {
		return ModLootConditionTypes.ALLOW_FIREARMS_LOOT;
	}
	
	@Override
	public boolean test(LootContext p_81930_) {
		return OldGunsConfig.SERVER.lootSettings.allowFirearmsInLoot.get();
	}

	public static LootItemCondition.Builder builder() {
		return () -> INSTANCE;
	}

	public static class ConditionSerializer implements Serializer<LootSpawnFirearmsLootCondition> {
		@Override
		public void serialize(final JsonObject object, final LootSpawnFirearmsLootCondition instance, final JsonSerializationContext context) {}

		@Override	
		public LootSpawnFirearmsLootCondition deserialize(final JsonObject object, final JsonDeserializationContext context) {
			return LootSpawnFirearmsLootCondition.INSTANCE;
		}
	}
}
