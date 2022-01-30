package com.zach2039.oldguns.client.model.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.LivingEntity;

public class AbstractArmorModel<A extends LivingEntity> extends HumanoidModel<A> {

	protected static final String HAT = "hat";
	protected static final String HEAD = "head";
	protected static final String BODY = "body";
	protected static final String RIGHT_ARM = "right_arm";
	protected static final String LEFT_ARM = "left_arm";
	protected static final String RIGHT_LEG = "right_leg";
	protected static final String LEFT_LEG = "left_leg";

	public AbstractArmorModel(ModelPart root) {
		super(root, RenderType::armorCutoutNoCull);
	}
	
	protected void setAllInvisible() {
		this.hat.visible = false;
		this.head.visible = false;
		this.body.visible = false;
		this.rightArm.visible = false;
		this.leftArm.visible = false;
		this.rightLeg.visible = false;
		this.leftLeg.visible = false;
	}
}
