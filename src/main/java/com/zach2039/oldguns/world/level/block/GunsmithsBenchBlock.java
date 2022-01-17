package com.zach2039.oldguns.world.level.block;

import com.zach2039.oldguns.world.inventory.menu.GunsmithsBenchMenu;

import net.minecraft.block.BlockState;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.state.DirectionProperty;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mirror;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;

public class GunsmithsBenchBlock extends Block {
	public static final Component CONTAINER_TITLE = new TranslatableComponent("container.gunsmiths_bench");
	public static final Property<Direction> FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);;
	
	public GunsmithsBenchBlock() {
		super(Block.Properties
				.of(Material.WOOD)
				.sound(SoundType.WOOD)
				.strength(3.0F)
				.dynamicShape()
				.noOcclusion()
				);
		
		this.registerDefaultState(getStateDefinition().any().setValue(FACING, Direction.NORTH));
	}
	
	@Override
	protected void createBlockStateDefinition(final StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
	
	@Override
	public BlockState rotate(final BlockState state, final LevelAccessor level, final BlockPos pos, final Rotation direction) {
		return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
	}
	
	@Override
	public RenderShape getRenderShape(BlockState p_51567_) {
		return RenderShape.MODEL;
	}
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx) {
		Direction direction = ctx.getHorizontalDirection().getOpposite();
		
		return this.defaultBlockState().setValue(FACING, direction);
	}
	
	@Override
	public BlockState mirror(BlockState blockstate, Mirror mirror) {
		return blockstate.setValue(FACING, mirror.mirror(blockstate.getValue(FACING)));
	}
	
	public InteractionResult use(BlockState blockstate, Level level, BlockPos blockpos, Player player, InteractionHand hand, BlockHitResult result) {
		if (level.isClientSide) {
			return InteractionResult.SUCCESS;
		} else {
			player.openMenu(blockstate.getMenuProvider(level, blockpos));
			player.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
			return InteractionResult.CONSUME;
		}
	}

	   public MenuProvider getMenuProvider(BlockState blockstate, Level level, BlockPos blockpos) {
	      return new SimpleMenuProvider((p_52229_, p_52230_, p_52231_) -> {
	         return new GunsmithsBenchMenu(p_52229_, p_52230_, ContainerLevelAccess.create(level, blockpos));
	      }, CONTAINER_TITLE);
	   }
}
