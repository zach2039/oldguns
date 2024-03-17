package com.zach2039.oldguns.mixin.accessors;

import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractSkeleton.class)
public interface AbstractSkeletonAccess {

	@Accessor("bowGoal")
	public RangedBowAttackGoal<AbstractSkeleton> getBowGoal();
	
	@Accessor("meleeGoal")
	public MeleeAttackGoal getMeleeGoal();
	
	@Accessor("bowGoal")
	public void setBowGoal(RangedBowAttackGoal<AbstractSkeleton> bowGoal);
	
	@Accessor("meleeGoal")
	public void setMeleeGoal(MeleeAttackGoal meleeGoal);
}
