package com.zach2039.oldguns.world.damagesource;

import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;

public class OldGunsDamageSourceEntity extends DamageSource {
	public OldGunsDamageSourceEntity(Holder<DamageType> typeHolder, Entity attacker) {
		super(typeHolder, attacker);
	}
}
