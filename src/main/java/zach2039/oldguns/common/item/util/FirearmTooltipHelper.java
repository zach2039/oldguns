package zach2039.oldguns.common.item.util;

import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;

import net.minecraft.item.ItemStack;
import zach2039.oldguns.common.item.firearm.ItemFirearm;

public class FirearmTooltipHelper
{
	public static void populateTooltipInfo(ItemFirearm itemIn, ItemStack stackIn, List<String> tooltip)
	{
		/* Get info on ammo. */
		int ammoCount = FirearmNBTHelper.peekNBTTagAmmoCount(stackIn);
		int ammoCapacity = itemIn.getAmmoCapacity();
		
		/* Print ammo count and ammo capacity to tooltip. Color based on ammo amount. */
		ChatFormatting ammoFormatting = (ammoCount == 0) ? ChatFormatting.DARK_RED : ChatFormatting.DARK_GREEN;
		
		tooltip.add(ammoFormatting + String.format("Ammo: %d/%d", ammoCount, ammoCapacity));
		
		/* Print condition to tooltip. Color based on status. */
		ChatFormatting conditionFormatting = ChatFormatting.GREEN;
		String conditionString = "Very Good";
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
		
		tooltip.add(conditionFormatting + String.format("Cnd: %s", conditionString));
	}
}
