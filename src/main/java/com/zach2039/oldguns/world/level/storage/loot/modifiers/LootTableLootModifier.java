package com.zach2039.oldguns.world.level.storage.loot.modifiers;

import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.zach2039.oldguns.OldGuns;

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
public abstract class LootTableLootModifier extends LootModifier {
	protected final ResourceLocation lootTableID;
	
	public LootTableLootModifier(final LootItemCondition[] conditions, final ResourceLocation lootTableID) {
		super(conditions);
		this.lootTableID = lootTableID;
	}

	@Override
	protected @NotNull ObjectArrayList<ItemStack> doApply(final ObjectArrayList<ItemStack> generatedLoot, final LootContext context) {
		final var lootTable = context.getLootTable(lootTableID);

		// Generate additional loot without applying loot modifiers, otherwise each modifier would run multiple times
		// for the same loot generation.
		lootTable.getRandomItemsRaw(context, generatedLoot::add);

		return generatedLoot;
	}

	@Override
	public abstract Codec<? extends IGlobalLootModifier> codec();
}

