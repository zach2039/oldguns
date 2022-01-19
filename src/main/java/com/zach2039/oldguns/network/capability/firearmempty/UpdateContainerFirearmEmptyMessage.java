package com.zach2039.oldguns.network.capability.firearmempty;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.zach2039.oldguns.api.capability.firearmempty.IFirearmEmpty;
import com.zach2039.oldguns.capability.firearmempty.FirearmEmptyCapability;
import com.zach2039.oldguns.network.capability.UpdateContainerCapabilityMessage;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Direction;
import net.minecraftforge.fml.network.NetworkEvent;


/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author grilled-salmon
 */
public class UpdateContainerFirearmEmptyMessage extends UpdateContainerCapabilityMessage<IFirearmEmpty, Boolean> {
	public UpdateContainerFirearmEmptyMessage(
			@Nullable final Direction facing,
			final int containerID,	
			final int slotNumber,
			final IFirearmEmpty firearmEmpty
	) {
		super(
				FirearmEmptyCapability.FIREARM_EMPTY_CAPABILITY,
				facing, containerID, slotNumber, firearmEmpty,
				FirearmEmptyFunctions::convertFirearmEmptyToFirearmEmptyValue
		);
	}

	private UpdateContainerFirearmEmptyMessage(
			@Nullable final Direction facing,
			final int containerID,
			final int slotNumber,
			final boolean isEmpty
	) {
		super(
				FirearmEmptyCapability.FIREARM_EMPTY_CAPABILITY,
				facing, containerID, slotNumber, isEmpty
		);
	}

	public static UpdateContainerFirearmEmptyMessage decode(final PacketBuffer buffer) {
		return UpdateContainerCapabilityMessage.<IFirearmEmpty, Boolean, UpdateContainerFirearmEmptyMessage>decode(
				buffer,
				FirearmEmptyFunctions::decodeFirearmEmptyValue,
				UpdateContainerFirearmEmptyMessage::new
		);
	}

	public static void encode(final UpdateContainerFirearmEmptyMessage message, final PacketBuffer buffer) {
		UpdateContainerCapabilityMessage.encode(
				message,
				buffer,
				FirearmEmptyFunctions::encodeFirearmEmptyValue
		);
	}

	public static void handle(final UpdateContainerFirearmEmptyMessage message, final Supplier<NetworkEvent.Context> ctx) {
		UpdateContainerCapabilityMessage.handle(
				message,
				ctx,
				FirearmEmptyFunctions::applyFirearmEmptyValueToFirearmEmpty
		);
	}
}