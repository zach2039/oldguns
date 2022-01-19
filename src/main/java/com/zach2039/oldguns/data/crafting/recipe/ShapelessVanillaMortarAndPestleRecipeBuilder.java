package com.zach2039.oldguns.data.crafting.recipe;

import com.zach2039.oldguns.crafting.recipe.ShapelessVanillaMortarAndPestleRecipe;
import com.zach2039.oldguns.init.ModCrafting;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;

public class ShapelessVanillaMortarAndPestleRecipeBuilder extends EnhancedShapelessRecipeBuilder<ShapelessVanillaMortarAndPestleRecipe, ShapelessVanillaMortarAndPestleRecipeBuilder> {
	protected ShapelessVanillaMortarAndPestleRecipeBuilder(final ItemStack result) {
		super(result, ModCrafting.Recipes.MORTAR_AND_PESTLE_SHAPELESS.get());
	}

	public static ShapelessVanillaMortarAndPestleRecipeBuilder shapeless(final IItemProvider result) {
		return shapeless(new ItemStack(result));
	}

	public static ShapelessVanillaMortarAndPestleRecipeBuilder shapeless(final ItemStack result) {
		return new ShapelessVanillaMortarAndPestleRecipeBuilder(result);
	}
	
	public static ShapelessVanillaMortarAndPestleRecipeBuilder shapeless(final IItemProvider result, final int count) {
		return shapeless(new ItemStack(result, count));
	}
}
