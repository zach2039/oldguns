package zach2039.oldguns.common.item.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import zach2039.oldguns.common.OldGuns;

public class FirearmStackHelper {

	public static float getReloadProgress(EntityLivingBase entityIn, int requiredReloadTicks)
	{
		return MathHelper.clamp((float) entityIn.getItemInUseMaxCount() / (float) requiredReloadTicks, 0.0f, 1.0f);
	}
}
