package com.zach2039.oldguns.compat.jei;

import com.zach2039.oldguns.init.ModRecipeTypes;
import com.zach2039.oldguns.world.item.crafting.GunsmithsBenchRecipe;

import mezz.jei.api.recipe.RecipeType;
import net.minecraft.world.item.crafting.Recipe;

public class JEIRecipeTypes {
	public static final RecipeType<GunsmithsBenchRecipe> GUNSMITHS_BENCH = create(ModRecipeTypes.GUNSMITHS_BENCH);
	
	private static <T extends Recipe<?>> RecipeType<T> create(ModRecipeTypes.TypeWithClass<T> type)
	{
		return new RecipeType<>(type.type().getId(), type.recipeClass());
	}
}
