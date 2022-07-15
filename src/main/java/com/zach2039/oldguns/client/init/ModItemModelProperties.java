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
			FirearmEmptyPropertyFunction.registerForItem(ModItems.MATCHLOCK_DERRINGER.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.MATCHLOCK_PISTOL.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.MATCHLOCK_ARQUEBUS.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.MATCHLOCK_CALIVER.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.MATCHLOCK_MUSKETOON.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.MATCHLOCK_MUSKET.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.MATCHLOCK_LONG_MUSKET.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.MATCHLOCK_BLUNDERBUSS_PISTOL.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.MATCHLOCK_BLUNDERBUSS.get());
		
			FirearmEmptyPropertyFunction.registerForItem(ModItems.WHEELLOCK_DERRINGER.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.WHEELLOCK_PISTOL.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.WHEELLOCK_DOUBLEBARREL_PISTOL.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.WHEELLOCK_ARQUEBUS.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.WHEELLOCK_CALIVER.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.WHEELLOCK_MUSKETOON.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.WHEELLOCK_MUSKET.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.WHEELLOCK_LONG_MUSKET.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.WHEELLOCK_BLUNDERBUSS_PISTOL.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.WHEELLOCK_BLUNDERBUSS.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.WHEELLOCK_HAND_MORTAR.get());
			
			FirearmEmptyPropertyFunction.registerForItem(ModItems.FLINTLOCK_DERRINGER.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.FLINTLOCK_DUCKFOOT_DERRINGER.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.FLINTLOCK_PISTOL.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.FLINTLOCK_PEPPERBOX_PISTOL.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.FLINTLOCK_ARQUEBUS.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.FLINTLOCK_CALIVER.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.FLINTLOCK_MUSKETOON.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.FLINTLOCK_MUSKET.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.FLINTLOCK_NOCK_GUN.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.FLINTLOCK_LONG_MUSKET.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.FLINTLOCK_BLUNDERBUSS_PISTOL.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.FLINTLOCK_BLUNDERBUSS.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS.get());
			
			FirearmEmptyPropertyFunction.registerForItem(ModItems.CAPLOCK_DERRINGER.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.CAPLOCK_DUCKFOOT_DERRINGER.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.CAPLOCK_PISTOL.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.CAPLOCK_PEPPERBOX_PISTOL.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.CAPLOCK_ARQUEBUS.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.CAPLOCK_CALIVER.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.CAPLOCK_MUSKETOON.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.CAPLOCK_MUSKET.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.CAPLOCK_LONG_MUSKET.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.CAPLOCK_BLUNDERBUSS_PISTOL.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.CAPLOCK_BLUNDERBUSS.get());
			FirearmEmptyPropertyFunction.registerForItem(ModItems.CAPLOCK_DOUBLEBARREL_BLUNDERBUSS.get());
		});
	}
}
