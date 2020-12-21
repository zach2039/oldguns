package zach2039.oldguns.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import zach2039.oldguns.api.artillery.ArtilleryEffect;
import zach2039.oldguns.common.network.util.FirearmEffectHelper;

public class MessageArtilleryEffect implements IMessage
{
	int shootingEntityId, parameter;
	ArtilleryEffect effectType;
	double posX, posY, posZ;
	float rotationPitch, rotationYaw;
	
	public MessageArtilleryEffect(Entity shootingEntity, ArtilleryEffect effect, double x, double y, double z, float pitch, float yaw, int parameter)
	{
		this.shootingEntityId = shootingEntity.getEntityId();
		this.parameter = parameter;
		this.effectType = effect;
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.rotationPitch = pitch;
		this.rotationYaw = yaw;
	}
	
	public MessageArtilleryEffect()
	{
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.shootingEntityId = buf.readInt();
		this.parameter = buf.readInt();
		this.effectType = ArtilleryEffect.values()[buf.readInt()];
		this.posX = buf.readDouble();
		this.posY = buf.readDouble();
		this.posZ = buf.readDouble();
		this.rotationPitch = buf.readFloat();
		this.rotationYaw = buf.readFloat();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.shootingEntityId);
		buf.writeInt(this.parameter);
		buf.writeInt(this.effectType.ordinal());
		buf.writeDouble(this.posX);
		buf.writeDouble(this.posY);
		buf.writeDouble(this.posZ);
		buf.writeFloat(this.rotationPitch);
		buf.writeFloat(this.rotationYaw);
	}

	public static class HandlerClient implements IMessageHandler<MessageArtilleryEffect, IMessage>
	{
		@Override
		public IMessage onMessage(MessageArtilleryEffect message, MessageContext ctx)
		{
			if (ctx.side == Side.CLIENT)
			{
				Minecraft.getMinecraft().addScheduledTask(() -> {
					/* Get world of client. */
					World world = Minecraft.getMinecraft().player.world;
					
					/* Only process effects if world isn't null. */
					if (world != null) 
					{
						/* Get information from message. */
						Entity entity = world.getEntityByID(message.shootingEntityId);
						ArtilleryEffect effect = message.effectType;
						double x = message.posX;
						double y = message.posY;
						double z = message.posZ;
						double pitch = message.rotationPitch;
						double yaw = message.rotationYaw;
						int parameter = message.parameter;
						
						/* Pick particle effect from message. */
						switch (effect)
						{
							case CANNON_SHOT:
								FirearmEffectHelper.doArtilleryShootEffect(world, entity, effect,
										x, y, z, pitch, yaw, parameter);
								break;
							default:
								break;
						}
					}
				});
			}
			return null;
		}
	}
}
