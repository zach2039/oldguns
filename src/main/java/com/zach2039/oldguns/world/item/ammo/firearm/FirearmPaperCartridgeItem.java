package com.zach2039.oldguns.world.item.ammo.firearm;

import java.util.List;

import javax.annotation.Nullable;

import com.zach2039.oldguns.api.ammo.AmmoTypes.FirearmAmmo;
import com.zach2039.oldguns.api.ammo.ProjectilePowderType;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class FirearmPaperCartridgeItem extends FirearmAmmoItem {

	private final FirearmAmmo ammoEntry;
	private final ProjectilePowderType powderType;
	
	public FirearmPaperCartridgeItem(FirearmAmmo ammoEntry, ProjectilePowderType powderType) {
		super(ammoEntry);
		this.ammoEntry = ammoEntry;
		this.powderType = powderType;
	}

	@Override
	public void appendHoverText(ItemStack stackIn, @Nullable Level level, List<Component> tooltip, TooltipFlag flagIn) {
		super.appendHoverText(stackIn, level, tooltip, flagIn);

		tooltip.add(new TextComponent(""));
		tooltip.add(new TextComponent("Loaded with:").withStyle(ChatFormatting.GRAY));
		
		Component projectileMsg = new TranslatableComponent("item.oldguns." + this.ammoEntry.name().toLowerCase()).withStyle(ChatFormatting.DARK_GRAY);
		tooltip.add(new TextComponent("⁍ ").withStyle(ChatFormatting.GRAY).append(projectileMsg));
		
		Component powderMsg = new TranslatableComponent("item.oldguns." + this.powderType.name().toLowerCase() + "_black_powder").withStyle(ChatFormatting.DARK_GRAY);
		tooltip.add(new TextComponent("☼ ").withStyle(ChatFormatting.GRAY).append(powderMsg));
	}
}
