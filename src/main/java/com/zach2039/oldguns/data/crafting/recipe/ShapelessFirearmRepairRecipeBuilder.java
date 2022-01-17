package com.zach2039.oldguns.data.crafting.recipe;

import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessVanillaFirearmRepairRecipe;

import net.minecraft.item.ItemStack;

public class ShapelessFirearmRepairRecipeBuilder extends EnhancedShapelessRecipeBuilder<ShapelessVanillaFirearmRepairRecipe, ShapelessFirearmRepairRecipeBuilder> {
	protected ShapelessFirearmRepairRecipeBuilder(final ItemStack result) {
		super(result, ModCrafting.Recipes.FIREARM_REPAIR_SHAPELESS.get());
	}

	public static ShapelessFirearmRepairRecipeBuilder shapeless() {
		return new ShapelessFirearmRepairRecipeBuilder(ItemStack.EMPTY).hasDummyOutput();
	}
}
