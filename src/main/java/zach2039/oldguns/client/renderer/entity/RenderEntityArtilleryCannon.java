package zach2039.oldguns.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import zach2039.oldguns.client.model.entity.ModelArtilleryCarriage;
import zach2039.oldguns.client.model.entity.ModelCannonBarrel;
import zach2039.oldguns.client.model.entity.ModelCarriageWheel;
import zach2039.oldguns.common.entity.EntityArtilleryCannon;

public class RenderEntityArtilleryCannon extends Render<EntityArtilleryCannon>
{
	private ResourceLocation entityTexture;
	private ModelBase modelBarrel = new ModelCannonBarrel();
	private ModelBase modelCarriage = new ModelArtilleryCarriage();
	private ModelBase modelLeftWheel = new ModelCarriageWheel();
	private ModelBase modelRightWheel = new ModelCarriageWheel();
	
	public RenderEntityArtilleryCannon(RenderManager renderManagerIn, ResourceLocation texture)
	{
		super(renderManagerIn);
		entityTexture = texture;
	}

	@Override
	public void doRender(EntityArtilleryCannon entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		
		float f3 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
		
		//System.out.printf("spin: %f\n", entity.getWheelSpin());
		
		GlStateManager.enableLighting();
		GlStateManager.enableNormalize();
		GlStateManager.enableRescaleNormal();
		bindEntityTexture(entity);
		GlStateManager.translate(x, y, z);
		GlStateManager.scale(-3.0F, -3.0F, 3.0F);
		GlStateManager.rotate(180.0F + entityYaw, 0.0F, 1.0F, 0.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(0, -0.35, 0);
		GlStateManager.rotate(f3, 1.0F, 0.0F, 0.0F);
		modelBarrel.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.025F);
		GlStateManager.popMatrix();
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(0, -0.78, 0);
		modelCarriage.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.025F);
		GlStateManager.popMatrix();
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(0.17, -0.18, 0);
		GlStateManager.rotate(180, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(-entity.getWheelSpin(), 1, 0, 0);
		modelLeftWheel.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.025F);
		GlStateManager.popMatrix();
		
		GlStateManager.pushMatrix();
		GlStateManager.rotate(180, 1.0F, 0.0F, 0.0F);
		GlStateManager.translate(-0.17, 0.18, 0);
		GlStateManager.rotate(entity.getWheelSpin(), 1, 0, 0);		
		modelRightWheel.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.025F);
		GlStateManager.popMatrix();
		
		GlStateManager.popMatrix();
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityArtilleryCannon entity)
	{
		return entityTexture;
	}
}
