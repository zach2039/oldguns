package zach2039.oldguns.client.renderer.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import zach2039.oldguns.client.model.entity.ModelArtilleryCarriage;
import zach2039.oldguns.client.model.entity.ModelCannonBarrel;
import zach2039.oldguns.client.model.entity.ModelCarriageWheel;
import zach2039.oldguns.common.entity.EntityArtilleryCannon;
import zach2039.oldguns.common.item.tools.ItemGunnersQuadrant;

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
		
		float f3 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
		
		//System.out.printf("spin: %f\n", entity.getWheelSpin());
		
		GlStateManager.pushMatrix();
		GlStateManager.enableLighting();
		GlStateManager.enableNormalize();
		GlStateManager.enableRescaleNormal();
		setupTranslation(x, y, z);
        setupRotation(entity, entityYaw, partialTicks);
		bindEntityTexture(entity);
		
		if (this.renderOutlines)
        {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }
		
		//GlStateManager.translate(x, y, z);
		//GlStateManager.scale(-3.0F, -3.0F, 3.0F);
		//GlStateManager.rotate(180.0F + entityYaw, 0.0F, 1.0F, 0.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(0, -0.35, 0);
		GlStateManager.rotate(f3, 1.0F, 0.0F, 0.0F);
		modelBarrel.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.025F);
		GlStateManager.popMatrix();
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(0, -0.78, 0);
		modelCarriage.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.025F);
		GlStateManager.popMatrix();
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(0.17, -0.18, 0);
		GlStateManager.rotate(180, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(-entity.getWheelSpin(), 1, 0, 0);
		modelLeftWheel.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.025F);
		GlStateManager.popMatrix();
		
		GlStateManager.pushMatrix();
		GlStateManager.rotate(180, 1.0F, 0.0F, 0.0F);
		GlStateManager.translate(-0.17, 0.18, 0);
		GlStateManager.rotate(entity.getWheelSpin(), 1, 0, 0);		
		modelRightWheel.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.025F);
		GlStateManager.popMatrix();
		
		if (this.renderOutlines)
		{
			GlStateManager.disableOutlineMode();
			GlStateManager.disableColorMaterial();
		}
		
		GlStateManager.popMatrix();
		
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	public void setupRotation(EntityArtilleryCannon entityCannon, float p_188311_2_, float p_188311_3_)
	{
		GlStateManager.rotate(180.0F - p_188311_2_, 0.0F, 1.0F, 0.0F);
		float f = (float)entityCannon.getTimeSinceHit() - p_188311_3_;
		float f1 = entityCannon.getDamageTaken() - p_188311_3_;
		
		if (f1 < 0.0F)
		{
			f1 = 0.0F;
		}
		
		if (f > 0.0F)
		{
			GlStateManager.rotate(MathHelper.sin(f) * f * f1 / 10.0F * (float)entityCannon.getForwardDirection(), 1.0F, 0.0F, 0.0F);
		}

        GlStateManager.scale(-3.0F, -3.0F, 3.0F);
    }

    public void setupTranslation(double p_188309_1_, double p_188309_3_, double p_188309_5_)
    {
        GlStateManager.translate((float)p_188309_1_, (float)p_188309_3_, (float)p_188309_5_);
    }
	
	@Override
	protected ResourceLocation getEntityTexture(EntityArtilleryCannon entity)
	{
		return entityTexture;
	}
}
