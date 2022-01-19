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

public class LootSpawnDesignNotesLootCondition implements ILootCondition {
	private static final LootSpawnDesignNotesLootCondition INSTANCE = new LootSpawnDesignNotesLootCondition();
	
	@Override
	public LootConditionType getType() {
		return ModLootConditionTypes.ALLOW_DESIGN_NOTES_LOOT;
	}
	
	@Override
	public boolean test(LootContext p_81930_) {
		return OldGunsConfig.SERVER.lootSettings.allowDesignNotesInLoot.get();
	}

	public static ILootCondition.IBuilder builder() {
		return () -> INSTANCE;
	}

	public static class ConditionSerializer implements ILootSerializer<LootSpawnDesignNotesLootCondition> {
		@Override
		public void serialize(final JsonObject object, final LootSpawnDesignNotesLootCondition instance, final JsonSerializationContext context) {}

		@Override	
		public LootSpawnDesignNotesLootCondition deserialize(final JsonObject object, final JsonDeserializationContext context) {
			return LootSpawnDesignNotesLootCondition.INSTANCE;
		}
	}
}
