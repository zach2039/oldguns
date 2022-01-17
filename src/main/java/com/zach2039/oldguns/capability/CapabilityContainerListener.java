package com.zach2039.oldguns.capability;

import javax.annotation.Nullable;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.network.capability.UpdateMenuCapabilityMessage;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.IContainerListener;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.network.PacketDistributor;

/**
 * Syncs the capability handler instances for items in {@link AbstractContainerMenu}s.
 *
 * @param <HANDLER> The capability handler type to sync
 * @author Choonster
 */
public abstract class CapabilityContainerListener<HANDLER> implements IContainerListener {
	/**
	 * The player.
	 */
	private final ServerPlayerEntity player;

	/**
	 * The {@link Capability} instance to update.
	 */
	private final Capability<HANDLER> capability;

	/**
	 * The {@link Direction} to get the capability handler from.
	 */
	@Nullable
	private final Direction facing;

	public CapabilityContainerListener(final ServerPlayerEntity player, final Capability<HANDLER> capability, @Nullable final Direction facing) {
		this.player = player;
		this.capability = capability;
		this.facing = facing;
	}

	@Override
	public final void slotChanged(final Container menu, final int slotNumber, final ItemStack stack) {
		if (!shouldSyncItem(stack)) {
			return;
		}

		stack.getCapability(capability, facing).ifPresent(handler -> {
			final UpdateMenuCapabilityMessage<HANDLER, ?> message = createUpdateMessage(menu.containerId, slotNumber, handler);
			if (message.hasData()) { // Don't send the message if there's nothing to update
				OldGuns.network.send(PacketDistributor.PLAYER.with(() -> player), message);
			}
		});
	}

	@Override
	public void setContainerData(final Container menu, final int variable, final int newValue) {
		// No-op
	}

	/**
	 * Should the {@link ItemStack}'s capability data be synced?
	 *
	 * @param stack The item
	 * @return Should the capability data be synced?
	 */
	protected boolean shouldSyncItem(final ItemStack stack) {
		return true;
	}

	/**
	 * Create an instance of the update message.
	 *
	 * @param containerID The ID of the menu
	 * @param stateID     The state ID from the menu
	 * @param slotNumber  The slot's index in the menu
	 * @param handler     The capability handler instance
	 * @return The update message
	 */
	protected abstract UpdateMenuCapabilityMessage<HANDLER, ?> createUpdateMessage(final int containerID, final int slotNumber, final HANDLER handler);
}
