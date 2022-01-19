package com.zach2039.oldguns.block;

import com.zach2039.oldguns.init.ModMaterials;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.pathfinding.PathType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class HighGradeBlackPowderCakeBlock extends Block {
	
	public HighGradeBlackPowderCakeBlock() {
		super(Block.Properties.of(ModMaterials.BLACK_POWDER_CAKE).strength(0.5F).sound(SoundType.SAND).randomTicks());
		this.registerDefaultState(this.stateDefinition.any());
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
	public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos blockpos, ISelectionContext ctx) {
		return Block.box(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D);
	}

	@Override
	public BlockState updateShape(BlockState stateA, Direction direction, BlockState stateB, IWorld world, BlockPos blockposA, BlockPos blockposB) {
		return direction == Direction.DOWN && !stateA.canSurvive(world, blockposA) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateA, direction, stateB, world, blockposA, blockposB);
	}
	
	@Override
	public boolean canSurvive(BlockState state, IWorldReader level, BlockPos p_51211_) {
		return level.getBlockState(p_51211_.below()).getMaterial().isSolid();
	}

	@Override
	public boolean isPathfindable(BlockState p_51193_, IBlockReader p_51194_, BlockPos p_51195_, PathType p_51196_) {
		return false;
	}
}
