package com.zach2039.oldguns.world.inventory;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;

public class GunsmithsBenchCraftingContainer implements Container, StackedContentsCompatible {
	private final NonNullList<ItemStack> items;
	private final int width;
	private final int height;
	private final AbstractContainerMenu menu;

	public GunsmithsBenchCraftingContainer(AbstractContainerMenu menu, int width, int height) {
		this.items = NonNullList.withSize(width * height, ItemStack.EMPTY);
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
		return ContainerHelper.takeItem(this.items, slot);
	}

	public ItemStack removeItem(int slot, int amount) {
		ItemStack itemstack = ContainerHelper.removeItem(this.items, slot, amount);
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

	public boolean stillValid(Player player) {
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

	public void fillStackedContents(StackedContents p_39342_) {
		for(ItemStack itemstack : this.items) {
			p_39342_.accountSimpleStack(itemstack);
		}
	}
}