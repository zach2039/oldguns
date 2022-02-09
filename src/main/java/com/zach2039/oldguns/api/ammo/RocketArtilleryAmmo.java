package com.zach2039.oldguns.api.ammo;

import java.util.List;

import com.zach2039.oldguns.world.entity.RocketProjectile;
import com.zach2039.oldguns.world.level.block.entity.StationaryRocketBlockEntity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface RocketArtilleryAmmo {	
	List<RocketProjectile> createProjectiles(Level worldIn, double x, double y, double z, RocketArtilleryAmmo stack, StationaryRocketBlockEntity stationaryRocketBlockEntity, LivingEntity shooter);
	
	int getEffectTicks();
	
	float getEffectPotency();
	
	float getFlightTime(ItemStack ammoStack);
}
