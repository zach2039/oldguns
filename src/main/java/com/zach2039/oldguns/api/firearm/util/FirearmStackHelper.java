package com.zach2039.oldguns.api.firearm.util;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class FirearmStackHelper {
	public static float getReloadProgress(LivingEntity entityIn, int currentUseTicks, int requiredReloadTicks)
	{
		return Mth.clamp((float) currentUseTicks / (float) requiredReloadTicks, 0.0f, 1.0f);
	}
}
