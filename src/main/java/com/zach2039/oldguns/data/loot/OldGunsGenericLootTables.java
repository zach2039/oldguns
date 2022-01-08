package com.zach2039.oldguns.data.loot;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.init.ModLootTables;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class OldGunsGenericLootTables implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {
	@Override
	public void accept(final BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
		consumer.accept(
				ModLootTables.LOOT_TABLE_FIREARMS,
				LootTable.lootTable()
				.withPool(
						LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						.add(
								LootItem.lootTableItem(ModItems.FLINTLOCK_PISTOL.get())
								.setWeight(1)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 1)))
								.apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15f, 1.0f)))
							)
						)
				);
		consumer.accept(
				ModLootTables.LOOT_TABLE_AMMO,
				LootTable.lootTable()
				.withPool(
						LootPool.lootPool()
						.setRolls(UniformGenerator.between(2, 4))
						.add(
								LootItem.lootTableItem(ModItems.SMALL_IRON_MUSKET_BALL.get())
								.setWeight(10)
								.apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
							)
						)
				);
	}
}
