package com.zach2039.oldguns.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.level.block.entity.MediumNavalCannonBlockEntity;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

/**
 * Ship Cannon Model by
 * @author xiraxis9
 * 
 * With minor edits by
 * @author zach2039
 * 
 * Made with Blockbench 4.1.1
 * Exported for Minecraft version 1.17 with Mojang mappings
 */
public class NavalCannonModel extends Model {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(OldGuns.MODID, "ship_cannon"), "main");

	private final ModelPart carriage;

	public NavalCannonModel(ModelPart root) {
		super(RenderType::entityTranslucent);
		this.carriage = root.getChild("carriage");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition carriage = partdefinition.addOrReplaceChild("carriage", CubeListBuilder.create().texOffs(0, 24).addBox(-3.0F, 0.9667F, 6.5333F, 6.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(35, 21).addBox(-7.5F, 3.9667F, -5.4667F, 15.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(35, 19).addBox(-7.5F, 3.9667F, 7.5333F, 15.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 31).addBox(-5.0F, 1.9667F, -8.4667F, 10.0F, 2.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(40, 31).addBox(3.0F, -0.0333F, -7.4667F, 2.0F, 2.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(18, 54).addBox(3.0F, -1.0333F, -6.4667F, 2.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(61, 13).addBox(3.0F, -2.0333F, -6.4667F, 2.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 11).addBox(3.0F, -3.0333F, -6.4667F, 2.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(35, 10).addBox(3.0F, -4.0333F, -5.4667F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(35, 0).addBox(-5.0F, -0.0333F, -7.4667F, 2.0F, 2.0F, 17.0F, new CubeDeformation(0.0F))
		.texOffs(0, 53).addBox(-5.0F, -1.0333F, -6.4667F, 2.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(56, 0).addBox(-5.0F, -2.0333F, -6.4667F, 2.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-5.0F, -3.0333F, -6.4667F, 2.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(35, 4).addBox(-5.0F, -4.0333F, -5.4667F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(35, 0).addBox(-3.0F, -0.0333F, -6.4667F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 18.0333F, 2.4667F));

		PartDefinition wheels = carriage.addOrReplaceChild("wheels", CubeListBuilder.create().texOffs(14, 16).addBox(5.0F, -3.0F, 4.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(14, 0).addBox(5.0F, -2.5F, 4.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 16).addBox(5.0F, -3.0F, 17.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 11).addBox(5.0F, -2.5F, 17.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(14, 5).addBox(-7.0F, -3.0F, 4.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 5).addBox(-7.0F, -2.5F, 4.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(14, 11).addBox(-7.0F, -3.0F, 17.5F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-7.0F, -2.5F, 17.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.9667F, -10.4667F));

		PartDefinition cannon = carriage.addOrReplaceChild("cannon", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -2.1855F, -13.5357F, 4.0F, 4.0F, 27.0F, new CubeDeformation(0.0F))
		.texOffs(0, 31).addBox(-2.5F, -2.6855F, -12.5357F, 5.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(48, 50).addBox(-2.5F, -2.6855F, 0.4643F, 5.0F, 5.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 22).addBox(-5.5F, -0.6855F, -0.5357F, 11.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(6, 16).addBox(-0.5F, -0.6855F, 13.4643F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.8479F, -2.931F));

		PartDefinition cannon_righthandle_r1 = cannon.addOrReplaceChild("cannon_righthandle_r1", CubeListBuilder.create().texOffs(11, 35).addBox(-1.5F, -4.5F, 5.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.1855F, -0.0357F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cannon_lefthandle_r1 = cannon.addOrReplaceChild("cannon_lefthandle_r1", CubeListBuilder.create().texOffs(0, 38).addBox(0.5F, -4.5F, 5.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.1855F, -0.0357F, 0.0F, 0.0F, 0.3927F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		carriage.render(poseStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	
	public void setupAnim(MediumNavalCannonBlockEntity blockEntity, float limbSwing, float limbSwingAmount, float ageInTicks, float yaw, float pitch) {
		float angleRads = (float) Math.toRadians(blockEntity.getShotPitch());
		carriage.getChild("cannon").xRot = angleRads;
		
		if (blockEntity.getFiringCooldown() > 1) {
			carriage.z = (0.1f * blockEntity.getFiringCooldown());
		} else {
			carriage.z = 0;
		}
	}
}