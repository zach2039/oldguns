package com.zach2039.oldguns.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.ammo.ArtilleryCharge;
import com.zach2039.oldguns.client.model.NavalCannonModel;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.world.entity.BulletProjectile;
import com.zach2039.oldguns.world.item.ammo.artillery.ArtilleryAmmoItem;
import com.zach2039.oldguns.world.level.block.entity.MediumNavalCannonBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class NavalCannonRenderer implements BlockEntityRenderer<MediumNavalCannonBlockEntity> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(OldGuns.MODID, "textures/block/entity/ship_cannon.png");
	private final NavalCannonModel model;
	
	public NavalCannonRenderer(BlockEntityRendererProvider.Context ctx) {
		this.model = new NavalCannonModel(ctx.bakeLayer(NavalCannonModel.LAYER_LOCATION));
	}
	
	@SuppressWarnings("resource")
	@Override
	public void render(MediumNavalCannonBlockEntity blockEntity, float partialTicks, PoseStack stackIn,	MultiBufferSource buffer, int packedLight, int packedOverlay) {
		
		stackIn.pushPose();
		
		if (Minecraft.getInstance().player.getMainHandItem().getItem() == ModItems.GUNNERS_QUADRANT.get()) {
			renderTrajectory(blockEntity, stackIn, buffer);
		}
		
		stackIn.translate(0.5f, 2.25f, 0.5f);
		model.setupAnim(blockEntity, 0, 0, 0, 0, 0);
		stackIn.mulPose(Axis.YP.rotationDegrees(-blockEntity.getYawFromFacing()));
		
		stackIn.pushPose();
		stackIn.scale(1.5f, -1.5f, -1.5f);	
		VertexConsumer vertexconsumer = buffer.getBuffer(RenderType.entitySolid(TEXTURE));
		model.renderToBuffer(stackIn, vertexconsumer, packedLight, packedOverlay, 1f, 1f, 1f, 1f); 
		stackIn.popPose();
		
		stackIn.popPose();
	}
	
	private void renderTrajectory(MediumNavalCannonBlockEntity blockEntity, PoseStack stackIn, MultiBufferSource buffer) {
		ArtilleryAmmoItem ammo = (ArtilleryAmmoItem) blockEntity.peekAmmoProjectile(0);
		ArtilleryCharge charge = blockEntity.peekAmmoCharge(0);
		Vec3 pos = new Vec3(blockEntity.getBlockPos().getX(), blockEntity.getBlockPos().getY(), blockEntity.getBlockPos().getZ());
		if (ammo == null ||	charge == null || (Minecraft.getInstance().player.distanceToSqr(pos) > 32f))
			return;
		
		// Need to offset spawn location of projectiles, since block pos will end up aligned to block edge
		double pX = blockEntity.getBlockPos().getX() + 0.5D;
		double pY = blockEntity.getBlockPos().getY() + blockEntity.getShotHeight();
		double pZ = blockEntity.getBlockPos().getZ() + 0.5D;
		
		float finalVelocity = blockEntity.getBaseProjectileSpeed() * charge.getChargeAmount();
		float finalEffectiveRange = ammo.getProjectileEffectiveRange() * blockEntity.getEffectiveRangeModifier();
		float finalDeviation = blockEntity.getBaseProjectileDeviation() * ammo.getProjectileDeviationModifier();
		
        List<BulletProjectile> entityProjectiles = ammo.createProjectiles(blockEntity.getLevel(), pX, pY, pZ, ammo, blockEntity, Minecraft.getInstance().player);
        
        BulletProjectile proj = entityProjectiles.get(0);
        
        proj.setEffectiveRange(finalEffectiveRange);
        proj.setLaunchLocation(proj.blockPosition());
        proj.isSimulated = true;
        
        proj.shoot(pX, pY, pZ, blockEntity.getShotPitch(), blockEntity.getShotYaw(), 0.0F, finalVelocity, finalDeviation);        		 
	
		for (int i=0; i < 100; i++) {
			if (Minecraft.getInstance().level.random.nextFloat() < 0.04f) 
				Minecraft.getInstance().level.addParticle(ParticleTypes.CRIT,
						proj.getX(), proj.getY(), proj.getZ(),
						0d, 0d, 0d);
			
			proj.tick();
		}
	}
	
}
