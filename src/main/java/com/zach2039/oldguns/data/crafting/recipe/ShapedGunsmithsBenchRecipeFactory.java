package com.zach2039.oldguns.data.crafting.recipe;

import com.zach2039.oldguns.world.item.crafting.recipe.ShapedGunsmithsBenchRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapedRecipePattern;

/**
 * Represents a factory or constructor for {@link ShapedRecipe} classes.
 *
 * @author Choonster
 */
public interface ShapedGunsmithsBenchRecipeFactory<T extends ShapedGunsmithsBenchRecipe> {
	T createRecipe(
			String group,
			CraftingBookCategory category,
			ShapedRecipePattern pattern,
			ItemStack result,
			boolean showNotification
	);
}