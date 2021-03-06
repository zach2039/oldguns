package com.zach2039.oldguns.world.entity.ai.goal;

import java.util.EnumSet;

import com.zach2039.oldguns.world.item.firearm.FirearmItem;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.projectile.ProjectileUtil;

public class RangedFirearmAttackGoal<T extends net.minecraft.world.entity.Mob & RangedAttackMob> extends RangedBowAttackGoal<T> {

	protected final T mob;
	protected final double speedModifier;
	protected int attackIntervalMin;
	protected final float attackRadiusSqr;
	protected int attackTime = -1;
	protected int seeTime;
	protected boolean strafingClockwise;
	protected boolean strafingBackwards;
	protected int strafingTime = -1;

	@SuppressWarnings("unchecked")
	public <M extends Monster & RangedAttackMob> RangedFirearmAttackGoal(M mob, double speedModifier, int attackIntervalMin, float attackRadius) {
		this((T) mob, speedModifier, attackIntervalMin, attackRadius);
	}

	public RangedFirearmAttackGoal(T mob, double speedModifier, int attackIntervalMin, float attackRadius) {
		super(mob, speedModifier, attackIntervalMin, attackRadius);
		this.mob = mob;
		this.speedModifier = speedModifier;
		this.attackIntervalMin = attackIntervalMin;
		this.attackRadiusSqr = attackRadius * attackRadius;
		this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	public void setMinAttackInterval(int p_25798_) {
		this.attackIntervalMin = p_25798_;
	}

	public boolean canUse() {
		return (this.mob.getTarget() == null) ? false : this.isHoldingFirearm();
	}

	protected boolean isHoldingFirearm() {
		return this.mob.isHolding(is -> is.getItem() instanceof FirearmItem);
	}

	public boolean canContinueToUse() {
		return (this.canUse() || !this.mob.getNavigation().isDone()) && this.isHoldingFirearm();
	}
	
	public void interruptFiring() {
		this.attackTime = this.attackIntervalMin;
	}

	public void start() {
		super.start();
		
		this.attackTime = this.attackIntervalMin / 2;
		
		this.mob.setAggressive(true);
	}

	public void stop() {
		super.stop();
		this.mob.setAggressive(false);
		this.seeTime = 0;
		this.attackTime = -1;
		this.mob.stopUsingItem();
	}

	public boolean requiresUpdateEveryTick() {
		return true;
	}

	public void tick() {
		LivingEntity livingentity = this.mob.getTarget();
		if (livingentity != null) {
			double d0 = this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
			boolean flag = this.mob.getSensing().hasLineOfSight(livingentity);
			boolean flag1 = this.seeTime > 0;
			if (flag != flag1) {
				this.seeTime = 0;
			}

			if (flag) {
				++this.seeTime;
			} else {
				--this.seeTime;
			}

			if (!(d0 > (double)this.attackRadiusSqr) && this.seeTime >= 20) {
				this.mob.getNavigation().stop();
				++this.strafingTime;
			} else {
				this.mob.getNavigation().moveTo(livingentity, (this.mob.isPassenger()) ? this.speedModifier * 2: this.speedModifier);
				this.strafingTime = -1;
			}

			if (this.strafingTime >= 20) {
				if ((double)this.mob.getRandom().nextFloat() < 0.3D) {
					this.strafingClockwise = !this.strafingClockwise;
				}

				if ((double)this.mob.getRandom().nextFloat() < 0.3D) {
					this.strafingBackwards = !this.strafingBackwards;
				}

				this.strafingTime = 0;
			}

			if (this.strafingTime > -1) {
				if (d0 > (double)(this.attackRadiusSqr * 0.75F)) {
					this.strafingBackwards = false;
				} else if (d0 < (double)(this.attackRadiusSqr * 0.25F)) {
					this.strafingBackwards = true;
				}

				this.mob.getMoveControl().strafe(this.strafingBackwards ? -0.5F : 0.5F, this.strafingClockwise ? 0.5F : -0.5F);
				this.mob.lookAt(livingentity, 30.0F, 30.0F);
			} else {
				this.mob.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
			}

			if (this.mob.isUsingItem()) {
				if (!flag && this.seeTime < -60) {
					this.mob.stopUsingItem();
				} else if (flag) {
					int i = this.mob.getTicksUsingItem();
					if (i >= 20) {
						this.mob.stopUsingItem();
						this.mob.performRangedAttack(livingentity, 1.0f);
						//EntityFirearmHelper.performRangedAttack(this.mob, livingentity, 1.0f);
						this.attackTime = this.attackIntervalMin;
					}
				}
			} else if (--this.attackTime <= 0 && this.seeTime >= -60) {
				this.mob.startUsingItem(ProjectileUtil.getWeaponHoldingHand(this.mob, item -> item instanceof FirearmItem));
			}

		}
	}
}

