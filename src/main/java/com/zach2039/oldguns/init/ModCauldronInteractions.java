package com.zach2039.oldguns.init;

import java.util.Map;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.item.crafting.recipe.LiquidNiterRecipe;

import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
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
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = OldGuns.MODID, bus = Bus.MOD)
public class ModCauldronInteractions {

	public static class LiquidNiterInteraction implements CauldronInteraction {
		public static Map<Item, CauldronInteraction> LIQUID_NITER = CauldronInteraction.newInteractionMap();

		CauldronInteraction FILL_LIQUID_NITER = (p_175683_, p_175684_, p_175685_, p_175686_, p_175687_, p_175688_) -> {
			return emptyBucket(p_175684_, p_175685_, p_175686_, p_175687_, p_175688_, ModBlocks.LIQUID_NITER_CAULDRON.get().defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, Integer.valueOf(3)), SoundEvents.BUCKET_EMPTY);
		};;

		@Override
		public InteractionResult interact(BlockState p_175711_, Level p_175712_, BlockPos p_175713_, Player p_175714_,
				InteractionHand p_175715_, ItemStack p_175716_) {
			// TODO Auto-generated method stub
			return null;
		}

		static void bootstrap() {
			CauldronInteraction.EMPTY.put(ModItems.LIQUID_NITER.get(), (p_175732_, p_175733_, p_175734_, p_175735_, p_175736_, p_175737_) -> {
				if (!p_175733_.isClientSide) {
					Item item = p_175737_.getItem();
					p_175735_.setItemInHand(p_175736_, ItemUtils.createFilledResult(p_175737_, p_175735_, new ItemStack(Items.GLASS_BOTTLE)));
					p_175735_.awardStat(Stats.USE_CAULDRON);
					p_175735_.awardStat(Stats.ITEM_USED.get(item));
					p_175733_.setBlockAndUpdate(p_175734_, ModBlocks.LIQUID_NITER_CAULDRON.get().defaultBlockState());
					p_175733_.playSound((Player)null, p_175734_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
					p_175733_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175734_);
				}

				return InteractionResult.sidedSuccess(p_175733_.isClientSide);
			});

			LIQUID_NITER.put(Items.GLASS_BOTTLE, (p_175718_, p_175719_, p_175720_, p_175721_, p_175722_, p_175723_) -> {
				if (!p_175719_.isClientSide) {
					Item item = p_175723_.getItem();
					p_175721_.setItemInHand(p_175722_, ItemUtils.createFilledResult(p_175723_, p_175721_, new ItemStack(ModItems.LIQUID_NITER.get())));
					p_175721_.awardStat(Stats.USE_CAULDRON);
					p_175721_.awardStat(Stats.ITEM_USED.get(item));
					LayeredCauldronBlock.lowerFillLevel(p_175718_, p_175719_, p_175720_);
					p_175719_.playSound((Player)null, p_175720_, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
					p_175719_.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, p_175720_);
				}

				return InteractionResult.sidedSuccess(p_175719_.isClientSide);
			});
			LIQUID_NITER.put(ModItems.LIQUID_NITER.get(), (p_175704_, p_175705_, p_175706_, p_175707_, p_175708_, p_175709_) -> {
				if (p_175704_.getValue(LayeredCauldronBlock.LEVEL) != 3) {
					if (!p_175705_.isClientSide) {
						p_175707_.setItemInHand(p_175708_, ItemUtils.createFilledResult(p_175709_, p_175707_, new ItemStack(Items.GLASS_BOTTLE)));
						p_175707_.awardStat(Stats.USE_CAULDRON);
						p_175707_.awardStat(Stats.ITEM_USED.get(p_175709_.getItem()));
						p_175705_.setBlockAndUpdate(p_175706_, p_175704_.cycle(LayeredCauldronBlock.LEVEL));
						p_175705_.playSound((Player)null, p_175706_, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
						p_175705_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175706_);
					}

					return InteractionResult.sidedSuccess(p_175705_.isClientSide);
				} else {
					return InteractionResult.PASS;
				}
			});
		}

		static InteractionResult emptyBucket(Level p_175619_, BlockPos p_175620_, Player p_175621_, InteractionHand p_175622_, ItemStack p_175623_, BlockState p_175624_, SoundEvent p_175625_) {
			if (!p_175619_.isClientSide) {
				Item item = p_175623_.getItem();
				p_175621_.setItemInHand(p_175622_, ItemUtils.createFilledResult(p_175623_, p_175621_, new ItemStack(Items.BUCKET)));
				p_175621_.awardStat(Stats.FILL_CAULDRON);
				p_175621_.awardStat(Stats.ITEM_USED.get(item));
				p_175619_.setBlockAndUpdate(p_175620_, p_175624_);
				p_175619_.playSound((Player)null, p_175620_, p_175625_, SoundSource.BLOCKS, 1.0F, 1.0F);
				p_175619_.gameEvent((Entity)null, GameEvent.FLUID_PLACE, p_175620_);
			}

			return InteractionResult.sidedSuccess(p_175619_.isClientSide);
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
