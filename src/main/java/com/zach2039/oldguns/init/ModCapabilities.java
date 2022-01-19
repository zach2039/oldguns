package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.capability.firearmempty.FirearmEmptyCapability;

import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = OldGuns.MODID, bus = Bus.MOD)
public class ModCapabilities {
	@SubscribeEvent
	public static void registerCapabilities(final RegisterCapabilitiesEvent event) {
		FirearmEmptyCapability.register(null);
	}
}
