package com.zach2039.oldguns.api.ammo;

import java.util.List;

import com.zach2039.oldguns.world.entity.BulletProjectile;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface FirearmAmmo {
	List<BulletProjectile> createProjectiles(Level worldIn, ItemStack stack, LivingEntity shooter);
}
