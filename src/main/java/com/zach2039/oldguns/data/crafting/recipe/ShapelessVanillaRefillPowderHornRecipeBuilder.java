package com.zach2039.oldguns.data.crafting.recipe;

import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessVanillaRefillPowderHornRecipe;
import net.minecraft.world.item.ItemStack;

public class ShapelessVanillaRefillPowderHornRecipeBuilder extends EnhancedShapelessRecipeBuilder<ShapelessVanillaRefillPowderHornRecipe, ShapelessVanillaRefillPowderHornRecipeBuilder> {
	protected ShapelessVanillaRefillPowderHornRecipeBuilder(final ItemStack result) {
		super(result, ModCrafting.Recipes.POWDER_HORN_REFILL_SHAPELESS.get());
	}

	public static ShapelessVanillaRefillPowderHornRecipeBuilder shapeless() {
		return new ShapelessVanillaRefillPowderHornRecipeBuilder(ItemStack.EMPTY).hasDummyOutput();
	}
}
