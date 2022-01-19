package com.zach2039.oldguns.block;

import java.util.List;
import java.util.Random;

import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.config.OldGunsConfig.NiterProductionSettings;
import com.zach2039.oldguns.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;

public class NiterBeddingBlock extends Block {

	private static final NiterProductionSettings NITER_PRODUCTION_SETTINGS = OldGunsConfig.SERVER.recipeSettings.blackPowderManufactureSettings.niterProductionSettings;
	
	public static final int MAX_REFUSE_AMOUNT = 9;
	public static final Property<Integer> REFUSE_AMOUNT = IntegerProperty.create("refuse_amount", 0, MAX_REFUSE_AMOUNT);

	public NiterBeddingBlock() {
		super(Block.Properties.of(Material.DIRT, MaterialColor.DIRT).strength(0.5F).sound(SoundType.GRAVEL).randomTicks());
		registerDefaultState(stateDefinition.any().setValue(REFUSE_AMOUNT, Integer.valueOf(0)));
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(REFUSE_AMOUNT);
	}
	
	@Override
	public void randomTick(BlockState state, ServerWorld level, BlockPos blockpos, Random rand) {
		double locallyGeneratedRefuse = getNearbyAnimalRefuseAmount(level, blockpos);	
		
		tryNitrateWorldIncrease(state, level, blockpos, rand, (NITER_PRODUCTION_SETTINGS.niterBeddingRefuseGenerationDifficulty.get() / (locallyGeneratedRefuse + 1)));
	}

	@Override
	public void tick(BlockState state, ServerWorld level, BlockPos blockpos, Random rand) {
		// TODO: Add alternative way to collect niter from caves since dripstone is 1.17 and later
		//BlockPos posAbove = PointedDripstoneBlock.findStalactiteTipAboveCauldron(level, blockpos);
		//if (posAbove != null) {
		//	Fluid fluid = PointedDripstoneBlock.getCauldronFillFluidType(level, posAbove);
		//	if (this.canReceiveStalactiteDrip(fluid)) {
		//		this.receiveStalactiteDrip(state, level, blockpos, fluid);
		//	}
		//}
	}
	
	//private boolean canReceiveStalactiteDrip(Fluid fluid) {
	//	return fluid == Fluids.WATER || fluid == Fluids.EMPTY;
	//}
	
	//private void receiveStalactiteDrip(BlockState state, World level, BlockPos blockpos, Fluid fluid) {
	//	tryNitrateWorldIncrease(state, level, blockpos, level.getRandom(), (NITER_PRODUCTION_SETTINGS.niterBeddingDripstoneGenerationDifficulty.get()));
	//}
	
	private void tryNitrateWorldIncrease(BlockState state, World level, BlockPos blockpos, Random rand, double difficulty) {
		int refuseAmount = state.getValue(REFUSE_AMOUNT);
		boolean isNirateSoilReady = (refuseAmount == MAX_REFUSE_AMOUNT);
		
		if (!isNirateSoilReady) {
			int advanceRefuseStageRoll = rand.nextInt((int)(difficulty) + 1);
			boolean allowWorldIncrease = (advanceRefuseStageRoll == 0);
			
			if (allowWorldIncrease) {
				refuseAmount += 1;
			}
		}
		
		if (refuseAmount >= getRequiredRefuseAmountForNitratedSoil()) {
			isNirateSoilReady = true;
		}
		
		level.setBlock(blockpos, state.setValue(REFUSE_AMOUNT, Integer.valueOf(refuseAmount)), 2);
	}
	
	public static void dropNitrateSoil(World level, BlockPos blockpos) {
		int harvestAmt = Math.max(1, NITER_PRODUCTION_SETTINGS.niterBeddingHarvestAmount.get());
		popResource(level, blockpos.above(), new ItemStack(ModItems.NITRATE_SOIL.get(), harvestAmt));
	}
	
	@Override
	public ActionResultType use(final BlockState state, final World level, final BlockPos blockpos, final PlayerEntity player, final Hand hand, final BlockRayTraceResult rayTraceResult) {
		final ItemStack heldItem = player.getItemInHand(hand);
		int refuseAmount = state.getValue(REFUSE_AMOUNT);
		boolean isNirateSoilReady = (refuseAmount == MAX_REFUSE_AMOUNT);

		if (!heldItem.isEmpty()) {
			boolean isShovel = (heldItem.getToolTypes().contains(ToolType.SHOVEL));

			if (isShovel && isNirateSoilReady) {
				level.playSound(player, blockpos.getX(), blockpos.getY(), blockpos.getZ(), SoundEvents.SHOVEL_FLATTEN, SoundCategory.NEUTRAL, 1.0F, 1.0F);
				level.playSound(player, blockpos.getX(), blockpos.getY(), blockpos.getZ(), SoundEvents.SLIME_BLOCK_HIT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
				level.setBlockAndUpdate(blockpos, state.setValue(REFUSE_AMOUNT, 0));
				
				dropNitrateSoil(level, blockpos);
				
				heldItem.hurtAndBreak(1, player, (e) -> {
					e.broadcastBreakEvent(hand);
	            });	
				return ActionResultType.SUCCESS;
			}
		}

		return ActionResultType.PASS;
	}
	
	private double getNearbyAnimalRefuseAmount(World level, BlockPos pos) {
		int animalEffectRadius = NITER_PRODUCTION_SETTINGS.niterBeddingAnimalRadius.get();
		
		if (animalEffectRadius == -1)
			return 0;
		
		double totalAnimalRefuseAmount = 0;
		double lowRefuseAnimalAmountPer = NITER_PRODUCTION_SETTINGS.lowRefuseAnimalGeneratedAmount.get();
		double highRefuseAnimalAmountPer = NITER_PRODUCTION_SETTINGS.highRefuseAnimalGeneratedAmount.get();
		
		List<String> lowRefuseAnimalEntityNames = NITER_PRODUCTION_SETTINGS.lowRefuseAnimals.get();
		List<String> highRefuseAnimalEntityNames = NITER_PRODUCTION_SETTINGS.highRefuseAnimals.get();
		
		AxisAlignedBB niterBedNitrateRadius = new AxisAlignedBB(
				pos.getX() - animalEffectRadius, pos.getY() + 1, pos.getZ() - animalEffectRadius, 
				pos.getX() + animalEffectRadius, pos.getY() + 2, pos.getZ() + animalEffectRadius);
		
		List<AnimalEntity> animalsInRange = level.getEntitiesOfClass(AnimalEntity.class, niterBedNitrateRadius);
		
		for (final AnimalEntity animal : animalsInRange) {
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
