package com.zach2039.oldguns.block;

import java.util.Random;

import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.config.OldGunsConfig.CorningProcessSettings;
import com.zach2039.oldguns.init.ModBlocks;
import com.zach2039.oldguns.init.ModMaterials;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.WorldAccessor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.server.ServerWorld;

public class WetHighGradeBlackPowderBlock extends Block {
	private static final CorningProcessSettings CORNING_PROCESS_SETTINGS = OldGunsConfig.SERVER.recipeSettings.blackPowderManufactureSettings.corningProcessSettings;
	
	public WetHighGradeBlackPowderBlock() {
		super(BlockBehaviour.Properties.of(ModMaterials.WET_BLACK_POWDER).strength(0.5F).sound(SoundType.SAND).randomTicks());
		this.registerDefaultState(this.stateDefinition.any());
	}
	
	private static boolean canDry(BlockState state, ServerWorld level, BlockPos blockpos) {
		return level.isDay() && (level.getBrightness(LightLayer.SKY, blockpos.above()) >= 12);			
	}
	
	@Override
	public void randomTick(BlockState state, ServerWorld level, BlockPos blockpos, Random rand) {
		boolean canDry = canDry(state, level, blockpos);
		
		if (canDry) {
			float difficulty = (float) Math.min(CORNING_PROCESS_SETTINGS.wetBlackPowderSunDryingDifficulty.get(), Float.MAX_VALUE);
			int dryRoll = rand.nextInt((int)(Math.round(difficulty)) + 1);
			boolean allowDrying = (dryRoll == 0);
			
			if (allowDrying) {
				level.setBlockAndUpdate(blockpos, ModBlocks.HIGH_GRADE_BLACK_POWDER_BLOCK.get().defaultBlockState());
			}
		}
	}
	
	@Override
	public BlockState updateShape(BlockState p_51213_, Direction p_51214_, BlockState p_51215_, WorldAccessor p_51216_, BlockPos p_51217_, BlockPos p_51218_) {
		return p_51214_ == Direction.DOWN && !p_51213_.canSurvive(p_51216_, p_51217_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_51213_, p_51214_, p_51215_, p_51216_, p_51217_, p_51218_);
	}
}
