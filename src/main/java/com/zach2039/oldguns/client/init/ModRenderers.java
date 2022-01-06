package com.zach2039.oldguns.client.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.client.renderer.entity.BulletProjectileRenderer;
import com.zach2039.oldguns.init.ModEntities;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = OldGuns.MODID, value = Dist.CLIENT, bus = Bus.MOD)
public class ModRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ModEntities.BULLET_PROJECTILE.get(), ctx -> new BulletProjectileRenderer(ctx));
	}
}
