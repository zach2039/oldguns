package com.zach2039.oldguns.world.item.crafting.recipe;

import com.zach2039.oldguns.init.ModItems;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class LiquidNiterRecipe implements IBrewingRecipe {

	@Override
	public boolean isInput(ItemStack input) {
		 return PotionUtils.getPotion(input) == Potions.WATER;
	}

	@Override
	public boolean isIngredient(ItemStack ingredient) {
		 return (ingredient.getItem() == ModItems.NITRATE_SOIL.get());
	}

	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
		if (isInput(input) && isIngredient(ingredient))
            return new ItemStack(ModItems.LIQUID_NITER.get());
		
        return ItemStack.EMPTY;
	}

}
