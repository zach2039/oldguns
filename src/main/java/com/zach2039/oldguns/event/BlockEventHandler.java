package com.zach2039.oldguns.event;

import java.util.Random;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.init.ModBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.client.audio.SoundSource;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.level.WorldAccessor;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.server.ServerWorld;
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
		final WorldAccessor level = event.getWorld();
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
						ServerWorld serverLevel = (ServerLevel) level;
						serverWorld.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
						serverWorld.addFreshEntity(new ItemEntity(serverLevel, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_CAKE.get())));
					} else {
						Vec3 vec = new Vec3(pos.getX(), pos.getY(), pos.getZ());
						for (int i = 0; i < 5; i++) {
							level.addParticle(ParticleTypes.CLOUD, 
									vec.x + (rand.nextFloat() - 0.5f) + 0.5f, 
									vec.y + (rand.nextFloat() - 0.5f) + 0.5f, 
									vec.z + (rand.nextFloat() - 0.5f) + 0.5f,
									0.0D, 0.0D, 0.0D);
							((World) level).playLocalSound(vec.x, vec.y, vec.z, SoundEvents.SLIME_BLOCK_BREAK, SoundSource.BLOCKS, 0.3F + (rand.nextFloat() / 8f), rand.nextFloat() * 0.5F, false);
						}
					}
				}
			}
		}
	}
}
