package com.zach2039.oldguns.world.entity.ai.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;

public class TryAvoidWaterGoal extends Goal {
	private final PathfinderMob mob;
	private final double searchRadius;

	public TryAvoidWaterGoal(PathfinderMob mob, double searchRadius) {
		this.mob = mob;
		this.searchRadius = searchRadius;
	}

	public boolean canUse() {
		return this.mob.isInWater();
	}

	public void start() {
		BlockPos blockpos = null;

		for(BlockPos blockpos1 : BlockPos.betweenClosed(Mth.floor(this.mob.getX() - searchRadius), Mth.floor(this.mob.getY() - searchRadius), Mth.floor(this.mob.getZ() - searchRadius), Mth.floor(this.mob.getX() + searchRadius), Mth.floor(this.mob.getY() + searchRadius), Mth.floor(this.mob.getZ() + searchRadius))) {
			if (this.mob.level().getFluidState(blockpos1).isEmpty()) {
				blockpos = blockpos1;
				break;
			}
		}

		if (blockpos != null) {
			this.mob.getMoveControl().setWantedPosition((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), 1.0D);
		}

	}
}
