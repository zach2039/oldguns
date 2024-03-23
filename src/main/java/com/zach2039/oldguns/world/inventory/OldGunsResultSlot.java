package com.zach2039.oldguns.world.inventory;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.event.EventHooks;

public class OldGunsResultSlot extends Slot {
	   private final GunsmithsBenchCraftingContainer craftSlots;
	   private final Player player;
	   private int removeCount;

	   public OldGunsResultSlot(Player player, GunsmithsBenchCraftingContainer craftinv, Container inv, int slot, int x, int y) {
	      super(inv, slot, x, y);
	      this.player = player;
	      this.craftSlots = craftinv;
	   }

	   @Override
	   public boolean mayPlace(ItemStack stackIn) {
	      return false;
	   }

	   @Override
	   public ItemStack remove(int amount) {
	      if (this.hasItem()) {
	         this.removeCount += Math.min(amount, this.getItem().getCount());
	      }

	      return super.remove(amount);
	   }

	   @Override
	   protected void onQuickCraft(ItemStack itemStack, int numCrafted) {
	      this.removeCount += numCrafted;
	      this.checkTakeAchievements(itemStack);
	   }

	   @Override
	   protected void onSwapCraft(int numCrafted) {
	      this.removeCount += numCrafted;
	   }

	   @Override
	   protected void checkTakeAchievements(ItemStack stackIn) {
		   if (this.removeCount > 0) {
			   stackIn.onCraftedBy(this.player.level(), this.player, this.removeCount);
			   EventHooks.firePlayerCraftingEvent(this.player, stackIn, this.craftSlots);
		   }

		   Container container = this.container;
		   if (container instanceof RecipeCraftingHolder recipecraftingholder) {
			   recipecraftingholder.awardUsedRecipes(this.player, this.craftSlots.getItems());
		   }

		   this.removeCount = 0;
	   }

	   @Override
	   public void onTake(Player player, ItemStack stackIn) {
		   this.checkTakeAchievements(stackIn);
		   CommonHooks.setCraftingPlayer(player);
		   NonNullList<ItemStack> nonnulllist = player.level().getRecipeManager().getRemainingItemsFor(RecipeType.CRAFTING, this.craftSlots, player.level());
		   CommonHooks.setCraftingPlayer((Player)null);

		   for(int i = 0; i < nonnulllist.size(); ++i) {
			   ItemStack itemstack = this.craftSlots.getItem(i);
			   ItemStack itemstack1 = (ItemStack)nonnulllist.get(i);
			   if (!itemstack.isEmpty()) {
				   this.craftSlots.removeItem(i, 1);
				   itemstack = this.craftSlots.getItem(i);
			   }

			   if (!itemstack1.isEmpty()) {
				   if (itemstack.isEmpty()) {
					   this.craftSlots.setItem(i, itemstack1);
				   } else if (ItemStack.isSameItemSameTags(itemstack, itemstack1)) {
					   itemstack1.grow(itemstack.getCount());
					   this.craftSlots.setItem(i, itemstack1);
				   } else if (!this.player.getInventory().add(itemstack1)) {
					   this.player.drop(itemstack1, false);
				   }
			   }
		   }
	   }
	}
