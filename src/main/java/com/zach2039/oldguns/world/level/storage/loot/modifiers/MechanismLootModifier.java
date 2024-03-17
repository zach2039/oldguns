package com.zach2039.oldguns.world.level.storage.loot.modifiers;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;

import java.util.function.Supplier;

public class MechanismLootModifier extends LootTableLootModifier {
	public MechanismLootModifier(LootItemCondition[] conditions, ResourceLocation lootTableID) {
		super(conditions, lootTableID);
	}

	public static final Supplier<Codec<LootTableLootModifier>> CODEC = Suppliers.memoize(() ->
	RecordCodecBuilder.create(inst ->
			codecStart(inst)
				
					.and(
							ResourceLocation.CODEC
									.fieldOf("loot_table")
									.forGetter(m -> m.lootTableID)
					)
					.apply(inst, MechanismLootModifier::new)
	)
);

	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return CODEC.get();
	}
}
