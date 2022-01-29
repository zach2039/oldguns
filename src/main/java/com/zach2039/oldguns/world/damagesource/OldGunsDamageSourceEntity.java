package com.zach2039.oldguns.world.damagesource;

import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;

public class OldGunsDamageSourceEntity extends EntityDamageSource {
	public OldGunsDamageSourceEntity(String tag, Entity attacker) {
		super(tag, attacker);
	}
}
