package com.zach2039.oldguns.block;

import java.util.Random;

import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.config.OldGunsConfig.CorningProcessSettings;
import com.zach2039.oldguns.init.ModBlocks;
import com.zach2039.oldguns.init.ModMaterials;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.LightType;
import net.minecraft.world.server.ServerWorld;

public class WetHighGradeBlackPowderCakeBlock extends Block {
	private static final CorningProcessSettings CORNING_PROCESS_SETTINGS = OldGunsConfig.SERVER.recipeSettings.blackPowderManufactureSettings.corningProcessSettings;

	public static final BooleanProperty DRY = BooleanProperty.create("dry");

	public WetHighGradeBlackPowderCakeBlock() {
		super(Block.Properties.of(ModMaterials.BLACK_POWDER_CAKE).strength(0.5F).sound(SoundType.SAND).randomTicks());
		this.registerDefaultState(this.stateDefinition.any().setValue(DRY, Boolean.valueOf(false)));
	}

	private static boolean canDry(BlockState state, ServerWorld level, BlockPos blockpos) {
		return level.isDay() && (level.getBrightness(LightType.SKY, blockpos) >= 12);			
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos blockpos, ISelectionContext ctx) {
		return Block.box(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D);
	}

	@Override
	public BlockState updateShape(BlockState stateA, Direction direction, BlockState stateB, IWorld world, BlockPos blockposA, BlockPos blockposB) {
		return direction == Direction.DOWN && !stateA.canSurvive(world, blockposA) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateA, direction, stateB, world, blockposA, blockposB);
	}

	@Override
	public void randomTick(BlockState state, ServerWorld level, BlockPos blockpos, Random rand) {
		boolean canDry = canDry(state, level, blockpos);

		if (canDry) {
			float difficulty = (float) Math.min(CORNING_PROCESS_SETTINGS.wetBlackPowderSunDryingDifficulty.get(), Float.MAX_VALUE);
			int dryRoll = rand.nextInt((int)(Math.round(difficulty)) + 1);
			boolean allowDrying = (dryRoll == 0);

			if (allowDrying) {
				level.setBlockAndUpdate(blockpos, ModBlocks.HIGH_GRADE_BLACK_POWDER_CAKE.get().defaultBlockState());
			}
		}
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader level, BlockPos p_51211_) {
		return level.getBlockState(p_51211_.below()).getMaterial().isSolid();
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(DRY);
	}

	@Override
	public boolean isPathfindable(BlockState p_51193_, IBlockReader p_51194_, BlockPos p_51195_, PathType p_51196_) {
		return false;
	}
}
