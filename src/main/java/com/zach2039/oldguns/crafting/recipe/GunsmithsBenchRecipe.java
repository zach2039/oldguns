package com.zach2039.oldguns.crafting.recipe;

import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.init.ModRecipeTypes;
import com.zach2039.oldguns.world.inventory.GunsmithsBenchCraftingContainer;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeHooks;

public interface GunsmithsBenchRecipe extends IRecipe<GunsmithsBenchCraftingContainer> {
	default IRecipeType<?> getType() {
		return ModRecipeTypes.GUNSMITHS_BENCH;
	}
	
	default boolean requiresDesignNotes(Item item) {
		return OldGunsConfig.SERVER.recipeSettings.designNotesSettings.designNotesRequiredItems.get().contains(item.getRegistryName().toString());
	}
	
	default NonNullList<ItemStack> getRemainingItems(final GunsmithsBenchCraftingContainer inv) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);

		for (int i = 0; i < remainingItems.size(); ++i) {
			final ItemStack itemstack = inv.getItem(i);

			remainingItems.set(i, ForgeHooks.getContainerItem(itemstack));
		}

		return remainingItems;
	}
}