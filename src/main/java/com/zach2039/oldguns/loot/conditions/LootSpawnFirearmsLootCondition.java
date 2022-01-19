package com.zach2039.oldguns.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.init.ModLootConditionTypes;

import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;

public class LootSpawnFirearmsLootCondition implements ILootCondition {
	private static final LootSpawnFirearmsLootCondition INSTANCE = new LootSpawnFirearmsLootCondition();
	
	@Override
	public LootConditionType getType() {
		return ModLootConditionTypes.ALLOW_FIREARMS_LOOT;
	}
	
	@Override
	public boolean test(LootContext p_81930_) {
		return OldGunsConfig.SERVER.lootSettings.allowFirearmsInLoot.get();
	}

	public static ILootCondition.IBuilder builder() {
		return () -> INSTANCE;
	}

	public static class ConditionSerializer implements ILootSerializer<LootSpawnFirearmsLootCondition> {
		@Override
		public void serialize(final JsonObject object, final LootSpawnFirearmsLootCondition instance, final JsonSerializationContext context) {}

		@Override	
		public LootSpawnFirearmsLootCondition deserialize(final JsonObject object, final JsonDeserializationContext context) {
			return LootSpawnFirearmsLootCondition.INSTANCE;
		}
	}
}
