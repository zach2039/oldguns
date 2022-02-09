package com.zach2039.oldguns.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.level.block.entity.CongreveRocketStandBlockEntity;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class CongreveRocketStandModel extends Model {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(OldGuns.MODID, "congreve_rocket_stand"), "main");
	private final ModelPart base;

	public CongreveRocketStandModel(ModelPart root) {
		super(RenderType::entityTranslucent);
		this.base = root.getChild("base");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(6, 0).addBox(-4.0F, -29.0F, -9.6F, 1.0F, 29.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(12, 0).addBox(3.0F, -29.0F, -9.6F, 1.0F, 29.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition support_r1 = base.addOrReplaceChild("support_r1", CubeListBuilder.create().texOffs(26, 0).addBox(0.1883F, -0.3464F, -0.1443F, 1.0F, 29.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.9F, -23.8F, -9.05F, 0.3645F, -0.4841F, -0.6262F));

		PartDefinition support_r2 = base.addOrReplaceChild("support_r2", CubeListBuilder.create().texOffs(22, 0).addBox(-1.1883F, -0.3464F, -0.1443F, 1.0F, 29.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.5F, -24.0F, -9.0F, -0.4913F, -0.3822F, 0.5112F));

		PartDefinition support_r3 = base.addOrReplaceChild("support_r3", CubeListBuilder.create().texOffs(18, 0).addBox(-1.1883F, -0.3464F, -0.1443F, 1.0F, 29.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.9F, -23.8F, -9.05F, 0.3645F, 0.4841F, 0.6262F));

		PartDefinition support_r4 = base.addOrReplaceChild("support_r4", CubeListBuilder.create().texOffs(29, 29).addBox(0.1883F, -0.3464F, -0.1443F, 1.0F, 29.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.5F, -24.0F, -9.0F, -0.4913F, 0.3822F, -0.5112F));

		PartDefinition launcher = base.addOrReplaceChild("launcher", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 23.5F));

		PartDefinition launcher_r1 = launcher.addOrReplaceChild("launcher_r1", CubeListBuilder.create().texOffs(30, 0).addBox(-3.5F, -24.5F, -1.5F, 1.0F, 16.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(33, 20).addBox(-2.5F, -24.5F, -1.5F, 4.0F, 16.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(14, 30).addBox(1.5F, -24.5F, -1.5F, 1.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -13.75F, -18.0F, 0.9163F, 0.0F, 0.0F));

		PartDefinition launcher_r2 = launcher.addOrReplaceChild("launcher_r2", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -18.5F, -0.5F, 2.0F, 41.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -13.5F, -18.0F, 0.9163F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
	
	public void setupAnim(CongreveRocketStandBlockEntity blockEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float angleRads = (float) Math.toRadians(blockEntity.getShotPitch());
		base.getChild("launcher").xRot = angleRads;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		base.render(poseStack, buffer, packedLight, packedOverlay);
	}
}
