package com.zach2039.oldguns.world.level.block.entity;

import com.zach2039.oldguns.init.ModBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class BlastingPowderStickBlockEntity extends TimedExplosiveBlockEntity {

	public BlastingPowderStickBlockEntity(BlockPos blockpos, BlockState state) {
		super(ModBlockEntities.BLASTING_POWDER_STICK.get(), blockpos, state);
	}
	
	@Override
	public void lightFuse() {
		Level level = this.getLevel();
		
		if (!level.isClientSide()) {
			this.fuseTime = 100 + level.random.nextInt(80);
		}
		
		this.isLit = true;
	}
	
	@Override
	public void explode() {
		Level level = this.getLevel();
		
		BlockPos pos = this.getBlockPos();
		double posX = pos.getX();
		double posY = pos.getY();
		double posZ = pos.getZ();
		
		if (!level.isClientSide()) {
			level.removeBlock(pos, false);
			
			Explosion exp = level.explode(null, posX, posY, posZ, 2.0F, Level.ExplosionInteraction.NONE);
			
			for (int x = -1; x < 2; x++) {
				for (int y = -1; y < 2; y++) {
					for (int z = -1; z < 2; z++) {
						BlockPos posOffset = pos.offset(x, y, z);
						level.getBlockState(posOffset).getBlock().wasExploded(level, posOffset, exp);
					}
				}
			}	
			
			for (int x = -1; x < 2; x++) {
				for (int y = -1; y < 2; y++) {
					for (int z = -1; z < 2; z++) {
						BlockPos posOffset = pos.offset(x, y, z);
						harvestBlockWithCheck(level, posOffset, exp);
					}
				}
			}			
			
			setRemoved();
		}
	}
	
	private void harvestBlockWithCheck(Level level, BlockPos blockpos, Explosion exp) {
		boolean canDestroy = level.getBlockState(blockpos).getExplosionResistance(level, blockpos, null) < 9.0f;
		boolean canDropFromExplosions = level.getBlockState(blockpos).getBlock().dropFromExplosion(exp);
		
		if (canDestroy) {
			level.destroyBlock(blockpos, canDropFromExplosions);
		}
	}
}
