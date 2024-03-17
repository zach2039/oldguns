package com.zach2039.oldguns.world.item.material;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

import javax.annotation.Nullable;

public class BurnableMaterialItem extends MaterialItem {
	
	private int burnTime = 100;
	
	public BurnableMaterialItem(int maxStackSize, int burnTime) {
		super(maxStackSize);
		this.burnTime = burnTime;
	}
	
	public BurnableMaterialItem(int burnTime) {
		super();
		this.burnTime = burnTime;
	}
	
	public BurnableMaterialItem() {
		super();
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
		return this.burnTime;
	}

}
