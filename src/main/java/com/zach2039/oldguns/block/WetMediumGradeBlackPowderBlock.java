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
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.server.ServerWorld;

public class WetMediumGradeBlackPowderBlock extends Block {

	private static final CorningProcessSettings CORNING_PROCESS_SETTINGS = OldGunsConfig.SERVER.recipeSettings.blackPowderManufactureSettings.corningProcessSettings;
	
	public WetMediumGradeBlackPowderBlock() {
		super(Block.Properties.of(ModMaterials.WET_BLACK_POWDER).strength(0.5F).sound(SoundType.SAND).randomTicks());
		this.registerDefaultState(this.stateDefinition.any());
	}
	
	private static boolean canDry(BlockState state, ServerWorld level, BlockPos blockpos) {
		return level.isDay() && (level.getBrightness(LightType.SKY, blockpos.above()) >= 12);			
	}
	
	@Override
	public void randomTick(BlockState state, ServerWorld level, BlockPos blockpos, Random rand) {
		boolean canDry = canDry(state, level, blockpos);
		
		if (canDry) {
			float difficulty = (float) Math.min(CORNING_PROCESS_SETTINGS.wetBlackPowderSunDryingDifficulty.get(), Float.MAX_VALUE);
			int dryRoll = rand.nextInt((int)(Math.round(difficulty)) + 1);
			boolean allowDrying = (dryRoll == 0);
			
			if (allowDrying) {
				level.setBlockAndUpdate(blockpos, ModBlocks.MEDIUM_GRADE_BLACK_POWDER_BLOCK.get().defaultBlockState());
			}
		}
	}
	
	@Override
	public BlockState updateShape(BlockState stateA, Direction direction, BlockState stateB, IWorld world, BlockPos blockposA, BlockPos blockposB) {
		return direction == Direction.DOWN && !stateA.canSurvive(world, blockposA) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateA, direction, stateB, world, blockposA, blockposB);
	}
}
