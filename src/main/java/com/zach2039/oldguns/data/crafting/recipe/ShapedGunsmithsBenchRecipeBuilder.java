package com.zach2039.oldguns.data.crafting.recipe;

import com.zach2039.oldguns.crafting.recipe.ShapedGunsmithsBenchRecipe;
import com.zach2039.oldguns.init.ModCrafting;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;

public class ShapedGunsmithsBenchRecipeBuilder extends EnhancedShapedRecipeBuilder<ShapedGunsmithsBenchRecipe, ShapedGunsmithsBenchRecipeBuilder> {
	protected ShapedGunsmithsBenchRecipeBuilder(final ItemStack result) {
		super(result, ModCrafting.Recipes.GUNSMITHS_BENCH_SHAPED.get());
	}

	public static ShapedGunsmithsBenchRecipeBuilder shaped(final IItemProvider result) {
		return shaped(new ItemStack(result));
	}

	public static ShapedGunsmithsBenchRecipeBuilder shaped(final ItemStack result) {
		return new ShapedGunsmithsBenchRecipeBuilder(result);
	}
	
	public static ShapedGunsmithsBenchRecipeBuilder shaped(final IItemProvider result, final int count) {
		return shaped(new ItemStack(result, count));
	}
}
