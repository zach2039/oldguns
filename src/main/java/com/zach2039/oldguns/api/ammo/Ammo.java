package com.zach2039.oldguns.api.ammo;

import java.util.List;

import com.zach2039.oldguns.world.entity.BulletProjectile;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface Ammo {	
	ProjectileType getAmmoType();
	
	float getProjectileSize();
	
	float getProjectileCount();

	double getProjectileDamage();

	public float getProjectileDeviationModifier();
	
	public float getProjectileEffectiveRange();
	
	public float getProjectileArmorBypassPercentage();
}
