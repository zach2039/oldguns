package com.zach2039.oldguns.data.crafting.recipe;

import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessVanillaMuzzleloaderPowderHornReloadRecipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class ShapelessFirearmMuzzleloaderPowderHornReloadRecipeBuilder extends EnhancedShapelessRecipeBuilder<ShapelessVanillaMuzzleloaderPowderHornReloadRecipe, ShapelessFirearmMuzzleloaderPowderHornReloadRecipeBuilder> {
	protected ShapelessFirearmMuzzleloaderPowderHornReloadRecipeBuilder(final ItemStack result) {
		super(result, ModCrafting.Recipes.FIREARM_MUZZLELOADER_POWDER_HORN_RELOAD_SHAPELESS.get());
	}

	public static ShapelessFirearmMuzzleloaderPowderHornReloadRecipeBuilder shapeless(final ItemLike result) {
		return shapeless(new ItemStack(result));
	}

	public static ShapelessFirearmMuzzleloaderPowderHornReloadRecipeBuilder shapeless(final ItemStack result) {
		return new ShapelessFirearmMuzzleloaderPowderHornReloadRecipeBuilder(result);
	}
}
