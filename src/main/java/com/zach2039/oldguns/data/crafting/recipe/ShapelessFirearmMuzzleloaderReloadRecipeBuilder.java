package com.zach2039.oldguns.data.crafting.recipe;

import com.zach2039.oldguns.crafting.recipe.ShapelessVanillaMuzzleloaderReloadRecipe;
import com.zach2039.oldguns.init.ModCrafting;

import net.minecraft.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class ShapelessFirearmMuzzleloaderReloadRecipeBuilder extends EnhancedShapelessRecipeBuilder<ShapelessVanillaMuzzleloaderReloadRecipe, ShapelessFirearmMuzzleloaderReloadRecipeBuilder> {
	protected ShapelessFirearmMuzzleloaderReloadRecipeBuilder(final ItemStack result) {
		super(result, ModCrafting.Recipes.FIREARM_MUZZLELOADER_RELOAD_SHAPELESS.get());
	}

	public static ShapelessFirearmMuzzleloaderReloadRecipeBuilder shapeless(final ItemLike result) {
		return shapeless(new ItemStack(result));
	}

	public static ShapelessFirearmMuzzleloaderReloadRecipeBuilder shapeless(final ItemStack result) {
		return new ShapelessFirearmMuzzleloaderReloadRecipeBuilder(result);
	}
}
