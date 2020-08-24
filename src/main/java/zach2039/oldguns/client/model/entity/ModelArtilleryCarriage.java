package zach2039.oldguns.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

//Made with Blockbench 3.5.4
//Exported for Minecraft version 1.12
//Paste this class into your mod and generate all required imports

public class ModelArtilleryCarriage extends ModelBase {
	private final ModelRenderer Carriage;
	private final ModelRenderer CarriageArms2;
	private final ModelRenderer CarriageArms;
	private final ModelRenderer CarriageArms1;
	private final ModelRenderer CarriageArms0;

	public ModelArtilleryCarriage() {
		textureWidth = 128;
		textureHeight = 128;

		Carriage = new ModelRenderer(this);
		Carriage.setRotationPoint(0.0F, 24.0F, 0.0F);
		Carriage.cubeList.add(new ModelBox(Carriage, 24, 0, -8.0F, -1.0F, -1.0F, 16, 2, 2, 0.0F, false));

		CarriageArms2 = new ModelRenderer(this);
		CarriageArms2.setRotationPoint(0.0F, 7.8461F, 27.6239F);
		Carriage.addChild(CarriageArms2);
		CarriageArms2.cubeList.add(new ModelBox(CarriageArms2, 63, 20, 2.5F, -3.4923F, -7.6622F, 2, 3, 9, 0.0F, false));
		CarriageArms2.cubeList.add(new ModelBox(CarriageArms2, 63, 0, -4.5F, -3.4923F, -7.6622F, 2, 3, 9, 0.0F, false));

		CarriageArms = new ModelRenderer(this);
		CarriageArms.setRotationPoint(-0.75F, 1.25F, 2.0F);
		Carriage.addChild(CarriageArms);
		setRotationAngle(CarriageArms, -0.1745F, 0.0F, 0.0F);
		CarriageArms.cubeList.add(new ModelBox(CarriageArms, 27, 29, 3.25F, -7.2082F, -6.4775F, 2, 6, 17, 0.0F, false));
		CarriageArms.cubeList.add(new ModelBox(CarriageArms, 27, 6, -3.75F, -7.2082F, -6.4775F, 2, 6, 17, 0.0F, false));

		CarriageArms1 = new ModelRenderer(this);
		CarriageArms1.setRotationPoint(0.75F, 2.5961F, 23.6239F);
		CarriageArms.addChild(CarriageArms1);
		setRotationAngle(CarriageArms1, -0.2269F, 0.0F, 0.0F);
		CarriageArms1.cubeList.add(new ModelBox(CarriageArms1, 0, 23, 2.4F, -3.6904F, -19.9635F, 2, 3, 20, 0.0F, false));
		CarriageArms1.cubeList.add(new ModelBox(CarriageArms1, 0, 0, -4.4F, -3.6904F, -19.9635F, 2, 3, 20, 0.0F, false));

		CarriageArms0 = new ModelRenderer(this);
		CarriageArms0.setRotationPoint(0.75F, 0.6836F, 17.1941F);
		CarriageArms.addChild(CarriageArms0);
		setRotationAngle(CarriageArms0, -0.5236F, 0.0F, 0.0F);
		CarriageArms0.cubeList.add(new ModelBox(CarriageArms0, 60, 43, 2.6F, -3.4923F, -9.6622F, 2, 3, 11, 0.0F, false));
		CarriageArms0.cubeList.add(new ModelBox(CarriageArms0, 0, 61, -4.6F, -3.4923F, -9.6622F, 2, 3, 11, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Carriage.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}