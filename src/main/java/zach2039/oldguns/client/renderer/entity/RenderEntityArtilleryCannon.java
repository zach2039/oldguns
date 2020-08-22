package zach2039.oldguns.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import zach2039.oldguns.client.model.entity.ModelEntityArtilleryCannon;
import zach2039.oldguns.common.entity.EntityArtilleryCannon;
import zach2039.oldguns.common.entity.EntityProjectile;

public class RenderEntityArtilleryCannon extends Render<EntityArtilleryCannon>
{
	private ResourceLocation entityTexture;
	private ModelBase model = new ModelEntityArtilleryCannon();
	
	public RenderEntityArtilleryCannon(RenderManager renderManagerIn, ResourceLocation texture)
	{
		super(renderManagerIn);
		entityTexture = texture;
	}

	@Override
	public void doRender(EntityArtilleryCannon entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.enableLighting();
		GlStateManager.enableNormalize();
		GlStateManager.enableRescaleNormal();
		bindEntityTexture(entity);
		GlStateManager.translate(x, y, z);
		GlStateManager.translate(0, 1.78, 0);
		GlStateManager.rotate(entity.rotationPitch - 180F, 0, 1, 0);
		GlStateManager.rotate(entity.rotationYaw, 1, 0, 1);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		bindEntityTexture(entity);
		GlStateManager.scale(-3.0F, -3.0F, 3.0F);
		model.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.025F);
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityArtilleryCannon entity) {
		return entityTexture;
	}
}
