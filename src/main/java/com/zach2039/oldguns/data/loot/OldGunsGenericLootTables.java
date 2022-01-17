package com.zach2039.oldguns.data.loot;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.zach2039.oldguns.api.crafting.IDesignNotes;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.init.ModLootTables;

import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.functions.SetNbtFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class OldGunsGenericLootTables implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {
	
	@SuppressWarnings("deprecation")
	private Builder<?> createDesignNotesForItem(int weight, Item item) {
		CompoundTag tag = new CompoundTag();
		IDesignNotes.setDesignOnTag(tag, item);
		
		return (Builder<?>) LootItem.lootTableItem(ModItems.DESIGN_NOTES.get())
				.setWeight(weight)
				.apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 1)))
				.apply(SetNbtFunction.setTag(tag));
	}
	
	private Builder<?> createItem(int weight, Item item) {
		return (Builder<?>) LootItem.lootTableItem(item)
				.setWeight(weight)
				.apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 1)));
	}
	
	private Builder<?> createDamagedItem(int weight, Item item) {
		return (Builder<?>) LootItem.lootTableItem(item)
				.setWeight(weight)
				.apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 1)))
				.apply(SetItemDamageFunction.setDamage(UniformGenerator.between(0.15f, 0.70f)));
	}
	
	@Override
	public void accept(final BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
		consumer.accept(
				ModLootTables.LOOT_TABLE_MECHANISM,
				LootTable.lootTable()
				.withPool(
						LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						.add(
								createItem(75, ModItems.FLINTLOCK_MECHANISM.get())
							)
						.add(
								createItem(25, ModItems.CAPLOCK_MECHANISM.get())
							)
						)
				);
		consumer.accept(
				ModLootTables.LOOT_TABLE_MATCHLOCK_FIREARM,
				LootTable.lootTable()
				.withPool(
						LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						.add(
								createDamagedItem(25, ModItems.MATCHLOCK_DERRINGER.get())
							)
						.add(
								createDamagedItem(25, ModItems.MATCHLOCK_PISTOL.get())
							)
						.add(
								createDamagedItem(25, ModItems.MATCHLOCK_ARQUEBUS.get())
							)
						.add(
								createDamagedItem(25, ModItems.MATCHLOCK_CALIVER.get())
							)
						.add(
								createDamagedItem(25, ModItems.MATCHLOCK_MUSKET.get())
							)
						.add(
								createDamagedItem(25, ModItems.MATCHLOCK_LONG_MUSKET.get())
							)
						.add(
								createDamagedItem(25, ModItems.MATCHLOCK_BLUNDERBUSS.get())
							)
						)
				);
		consumer.accept(
				ModLootTables.LOOT_TABLE_DESIGN_NOTES_MECHANISM,
				LootTable.lootTable()
				.withPool(
						LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						.add(
								createDesignNotesForItem(75, ModItems.FLINTLOCK_MECHANISM.get())
							)
						.add(
								createDesignNotesForItem(25, ModItems.CAPLOCK_MECHANISM.get())
							)
						)
				);
		consumer.accept(
				ModLootTables.LOOT_TABLE_DESIGN_NOTES_MATCHLOCK,
				LootTable.lootTable()
				.withPool(
						LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						.add(
								createDesignNotesForItem(25, ModItems.MATCHLOCK_MUSKETOON.get())
							)
						.add(
								createDesignNotesForItem(25, ModItems.MATCHLOCK_BLUNDERBUSS_PISTOL.get())
							)
						)
				);
		consumer.accept(
				ModLootTables.LOOT_TABLE_DESIGN_NOTES_WHEELLOCK,
				LootTable.lootTable()
				.withPool(
						LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						.add(
								createDesignNotesForItem(25, ModItems.WHEELLOCK_DOUBLEBARREL_PISTOL.get())
							)
						.add(
								createDesignNotesForItem(25, ModItems.WHEELLOCK_MUSKETOON.get())
							)
						.add(
								createDesignNotesForItem(25, ModItems.WHEELLOCK_BLUNDERBUSS_PISTOL.get())
							)
						)
				);
		consumer.accept(
				ModLootTables.LOOT_TABLE_DESIGN_NOTES_FLINTLOCK,
				LootTable.lootTable()
				.withPool(
						LootPool.lootPool()
						.setRolls(ConstantValue.exactly(1))
						.add(
								createDesignNotesForItem(25, ModItems.FLINTLOCK_DUCKFOOT_DERRINGER.get())
							)
						.add(
								createDesignNotesForItem(25, ModItems.FLINTLOCK_BLUNDERBUSS_PISTOL.get())
							)
						.add(
								createDesignNotesForItem(25, ModItems.FLINTLOCK_PEPPERBOX_PISTOL.get())
							)
						.add(
								createDesignNotesForItem(25, ModItems.FLINTLOCK_MUSKETOON.get())
							)
						.add(
								createDesignNotesForItem(25, ModItems.FLINTLOCK_NOCK_GUN.get())
							)
						)
				);
	}
}
