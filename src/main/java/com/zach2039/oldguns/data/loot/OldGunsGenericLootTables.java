package com.zach2039.oldguns.data.loot;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.zach2039.oldguns.api.crafting.IDesignNotes;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.init.ModLootTables;

import net.minecraft.item.Item;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.loot.functions.SetDamage;
import net.minecraft.loot.functions.SetNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

public class OldGunsGenericLootTables implements Consumer<BiConsumer<ResourceLocation, LootTable.Builder>> {

	private LootEntry.Builder<?> createDesignNotesForItem(int weight, Item item) {
		CompoundNBT tag = new CompoundNBT();
		IDesignNotes.setDesignOnTag(tag, item);
		
		return (LootEntry.Builder<?>) ItemLootEntry.lootTableItem(ModItems.DESIGN_NOTES.get())
				.setWeight(weight)
				.apply(SetCount.setCount(ConstantRange.exactly(1)))
				.apply(SetNBT.setTag(tag));
	}
	
	private LootEntry.Builder<?> createItem(int weight, Item item) {
		return (LootEntry.Builder<?>) ItemLootEntry.lootTableItem(item)
				.setWeight(weight)
				.apply(SetCount.setCount(ConstantRange.exactly(1)));
	}
	
	private LootEntry.Builder<?> createDamagedItem(int weight, Item item) {
		return (LootEntry.Builder<?>) ItemLootEntry.lootTableItem(item)
				.setWeight(weight)
				.apply(SetCount.setCount(ConstantRange.exactly(1)))
				.apply(SetDamage.setDamage(RandomValueRange.between(0.15f, 0.70f)));
	}
	
	@Override
	public void accept(final BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
		consumer.accept(
				ModLootTables.LOOT_TABLE_MECHANISM,
				LootTable.lootTable()
				.withPool(
						LootPool.lootPool()
						.apply(SetCount.setCount(ConstantRange.exactly(1)))
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
						.apply(SetCount.setCount(ConstantRange.exactly(1)))
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
						.apply(SetCount.setCount(ConstantRange.exactly(1)))
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
						.apply(SetCount.setCount(ConstantRange.exactly(1)))
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
						.apply(SetCount.setCount(ConstantRange.exactly(1)))
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
						.apply(SetCount.setCount(ConstantRange.exactly(1)))
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
