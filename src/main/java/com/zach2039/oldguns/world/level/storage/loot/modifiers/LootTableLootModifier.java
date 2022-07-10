package com.zach2039.oldguns.world.level.storage.loot.modifiers;

import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

/**
 * A global loot modifier that generates loot from another loot table.
 *
 * @author Choonster
 */
public class LootTableLootModifier extends LootModifier {
	private final ResourceLocation lootTableID;
	public static final Supplier<Codec<LootTableLootModifier>> CODEC = Suppliers.memoize(() ->
		RecordCodecBuilder.create(inst ->
				codecStart(inst)
						.and(
								ResourceLocation.CODEC
										.fieldOf("loot_table")
										.forGetter(m -> m.lootTableID)
						)
						.apply(inst, LootTableLootModifier::new)
		)
	);
	
	public LootTableLootModifier(final LootItemCondition[] conditions, final ResourceLocation lootTableID) {
		super(conditions);
		this.lootTableID = lootTableID;
	}

	@Override
	protected @NotNull ObjectArrayList<ItemStack> doApply(final ObjectArrayList<ItemStack> generatedLoot, final LootContext context) {
		final LootTable lootTable = context.getLootTable(lootTableID);

		// Generate additional loot without applying loot modifiers, otherwise each modifier would run multiple times
		// for the same loot generation.
		lootTable.getRandomItemsRaw(context, generatedLoot::add);

		return generatedLoot;
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return CODEC.get();
	}
}

