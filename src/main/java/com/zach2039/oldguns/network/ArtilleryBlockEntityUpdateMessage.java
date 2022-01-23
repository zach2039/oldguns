package com.zach2039.oldguns.network;

import java.util.function.Supplier;

import com.zach2039.oldguns.client.util.ClientUtil;
import com.zach2039.oldguns.world.item.firearm.FirearmItem;
import com.zach2039.oldguns.world.level.block.entity.StationaryArtilleryBlockEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

/**
 * Sent to the server by {@link FirearmItem} to display firing effects.
 */
public class ArtilleryBlockEntityUpdateMessage {
	
	BlockPos pos;
	CompoundTag tag;

	public ArtilleryBlockEntityUpdateMessage(BlockPos pos, CompoundTag tag) {
		this.pos = pos;
		this.tag = tag;
	}
	
	public static ArtilleryBlockEntityUpdateMessage decode(final FriendlyByteBuf buf) {		
		return new ArtilleryBlockEntityUpdateMessage(buf.readBlockPos(), buf.readAnySizeNbt());
	}

	public static void encode(final ArtilleryBlockEntityUpdateMessage message, final FriendlyByteBuf buf) {
		buf.writeBlockPos(message.pos);
		buf.writeNbt(message.tag);
	}

	public static void handle(final ArtilleryBlockEntityUpdateMessage message, final Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                if (ClientUtil.getClientPlayer() == null) return;
                
                /* Get world of client. */
				Level world = Minecraft.getInstance().player.level;
				
				/* Only process effects if world isn't null. */
				if (world != null) 
				{
					if (world.getBlockEntity(message.pos) != null) {
						BlockEntity be = world.getBlockEntity(message.pos);
						
						if (be instanceof StationaryArtilleryBlockEntity) {
							((StationaryArtilleryBlockEntity)be).readFromTag(message.tag);
						}
						
						//OldGuns.LOGGER.info(message.tag);
					}
				}
			});
		});

		ctx.get().setPacketHandled(true);
	}
}
