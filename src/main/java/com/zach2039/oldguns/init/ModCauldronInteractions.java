package com.zach2039.oldguns.init;

import java.util.Map;

import com.zach2039.oldguns.OldGuns;

import net.minecraft.block.BlockState;
import net.minecraft.client.audio.SoundSource;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = OldGuns.MODID, bus = Bus.MOD)
public class ModCauldronInteractions {

	public static class LiquidNiterInteraction implements CauldronInteraction {
		public static Map<Item, CauldronInteraction> LIQUID_NITER = CauldronInteraction.newInteractionMap();

		CauldronInteraction FILL_LIQUID_NITER = (state, level, blockpos, player, hand, stack) -> {
			return emptyBucket(level, blockpos, player, hand, stack, ModBlocks.LIQUID_NITER_CAULDRON.get().defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, Integer.valueOf(3)), SoundEvents.BUCKET_EMPTY);
		};;

		@Override
		public ActionResultType interact(BlockState state, World level, BlockPos blockpos, Player player,
				Hand hand, ItemStack stack) {
			// TODO Auto-generated method stub
			return null;
		}

		static void bootstrap() {
			CauldronInteraction.EMPTY.put(ModItems.LIQUID_NITER.get(), (state, level, blockpos, player, hand, stack) -> {
				if (!level.isClientSide) {
					Item item = stack.getItem();
					player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
					player.awardStat(Stats.USE_CAULDRON);
					player.awardStat(Stats.ITEM_USED.get(item));
					level.setBlockAndUpdate(blockpos, ModBlocks.LIQUID_NITER_CAULDRON.get().defaultBlockState());
					level.playSound((Player)null, blockpos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
					level.gameEvent((Entity)null, GameEvent.FLUID_PLACE, blockpos);
				}

				return ActionResultType.sidedSuccess(level.isClientSide);
			});
			
			CauldronInteraction.WATER.put(ModBlocks.MEDIUM_GRADE_BLACK_POWDER_BLOCK.get().asItem(), (state, level, blockpos, player, hand, stack) -> {
				if (!level.isClientSide) {
					Item item = stack.getItem();
					int amount = player.getItemInHand(hand).getCount();
					player.setItemInHand(hand, new ItemStack(ModBlocks.WET_MEDIUM_GRADE_BLACK_POWDER_BLOCK.get(), amount));
					player.awardStat(Stats.USE_CAULDRON);
					player.awardStat(Stats.ITEM_USED.get(item));
					LayeredCauldronBlock.lowerFillWorld(state, level, blockpos);
					level.playSound((Player)null, blockpos, SoundEvents.GENERIC_SPLASH, SoundSource.BLOCKS, 1.0F, 1.0F);
				}

				return ActionResultType.sidedSuccess(level.isClientSide);
			});
			
			CauldronInteraction.WATER.put(ModBlocks.HIGH_GRADE_BLACK_POWDER_BLOCK.get().asItem(), (state, level, blockpos, player, hand, stack) -> {
				if (!level.isClientSide) {
					Item item = stack.getItem();
					int amount = player.getItemInHand(hand).getCount();
					player.setItemInHand(hand, new ItemStack(ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_BLOCK.get(), amount));
					player.awardStat(Stats.USE_CAULDRON);
					player.awardStat(Stats.ITEM_USED.get(item));
					LayeredCauldronBlock.lowerFillWorld(state, level, blockpos);
					level.playSound((Player)null, blockpos, SoundEvents.GENERIC_SPLASH, SoundSource.BLOCKS, 1.0F, 1.0F);
				}

				return ActionResultType.sidedSuccess(level.isClientSide);
			});

			LIQUID_NITER.put(Items.GLASS_BOTTLE, (state, level, blockpos, player, hand, stack) -> {
				if (!level.isClientSide) {
					Item item = stack.getItem();
					player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(ModItems.LIQUID_NITER.get())));
					player.awardStat(Stats.USE_CAULDRON);
					player.awardStat(Stats.ITEM_USED.get(item));
					LayeredCauldronBlock.lowerFillWorld(state, level, blockpos);
					level.playSound((Player)null, blockpos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
					level.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, blockpos);
				}

				return ActionResultType.sidedSuccess(level.isClientSide);
			});
			LIQUID_NITER.put(ModItems.LIQUID_NITER.get(), (state, level, blockpos, player, hand, stack) -> {
				if (state.getValue(LayeredCauldronBlock.LEVEL) != 3) {
					if (!level.isClientSide) {
						player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
						player.awardStat(Stats.USE_CAULDRON);
						player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
						level.setBlockAndUpdate(blockpos, state.cycle(LayeredCauldronBlock.LEVEL));
						level.playSound((Player)null, blockpos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
						level.gameEvent((Entity)null, GameEvent.FLUID_PLACE, blockpos);
					}

					return ActionResultType.sidedSuccess(level.isClientSide);
				} else {
					return ActionResultType.PASS;
				}
			});
		}

		static ActionResultType emptyBucket(World level, BlockPos blockpos, Player player, Hand hand, ItemStack stack, BlockState state, SoundEvent soundEvent) {
			if (!level.isClientSide) {
				Item item = stack.getItem();
				player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.BUCKET)));
				player.awardStat(Stats.FILL_CAULDRON);
				player.awardStat(Stats.ITEM_USED.get(item));
				level.setBlockAndUpdate(blockpos, state);
				level.playSound((Player)null, blockpos, soundEvent, SoundSource.BLOCKS, 1.0F, 1.0F);
				level.gameEvent((Entity)null, GameEvent.FLUID_PLACE, blockpos);
			}

			return ActionResultType.sidedSuccess(level.isClientSide);
		}
	}

	/**
	 * Add this mod's cauldron interactions.
	 *
	 * @param event The common setup event
	 */
	@SubscribeEvent
	public static void registerBrewingRecipes(final FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			LiquidNiterInteraction.bootstrap();
		});
	}
}
