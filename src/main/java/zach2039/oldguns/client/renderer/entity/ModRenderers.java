package zach2039.oldguns.client.renderer.entity;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.entity.EntityProjectile;

public class ModRenderers
{
	public static void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityProjectile.class, renderManager -> new RenderEntityProjectile(renderManager, new ResourceLocation(OldGuns.MODID, "textures/entity/entity_projectile.png")));
	}
}
