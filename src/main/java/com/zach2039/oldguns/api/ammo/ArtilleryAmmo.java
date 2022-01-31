package com.zach2039.oldguns.api.ammo;

import java.util.List;

import com.zach2039.oldguns.world.entity.BulletProjectile;
import com.zach2039.oldguns.world.level.block.entity.StationaryArtilleryBlockEntity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public interface ArtilleryAmmo {
	List<BulletProjectile> createProjectiles(Level worldIn, double x, double y, double z, ArtilleryAmmo stack, StationaryArtilleryBlockEntity stationaryArtilleryEntity, LivingEntity shooter);
	
	int getEffectTicks();
	
	float getEffectPotency();
}
