package com.zach2039.oldguns.world.item.artillery;

import com.zach2039.oldguns.api.artillery.ArtilleryType;
import com.zach2039.oldguns.world.entity.MoveableArtillery;

import net.minecraft.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;

public abstract class ArtilleryItem extends Item {

	protected ArtilleryType artilleryType = ArtilleryType.CANNON;

	public ArtilleryItem(Properties builder) {
		super(builder);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
	{
		ItemStack itemstack = player.getItemInHand(hand);

		if (!level.isClientSide()) {

			MoveableArtillery entityartillery = MoveableArtillery.create(level, (double)player.getX() + 0.5D, (double)player.getY() + 1D, (double)player.getZ() + 0.5D, artilleryType);

			if (itemstack.hasCustomHoverName()) {
				entityartillery.setCustomName(itemstack.getDisplayName());
			}

			entityartillery.xRotO = (player.xRotO);
			level.addFreshEntity(entityartillery);
		}

		itemstack.shrink(1);

		return InteractionResultHolder.pass(itemstack);
	}

}
