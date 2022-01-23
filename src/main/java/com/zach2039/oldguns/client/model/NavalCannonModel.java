package com.zach2039.oldguns.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.level.block.entity.NavalCannonBlockEntity;

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

//Made with Blockbench 4.1.1
//Exported for Minecraft version 1.17 with Mojang mappings
//Paste this class into your mod and generate all required imports


public class NavalCannonModel extends Model {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(OldGuns.MODID, "naval_cannon"), "main");
	private final ModelPart base;

	public NavalCannonModel(ModelPart root) {
		super(RenderType::entityTranslucent);
		this.base = root.getChild("base");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition base = partdefinition.addOrReplaceChild("base", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition barrel = base.addOrReplaceChild("barrel", CubeListBuilder.create().texOffs(0, 36).addBox(-4.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.0167F, -0.2F, -0.0167F));

		PartDefinition BarrelMain = barrel.addOrReplaceChild("BarrelMain", CubeListBuilder.create().texOffs(0, 17).addBox(-2.5167F, 2.95F, -2.8167F, 5.0F, 5.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.4667F, 3.75F, -15.4167F, 3.0F, 3.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0167F, -5.05F, 2.5167F));

		PartDefinition carriage = base.addOrReplaceChild("carriage", CubeListBuilder.create().texOffs(20, 0).addBox(-5.0208F, 0.8226F, -9.612F, 10.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(21, 17).addBox(-5.0208F, 0.8226F, 3.488F, 10.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(34, 13).addBox(-5.0208F, -0.5774F, -4.762F, 10.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(40, 40).addBox(3.0292F, -5.8274F, -2.912F, 2.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(19, 47).addBox(3.0292F, -5.8274F, -5.912F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(48, 25).addBox(3.0292F, -4.8274F, -4.912F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(47, 48).addBox(5.4292F, 0.5726F, -8.262F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(20, 9).addBox(-6.9708F, 1.6226F, -7.312F, 14.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 11).addBox(-6.9708F, 1.6226F, 6.338F, 14.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(20, 0).addBox(-6.3708F, 0.5726F, 5.388F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(47, 36).addBox(-6.3708F, 0.5726F, -8.262F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(40, 25).addBox(5.4292F, 0.5726F, 5.388F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(28, 36).addBox(3.0292F, -2.8274F, 3.988F, 2.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(48, 17).addBox(3.0292F, -0.8274F, 7.938F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(48, 0).addBox(-5.0708F, -0.8274F, 7.938F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(16, 36).addBox(3.0292F, -4.8274F, 0.038F, 2.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 40).addBox(-5.0708F, -2.8274F, 3.988F, 2.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-5.0708F, -4.8274F, 0.038F, 2.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(-5.0708F, -5.8274F, -2.912F, 2.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(35, 50).addBox(-5.0708F, -5.8274F, -5.912F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 49).addBox(-5.0708F, -4.8274F, -4.912F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(9, 46).addBox(-5.0708F, -4.8274F, -8.962F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(25, 45).addBox(3.0292F, -4.8274F, -8.962F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.8149F, 3.7785F));

		PartDefinition CarriageWedge_r1 = carriage.addOrReplaceChild("CarriageWedge_r1", CubeListBuilder.create().texOffs(24, 25).addBox(-1.887F, -2.1202F, -0.2497F, 4.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0661F, -0.4575F, -0.0082F, -0.3927F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		base.render(poseStack, buffer, packedLight, packedOverlay);
	}
	
	public void setupAnim(NavalCannonBlockEntity blockEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float yaw, float pitch) {
		float angleRads = (float) Math.toRadians(blockEntity.getShotPitch());
		base.getChild("barrel").xRot = angleRads;
		
		if (blockEntity.getFiringCooldown() > 1) {
			base.z = (0.1f * blockEntity.getFiringCooldown());
		} else {
			base.z = 0;
		}
	}
}