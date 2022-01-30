package com.zach2039.oldguns.world.entity.monster;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.level.Level;

public abstract class AbstractFirearmSkeleton extends AbstractSkeleton {

	protected AbstractFirearmSkeleton(EntityType<? extends AbstractFirearmSkeleton> type, Level level) {
		super(type, level);
	}

	@Override
	protected SoundEvent getStepSound() {
		 return SoundEvents.SKELETON_STEP;
	}
}
