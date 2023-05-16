package com.zach2039.oldguns.world.damagesource;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;

public class OldGunsDamageSourceEntity extends DamageSource {
	public OldGunsDamageSourceEntity(String tag, Entity attacker) {
		super(tag, attacker);
	}
}
