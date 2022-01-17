package com.zach2039.oldguns.api.artillery;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public interface IArtillery {

	void initArtilleryConfiguration();

	void setArtilleryType(ArtilleryType artilleryType);

	ArtilleryType getArtilleryType();

	void pushAmmoProjectile(ItemStack stackIn);
	
	void pushAmmoCharge(ItemStack stackIn);

	ItemStack popAmmoProjectile();
	
	ItemStack popAmmoCharge();
	
	ItemStack peekAmmoProjectile();
	
	ItemStack peekAmmoCharge();
	
	void setLoadedAmmoCharges(CompoundNBT tag);
	
	CompoundNBT getLoadedAmmoCharges();

	void setLoadedAmmoProjectiles(CompoundNBT tag);
	
	CompoundNBT getLoadedAmmoProjectiles();
	
	void setFiringState(FiringState firingState);

	FiringState getFiringState();

	void setFiringCooldown(int cooldown);

	int getFiringCooldown();

	void setBaseProjectileSpeed(double projectileSpeed);

	float getBaseProjectileSpeed();

	void setBaseDeviation(double deviationModifier);

	float getBaseDeviation();

	void setDamageModifier(double damageModifier);

	float getDamageModifier();

	void setEffectiveRangeModifier(double effectiveRangeModifier);

	float getEffectiveRangeModifier();

	float getBarrelHeight();

	float getMinBarrelPitch();

	float getMaxBarrelPitch();

	float getMinBarrelYaw();

	float getMaxBarrelYaw();

	void setBarrelPitch(float pitch);

	float getBarrelPitch();

	void setBarrelYaw(float yaw);

	float getBarrelYaw();

	void doFiringEffect(World level, PlayerEntity player, double posX, double posY, double posZ);
}
