package zach2039.oldguns.common.entity.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

public class EntityHelpers
{
	public static Vec3d getHeading(Entity entity)
	{
		return new Vec3d(entity.motionX, entity.motionY, entity.motionZ);
	}
	
}
