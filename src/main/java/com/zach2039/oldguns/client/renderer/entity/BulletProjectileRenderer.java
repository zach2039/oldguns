package com.zach2039.oldguns.client.renderer.entity;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.client.model.BulletProjectileModel;
import com.zach2039.oldguns.world.entity.BulletProjectile;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BulletProjectileRenderer extends EntityRenderer<BulletProjectile> implements EntityRendererProvider<BulletProjectile> {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(OldGuns.MODID, "textures/entity/bullet_projectile.png");
	private final BulletProjectileModel<BulletProjectile> model;
	
	public BulletProjectileRenderer(EntityRendererProvider.Context ctx) {
		super(ctx);
		this.model = new BulletProjectileModel<BulletProjectile>(ctx.bakeLayer(BulletProjectileModel.LAYER_LOCATION));
	}

	@Override
	public void render(BulletProjectile entity, float entityYaw, float partialTicks, @Nonnull PoseStack matrixStackIn, @Nonnull MultiBufferSource bufferIn, int packedLightIn) {
  
		matrixStackIn.pushPose();	
		
		matrixStackIn.translate(0, -.5f, 0);
		//matrixStackIn.mulPose(new Quaternion(0f, 1f, 0f, entity.yRotO));
		//matrixStackIn.mulPose(new Quaternion(1f, 0f, 1f, entity.xRotO));
		matrixStackIn.scale(entity.getProjectileSize(), entity.getProjectileSize(), entity.getProjectileSize());
		VertexConsumer vertexconsumer = bufferIn.getBuffer(RenderType.entitySolid(this.getTextureLocation(entity)));
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

	@Override
	public EntityRenderer<BulletProjectile> create(Context context) {
		return new BulletProjectileRenderer(context);
	}	
}
