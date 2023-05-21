package com.zach2039.oldguns.world.damagesource;

import javax.annotation.Nullable;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.world.entity.BulletProjectile;
import com.zach2039.oldguns.world.entity.RocketProjectile;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class OldGunsDamageSources {

	public static DamageSource projectile(ResourceKey<DamageType> damageType, BulletProjectile projectile, @Nullable Entity shooter, float percentBypassArmor) {
		boolean allowArmorBypass = (damageType == OldGunsDamageTypes.FIREARM) ? OldGunsConfig.SERVER.firearmSettings.ammoSettings.allowArmorBypass.get() : OldGunsConfig.SERVER.artillerySettings.artilleryAmmoSettings.allowArmorBypass.get();
		float percentBypassArmorActual = allowArmorBypass ? Math.min(1.0F, percentBypassArmor) : 0.0f;
		
		if (damageType == OldGunsDamageTypes.FIREARM) {
			return firearm(projectile, shooter, percentBypassArmorActual);
		}
		
		return artillery(projectile, shooter, percentBypassArmorActual);
	}
	
	public static DamageSource firearm(BulletProjectile projectile, @Nullable Entity shooter, float percentBypassArmor) {
		return new OldGunsDamageSourceIndirectEntity(typeHolder(shooter.level, OldGunsDamageTypes.FIREARM), projectile, shooter, percentBypassArmor);
	}
	
	public static DamageSource artillery(BulletProjectile projectile, @Nullable Entity shooter, float percentBypassArmor) {
		return new OldGunsDamageSourceIndirectEntity(typeHolder(shooter.level, OldGunsDamageTypes.ARTILLERY), projectile, shooter, percentBypassArmor);
	}
	
	public static OldGunsDamageSourceIndirectEntity rocket(RocketProjectile projectile, @Nullable Entity shooter) {
		return new OldGunsDamageSourceIndirectEntity(typeHolder(shooter.level, OldGunsDamageTypes.ARTILLERY), projectile, shooter, 0.0F);
	}

	private static Holder<DamageType> typeHolder(Level level, ResourceKey<DamageType> typeKey)
	{
		final Registry<DamageType> registry = level.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE);
		return registry.getHolderOrThrow(typeKey);
	}
}