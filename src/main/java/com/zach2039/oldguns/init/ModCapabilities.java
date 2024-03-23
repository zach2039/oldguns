package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.capability.firearmempty.FirearmEmptyCapability;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.Mod.EventBusSubscriber.Bus;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@Mod.EventBusSubscriber(modid = OldGuns.MODID, bus = Bus.MOD)
public class ModCapabilities {
	@SubscribeEvent
	public static void registerCapabilities(final RegisterCapabilitiesEvent event) {
		FirearmEmptyCapability.register(event);
	}
}
