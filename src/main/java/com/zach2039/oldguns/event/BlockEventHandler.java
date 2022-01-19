package com.zach2039.oldguns.event;

import java.util.Random;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.init.ModBlocks;
import com.zach2039.oldguns.init.ModItems;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CauldronBlock;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.stats.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.PistonEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OldGuns.MODID)
public class BlockEventHandler {

	@SubscribeEvent
	public static void onCauldronClickedEvent(final PlayerInteractEvent.RightClickBlock event) {
		// Required in 1.16.5 due to lack of interceptable cauldron interactions
		PlayerEntity player = event.getPlayer();
		if (player != null) {
			World level = event.getWorld();
			BlockPos blockpos = event.getPos();
			BlockState state = event.getWorld().getBlockState(blockpos);
			if (state.getBlock() == Blocks.CAULDRON) {
				if (state.getValue(CauldronBlock.LEVEL) == 0) {
					Hand hand = event.getHand();
					if (player.getItemInHand(hand).getItem() == ModItems.LIQUID_NITER.get()) {
						if (event.getWorld().isClientSide()) {
							player.swing(hand);
						} else {
							if (!player.abilities.instabuild) {
								ItemStack itemstack3 = new ItemStack(Items.GLASS_BOTTLE);
								player.awardStat(Stats.USE_CAULDRON);
								player.setItemInHand(hand, itemstack3);
								if (player instanceof ServerPlayerEntity) {
									((ServerPlayerEntity)player).refreshContainer(player.inventoryMenu);
								}
							}
							level.playSound((PlayerEntity)null, blockpos, SoundEvents.BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
							event.getWorld().setBlockAndUpdate(blockpos, ModBlocks.LIQUID_NITER_CAULDRON.get().defaultBlockState());
						}
					}
				}
			}
		}
	}
	
	@SubscribeEvent
	public static void onPistonEventPost(final PistonEvent.Pre event) {
		processBlackPowderCake(event);
	}

	private static void processBlackPowderCake(PistonEvent.Pre event) {
		final IWorld level = event.getWorld();
		final Random rand = level.getRandom();
		if (event.getPistonMoveType().isExtend) {
			BlockPos pos = event.getFaceOffsetPos();
			BlockState stateAdj = level.getBlockState(pos);
			boolean hasWetBlackPowderReady = (
					stateAdj == ModBlocks.WET_MEDIUM_GRADE_BLACK_POWDER_BLOCK.get().defaultBlockState() ||
					stateAdj == ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_BLOCK.get().defaultBlockState());
			if (hasWetBlackPowderReady) {
				boolean hasObsidianBase = level.getBlockState(event.getFaceOffsetPos().relative(event.getDirection())) == Blocks.OBSIDIAN.defaultBlockState();
				if (hasObsidianBase) {
					if (!event.getWorld().isClientSide()) {
						World serverLevel = (World) level;
						serverLevel.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
						serverLevel.addFreshEntity(new ItemEntity(serverLevel, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_CAKE.get())));
					} else {
						Vector3d vec = new Vector3d(pos.getX(), pos.getY(), pos.getZ());
						for (int i = 0; i < 5; i++) {
							level.addParticle(ParticleTypes.CLOUD, 
									vec.x + (rand.nextFloat() - 0.5f) + 0.5f, 
									vec.y + (rand.nextFloat() - 0.5f) + 0.5f, 
									vec.z + (rand.nextFloat() - 0.5f) + 0.5f,
									0.0D, 0.0D, 0.0D);
							((World) level).playLocalSound(vec.x, vec.y, vec.z, SoundEvents.SLIME_BLOCK_BREAK, SoundCategory.BLOCKS, 0.3F + (rand.nextFloat() / 8f), rand.nextFloat() * 0.5F, false);
						}
					}
				}
			}
		}
	}
}
