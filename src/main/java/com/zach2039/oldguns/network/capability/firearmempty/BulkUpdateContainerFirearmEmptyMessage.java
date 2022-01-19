package com.zach2039.oldguns.network.capability.firearmempty;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.zach2039.oldguns.api.capability.firearmempty.IFirearmEmpty;
import com.zach2039.oldguns.capability.firearmempty.FirearmEmptyCapability;
import com.zach2039.oldguns.network.capability.BulkUpdateContainerCapabilityMessage;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.network.NetworkEvent;


/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author grilled-salmon
 */
public class BulkUpdateContainerFirearmEmptyMessage extends BulkUpdateContainerCapabilityMessage<IFirearmEmpty, Boolean> {
	public BulkUpdateContainerFirearmEmptyMessage(
			@Nullable final Direction facing,
			final int windowID,	
			final NonNullList<ItemStack> items
	) {
		super(
				FirearmEmptyCapability.FIREARM_EMPTY_CAPABILITY,
				facing, windowID, items,
				FirearmEmptyFunctions::convertFirearmEmptyToFirearmEmptyValue
		);
	}

	private BulkUpdateContainerFirearmEmptyMessage(
			@Nullable final Direction facing,
			final int windowID,
			final Int2ObjectMap<Boolean> capabilityData
	) {
		super(
				FirearmEmptyCapability.FIREARM_EMPTY_CAPABILITY,
				facing, windowID, capabilityData
		);
	}

	public static BulkUpdateContainerFirearmEmptyMessage decode(final PacketBuffer buffer) {
		return BulkUpdateContainerCapabilityMessage.<IFirearmEmpty, Boolean, BulkUpdateContainerFirearmEmptyMessage>decode(
				buffer,
				FirearmEmptyFunctions::decodeFirearmEmptyValue,
				BulkUpdateContainerFirearmEmptyMessage::new
		);
	}

	public static void encode(final BulkUpdateContainerFirearmEmptyMessage message, final PacketBuffer buffer) {
		BulkUpdateContainerCapabilityMessage.encode(
				message,
				buffer,
				FirearmEmptyFunctions::encodeFirearmEmptyValue
		);
	}

	public static void handle(final BulkUpdateContainerFirearmEmptyMessage message, final Supplier<NetworkEvent.Context> ctx) {
		BulkUpdateContainerCapabilityMessage.handle(
				message,
				ctx,
				FirearmEmptyFunctions::applyFirearmEmptyValueToFirearmEmpty
		);
	}
}