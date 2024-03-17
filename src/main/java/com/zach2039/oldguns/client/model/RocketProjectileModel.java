package com.zach2039.oldguns.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.level.block.entity.CongreveRocketStandBlockEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class RocketProjectileModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(OldGuns.MODID, "rocket_projectile"), "main");
	private final ModelPart rocket;

	public RocketProjectileModel(ModelPart root) {
		this.rocket = root.getChild("rocket");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition rocket = partdefinition.addOrReplaceChild("rocket", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 23.5F));

		PartDefinition warhead = rocket.addOrReplaceChild("warhead", CubeListBuilder.create().texOffs(17, 17).addBox(-1.5F, -1.5F, -15.0F, 3.0F, 3.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.0F, -1.0F, -18.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 5).addBox(-0.5F, -0.5F, -20.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -29.5F));

		PartDefinition rod = rocket.addOrReplaceChild("rod", CubeListBuilder.create().texOffs(17, 1).addBox(-0.5F, -2.5F, -13.0F, 1.0F, 1.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(-0.5F, -2.5F, 2.0F, 1.0F, 1.0F, 15.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.5F, -2.5F, 17.0F, 1.0F, 1.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, -29.5F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	public void setupAnim(CongreveRocketStandBlockEntity blockEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		// bootleg render methods
		float angleRads = (float) Math.toRadians(blockEntity.getShotPitch() - 37);
		rocket.xRot = angleRads;
		rocket.visible = blockEntity.peekAmmoProjectile(0) != null;
	}
	
	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		rocket.render(poseStack, buffer, packedLight, packedOverlay);
	}
}

