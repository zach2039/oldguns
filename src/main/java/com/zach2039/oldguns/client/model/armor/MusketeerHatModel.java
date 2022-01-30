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
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

public class MusketeerHatModel<A extends LivingEntity> extends AbstractArmorModel<A> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(OldGuns.MODID, "musketeer_hat"), "main");

	public MusketeerHatModel(ModelPart root) {
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

		PartDefinition cap = hat.addOrReplaceChild("cap", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -9F, 0.0F, 0.0F, 0.2618F, -0.1309F));

		PartDefinition rim = cap.addOrReplaceChild("rim", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 1.5F, -8.0F, 6.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(20, 18).addBox(-5.0F, 1.5F, -7.0F, 2.0F, 1.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(32, 33).addBox(5.45F, -1.65F, -5.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 32).addBox(-6.0F, 1.5F, -6.0F, 1.0F, 1.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(38, 14).addBox(-7.0F, 1.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition rim_bent_r1 = rim.addOrReplaceChild("rim_bent_r1", CubeListBuilder.create().texOffs(38, 23).addBox(5.4932F, -0.8674F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 17).addBox(1.6917F, 1.5759F, -7.0F, 3.0F, 1.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.395F, 0.7449F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition rim_bent_r2 = rim.addOrReplaceChild("rim_bent_r2", CubeListBuilder.create().texOffs(28, 0).addBox(4.1274F, -3.5606F, -6.0F, 1.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.395F, 0.7449F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition top = cap.addOrReplaceChild("top", CubeListBuilder.create().texOffs(42, 4).addBox(-3.6F, -1.525F, -4.05F, 7.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(14, 33).addBox(-3.6F, -0.85F, -3.6F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(42, 0).addBox(-3.6F, -1.525F, 3.15F, 7.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(19, 41).addBox(-4.15F, -1.625F, -3.6F, 1.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(3.05F, -1.625F, -3.6F, 1.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition feather = cap.addOrReplaceChild("feather", CubeListBuilder.create(), PartPose.offset(-0.8215F, -4.7907F, 0.625F));

		PartDefinition pin_r1 = feather.addOrReplaceChild("pin_r1", CubeListBuilder.create().texOffs(0, 4).addBox(0.0F, -0.5F, 0.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.8215F, 2.7907F, -1.125F, 0.0F, 0.0F, -0.3927F));

		PartDefinition feather_r1 = feather.addOrReplaceChild("feather_r1", CubeListBuilder.create().texOffs(0, 17).addBox(-2.25F, 0.0F, -2.125F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.125F, -0.3F, -0.2618F, 0.0F, 0.0F));

		PartDefinition feather_r2 = feather.addOrReplaceChild("feather_r2", CubeListBuilder.create().texOffs(0, 17).addBox(-2.25F, 0.0F, -2.125F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.125F, -0.3F, 0.3927F, 0.0F, 0.0F));

		PartDefinition feather_r3 = feather.addOrReplaceChild("feather_r3", CubeListBuilder.create().texOffs(5, 0).addBox(-1.75F, 0.0F, -2.125F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2522F, 0.7155F, -0.3F, -0.4363F, 0.0F, 0.0F));

		PartDefinition feather_r4 = feather.addOrReplaceChild("feather_r4", CubeListBuilder.create().texOffs(5, 0).addBox(-1.75F, 0.0F, -2.125F, 3.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.2522F, 0.7155F, -0.3F, 0.3927F, 0.0F, 0.0F));

		PartDefinition feather_r5 = feather.addOrReplaceChild("feather_r5", CubeListBuilder.create().texOffs(0, 6).addBox(-4.4762F, -1.4715F, -0.125F, 3.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.5524F, 4.8857F, -0.3F, 0.0F, 0.0F, 0.8727F));

		PartDefinition feather_r6 = feather.addOrReplaceChild("feather_r6", CubeListBuilder.create().texOffs(0, 15).addBox(-2.5F, -0.5F, 0.0F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9449F, 0.1858F, -0.425F, 0.0F, 0.0F, -0.0873F));

		PartDefinition feather_r7 = feather.addOrReplaceChild("feather_r7", CubeListBuilder.create().texOffs(8, 14).addBox(-5.4762F, -1.4715F, -0.125F, 4.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0524F, 2.8857F, -0.3F, 0.0F, 0.0F, 0.3927F));

		PartDefinition feather_r8 = feather.addOrReplaceChild("feather_r8", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0.0F, -2.125F, 1.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.8419F, 1.1687F, -0.3F, -0.413F, -0.1451F, 0.4668F));

		PartDefinition feather_r9 = feather.addOrReplaceChild("feather_r9", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0.0F, -2.125F, 1.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.3419F, 1.4187F, -0.3F, 0.0F, 0.0F, 0.7854F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.hat.render(poseStack, buffer, packedLight, packedOverlay);
	}

}
