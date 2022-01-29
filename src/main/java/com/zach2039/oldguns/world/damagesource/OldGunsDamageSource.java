package com.zach2039.oldguns.world.damagesource;

import javax.annotation.Nullable;

import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.world.damagesource.OldGunsDamageSource.DamageType;
import com.zach2039.oldguns.world.entity.BulletProjectile;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;

public class OldGunsDamageSource extends DamageSource {	
	public OldGunsDamageSource(String tag) {
		super(tag);
	}
	
	public static DamageSource projectile(DamageType damageType, BulletProjectile projectile, @Nullable Entity shooter, float percentBypassArmor) {
		boolean allowArmorBypass = (damageType == DamageType.FIREARM) ? OldGunsConfig.SERVER.firearmSettings.ammoSettings.allowArmorBypass.get() : OldGunsConfig.SERVER.artillerySettings.artilleryAmmoSettings.allowArmorBypass.get();
		float percentBypassArmorActual = allowArmorBypass ? percentBypassArmor : 0.0f;
		
		if (damageType == DamageType.FIREARM) {
			return firearm(projectile, shooter, percentBypassArmorActual);
		}
		
		return artillery(projectile, shooter, percentBypassArmorActual);
	}
	
	public static DamageSource firearm(BulletProjectile projectile, @Nullable Entity shooter, float percentBypassArmor) {
		return new OldGunsDamageSourceIndirectEntity("firearm", projectile, shooter, percentBypassArmor).setProjectile();
	}
	
	public static DamageSource artillery(BulletProjectile projectile, @Nullable Entity shooter, float percentBypassArmor) {
		return new OldGunsDamageSourceIndirectEntity("artillery", projectile, shooter, percentBypassArmor).setProjectile();
	}
	
	public enum DamageType {
		FIREARM,
		ARTILLERY
	}
}