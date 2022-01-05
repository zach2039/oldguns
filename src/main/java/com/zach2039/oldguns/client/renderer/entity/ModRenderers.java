package com.zach2039.oldguns.client.renderer.entity;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.init.ModEntities;

import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = OldGuns.MODID, value = Dist.CLIENT, bus = Bus.MOD)
public class ModRenderers {
	@SubscribeEvent
	public static void register(final EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ModEntities.BULLET_PROJECTILE.get(), context -> new RenderBulletProjectile(context, new ResourceLocation(OldGuns.MODID, "textures/entity/entity_projectile.png")));
	}
}
