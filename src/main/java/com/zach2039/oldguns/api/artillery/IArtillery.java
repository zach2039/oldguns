package com.zach2039.oldguns.api.artillery;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

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
	
	void setLoadedAmmoCharges(CompoundTag tag);
	
	CompoundTag getLoadedAmmoCharges();

	void setLoadedAmmoProjectiles(CompoundTag tag);
	
	CompoundTag getLoadedAmmoProjectiles();
	
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

	void doFiringEffect(Level level, Player player, double posX, double posY, double posZ);
}
