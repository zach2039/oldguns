package com.zach2039.oldguns.crafting.recipe;

import com.zach2039.oldguns.init.ModRecipeTypes;
import com.zach2039.oldguns.item.tools.MortarAndPestleItem;
import com.zach2039.oldguns.item.tools.RepairKitItem;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;

public interface DamageableToolRecipe extends ICraftingRecipe {
	default IRecipeType<?> getType() {
		return ModRecipeTypes.DAMAGEABLE_TOOL_CRAFT;
	}
	
	default NonNullList<ItemStack> getRemainingItems(final CraftingInventory inv) {
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
		final PlayerEntity craftingPlayer = ForgeHooks.getCraftingPlayer();
		
		World level = craftingPlayer.getCommandSenderWorld();
		if (stack.hurt(1, level.random, craftingPlayer instanceof ServerPlayerEntity ? (ServerPlayerEntity) craftingPlayer : null)) {
			ForgeEventFactory.onPlayerDestroyItem(craftingPlayer, stack, null);
			return ItemStack.EMPTY;
		}
		
		return stack;
	}
}