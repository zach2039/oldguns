package com.zach2039.oldguns.client.renderer.entity;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.client.model.BombardModel;
import com.zach2039.oldguns.world.entity.Bombard;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BombardRenderer extends EntityRenderer<Bombard> implements EntityRendererProvider<Bombard> {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(OldGuns.MODID, "textures/entity/bombard.png");
	private final BombardModel model;
	
	public BombardRenderer(EntityRendererProvider.Context ctx) {
		super(ctx);
		this.model = new BombardModel(ctx.bakeLayer(BombardModel.LAYER_LOCATION));
	}

	@Override
	public void render(Bombard entity, float entityYaw, float partialTicks, @Nonnull PoseStack matrixStackIn, @Nonnull MultiBufferSource bufferIn, int packedLightIn) {
  
		matrixStackIn.pushPose();	
		
		
		matrixStackIn.translate(0, 0.375f, 0);
		model.setupAnim(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
		matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F - entityYaw));
		matrixStackIn.scale(-1.5f, -1.5f, 1.5f);
		
		VertexConsumer vertexconsumer = bufferIn.getBuffer(RenderType.entitySolid(this.getTextureLocation(entity)));
		model.renderToBuffer(matrixStackIn, vertexconsumer, packedLightIn, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f); 
		
		matrixStackIn.popPose();
		
		super.render(entity, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
	
	@Override
	@Nonnull
	public ResourceLocation getTextureLocation(Bombard entity)
	{
		return TEXTURE;
	}

	@Override
	public EntityRenderer<Bombard> create(Context context) {
		return new BombardRenderer(context);
	}	
}
