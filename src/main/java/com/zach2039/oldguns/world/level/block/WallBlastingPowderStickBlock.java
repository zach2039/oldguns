package com.zach2039.oldguns.world.level.block;

import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.zach2039.oldguns.init.ModBlockEntities;
import com.zach2039.oldguns.world.level.block.entity.BlastingPowderStickBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WallBlastingPowderStickBlock extends BlastingPowderStickBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	protected static final float AABB_OFFSET = 2.5F;
	private static final Map<Direction, VoxelShape> AABBS = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.box(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D), Direction.SOUTH, Block.box(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D), Direction.WEST, Block.box(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D), Direction.EAST, Block.box(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D)));

	public WallBlastingPowderStickBlock() {
		super();
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
	}

	@Override
	public String getDescriptionId() {
		return this.asItem().getDescriptionId();
	}

	@Override
	public VoxelShape getShape(BlockState blockstate, BlockGetter blockgetter, BlockPos blockpos, CollisionContext collisionCtx) {
		return getShape(blockstate);
	}

	public static VoxelShape getShape(BlockState blockstate) {
		return AABBS.get(blockstate.getValue(FACING));
	}

	@Override
	public boolean canSurvive(BlockState blockstate, LevelReader level, BlockPos p_58135_) {
		Direction direction = blockstate.getValue(FACING);
		BlockPos blockpos = p_58135_.relative(direction.getOpposite());
		BlockState mountedstate = level.getBlockState(blockpos);
		return mountedstate.isFaceSturdy(level, blockpos, direction);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		BlockState blockstate = this.defaultBlockState();
		LevelReader levelreader = ctx.getLevel();
		BlockPos blockpos = ctx.getClickedPos();
		Direction[] adirection = ctx.getNearestLookingDirections();

		for(Direction direction : adirection) {
			if (direction.getAxis().isHorizontal()) {
				Direction direction1 = direction.getOpposite();
				blockstate = blockstate.setValue(FACING, direction1);
				if (blockstate.canSurvive(levelreader, blockpos)) {
					return blockstate;
				}
			}
		}

		return null;
	}

	@Override
	public BlockState updateShape(BlockState stateA, Direction facing, BlockState stateB, LevelAccessor levelAccessor, BlockPos blockposA, BlockPos blockposB) {
		return facing.getOpposite() == stateA.getValue(FACING) && !stateA.canSurvive(levelAccessor, blockposA) ? Blocks.AIR.defaultBlockState() : stateA;
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos blockpos, Random rand) {
		BlockEntity be = level.getBlockEntity(blockpos);

		if (be.getType() == ModBlockEntities.BLASTING_POWDER_STICK.get()) {
			if (((BlastingPowderStickBlockEntity) be).isLit()) {
				Direction direction = state.getValue(FACING);
				double d0 = (double)blockpos.getX() + 0.5D;
				double d1 = (double)blockpos.getY() + 0.7D;
				double d2 = (double)blockpos.getZ() + 0.5D;
				double d3 = 0.22D;
				double d4 = 0.27D;
				Direction direction1 = direction.getOpposite();
				level.addParticle(ParticleTypes.SMOKE, d0 + 0.27D * (double)direction1.getStepX(), d1 + 0.22D, d2 + 0.27D * (double)direction1.getStepZ(), 0.0D, 0.0D, 0.0D);
				level.addParticle(ParticleTypes.SMALL_FLAME, d0 + 0.27D * (double)direction1.getStepX(), d1 + 0.22D, d2 + 0.27D * (double)direction1.getStepZ(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public BlockState rotate(BlockState state, Rotation facing) {
		return state.setValue(FACING, facing.rotate(state.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}
}
