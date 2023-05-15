package com.zach2039.oldguns.world.item.ammo.firearm;

import java.util.List;

import javax.annotation.Nullable;

import com.zach2039.oldguns.api.ammo.AmmoTypes.FirearmAmmo;
import com.zach2039.oldguns.api.ammo.ProjectilePowderType;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class FirearmPaperCartridgeItem extends FirearmAmmoItem {

	private final FirearmAmmo ammoEntry;
	private final ProjectilePowderType powderType;
	private boolean hasPercussionCap = false;
	
	public FirearmPaperCartridgeItem(FirearmAmmo ammoEntry, ProjectilePowderType powderType) {
		super(ammoEntry);
		this.ammoEntry = ammoEntry;
		this.powderType = powderType;
	}
	
	public FirearmPaperCartridgeItem(FirearmAmmo ammoEntry, ProjectilePowderType powderType, boolean hasPercussionCap) {
		super(ammoEntry);
		this.ammoEntry = ammoEntry;
		this.powderType = powderType;
		this.hasPercussionCap = hasPercussionCap;
	}

	@Override
	public void appendHoverText(ItemStack stackIn, @Nullable Level level, List<Component> tooltip, TooltipFlag flagIn) {
		super.appendHoverText(stackIn, level, tooltip, flagIn);

		tooltip.add(Component.literal(""));
		tooltip.add(Component.literal("Loaded with:").withStyle(ChatFormatting.GRAY));
		
		Component projectileMsg = Component.translatable("item.oldguns." + this.ammoEntry.name().toLowerCase()).withStyle(ChatFormatting.DARK_GRAY);
		tooltip.add(Component.translatable("\u204D ").withStyle(ChatFormatting.GRAY).append(projectileMsg));
		
		Component powderMsg = Component.translatable("item.oldguns." + this.powderType.name().toLowerCase() + "_black_powder").withStyle(ChatFormatting.DARK_GRAY);
		tooltip.add(Component.literal("☼ ").withStyle(ChatFormatting.GRAY).append(powderMsg));
		
		if (hasPercussionCap) {
			Component capMsg = Component.translatable("item.oldguns.percussion_cap").withStyle(ChatFormatting.DARK_GRAY);
			tooltip.add(Component.literal("· ").withStyle(ChatFormatting.GRAY).append(powderMsg));
		}
	}
}
