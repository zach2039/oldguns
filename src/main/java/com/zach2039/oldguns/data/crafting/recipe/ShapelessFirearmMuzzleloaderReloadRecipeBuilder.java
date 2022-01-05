package com.zach2039.oldguns.data.crafting.recipe;

import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessFirearmMuzzleloaderReloadRecipe;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class ShapelessFirearmMuzzleloaderReloadRecipeBuilder extends EnhancedShapelessRecipeBuilder<ShapelessFirearmMuzzleloaderReloadRecipe, ShapelessFirearmMuzzleloaderReloadRecipeBuilder> {
	protected ShapelessFirearmMuzzleloaderReloadRecipeBuilder(final ItemStack result) {
		super(result, ModCrafting.Recipes.FIREARM_MUZZLELOADER_RELOAD_SHAPELESS.get());
	}

	public static ShapelessFirearmMuzzleloaderReloadRecipeBuilder shapelessFirearmMuzzleloaderReloadRecipe(final ItemLike result) {
		return shapelessFirearmMuzzleloaderReloadRecipe(new ItemStack(result));
	}

	public static ShapelessFirearmMuzzleloaderReloadRecipeBuilder shapelessFirearmMuzzleloaderReloadRecipe(final ItemStack result) {
		return new ShapelessFirearmMuzzleloaderReloadRecipeBuilder(result);
	}
}
