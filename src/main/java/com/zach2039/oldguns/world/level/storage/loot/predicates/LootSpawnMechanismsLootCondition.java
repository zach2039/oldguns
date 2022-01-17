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

public class LootSpawnMechanismsLootCondition implements LootItemCondition {
	private static final LootSpawnMechanismsLootCondition INSTANCE = new LootSpawnMechanismsLootCondition();
	
	@Override
	public LootItemConditionType getType() {
		return ModLootConditionTypes.ALLOW_MECHANISMS_LOOT;
	}
	
	@Override
	public boolean test(LootContext p_81930_) {
		return OldGunsConfig.SERVER.lootSettings.allowMechanismsInLoot.get();
	}

	public static LootItemCondition.Builder builder() {
		return () -> INSTANCE;
	}

	public static class ConditionSerializer implements Serializer<LootSpawnMechanismsLootCondition> {
		@Override
		public void serialize(final JsonObject object, final LootSpawnMechanismsLootCondition instance, final JsonSerializationContext context) {}

		@Override	
		public LootSpawnMechanismsLootCondition deserialize(final JsonObject object, final JsonDeserializationContext context) {
			return LootSpawnMechanismsLootCondition.INSTANCE;
		}
	}
}
