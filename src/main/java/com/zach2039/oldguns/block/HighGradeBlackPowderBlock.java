package com.zach2039.oldguns.block;

import java.util.Random;

import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.config.OldGunsConfig.CorningProcessSettings;
import com.zach2039.oldguns.init.ModBlocks;
import com.zach2039.oldguns.init.ModMaterials;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

public class HighGradeBlackPowderBlock extends FallingBlock {
	private static final CorningProcessSettings CORNING_PROCESS_SETTINGS = OldGunsConfig.SERVER.recipeSettings.blackPowderManufactureSettings.corningProcessSettings;
	
	public HighGradeBlackPowderBlock() {
		super(Block.Properties
				.of(ModMaterials.BLACK_POWDER)
				.strength(0.5F)
				.sound(SoundType.SAND)
				.randomTicks()
				.harvestTool(ToolType.SHOVEL)
				);
		this.registerDefaultState(this.stateDefinition.any());
	}
	
	@Override
	public void randomTick(BlockState state, ServerWorld level, BlockPos blockpos, Random rand) {
		boolean canGetWet = level.isRainingAt(blockpos.above());
		
		if (canGetWet) {
			float difficulty = (float) Math.min(CORNING_PROCESS_SETTINGS.blackPowderRainWettingDifficulty.get(), Float.MAX_VALUE);
			int wetRoll = rand.nextInt((int)(Math.round(difficulty)) + 1);
			boolean allowWet = (wetRoll == 0);
			
			if (allowWet) {
				level.setBlockAndUpdate(blockpos, ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_BLOCK.get().defaultBlockState());
			}
		}
	}
	
	@Override
	public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face)
    {
		int flammability = 100;
        return flammability;
    }
	
	@Override
	public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face)
    {
		int spread = 500;
        return spread;
    }
	
	@Override
	public void onLand(World level, BlockPos blockpos, BlockState stateA, BlockState stateB, FallingBlockEntity p_52072_) {
		if (canGetWet(level, blockpos, stateB)) {
			level.setBlock(blockpos, ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_BLOCK.get().defaultBlockState(), 3);
		} 
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext stateContext) {
		IBlockReader blockgetter = stateContext.getLevel();
		BlockPos blockpos = stateContext.getClickedPos();
		BlockState blockstate = blockgetter.getBlockState(blockpos);
		return canGetWet(blockgetter, blockpos, blockstate) ? ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_BLOCK.get().defaultBlockState() : super.getStateForPlacement(stateContext);
	}

	private static boolean canGetWet(IBlockReader blockGet, BlockPos blockpos, BlockState state) {
		return canSolidify(state) || touchesLiquid(blockGet, blockpos);
	}

	private static boolean touchesLiquid(IBlockReader blockGet, BlockPos blockpos) {
		boolean flag = false;
		Mutable blockpos$mutableblockpos = blockpos.mutable();

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
	public BlockState updateShape(BlockState stateA, Direction facing, BlockState stateB, IWorld level, BlockPos blockposA, BlockPos blockposB) {
		return touchesLiquid(level, blockposA) ? ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_BLOCK.get().defaultBlockState() : super.updateShape(stateA, facing, stateB, level, blockposA, blockposB);
	}

	@Override
	public int getDustColor(BlockState state, IBlockReader blockGet, BlockPos blockpos) {
		return state.getMapColor(blockGet, blockpos).col;
	}
}
