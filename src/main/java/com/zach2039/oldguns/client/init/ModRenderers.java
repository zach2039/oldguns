package com.zach2039.oldguns.client.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.client.renderer.entity.BulletProjectileRenderer;
import com.zach2039.oldguns.client.renderer.entity.NavalCannonRenderer;
import com.zach2039.oldguns.init.ModBlockEntities;
import com.zach2039.oldguns.init.ModBlocks;
import com.zach2039.oldguns.init.ModEntities;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.minecraftforge.client.model.IModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = OldGuns.MODID, value = Dist.CLIENT, bus = Bus.MOD)
public class ModRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(final EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(ModEntities.BULLET_PROJECTILE.get(), ctx -> new BulletProjectileRenderer(ctx));		
		
		event.registerBlockEntityRenderer(ModBlockEntities.MEDIUM_NAVAL_CANNON.get(), ctx -> new NavalCannonRenderer(ctx));
	}
}
