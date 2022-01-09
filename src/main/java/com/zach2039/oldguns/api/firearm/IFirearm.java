package com.zach2039.oldguns.api.firearm;

import com.zach2039.oldguns.api.firearm.FirearmType.FirearmReloadType;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmSize;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmWaterResiliency;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IFirearm {

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
	
	FirearmReloadType getReloadType();
	
	FirearmSize getFirearmSize();
	
	FirearmWaterResiliency getFirearmWaterResiliency();
}

