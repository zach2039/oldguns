package com.zach2039.oldguns.api.artillery;

import com.zach2039.oldguns.api.ammo.Ammo;
import com.zach2039.oldguns.api.ammo.ArtilleryAmmo;
import com.zach2039.oldguns.api.ammo.ArtilleryCharge;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface Artillery {

	ArtilleryType getArtilleryType();
	
	ArtilleryFiringState determineOverallFiringState();

	public InteractionResult processInteraction(Level level, BlockPos blockpos, Player player, InteractionHand hand);
	
	void putAmmoProjectile(int slot, ItemStack stackIn);
	
	Ammo getAmmoProjectile(int slot);
	
	Ammo peekAmmoProjectile(int slot);
	
	int getAmmoSlots();

	void setFiringCooldown(int cooldown);

	int getFiringCooldown();

	float getBaseProjectileDeviation();

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
