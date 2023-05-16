package com.zach2039.oldguns.world.level.block.entity;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TimedExplosiveBlockEntity extends BlockEntity {

	protected LivingEntity placer;
	protected boolean isLit = false;
	protected int fuseTime;
	
	public TimedExplosiveBlockEntity(BlockEntityType<?> type, BlockPos blockpos, BlockState state) {
		super(type, blockpos, state);
	}

	public void setPlacer(LivingEntity livingEntity) {
		this.placer = livingEntity;
	}

	public boolean isLit() {
		return this.isLit;
	}
	
	public void lightFuse() {
		Level level = this.getLevel();
		
		if (!level.isClientSide()) {
			this.isLit = true;
			this.fuseTime = 100 + level.random.nextInt(80);
		}
	}
	
	public void explode() {
		double x = this.getBlockPos().getX();
		double y = this.getBlockPos().getY();
		double z = this.getBlockPos().getZ();
		level.explode(null, x, y, z, 3.0F, Level.ExplosionInteraction.BLOCK);
		
		setRemoved();
	}
	
	public void tick() {
		Level level = this.getLevel();
		
		if (!level.isClientSide()) {
			if (this.isLit) {
				this.fuseTime--;
				
				if (this.fuseTime <= 0) {
					explode();
				}
			}
		}
	}
	
	@Override
    public void load(CompoundTag nbt) {
        if (nbt != null) {
            super.load(nbt);

            fuseTime = nbt.getInt("fuseTime");
            isLit = nbt.getBoolean("isLit");
            if (placer != null) {
            	placer = (LivingEntity) level.getEntity(nbt.getInt("placer"));
            }
        }
    }

    @Override
    public void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);

        nbt.putInt("fuseTime", fuseTime);
        nbt.putBoolean("isLit", isLit);
        if (nbt.contains("placer")) {
        	nbt.putInt("placer", placer.getId());
        }
    }
    
    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        this.load(pkt.getTag());
    }
}
