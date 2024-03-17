package com.zach2039.oldguns.network;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.FirearmEffect;
import com.zach2039.oldguns.api.firearm.util.FirearmEffectHelper;
import com.zach2039.oldguns.client.util.ClientUtil;
import com.zach2039.oldguns.world.item.firearm.FirearmItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

/**
 * Sent to the server by {@link FirearmItem} to display firing effects.
 */
public class FirearmEffectMessage {
	private static final FirearmEffectMessage INSTANCE = new FirearmEffectMessage();

	public static FirearmEffectMessage getInstance() {
		return INSTANCE;
	}

	public void handle(final FirearmEffectMessage.Data data, final PlayPayloadContext ctx) {
		ctx.workHandler().submitAsync(() -> {
			if (FMLEnvironment.dist.isClient())
			{
				if (ClientUtil.getClientPlayer() == null) return;

				/* Get world of client. */
				Level world = ClientUtil.getClientPlayer().level();

				/* Only process effects if world isn't null. */
				if (world != null)
				{
					/* Get information from data. */
					Entity entity = data.shooter;
					FirearmEffect effect = data.effect;
					double x = data.x;
					double y = data.y;
					double z = data.z;
					double pitch = data.pitch;
					double yaw = data.yaw;
					int parameter = data.parameter;

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
			}
		});
	}

	public record Data(LivingEntity shooter, FirearmEffect effect, double x, double y, double z, float pitch, float yaw, int parameter) implements CustomPacketPayload {
		public static final ResourceLocation ID = new ResourceLocation(OldGuns.MODID, "firearm_effect_data");

		public Data(final FriendlyByteBuf buf) {
			this(
					(LivingEntity) ClientUtil.getClientPlayer().level().getEntity(buf.readInt()),
					FirearmEffect.values()[buf.readInt()],
					buf.readDouble(),
					buf.readDouble(),
					buf.readDouble(),
					buf.readFloat(),
					buf.readFloat(),
					buf.readInt()
			);
		}

		@Override
		public void write(final FriendlyByteBuf buf) {
			buf.writeInt(shooter.getId());
			buf.writeInt(effect.ordinal());
			buf.writeDouble(x);
			buf.writeDouble(y);
			buf.writeDouble(z);
			buf.writeFloat(pitch);
			buf.writeFloat(yaw);
			buf.writeInt(parameter);
		}

		@Override
		public ResourceLocation id() {
			return ID;
		}
	}
}
