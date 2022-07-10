package com.zach2039.oldguns.world.level.storage.loot.modifiers;

import java.util.function.Supplier;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;

public class DesignNotesMatchlockLootModifier extends LootTableLootModifier {
	public DesignNotesMatchlockLootModifier(LootItemCondition[] conditions, ResourceLocation lootTableID) {
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
					.apply(inst, DesignNotesMatchlockLootModifier::new)
	)
);

	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return CODEC.get();
	}
}
