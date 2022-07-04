package com.zach2039.oldguns.client.event;

import com.zach2039.oldguns.init.ModFluids;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

/**
 * Some taken from <a href="https://github.com/Creators-of-Create/Create">Create</a> on Github
 * 
 * @author Creators-of-Create
 *
 * With additions by:
 * @author zach2039
 */
@EventBusSubscriber(Dist.CLIENT)
public class EntityViewRenderHandler {
	
	@SubscribeEvent
	public static void getFogDensity(EntityViewRenderEvent.RenderFogEvent event) {
		Camera info = event.getCamera();
		Level level = Minecraft.getInstance().level;
		BlockPos blockPos = info.getBlockPosition();
		FluidState fluidstate = level.getFluidState(blockPos);
        if (info.getPosition().y > blockPos.getY() + fluidstate.getHeight(level, blockPos)) 
           return;
        
		Fluid fluid = fluidstate.getType();

		if (fluid.isSame(ModFluids.LIQUID_NITER.getFlowing().get()) || fluid.isSame(ModFluids.LIQUID_NITER.getStill().get())) {
			event.setNearPlaneDistance(1f);
			event.setFarPlaneDistance(2f);
			event.setCanceled(true);
			return;
		}
	}
	
	@SubscribeEvent
	public static void getFogColor(EntityViewRenderEvent.FogColors event) {
		Camera info = event.getCamera();
		Level level = Minecraft.getInstance().level;
		BlockPos blockPos = info.getBlockPosition();
		FluidState fluidstate = level.getFluidState(blockPos);
        if (info.getPosition().y > blockPos.getY() + fluidstate.getHeight(level, blockPos)) 
           return;
        
		Fluid fluid = fluidstate.getType();

		if (fluid.isSame(ModFluids.LIQUID_NITER.getFlowing().get()) || fluid.isSame(ModFluids.LIQUID_NITER.getStill().get())) {
			event.setRed(255 / 256f);
			event.setGreen(235 / 256f);
			event.setBlue(241 / 256f);
		}
	}
}
