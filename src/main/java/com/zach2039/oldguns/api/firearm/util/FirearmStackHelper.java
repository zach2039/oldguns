package com.zach2039.oldguns.api.firearm.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class FirearmStackHelper {
	public static float getReloadProgress(LivingEntity entityIn, int currentUseTicks, int requiredReloadTicks)
	{
		return MathHelper.clamp((float) currentUseTicks / (float) requiredReloadTicks, 0.0f, 1.0f);
	}
}
