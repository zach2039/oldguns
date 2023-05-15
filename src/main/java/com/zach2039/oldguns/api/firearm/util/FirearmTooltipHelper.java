package com.zach2039.oldguns.api.firearm.util;

import java.util.List;

import com.zach2039.oldguns.api.ammo.Ammo;
import com.zach2039.oldguns.world.item.firearm.FirearmItem;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;

public class FirearmTooltipHelper {
	public static void populateTooltipInfo(FirearmItem firearmItem, ItemStack firearmStack, List<Component> tooltip)
	{
		/* Get info on ammo. */
		int ammoCount = FirearmNBTHelper.peekNBTTagAmmoCount(firearmStack);
		int ammoCapacity = firearmItem.getAmmoCapacity();
		int emptyAmmo = ammoCapacity - ammoCount;
		
		tooltip.add(Component.literal("Ammo: ").withStyle(ChatFormatting.GRAY)
				.append(Component.literal("■".repeat(ammoCount)).withStyle(ChatFormatting.DARK_GREEN))
				.append(Component.literal("□".repeat(emptyAmmo)).withStyle(ChatFormatting.DARK_RED))
				);
		
		/* Print condition to tooltip. Color based on status. */
		Component conditionMsg = Component.literal("");
		
		FirearmNBTHelper.refreshFirearmCondition(firearmStack);
		switch (FirearmNBTHelper.getNBTTagCondition(firearmStack))
		{
			case BROKEN:
				conditionMsg = Component.literal("█████").withStyle(ChatFormatting.DARK_GRAY)
						.append(Component.literal(" (Broken)").withStyle(ChatFormatting.DARK_RED));
				break;
			case VERY_POOR:
				conditionMsg = Component.literal("█").withStyle(ChatFormatting.RED)
						.append(Component.literal("████").withStyle(ChatFormatting.DARK_GRAY));
				break;
			case POOR:
				conditionMsg = Component.literal("██").withStyle(ChatFormatting.RED)
						.append(Component.literal("███").withStyle(ChatFormatting.DARK_GRAY));
				break;
			case FAIR:
				conditionMsg = Component.literal("███").withStyle(ChatFormatting.YELLOW)
						.append(Component.literal("██").withStyle(ChatFormatting.DARK_GRAY));
				break;
			case GOOD:
				conditionMsg = Component.literal("████").withStyle(ChatFormatting.GREEN)
						.append(Component.literal("█").withStyle(ChatFormatting.DARK_GRAY));
				break;
			case VERY_GOOD:
				conditionMsg = Component.literal("█████").withStyle(ChatFormatting.DARK_GREEN);
				break;
			default:
				break;
		}
		
		tooltip.add(Component.literal("Cnd: ").withStyle(ChatFormatting.GRAY)
				.append(conditionMsg)
				);
		
		Component sizeMsg = Component.literal("");
		switch (firearmItem.getFirearmSize())
		{
			case SMALL:
				sizeMsg = Component.literal("Light").withStyle(ChatFormatting.AQUA);
				break;
			case MEDIUM:
				sizeMsg = Component.literal("Medium").withStyle(ChatFormatting.WHITE);
				break;
			case LARGE:
				sizeMsg = Component.literal("Heavy").withStyle(ChatFormatting.YELLOW);
				break;
			case HUGE:
				sizeMsg = Component.literal("Unwieldy").withStyle(ChatFormatting.RED);
				break;
			default:
				break;
		}
		
		tooltip.add(Component.literal("Weight: ").withStyle(ChatFormatting.GRAY)
				.append(sizeMsg)
				);
		
		// Additional info on shift
		if (!Screen.hasShiftDown()) {
			tooltip.add(Component.literal("[SHIFT]").withStyle(ChatFormatting.DARK_GRAY));
		} else {
			tooltip.add(Component.literal("[SHIFT]").withStyle(ChatFormatting.BOLD));		
			
			List<ItemStack> loadedAmmo = FirearmNBTHelper.getNBTTagMagazineStack(firearmStack);
			for (int i = 0; i < ammoCapacity; i++) {
				Component loadedAmmoMsg = (i < loadedAmmo.size()) ? ((MutableComponent)loadedAmmo.get(i).getHoverName()).withStyle(ChatFormatting.DARK_GRAY) : Component.literal("");
				tooltip.add(Component.literal("\u204D ").withStyle(ChatFormatting.GRAY)
						.append(loadedAmmoMsg)
						);
			}
		}
	}
}
