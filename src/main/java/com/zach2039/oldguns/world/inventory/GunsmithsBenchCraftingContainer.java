package com.zach2039.oldguns.world.inventory;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class GunsmithsBenchCraftingContainer implements Container, StackedContentsCompatible, CraftingContainer {
	
	private final NonNullList<ItemStack> items;
	private final int width;
	private final int height;
	private final AbstractContainerMenu menu;

	public GunsmithsBenchCraftingContainer(AbstractContainerMenu menu, int width, int height) {
		this.items = NonNullList.withSize((width * height) + 1, ItemStack.EMPTY);
		this.menu = menu;
		this.width = width;
		this.height = height;
	}

	@Override
	public int getContainerSize() {
		return this.items.size();
	}

	@Override
	public boolean isEmpty() {
		for(ItemStack itemstack : this.items) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	@Override
	public ItemStack getItem(int slot) {
		return slot >= this.getContainerSize() ? ItemStack.EMPTY : this.items.get(slot);
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return ContainerHelper.takeItem(this.items, slot);
	}

	@Override
	public ItemStack removeItem(int slot, int amount) {
		ItemStack itemstack = ContainerHelper.removeItem(this.items, slot, amount);
		if (!itemstack.isEmpty()) {
			this.menu.slotsChanged(this);
		}

		return itemstack;
	}

	@Override
	public void setItem(int slot, ItemStack itemstack) {
		this.items.set(slot, itemstack);
		this.menu.slotsChanged(this);
	}

	@Override
	public void setChanged() {
	}

	@Override
	public boolean stillValid(Player player) {
		return true;
	}

	@Override
	public void clearContent() {
		this.items.clear();
	}

	public int getHeight() {
		return this.height;
	}

	@Override
	public List<ItemStack> getItems() {
		return this.items;
	}

	public int getWidth() {
		return this.width;
	}

	@Override
	public void fillStackedContents(StackedContents stackedContents) {
		for(ItemStack itemstack : this.items) {
			stackedContents.accountSimpleStack(itemstack);
		}
	}
}