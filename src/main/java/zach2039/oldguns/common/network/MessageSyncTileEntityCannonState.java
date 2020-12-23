package zach2039.oldguns.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import zach2039.oldguns.api.artillery.ArtilleryType;
import zach2039.oldguns.api.artillery.FiringState;
import zach2039.oldguns.api.artillery.impl.IArtillery;
import zach2039.oldguns.api.artillery.impl.IArtilleryPowderable;
import zach2039.oldguns.common.OldGuns;

public class MessageSyncTileEntityCannonState implements IMessage {
	
	BlockPos blockPos;
	ArtilleryType type;
	FiringState firingState;
	int powderCharge;
	ItemStack loadedProjectile;
	int firingCooldown;
	
	public MessageSyncTileEntityCannonState(BlockPos blockPos, ArtilleryType type, FiringState firingState, int powderCharge, ItemStack loadedProjectile, int firingCooldown)
	{
		this.blockPos = blockPos;
		this.type = type;
		this.firingState = firingState;
		this.powderCharge = powderCharge;
		this.loadedProjectile = loadedProjectile;
		this.firingCooldown = firingCooldown;
	}
	
	public MessageSyncTileEntityCannonState()
	{
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.blockPos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
		this.type = ArtilleryType.values()[buf.readInt()];
		this.firingState = FiringState.values()[buf.readInt()];
		this.powderCharge = buf.readInt();
		this.loadedProjectile = new ItemStack(ByteBufUtils.readTag(buf));
		this.firingCooldown = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.blockPos.getX());
		buf.writeInt(this.blockPos.getY());
		buf.writeInt(this.blockPos.getZ());
		buf.writeInt(this.type.ordinal());
		buf.writeInt(this.firingState.ordinal());
		buf.writeInt(this.powderCharge);
		ByteBufUtils.writeTag(buf, this.loadedProjectile.serializeNBT());
		buf.writeInt(this.firingCooldown);
	}

	public static class HandlerClient implements IMessageHandler<MessageSyncTileEntityCannonState, IMessage>
	{
		@Override
		public IMessage onMessage(MessageSyncTileEntityCannonState message, MessageContext ctx)
		{
			if(ctx.side.isClient()) {
				IThreadListener thread = (IThreadListener) Minecraft.getMinecraft();
				thread.addScheduledTask(new Runnable() {
	
					@Override
					public void run() {
						if(Minecraft.getMinecraft().world.getTileEntity(message.blockPos) != null) 
						{
							TileEntity te = Minecraft.getMinecraft().world.getTileEntity(message.blockPos);
							
							if (te instanceof IArtillery && te instanceof IArtilleryPowderable)
							{
								IArtillery art = (IArtillery) te;
								IArtilleryPowderable artp = (IArtilleryPowderable) te;
								art.setArtilleryType(message.type);
								art.setFiringState(message.firingState);
								artp.setPowderCharge(message.powderCharge);
								art.setLoadedProjectile(message.loadedProjectile);
								art.setFiringCooldown(message.firingCooldown);
								OldGuns.logger.debug("Packet has been received on " + ctx.side);
								OldGuns.logger.debug("    artilleryType : " + message.type);
								OldGuns.logger.debug("      firingState : " + message.firingState);
								OldGuns.logger.debug("     powderCharge : " + message.powderCharge);
								OldGuns.logger.debug(" loadedProjectile : " + message.loadedProjectile);
							}
							else
							{
								OldGuns.logger.error("Tile entity rotation sync packet tried to sync a tile entity that does not implement IArtillery and IArtilleryPowderable");
							}
						}
					}
						
				});
			}
			return null;
		}
	}
}
