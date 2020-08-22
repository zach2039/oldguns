package zach2039.oldguns.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

//Made with Blockbench 3.5.4
//Exported for Minecraft version 1.12
//Paste this class into your mod and generate all required imports


public class ModelEntityArtilleryCannon extends ModelBase {
	private final ModelRenderer Barrel;
	private final ModelRenderer BarrelMain;
	private final ModelRenderer Carriage;
	private final ModelRenderer WheelRight;
	private final ModelRenderer WheelSpokes0;
	private final ModelRenderer WheelSpokes1;
	private final ModelRenderer Wheel1;
	private final ModelRenderer Wheel2;
	private final ModelRenderer Wheel3;
	private final ModelRenderer Wheel0;
	private final ModelRenderer WheelLeft;
	private final ModelRenderer WheelSpokes2;
	private final ModelRenderer WheelSpokes3;
	private final ModelRenderer Wheel4;
	private final ModelRenderer Wheel5;
	private final ModelRenderer Wheel6;
	private final ModelRenderer Wheel7;
	private final ModelRenderer CarriageArms2;
	private final ModelRenderer CarriageArms;
	private final ModelRenderer CarriageArms1;
	private final ModelRenderer CarriageArms0;

	public ModelEntityArtilleryCannon() {
		textureWidth = 128;
		textureHeight = 128;

		Barrel = new ModelRenderer(this);
		Barrel.setRotationPoint(0.0F, 10.3F, -0.5F);
		Barrel.cubeList.add(new ModelBox(Barrel, 0, 0, -3.5F, -1.05F, -1.0F, 7, 2, 2, 0.0F, false));

		BarrelMain = new ModelRenderer(this);
		BarrelMain.setRotationPoint(0.0F, 2.7F, -0.75F);
		Barrel.addChild(BarrelMain);
		BarrelMain.cubeList.add(new ModelBox(BarrelMain, 32, 52, -2.0F, -4.75F, 0.25F, 4, 4, 9, 0.0F, false));
		BarrelMain.cubeList.add(new ModelBox(BarrelMain, 0, 46, -1.5F, -4.25F, -10.75F, 3, 3, 12, 0.0F, false));

		Carriage = new ModelRenderer(this);
		Carriage.setRotationPoint(0.0F, 16.35F, -0.5F);
		Carriage.cubeList.add(new ModelBox(Carriage, 24, 0, -8.032F, -1.1F, -1.0F, 16, 2, 2, 0.0F, false));
		Carriage.cubeList.add(new ModelBox(Carriage, 24, 0, -8.032F, -1.1F, -1.0F, 16, 2, 2, 0.0F, false));

		WheelRight = new ModelRenderer(this);
		WheelRight.setRotationPoint(-6.4518F, -0.1053F, 0.0185F);
		Carriage.addChild(WheelRight);
		WheelRight.cubeList.add(new ModelBox(WheelRight, 10, 23, -0.5927F, -1.4316F, -1.489F, 2, 3, 3, 0.0F, false));

		WheelSpokes0 = new ModelRenderer(this);
		WheelSpokes0.setRotationPoint(0.0393F, 2.3184F, 0.611F);
		WheelRight.addChild(WheelSpokes0);
		WheelSpokes0.cubeList.add(new ModelBox(WheelSpokes0, 45, 52, -0.382F, -2.75F, -7.1F, 1, 1, 13, 0.0F, false));
		WheelSpokes0.cubeList.add(new ModelBox(WheelSpokes0, 12, 4, -0.382F, -8.75F, -1.1F, 1, 13, 1, 0.0F, false));

		WheelSpokes1 = new ModelRenderer(this);
		WheelSpokes1.setRotationPoint(-0.4607F, 2.8184F, 0.111F);
		WheelRight.addChild(WheelSpokes1);
		setRotationAngle(WheelSpokes1, -0.7854F, 0.0F, 0.0F);
		WheelSpokes1.cubeList.add(new ModelBox(WheelSpokes1, 17, 52, 0.118F, -2.3738F, -8.5153F, 1, 1, 13, 0.0F, false));
		WheelSpokes1.cubeList.add(new ModelBox(WheelSpokes1, 8, 4, 0.118F, -8.3738F, -2.5153F, 1, 13, 1, 0.0F, false));

		Wheel1 = new ModelRenderer(this);
		Wheel1.setRotationPoint(-0.9607F, 2.8184F, 0.111F);
		WheelRight.addChild(Wheel1);
		setRotationAngle(Wheel1, -0.384F, 0.0F, 0.0F);
		Wheel1.cubeList.add(new ModelBox(Wheel1, 48, 14, -0.382F, -4.0123F, -8.6229F, 2, 3, 1, 0.0F, false));
		Wheel1.cubeList.add(new ModelBox(Wheel1, 0, 33, -0.382F, 3.9877F, -2.6229F, 2, 1, 3, 0.0F, false));
		Wheel1.cubeList.add(new ModelBox(Wheel1, 31, 13, -0.382F, -10.0123F, -2.6229F, 2, 1, 3, 0.0F, false));
		Wheel1.cubeList.add(new ModelBox(Wheel1, 48, 8, -0.382F, -4.0123F, 5.3771F, 2, 3, 1, 0.0F, false));

		Wheel2 = new ModelRenderer(this);
		Wheel2.setRotationPoint(-1.0107F, 2.8184F, 0.111F);
		WheelRight.addChild(Wheel2);
		setRotationAngle(Wheel2, -0.7854F, 0.0F, 0.0F);
		Wheel2.cubeList.add(new ModelBox(Wheel2, 48, 29, -0.382F, -3.3738F, -9.5153F, 2, 3, 1, 0.0F, false));
		Wheel2.cubeList.add(new ModelBox(Wheel2, 24, 33, -0.382F, 4.6262F, -3.5153F, 2, 1, 3, 0.0F, false));
		Wheel2.cubeList.add(new ModelBox(Wheel2, 10, 33, -0.382F, -9.3738F, -3.5153F, 2, 1, 3, 0.0F, false));
		Wheel2.cubeList.add(new ModelBox(Wheel2, 48, 18, -0.382F, -3.3738F, 4.4847F, 2, 3, 1, 0.0F, false));

		Wheel3 = new ModelRenderer(this);
		Wheel3.setRotationPoint(6.4393F, 0.2184F, 0.111F);
		WheelRight.addChild(Wheel3);
		setRotationAngle(Wheel3, -1.1694F, 0.0F, 0.0F);
		Wheel3.cubeList.add(new ModelBox(Wheel3, 48, 4, -7.782F, -1.4666F, -7.6771F, 2, 3, 1, 0.0F, false));
		Wheel3.cubeList.add(new ModelBox(Wheel3, 31, 9, -7.782F, 6.5334F, -1.6771F, 2, 1, 3, 0.0F, false));
		Wheel3.cubeList.add(new ModelBox(Wheel3, 31, 5, -7.782F, -7.4666F, -1.6771F, 2, 1, 3, 0.0F, false));
		Wheel3.cubeList.add(new ModelBox(Wheel3, 18, 46, -7.782F, -1.4666F, 6.3229F, 2, 3, 1, 0.0F, false));

		Wheel0 = new ModelRenderer(this);
		Wheel0.setRotationPoint(-0.9607F, 3.3184F, 0.611F);
		WheelRight.addChild(Wheel0);
		Wheel0.cubeList.add(new ModelBox(Wheel0, 48, 33, -0.357F, -4.75F, -8.1F, 2, 3, 1, 0.0F, false));
		Wheel0.cubeList.add(new ModelBox(Wheel0, 48, 37, -0.357F, -4.75F, 5.9F, 2, 3, 1, 0.0F, false));
		Wheel0.cubeList.add(new ModelBox(Wheel0, 0, 37, -0.357F, -10.75F, -2.1F, 2, 1, 3, 0.0F, false));
		Wheel0.cubeList.add(new ModelBox(Wheel0, 31, 35, -0.357F, 3.25F, -2.1F, 2, 1, 3, 0.0F, false));

		WheelLeft = new ModelRenderer(this);
		WheelLeft.setRotationPoint(6.6982F, -0.1053F, 0.0185F);
		Carriage.addChild(WheelLeft);
		setRotationAngle(WheelLeft, 0.0F, 3.1416F, 0.0F);
		WheelLeft.cubeList.add(new ModelBox(WheelLeft, 0, 23, -0.3927F, -1.4316F, -1.489F, 2, 3, 3, 0.0F, false));

		WheelSpokes2 = new ModelRenderer(this);
		WheelSpokes2.setRotationPoint(-11.9607F, 2.3184F, 0.411F);
		WheelLeft.addChild(WheelSpokes2);
		WheelSpokes2.cubeList.add(new ModelBox(WheelSpokes2, 48, 29, 11.818F, -2.75F, -6.9F, 1, 1, 13, 0.0F, false));
		WheelSpokes2.cubeList.add(new ModelBox(WheelSpokes2, 4, 4, 11.818F, -8.75F, -0.9F, 1, 13, 1, 0.0F, false));

		WheelSpokes3 = new ModelRenderer(this);
		WheelSpokes3.setRotationPoint(-12.4607F, 2.8184F, -0.089F);
		WheelLeft.addChild(WheelSpokes3);
		setRotationAngle(WheelSpokes3, -0.7854F, 0.0F, 0.0F);
		WheelSpokes3.cubeList.add(new ModelBox(WheelSpokes3, 48, 0, 12.318F, -2.5153F, -8.3738F, 1, 1, 13, 0.0F, false));
		WheelSpokes3.cubeList.add(new ModelBox(WheelSpokes3, 0, 4, 12.318F, -8.5153F, -2.3738F, 1, 13, 1, 0.0F, false));

		Wheel4 = new ModelRenderer(this);
		Wheel4.setRotationPoint(-12.9607F, 2.8184F, -0.089F);
		WheelLeft.addChild(Wheel4);
		setRotationAngle(Wheel4, -0.384F, 0.0F, 0.0F);
		Wheel4.cubeList.add(new ModelBox(Wheel4, 6, 46, 11.818F, -4.0872F, -8.4374F, 2, 3, 1, 0.0F, false));
		Wheel4.cubeList.add(new ModelBox(Wheel4, 31, 31, 11.818F, 3.9128F, -2.4374F, 2, 1, 3, 0.0F, false));
		Wheel4.cubeList.add(new ModelBox(Wheel4, 24, 29, 11.818F, -10.0872F, -2.4374F, 2, 1, 3, 0.0F, false));
		Wheel4.cubeList.add(new ModelBox(Wheel4, 0, 46, 11.818F, -4.0872F, 5.5626F, 2, 3, 1, 0.0F, false));

		Wheel5 = new ModelRenderer(this);
		Wheel5.setRotationPoint(-13.0107F, 2.8184F, -0.089F);
		WheelLeft.addChild(Wheel5);
		setRotationAngle(Wheel5, -0.7854F, 0.0F, 0.0F);
		Wheel5.cubeList.add(new ModelBox(Wheel5, 36, 39, 11.818F, -3.5153F, -9.3738F, 2, 3, 1, 0.0F, false));
		Wheel5.cubeList.add(new ModelBox(Wheel5, 10, 29, 11.818F, 4.4847F, -3.3738F, 2, 1, 3, 0.0F, false));
		Wheel5.cubeList.add(new ModelBox(Wheel5, 0, 29, 11.818F, -9.5153F, -3.3738F, 2, 1, 3, 0.0F, false));
		Wheel5.cubeList.add(new ModelBox(Wheel5, 30, 39, 11.818F, -3.5153F, 4.6262F, 2, 3, 1, 0.0F, false));

		Wheel6 = new ModelRenderer(this);
		Wheel6.setRotationPoint(-12.9607F, 2.8184F, -0.089F);
		WheelLeft.addChild(Wheel6);
		setRotationAngle(Wheel6, -1.1694F, 0.0F, 0.0F);
		Wheel6.cubeList.add(new ModelBox(Wheel6, 38, 29, 11.818F, -2.6666F, -9.9923F, 2, 3, 1, 0.0F, false));
		Wheel6.cubeList.add(new ModelBox(Wheel6, 24, 16, 11.818F, 5.3334F, -3.9923F, 2, 1, 3, 0.0F, false));
		Wheel6.cubeList.add(new ModelBox(Wheel6, 24, 12, 11.818F, -8.6666F, -3.9923F, 2, 1, 3, 0.0F, false));
		Wheel6.cubeList.add(new ModelBox(Wheel6, 38, 4, 11.818F, -2.6666F, 4.0077F, 2, 3, 1, 0.0F, false));

		Wheel7 = new ModelRenderer(this);
		Wheel7.setRotationPoint(-12.9607F, 3.3184F, 0.411F);
		WheelLeft.addChild(Wheel7);
		Wheel7.cubeList.add(new ModelBox(Wheel7, 24, 37, 11.843F, -4.75F, -7.9F, 2, 3, 1, 0.0F, false));
		Wheel7.cubeList.add(new ModelBox(Wheel7, 10, 37, 11.843F, -4.75F, 6.1F, 2, 3, 1, 0.0F, false));
		Wheel7.cubeList.add(new ModelBox(Wheel7, 24, 8, 11.843F, -10.75F, -1.9F, 2, 1, 3, 0.0F, false));
		Wheel7.cubeList.add(new ModelBox(Wheel7, 24, 4, 11.843F, 3.25F, -1.9F, 2, 1, 3, 0.0F, false));

		CarriageArms2 = new ModelRenderer(this);
		CarriageArms2.setRotationPoint(0.0F, 7.7461F, 27.6239F);
		Carriage.addChild(CarriageArms2);
		CarriageArms2.cubeList.add(new ModelBox(CarriageArms2, 63, 20, 2.5F, -3.4923F, -7.6622F, 2, 3, 9, 0.0F, false));
		CarriageArms2.cubeList.add(new ModelBox(CarriageArms2, 63, 0, -4.5F, -3.4923F, -7.6622F, 2, 3, 9, 0.0F, false));

		CarriageArms = new ModelRenderer(this);
		CarriageArms.setRotationPoint(-0.75F, 1.15F, 2.0F);
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
		Barrel.render(f5);
		Carriage.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}