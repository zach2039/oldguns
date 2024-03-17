package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.network.ArtilleryBlockEntityUpdateMessage;
import com.zach2039.oldguns.network.ArtilleryEffectMessage;
import com.zach2039.oldguns.network.FirearmEffectMessage;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

@Mod.EventBusSubscriber(modid = OldGuns.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModNetwork {

	@SubscribeEvent
	public static void register(final RegisterPayloadHandlerEvent event) {
		final IPayloadRegistrar registrar = event.registrar(OldGuns.MODID);

		registrar.play(ArtilleryEffectMessage.Data.ID, ArtilleryEffectMessage.Data::new, handler -> handler
				.client(ArtilleryEffectMessage.getInstance()::handle)
				.server(ArtilleryEffectMessage.getInstance()::handle)
		);

		registrar.play(ArtilleryBlockEntityUpdateMessage.Data.ID, ArtilleryBlockEntityUpdateMessage.Data::new, handler -> handler
				.client(ArtilleryBlockEntityUpdateMessage.getInstance()::handle)
				.server(ArtilleryBlockEntityUpdateMessage.getInstance()::handle)
		);

		registrar.play(FirearmEffectMessage.Data.ID, FirearmEffectMessage.Data::new, handler -> handler
				.client(FirearmEffectMessage.getInstance()::handle)
				.server(FirearmEffectMessage.getInstance()::handle)
		);
	}
}
