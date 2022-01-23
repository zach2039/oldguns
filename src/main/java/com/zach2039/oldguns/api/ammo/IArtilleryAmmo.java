package com.zach2039.oldguns.api.ammo;

import java.util.List;

import com.zach2039.oldguns.api.artillery.ArtilleryAmmoType;
import com.zach2039.oldguns.world.entity.BulletProjectile;
import com.zach2039.oldguns.world.level.block.entity.StationaryArtilleryBlockEntity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public interface IArtilleryAmmo {
	List<BulletProjectile> createProjectiles(Level worldIn, double x, double y, double z, IArtilleryAmmo stack, StationaryArtilleryBlockEntity stationaryArtilleryEntity, LivingEntity shooter);
	
	ArtilleryAmmoType getAmmoType();
	
	int getEffectTicks();
	
	float getEffectPotency();
	
	float getProjectileSize();
	
	float getProjectileCount();

	double getProjectileDamage();

	public float getProjectileDeviationModifier();
	
	public float getProjectileEffectiveRange();
}
