package zach2039.oldguns.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import zach2039.oldguns.common.item.util.FirearmType.FirearmEffect;
import zach2039.oldguns.common.network.util.FirearmEffectHelper;

public class MessageFirearmEffect implements IMessage
{
	int shootingEntityId, parameter;
	FirearmEffect effectType;
	double posX, posY, posZ;
	float rotationPitch, rotationYaw;
	
	public MessageFirearmEffect(EntityLivingBase shootingEntity, FirearmEffect effect, double x, double y, double z, float pitch, float yaw, int parameter)
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
	
	public MessageFirearmEffect()
	{
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.shootingEntityId = buf.readInt();
		this.parameter = buf.readInt();
		this.effectType = FirearmEffect.values()[buf.readInt()];
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

	public static class HandlerClient implements IMessageHandler<MessageFirearmEffect, IMessage>
	{
		@Override
		public IMessage onMessage(MessageFirearmEffect message, MessageContext ctx)
		{
			Minecraft.getMinecraft().addScheduledTask(() -> {
				/* Get world of client. */
				World world = Minecraft.getMinecraft().world;
				
				/* Only process effects if world isn't null. */
				if (world != null) 
				{
					/* Get information from message. */
					Entity entity = world.getEntityByID(message.shootingEntityId);
					FirearmEffect effect = message.effectType;
					double x = message.posX;
					double y = message.posY;
					double z = message.posZ;
					double pitch = message.rotationPitch;
					double yaw = message.rotationYaw;
					int parameter = message.parameter;
					
					/* Pick particle effect from message. */
					switch (effect)
					{
						case SMALL_FIREARM_SHOOT:
						case MEDIUM_FIREARM_SHOOT:
						case LARGE_FIREARM_SHOOT:
							FirearmEffectHelper.doFirearmShootEffect(world, entity, effect,
									x, y, z, pitch, yaw, parameter);
							break;
						case MISFIRE:
						case MISFIRE_WET:
						case BREAK:
							FirearmEffectHelper.doFirearmMisfireEffect(world, entity, effect,
									x, y, z, pitch, yaw, parameter);
							break;
						default:
							break;
					}
				}
			});
			return null;
		}
	}
}
