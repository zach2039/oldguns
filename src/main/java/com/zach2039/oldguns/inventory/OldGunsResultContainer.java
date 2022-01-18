package com.zach2039.oldguns.world.inventory;

import javax.annotation.Nullable;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.RecipeHolder;
import net.minecraft.world.item.crafting.Recipe;

public class OldGunsResultContainer implements Container, RecipeHolder {
	private final NonNullList<ItemStack> itemStacks = NonNullList.withSize(1, ItemStack.EMPTY);
	@Nullable
	private Recipe<?> recipeUsed;

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
		return ContainerHelper.takeItem(this.itemStacks, 0);
	}

	public ItemStack removeItemNoUpdate(int slot) {
		return ContainerHelper.takeItem(this.itemStacks, 0);
	}

	public void setItem(int slot, ItemStack itemstack) {
		this.itemStacks.set(0, itemstack);
	}

	public void setChanged() {
	}

	public boolean stillValid(Player player) {
		return true;
	}

	public void clearContent() {
		this.itemStacks.clear();
	}

	public void setRecipeUsed(@Nullable Recipe<?> recipe) {
		this.recipeUsed = recipe;
	}

	@Nullable
	public Recipe<?> getRecipeUsed() {
		return this.recipeUsed;
	}
}
