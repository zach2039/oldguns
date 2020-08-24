package zach2039.oldguns.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

//Made with Blockbench 3.5.4
//Exported for Minecraft version 1.12
//Paste this class into your mod and generate all required imports


public class ModelCarriageWheel extends ModelBase {
	private final ModelRenderer WheelRight;
	private final ModelRenderer WheelSpokes0;
	private final ModelRenderer WheelSpokes1;
	private final ModelRenderer Wheel1;
	private final ModelRenderer Wheel2;
	private final ModelRenderer Wheel3;
	private final ModelRenderer Wheel0;

	public ModelCarriageWheel() {
		textureWidth = 128;
		textureHeight = 128;

		WheelRight = new ModelRenderer(this);
		WheelRight.setRotationPoint(0.0F, 0.0F, 0.0F);
		WheelRight.cubeList.add(new ModelBox(WheelRight, 10, 23, -0.2445F, -1.4869F, -1.4705F, 2, 3, 3, 0.0F, false));

		WheelSpokes0 = new ModelRenderer(this);
		WheelSpokes0.setRotationPoint(0.3875F, 2.2631F, 0.6295F);
		WheelRight.addChild(WheelSpokes0);
		WheelSpokes0.cubeList.add(new ModelBox(WheelSpokes0, 45, 52, -0.382F, -2.75F, -7.1F, 1, 1, 13, 0.0F, false));
		WheelSpokes0.cubeList.add(new ModelBox(WheelSpokes0, 12, 4, -0.382F, -8.75F, -1.1F, 1, 13, 1, 0.0F, false));

		WheelSpokes1 = new ModelRenderer(this);
		WheelSpokes1.setRotationPoint(-0.1125F, 2.7631F, 0.1295F);
		WheelRight.addChild(WheelSpokes1);
		setRotationAngle(WheelSpokes1, -0.7854F, 0.0F, 0.0F);
		WheelSpokes1.cubeList.add(new ModelBox(WheelSpokes1, 17, 52, 0.118F, -2.3738F, -8.5153F, 1, 1, 13, 0.0F, false));
		WheelSpokes1.cubeList.add(new ModelBox(WheelSpokes1, 8, 4, 0.118F, -8.3738F, -2.5153F, 1, 13, 1, 0.0F, false));

		Wheel1 = new ModelRenderer(this);
		Wheel1.setRotationPoint(-0.6125F, 2.7631F, 0.1295F);
		WheelRight.addChild(Wheel1);
		setRotationAngle(Wheel1, -0.384F, 0.0F, 0.0F);
		Wheel1.cubeList.add(new ModelBox(Wheel1, 48, 14, -0.382F, -4.0123F, -8.6229F, 2, 3, 1, 0.0F, false));
		Wheel1.cubeList.add(new ModelBox(Wheel1, 0, 33, -0.382F, 3.9877F, -2.6229F, 2, 1, 3, 0.0F, false));
		Wheel1.cubeList.add(new ModelBox(Wheel1, 31, 13, -0.382F, -10.0123F, -2.6229F, 2, 1, 3, 0.0F, false));
		Wheel1.cubeList.add(new ModelBox(Wheel1, 48, 8, -0.382F, -4.0123F, 5.3771F, 2, 3, 1, 0.0F, false));

		Wheel2 = new ModelRenderer(this);
		Wheel2.setRotationPoint(-0.6625F, 2.7631F, 0.1295F);
		WheelRight.addChild(Wheel2);
		setRotationAngle(Wheel2, -0.7854F, 0.0F, 0.0F);
		Wheel2.cubeList.add(new ModelBox(Wheel2, 48, 29, -0.382F, -3.3738F, -9.5153F, 2, 3, 1, 0.0F, false));
		Wheel2.cubeList.add(new ModelBox(Wheel2, 24, 33, -0.382F, 4.6262F, -3.5153F, 2, 1, 3, 0.0F, false));
		Wheel2.cubeList.add(new ModelBox(Wheel2, 10, 33, -0.382F, -9.3738F, -3.5153F, 2, 1, 3, 0.0F, false));
		Wheel2.cubeList.add(new ModelBox(Wheel2, 48, 18, -0.382F, -3.3738F, 4.4847F, 2, 3, 1, 0.0F, false));

		Wheel3 = new ModelRenderer(this);
		Wheel3.setRotationPoint(6.7875F, 0.1631F, 0.1295F);
		WheelRight.addChild(Wheel3);
		setRotationAngle(Wheel3, -1.1694F, 0.0F, 0.0F);
		Wheel3.cubeList.add(new ModelBox(Wheel3, 48, 4, -7.782F, -1.4666F, -7.6771F, 2, 3, 1, 0.0F, false));
		Wheel3.cubeList.add(new ModelBox(Wheel3, 31, 9, -7.782F, 6.5334F, -1.6771F, 2, 1, 3, 0.0F, false));
		Wheel3.cubeList.add(new ModelBox(Wheel3, 31, 5, -7.782F, -7.4666F, -1.6771F, 2, 1, 3, 0.0F, false));
		Wheel3.cubeList.add(new ModelBox(Wheel3, 18, 46, -7.782F, -1.4666F, 6.3229F, 2, 3, 1, 0.0F, false));

		Wheel0 = new ModelRenderer(this);
		Wheel0.setRotationPoint(-0.6125F, 3.2631F, 0.6295F);
		WheelRight.addChild(Wheel0);
		Wheel0.cubeList.add(new ModelBox(Wheel0, 48, 33, -0.357F, -4.75F, -8.1F, 2, 3, 1, 0.0F, false));
		Wheel0.cubeList.add(new ModelBox(Wheel0, 48, 37, -0.357F, -4.75F, 5.9F, 2, 3, 1, 0.0F, false));
		Wheel0.cubeList.add(new ModelBox(Wheel0, 0, 37, -0.357F, -10.75F, -2.1F, 2, 1, 3, 0.0F, false));
		Wheel0.cubeList.add(new ModelBox(Wheel0, 31, 35, -0.357F, 3.25F, -2.1F, 2, 1, 3, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		WheelRight.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}