package com.zach2039.oldguns.data.crafting.recipe;

import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessGunsmithsBenchFirearmRepairRecipe;

import net.minecraft.item.ItemStack;

public class ShapelessGunsmithsBenchFirearmRepairRecipeBuilder extends EnhancedShapelessRecipeBuilder<ShapelessGunsmithsBenchFirearmRepairRecipe, ShapelessGunsmithsBenchFirearmRepairRecipeBuilder> {
	protected ShapelessGunsmithsBenchFirearmRepairRecipeBuilder(final ItemStack result) {
		super(result, ModCrafting.Recipes.GUNSMITHS_BENCH_FIREARM_REPAIR_SHAPELESS.get());
	}

	public static ShapelessGunsmithsBenchFirearmRepairRecipeBuilder shapeless() {
		return new ShapelessGunsmithsBenchFirearmRepairRecipeBuilder(ItemStack.EMPTY).hasDummyOutput();
	}
}
