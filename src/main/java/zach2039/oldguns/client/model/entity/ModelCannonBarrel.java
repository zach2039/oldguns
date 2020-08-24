package zach2039.oldguns.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

//Made with Blockbench 3.5.4
//Exported for Minecraft version 1.12
//Paste this class into your mod and generate all required imports


public class ModelCannonBarrel extends ModelBase {
	private final ModelRenderer Barrel;
	private final ModelRenderer BarrelMain;

	public ModelCannonBarrel() {
		textureWidth = 128;
		textureHeight = 128;

		Barrel = new ModelRenderer(this);
		Barrel.setRotationPoint(0.0F, 0.0F, 0.0F);
		Barrel.cubeList.add(new ModelBox(Barrel, 0, 0, -3.5F, -0.75F, -1.0F, 7, 2, 2, 0.0F, false));

		BarrelMain = new ModelRenderer(this);
		BarrelMain.setRotationPoint(0.0F, 3.0F, -0.75F);
		Barrel.addChild(BarrelMain);
		BarrelMain.cubeList.add(new ModelBox(BarrelMain, 32, 52, -2.0F, -4.75F, 0.25F, 4, 4, 9, 0.0F, false));
		BarrelMain.cubeList.add(new ModelBox(BarrelMain, 0, 46, -1.5F, -4.25F, -10.75F, 3, 3, 12, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		Barrel.render(f5);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}