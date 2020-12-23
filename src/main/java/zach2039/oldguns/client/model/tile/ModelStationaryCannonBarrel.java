package zach2039.oldguns.client.model.tile;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


public class ModelStationaryCannonBarrel extends ModelBase {
	private final ModelRenderer Barrel;
	private final ModelRenderer BarrelMain;

	public ModelStationaryCannonBarrel() {
		textureWidth = 64;
		textureHeight = 64;

		Barrel = new ModelRenderer(this);
		Barrel.setRotationPoint(0F, 0F,0F);
		Barrel.cubeList.add(new ModelBox(Barrel, 0, 36, -4.0083F, -1.1F, -0.8833F, 8, 2, 2, 0.0F, false));

		BarrelMain = new ModelRenderer(this);
		BarrelMain.setRotationPoint(0.0083F, -10.9F, -1.3667F);
		Barrel.addChild(BarrelMain);
		BarrelMain.cubeList.add(new ModelBox(BarrelMain, 0, 17, -2.5167F, 8.7F, 1.1833F, 5, 5, 11, 0.0F, false));
		BarrelMain.cubeList.add(new ModelBox(BarrelMain, 0, 0, -1.4667F, 9.5F, -11.4167F, 3, 3, 14, 0.0F, false));
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