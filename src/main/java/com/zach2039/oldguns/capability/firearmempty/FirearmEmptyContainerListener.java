package com.zach2039.oldguns.capability.firearmempty;

import com.zach2039.oldguns.api.capability.firearmempty.IFirearmEmpty;
import com.zach2039.oldguns.capability.CapabilityContainerListener;
import com.zach2039.oldguns.network.capability.firearmempty.UpdateMenuFirearmEmptyMessage;

import net.minecraft.server.level.ServerPlayer;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author grilled-salmon
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
