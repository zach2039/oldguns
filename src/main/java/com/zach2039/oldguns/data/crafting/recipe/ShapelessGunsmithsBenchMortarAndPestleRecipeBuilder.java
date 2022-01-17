package com.zach2039.oldguns.data.crafting.recipe;

import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessGunsmithsBenchMortarAndPestleRecipe;

import net.minecraft.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class ShapelessGunsmithsBenchMortarAndPestleRecipeBuilder extends EnhancedShapelessRecipeBuilder<ShapelessGunsmithsBenchMortarAndPestleRecipe, ShapelessGunsmithsBenchMortarAndPestleRecipeBuilder> {
	protected ShapelessGunsmithsBenchMortarAndPestleRecipeBuilder(final ItemStack result) {
		super(result, ModCrafting.Recipes.GUNSMITHS_BENCH_MORTAR_AND_PESTLE_SHAPELESS.get());
	}

	public static ShapelessGunsmithsBenchMortarAndPestleRecipeBuilder shapeless(final ItemLike result) {
		return shapeless(new ItemStack(result));
	}

	public static ShapelessGunsmithsBenchMortarAndPestleRecipeBuilder shapeless(final ItemStack result) {
		return new ShapelessGunsmithsBenchMortarAndPestleRecipeBuilder(result);
	}
	
	public static ShapelessGunsmithsBenchMortarAndPestleRecipeBuilder shapeless(final ItemLike result, final int count) {
		return shapeless(new ItemStack(result, count));
	}
}
