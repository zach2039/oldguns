package com.zach2039.oldguns.block;

import com.zach2039.oldguns.init.ModMaterials;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.WorldAccessor;
import net.minecraft.world.level.WorldReader;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;

public class HighGradeBlackPowderCakeBlock extends Block {
	
	public HighGradeBlackPowderCakeBlock() {
		super(BlockBehaviour.Properties.of(ModMaterials.BLACK_POWDER_CAKE).strength(0.5F).sound(SoundType.SAND).randomTicks());
		this.registerDefaultState(this.stateDefinition.any());
	}
	
	@Override
	public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face)
    {
		int flammability = 100;
        return flammability;
    }
	
	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter world, BlockPos pos, Direction face)
    {
		int spread = 500;
        return spread;
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
	public boolean canSurvive(BlockState state, WorldReader level, BlockPos p_51211_) {
		return level.getBlockState(p_51211_.below()).getMaterial().isSolid();
	}

	@Override
	public boolean isPathfindable(BlockState p_51193_, BlockGetter p_51194_, BlockPos p_51195_, PathComputationType p_51196_) {
		return false;
	}
}
