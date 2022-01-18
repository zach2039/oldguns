package com.zach2039.oldguns.world.item.artillery;

import com.zach2039.oldguns.api.artillery.ArtilleryType;
import com.zach2039.oldguns.world.entity.MoveableArtillery;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.ActionResultTypeHolder;
import net.minecraft.world.World;

public abstract class ArtilleryItem extends Item {

	protected ArtilleryType artilleryType = ArtilleryType.CANNON;

	public ArtilleryItem(Properties builder) {
		super(builder);
	}

	@Override
	public ActionResult<ItemStack> use(World level, PlayerEntity player, Hand hand)
	{
		ItemStack itemstack = player.getItemInHand(hand);

		if (!level.isClientSide()) {

			MoveableArtillery entityartillery = MoveableArtillery.create(level, (double)player.getX() + 0.5D, (double)player.getY() + 1D, (double)player.getZ() + 0.5D, artilleryType);

			if (itemstack.hasCustomHoverName()) {
				entityartillery.(itemstack.getDisplayName());
			}

			entityartillery.xRotO = (player.xRotO);
			level.addFreshEntity(entityartillery);
		}

		itemstack.shrink(1);

		return ActionResult.pass(itemstack);
	}

}
