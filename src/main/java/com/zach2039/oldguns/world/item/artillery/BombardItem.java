package com.zach2039.oldguns.world.item.artillery;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.artillery.ArtilleryType;
import com.zach2039.oldguns.world.entity.MoveableArtillery;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BombardItem extends ArtilleryItem {
	
	public BombardItem() {
		super(new Properties().stacksTo(1).tab(OldGuns.CREATIVE_MODE_TAB));
		this.artilleryType = ArtilleryType.BOMBARD;
	}
}
