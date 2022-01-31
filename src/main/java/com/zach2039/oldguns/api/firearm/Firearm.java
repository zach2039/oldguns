package com.zach2039.oldguns.api.firearm;

import com.zach2039.oldguns.api.ammo.ProjectileType;
import com.zach2039.oldguns.api.firearm.Firearms.MechanismType;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface Firearm {
	
	void initNBTTags(ItemStack stackIn);
	
	boolean canReload(ItemStack stackIn);
	
	void doFiringEffect(Level worldIn, Entity entityShooter, ItemStack stackIn);
	
	int getAmmoCapacity();

	float getProjectileSpeed();

	float getEffectiveRangeModifier();

	float getFirearmDeviation();
	
	float getDamageModifier();
	
	int getRequiredReloadTicks();
	
	boolean firesAllLoadedAmmoAtOnce();
	
	ProjectileType getDefaultProjectileType();

	MechanismType getMechanismType();
	
	FirearmReloadType getReloadType();
	
	FirearmSize getFirearmSize();
	
	FirearmWaterResiliency getFirearmWaterResiliency();

	ItemStack getDefaultProjectileForFirearm();

	Item getDefaultAmmoItem();
}

