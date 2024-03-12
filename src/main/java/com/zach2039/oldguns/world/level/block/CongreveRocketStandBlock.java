package com.zach2039.oldguns.world.level.block;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.Nullable;

import com.zach2039.oldguns.init.ModBlockEntities;
import com.zach2039.oldguns.util.ModVectorUtils;
import com.zach2039.oldguns.world.level.block.entity.CongreveRocketStandBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CongreveRocketStandBlock extends HorizontalDirectionalBlock implements EntityBlock {

	public static final DirectionProperty HORIZONTAL_ROTATION = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);
	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	private static final Map<Direction, VoxelShape> SHAPES = new EnumMap<>(Direction.class);
	private static final VoxelShape SHAPE = Shapes.or(
			Block.box(0, 0, 0, 16, 15, 16).move(0, 0, 0)
			);

	public CongreveRocketStandBlock() {
		super(Block.Properties.of()
				.mapColor(MapColor.WOOD)
				.ignitedByLava()
				.instrument(NoteBlockInstrument.BASS)
				.sound(SoundType.WOOD)
				.strength(3.0F)
				.dynamicShape()
				.noOcclusion()
				.lightLevel((e) -> {
					if (e.getValue(LIT).booleanValue())
						return 7;
					
					return 0;
				})
				);

		registerDefaultState(getStateDefinition().any().setValue(HORIZONTAL_ROTATION, Direction.NORTH).setValue(LIT, false));
		processShapes(SHAPE);
	}
	
	@Override
	public boolean canSurvive(BlockState state, LevelReader level, BlockPos blockpos) {
		return canSupportCenter(level, blockpos.below(), Direction.UP);
	}

	@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState stateAlt, LevelAccessor level, BlockPos blockpos, BlockPos blockposAlt) {
		return direction == Direction.DOWN && !state.canSurvive(level, blockpos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, direction, stateAlt, level, blockpos, blockposAlt);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPES.get(state.getValue(FACING));
	}
	
	@Override
	public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
		return SHAPES.get(state.getValue(FACING));
	}
	
	@Override
	protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(HORIZONTAL_ROTATION);
		builder.add(LIT);
	}

	@Override
	public BlockState rotate(final BlockState state, final LevelAccessor level, final BlockPos pos, final Rotation direction) {
		return state.setValue(HORIZONTAL_ROTATION, direction.rotate(state.getValue(HORIZONTAL_ROTATION)));
	}

	@Override
	public BlockState mirror(BlockState blockstate, Mirror mirror) {
		return blockstate.setValue(HORIZONTAL_ROTATION, mirror.mirror(blockstate.getValue(HORIZONTAL_ROTATION)));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		Direction direction = ctx.getHorizontalDirection();

		return this.defaultBlockState().setValue(HORIZONTAL_ROTATION, direction);
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos blockpos, Player player, InteractionHand hand, BlockHitResult result) {
		BlockEntity blockEnt = level.getBlockEntity(blockpos);

		if (blockEnt != null) {
			if (blockEnt instanceof CongreveRocketStandBlockEntity) {
				CongreveRocketStandBlockEntity rocketStandEnt = (CongreveRocketStandBlockEntity) blockEnt;

				rocketStandEnt.processInteraction(level, blockpos, player, hand);

				if (!level.isClientSide) {
					level.blockUpdated(blockpos, this);
					rocketStandEnt.setChanged();
				}

				return InteractionResult.SUCCESS;
			}
		}

		return InteractionResult.PASS;
	}

	@Override
	public void setPlacedBy(Level level, BlockPos blockpos, BlockState state, @Nullable LivingEntity livingEntity, ItemStack stackIn) {
		super.setPlacedBy(level, blockpos, state, livingEntity, stackIn);

		BlockEntity blockEnt = level.getBlockEntity(blockpos);
		if (blockEnt != null) {
			if (blockEnt instanceof CongreveRocketStandBlockEntity) {
				CongreveRocketStandBlockEntity rocketStandEnt = (CongreveRocketStandBlockEntity) blockEnt;

				rocketStandEnt.setFacing(livingEntity.getDirection());
				rocketStandEnt.setShotPitch(0f);
				//rocketStandEnt.setShotYaw(rocketStandEnt.getYawFromFacing());
				rocketStandEnt.setShotYaw(livingEntity.getYRot());

			}
		}
	}
	
	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(final Level level, final BlockState state, final BlockEntityType<T> blockEntityType) {
		return createTickerHelper(blockEntityType, ModBlockEntities.CONGREVE_ROCKET_STAND.get(), CongreveRocketStandBlockEntity::tick);
	}
	
	@SuppressWarnings("unchecked")
	@Nullable
	protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTickerHelper(
			final BlockEntityType<A> actualType, final BlockEntityType<E> requiredType,
			final BlockEntityTicker<? super E> ticker
	) {
		return requiredType == actualType ? (BlockEntityTicker<A>) ticker : null;
	}
	
	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos blockpos, BlockState state) {
		return new CongreveRocketStandBlockEntity(blockpos, state);
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}

	protected void processShapes(VoxelShape shape) {
		for (final Direction direction : Direction.values()) {
			SHAPES.put(direction, ModVectorUtils.calculateShapes(direction, shape));
		}
	}

}
