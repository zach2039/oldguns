package com.zach2039.oldguns.data.crafting.recipe;

import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessVanillaMortarAndPestleRecipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class ShapelessVanillaMortarAndPestleRecipeBuilder extends EnhancedShapelessRecipeBuilder<ShapelessVanillaMortarAndPestleRecipe, ShapelessVanillaMortarAndPestleRecipeBuilder> {
	protected ShapelessVanillaMortarAndPestleRecipeBuilder(final ItemStack result) {
		super(result, ModCrafting.Recipes.MORTAR_AND_PESTLE_SHAPELESS.get());
	}

	public static ShapelessVanillaMortarAndPestleRecipeBuilder shapeless(final ItemLike result) {
		return shapeless(new ItemStack(result));
	}

	public static ShapelessVanillaMortarAndPestleRecipeBuilder shapeless(final ItemStack result) {
		return new ShapelessVanillaMortarAndPestleRecipeBuilder(result);
	}
	
	public static ShapelessVanillaMortarAndPestleRecipeBuilder shapeless(final ItemLike result, final int count) {
		return shapeless(new ItemStack(result, count));
	}
}
