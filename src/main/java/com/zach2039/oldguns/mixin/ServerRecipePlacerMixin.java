/**
 * Taken from RandomPatches to deal with firearm reload recipes not working with vanilla recipe book.
 * 
 * @author TheRandomLabs
 * @author zach2039
 * 
 * The MIT License (MIT)
 *
 * Copyright (c) 2020-2021 TheRandomLabs
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.zach2039.oldguns.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.zach2039.oldguns.config.OldGunsConfig;

import net.minecraft.recipebook.ServerPlaceRecipe;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;

@Mixin(ServerPlaceRecipe.class)
public final class ServerRecipePlacerMixin {
	@Redirect(
			method = "m_135438_",
			at = @At(
					value = "INVOKE",
					target = "net/minecraft/world/entity/player/Inventory.m_36043_(Lnet/minecraft/world/item/ItemStack;)I"
			)
	)
	private int getSlotWithUnusedStack(Inventory inventory, ItemStack stack) {
		if (OldGunsConfig.COMMON.patchRecipeBook.get()) {
			for (int i = 0; i < inventory.items.size(); i++) {
				final ItemStack toMatch = inventory.items.get(i);

				if (!toMatch.isEmpty() && toMatch.getItem() == stack.getItem() &&
						!toMatch.isDamaged() && !toMatch.isEnchanted() &&
						!toMatch.hasCustomHoverName()) {
					return i;
				}
			}

			return -1;
		}

		for (int i = 0; i < inventory.items.size(); i++) {
			final ItemStack toMatch = inventory.items.get(i);

			if (!toMatch.isEmpty() && toMatch.getItem() == stack.getItem() &&
					ItemStack.isSameItemSameTags(toMatch, stack) &&
					!toMatch.isDamaged() && !toMatch.isEnchanted() && !toMatch.hasCustomHoverName()) {
				return i;
			}
		}

		return -1;
	}
}
