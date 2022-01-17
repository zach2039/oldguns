package com.zach2039.oldguns.api.firearm.util;

import java.util.List;

import com.zach2039.oldguns.world.item.firearm.FirearmItem;

import net.minecraft.ChatFormatting;
import net.minecraft.item.ItemStack;

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
		ChatFormatting formatString = ChatFormatting.GREEN;
		String tooltipString = "Very Good";
		
		FirearmNBTHelper.refreshFirearmCondition(stackIn);
		switch (FirearmNBTHelper.getNBTTagCondition(stackIn))
		{
			case BROKEN:
				formatString = ChatFormatting.DARK_RED;
				tooltipString = "Broken";
				break;
			case VERY_POOR:
				formatString = ChatFormatting.RED;
				tooltipString = "Very Poor";
				break;
			case POOR:
				formatString = ChatFormatting.RED;
				tooltipString = "Poor";
				break;
			case FAIR:
				formatString = ChatFormatting.YELLOW;
				tooltipString = "Fair";
				break;
			case GOOD:
				formatString = ChatFormatting.GREEN;
				tooltipString = "Good";
				break;
			default:
				break;
		}
		
		if (tooltipString != "")
			tooltip.add(new TextComponent(formatString + String.format("Cnd: %s", tooltipString)));
		
		switch (itemIn.getFirearmSize())
		{
			case SMALL:
				formatString = ChatFormatting.AQUA;
				tooltipString = "Light";
				break;
			case MEDIUM:
				formatString = ChatFormatting.WHITE;
				tooltipString = "";
				break;
			case LARGE:
				formatString = ChatFormatting.YELLOW;
				tooltipString = "Heavy";
				break;
			case HUGE:
				formatString = ChatFormatting.RED;
				tooltipString = "Unwieldy";
				break;
			default:
				break;
		}
		
		if (tooltipString != "")
			tooltip.add(new TextComponent(formatString + tooltipString));
	}
}
