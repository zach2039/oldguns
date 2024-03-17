package com.zach2039.oldguns.data.crafting.recipe;

import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessGunsmithsBenchFirearmRepairWithPartsRecipe;
import net.minecraft.world.item.ItemStack;

public class ShapelessGunsmithsBenchFirearmRepairWithPartsRecipeBuilder extends EnhancedShapelessRecipeBuilder<ShapelessGunsmithsBenchFirearmRepairWithPartsRecipe, ShapelessGunsmithsBenchFirearmRepairWithPartsRecipeBuilder> {
	protected ShapelessGunsmithsBenchFirearmRepairWithPartsRecipeBuilder(final ItemStack result) {
		super(result, ModCrafting.Recipes.GUNSMITHS_BENCH_FIREARM_REPAIR_WITH_PARTS_SHAPELESS.get());
	}

	public static ShapelessGunsmithsBenchFirearmRepairWithPartsRecipeBuilder shapeless() {
		return new ShapelessGunsmithsBenchFirearmRepairWithPartsRecipeBuilder(ItemStack.EMPTY).hasDummyOutput();
	}
}
