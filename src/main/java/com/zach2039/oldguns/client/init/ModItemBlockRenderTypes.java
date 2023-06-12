package com.zach2039.oldguns.client.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.init.ModBlocks;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.lang.annotation.ElementType;

@Mod.EventBusSubscriber(modid = OldGuns.MODID, value = Dist.CLIENT, bus = Bus.MOD)
public class ModItemBlockRenderTypes {

	@SubscribeEvent
	public static void registerItemModelProperties(final FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLASTING_POWDER_STICK_BLOCK.get(), RenderType.cutout());
			ItemBlockRenderTypes.setRenderLayer(ModBlocks.WALL_BLASTING_POWDER_STICK_BLOCK.get(), RenderType.cutout());
		});
	}
}
