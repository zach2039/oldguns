package com.zach2039.oldguns.mixin.coremods;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.world.entity.ai.goal.RangedFirearmAttackGoal;
import com.zach2039.oldguns.world.item.firearm.FirearmItem;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
	/**
	 * Mixin to reset attack timer on firearm goals of mobs when hit
	 * @param damagesource
	 * @param amount
	 */
	@Inject(method = "hurt", at = @At(value = "HEAD"))
	private void hurt(DamageSource damagesource, float amount, CallbackInfoReturnable<Boolean> ci) {

		if (((LivingEntity)(Object)this) instanceof Mob) {
			Mob mob = (Mob) ((LivingEntity)(Object)this);
			if (mob.level() != null && !mob.level().isClientSide && OldGunsConfig.SERVER.mobSettings.resetMobShotTimerOnHit.get()) {
				mob.goalSelector.getAvailableGoals().stream()
				.filter((goal) -> {return goal.getGoal() instanceof RangedFirearmAttackGoal;}).forEach((goal) -> {
					((RangedFirearmAttackGoal<?>)goal.getGoal()).interruptFiring();
				});	
			}
		}
	}
}
