package com.zach2039.oldguns.capability.firearmempty;

import com.zach2039.oldguns.api.capability.empty.IFirearmEmpty;
import com.zach2039.oldguns.capability.CapabilityContainerListener;
import com.zach2039.oldguns.network.capability.firearmempty.UpdateMenuFirearmEmptyMessage;

import net.minecraft.server.level.ServerPlayer;

/**
 * Some taken from TestMod3 on Github
 * @author grilled-salmon
 * @author Choonster
 */
public class FirearmEmptyContainerListener extends CapabilityContainerListener<IFirearmEmpty> {

	public FirearmEmptyContainerListener(final ServerPlayer player) {
		super(player, FirearmEmptyCapability.FIREARM_EMPTY_CAPABILITY, FirearmEmptyCapability.DEFAULT_FACING);
	}

	@Override
	protected UpdateMenuFirearmEmptyMessage createUpdateMessage(final int containerID, final int stateID, final int slotNumber, final IFirearmEmpty firearmEmpty) {
		return new UpdateMenuFirearmEmptyMessage(FirearmEmptyCapability.DEFAULT_FACING, containerID, stateID, slotNumber, firearmEmpty);
	}
}
