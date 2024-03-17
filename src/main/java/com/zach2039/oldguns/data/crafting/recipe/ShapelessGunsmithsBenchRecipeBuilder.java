package com.zach2039.oldguns.data.crafting.recipe;

import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessGunsmithsBenchRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class ShapelessGunsmithsBenchRecipeBuilder extends EnhancedShapelessRecipeBuilder<ShapelessGunsmithsBenchRecipe, ShapelessGunsmithsBenchRecipeBuilder> {
	protected ShapelessGunsmithsBenchRecipeBuilder(final ItemStack result) {
		super(result, ModCrafting.Recipes.GUNSMITHS_BENCH_SHAPELESS.get());
	}

	public static ShapelessGunsmithsBenchRecipeBuilder shapeless(final ItemLike result) {
		return shapeless(new ItemStack(result));
	}

	public static ShapelessGunsmithsBenchRecipeBuilder shapeless(final ItemStack result) {
		return new ShapelessGunsmithsBenchRecipeBuilder(result);
	}
	
	public static ShapelessGunsmithsBenchRecipeBuilder shapeless(final ItemLike result, final int count) {
		return shapeless(new ItemStack(result, count));
	}
}
