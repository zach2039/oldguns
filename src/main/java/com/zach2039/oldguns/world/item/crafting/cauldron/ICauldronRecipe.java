package com.zach2039.oldguns.world.item.crafting.cauldron;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluid;

public interface ICauldronRecipe {

    boolean isInput(ItemStack input);

    ItemStack getOutput(ItemStack input);

	ItemStack getOutput();
	
	Fluid getFluid();
}
