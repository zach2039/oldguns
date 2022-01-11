package com.zach2039.oldguns.data.crafting.recipe;

import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessFirearmRepairRecipe;

import net.minecraft.world.item.ItemStack;

public class ShapelessFirearmRepairRecipeBuilder extends EnhancedShapelessRecipeBuilder<ShapelessFirearmRepairRecipe, ShapelessFirearmRepairRecipeBuilder> {
	protected ShapelessFirearmRepairRecipeBuilder(final ItemStack result) {
		super(result, ModCrafting.Recipes.FIREARM_REPAIR_SHAPELESS.get());
	}

	public static ShapelessFirearmRepairRecipeBuilder shapelessFirearmRepairRecipe() {
		return new ShapelessFirearmRepairRecipeBuilder(ItemStack.EMPTY).hasDummyOutput();
	}
}
