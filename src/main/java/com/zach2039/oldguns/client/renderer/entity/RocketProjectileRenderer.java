package com.zach2039.oldguns.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.client.model.RocketProjectileModel;
import com.zach2039.oldguns.world.entity.BulletProjectile;
import com.zach2039.oldguns.world.entity.RocketProjectile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class RocketProjectileRenderer extends EntityRenderer<RocketProjectile> implements EntityRendererProvider<RocketProjectile> {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(OldGuns.MODID, "textures/entity/rocket_projectile.png");
	private final RocketProjectileModel<BulletProjectile> model;
	
	public RocketProjectileRenderer(EntityRendererProvider.Context ctx) {
		super(ctx);
		this.model = new RocketProjectileModel<BulletProjectile>(ctx.bakeLayer(RocketProjectileModel.LAYER_LOCATION));
	}

	@Override
	public void render(RocketProjectile entity, float entityYaw, float partialTicks, @Nonnull PoseStack stackIn, @Nonnull MultiBufferSource bufferIn, int packedLightIn) {
		stackIn.pushPose();	
		
		stackIn.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entity.yRotO, entity.getYRot()) - 40.0F));
		stackIn.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entity.xRotO, entity.getXRot()) + 90.0F));
		stackIn.mulPose(Axis.XP.rotationDegrees(45.0F));
		
		stackIn.pushPose();
		stackIn.scale(1.5f, -1.5f, -1.5f);	
		stackIn.translate(0, -1.5, 0);
		VertexConsumer vertexconsumer = bufferIn.getBuffer(RenderType.entitySolid(TEXTURE));
		model.renderToBuffer(stackIn, vertexconsumer, packedLightIn, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f); 
		stackIn.popPose();
		
		stackIn.popPose();
		
		super.render(entity, entityYaw, partialTicks, stackIn, bufferIn, packedLightIn);
	}
	
	@Override
	@Nonnull
	public ResourceLocation getTextureLocation(RocketProjectile entity) {
		return TEXTURE;
	}

	@Override
	public EntityRenderer<RocketProjectile> create(Context context) {
		return new RocketProjectileRenderer(context);
	}	
}
