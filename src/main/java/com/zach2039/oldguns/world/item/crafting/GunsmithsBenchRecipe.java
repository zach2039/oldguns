package com.zach2039.oldguns.world.item.crafting;

import com.zach2039.oldguns.init.ModRecipeTypes;
import com.zach2039.oldguns.world.inventory.GunsmithsBenchCraftingContainer;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public interface GunsmithsBenchRecipe extends Recipe<GunsmithsBenchCraftingContainer> {
	default RecipeType<?> getType() {
		return ModRecipeTypes.GUNSMITHS_BENCH;
	}
}