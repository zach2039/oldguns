package com.zach2039.oldguns.world.level.block;

import com.zach2039.oldguns.init.ModBlockEntities;
import com.zach2039.oldguns.world.item.equipment.BlastingPowderStickBlockItem;
import com.zach2039.oldguns.world.level.block.entity.BlastingPowderStickBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class BlastingPowderStickBlock extends BaseEntityBlock implements EntityBlock {
	protected static final int AABB_STANDING_OFFSET = 2;
	protected static final VoxelShape AABB = Block.box(6.0D, 0.0D, 6.0D, 10.0D, 10.0D, 10.0D);
	public static final BooleanProperty UNSTABLE = BlockStateProperties.UNSTABLE;

	public BlastingPowderStickBlock() {
		super(BlockBehaviour.Properties.of()
				.mapColor(MapColor.FIRE)
				.ignitedByLava()
				.noCollission()
				.instabreak()
				.sound(SoundType.GRASS));
		this.registerDefaultState(this.defaultBlockState().setValue(UNSTABLE, Boolean.valueOf(false)));
	}
	
	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos blockpos, CollisionContext collisionCtx) {
		return AABB;
	}

	@SuppressWarnings("deprecation")
	@Override
	public BlockState updateShape(BlockState stateA, Direction facing, BlockState stateB, LevelAccessor levelAccessor, BlockPos blockposA, BlockPos blockposB) {
		return facing == Direction.DOWN && !this.canSurvive(stateA, levelAccessor, blockposA) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateA, facing, stateB, levelAccessor, blockposA, blockposB);
	}

	@Override
	public boolean canSurvive(BlockState p_57499_, LevelReader p_57500_, BlockPos p_57501_) {
		return canSupportCenter(p_57500_, p_57501_.below(), Direction.UP);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos blockpos, RandomSource rand) {
		BlockEntity be = level.getBlockEntity(blockpos);

		if (be.getType() == ModBlockEntities.BLASTING_POWDER_STICK.get()) {
			if (((BlastingPowderStickBlockEntity) be).isLit()) {
				double d0 = (double)blockpos.getX() + 0.5D;
				double d1 = (double)blockpos.getY() + 0.7D;
				double d2 = (double)blockpos.getZ() + 0.5D;
				level.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
				level.addParticle(ParticleTypes.SMALL_FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	@Override
	public void onCaughtFire(BlockState state, Level level, BlockPos blockpos, @Nullable net.minecraft.core.Direction face, @Nullable LivingEntity igniter) {
		BlockEntity be = level.getBlockEntity(blockpos);

		if (be.getType() == ModBlockEntities.BLASTING_POWDER_STICK.get()) {
			if (!((BlastingPowderStickBlockEntity) be).isLit()) {
				((BlastingPowderStickBlockEntity) be).lightFuse();
				level.playSound(null, blockpos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1f, 1f);
				level.playSound(null, blockpos, SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1f, 1f);
			}
		}
	}

	@Override
	public void playerWillDestroy(Level level, BlockPos blockpos, BlockState state, Player player) {
		BlockEntity be = level.getBlockEntity(blockpos);
		
		if (be.getType() == ModBlockEntities.BLASTING_POWDER_STICK.get()) {
			if (((BlastingPowderStickBlockEntity) be).isLit()) {
				((BlastingPowderStickBlockEntity) be).explode();
			} else {				
				super.playerWillDestroy(level, blockpos, state, player);
			}
		}		
	}
	
	@Override
	public void onBlockExploded(BlockState state, Level level, BlockPos blockpos, Explosion explosion) {
        this.wasExploded(level, blockpos, explosion);
    }
	
	@Override
	public void wasExploded(Level level, BlockPos blockpos, Explosion explosion) {
		BlockEntity be = level.getBlockEntity(blockpos);

		if (be != null) {
			if (be.getType() == ModBlockEntities.BLASTING_POWDER_STICK.get()) {
				((BlastingPowderStickBlockEntity) be).explode();
			}
		}
    }

	@Override
	public void onProjectileHit(Level level, BlockState state, BlockHitResult hitResult, Projectile projectile) {
		BlockPos blockpos = hitResult.getBlockPos();
		Entity entity = projectile.getOwner();
		if (projectile.isOnFire() && projectile.mayInteract(level, blockpos)) {
			onCaughtFire(state, level, blockpos, null, entity instanceof LivingEntity ? (LivingEntity)entity : null);
		}
	}

	@Override
	public boolean canDropFromExplosion(BlockState state, BlockGetter world, BlockPos pos, Explosion explosion) {
		return this.dropFromExplosion(explosion);
	}

	@Override
	public boolean dropFromExplosion(Explosion explosion) {
		return false;
	}
	
	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos blockpos, Player player, InteractionHand hand, BlockHitResult hit) {
		ItemStack stack = player.getItemInHand(hand);
		BlockEntity be = level.getBlockEntity(blockpos);

		if (be.getType() == ModBlockEntities.BLASTING_POWDER_STICK.get()) {

			if (!stack.is(Items.FLINT_AND_STEEL) && !stack.is(Items.FIRE_CHARGE)) {
				return super.use(state, level, blockpos, player, hand, hit);
			} else {
				if (!((BlastingPowderStickBlockEntity) be).isLit()) {
					((BlastingPowderStickBlockEntity) be).lightFuse();
					level.playSound(null, blockpos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1f, 1f);
					level.playSound(null, blockpos, SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1f, 1f);

					Item item = stack.getItem();
					if (!player.isCreative()) {
						if (stack.is(Items.FLINT_AND_STEEL)) {
							stack.hurtAndBreak(1, player, (ctx) -> {
								ctx.broadcastBreakEvent(hand);
							});
						} else {
							stack.shrink(1);
						}
					}

					player.awardStat(Stats.ITEM_USED.get(item));
				}

				return InteractionResult.sidedSuccess(level.isClientSide);
			}		
		}

		return InteractionResult.PASS;
	}


	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
		return type == ModBlockEntities.BLASTING_POWDER_STICK.get() ? (level, pos, blockState, be) -> ((BlastingPowderStickBlockEntity) be).tick() : super.getTicker(world, state, type);
	}

	@Override
	public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
		super.setPlacedBy(world, pos, state, placer, itemStack);

		BlockEntity blockEnt = world.getBlockEntity(pos);

		if (blockEnt != null && blockEnt instanceof BlastingPowderStickBlockEntity && itemStack.getItem() instanceof BlastingPowderStickBlockItem) {
			((BlastingPowderStickBlockEntity) blockEnt).setPlacer(placer);
		}
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos blockpos, BlockState state) {
		return new BlastingPowderStickBlockEntity(blockpos, state);
	}
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(UNSTABLE);
	}
}
