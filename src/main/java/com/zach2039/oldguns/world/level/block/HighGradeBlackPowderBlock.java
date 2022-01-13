package com.zach2039.oldguns.world.level.block;

import java.util.Random;

import com.zach2039.oldguns.init.ModBlocks;
import com.zach2039.oldguns.init.ModMaterials;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class HighGradeBlackPowderBlock extends FallingBlock {
	
	public HighGradeBlackPowderBlock() {
		super(BlockBehaviour.Properties.of(ModMaterials.BLACK_POWDER).strength(0.5F).sound(SoundType.SAND).randomTicks());
		this.registerDefaultState(this.stateDefinition.any());
	}
	
	@Override
	public void onLand(Level level, BlockPos blockpos, BlockState stateA, BlockState stateB, FallingBlockEntity p_52072_) {
		if (canGetWet(level, blockpos, stateB)) {
			level.setBlock(blockpos, ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_BLOCK.get().defaultBlockState(), 3);
		}

	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext stateContext) {
		BlockGetter blockgetter = stateContext.getLevel();
		BlockPos blockpos = stateContext.getClickedPos();
		BlockState blockstate = blockgetter.getBlockState(blockpos);
		return canGetWet(blockgetter, blockpos, blockstate) ? ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_BLOCK.get().defaultBlockState() : super.getStateForPlacement(stateContext);
	}

	private static boolean canGetWet(BlockGetter blockGet, BlockPos blockpos, BlockState state) {
		return canSolidify(state) || touchesLiquid(blockGet, blockpos);
	}

	private static boolean touchesLiquid(BlockGetter blockGet, BlockPos blockpos) {
		boolean flag = false;
		BlockPos.MutableBlockPos blockpos$mutableblockpos = blockpos.mutable();

		for(Direction direction : Direction.values()) {
			BlockState blockstate = blockGet.getBlockState(blockpos$mutableblockpos);
			if (direction != Direction.DOWN || canSolidify(blockstate)) {
				blockpos$mutableblockpos.setWithOffset(blockpos, direction);
				blockstate = blockGet.getBlockState(blockpos$mutableblockpos);
				if (canSolidify(blockstate) && !blockstate.isFaceSturdy(blockGet, blockpos, direction.getOpposite())) {
					flag = true;
					break;
				}
			}
		}

		return flag;
	}

	private static boolean canSolidify(BlockState p_52089_) {
		return p_52089_.getFluidState().is(FluidTags.WATER);
	}

	@Override
	public BlockState updateShape(BlockState stateA, Direction facing, BlockState stateB, LevelAccessor level, BlockPos blockposA, BlockPos blockposB) {
		return touchesLiquid(level, blockposA) ? ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_BLOCK.get().defaultBlockState() : super.updateShape(stateA, facing, stateB, level, blockposA, blockposB);
	}

	@Override
	public int getDustColor(BlockState state, BlockGetter blockGet, BlockPos blockpos) {
		return state.getMapColor(blockGet, blockpos).col;
	}
}
