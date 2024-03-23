package com.zach2039.oldguns.data.crafting.recipe;

import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessGunsmithsBenchRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;

/**
 * Represents a factory or constructor for {@link ShapelessRecipe} classes.
 *
 * @author Choonster
 */
public interface ShapelessGunsmithsBenchRecipeFactory<T extends ShapelessGunsmithsBenchRecipe> {
	T createRecipe(
			String group,
			CraftingBookCategory category,
			ItemStack result,
			NonNullList<Ingredient> ingredients
	);
}
