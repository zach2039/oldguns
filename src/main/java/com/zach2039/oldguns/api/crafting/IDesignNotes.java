package com.zach2039.oldguns.api.crafting;

import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.init.ModItems;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public interface IDesignNotes {
	static String getDesign(ItemStack stack) {
		if (stack.getOrCreateTag().contains("item")) {
			return stack.getOrCreateTag().getString("item");
		}
		return "";
	}
	
	static ItemStack setDesignTagOnItem(ItemStack notes, Item item) {
		notes.getOrCreateTag().putString("item", ForgeRegistries.ITEMS.getKey(item).toString());
		return notes.copy();
	}
	
	static CompoundTag setDesignOnTag(CompoundTag tag, Item item) {
		tag.putString("item", ForgeRegistries.ITEMS.getKey(item).toString());
		return tag;
	}
	
	static ItemStack setDesignTagOnItem(ItemStack stack, String itemName) {
		stack.getOrCreateTag().putString("item", itemName);
		return stack.copy();
	}	
	
	static boolean hasDesignNotes(Item item) {
		return OldGunsConfig.SERVER.recipeSettings.designNotesSettings.designNotesRequiredItems.get().contains(ForgeRegistries.ITEMS.getKey(item.asItem()).toString());
	}
	
	static ItemStack getDesignNotesForItem(Item item) {
		ItemStack notes = new ItemStack(ModItems.DESIGN_NOTES.get());
		setDesignTagOnItem(notes, item);
		return notes;
	}
}
