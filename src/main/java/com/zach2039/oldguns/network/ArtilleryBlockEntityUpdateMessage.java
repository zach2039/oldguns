package com.zach2039.oldguns.network;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.client.util.ClientUtil;
import com.zach2039.oldguns.world.item.firearm.FirearmItem;
import com.zach2039.oldguns.world.level.block.entity.StationaryArtilleryBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

/**
 * Sent to the server by {@link FirearmItem} to display firing effects.
 */
public class ArtilleryBlockEntityUpdateMessage {

	private static final ArtilleryBlockEntityUpdateMessage INSTANCE = new ArtilleryBlockEntityUpdateMessage();

	public static ArtilleryBlockEntityUpdateMessage getInstance() {
		return INSTANCE;
	}

	public void handle(final ArtilleryBlockEntityUpdateMessage.Data data, final PlayPayloadContext ctx) {
		ctx.workHandler().submitAsync(() -> {
			if (FMLEnvironment.dist.isClient())
			{
				if (ClientUtil.getClientPlayer() == null) return;

				/* Get world of client. */
				Level world = Minecraft.getInstance().player.level();

				/* Only process effects if world isn't null. */
				if (world != null)
				{
					if (world.getBlockEntity(data.pos) != null) {
						BlockEntity be = world.getBlockEntity(data.pos);

						if (be instanceof StationaryArtilleryBlockEntity) {
							((StationaryArtilleryBlockEntity)be).readFromTag(data.tag);
						}

						//OldGuns.LOGGER.info(message.tag);
					}
				}
			}
		});
	}

	public record Data(BlockPos pos, CompoundTag tag) implements CustomPacketPayload {
		public static final ResourceLocation ID = new ResourceLocation(OldGuns.MODID, "artillery_te_update_data");

		public Data(final FriendlyByteBuf buf) {
			this(
					buf.readBlockPos(),
					buf.readNbt()
			);
		}

		@Override
		public void write(final FriendlyByteBuf buf) {
			buf.writeBlockPos(pos);
			buf.writeNbt(tag);
		}

		@Override
		public ResourceLocation id() {
			return ID;
		}
	}
}
