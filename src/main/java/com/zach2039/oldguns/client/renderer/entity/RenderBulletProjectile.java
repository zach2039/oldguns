package com.zach2039.oldguns.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import com.zach2039.oldguns.world.entity.BulletProjectile;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class RenderBulletProjectile extends EntityRenderer<BulletProjectile> {
	
	private ResourceLocation entityTexture;
	//private EntityModel model = new BulletProjectileModel();
	
	public RenderBulletProjectile(final EntityRendererProvider.Context context, final ResourceLocation entityTexture)	{
		super(context);
		this.entityTexture = entityTexture;
	}

	@Override
	public void render(BulletProjectile entity, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn)
	{
		// IE magic
		double x = entity.position().x;
		double y = entity.position().y;
		double z = entity.position().z;
		double yaw = entity.yRotO + (entity.getYRot() - entity.yRotO) * partialTicks - 90.0F;
		double pitch = entity.xRotO + (entity.getXRot() - entity.xRotO) * partialTicks;
		float size = entity.getProjectileSize();

		matrixStackIn.pushPose();
		matrixStackIn.translate(x, y, z);
		matrixStackIn.mulPose(new Quaternion(new Vector3f(0f, 1f, 0f), (float)pitch, true));
		matrixStackIn.mulPose(new Quaternion(new Vector3f(1f, 0f, 1f), (float)yaw, true));
		matrixStackIn.scale(size, size, size);
		//bindEntityTexture(entity);
		//model.render(entity, 0.0F, 0.0F, -0.1F, (float)yaw, (float)pitch, 0.025F);
		matrixStackIn.popPose();
		super.render(entity, (float) yaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}
	
	@Override
	public ResourceLocation getTextureLocation(BulletProjectile p_114482_) {
		// TODO Auto-generated method stub
		return this.entityTexture;
	}

}
