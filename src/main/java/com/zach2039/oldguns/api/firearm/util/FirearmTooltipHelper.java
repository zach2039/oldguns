package com.zach2039.oldguns.api.firearm.util;

import java.util.List;

import com.zach2039.oldguns.world.item.firearm.FirearmItem;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.item.ItemStack;

public class FirearmTooltipHelper {
	public static void populateTooltipInfo(FirearmItem itemIn, ItemStack stackIn, List<Component> tooltip)
	{
		/* Get info on ammo. */
		int ammoCount = FirearmNBTHelper.peekNBTTagAmmoCount(stackIn);
		int ammoCapacity = itemIn.getAmmoCapacity();
		
		/* Print ammo count and ammo capacity to tooltip. Color based on ammo amount. */
		ChatFormatting ammoFormatting = (ammoCount == 0) ? ChatFormatting.DARK_RED : ChatFormatting.DARK_GREEN;
		
		tooltip.add(new TextComponent(ammoFormatting + String.format("Ammo: %d/%d", ammoCount, ammoCapacity)));
		
		/* Print condition to tooltip. Color based on status. */
		ChatFormatting conditionFormatting = ChatFormatting.GREEN;
		String conditionString = "Very Good";
		FirearmNBTHelper.refreshFirearmCondition(stackIn);
		switch (FirearmNBTHelper.getNBTTagCondition(stackIn))
		{
			case BROKEN:
				conditionFormatting = ChatFormatting.DARK_RED;
				conditionString = "Broken";
				break;
			case VERY_POOR:
				conditionFormatting = ChatFormatting.RED;
				conditionString = "Very Poor";
				break;
			case POOR:
				conditionFormatting = ChatFormatting.RED;
				conditionString = "Poor";
				break;
			case FAIR:
				conditionFormatting = ChatFormatting.YELLOW;
				conditionString = "Fair";
				break;
			case GOOD:
				conditionFormatting = ChatFormatting.GREEN;
				conditionString = "Good";
				break;
			default:
				break;
		}
		
		tooltip.add(new TextComponent(conditionFormatting + String.format("Cnd: %s", conditionString)));
	}

}
