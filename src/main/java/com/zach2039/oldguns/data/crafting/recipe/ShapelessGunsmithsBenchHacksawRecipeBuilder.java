package com.zach2039.oldguns.data.crafting.recipe;

import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessGunsmithsBenchHacksawRecipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class ShapelessGunsmithsBenchHacksawRecipeBuilder extends EnhancedShapelessRecipeBuilder<ShapelessGunsmithsBenchHacksawRecipe, ShapelessGunsmithsBenchHacksawRecipeBuilder> {
	protected ShapelessGunsmithsBenchHacksawRecipeBuilder(final ItemStack result) {
		super(result, ModCrafting.Recipes.GUNSMITHS_BENCH_HACKSAW_SHAPELESS.get());
	}

	public static ShapelessGunsmithsBenchHacksawRecipeBuilder shapeless(final ItemLike result) {
		return shapeless(new ItemStack(result));
	}

	public static ShapelessGunsmithsBenchHacksawRecipeBuilder shapeless(final ItemStack result) {
		return new ShapelessGunsmithsBenchHacksawRecipeBuilder(result);
	}
	
	public static ShapelessGunsmithsBenchHacksawRecipeBuilder shapeless(final ItemLike result, final int count) {
		return shapeless(new ItemStack(result, count));
	}
}
