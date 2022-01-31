package com.zach2039.oldguns.world.damagesource;

import javax.annotation.Nullable;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.world.entity.BulletProjectile;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;

public class OldGunsDamageSource extends DamageSource {	
	public OldGunsDamageSource(String tag) {
		super(tag);
	}
	
	public static OldGunsDamageSourceIndirectEntity projectile(DamageType damageType, BulletProjectile projectile, @Nullable Entity shooter, float percentBypassArmor) {
		boolean allowArmorBypass = (damageType == DamageType.FIREARM) ? OldGunsConfig.SERVER.firearmSettings.ammoSettings.allowArmorBypass.get() : OldGunsConfig.SERVER.artillerySettings.artilleryAmmoSettings.allowArmorBypass.get();
		float percentBypassArmorActual = allowArmorBypass ? percentBypassArmor : 0.0f;
		
		if (damageType == DamageType.FIREARM) {
			return firearm(projectile, shooter, percentBypassArmorActual);
		}
		
		return artillery(projectile, shooter, percentBypassArmorActual);
	}
	
	public static OldGunsDamageSourceIndirectEntity firearm(BulletProjectile projectile, @Nullable Entity shooter, float percentBypassArmor) {
		return new OldGunsDamageSourceIndirectEntity(OldGuns.MODID + ".firearm", projectile, shooter, percentBypassArmor);
	}
	
	public static OldGunsDamageSourceIndirectEntity artillery(BulletProjectile projectile, @Nullable Entity shooter, float percentBypassArmor) {
		return new OldGunsDamageSourceIndirectEntity(OldGuns.MODID + ".artillery", projectile, shooter, percentBypassArmor);
	}
	
	public enum DamageType {
		FIREARM,
		ARTILLERY
	}
}