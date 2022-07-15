package com.zach2039.oldguns.api.firearm.util;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class PowderHornNBTHelper {
	
	public static int peekPowderCount(ItemStack powderHornStack)
	{
		return ItemStack.of(powderHornStack.getTag().getCompound("powder")).getCount();
	}
	
	public static ItemStack peekPowderStack(ItemStack powderHornStack)
	{
		return ItemStack.of(powderHornStack.getTag().getCompound("powder"));
	}
	
	public static boolean hasPowderOfTag(ItemStack powderHornStack, TagKey<Item> powderTag)
	{
		return peekPowderStack(powderHornStack).is(powderTag);
	}
	
	public static void setPowderStack(ItemStack powderHornStack, ItemStack powderStack)
	{
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
