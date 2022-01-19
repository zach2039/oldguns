package com.zach2039.oldguns.inventory;

import javax.annotation.Nullable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;

public class OldGunsResultContainer implements IInventory, IRecipeHolder {
	private final NonNullList<ItemStack> itemStacks = NonNullList.withSize(1, ItemStack.EMPTY);
	@Nullable
	private IRecipe<?> recipeUsed;

	public int getContainerSize() {
		return 1;
	}

	public boolean isEmpty() {
		for(ItemStack itemstack : this.itemStacks) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	public ItemStack getItem(int slot) {
		return this.itemStacks.get(0);
	}

	public ItemStack removeItem(int slot, int amount) {
		return ItemStackHelper.takeItem(this.itemStacks, 0);
	}

	public ItemStack removeItemNoUpdate(int slot) {
		return ItemStackHelper.takeItem(this.itemStacks, 0);
	}

	public void setItem(int slot, ItemStack itemstack) {
		this.itemStacks.set(0, itemstack);
	}

	public void setChanged() {
	}

	public boolean stillValid(PlayerEntity player) {
		return true;
	}

	public void clearContent() {
		this.itemStacks.clear();
	}

	public void setRecipeUsed(@Nullable IRecipe<?> recipe) {
		this.recipeUsed = recipe;
	}

	@Nullable
	public IRecipe<?> getRecipeUsed() {
		return this.recipeUsed;
	}

}
