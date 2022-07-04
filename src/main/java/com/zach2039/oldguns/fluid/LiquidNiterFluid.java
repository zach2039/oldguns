package com.zach2039.oldguns.fluid;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class LiquidNiterFluid extends ForgeFlowingFluid {

	protected LiquidNiterFluid(Properties properties) {
		super(properties);
	}

	@Override
	public int getTickDelay(LevelReader level) {
		return 10;
	}

	@Override
	protected boolean isRandomlyTicking() {
		return true;
	}

	@Override
	protected void animateTick(Level level, BlockPos blockpos, FluidState state, RandomSource rand) {
		if (!state.isSource() && !state.getValue(FALLING)) {
			if (rand.nextInt(64) == 0) {
				level.playLocalSound((double)blockpos.getX() + 0.5D, (double)blockpos.getY() + 0.5D, (double)blockpos.getZ() + 0.5D, SoundEvents.WATER_AMBIENT, SoundSource.BLOCKS, rand.nextFloat() * 0.25F + 0.75F, rand.nextFloat() + 0.5F, false);
			}
		} else if (rand.nextInt(10) == 0) {
			level.addParticle(ParticleTypes.FIREWORK, (double)blockpos.getX() + rand.nextDouble(), (double)blockpos.getY() + rand.nextDouble(), (double)blockpos.getZ() + rand.nextDouble(), 0.0D, 0.05D, 0.0D);
		}

	}

	public static class Flowing extends LiquidNiterFluid {
		public Flowing(final Properties properties) {
			super(properties);
			registerDefaultState(getStateDefinition().any().setValue(LEVEL, 7));
		}

		@Override
		protected void createFluidStateDefinition(final StateDefinition.Builder<Fluid, FluidState> builder) {
			super.createFluidStateDefinition(builder);
			builder.add(LEVEL);
		}

		@Override
		public int getAmount(final FluidState state) {
			return state.getValue(LEVEL);
		}

		@Override
		public boolean isSource(final FluidState state) {
			return false;
		}
	}

	public static class Source extends LiquidNiterFluid {
		public Source(final Properties properties) {
			super(properties);
		}

		@Override
		public int getAmount(final FluidState state) {
			return 8;
		}

		@Override
		public boolean isSource(final FluidState state) {
			return true;
		}
	}
}
