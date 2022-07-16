package com.zach2039.oldguns.data.crafting.recipe;

import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessVanillaScrapDesignNotesRecipe;

import net.minecraft.world.item.ItemStack;

public class ShapelessVanillaScrapDesignNotesRecipeBuilder extends EnhancedShapelessRecipeBuilder<ShapelessVanillaScrapDesignNotesRecipe, ShapelessVanillaScrapDesignNotesRecipeBuilder> {
	protected ShapelessVanillaScrapDesignNotesRecipeBuilder(final ItemStack result) {
		super(result, ModCrafting.Recipes.SCRAP_DESIGN_NOTES_SHAPELESS.get());
	}

	public static ShapelessVanillaScrapDesignNotesRecipeBuilder shapeless() {
		return new ShapelessVanillaScrapDesignNotesRecipeBuilder(ItemStack.EMPTY).hasDummyOutput();
	}
}
