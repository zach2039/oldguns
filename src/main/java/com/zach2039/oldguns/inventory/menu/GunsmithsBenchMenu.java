package com.zach2039.oldguns.inventory.menu;

import java.util.Optional;

import com.zach2039.oldguns.crafting.recipe.GunsmithsBenchRecipe;
import com.zach2039.oldguns.init.ModBlocks;
import com.zach2039.oldguns.init.ModMenuTypes;
import com.zach2039.oldguns.init.ModRecipeTypes;
import com.zach2039.oldguns.inventory.GunsmithsBenchCraftingContainer;
import com.zach2039.oldguns.inventory.OldGunsDesignNotesSlot;
import com.zach2039.oldguns.inventory.OldGunsResultContainer;
import com.zach2039.oldguns.inventory.OldGunsResultSlot;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.IContainerFactory;

public class GunsmithsBenchMenu extends Container {
	public static final int RESULT_SLOT = 0;
	public static final int NOTES_SLOT = 9;
	
	private final GunsmithsBenchCraftingContainer craftSlots = new GunsmithsBenchCraftingContainer(this, 3, 3);
	private final OldGunsResultContainer resultSlots = new OldGunsResultContainer();
	private final IWorldPosCallable access;
	private final PlayerEntity player;

	public GunsmithsBenchMenu(int containerId, PlayerInventory inv) {
		this(containerId, inv, IWorldPosCallable.NULL);
	}

	public GunsmithsBenchMenu(int containerId, PlayerInventory inv, IWorldPosCallable access) {
		super(ModMenuTypes.GUNSMITHS_BENCH.get(), containerId);
		this.access = access;
		this.player = inv.player;
		
		this.addSlot(new OldGunsResultSlot(inv.player, this.craftSlots, this.resultSlots, 0, 124, 35));

		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 3; ++j) {
				this.addSlot(new Slot(this.craftSlots, j + i * 3, 30 + j * 18, 17 + i * 18));
			}
		}
		
		this.addSlot(new OldGunsDesignNotesSlot(this.craftSlots, NOTES_SLOT, 8, 35));
		
		for(int k = 0; k < 3; ++k) {
			for(int i1 = 0; i1 < 9; ++i1) {
				this.addSlot(new Slot(inv, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
			}
		}

		for(int l = 0; l < 9; ++l) {
			this.addSlot(new Slot(inv, l, 8 + l * 18, 142));
		}

	}

	protected static void slotChangedCraftingGrid(int slot, World level, PlayerEntity player, GunsmithsBenchCraftingContainer containerCrafting, OldGunsResultContainer containerResult) {
		if (!level.isClientSide) {
			ServerPlayerEntity serverplayer = (ServerPlayerEntity)player;
			ItemStack itemstack = ItemStack.EMPTY;
			Optional<GunsmithsBenchRecipe> optional = level.getServer().getRecipeManager().getRecipeFor(ModRecipeTypes.GUNSMITHS_BENCH, containerCrafting, level);
			if (optional.isPresent()) {
				GunsmithsBenchRecipe craftingrecipe = optional.get();
				if (containerResult.setRecipeUsed(level, serverplayer, craftingrecipe)) {
					itemstack = craftingrecipe.assemble(containerCrafting);
				}
			}

			containerResult.setItem(0, itemstack);
			serverplayer.connection.send(new SSetSlotPacket(slot, 0, itemstack));
		}
	}

	public void slotsChanged(IInventory inv) {
		this.access.execute((level, player) -> {
			slotChangedCraftingGrid(this.containerId, level, this.player, this.craftSlots, this.resultSlots);
		});
	}

	public void fillCraftSlotsStackedContents(RecipeItemHelper contents) {
		this.craftSlots.fillStackedContents(contents);
	}

	public void clearCraftingContent() {
		this.craftSlots.clearContent();
		this.resultSlots.clearContent();
	}
	
	public boolean recipeMatches(IRecipe<? super GunsmithsBenchCraftingContainer> recipe) {
		return recipe.matches(this.craftSlots, this.player.level);
	}

	@Override
	public void removed(PlayerEntity player) {
		super.removed(player);
		this.access.execute((p_217068_2_, p_217068_3_) -> {
			this.clearContainer(player, p_217068_2_, this.craftSlots);
		});
	}

	@Override
	public boolean stillValid(PlayerEntity player) {
		return stillValid(this.access, player, ModBlocks.GUNSMITHS_BENCH.get());
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity player, int slotIdx) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(slotIdx);
		int playerTotalInvSize = player.inventory.getContainerSize();
		int totalInvSize = (getSize() + playerTotalInvSize);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (slotIdx == 0) {
				this.access.execute((p_39378_, p_39379_) -> {
					itemstack1.getItem().onCraftedBy(itemstack1, p_39378_, player);
				});
				if (!this.moveItemStackTo(itemstack1, getSize(), totalInvSize, true)) {
					return ItemStack.EMPTY;
				}

				slot.onQuickCraft(itemstack1, itemstack);
			} else if (slotIdx >= getSize() && slotIdx < (totalInvSize)) {
				if (!this.moveItemStackTo(itemstack1, getSize() - 1, getSize(), false)) {
					if (!this.moveItemStackTo(itemstack1, 1, getSize(), false)) {
						if (slotIdx < (totalInvSize - getSize())) {
							if (!this.moveItemStackTo(itemstack1, (totalInvSize - getSize()), totalInvSize, false)) {
								return ItemStack.EMPTY;
							}
						} else if (!this.moveItemStackTo(itemstack1, getSize(), (totalInvSize - getSize()), false)) {
							return ItemStack.EMPTY;
						}
					}
				}
			} else if (!this.moveItemStackTo(itemstack1, getSize(), totalInvSize, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(player, itemstack1);
			if (slotIdx == 0) {
				player.drop(itemstack1, false);
			}
		}

		return itemstack;
	}

	public boolean canTakeItemForPickAll(ItemStack p_39381_, Slot p_39382_) {
		return p_39382_.container != this.resultSlots && super.canTakeItemForPickAll(p_39381_, p_39382_);
	}

	public int getResultSlotIndex() {
		return 0;
	}
	
	public int getDesignNotesSlotIndex() {
		return 10;
	}

	public int getGridWidth() {
		return this.craftSlots.getWidth();
	}

	public int getGridHeight() {
		return this.craftSlots.getHeight();
	}

	public int getSize() {
		return 11;
	}

	public boolean shouldMoveToInventory(int slot) {
		return (slot != this.getResultSlotIndex());
	}
	
	public static class Factory implements IContainerFactory<GunsmithsBenchMenu> {
		@Override
		public GunsmithsBenchMenu create(final int windowId, final PlayerInventory inv, final PacketBuffer data) {
			return new GunsmithsBenchMenu(windowId, inv);
		}
	}
}