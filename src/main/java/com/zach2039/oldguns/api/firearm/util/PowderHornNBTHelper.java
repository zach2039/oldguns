package com.zach2039.oldguns.api.firearm.util;

import com.zach2039.oldguns.world.item.tools.PowderHornItem;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class PowderHornNBTHelper {
	
	public static int peekPowderCount(ItemStack powderHornStack)
	{
		PowderHornItem.initNBTTags(powderHornStack);
		
		return ItemStack.of(powderHornStack.getTag().getCompound("powder")).getCount();
	}
	
	public static ItemStack peekPowderStack(ItemStack powderHornStack)
	{
		PowderHornItem.initNBTTags(powderHornStack);
		
		return ItemStack.of(powderHornStack.getTag().getCompound("powder"));
	}
	
	public static boolean hasPowderOfTag(ItemStack powderHornStack, TagKey<Item> powderTag)
	{
		return peekPowderStack(powderHornStack).is(powderTag);
	}
	
	public static void setPowderStack(ItemStack powderHornStack, ItemStack powderStack)
	{
		PowderHornItem.initNBTTags(powderHornStack);
		
		powderHornStack.getTag().put("powder", powderStack.serializeNBT());
	}
	
	public static void decrementPowderStack(ItemStack powderHornStack, int amount)
	{
		ItemStack powderStack = peekPowderStack(powderHornStack);
		
		if (powderStack.isEmpty())
			return;
		
		if (powderStack.getCount() <= amount) {
			setPowderStack(powderHornStack, ItemStack.EMPTY);
		} else {
			powderStack.shrink(amount);
			setPowderStack(powderHornStack, powderStack);
		}		
	}
}
