package com.zach2039.oldguns.world.item.crafting.recipe;

import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.item.crafting.DamageableToolRecipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class ShapelessVanillaMortarAndPestleRecipe extends ShapelessRecipe implements DamageableToolRecipe {
	
	public ShapelessVanillaMortarAndPestleRecipe(ResourceLocation p_44246_, String p_44247_, ItemStack p_44248_,
			NonNullList<Ingredient> p_44249_) {
		super(p_44246_, p_44247_, p_44248_, p_44249_);
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModCrafting.Recipes.MORTAR_AND_PESTLE_SHAPELESS.get();
	}
}

