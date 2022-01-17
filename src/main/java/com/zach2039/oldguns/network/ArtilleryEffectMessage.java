package com.zach2039.oldguns.network;

import java.util.function.Supplier;

import com.zach2039.oldguns.api.artillery.ArtilleryEffect;
import com.zach2039.oldguns.api.firearm.util.FirearmEffectHelper;
import com.zach2039.oldguns.client.util.ClientUtil;
import com.zach2039.oldguns.world.item.firearm.FirearmItem;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

/**
 * Sent to the server by {@link FirearmItem} to display firing effects.
 */
public class ArtilleryEffectMessage {
	int shootingEntityId, parameter;
	ArtilleryEffect effectType;
	double posX, posY, posZ;
	float rotationPitch, rotationYaw;

	public ArtilleryEffectMessage(LivingEntity shooter, ArtilleryEffect effect, double x, double y, double z, float pitch, float yaw, int parameter) {
		this.shootingEntityId = shooter.getId();
		this.parameter = parameter;
		this.effectType = effect;
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.rotationPitch = pitch;
		this.rotationYaw = yaw;
	}
	
	public static ArtilleryEffectMessage decode(final FriendlyByteBuf buf) {
		LivingEntity shooter = (LivingEntity) ClientUtil.getClientPlayer().level.getEntity(buf.readInt());
		int parameter = buf.readInt();
		ArtilleryEffect effectType = ArtilleryEffect.values()[buf.readInt()];
		double x = buf.readDouble();
		double y = buf.readDouble();
		double z = buf.readDouble();
		float pitch = buf.readFloat();
		float yaw = buf.readFloat();
		
		return new ArtilleryEffectMessage(shooter, effectType, x, y, z, pitch, yaw, parameter);
	}

	public static void encode(final ArtilleryEffectMessage message, final FriendlyByteBuf buf) {
		buf.writeInt(message.shootingEntityId);
		buf.writeInt(message.parameter);
		buf.writeInt(message.effectType.ordinal());
		buf.writeDouble(message.posX);
		buf.writeDouble(message.posY);
		buf.writeDouble(message.posZ);
		buf.writeFloat(message.rotationPitch);
		buf.writeFloat(message.rotationYaw);
	}

	public static void handle(final ArtilleryEffectMessage message, final Supplier<NetworkEvent.Context> ctx) {
		ctx.get().enqueueWork(() -> {
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                if (ClientUtil.getClientPlayer() == null) return;
                
                /* Get world of client. */
				World world = Minecraft.getInstance().player.level;
				
				/* Only process effects if world isn't null. */
				if (world != null) 
				{
					/* Get information from message. */
					Entity entity = world.getEntity(message.shootingEntityId);
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
		});

		ctx.get().setPacketHandled(true);
	}
}
