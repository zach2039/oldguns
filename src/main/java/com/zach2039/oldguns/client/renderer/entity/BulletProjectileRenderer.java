package com.zach2039.oldguns.client.renderer.entity;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.client.model.BulletProjectileModel;
import com.zach2039.oldguns.entity.BulletProjectile;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BulletProjectileRenderer extends EntityRenderer<BulletProjectile> {
	
	public static final ResourceLocation TEXTURE = new ResourceLocation(OldGuns.MODID, "textures/entity/bullet_projectile.png");
	private final BulletProjectileModel<BulletProjectile> model;
	
	public BulletProjectileRenderer(final EntityRendererManager manager) {
		super(manager);
		this.model = new BulletProjectileModel<BulletProjectile>();
	}

	@Override
	public void render(BulletProjectile entity, float entityYaw, float partialTicks, @Nonnull MatrixStack matrixStackIn, @Nonnull IRenderTypeBuffer bufferIn, int packedLightIn) {
  
		matrixStackIn.pushPose();	
		
		matrixStackIn.translate(0, -.5f, 0);
		//matrixStackIn.mulPose(new Quaternion(0f, 1f, 0f, entity.yRotO));
		//matrixStackIn.mulPose(new Quaternion(1f, 0f, 1f, entity.xRotO));
		matrixStackIn.scale(entity.getProjectileSize(), entity.getProjectileSize(), entity.getProjectileSize());
		IVertexBuilder vertexconsumer = bufferIn.getBuffer(RenderType.entitySolid(this.getTextureLocation(entity)));
		model.renderToBuffer(matrixStackIn, vertexconsumer, packedLightIn, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f); 
		
		matrixStackIn.popPose();
		
		super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
	
	@Override
	@Nonnull
	public ResourceLocation getTextureLocation(BulletProjectile entity)
	{
		return TEXTURE;
	}
}
