package com.zach2039.oldguns.world.level.block;

import java.util.List;
import java.util.Random;

import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.config.OldGunsConfig.NiterProductionSettings;
import com.zach2039.oldguns.init.ModItems;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;

public class NiterBeddingBlock extends Block {

	private static final NiterProductionSettings NITER_PRODUCTION_SETTINGS = OldGunsConfig.SERVER.recipeSettings.blackPowderManufactureSettings.niterProductionSettings;
	
	public static final int MAX_REFUSE_AMOUNT = 9;
	public static final Property<Integer> REFUSE_AMOUNT = IntegerProperty.create("refuse_amount", 0, MAX_REFUSE_AMOUNT);

	public NiterBeddingBlock() {
		super(Block.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL).randomTicks());
		registerDefaultState(stateDefinition.any().setValue(REFUSE_AMOUNT, Integer.valueOf(0)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(REFUSE_AMOUNT);
	}
	
	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos blockpos, RandomSource rand) {
		double locallyGeneratedRefuse = getNearbyAnimalRefuseAmount(level, blockpos);	
		
		tryNitrateLevelIncrease(state, level, blockpos, rand, (NITER_PRODUCTION_SETTINGS.niterBeddingRefuseGenerationDifficulty.get() / (locallyGeneratedRefuse + 1)));
	}

	@Override
	public void tick(BlockState state, ServerLevel level, BlockPos blockpos, RandomSource rand) {
		BlockPos posAbove = PointedDripstoneBlock.findStalactiteTipAboveCauldron(level, blockpos);
		if (posAbove != null) {
			Fluid fluid = PointedDripstoneBlock.getCauldronFillFluidType(level, posAbove);
			if (this.canReceiveStalactiteDrip(fluid)) {
				this.receiveStalactiteDrip(state, level, blockpos, fluid);
			}

		}
	}
	
	private boolean canReceiveStalactiteDrip(Fluid fluid) {
		return fluid == Fluids.WATER || fluid == Fluids.EMPTY;
	}
	
	private void receiveStalactiteDrip(BlockState state, Level level, BlockPos blockpos, Fluid fluid) {
		tryNitrateLevelIncrease(state, level, blockpos, level.getRandom(), (NITER_PRODUCTION_SETTINGS.niterBeddingDripstoneGenerationDifficulty.get()));
	}
	
	private void tryNitrateLevelIncrease(BlockState state, Level level, BlockPos blockpos, RandomSource rand, double difficulty) {
		int refuseAmount = state.getValue(REFUSE_AMOUNT);
		boolean isNirateSoilReady = (refuseAmount == MAX_REFUSE_AMOUNT);
		
		if (!isNirateSoilReady) {
			int advanceRefuseStageRoll = rand.nextInt((int)(difficulty) + 1);
			boolean allowLevelIncrease = (advanceRefuseStageRoll == 0);
			
			if (allowLevelIncrease) {
				refuseAmount += 1;
			}
		}
		
		if (refuseAmount >= getRequiredRefuseAmountForNitratedSoil()) {
			isNirateSoilReady = true;
		}
		
		level.setBlock(blockpos, state.setValue(REFUSE_AMOUNT, Integer.valueOf(refuseAmount)), 2);
	}
	
	public static void dropNitrateSoil(Level level, BlockPos blockpos) {
		int harvestAmt = Math.max(1, NITER_PRODUCTION_SETTINGS.niterBeddingHarvestAmount.get());
		popResource(level, blockpos.above(), new ItemStack(ModItems.NITRATE_SOIL.get(), harvestAmt));
	}
	
	@Override
	public InteractionResult use(final BlockState state, final Level level, final BlockPos blockpos, final Player player, final InteractionHand hand, final BlockHitResult rayTraceResult) {
		final ItemStack heldItem = player.getItemInHand(hand);
		int refuseAmount = state.getValue(REFUSE_AMOUNT);
		boolean isNirateSoilReady = (refuseAmount == MAX_REFUSE_AMOUNT);

		if (!heldItem.isEmpty()) {
			boolean isShovel = heldItem.canPerformAction(net.minecraftforge.common.ToolActions.SHOVEL_FLATTEN);

			if (isShovel && isNirateSoilReady) {
				level.playSound(player, blockpos.getX(), blockpos.getY(), blockpos.getZ(), SoundEvents.SHOVEL_FLATTEN, SoundSource.NEUTRAL, 1.0F, 1.0F);
				level.playSound(player, blockpos.getX(), blockpos.getY(), blockpos.getZ(), SoundEvents.SLIME_BLOCK_HIT, SoundSource.NEUTRAL, 1.0F, 1.0F);
				level.setBlockAndUpdate(blockpos, state.setValue(REFUSE_AMOUNT, 0));
				
				dropNitrateSoil(level, blockpos);
				
				heldItem.hurtAndBreak(1, player, (e) -> {
					e.broadcastBreakEvent(hand);
	            });	
				return InteractionResult.SUCCESS;
			}
		}

		return InteractionResult.PASS;
	}
	
	private double getNearbyAnimalRefuseAmount(Level level, BlockPos pos) {
		int animalEffectRadius = NITER_PRODUCTION_SETTINGS.niterBeddingAnimalRadius.get();
		
		if (animalEffectRadius == -1)
			return 0;
		
		double totalAnimalRefuseAmount = 0;
		double lowRefuseAnimalAmountPer = NITER_PRODUCTION_SETTINGS.lowRefuseAnimalGeneratedAmount.get();
		double highRefuseAnimalAmountPer = NITER_PRODUCTION_SETTINGS.highRefuseAnimalGeneratedAmount.get();
		
		List<? extends String> lowRefuseAnimalEntityNames = NITER_PRODUCTION_SETTINGS.lowRefuseAnimals.get();
		List<? extends String> highRefuseAnimalEntityNames = NITER_PRODUCTION_SETTINGS.highRefuseAnimals.get();
		
		AABB niterBedNitrateRadius = new AABB(
				pos.getX() - animalEffectRadius, pos.getY() + 1, pos.getZ() - animalEffectRadius, 
				pos.getX() + animalEffectRadius, pos.getY() + 2, pos.getZ() + animalEffectRadius);
		
		List<Animal> animalsInRange = level.getEntitiesOfClass(Animal.class, niterBedNitrateRadius);
		
		for (final Animal animal : animalsInRange) {
			String animalName = animal.getType().toString();
			
			if (lowRefuseAnimalEntityNames.contains(animalName)) {
				totalAnimalRefuseAmount += lowRefuseAnimalAmountPer;
			} else if (highRefuseAnimalEntityNames.contains(animalName)) {
				totalAnimalRefuseAmount += highRefuseAnimalAmountPer;
			}
		}
	
		return totalAnimalRefuseAmount;
	}
	
	private int getRequiredRefuseAmountForNitratedSoil() {
		return MAX_REFUSE_AMOUNT;
	}
}
