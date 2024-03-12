package com.zach2039.oldguns.mixin.coremods;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.util.FirearmNBTHelper;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.mixin.accessors.AbstractSkeletonAccess;
import com.zach2039.oldguns.world.entity.ai.goal.RangedFirearmAttackGoal;
import com.zach2039.oldguns.world.item.firearm.FirearmItem;

import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;

@Mixin(AbstractSkeleton.class)
public class AbstractSkeletonMixin {	
	
	/***
	 * Mixin to load firearm for skeletons during use 
	 */
	@Inject(method = "aiStep", at = @At(value = "HEAD"))
	private void aiStep(CallbackInfo ci) {
		//OldGuns.LOGGER.debug("aiStep");
		if (((AbstractSkeleton)(Object)this).level() != null && !((AbstractSkeleton)(Object)this).level().isClientSide) {
			ItemStack firearmStack = ((AbstractSkeleton)(Object)this).getItemInHand(ProjectileUtil.getWeaponHoldingHand(((AbstractSkeleton)(Object)this), item -> item instanceof FirearmItem));
			
			if (firearmStack.getItem() instanceof FirearmItem) {
				FirearmItem firearmItem = (FirearmItem)firearmStack.getItem();

				// Load the firearm while mob is using
				if (((AbstractSkeleton)(Object)this).getUseItem() != ItemStack.EMPTY) {
					if (FirearmNBTHelper.peekNBTTagAmmo(firearmStack) == ItemStack.EMPTY)
						FirearmNBTHelper.pushNBTTagAmmo(firearmStack, firearmItem.getDefaultProjectileForFirearm());
				} else {
					FirearmNBTHelper.emptyNBTTagAmmo(firearmStack);
				}
			}
		}
	}
	
	/***
	 * Mixin to apply firearm weapon goal to skeleton after original weapon goals are set, if firearm is held
	 */
	@Inject(method = "reassessWeaponGoal", at = @At(value = "TAIL"))
	public void reassessWeaponGoal(CallbackInfo ci) {
		//OldGuns.LOGGER.debug("reassessWeaponGoal");
		RangedFirearmAttackGoal<AbstractSkeleton> firearmAttackGoal = new RangedFirearmAttackGoal<AbstractSkeleton>(((AbstractSkeleton)(Object)this), 1.0D, OldGunsConfig.SERVER.mobSettings.firearmMobShotTime.get(), 15.0F) {
			@Override
			public boolean canUse() {	
				//OldGuns.LOGGER.debug("canUse");
				ItemStack firearmStack = this.mob.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this.mob, item -> item instanceof FirearmItem));
				if (firearmStack.getDamageValue() >= firearmStack.getMaxDamage())
					return false;
				
				return (this.mob.getTarget() == null) ? false : this.isHoldingFirearm();
			}
		};
		
		if (((AbstractSkeleton)(Object)this).level() != null && !((AbstractSkeleton)(Object)this).level().isClientSide) {
			ItemStack itemstack = ((AbstractSkeleton)(Object)this).getItemInHand(ProjectileUtil.getWeaponHoldingHand(((AbstractSkeleton)(Object)this), item -> item instanceof FirearmItem));
			if ((itemstack.getItem() instanceof FirearmItem) && (itemstack.getMaxDamage() != itemstack.getDamageValue())) {
				((AbstractSkeleton)(Object)this).goalSelector.removeGoal(((AbstractSkeletonAccess)((AbstractSkeleton)(Object)this)).getMeleeGoal());
				((AbstractSkeleton)(Object)this).goalSelector.removeGoal(((AbstractSkeletonAccess)((AbstractSkeleton)(Object)this)).getBowGoal());
				
				int i = OldGunsConfig.SERVER.mobSettings.firearmMobShotTime.get();
				if (((AbstractSkeleton)(Object)this).level().getDifficulty() != Difficulty.HARD) {
					i = OldGunsConfig.SERVER.mobSettings.firearmMobShotTimeHard.get();
				}

				firearmAttackGoal.setMinAttackInterval(i);
				firearmAttackGoal.interruptFiring();
				((AbstractSkeleton)(Object)this).goalSelector.addGoal(4, firearmAttackGoal);
			}
		}
	}
	
	/**
	 * Mixin to allow skeletons to shoot firearms.
	 * @param pTarget
	 * @param pDistanceFactor
	 * @param ci
	 */
	@Inject(method = "performRangedAttack", at = @At(value = "HEAD"), cancellable = true)
	public void performRangedAttack(LivingEntity pTarget, float pDistanceFactor, CallbackInfo ci) {
		//OldGuns.LOGGER.debug("performRangedAttack");		
		if (((AbstractSkeleton)(Object)this).getMainHandItem().getItem() instanceof FirearmItem) {
			ItemStack firearmStack = ((AbstractSkeleton)(Object)this).getItemInHand(ProjectileUtil.getWeaponHoldingHand(((AbstractSkeleton)(Object)this), item -> item instanceof FirearmItem));
			FirearmItem firearmItem = (FirearmItem)firearmStack.getItem();
			ItemStack ammoStack = ((AbstractSkeleton)(Object)this).getProjectile(((AbstractSkeleton)(Object)this).getItemInHand(ProjectileUtil.getWeaponHoldingHand(((AbstractSkeleton)(Object)this), item -> item instanceof FirearmItem)));

			if (!((AbstractSkeleton)(Object)this).level().isClientSide()) {
				boolean failure = firearmItem.checkConditionForEffect(((AbstractSkeleton)(Object)this).level(), ((AbstractSkeleton)(Object)this), firearmStack);
	
				// If firearm broke or misfired, do nothing
				if (!failure) {
					firearmItem.fireProjectiles(((AbstractSkeleton)(Object)this).level(), ((AbstractSkeleton)(Object)this), firearmStack, ammoStack, OldGunsConfig.SERVER.mobSettings.firearmMobBaseProjectileDeviation.get().floatValue());
					
				}				
			}
			ci.cancel();
		}
	}
	
	/**
	 * Mixin to allow skeletons to use firearms.
	 * @param projectileWeapon
	 * @param cir
	 */
	@Inject(method = "canFireProjectileWeapon", at = @At(value = "HEAD"), cancellable = true)
	private void canFireProjectileWeapon(ProjectileWeaponItem projectileWeapon, CallbackInfoReturnable<Boolean> cir) {
		//OldGuns.LOGGER.debug("canFireProjectileWeapon");
		if (projectileWeapon instanceof FirearmItem) {
			cir.setReturnValue(true);
		}
	}
}
