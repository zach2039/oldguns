package com.zach2039.oldguns.world.item.crafting.recipe;

import com.zach2039.oldguns.init.ModItems;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
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
            return new ItemStack(ModItems.LIQUID_NITER_BOTTLE.get());
		
        return ItemStack.EMPTY;
	}

}
