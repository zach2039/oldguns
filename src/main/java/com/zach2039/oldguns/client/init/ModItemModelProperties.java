package com.zach2039.oldguns.client.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.client.item.FirearmEmptyPropertyFunction;
import com.zach2039.oldguns.init.ModItems;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = OldGuns.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class ModItemModelProperties {
	@SubscribeEvent
	public static void registerItemModelProperties(final FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			FirearmEmptyPropertyFunction.registerForItem(ModItems.FLINTLOCK_PISTOL.get());
		});
	}
}
