package com.zach2039.oldguns.data.crafting.recipe;

import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessGunsmithsBenchFirearmRepairWithKitRecipe;

import net.minecraft.world.item.ItemStack;

public class ShapelessGunsmithsBenchFirearmRepairWithKitRecipeBuilder extends EnhancedShapelessRecipeBuilder<ShapelessGunsmithsBenchFirearmRepairWithKitRecipe, ShapelessGunsmithsBenchFirearmRepairWithKitRecipeBuilder> {
	protected ShapelessGunsmithsBenchFirearmRepairWithKitRecipeBuilder(final ItemStack result) {
		super(result, ModCrafting.Recipes.GUNSMITHS_BENCH_FIREARM_REPAIR_WITH_KIT_SHAPELESS.get());
	}

	public static ShapelessGunsmithsBenchFirearmRepairWithKitRecipeBuilder shapeless() {
		return new ShapelessGunsmithsBenchFirearmRepairWithKitRecipeBuilder(ItemStack.EMPTY).hasDummyOutput();
	}
}
