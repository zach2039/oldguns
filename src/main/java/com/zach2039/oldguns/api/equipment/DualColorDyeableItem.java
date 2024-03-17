package com.zach2039.oldguns.api.equipment;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface DualColorDyeableItem {
	String TAG_COLOR_A = "colorA";
	String TAG_COLOR_B = "colorB";
	String TAG_DISPLAY = "display";
	int DEFAULT_A_COLOR = 0xb40a1a;
	int DEFAULT_B_COLOR = 0x132691;

	default boolean hasCustomColor(ItemStack stack) {
		CompoundTag compoundtag = stack.getTagElement(TAG_DISPLAY);
		return compoundtag != null && (compoundtag.contains(TAG_COLOR_A, 99) || compoundtag.contains(TAG_COLOR_B, 99));
	}

	default int getColorA(ItemStack stack) {
		CompoundTag compoundtag = stack.getTagElement(TAG_DISPLAY);
		return compoundtag != null && compoundtag.contains(TAG_COLOR_A, 99) ? compoundtag.getInt(TAG_COLOR_A) : DEFAULT_A_COLOR;
	}

	default void clearColorA(ItemStack stack) {
		CompoundTag compoundtag = stack.getTagElement(TAG_DISPLAY);
		if (compoundtag != null && compoundtag.contains(TAG_COLOR_A)) {
			compoundtag.remove(TAG_COLOR_A);
		}

	}
	
	default int getColorB(ItemStack stack) {
		CompoundTag compoundtag = stack.getTagElement(TAG_DISPLAY);
		return compoundtag != null && compoundtag.contains(TAG_COLOR_B, 99) ? compoundtag.getInt(TAG_COLOR_B) : DEFAULT_B_COLOR;
	}

	default void clearColorB(ItemStack stack) {
		CompoundTag compoundtag = stack.getTagElement(TAG_DISPLAY);
		if (compoundtag != null && compoundtag.contains(TAG_COLOR_B)) {
			compoundtag.remove(TAG_COLOR_B);
		}

	}

	default void setColorA(ItemStack stack, int colorHex) {
		stack.getOrCreateTagElement(TAG_DISPLAY).putInt(TAG_COLOR_A, colorHex);
	}

	default void setColorB(ItemStack stack, int colorHex) {
		stack.getOrCreateTagElement(TAG_DISPLAY).putInt(TAG_COLOR_B, colorHex);
	}
	
	static ItemStack dyeArmorColorA(ItemStack stack, List<DyeItem> dyeItems) {
		ItemStack itemstack = ItemStack.EMPTY;
		int[] aint = new int[3];
		int i = 0;
		int j = 0;
		DualColorDyeableItem dualColorDyeableItem = null;
		Item item = stack.getItem();
		if (item instanceof DyeableLeatherItem) {
			dualColorDyeableItem = (DualColorDyeableItem)item;
			itemstack = stack.copy();
			itemstack.setCount(1);
			if (dualColorDyeableItem.hasCustomColor(stack)) {
				int k = dualColorDyeableItem.getColorA(itemstack);
				float f = (float)(k >> 16 & 255) / 255.0F;
				float f1 = (float)(k >> 8 & 255) / 255.0F;
				float f2 = (float)(k & 255) / 255.0F;
				i = (int)((float)i + Math.max(f, Math.max(f1, f2)) * 255.0F);
				aint[0] = (int)((float)aint[0] + f * 255.0F);
				aint[1] = (int)((float)aint[1] + f1 * 255.0F);
				aint[2] = (int)((float)aint[2] + f2 * 255.0F);
				++j;
			}

			for(DyeItem dyeitem : dyeItems) {
				float[] afloat = dyeitem.getDyeColor().getTextureDiffuseColors();
				int i2 = (int)(afloat[0] * 255.0F);
				int l = (int)(afloat[1] * 255.0F);
				int i1 = (int)(afloat[2] * 255.0F);
				i += Math.max(i2, Math.max(l, i1));
				aint[0] += i2;
				aint[1] += l;
				aint[2] += i1;
				++j;
			}
		}

		if (dualColorDyeableItem == null) {
			return ItemStack.EMPTY;
		} else {
			int j1 = aint[0] / j;
			int k1 = aint[1] / j;
			int l1 = aint[2] / j;
			float f3 = (float)i / (float)j;
			float f4 = (float)Math.max(j1, Math.max(k1, l1));
			j1 = (int)((float)j1 * f3 / f4);
			k1 = (int)((float)k1 * f3 / f4);
			l1 = (int)((float)l1 * f3 / f4);
			int j2 = (j1 << 8) + k1;
			j2 = (j2 << 8) + l1;
			dualColorDyeableItem.setColorA(itemstack, j2);
			return itemstack;
		}
	}
	
	static ItemStack dyeArmorColorB(ItemStack stack, List<DyeItem> dyeItems) {
		ItemStack itemstack = ItemStack.EMPTY;
		int[] aint = new int[3];
		int i = 0;
		int j = 0;
		DualColorDyeableItem dualColorDyeableItem = null;
		Item item = stack.getItem();
		if (item instanceof DyeableLeatherItem) {
			dualColorDyeableItem = (DualColorDyeableItem)item;
			itemstack = stack.copy();
			itemstack.setCount(1);
			if (dualColorDyeableItem.hasCustomColor(stack)) {
				int k = dualColorDyeableItem.getColorB(itemstack);
				float f = (float)(k >> 16 & 255) / 255.0F;
				float f1 = (float)(k >> 8 & 255) / 255.0F;
				float f2 = (float)(k & 255) / 255.0F;
				i = (int)((float)i + Math.max(f, Math.max(f1, f2)) * 255.0F);
				aint[0] = (int)((float)aint[0] + f * 255.0F);
				aint[1] = (int)((float)aint[1] + f1 * 255.0F);
				aint[2] = (int)((float)aint[2] + f2 * 255.0F);
				++j;
			}

			for(DyeItem dyeitem : dyeItems) {
				float[] afloat = dyeitem.getDyeColor().getTextureDiffuseColors();
				int i2 = (int)(afloat[0] * 255.0F);
				int l = (int)(afloat[1] * 255.0F);
				int i1 = (int)(afloat[2] * 255.0F);
				i += Math.max(i2, Math.max(l, i1));
				aint[0] += i2;
				aint[1] += l;
				aint[2] += i1;
				++j;
			}
		}

		if (dualColorDyeableItem == null) {
			return ItemStack.EMPTY;
		} else {
			int j1 = aint[0] / j;
			int k1 = aint[1] / j;
			int l1 = aint[2] / j;
			float f3 = (float)i / (float)j;
			float f4 = (float)Math.max(j1, Math.max(k1, l1));
			j1 = (int)((float)j1 * f3 / f4);
			k1 = (int)((float)k1 * f3 / f4);
			l1 = (int)((float)l1 * f3 / f4);
			int j2 = (j1 << 8) + k1;
			j2 = (j2 << 8) + l1;
			dualColorDyeableItem.setColorB(itemstack, j2);
			return itemstack;
		}
	}
}
