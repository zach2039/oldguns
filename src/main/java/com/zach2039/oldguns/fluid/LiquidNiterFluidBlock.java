package com.zach2039.oldguns.fluid;

import java.util.Optional;
import java.util.Random;

import javax.annotation.Nonnull;

import com.zach2039.oldguns.fluid.group.FluidGroup;
import com.zach2039.oldguns.init.ModMaterials;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

public class LiquidNiterFluidBlock extends LiquidBlock {

	public LiquidNiterFluidBlock(java.util.function.Supplier<? extends FlowingFluid> fluidSupplier) {
		super(fluidSupplier, FluidGroup.defaultBlockProperties(ModMaterials.LIQUID_NITER));
	}

	@Override
	public void entityInside(@Nonnull BlockState state, @Nonnull Level worldIn, @Nonnull BlockPos pos, @Nonnull Entity entity)
	{
		super.entityInside(state, worldIn, pos, entity);
		
		if (entity instanceof LivingEntity)
			((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.POISON, 900, 0));
	}
	
	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		int flammability = 100;
        return flammability;
    }
	
	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
		int spread = 250;
        return spread;
    }
	
	@Override
	public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos posAlt, boolean parm) {
		if (this.shouldSpreadLiquid(level, pos, state)) {
			level.scheduleTick(pos, state.getFluidState().getType(), this.getFluid().getTickDelay(level));
		}

	}

	private boolean shouldSpreadLiquid(Level level, BlockPos pos, BlockState state) {
		if (!level.isClientSide()) {
			oxidizeCopper((ServerLevel)level, pos);
		}
		
		return true;
	}
	
	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos pos, Random rand) {
		oxidizeCopper(level, pos);
	}
	
	private void oxidizeCopper(ServerLevel level, BlockPos pos) {
		for(Direction direction : POSSIBLE_FLOW_DIRECTIONS) {
			BlockPos blockpos = pos.relative(direction);
			BlockState blockstate = level.getBlockState(blockpos);
			if (blockstate.getBlock() instanceof WeatheringCopper) {
				for (int i = 0; i < 3; i++) {
					Optional<BlockState> optionalBlockState = ((WeatheringCopper)blockstate.getBlock()).getNext(blockstate);
					
					if (optionalBlockState.isPresent()) {
						level.setBlock(blockpos, ((WeatheringCopper)blockstate.getBlock()).getNext(blockstate).get(), 2);
						blockstate = level.getBlockState(blockpos);
					}
				}
				this.fizz(level, blockpos);
			}
		}
	}
	
	private void fizz(LevelAccessor level, BlockPos blockpos) {
		level.levelEvent(1501, blockpos, 0);
	}
}
