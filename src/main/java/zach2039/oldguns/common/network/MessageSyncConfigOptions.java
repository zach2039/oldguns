package zach2039.oldguns.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import zach2039.oldguns.api.artillery.impl.IArtillery;
import zach2039.oldguns.common.OldGuns;

public class MessageSyncConfigOptions implements IMessage {
	
	BlockPos blockPos;
	float barrelPitch;
	float barrelYaw;
	
	public MessageSyncConfigOptions(BlockPos blockPos, float rotationPitch, float rotationYaw)
	{
		this.blockPos = blockPos;
		this.barrelPitch = rotationPitch;
		this.barrelYaw = rotationYaw;
	}
	
	public MessageSyncConfigOptions()
	{
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.blockPos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
		this.barrelPitch = buf.readFloat();
		this.barrelYaw = buf.readFloat();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.blockPos.getX());
		buf.writeInt(this.blockPos.getY());
		buf.writeInt(this.blockPos.getZ());
		buf.writeFloat(this.barrelPitch);
		buf.writeFloat(this.barrelYaw);
	}

	public static class HandlerClient implements IMessageHandler<MessageSyncConfigOptions, IMessage>
	{
		@Override
		public IMessage onMessage(MessageSyncConfigOptions message, MessageContext ctx)
		{
			if(ctx.side.isClient()) {
				IThreadListener thread = (IThreadListener) Minecraft.getMinecraft();
				thread.addScheduledTask(new Runnable() {
	
					@Override
					public void run() {
						if(Minecraft.getMinecraft().world.getTileEntity(message.blockPos) != null) 
						{
							TileEntity te = Minecraft.getMinecraft().world.getTileEntity(message.blockPos);
							
							if (te instanceof IArtillery)
							{
								IArtillery art = (IArtillery) te;
								art.setBarrelPitch(message.barrelPitch);
								art.setBarrelYaw(message.barrelYaw);
							}
							else
							{
								OldGuns.logger.error("Tile entity rotation sync packet tried to sync a tile entity that does not implement IArtillery");
							}
						}
					}
						
				});
			}
			return null;
		}
	}
}
