package com.zach2039.oldguns.block;

import com.zach2039.oldguns.inventory.menu.GunsmithsBenchMenu;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;


public class GunsmithsBenchBlock extends Block {
	public static final TranslationTextComponent CONTAINER_TITLE = new TranslationTextComponent("container.gunsmiths_bench");
	public static final Property<Direction> FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);;
	
	public GunsmithsBenchBlock() {
		super(Block.Properties
				.of(Material.WOOD)
				.sound(SoundType.WOOD)
				.strength(3.0F)
				.dynamicShape()
				.noOcclusion()
				.harvestTool(ToolType.AXE)
				);
		
		this.registerDefaultState(getStateDefinition().any().setValue(FACING, Direction.NORTH));
	}
	
	@Override
	protected void createBlockStateDefinition(final StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
	
	@Override
	public BlockState rotate(final BlockState state, final IWorld level, final BlockPos pos, final Rotation direction) {
		return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
	}
	
//	@Override
//	public RenderShape getRenderShape(BlockState p_51567_) {
//		return RenderShape.MODEL;
//	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx) {
		Direction direction = ctx.getHorizontalDirection().getOpposite();
		
		return this.defaultBlockState().setValue(FACING, direction);
	}
	
	@Override
	public BlockState mirror(BlockState blockstate, Mirror mirror) {
		return blockstate.setValue(FACING, mirror.mirror(blockstate.getValue(FACING)));
	}
	
	public ActionResultType use(BlockState blockstate, World level, BlockPos blockpos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
		if (level.isClientSide) {
			return ActionResultType.SUCCESS;
		} else {
			player.openMenu(blockstate.getMenuProvider(level, blockpos));
			player.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
			return ActionResultType.CONSUME;
		}
	}

	   public INamedContainerProvider getMenuProvider(BlockState blockstate, World level, BlockPos blockpos) {
	      return new SimpleNamedContainerProvider((p_52229_, p_52230_, p_52231_) -> {
	         return new GunsmithsBenchMenu(p_52229_, p_52230_, IWorldPosCallable.create(level, blockpos));
	      }, CONTAINER_TITLE);
	   }
}
