package zach2039.oldguns.client.model.tile;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench 3.7.4
// Exported for Minecraft version 1.12
// Paste this class into your mod and generate all required imports


public class ModelStationaryCannonBase extends ModelBase {
	private final ModelRenderer Carriage;
	private final ModelRenderer CarriageWedge_r1;

	public ModelStationaryCannonBase() {
		/* FIXME: This breaks in newer versions of 1.12.2 forge. */
		textureWidth = 64;
		textureHeight = 64;

		Carriage = new ModelRenderer(this);
		Carriage.setRotationPoint(0.0F, 24.0F, 3.9F);
		Carriage.cubeList.add(new ModelBox(Carriage, 20, 0, -5.0208F, 0.8875F, -9.5835F, 10, 1, 8, 0.0F, false));
		Carriage.cubeList.add(new ModelBox(Carriage, 21, 17, -5.0208F, 0.8875F, 3.5165F, 10, 1, 7, 0.0F, false));
		Carriage.cubeList.add(new ModelBox(Carriage, 34, 13, -5.0208F, -0.5125F, -4.7335F, 10, 1, 3, 0.0F, false));
		Carriage.cubeList.add(new ModelBox(Carriage, 40, 40, 3.0292F, -5.7625F, -2.8835F, 2, 8, 3, 0.0F, false));
		Carriage.cubeList.add(new ModelBox(Carriage, 19, 47, 3.0292F, -5.7625F, -5.8835F, 2, 8, 1, 0.0F, false));
		Carriage.cubeList.add(new ModelBox(Carriage, 48, 25, 3.0292F, -4.7625F, -4.8835F, 2, 7, 2, 0.0F, false));
		Carriage.cubeList.add(new ModelBox(Carriage, 47, 48, 5.4292F, 0.6375F, -8.2335F, 1, 3, 3, 0.0F, false));
		Carriage.cubeList.add(new ModelBox(Carriage, 20, 9, -6.9708F, 1.6875F, -7.2835F, 14, 1, 1, 0.0F, false));
		Carriage.cubeList.add(new ModelBox(Carriage, 20, 11, -6.9708F, 1.6875F, 6.3665F, 14, 1, 1, 0.0F, false));
		Carriage.cubeList.add(new ModelBox(Carriage, 20, 0, -6.3708F, 0.6375F, 5.4165F, 1, 3, 3, 0.0F, false));
		Carriage.cubeList.add(new ModelBox(Carriage, 47, 36, -6.3708F, 0.6375F, -8.2335F, 1, 3, 3, 0.0F, false));
		Carriage.cubeList.add(new ModelBox(Carriage, 40, 25, 5.4292F, 0.6375F, 5.4165F, 1, 3, 3, 0.0F, false));
		Carriage.cubeList.add(new ModelBox(Carriage, 28, 36, 3.0292F, -2.7625F, 4.0165F, 2, 5, 4, 0.0F, false));
		Carriage.cubeList.add(new ModelBox(Carriage, 48, 17, 3.0292F, -0.7625F, 7.9665F, 2, 3, 2, 0.0F, false));
		Carriage.cubeList.add(new ModelBox(Carriage, 48, 0, -5.0708F, -0.7625F, 7.9665F, 2, 3, 2, 0.0F, false));
		Carriage.cubeList.add(new ModelBox(Carriage, 16, 36, 3.0292F, -4.7625F, 0.0665F, 2, 7, 4, 0.0F, false));
		Carriage.cubeList.add(new ModelBox(Carriage, 0, 40, -5.0708F, -2.7625F, 4.0165F, 2, 5, 4, 0.0F, false));
		Carriage.cubeList.add(new ModelBox(Carriage, 0, 0, -5.0708F, -4.7625F, 0.0665F, 2, 7, 4, 0.0F, false));
		Carriage.cubeList.add(new ModelBox(Carriage, 0, 17, -5.0708F, -5.7625F, -2.8835F, 2, 8, 3, 0.0F, false));
		Carriage.cubeList.add(new ModelBox(Carriage, 35, 50, -5.0708F, -5.7625F, -5.8835F, 2, 8, 1, 0.0F, false));
		Carriage.cubeList.add(new ModelBox(Carriage, 0, 49, -5.0708F, -4.7625F, -4.8835F, 2, 7, 2, 0.0F, false));
		Carriage.cubeList.add(new ModelBox(Carriage, 9, 46, -5.0708F, -4.7625F, -8.9335F, 2, 7, 3, 0.0F, false));
		Carriage.cubeList.add(new ModelBox(Carriage, 25, 45, 3.0292F, -4.7625F, -8.9335F, 2, 7, 3, 0.0F, false));

		CarriageWedge_r1 = new ModelRenderer(this);
		CarriageWedge_r1.setRotationPoint(0.0661F, -0.3926F, 0.0204F);
		Carriage.addChild(CarriageWedge_r1);
		setRotationAngle(CarriageWedge_r1, -0.3927F, 0.0F, 0.0F);
		CarriageWedge_r1.cubeList.add(new ModelBox(CarriageWedge_r1, 24, 25, -1.887F, -2.1202F, -0.2497F, 4, 1, 8, 0.0F, false));
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