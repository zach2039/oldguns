package com.zach2039.oldguns.network;

import java.util.function.Supplier;

import com.zach2039.oldguns.api.firearm.FirearmType.FirearmEffect;
import com.zach2039.oldguns.api.firearm.util.FirearmEffectHelper;
import com.zach2039.oldguns.world.item.firearm.FirearmItem;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

/**
 * Sent to the server by {@link FirearmItem} to display firing effects.
 */
public class FirearmEffectMessage {
	int shootingEntityId, parameter;
	FirearmEffect effectType;
	double posX, posY, posZ;
	float rotationPitch, rotationYaw;

	public FirearmEffectMessage(LivingEntity shooter, FirearmEffect effect, double x, double y, double z, float pitch, float yaw, int parameter) {
		this.shootingEntityId = shooter.getId();
		this.parameter = parameter;
		this.effectType = effect;
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.rotationPitch = pitch;
		this.rotationYaw = yaw;
	}
	
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

	public static void onMessage(FirearmEffectMessage message, final Supplier<NetworkEvent.Context> ctx)
	{
		ctx.get().enqueueWork(() -> {
			/* Get world of client. */
			Level world = Minecraft.getInstance().player.level;
			
			/* Only process effects if world isn't null. */
			if (world != null)
			{
				/* Get information from message. */
				Entity entity = world.getEntity(message.shootingEntityId);
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
		
		ctx.get().setPacketHandled(true);
	}
}
