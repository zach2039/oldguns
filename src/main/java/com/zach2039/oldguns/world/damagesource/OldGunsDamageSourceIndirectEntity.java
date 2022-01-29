package com.zach2039.oldguns.world.damagesource;

import javax.annotation.Nullable;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class OldGunsDamageSourceIndirectEntity extends IndirectEntityDamageSource {
	private float percentBypassArmor = 0.0f;

	public OldGunsDamageSourceIndirectEntity(String tag, Entity projectile, @Nullable Entity shooter, float percentBypassArmor) {
		super(tag, projectile, shooter);
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