package com.zach2039.oldguns.api.ammo;

public interface Ammo {
	ProjectileType getAmmoType();
	
	float getProjectileSize();
	
	float getProjectileCount();

	double getProjectileDamage();

	public float getProjectileDeviationModifier();
	
	public float getProjectileEffectiveRange();
	
	public float getProjectileArmorBypassPercentage();
}
