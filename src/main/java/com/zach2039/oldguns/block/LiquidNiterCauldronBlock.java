package com.zach2039.oldguns.block;

import java.util.Random;

import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.config.OldGunsConfig.NiterProductionSettings;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.util.ModRandomUtil;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.CauldronBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class LiquidNiterCauldronBlock extends CauldronBlock {

	private static final NiterProductionSettings NITER_PRODUCTION_SETTINGS = OldGunsConfig.SERVER.recipeSettings.blackPowderManufactureSettings.niterProductionSettings;

	public static final int MIN_FILL_LEVEL = 1;
	public static final int MAX_FILL_LEVEL = 3;
	public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL_CAULDRON;

	public LiquidNiterCauldronBlock() {
		//super(Block.Properties.copy(Blocks.CAULDRON).randomTicks(), null, LiquidNiterInteraction.LIQUID_NITER);
		super(Block.Properties
				.copy(Blocks.CAULDRON)
				.randomTicks()
				);
		this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, Integer.valueOf(1)));
	}

	@Override
	public void animateTick(BlockState state, World level, BlockPos blockpos, Random rand) {

		if (canBoil(level, blockpos)) {
			addParticlesAndSound(level, state, new Vector3d(blockpos.getX(), blockpos.getY(), blockpos.getZ()), rand);
		}
	}

	private static boolean canBoil(World level, BlockPos blockpos) {
		Block blockBelow = level.getBlockState(blockpos.below()).getBlock();

		if (
				blockBelow == Blocks.FIRE || blockBelow == Blocks.LAVA ||
				(blockBelow == Blocks.CAMPFIRE && level.getBlockState(blockpos.below()).getValue(CampfireBlock.LIT)) ||
				(blockBelow == Blocks.SOUL_CAMPFIRE && level.getBlockState(blockpos.below()).getValue(CampfireBlock.LIT))
				) {
			return true;
		}

		return false;			
	}

	private static void addParticlesAndSound(World level, BlockState state, Vector3d vec, Random rand) {
		float f = rand.nextFloat();
		int i = state.getValue(LEVEL);

		if (f < 0.3F) {
			level.addParticle(ParticleTypes.CLOUD, 
					vec.x + (rand.nextFloat() - 0.5f) + 0.5f, 
					vec.y + 1.7f, 
					vec.z + (rand.nextFloat() - 0.5f) + 0.5f,
					0.0D, 0.025D, 0.0D);
			if (f < 0.17F) {
				level.addParticle(ParticleTypes.BUBBLE, 	
						vec.x + (rand.nextFloat() - 0.5f) + 0.5f, 
						vec.y + (rand.nextFloat() / 32f) + (i * 0.35f), 
						vec.z + (rand.nextFloat() - 0.5f) + 0.5f,
						0.0D, 0.1D, 0.0D);
				if (f < 0.10F) {
					level.playLocalSound(vec.x, vec.y, vec.z, SoundEvents.LAVA_POP, SoundCategory.BLOCKS, 0.3F + (rand.nextFloat() / 8f), rand.nextFloat() * 2.0F, false);
					level.addParticle(ParticleTypes.BUBBLE_POP, 	
							vec.x + (rand.nextFloat() - 0.5f) + 0.5f, 
							vec.y + (rand.nextFloat() / 32f) + (i * 0.35f), 
							vec.z + (rand.nextFloat() - 0.5f) + 0.5f,
							0.0D, 0.1D, 0.0D);
				}
			}
		}

		if (f < 0.75F) {
			level.playLocalSound(vec.x, vec.y, vec.z, SoundEvents.REDSTONE_TORCH_BURNOUT, SoundCategory.BLOCKS, 0.03F, rand.nextFloat() * 0.7F, false);
		}
	}

	@Override
	public void randomTick(BlockState state, ServerWorld level, BlockPos blockpos, Random rand) {
		int fluidLevel = state.getValue(LEVEL);
		boolean canCrystalize = (fluidLevel > 0) && canBoil(level, blockpos);

		if (canCrystalize) {
			float difficulty = (float) Math.min(NITER_PRODUCTION_SETTINGS.niterCrystalizationDifficulty.get(), Float.MAX_VALUE);
			int crystalizeRoll = rand.nextInt((int)(Math.round(difficulty)) + 1);
			boolean allowCrystalization = (crystalizeRoll == 0);

			if (allowCrystalization) {
				level.playSound(null, blockpos.getX(), blockpos.getY(), blockpos.getZ(), SoundEvents.GLASS_BREAK, SoundCategory.BLOCKS, 0.5F, 1.0F);
				level.playSound(null, blockpos.getX(), blockpos.getY(), blockpos.getZ(), SoundEvents.STONE_PLACE, SoundCategory.BLOCKS, 0.75F, 1.0F);

				dropNiter(level, blockpos);
				
				this.setWaterLevel(level, blockpos, state, fluidLevel - 1);
			}
		}
	}

	public static void dropNiter(World level, BlockPos blockpos) {
		int harvestCalc = ModRandomUtil.getRandMinMax(level.random, 
				NITER_PRODUCTION_SETTINGS.niterCrystalizationAmountMin.get(),
				NITER_PRODUCTION_SETTINGS.niterCrystalizationAmountMax.get()
				);

		int harvestAmt = Math.min(64, Math.max(1, harvestCalc));
		popResource(level, blockpos.above(), new ItemStack(ModItems.NITER.get(), harvestAmt));
	}

	@Override
	public void entityInside(BlockState state, World level, BlockPos blockpos, Entity entity) {
		int i = state.getValue(LEVEL);
		float f = (float)entity.getY() + (6.0F + (float)(3 * i)) / 16.0F;
		if (!level.isClientSide && i > 0 && entity.getY() <= (double)f) {
			if (entity.isOnFire()) {
				level.explode(null, blockpos.getX(), blockpos.getY() + 1, blockpos.getZ(), 3f, Explosion.Mode.BREAK);
			} else {
				if (entity instanceof LivingEntity) {
					((LivingEntity)entity).addEffect(new EffectInstance(Effects.POISON, 900, 0));
				}
			}
		}
	}

	@Override
	public ActionResultType use(BlockState state, World level, BlockPos blockpos, PlayerEntity player, Hand hand, BlockRayTraceResult result) {
		ItemStack itemstack = player.getItemInHand(hand);
		if (itemstack.isEmpty()) {
			return ActionResultType.PASS;
		} else {
			int fluidLevel = state.getValue(LEVEL);
			Item item = itemstack.getItem();
			if (item == ModItems.LIQUID_NITER.get()) {
				if (fluidLevel < 3 && !level.isClientSide) {
					if (!player.abilities.instabuild) {
						ItemStack itemstack3 = new ItemStack(Items.GLASS_BOTTLE);
						player.awardStat(Stats.USE_CAULDRON);
						player.setItemInHand(hand, itemstack3);
						if (player instanceof ServerPlayerEntity) {
							((ServerPlayerEntity)player).refreshContainer(player.inventoryMenu);
						}
					}

					level.playSound((PlayerEntity)null, blockpos, SoundEvents.BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
					this.setWaterLevel(level, blockpos, state, fluidLevel + 1);
				}

				return ActionResultType.sidedSuccess(level.isClientSide);
			} else if (item == Items.GLASS_BOTTLE) {
				if (fluidLevel > 0 && !level.isClientSide) {
					if (!player.abilities.instabuild) {
						ItemStack itemstack4 = new ItemStack(ModItems.LIQUID_NITER.get());
						player.awardStat(Stats.USE_CAULDRON);
						itemstack.shrink(1);
						if (itemstack.isEmpty()) {
							player.setItemInHand(hand, itemstack4);
						} else if (!player.inventory.add(itemstack4)) {
							player.drop(itemstack4, false);
						} else if (player instanceof ServerPlayerEntity) {
							((ServerPlayerEntity)player).refreshContainer(player.inventoryMenu);
						}
					}

					level.playSound((PlayerEntity)null, blockpos, SoundEvents.BOTTLE_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);

					this.setWaterLevel(level, blockpos, state, fluidLevel - 1);
				}

				return ActionResultType.sidedSuccess(level.isClientSide);
			}
		}
		return ActionResultType.PASS;
	}

	@Override
	public void setWaterLevel(World level, BlockPos blockpos, BlockState state, int fluidLevel) {
		
		if (fluidLevel == 0) {
			// Need this without cauldron interactions of 1.18 I think
			level.setBlockAndUpdate(blockpos, Blocks.CAULDRON.defaultBlockState());
		} else {
			level.setBlock(blockpos, state.setValue(LEVEL, Integer.valueOf(MathHelper.clamp(fluidLevel, 1, 3))), 2);	
		}
		
		level.updateNeighbourForOutputSignal(blockpos, this);
	}

}
