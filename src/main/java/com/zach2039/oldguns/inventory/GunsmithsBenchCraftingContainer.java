package com.zach2039.oldguns.world.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.util.NonNullList;

public class GunsmithsBenchCraftingContainer implements IInventory, IRecipeHelperPopulator {
	
	private final NonNullList<ItemStack> items;
	private final int width;
	private final int height;
	private final Container menu;

	public GunsmithsBenchCraftingContainer(Container menu, int width, int height) {
		this.items = NonNullList.withSize((width * height) + 1, ItemStack.EMPTY);
		this.menu = menu;
		this.width = width;
		this.height = height;
	}

	public int getContainerSize() {
		return this.items.size();
	}

	public boolean isEmpty() {
		for(ItemStack itemstack : this.items) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	public ItemStack getItem(int slot) {
		return slot >= this.getContainerSize() ? ItemStack.EMPTY : this.items.get(slot);
	}

	public ItemStack removeItemNoUpdate(int slot) {
		return ItemStackHelper.takeItem(this.items, slot);
	}

	public ItemStack removeItem(int slot, int amount) {
		ItemStack itemstack = ItemStackHelper.removeItem(this.items, slot, amount);
		if (!itemstack.isEmpty()) {
			this.menu.slotsChanged(this);
		}

		return itemstack;
	}

	public void setItem(int slot, ItemStack itemstack) {
		this.items.set(slot, itemstack);
		this.menu.slotsChanged(this);
	}

	public void setChanged() {
	}

	public boolean stillValid(PlayerEntity player) {
		return true;
	}

	public void clearContent() {
		this.items.clear();
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	public void fillStackedContents(RecipeItemHelper p_39342_) {
		for(ItemStack itemstack : this.items) {
			p_39342_.accountSimpleStack(itemstack);
		}
	}
}