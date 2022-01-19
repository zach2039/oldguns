package com.zach2039.oldguns.data.crafting.recipe;

import com.zach2039.oldguns.crafting.recipe.ShapelessGunsmithsBenchRecipe;
import com.zach2039.oldguns.init.ModCrafting;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;

public class ShapelessGunsmithsBenchRecipeBuilder extends EnhancedShapelessRecipeBuilder<ShapelessGunsmithsBenchRecipe, ShapelessGunsmithsBenchRecipeBuilder> {
	protected ShapelessGunsmithsBenchRecipeBuilder(final ItemStack result) {
		super(result, ModCrafting.Recipes.GUNSMITHS_BENCH_SHAPELESS.get());
	}

	public static ShapelessGunsmithsBenchRecipeBuilder shapeless(final IItemProvider result) {
		return shapeless(new ItemStack(result));
	}

	public static ShapelessGunsmithsBenchRecipeBuilder shapeless(final ItemStack result) {
		return new ShapelessGunsmithsBenchRecipeBuilder(result);
	}
	
	public static ShapelessGunsmithsBenchRecipeBuilder shapeless(final IItemProvider result, final int count) {
		return shapeless(new ItemStack(result, count));
	}
}
