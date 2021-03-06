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

package com.zach2039.oldguns.mixin.coremods;

//@Mixin(ServerPlaceRecipe.class)
//public final class ServerRecipePlacerMixin {
//	@Redirect(
//			method = "moveItemToGrid",
//			at = @At(
//					value = "INVOKE",
//					target = "net/minecraft/world/entity/player/Inventory.findSlotMatchingUnusedItem(Lnet/minecraft/world/item/ItemStack;)I"
//			)
//	)
//	private int getSlotWithUnusedStack(Inventory inventory, ItemStack stack) {
//		if (OldGunsConfig.COMMON.patchRecipeBook.get()) {
//			for (int i = 0; i < inventory.items.size(); i++) {
//				final ItemStack itemstack = inventory.items.get(i);
//				
//				if (
//						!inventory.items.get(i).isEmpty() && 
//						itemstack.getItem() == stack.getItem() &&
//						!inventory.items.get(i).isDamaged() && 
//						!itemstack.isEnchanted() && 
//						!itemstack.hasCustomHoverName()
//					) {
//					return i;
//				}
//			}
//
//			return -1;
//		}
//
//		for(int i = 0; i < inventory.items.size(); ++i) {
//			ItemStack itemstack = inventory.items.get(i);
//
//			if (
//					!inventory.items.get(i).isEmpty() && 
//					ItemStack.isSameItemSameTags(stack, inventory.items.get(i)) &&
//					!inventory.items.get(i).isDamaged() && 
//					!itemstack.isEnchanted() && 
//					!itemstack.hasCustomHoverName()
//				) {
//				return i;
//			}
//		}
//
//		return -1;
//	}
//}
