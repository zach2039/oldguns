package com.zach2039.oldguns.api.firearm.util;

import java.util.List;

import com.zach2039.oldguns.api.ammo.Ammo;
import com.zach2039.oldguns.world.item.firearm.FirearmItem;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;

public class FirearmTooltipHelper {
	public static void populateTooltipInfo(FirearmItem firearmItem, ItemStack firearmStack, List<Component> tooltip)
	{
		/* Get info on ammo. */
		int ammoCount = FirearmNBTHelper.peekNBTTagAmmoCount(firearmStack);
		int ammoCapacity = firearmItem.getAmmoCapacity();
		int emptyAmmo = ammoCapacity - ammoCount;
		
		tooltip.add(new TextComponent("Ammo: ").withStyle(ChatFormatting.GRAY)
				.append(new TextComponent("■".repeat(ammoCount)).withStyle(ChatFormatting.DARK_GREEN))
				.append(new TextComponent("□".repeat(emptyAmmo)).withStyle(ChatFormatting.DARK_RED))
				);
		
		/* Print condition to tooltip. Color based on status. */
		Component conditionMsg = new TextComponent("");
		
		FirearmNBTHelper.refreshFirearmCondition(firearmStack);
		switch (FirearmNBTHelper.getNBTTagCondition(firearmStack))
		{
			case BROKEN:
				conditionMsg = new TextComponent("█████").withStyle(ChatFormatting.DARK_GRAY)
						.append(new TextComponent(" (Broken)").withStyle(ChatFormatting.DARK_RED));
				break;
			case VERY_POOR:
				conditionMsg = new TextComponent("█").withStyle(ChatFormatting.RED)
						.append(new TextComponent("████").withStyle(ChatFormatting.DARK_GRAY));
				break;
			case POOR:
				conditionMsg = new TextComponent("██").withStyle(ChatFormatting.RED)
						.append(new TextComponent("███").withStyle(ChatFormatting.DARK_GRAY));
				break;
			case FAIR:
				conditionMsg = new TextComponent("███").withStyle(ChatFormatting.YELLOW)
						.append(new TextComponent("██").withStyle(ChatFormatting.DARK_GRAY));
				break;
			case GOOD:
				conditionMsg = new TextComponent("████").withStyle(ChatFormatting.GREEN)
						.append(new TextComponent("█").withStyle(ChatFormatting.DARK_GRAY));
				break;
			case VERY_GOOD:
				conditionMsg = new TextComponent("█████").withStyle(ChatFormatting.DARK_GREEN);
				break;
			default:
				break;
		}
		
		tooltip.add(new TextComponent("Cnd: ").withStyle(ChatFormatting.GRAY)
				.append(conditionMsg)
				);
		
		Component sizeMsg = new TextComponent("");
		switch (firearmItem.getFirearmSize())
		{
			case SMALL:
				sizeMsg = new TextComponent("Light").withStyle(ChatFormatting.AQUA);
				break;
			case MEDIUM:
				sizeMsg = new TextComponent("Medium").withStyle(ChatFormatting.WHITE);
				break;
			case LARGE:
				sizeMsg = new TextComponent("Heavy").withStyle(ChatFormatting.YELLOW);
				break;
			case HUGE:
				sizeMsg = new TextComponent("Unwieldy").withStyle(ChatFormatting.RED);
				break;
			default:
				break;
		}
		
		tooltip.add(new TextComponent("Weight: ").withStyle(ChatFormatting.GRAY)
				.append(sizeMsg)
				);
		
		// Additional info on shift
		if (!Screen.hasShiftDown()) {
			tooltip.add(new TextComponent("[SHIFT]").withStyle(ChatFormatting.DARK_GRAY));
		} else {
			tooltip.add(new TextComponent("[SHIFT]").withStyle(ChatFormatting.BOLD));		
			
			List<ItemStack> loadedAmmo = FirearmNBTHelper.getNBTTagMagazineStack(firearmStack);
			for (int i = 0; i < ammoCapacity; i++) {
				Component loadedAmmoMsg = (i < loadedAmmo.size()) ? ((TranslatableComponent)loadedAmmo.get(i).getHoverName()).withStyle(ChatFormatting.DARK_GRAY) : new TextComponent("");
				tooltip.add(new TextComponent("⁍ ").withStyle(ChatFormatting.GRAY)
						.append(loadedAmmoMsg)
						);
			}
		}
	}
}
