package com.zach2039.oldguns.world.level.storage.loot.predicates;

import com.mojang.serialization.Codec;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.init.ModLootConditionTypes;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class LootSpawnMechanismsLootCondition implements LootItemCondition {
	private static final LootSpawnMechanismsLootCondition INSTANCE = new LootSpawnMechanismsLootCondition();

	public static final Codec<LootSpawnMechanismsLootCondition> CODEC = Codec.unit(INSTANCE);

	@Override
	public LootItemConditionType getType() {
		return ModLootConditionTypes.ALLOW_MECHANISMS_LOOT.get();
	}
	
	@Override
	public boolean test(LootContext p_81930_) {
		return OldGunsConfig.SERVER.lootSettings.allowMechanismsInLoot.get();
	}

	public static LootItemCondition.Builder builder() {
		return () -> INSTANCE;
	}
}
