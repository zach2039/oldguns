package com.zach2039.oldguns.world.damagesource;

import javax.annotation.Nullable;

import com.zach2039.oldguns.world.entity.BulletProjectile;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;

public class OldGunsDamageSource extends DamageSource {	
	public OldGunsDamageSource(String tag) {
		super(tag);
	}
	
	public static OldGunsDamageSource projectile(DamageType damageType, BulletProjectile projectile, @Nullable Entity shooter) {
		if (damageType == DamageType.FIREARM)
			return firearm(projectile, shooter);
		
		return artillery(projectile, shooter);
	}
	
	public static OldGunsDamageSource firearm(BulletProjectile projectile, @Nullable Entity shooter) {
		return (OldGunsDamageSource) (new OldGunsDamageSourceIndirectEntity("firearm", projectile, shooter)).setProjectile().bypassArmor();
	}
	
	public static OldGunsDamageSource artillery(BulletProjectile projectile, @Nullable Entity shooter) {
		return (OldGunsDamageSource) (new OldGunsDamageSourceIndirectEntity("artillery", projectile, shooter)).setProjectile().bypassArmor();
	}
	
	public enum DamageType {
		FIREARM,
		ARTILLERY
	}
}