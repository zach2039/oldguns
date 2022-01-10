package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.network.FirearmEffectMessage;
import com.zach2039.oldguns.network.capability.firearmempty.UpdateMenuFirearmEmptyMessage;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModNetwork {
	public static final ResourceLocation CHANNEL_NAME = new ResourceLocation(OldGuns.MODID, "network");
	
	public static final String NETWORK_VERSION = new ResourceLocation(OldGuns.MODID, "2").toString();
	
	public static SimpleChannel getNetworkChannel() {
		final SimpleChannel channel = NetworkRegistry.ChannelBuilder.named(CHANNEL_NAME)
				.clientAcceptedVersions(version -> true)
				.serverAcceptedVersions(version -> true)
				.networkProtocolVersion(() -> NETWORK_VERSION)
				.simpleChannel();
		
		channel.messageBuilder(FirearmEffectMessage.class, 1)
				.decoder(FirearmEffectMessage::decode)
				.encoder(FirearmEffectMessage::encode)
				.consumer(FirearmEffectMessage::handle)
				.add();
		
		channel.messageBuilder(UpdateMenuFirearmEmptyMessage.class, 2)
				.decoder(UpdateMenuFirearmEmptyMessage::decode)
				.encoder(UpdateMenuFirearmEmptyMessage::encode)
				.consumer(UpdateMenuFirearmEmptyMessage::handle)
				.add();
		
		return channel;
	}
}
