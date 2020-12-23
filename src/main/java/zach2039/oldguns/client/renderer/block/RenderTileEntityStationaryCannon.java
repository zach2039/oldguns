package zach2039.oldguns.client.renderer.block;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import zach2039.oldguns.client.model.tile.ModelStationaryCannonBarrel;
import zach2039.oldguns.client.model.tile.ModelStationaryCannonBase;
import zach2039.oldguns.common.tile.TileEntityStationaryCannon;

public class RenderTileEntityStationaryCannon extends TileEntitySpecialRenderer<TileEntityStationaryCannon> {

	private ResourceLocation tileTexture;
	private ModelStationaryCannonBase modelStationaryCannonBase = new ModelStationaryCannonBase();
	private ModelStationaryCannonBarrel modelStationaryCannonBarrel = new ModelStationaryCannonBarrel();
	
	public RenderTileEntityStationaryCannon(ResourceLocation texture)
	{
		this.tileTexture = texture;
	}
	
	@Override
	public void render(TileEntityStationaryCannon te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		
		float gunPitch = te.getBarrelPitch();
		float gunYaw = te.getBarrelYaw();
		
		GlStateManager.pushMatrix();
		GlStateManager.enableLighting();
		GlStateManager.enableNormalize();
		GlStateManager.enableRescaleNormal();
		setupTranslation(te, x + 0.5D, y + 1D, z + 0.5D);
		setupRotation(te, -gunYaw, partialTicks);
		bindTexture(this.tileTexture);

		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		//Render Base
		GlStateManager.pushMatrix();
			GL11.glTranslated(0D, -0.70D, 0D);
			
			if (te.getFiringCooldown() > 0) {
				GlStateManager.translate(0f, 0f, 0.05f * te.getFiringCooldown());
			}
			this.modelStationaryCannonBase.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0620F);
			//GL11.glTranslated(0D, 0.75D, 0D);
		GlStateManager.popMatrix();
		
		//Render Barrel
		GlStateManager.pushMatrix();
			GL11.glTranslated(0D, 0.43D, 0D);
			if (te.getFiringCooldown() > 0) {
				GlStateManager.translate(0f, 0f, 0.05f * te.getFiringCooldown());
			}
			GL11.glRotated(gunPitch, 1D, 0D, 0D);	
			this.modelStationaryCannonBarrel.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0620F);
		GlStateManager.popMatrix();
		
		GlStateManager.popMatrix();	
	}
	
	public void setupRotation(TileEntityStationaryCannon te, float p_188311_2_, float p_188311_3_)
	{
		GlStateManager.rotate(180f + p_188311_2_, 0.0F, 1.0F, 0.0F);		
		
        GlStateManager.scale(-1.0F, -1.0F, 1.0F);
    }
	
    public void setupTranslation(TileEntityStationaryCannon te, double p_188309_1_, double p_188309_3_, double p_188309_5_)
    {
        GlStateManager.translate((float)p_188309_1_, (float)p_188309_3_, (float)p_188309_5_); 
    }
}

