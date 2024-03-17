package com.zach2039.oldguns.world.entity.monster;

import com.zach2039.oldguns.api.firearm.util.FirearmNBTHelper;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.world.entity.ai.goal.RangedFirearmAttackGoal;
import com.zach2039.oldguns.world.item.firearm.FirearmItem;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;

public abstract class AbstractFirearmSkeleton extends AbstractSkeleton {
	
	private final RangedFirearmAttackGoal<AbstractSkeleton> firearmAttackGoal = new RangedFirearmAttackGoal<>(this, 1.0D, OldGunsConfig.SERVER.mobSettings.firearmMobShotTime.get(), 15.0F) {
		@Override
		public boolean canUse() {			
			ItemStack firearmStack = this.mob.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this.mob, item -> item instanceof FirearmItem));
			if (firearmStack.getDamageValue() >= firearmStack.getMaxDamage())
				return false;
			
			return super.canUse();
		}
	};
	
	private final MeleeAttackGoal meleeAttackGoal = new MeleeAttackGoal(this, 1.0D, false) {
		@Override
		public void stop() {
			super.stop();
			AbstractFirearmSkeleton.this.setAggressive(false);
		}

		@Override
		public void start() {
			super.start();
			AbstractFirearmSkeleton.this.setAggressive(true);
		}
	};

	protected AbstractFirearmSkeleton(EntityType<? extends AbstractFirearmSkeleton> type, Level level) {
		super(type, level);
		this.xpReward = 7;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundEvents.SKELETON_STEP;
	}

	@Override
	protected SoundEvent getSwimSound() {
		return SoundEvents.HOSTILE_SWIM;
	}

	@Override
	protected SoundEvent getSwimSplashSound() {
		return SoundEvents.HOSTILE_SPLASH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damagesource) {
		return SoundEvents.SKELETON_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.SKELETON_DEATH;
	}

	@Override
	public boolean hurt(DamageSource damagesource, float amount) {

		// Make firearm skeletons reset shot time when hit
		if (this.level() != null && !this.level().isClientSide && OldGunsConfig.SERVER.mobSettings.resetMobShotTimerOnHit.get()) {
			this.firearmAttackGoal.interruptFiring();
		}

		return super.hurt(damagesource, amount);
	}

	@Override
	public void aiStep() {
		if (this.level() != null && !this.level().isClientSide) {
			ItemStack firearmStack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, item -> item instanceof FirearmItem));
			if (firearmStack.getItem() instanceof FirearmItem) {
				FirearmItem firearmItem = (FirearmItem)firearmStack.getItem();

				// Load the firearm while mob is using
				if (this.useItem != ItemStack.EMPTY) {
					if (FirearmNBTHelper.peekNBTTagAmmo(firearmStack) == ItemStack.EMPTY)
						FirearmNBTHelper.pushNBTTagAmmo(firearmStack, firearmItem.getDefaultProjectileForFirearm());
				} else {
					FirearmNBTHelper.emptyNBTTagAmmo(firearmStack);
				}
			}
		}
		
		super.aiStep();
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(2, new RestrictSunGoal(this));
		this.goalSelector.addGoal(3, new FleeSunGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Wolf.class, 6.0F, 1.0D, 1.2D));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
	}

	@Override
	public void reassessWeaponGoal() {		
		if (this.level() != null && !this.level().isClientSide) {
			this.goalSelector.removeGoal(this.meleeAttackGoal);
			this.goalSelector.removeGoal(this.firearmAttackGoal);
			ItemStack itemstack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, item -> item instanceof FirearmItem));
			if ((itemstack.getItem() instanceof FirearmItem) && (itemstack.getMaxDamage() != itemstack.getDamageValue())) {
				int i = OldGunsConfig.SERVER.mobSettings.firearmMobShotTime.get();
				if (this.level().getDifficulty() != Difficulty.HARD) {
					i = OldGunsConfig.SERVER.mobSettings.firearmMobShotTimeHard.get();
				}

				this.firearmAttackGoal.setMinAttackInterval(i);
				this.firearmAttackGoal.interruptFiring();
				this.goalSelector.addGoal(4, this.firearmAttackGoal);
			} else {
				super.reassessWeaponGoal();
			}
		}
	}

	@Override
	public void performRangedAttack(LivingEntity target, float power) {
		ItemStack firearmStack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, item -> item instanceof FirearmItem));
		FirearmItem firearmItem = (FirearmItem)firearmStack.getItem();
		ItemStack ammoStack = this.getProjectile(this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, item -> item instanceof FirearmItem)));

		if (!this.level().isClientSide()) {
			boolean failure = firearmItem.checkConditionForEffect(this.level(), this, firearmStack);

			// If firearm broke or misfired, do nothing
			if (failure) {
				return;
			}

			firearmItem.fireProjectiles(this.level(), this, firearmStack, ammoStack, OldGunsConfig.SERVER.mobSettings.firearmMobBaseProjectileDeviation.get().floatValue());
		}
	}

	@Override
	public ItemStack getProjectile(ItemStack stack) {
		if (stack.getItem() instanceof FirearmItem) {
			net.minecraft.world.item.Item ammoItem = ((FirearmItem)stack.getItem()).getDefaultAmmoItem();
			return (ammoItem != null) ? new ItemStack(ammoItem) : ItemStack.EMPTY;
		} else {
			return ItemStack.EMPTY;
		}
	}

	@Override
	public boolean canFireProjectileWeapon(ProjectileWeaponItem projectileWeapon) {
		return (projectileWeapon instanceof FirearmItem);
	}
}
