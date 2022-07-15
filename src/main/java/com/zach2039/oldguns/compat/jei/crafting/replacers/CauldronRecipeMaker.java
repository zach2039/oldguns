package com.zach2039.oldguns.compat.jei.crafting.replacers;

import java.util.List;

import com.zach2039.oldguns.init.ModCauldronInteractions;
import com.zach2039.oldguns.world.item.crafting.cauldron.CauldronRecipe;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapelessRecipe;

public class CauldronRecipeMaker {
	private static final String group = "oldguns.cauldron";

	public static List<CraftingRecipe> createRecipes() {

		return ModCauldronInteractions.OldGunsCauldronInteraction.RECIPES.stream()
			.<CraftingRecipe>map(recipe -> createRecipe((CauldronRecipe) recipe))
			.toList();
	}

	private static CraftingRecipe createRecipe(CauldronRecipe recipe) {
		return new ShapelessRecipe(recipe.getId(), group, recipe.getOutput(), NonNullList.of(Ingredient.of(recipe.getInput())));
	}
}
