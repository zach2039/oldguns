package com.zach2039.oldguns.api.firearm.util;

import java.util.List;

import com.zach2039.oldguns.world.item.firearm.FirearmItem;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class FirearmTooltipHelper {
	public static void populateTooltipInfo(FirearmItem itemIn, ItemStack stackIn, List<StringTextComponent> tooltip)
	{
		/* Get info on ammo. */
		int ammoCount = FirearmNBTHelper.peekNBTTagAmmoCount(stackIn);
		int ammoCapacity = itemIn.getAmmoCapacity();
		
		/* Print ammo count and ammo capacity to tooltip. Color based on ammo amount. */
		TextFormatting ammoFormatting = (ammoCount == 0) ? TextFormatting.DARK_RED : TextFormatting.DARK_GREEN;
		
		tooltip.add(new StringTextComponent(ammoFormatting + String.format("Ammo: %d/%d", ammoCount, ammoCapacity)));
		
		/* Print condition to tooltip. Color based on status. */
		TextFormatting formatString = TextFormatting.GREEN;
		String tooltipString = "Very Good";
		
		FirearmNBTHelper.refreshFirearmCondition(stackIn);
		switch (FirearmNBTHelper.getNBTTagCondition(stackIn))
		{
			case BROKEN:
				formatString = TextFormatting.DARK_RED;
				tooltipString = "Broken";
				break;
			case VERY_POOR:
				formatString = TextFormatting.RED;
				tooltipString = "Very Poor";
				break;
			case POOR:
				formatString = TextFormatting.RED;
				tooltipString = "Poor";
				break;
			case FAIR:
				formatString = TextFormatting.YELLOW;
				tooltipString = "Fair";
				break;
			case GOOD:
				formatString = TextFormatting.GREEN;
				tooltipString = "Good";
				break;
			default:
				break;
		}
		
		if (tooltipString != "")
			tooltip.add(new StringTextComponent(formatString + String.format("Cnd: %s", tooltipString)));
		
		switch (itemIn.getFirearmSize())
		{
			case SMALL:
				formatString = TextFormatting.AQUA;
				tooltipString = "Light";
				break;
			case MEDIUM:
				formatString = TextFormatting.WHITE;
				tooltipString = "";
				break;
			case LARGE:
				formatString = TextFormatting.YELLOW;
				tooltipString = "Heavy";
				break;
			case HUGE:
				formatString = TextFormatting.RED;
				tooltipString = "Unwieldy";
				break;
			default:
				break;
		}
		
		if (tooltipString != "")
			tooltip.add(new StringTextComponent(formatString + tooltipString));
	}
}
