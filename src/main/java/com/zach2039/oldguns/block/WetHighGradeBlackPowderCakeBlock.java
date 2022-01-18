package com.zach2039.oldguns.block;

import java.util.Random;

import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.config.OldGunsConfig.CorningProcessSettings;
import com.zach2039.oldguns.init.ModBlocks;
import com.zach2039.oldguns.init.ModMaterials;

import net.minecraft.block.BlockState;
import net.minecraft.server.level.ServerWorld;
import net.minecraft.state.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.WorldAccessor;
import net.minecraft.world.level.WorldReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;

public class WetHighGradeBlackPowderCakeBlock extends Block {
	private static final CorningProcessSettings CORNING_PROCESS_SETTINGS = OldGunsConfig.SERVER.recipeSettings.blackPowderManufactureSettings.corningProcessSettings;

	public static final BooleanProperty DRY = BooleanProperty.create("dry");
	
	public WetHighGradeBlackPowderCakeBlock() {
		super(BlockBehaviour.Properties.of(ModMaterials.BLACK_POWDER_CAKE).strength(0.5F).sound(SoundType.SAND).randomTicks());
		this.registerDefaultState(this.stateDefinition.any().setValue(DRY, Boolean.valueOf(false)));
	}

	private static boolean canDry(BlockState state, ServerWorld level, BlockPos blockpos) {
		return level.isDay() && (level.getBrightness(LightLayer.SKY, blockpos) >= 12);			
	}
	
	@Override
	public VoxelShape getShape(BlockState p_51222_, BlockGetter p_51223_, BlockPos p_51224_, CollisionContext p_51225_) {
		return Block.box(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D);
	}
	
	@Override
	public BlockState updateShape(BlockState p_51213_, Direction p_51214_, BlockState p_51215_, WorldAccessor p_51216_, BlockPos p_51217_, BlockPos p_51218_) {
		return p_51214_ == Direction.DOWN && !p_51213_.canSurvive(p_51216_, p_51217_) ? Blocks.AIR.defaultBlockState() : super.updateShape(p_51213_, p_51214_, p_51215_, p_51216_, p_51217_, p_51218_);
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
	public boolean canSurvive(BlockState state, WorldReader level, BlockPos p_51211_) {
		return level.getBlockState(p_51211_.below()).getMaterial().isSolid();
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(DRY);
	}
	
	@Override
	public boolean isPathfindable(BlockState p_51193_, BlockGetter p_51194_, BlockPos p_51195_, PathComputationType p_51196_) {
		return false;
	}
}
