package com.zach2039.oldguns.world.inventory.menu;

import java.util.Optional;

import com.zach2039.oldguns.init.ModBlocks;
import com.zach2039.oldguns.init.ModMenuTypes;
import com.zach2039.oldguns.init.ModRecipeTypes;
import com.zach2039.oldguns.world.inventory.GunsmithsBenchCraftingContainer;
import com.zach2039.oldguns.world.inventory.OldGunsDesignNotesSlot;
import com.zach2039.oldguns.world.inventory.OldGunsResultContainer;
import com.zach2039.oldguns.world.inventory.OldGunsResultSlot;
import com.zach2039.oldguns.world.item.crafting.GunsmithsBenchRecipe;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.IContainerFactory;

public class GunsmithsBenchMenu extends AbstractContainerMenu {
	public static final int RESULT_SLOT = 0;
	public static final int NOTES_SLOT = 9;
	
	private final GunsmithsBenchCraftingContainer craftSlots = new GunsmithsBenchCraftingContainer(this, 3, 3);
	private final OldGunsResultContainer resultSlots = new OldGunsResultContainer();
	private final ContainerLevelAccess access;
	private final Player player;

	public GunsmithsBenchMenu(int containerId, Inventory inv) {
		this(containerId, inv, ContainerLevelAccess.NULL);
	}

	public GunsmithsBenchMenu(int containerId, Inventory inv, ContainerLevelAccess access) {
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

	protected static void slotChangedCraftingGrid(AbstractContainerMenu menu, Level level, Player player, GunsmithsBenchCraftingContainer containerCrafting, OldGunsResultContainer containerResult) {
		if (!level.isClientSide) {
			ServerPlayer serverplayer = (ServerPlayer)player;
			ItemStack itemstack = ItemStack.EMPTY;
			Optional<GunsmithsBenchRecipe> optional = level.getServer().getRecipeManager().getRecipeFor(ModRecipeTypes.GUNSMITHS_BENCH.get(), containerCrafting, level);
			if (optional.isPresent()) {
				GunsmithsBenchRecipe craftingrecipe = optional.get();
				if (containerResult.setRecipeUsed(level, serverplayer, craftingrecipe)) {
					itemstack = craftingrecipe.assemble(containerCrafting);
				}
			}

			containerResult.setItem(0, itemstack);
			menu.setRemoteSlot(0, itemstack);
			serverplayer.connection.send(new ClientboundContainerSetSlotPacket(menu.containerId, menu.incrementStateId(), 0, itemstack));
		}
	}

	public void slotsChanged(Container p_39366_) {
		this.access.execute((level, player) -> {
			slotChangedCraftingGrid(this, level, this.player, this.craftSlots, this.resultSlots);
		});
	}

	public void fillCraftSlotsStackedContents(StackedContents contents) {
		this.craftSlots.fillStackedContents(contents);
	}

	public void clearCraftingContent() {
		this.craftSlots.clearContent();
		this.resultSlots.clearContent();
	}
	
	public boolean recipeMatches(Recipe<? super GunsmithsBenchCraftingContainer> recipe) {
		return recipe.matches(this.craftSlots, this.player.level);
	}

	public void removed(Player player) {
		super.removed(player);
		this.access.execute((p_39371_, p_39372_) -> {
			this.clearContainer(player, this.craftSlots);
		});
	}

	public boolean stillValid(Player p_39368_) {
		return stillValid(this.access, p_39368_, ModBlocks.GUNSMITHS_BENCH.get());
	}

	public ItemStack quickMoveStack(Player player, int slotIdx) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(slotIdx);
		int playerTotalInvSize = player.getInventory().items.size();
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
		public GunsmithsBenchMenu create(final int windowId, final Inventory inv, final FriendlyByteBuf data) {
			return new GunsmithsBenchMenu(windowId, inv);
		}
	}
}