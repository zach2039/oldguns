package com.zach2039.oldguns.world.damagesource;

import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;

public class OldGunsDamageSourceIndirectEntity extends IndirectEntityDamageSource {
	public OldGunsDamageSourceIndirectEntity(String tag, Entity shot, Entity shooter) {
		super(tag, shot, shooter);
	}
}