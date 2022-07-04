package com.zach2039.oldguns.world.item.crafting;

import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.init.ModRecipeTypes;
import com.zach2039.oldguns.util.ModRegistryUtil;
import com.zach2039.oldguns.world.inventory.GunsmithsBenchCraftingContainer;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.ForgeHooks;

public interface GunsmithsBenchRecipe extends Recipe<GunsmithsBenchCraftingContainer> {
	default RecipeType<?> getType() {
		return ModRecipeTypes.GUNSMITHS_BENCH;
	}
	
	default boolean requiresDesignNotes(Item item) {
		return OldGunsConfig.SERVER.recipeSettings.designNotesSettings.designNotesRequiredItems.get().contains(ModRegistryUtil.getKey(item).toString());
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