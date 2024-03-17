package com.zach2039.oldguns.world.level.storage.loot.predicates;

import com.mojang.serialization.Codec;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.init.ModLootConditionTypes;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class LootSpawnDesignNotesLootCondition implements LootItemCondition {
	private static final LootSpawnDesignNotesLootCondition INSTANCE = new LootSpawnDesignNotesLootCondition();

	public static final Codec<LootSpawnDesignNotesLootCondition> CODEC = Codec.unit(INSTANCE);
	
	@Override
	public LootItemConditionType getType() {
		return ModLootConditionTypes.ALLOW_DESIGN_NOTES_LOOT.get();
	}
	
	@Override
	public boolean test(LootContext lootContext) {
		return OldGunsConfig.SERVER.lootSettings.allowDesignNotesInLoot.get();
	}

	public static LootItemCondition.Builder builder() {
		return () -> INSTANCE;
	}
}
