package com.zach2039.oldguns.api.ammo;

import java.util.List;

import com.zach2039.oldguns.entity.BulletProjectile;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IFirearmAmmo {
	List<BulletProjectile> createProjectiles(World worldIn, ItemStack stack, LivingEntity shooter);
	
	float getProjectileSize();
	
	float getProjectileCount();

	double getProjectileDamage();

	public float getProjectileDeviationModifier();
	
	public float getProjectileEffectiveRange();
}
