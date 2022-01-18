package com.zach2039.oldguns.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.zach2039.oldguns.entity.Bombard;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;

public class BombardModel extends EntityModel<Bombard> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "bombard"), "main");
	private final ModelPart base;
	private final ModelPart leftWheel;
	private final ModelPart rightWheel;

	public BombardModel(ModelPart root) {
		this.base = root.getChild("base");
		this.leftWheel = root.getChild("leftWheel");
		this.rightWheel = root.getChild("rightWheel");
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		base.render(poseStack, buffer, packedLight, packedOverlay);
		leftWheel.render(poseStack, buffer, packedLight, packedOverlay);
		rightWheel.render(poseStack, buffer, packedLight, packedOverlay);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(22, 2).addBox(1.0F, -2.0F, -5.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-3.0F, 0.0F, -5.0F, 6.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(0, 12).addBox(-2.0F, -3.0F, 3.0F, 4.0F, 3.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(18, 15).addBox(-3.0F, -2.0F, -5.0F, 2.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
				.texOffs(28, 27).addBox(-5.25F, -1.0F, -1.0F, 10.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition barrel = base.addOrReplaceChild("barrel", CubeListBuilder.create().texOffs(32, 14).addBox(-2.0F, -4.0F, -5.5F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(38, 31).addBox(-1.5F, -3.5F, 0.5F, 3.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -0.5F));

		PartDefinition leftWheel = partdefinition.addOrReplaceChild("leftWheel", CubeListBuilder.create().texOffs(18, 27).addBox(-1.5F, -2.0F, -4.0F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(0, 37).addBox(-1.5F, -3.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(36, 0).addBox(-1.5F, 2.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 17).addBox(-1.5F, 3.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 12).addBox(-1.5F, -4.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.5F, 0.0F, 0.0F));

		PartDefinition rightWheel = partdefinition.addOrReplaceChild("rightWheel", CubeListBuilder.create().texOffs(0, 25).addBox(-1.5F, -2.0F, -4.0F, 1.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
				.texOffs(30, 33).addBox(-1.5F, -3.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(10, 25).addBox(-1.5F, 2.0F, -3.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(0, 5).addBox(-1.5F, 3.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-1.5F, -4.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Bombard entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		animateWheel(entity, this.leftWheel, false);
		animateWheel(entity, this.rightWheel, true);
	}

	private void animateWheel(Bombard entity, ModelPart part, boolean rightSide) {
		float spin = 0;
		
		if (entity.getDeltaMovement().lengthSqr() > 0.002d) {
			spin = (float) Math.max(entity.getDeltaMovement().lengthSqr() * 1f, 0.000000001d);
		}
		
		part.xRot += spin;
	}
}
