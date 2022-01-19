package com.zach2039.oldguns.block;

import java.util.Random;

import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.config.OldGunsConfig.NiterProductionSettings;
import com.zach2039.oldguns.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.CauldronBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
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
		super(Block.Properties.copy(Blocks.CAULDRON).randomTicks());
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
		int harvestAmt = Math.min(NITER_PRODUCTION_SETTINGS.niterCrystalizationAmountMax.get(), 
				Math.max(1, level.random.nextInt(NITER_PRODUCTION_SETTINGS.niterCrystalizationAmountMin.get())
				));
		popResource(level, blockpos.above(), new ItemStack(ModItems.NITER.get(), harvestAmt));
	}
	
	@Override
	public void entityInside(BlockState state, World level, BlockPos blockpos, Entity entity) {
		int i = state.getValue(LEVEL);
		float f = (float)entity.getY() + (6.0F + (float)(3 * i)) / 16.0F;
		if (!level.isClientSide && entity.isOnFire() && i > 0 && entity.getY() <= (double)f) {
			if (entity.isOnFire()) {
				level.explode(null, blockpos.getX(), blockpos.getY() + 1, blockpos.getZ(), 3f, Explosion.Mode.BREAK);
			} else {
				if (entity instanceof LivingEntity) {
					((LivingEntity)entity).addEffect(new EffectInstance(Effects.POISON, 900, 0));
				}
			}
		}
	}
	
	//@Override
	//protected double getContentHeight(BlockState state) {
	//	return (6.0D + (double)state.getValue(LEVEL).intValue() * 3.0D) / 16.0D;
	//}

}
