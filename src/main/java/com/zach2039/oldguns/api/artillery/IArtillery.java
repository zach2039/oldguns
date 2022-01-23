package com.zach2039.oldguns.api.artillery;

import com.zach2039.oldguns.api.ammo.IArtilleryAmmo;
import com.zach2039.oldguns.api.ammo.IArtilleryCharge;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IArtillery {

	ArtilleryType getArtilleryType();
	
	ArtilleryFiringState determineOverallFiringState();
	
	AmmoFiringState determineFiringStateOfSlot(int slot); 

	void putAmmoProjectile(int slot, ItemStack stackIn);
	
	void putAmmoCharge(int slot, ItemStack stackIn);

	void ramAmmoProjectile(int slot);
	
	void ramAmmoCharge(int slot);
	
	IArtilleryAmmo getAmmoProjectile(int slot);
	
	IArtilleryCharge getAmmoCharge(int slot);
	
	IArtilleryAmmo peekAmmoProjectile(int slot);
	
	IArtilleryCharge peekAmmoCharge(int slot);
	
	boolean isAmmoProjectileRammed(int slot);
	
	boolean isAmmoChargeRammed(int slot);
	
	int getAmmoSlots();

	void setFiringCooldown(int cooldown);

	int getFiringCooldown();

	float getBaseProjectileSpeed();

	float getBaseProjectileDeviation();

	float getProjectileDamageModifier();

	float getEffectiveRangeModifier();

	float getShotHeight();

	float getMinShotPitch();

	float getMaxShotPitch();

	float getMinShotYaw();

	float getMaxShotYaw();

	void setShotPitch(float pitch);

	float getShotPitch();

	void setShotYaw(float yaw);

	float getShotYaw();

	void doFiringEffect(Level level, Player player, double posX, double posY, double posZ);
}
