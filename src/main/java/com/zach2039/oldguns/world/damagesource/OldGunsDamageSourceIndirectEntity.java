package com.zach2039.oldguns.world.damagesource;

import javax.annotation.Nullable;

import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;

public class OldGunsDamageSourceIndirectEntity extends DamageSource {
	private float percentBypassArmor = 0.0f;

	public OldGunsDamageSourceIndirectEntity(Holder<DamageType> typeHolder, Entity projectile, @Nullable Entity shooter, float percentBypassArmor) {
		super(typeHolder, projectile, shooter);
		this.percentBypassArmor = percentBypassArmor;
	}

	public float getPercentBypassArmor() {
		return this.percentBypassArmor;
	}
	
	public OldGunsDamageSourceIndirectEntity bypassArmorPercentage(float percentBypassArmor) {
		this.percentBypassArmor = percentBypassArmor;
		return this;
	} 
}