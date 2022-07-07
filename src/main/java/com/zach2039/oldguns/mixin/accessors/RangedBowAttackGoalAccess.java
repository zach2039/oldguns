package com.zach2039.oldguns.mixin.accessors;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;

@Mixin(RangedBowAttackGoal.class)
public interface RangedBowAttackGoalAccess {
	
	@Accessor("attackIntervalMin")
	int getAttackIntervalMin();
	
	@Accessor("attackTime")
	void setAttackTime(int attackTime);
}
