package com.zach2039.oldguns.network.capability.firearmempty;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.zach2039.oldguns.api.capability.empty.IFirearmEmpty;
import com.zach2039.oldguns.capability.firearmempty.FirearmEmptyCapability;
import com.zach2039.oldguns.network.capability.UpdateMenuCapabilityMessage;

import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author grilled-salmon
 */
public class UpdateMenuFirearmEmptyMessage extends UpdateMenuCapabilityMessage<IFirearmEmpty, Boolean> {

	public UpdateMenuFirearmEmptyMessage(
			@Nullable final Direction facing,
			final int containerID,
			final int stateID,
			final int slotNumber,
			final IFirearmEmpty firearmEmpty) {
		super(
				FirearmEmptyCapability.FIREARM_EMPTY_CAPABILITY,
				facing, containerID, stateID, slotNumber, firearmEmpty,
				FirearmEmptyFunctions::convertFirearmEmptyToFirearmEmptyValue
			);
	}

	private UpdateMenuFirearmEmptyMessage(
			@Nullable final Direction facing,
			final int windowID,
			final int stateID,
			final int slotNumber,
			final boolean firearmEmpty
	) {
		super(
				FirearmEmptyCapability.FIREARM_EMPTY_CAPABILITY,
				facing, windowID, stateID, slotNumber, firearmEmpty
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
