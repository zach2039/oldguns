package com.zach2039.oldguns.network.capability.firearmempty;

import com.zach2039.oldguns.api.capability.firearmempty.IFirearmEmpty;
import com.zach2039.oldguns.capability.firearmempty.FirearmEmptyCapability;
import com.zach2039.oldguns.network.capability.UpdateMenuCapabilityMessage;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import javax.annotation.Nullable;
import java.util.function.Supplier;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author zach2039
 */
public class UpdateMenuFirearmEmptyMessage extends UpdateMenuCapabilityMessage<IFirearmEmpty, Boolean> {
	public UpdateMenuFirearmEmptyMessage(
			@Nullable final Direction facing,
			final int containerID,
			final int stateID,
			final int slotNumber,
			final IFirearmEmpty hiddenBlockRevealer
	) {
		super(
				FirearmEmptyCapability.FIREARM_EMPTY_CAPABILITY,
				facing, containerID, stateID, slotNumber, hiddenBlockRevealer,
				FirearmEmptyFunctions::convertFirearmEmptyToFirearmEmptyValue
		);
	}

	private UpdateMenuFirearmEmptyMessage(
			@Nullable final Direction facing,
			final int containerID,
			final int stateID,
			final int slotNumber,
			final boolean revealHiddenBlocks
	) {
		super(
				FirearmEmptyCapability.FIREARM_EMPTY_CAPABILITY,
				facing, containerID, stateID, slotNumber, revealHiddenBlocks
		);
	}

	public static UpdateMenuFirearmEmptyMessage decode(final FriendlyByteBuf buffer) {
		return UpdateMenuCapabilityMessage.<IFirearmEmpty, Boolean, UpdateMenuFirearmEmptyMessage>decode(
				buffer,
				FirearmEmptyFunctions::decodeFirearmEmptyValue,
				UpdateMenuFirearmEmptyMessage::new
		);
	}

	public static void encode(final UpdateMenuFirearmEmptyMessage message, final FriendlyByteBuf buffer) {
		UpdateMenuCapabilityMessage.encode(
				message,
				buffer,
				FirearmEmptyFunctions::encodeFirearmEmptyValue
		);
	}

	public static void handle(final UpdateMenuFirearmEmptyMessage message, final Supplier<NetworkEvent.Context> ctx) {
		UpdateMenuCapabilityMessage.handle(
				message,
				ctx,
				FirearmEmptyFunctions::applyFirearmEmptyValueToFirearmEmpty
		);
	}
}