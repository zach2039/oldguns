package com.zach2039.oldguns.event;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.level.PistonEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OldGuns.MODID)
public class BlockEventHandler {

	

	@SubscribeEvent
	public static void onPistonEventPost(final PistonEvent.Pre event) {
		processBlackPowderCake(event);
	}

	private static void processBlackPowderCake(final PistonEvent.Pre event) {
		final LevelAccessor level = event.getLevel();
		final RandomSource rand = level.getRandom();
		if (event.getPistonMoveType().isExtend) {
			BlockPos pos = event.getFaceOffsetPos();
			BlockState stateAdj = level.getBlockState(pos);
			boolean hasWetBlackPowderReady = (
					stateAdj == ModBlocks.WET_MEDIUM_GRADE_BLACK_POWDER_BLOCK.get().defaultBlockState() ||
					stateAdj == ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_BLOCK.get().defaultBlockState());
			if (hasWetBlackPowderReady) {
				boolean hasObsidianBase = level.getBlockState(event.getFaceOffsetPos().relative(event.getDirection())) == Blocks.OBSIDIAN.defaultBlockState();
				if (hasObsidianBase) {
					if (!event.getLevel().isClientSide()) {
						ServerLevel serverLevel = (ServerLevel) level;
						serverLevel.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
						serverLevel.addFreshEntity(new ItemEntity(serverLevel, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_CAKE.get())));
					} else {
						Vec3 vec = new Vec3(pos.getX(), pos.getY(), pos.getZ());
						for (int i = 0; i < 5; i++) {
							level.addParticle(ParticleTypes.CLOUD, 
									vec.x + (rand.nextFloat() - 0.5f) + 0.5f, 
									vec.y + (rand.nextFloat() - 0.5f) + 0.5f, 
									vec.z + (rand.nextFloat() - 0.5f) + 0.5f,
									0.0D, 0.0D, 0.0D);
							((Level) level).playLocalSound(vec.x, vec.y, vec.z, SoundEvents.SLIME_BLOCK_BREAK, SoundSource.BLOCKS, 0.3F + (rand.nextFloat() / 8f), rand.nextFloat() * 0.5F, false);
						}
					}
				}
			}
		}
	}
}
