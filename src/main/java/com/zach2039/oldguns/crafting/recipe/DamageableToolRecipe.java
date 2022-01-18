package com.zach2039.oldguns.crafting.recipe;

import com.zach2039.oldguns.init.ModRecipeTypes;
import com.zach2039.oldguns.item.tools.MortarAndPestleItem;
import com.zach2039.oldguns.item.tools.RepairKitItem;

import net.minecraft.item.ItemStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;

public interface DamageableToolRecipe extends CraftingRecipe {
	default RecipeType<?> getType() {
		return ModRecipeTypes.DAMAGEABLE_TOOL_CRAFT;
	}
	
	default NonNullList<ItemStack> getRemainingItems(final CraftingContainer inv) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);

		for (int i = 0; i < remainingItems.size(); ++i) {
			final ItemStack itemstack = inv.getItem(i);

			if (!itemstack.isEmpty() && (itemstack.getItem() instanceof MortarAndPestleItem || itemstack.getItem() instanceof RepairKitItem)) {
				remainingItems.set(i, damageItem(itemstack.copy()));
			} else {
				remainingItems.set(i, ForgeHooks.getContainerItem(itemstack));
			}
		}

		return remainingItems;
	}
	
	default ItemStack damageItem(final ItemStack stack) {
		final Player craftingPlayer = ForgeHooks.getCraftingPlayer();
		
		World level = craftingPlayer.getCommandSenderWorld();
		if (stack.hurt(1, level.random, craftingPlayer instanceof ServerPlayer ? (ServerPlayer) craftingPlayer : null)) {
			ForgeEventFactory.onPlayerDestroyItem(craftingPlayer, stack, null);
			return ItemStack.EMPTY;
		}
		
		return stack;
	}
}