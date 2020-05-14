package zach2039.oldguns.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import zach2039.oldguns.client.model.entity.ModelEntityProjectile;
import zach2039.oldguns.common.entity.EntityProjectile;

public class RenderEntityProjectile extends Render<EntityProjectile>
{
	private ResourceLocation entityTexture;
	private ModelBase model = new ModelEntityProjectile();
	
	public RenderEntityProjectile(RenderManager renderManagerIn, ResourceLocation texture)
	{
		super(renderManagerIn);
		entityTexture = texture;
	}

	@Override
	public void doRender(EntityProjectile entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y, z);
		GlStateManager.rotate(entity.rotationPitch, 0, 1, 0);
		GlStateManager.rotate(entity.rotationYaw, 1, 0, 1);
		bindEntityTexture(entity);
		model.render(entity, 0.0F, 0.0F, -0.1F, entity.rotationYaw, entity.rotationPitch, 0.025F);
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityProjectile entity) {
		return entityTexture;
	}
}
