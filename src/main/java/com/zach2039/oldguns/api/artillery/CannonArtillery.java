package com.zach2039.oldguns.api.artillery;

import com.zach2039.oldguns.api.ammo.ArtilleryCharge;
import net.minecraft.world.item.ItemStack;

public interface CannonArtillery {
	
	AmmoFiringState determineFiringStateOfSlot(int slot); 

	void putAmmoCharge(int slot, ItemStack stackIn);
	
	void ramAmmoProjectile(int slot);
	
	void ramAmmoCharge(int slot);
	
	ArtilleryCharge getAmmoCharge(int slot);
	
	ArtilleryCharge peekAmmoCharge(int slot);
	
	boolean isAmmoProjectileRammed(int slot);
	
	boolean isAmmoChargeRammed(int slot);
	
	float getBaseProjectileSpeed();
	
	float getProjectileDamageModifier();

	float getEffectiveRangeModifier();
}
