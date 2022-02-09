package com.zach2039.oldguns.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.client.model.CongreveRocketStandModel;
import com.zach2039.oldguns.client.model.RocketProjectileModel;
import com.zach2039.oldguns.world.level.block.entity.CongreveRocketStandBlockEntity;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CongreveRocketStandRenderer implements BlockEntityRenderer<CongreveRocketStandBlockEntity> {

	private static final ResourceLocation TEXTURE_STAND = new ResourceLocation(OldGuns.MODID, "textures/block/entity/congreve_rocket_stand.png");
	private static final ResourceLocation TEXTURE_ROCKET = new ResourceLocation(OldGuns.MODID, "textures/entity/rocket_projectile.png");
	private final CongreveRocketStandModel model;
	private final RocketProjectileModel modelRocket;
	
	public CongreveRocketStandRenderer(BlockEntityRendererProvider.Context ctx) {
		this.model = new CongreveRocketStandModel(ctx.bakeLayer(CongreveRocketStandModel.LAYER_LOCATION));
		this.modelRocket = new RocketProjectileModel(ctx.bakeLayer(RocketProjectileModel.LAYER_LOCATION));
	}
	
	@SuppressWarnings("resource")
	@Override
	public void render(CongreveRocketStandBlockEntity blockEntity, float partialTicks, PoseStack stackIn,	MultiBufferSource buffer, int packedLight, int packedOverlay) {
		
		stackIn.pushPose();
		
		stackIn.translate(0.5f, 2.25f, 0.5f);
		model.setupAnim(blockEntity, 0, 0, 0, 0, 0);
		modelRocket.setupAnim(blockEntity, 0, 0, 0, 0, 0);
		stackIn.mulPose(Vector3f.YP.rotationDegrees(-blockEntity.getYawFromFacing()));
		
		stackIn.pushPose();
		stackIn.scale(1.5f, -1.5f, -1.5f);	
		VertexConsumer vertexconsumerStand = buffer.getBuffer(RenderType.entitySolid(TEXTURE_STAND));
		model.renderToBuffer(stackIn, vertexconsumerStand, packedLight, packedOverlay, 1f, 1f, 1f, 1f);
		stackIn.popPose();
		
		stackIn.pushPose();
		stackIn.translate(0.0f, 0.4f, 0.0f);
		stackIn.scale(1.5f, -1.5f, -1.5f);	
		VertexConsumer vertexconsumerRocket = buffer.getBuffer(RenderType.entitySolid(TEXTURE_ROCKET));
		modelRocket.renderToBuffer(stackIn, vertexconsumerRocket, packedLight, packedOverlay, 1f, 1f, 1f, 1f);
		stackIn.popPose();
		
		stackIn.popPose();
	}	
}
