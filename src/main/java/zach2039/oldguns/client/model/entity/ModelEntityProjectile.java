package zach2039.oldguns.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEntityProjectile extends ModelBase
{
	private final ModelRenderer projectile_base;

	public ModelEntityProjectile()
	{
		textureWidth = 16;
		textureHeight = 16;

		projectile_base = new ModelRenderer(this);
		projectile_base.setRotationPoint(0.0F, 0.0F, 0.0F);
		projectile_base.cubeList.add(new ModelBox(projectile_base, 0, 0, -2.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) 
	{
		projectile_base.render(f5);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) 
	{
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
