package com.zach2039.oldguns.client.model.armor;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.zach2039.oldguns.OldGuns;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class HorsemansPotHelmModel<A extends LivingEntity> extends AbstractArmorModel<A> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(OldGuns.MODID, "horsemans_pot_helm"), "main");

	public HorsemansPotHelmModel(ModelPart root) {
		super(root);
		
		setAllInvisible();
		
		this.hat.visible = true;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		
		// Unused parts
		partdefinition.addOrReplaceChild(HEAD, CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild(BODY, CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild(LEFT_ARM, CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild(RIGHT_ARM, CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild(LEFT_LEG, CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild(RIGHT_LEG, CubeListBuilder.create(), PartPose.ZERO);

		PartDefinition hat = partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cap = hat.addOrReplaceChild("cap", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -2.0F, -5.0F, 10.0F, 2.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 12).addBox(-4.5F, -4.0F, -4.5F, 9.0F, 2.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(0, 23).addBox(-4.0F, -5.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(27, 12).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, 0.0F, -3.1416F, 0.0F, 3.1416F));

		PartDefinition side_r1 = cap.addOrReplaceChild("side_r1", CubeListBuilder.create().texOffs(24, 37).addBox(-0.1857F, -1.0F, -0.9081F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -0.75F, 0.0F, 0.0F, 1.3963F, 0.6545F));

		PartDefinition side_r2 = cap.addOrReplaceChild("side_r2", CubeListBuilder.create().texOffs(35, 35).addBox(-0.2189F, -0.8619F, -1.0142F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.75F, 5.0F, 2.3858F, 0.1802F, 3.0175F));

		PartDefinition side_r3 = cap.addOrReplaceChild("side_r3", CubeListBuilder.create().texOffs(24, 33).addBox(-4.7858F, -0.8618F, -1.0152F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.75F, 5.0F, 2.388F, -0.1629F, -3.0194F));

		PartDefinition side_r4 = cap.addOrReplaceChild("side_r4", CubeListBuilder.create().texOffs(12, 36).addBox(-0.0872F, -1.0F, -0.9254F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -0.75F, 0.0F, -3.1416F, -1.3963F, 2.4871F));

		PartDefinition side_r5 = cap.addOrReplaceChild("side_r5", CubeListBuilder.create().texOffs(0, 36).addBox(-4.8143F, -1.0F, -0.9081F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -0.75F, 0.0F, 0.0F, -1.3963F, -0.6545F));

		PartDefinition side_r6 = cap.addOrReplaceChild("side_r6", CubeListBuilder.create().texOffs(36, 19).addBox(-4.9128F, -1.0F, -0.9254F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -0.75F, 0.0F, -3.1416F, 1.3963F, -2.4871F));

		PartDefinition cap_r1 = cap.addOrReplaceChild("cap_r1", CubeListBuilder.create().texOffs(24, 24).addBox(-2.9296F, -0.5087F, -2.9296F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.45F, 3.5F, -0.1231F, -0.7816F, 0.0869F));

		PartDefinition tail = cap.addOrReplaceChild("tail", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -1.1122F, -4.8194F, 0.0873F, 0.0F, 0.0F));

		PartDefinition tail_r1 = tail.addOrReplaceChild("tail_r1", CubeListBuilder.create().texOffs(30, 4).addBox(-0.153F, -3.1975F, -1.2982F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.4122F, -4.5306F, -1.0971F, -0.0803F, 0.1551F));

		PartDefinition tail_r2 = tail.addOrReplaceChild("tail_r2", CubeListBuilder.create().texOffs(30, 0).addBox(-5.847F, -3.1975F, -1.2982F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.4122F, -4.5306F, -1.0971F, 0.0803F, -0.1551F));

		PartDefinition tail_r3 = tail.addOrReplaceChild("tail_r3", CubeListBuilder.create().texOffs(40, 8).addBox(-4.8559F, -0.7706F, -1.2481F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0122F, -1.2806F, -0.7057F, 0.1334F, -0.1129F));

		PartDefinition tail_r4 = tail.addOrReplaceChild("tail_r4", CubeListBuilder.create().texOffs(12, 39).addBox(-0.1441F, -0.7706F, -1.2481F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0122F, -1.2806F, -0.7057F, -0.1334F, 0.1129F));

		PartDefinition tail_r5 = tail.addOrReplaceChild("tail_r5", CubeListBuilder.create().texOffs(0, 39).addBox(-5.159F, -1.0F, -0.9689F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 0.2622F, -0.0306F, -0.6619F, 0.1382F, -0.1069F));

		PartDefinition tail_r6 = tail.addOrReplaceChild("tail_r6", CubeListBuilder.create().texOffs(36, 38).addBox(0.159F, -1.0F, -0.9689F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 0.2622F, -0.0306F, -0.6619F, -0.1382F, 0.1069F));

		PartDefinition tail_r7 = tail.addOrReplaceChild("tail_r7", CubeListBuilder.create().texOffs(24, 40).addBox(-5.159F, -1.0F, -0.9689F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, 1.5122F, -0.7806F, -0.5566F, 0.1486F, -0.0919F));

		PartDefinition tail_r8 = tail.addOrReplaceChild("tail_r8", CubeListBuilder.create().texOffs(36, 41).addBox(0.159F, -1.0F, -0.9689F, 5.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, 1.5122F, -0.7806F, -0.5566F, -0.1486F, 0.0919F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.hat.render(poseStack, buffer, packedLight, packedOverlay);
	}

}
