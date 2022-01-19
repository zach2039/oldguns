package com.zach2039.oldguns.event;

import java.util.Random;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.init.ModBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.world.PistonEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OldGuns.MODID)
public class BlockEventHandler {

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
