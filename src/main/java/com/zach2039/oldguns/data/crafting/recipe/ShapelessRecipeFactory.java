package com.zach2039.oldguns.data.crafting.recipe;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

/**
 * Represents a factory or constructor for {@link ShapelessRecipe} classes.
 *
 * @author Choonster
 */
public interface ShapelessRecipeFactory<T extends Recipe<?>> {
	T createRecipe(
			String group,
			CraftingBookCategory category,
			ItemStack result,
			NonNullList<Ingredient> ingredients
	);
}
