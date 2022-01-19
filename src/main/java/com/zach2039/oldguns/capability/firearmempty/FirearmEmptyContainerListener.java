package com.zach2039.oldguns.capability.firearmempty;

import com.zach2039.oldguns.api.capability.firearmempty.IFirearmEmpty;
import com.zach2039.oldguns.capability.CapabilityContainerListener;
import com.zach2039.oldguns.network.capability.BulkUpdateContainerCapabilityMessage;
import com.zach2039.oldguns.network.capability.UpdateContainerCapabilityMessage;
import com.zach2039.oldguns.network.capability.firearmempty.BulkUpdateContainerFirearmEmptyMessage;
import com.zach2039.oldguns.network.capability.firearmempty.UpdateContainerFirearmEmptyMessage;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author grilled-salmon
 */
public class FirearmEmptyContainerListener extends CapabilityContainerListener<IFirearmEmpty> {

	public FirearmEmptyContainerListener(final ServerPlayerEntity player) {
		super(player, FirearmEmptyCapability.FIREARM_EMPTY_CAPABILITY, FirearmEmptyCapability.DEFAULT_FACING);
	}

	@Override
	protected BulkUpdateContainerCapabilityMessage<IFirearmEmpty, ?> createBulkUpdateMessage(int containerID,
			NonNullList<ItemStack> items) {
		return new BulkUpdateContainerFirearmEmptyMessage(FirearmEmptyCapability.DEFAULT_FACING, containerID, items);
	}

	@Override
	protected UpdateContainerCapabilityMessage<IFirearmEmpty, ?> createSingleUpdateMessage(int containerID,
			int slotNumber, IFirearmEmpty handler) {
		return new UpdateContainerFirearmEmptyMessage(FirearmEmptyCapability.DEFAULT_FACING, containerID, slotNumber, handler);
	}
}
