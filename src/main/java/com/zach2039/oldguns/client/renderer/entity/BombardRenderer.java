package com.zach2039.oldguns.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
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

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class BombardRenderer extends EntityRenderer<Bombard> implements EntityRendererProvider<Bombard> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(OldGuns.MODID, "textures/entity/bombard.png");
	private final BombardModel model;

	public BombardRenderer(EntityRendererProvider.Context ctx) {
		super(ctx);
		this.model = new BombardModel(ctx.bakeLayer(BombardModel.LAYER_LOCATION));
	}
	
	@SuppressWarnings("resource")
	@Override
	public void render(Bombard entity, float entityYaw, float partialTicks, @Nonnull PoseStack matrixStackIn, @Nonnull MultiBufferSource bufferIn, int packedLightIn) {
		
		matrixStackIn.pushPose();
		
		matrixStackIn.translate(0.0f, 0.5f, 0.0f);
		model.setupAnim(entity, 0, 0, 0, 0, 0);
		matrixStackIn.mulPose(Axis.YP.rotationDegrees(-entity.getShotYaw()));
		
		matrixStackIn.pushPose();
		matrixStackIn.scale(1.5f, -1.5f, -1.5f);	
		VertexConsumer vertexconsumerStand = bufferIn.getBuffer(RenderType.entitySolid(this.getTextureLocation(entity)));
		model.renderToBuffer(matrixStackIn, vertexconsumerStand, packedLightIn, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
		matrixStackIn.popPose();
		
		matrixStackIn.popPose();
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
