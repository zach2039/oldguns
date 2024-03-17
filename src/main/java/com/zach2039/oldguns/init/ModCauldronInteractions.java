package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.world.item.crafting.cauldron.CauldronRecipe;
import com.zach2039.oldguns.world.item.crafting.cauldron.ICauldronRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.function.Supplier;


public class ModCauldronInteractions {
	
	public static class OldGunsCauldronInteraction implements CauldronInteraction {

		public static List<CauldronRecipe> RECIPES = new ArrayList<CauldronRecipe>();
		
		public static Map<Item, CauldronInteraction> LIQUID_NITER = CauldronInteraction.newInteractionMap();
		
		@Override
		public InteractionResult interact(BlockState pBlockState, Level pLevel, BlockPos pBlockPos, Player pPlayer,
				InteractionHand pHand, ItemStack pStack) {
			return InteractionResult.PASS;
		}
		
		public static void bootStrap() {
			// Bucket/Bottle Fill and Empty
			addCauldronBottleFillRecipe(
					new ResourceLocation(OldGuns.MODID, "fill_cauldron_from_niter_bottle"),
					CauldronInteraction.EMPTY,
					Fluids.EMPTY,
					ModItems.LIQUID_NITER_BOTTLE.get(),
					ModBlocks.LIQUID_NITER_CAULDRON.get().defaultBlockState()
					);
			addCauldronBucketFillRecipe(
					new ResourceLocation(OldGuns.MODID, "fill_cauldron_from_niter_bucket"),
					CauldronInteraction.EMPTY,
					Fluids.EMPTY,
					ModFluids.LIQUID_NITER.getBucket().get(),
					ModBlocks.LIQUID_NITER_CAULDRON.get().defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, Integer.valueOf(3))
					);
			addCauldronBottleFillRecipe(
					new ResourceLocation(OldGuns.MODID, "fill_niter_cauldron_from_niter_bottle"),
					LIQUID_NITER,
					ModFluids.LIQUID_NITER.getStill().get(),
					ModItems.LIQUID_NITER_BOTTLE.get(),
					ModBlocks.LIQUID_NITER_CAULDRON.get().defaultBlockState()
					);
			addCauldronBucketFillRecipe(
					new ResourceLocation(OldGuns.MODID, "fill_niter_cauldron_from_niter_bucket"),
					LIQUID_NITER,
					ModFluids.LIQUID_NITER.getStill().get(),
					ModFluids.LIQUID_NITER.getBucket().get(),
					ModBlocks.LIQUID_NITER_CAULDRON.get().defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, Integer.valueOf(3))
					);
			addCauldronBottleEmptyRecipe(
					new ResourceLocation(OldGuns.MODID, "empty_niter_cauldron_to_niter_bottle"),
					LIQUID_NITER,
					ModFluids.LIQUID_NITER.getStill().get(),
					ModItems.LIQUID_NITER_BOTTLE.get()
					);
			addCauldronBucketEmptyRecipe(
					new ResourceLocation(OldGuns.MODID, "empty_niter_cauldron_to_niter_bucket"),
					LIQUID_NITER,
					ModFluids.LIQUID_NITER.getStill().get(),
					ModFluids.LIQUID_NITER.getBucket().get()
					);
			
			// Wetting of Black Powder
			addCauldronWettingRecipe(
					new ResourceLocation(OldGuns.MODID, "wet_high_grade_black_powder"),
					CauldronInteraction.WATER,
					Fluids.WATER,
					ModBlocks.HIGH_GRADE_BLACK_POWDER_BLOCK.get().asItem(), 
					new ItemStack(ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_BLOCK.get()), 
					true,
					() -> Boolean.TRUE
					);
			addCauldronWettingRecipe(
					new ResourceLocation(OldGuns.MODID, "wet_medium_grade_black_powder"),
					CauldronInteraction.WATER,
					Fluids.WATER,
					ModBlocks.MEDIUM_GRADE_BLACK_POWDER_BLOCK.get().asItem(), 
					new ItemStack(ModBlocks.WET_MEDIUM_GRADE_BLACK_POWDER_BLOCK.get()), 
					true,
					() -> Boolean.TRUE
					);
			
			// Match Cord from String
			addCauldronConvertRecipe(
					new ResourceLocation(OldGuns.MODID, "convert_string_to_match_cord_niter_cauldron"),
					LIQUID_NITER,
					ModFluids.LIQUID_NITER.getStill().get(),
					Items.STRING, 
					new ItemStack(ModItems.MATCH_CORD.get()), 
					1,
					true,
					() -> Boolean.TRUE
					);
			
			// Mercury from Gold
			addCauldronConvertRecipe(
					new ResourceLocation(OldGuns.MODID, "convert_raw_gold_to_mercury_nugget_niter_cauldron"),
					LIQUID_NITER,
					ModFluids.LIQUID_NITER.getStill().get(),
					Items.RAW_GOLD, 
					new ItemStack(ModItems.MERCURY_NUGGET.get(), 3),
					3,
					true,
					OldGunsConfig.SERVER.recipeSettings.blackPowderManufactureSettings.mercuryProductionSettings.allowMercuryFromGoldAtNiterCauldronCrafting
					);
			addCauldronConvertRecipe(
					new ResourceLocation(OldGuns.MODID, "convert_raw_gold_block_to_mercury_nugget_niter_cauldron"),
					LIQUID_NITER,
					ModFluids.LIQUID_NITER.getStill().get(),
					Blocks.RAW_GOLD_BLOCK.asItem(), 
					new ItemStack(ModItems.MERCURY_NUGGET.get(), 27),
					27,
					true,
					OldGunsConfig.SERVER.recipeSettings.blackPowderManufactureSettings.mercuryProductionSettings.allowMercuryFromGoldAtNiterCauldronCrafting
					);
			
			// Mercury from Redstone
			addCauldronConvertRecipe(
					new ResourceLocation(OldGuns.MODID, "convert_redstone_to_mercury_nugget_niter_cauldron"),
					LIQUID_NITER,
					ModFluids.LIQUID_NITER.getStill().get(),
					Items.REDSTONE, 
					new ItemStack(ModItems.MERCURY_NUGGET.get()),
					1,
					true,
					OldGunsConfig.SERVER.recipeSettings.blackPowderManufactureSettings.mercuryProductionSettings.allowMercuryFromRedstoneAtNiterCauldronCrafting
					);
			addCauldronConvertRecipe(
					new ResourceLocation(OldGuns.MODID, "convert_redstone_block_to_mercury_nugget_niter_cauldron"),
					LIQUID_NITER,
					ModFluids.LIQUID_NITER.getStill().get(),
					Blocks.REDSTONE_BLOCK.asItem(), 
					new ItemStack(ModItems.MERCURY_NUGGET.get(), 9),
					9,
					true,
					OldGunsConfig.SERVER.recipeSettings.blackPowderManufactureSettings.mercuryProductionSettings.allowMercuryFromRedstoneAtNiterCauldronCrafting
					);
		}
		
		public static boolean addCauldronBottleFillRecipe(ResourceLocation id, Map<Item, CauldronInteraction> targetMap, Fluid fluid, Item input, BlockState postBlockState)
	    {
			var output = new ItemStack(Items.GLASS_BOTTLE);
			
			targetMap.put(input,
					(state, level, blockpos, player, hand, stack) -> {
	        			return emptyBottle(level, blockpos, player, hand, output, postBlockState, SoundEvents.BOTTLE_EMPTY);
	        		}
					);
			
	        return addRecipe(new CauldronRecipe(id, new ItemStack(input), output, fluid));
	    }
		
		public static boolean addCauldronBucketFillRecipe(ResourceLocation id, Map<Item, CauldronInteraction> targetMap, Fluid fluid, Item input, BlockState postBlockState)
	    {
			var output = new ItemStack(Items.BUCKET);
			
			targetMap.put(input,
					(state, level, blockpos, player, hand, stack) -> {
	        			return emptyBucket(level, blockpos, player, hand, new ItemStack(input), postBlockState, SoundEvents.BUCKET_EMPTY);
	        		}
					);
			
	        return addRecipe(new CauldronRecipe(id, new ItemStack(input), output, fluid));
	    }
		
		public static boolean addCauldronBucketEmptyRecipe(ResourceLocation id, Map<Item, CauldronInteraction> targetMap, Fluid fluid, Item output)
	    {
			var input = new ItemStack(Items.BUCKET);
			
			targetMap.put(input.getItem(), 
					(state, level, blockpos, player, hand, stack) -> {
		    			return fillBucket(state, level, blockpos, player, hand, input, new ItemStack(output), (Predicate<BlockState>) (cauldronState) -> cauldronState.getValue(LayeredCauldronBlock.LEVEL) == 3, SoundEvents.BUCKET_FILL);
		    		}
					);
			
	        return addRecipe(new CauldronRecipe(id, input, new ItemStack(output), fluid));
	    }
		
		public static boolean addCauldronBottleEmptyRecipe(ResourceLocation id, Map<Item, CauldronInteraction> targetMap, Fluid fluid, Item output)
	    {		
			targetMap.put(Items.GLASS_BOTTLE,
					(state, level, blockpos, player, hand, stack) -> {
						return fillBottle(state, level, blockpos, player, hand, stack, new ItemStack(output), (Predicate<BlockState>) (cauldronState) -> cauldronState.getValue(LayeredCauldronBlock.LEVEL) > 0, SoundEvents.BOTTLE_FILL);
	        		}
					);
			
	        return addRecipe(new CauldronRecipe(id, new ItemStack(Items.GLASS_BOTTLE), new ItemStack(output), fluid));
	    }
		
		public static boolean addCauldronConvertRecipe(ResourceLocation id, Map<Item, CauldronInteraction> targetMap, Fluid fluid, Item input, ItemStack output, int multiplier, boolean lowerFillLevel, Supplier<Boolean> allowed)
	    {
			targetMap.put(input,
					(state, level, blockpos, player, hand, stack) -> {
	        			return convertItem(level, blockpos, player, hand, new ItemStack(input), output, multiplier, state, true, lowerFillLevel, allowed);
	        		}
					);
			
	        return addRecipe(new CauldronRecipe(id, new ItemStack(input), output, fluid));
	    }
		
		public static boolean addCauldronWettingRecipe(ResourceLocation id, Map<Item, CauldronInteraction> targetMap, Fluid fluid, Item input, ItemStack output, boolean lowerFillLevel, Supplier<Boolean> allowed)
	    {
			targetMap.put(input,
					(state, level, blockpos, player, hand, stack) -> {
	        			return convertItem(level, blockpos, player, hand, new ItemStack(input), output, 1, state, false, lowerFillLevel, allowed);
	        		}
					);
			
	        return addRecipe(new CauldronRecipe(id, new ItemStack(input), output, fluid));
	    }
		
		public static boolean addRecipe(CauldronRecipe recipe)
	    {
	        return RECIPES.add(recipe);
	    }
		
		public static ItemStack getOutput(ItemStack input)
	    {
	        if (input.isEmpty()) return ItemStack.EMPTY;

	        for (ICauldronRecipe recipe : RECIPES)
	        {
	            ItemStack output = recipe.getOutput(input);
	            if (!output.isEmpty())
	            {
	                return output;
	            }
	        }
	        return ItemStack.EMPTY;
	    }

	    public static boolean hasOutput(ItemStack input)
	    {
	        return !getOutput(input).isEmpty();
	    }
	    
	    static InteractionResult convertItem(Level level, BlockPos blockpos, Player player, InteractionHand hand, ItemStack input, ItemStack output, int multiplier, BlockState state, boolean fizz, boolean lowerFillLevel, Supplier<Boolean> allowed) {				
			if (!level.isClientSide) {
				
				// Config check here since threads are weird
				if (!allowed.get()) {
					return InteractionResult.PASS;
				}
				
				ItemStack stack = player.getItemInHand(hand);
				int amount = stack.getCount();
				ItemStack outputCopy = output.copy();
				outputCopy.setCount(amount * multiplier);
				stack.shrink(amount);
				player.addItem(outputCopy);
				player.awardStat(Stats.USE_CAULDRON);
				player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
				if (lowerFillLevel) {
					LayeredCauldronBlock.lowerFillLevel(state, level, blockpos);
				}
				level.playSound((Player)null, blockpos, SoundEvents.GENERIC_SPLASH, SoundSource.BLOCKS, 1.0F, 1.0F);
				if (fizz) { 
					level.levelEvent(1501, blockpos, 0);
				}
			}

			return InteractionResult.sidedSuccess(level.isClientSide);
	    }
	    
	    static InteractionResult emptyBottle(Level level, BlockPos blockpos, Player player, InteractionHand hand, ItemStack input, BlockState state, SoundEvent soundEvent) {
	    	final var fillOptional = level.getBlockState(blockpos).getOptionalValue(LayeredCauldronBlock.LEVEL);
	    	
	    	if (fillOptional.isPresent()) {
	    		if (fillOptional.get() == 3) {
	    			// Dont overfill
	    			return InteractionResult.PASS;
	    		}
	    	}
				
			if (!level.isClientSide) {
				if (!player.isCreative()) {
					player.getItemInHand(hand).shrink(1);
					player.addItem(new ItemStack(Items.GLASS_BOTTLE));
				}
				player.awardStat(Stats.USE_CAULDRON);
				player.awardStat(Stats.ITEM_USED.get(input.getItem()));
				if (level.getBlockState(blockpos).getBlock() != state.getBlock()) {
					level.setBlockAndUpdate(blockpos, state);	
				} else {
					level.setBlockAndUpdate(blockpos, level.getBlockState(blockpos).cycle(LayeredCauldronBlock.LEVEL));
				}
				level.playSound((Player)null, blockpos, soundEvent, SoundSource.BLOCKS, 1.0F, 1.0F);
				level.gameEvent((Entity)null, GameEvent.FLUID_PLACE, blockpos);
			}

			return InteractionResult.sidedSuccess(level.isClientSide);
	    }
	    
	    static InteractionResult emptyBucket(Level level, BlockPos blockpos, Player player, InteractionHand hand, ItemStack input, BlockState state, SoundEvent soundEvent) {
	    	if (!level.isClientSide) {
	    		Item item = input.getItem();
	    		player.setItemInHand(hand, ItemUtils.createFilledResult(input, player, new ItemStack(Items.BUCKET)));
	    		player.awardStat(Stats.FILL_CAULDRON);
	    		player.awardStat(Stats.ITEM_USED.get(item));
	    		level.setBlockAndUpdate(blockpos, state);
	    		level.playSound((Player)null, blockpos, soundEvent, SoundSource.BLOCKS, 1.0F, 1.0F);
	    		level.gameEvent((Entity)null, GameEvent.FLUID_PLACE, blockpos);
	    		}

	    	return InteractionResult.sidedSuccess(level.isClientSide);
	    }
		
		static InteractionResult fillBucket(BlockState state, Level level, BlockPos blockpos, Player player, InteractionHand hand, ItemStack inputStack, ItemStack outputStack, Predicate<BlockState> canFill, SoundEvent sound) {
			if (!canFill.test(state)) {
				return InteractionResult.PASS;
			} else {
				if (!level.isClientSide) {
					Item item = outputStack.getItem();
					player.setItemInHand(hand, ItemUtils.createFilledResult(inputStack, player, outputStack));
					player.awardStat(Stats.USE_CAULDRON);
					player.awardStat(Stats.ITEM_USED.get(item));
					level.setBlockAndUpdate(blockpos, Blocks.CAULDRON.defaultBlockState());
					level.playSound((Player)null, blockpos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
					level.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, blockpos);
				}
				return InteractionResult.sidedSuccess(level.isClientSide);
			}
		}
		
		static InteractionResult fillBottle(BlockState state, Level level, BlockPos blockpos, Player player, InteractionHand hand, ItemStack inputStack, ItemStack outputStack, Predicate<BlockState> canFill, SoundEvent sound) {
			if (!canFill.test(state)) {
				return InteractionResult.PASS;
			} else {
				if (!level.isClientSide) {
					Item item = outputStack.getItem();
					if (!player.isCreative()) {
						player.getItemInHand(hand).shrink(1);
						player.addItem(outputStack);
					}
					player.awardStat(Stats.USE_CAULDRON);
					player.awardStat(Stats.ITEM_USED.get(item));
					LayeredCauldronBlock.lowerFillLevel(state, level, blockpos);
					level.playSound((Player)null, blockpos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
					level.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, blockpos);
				}
				return InteractionResult.sidedSuccess(level.isClientSide);
			}
		}
		
	}
}
