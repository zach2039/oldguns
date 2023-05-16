package com.zach2039.oldguns.world.level.block;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.config.OldGunsConfig.NiterProductionSettings;
import com.zach2039.oldguns.init.ModCauldronInteractions;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.util.ModRegistryUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.Vec3;

public class LiquidNiterCauldronBlock extends LayeredCauldronBlock {
	
	private static final NiterProductionSettings NITER_PRODUCTION_SETTINGS = OldGunsConfig.SERVER.recipeSettings.blackPowderManufactureSettings.niterProductionSettings;
	
	public static final int MIN_FILL_LEVEL = 1;
	public static final int MAX_FILL_LEVEL = 3;
	public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL_CAULDRON;

	public LiquidNiterCauldronBlock() {
		super(BlockBehaviour.Properties.copy(Blocks.CAULDRON).randomTicks(), null, ModCauldronInteractions.OldGunsCauldronInteraction.LIQUID_NITER);
		this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, Integer.valueOf(1)));
	}
	
	@Override
	public void animateTick(BlockState state, Level level, BlockPos blockpos, RandomSource rand) {
		
		if (canBoil(level, blockpos)) {
			addParticlesAndSound(level, state, new Vec3(blockpos.getX(), blockpos.getY(), blockpos.getZ()), rand);
		}
	}

	private static boolean canBoil(Level level, BlockPos blockpos) {
		BlockState stateBelow = level.getBlockState(blockpos.below());
		
		if (NITER_PRODUCTION_SETTINGS.niterCauldronValidHeatSources.get().contains(ModRegistryUtil.getKey(stateBelow.getBlock()).toString())) {
			if (stateBelow.hasProperty(BlockStateProperties.LIT)) {
				if (stateBelow.getValue(BlockStateProperties.LIT)) {
					return true;
				}
			} else {
				return true;
			}
		}
		
		return false;			
	}
	
	private static void addParticlesAndSound(Level level, BlockState state, Vec3 vec, RandomSource rand) {
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
					level.playLocalSound(vec.x, vec.y, vec.z, SoundEvents.LAVA_POP, SoundSource.BLOCKS, 0.3F + (rand.nextFloat() / 8f), rand.nextFloat() * 2.0F, false);
					level.addParticle(ParticleTypes.BUBBLE_POP, 	
							vec.x + (rand.nextFloat() - 0.5f) + 0.5f, 
							vec.y + (rand.nextFloat() / 32f) + (i * 0.35f), 
							vec.z + (rand.nextFloat() - 0.5f) + 0.5f,
							0.0D, 0.1D, 0.0D);
				}
			}
		}
		
		if (f < 0.75F) {
			level.playLocalSound(vec.x, vec.y, vec.z, SoundEvents.REDSTONE_TORCH_BURNOUT, SoundSource.BLOCKS, 0.03F, rand.nextFloat() * 0.7F, false);
		}
	}
	
	@Override
	public void randomTick(BlockState state, ServerLevel level, BlockPos blockpos, RandomSource rand) {
		int fluidLevel = state.getValue(LEVEL);
		boolean canCrystalize = (fluidLevel > 0) && canBoil(level, blockpos);
		
		if (canCrystalize) {
			float difficulty = (float) Math.min(NITER_PRODUCTION_SETTINGS.niterCrystallizationDifficulty.get(), Float.MAX_VALUE);
			int crystalizeRoll = rand.nextInt((int)(Math.round(difficulty)) + 1);
			boolean allowCrystalization = (crystalizeRoll == 0);
			
			if (allowCrystalization) {
				level.playSound(null, blockpos.getX(), blockpos.getY(), blockpos.getZ(), SoundEvents.AMETHYST_CLUSTER_BREAK, SoundSource.BLOCKS, 0.5F, 1.0F);
				level.playSound(null, blockpos.getX(), blockpos.getY(), blockpos.getZ(), SoundEvents.POINTED_DRIPSTONE_PLACE, SoundSource.BLOCKS, 0.75F, 1.0F);
				
				dropNiter(level, blockpos);
				
				lowerFillLevel(state, level, blockpos);
			}
		}
	}
	
	public static void dropNiter(Level level, BlockPos blockpos) {
		int harvestAmt = Math.min(64, Math.max(1, level.random.nextInt(
				NITER_PRODUCTION_SETTINGS.niterCrystallizationAmountMin.get(),
				NITER_PRODUCTION_SETTINGS.niterCrystallizationAmountMax.get())
				));
		popResource(level, blockpos.above(), new ItemStack(ModItems.NITER.get(), harvestAmt));
	}
	
	@Override
	public void entityInside(BlockState state, Level level, BlockPos blockpos, Entity entity) {
		if (!level.isClientSide && this.isEntityInsideContent(state, blockpos, entity)) {
			if (entity.isOnFire()) {
				level.explode(null, blockpos.getX(), blockpos.getY() + 1, blockpos.getZ(), 3f, Level.ExplosionInteraction.BLOCK);
			} else {
				if (entity instanceof LivingEntity) {
					((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.POISON, 900, 0));
				}
			}
			
			OldGuns.LOGGER.debug("Recipes:");
			ModCauldronInteractions.OldGunsCauldronInteraction.LIQUID_NITER.forEach((j, k) -> OldGuns.LOGGER.debug(j + " " + k));
		}
	}
	
	@Override
	protected double getContentHeight(BlockState state) {
		return (6.0D + (double)state.getValue(LEVEL).intValue() * 3.0D) / 16.0D;
	}

}
