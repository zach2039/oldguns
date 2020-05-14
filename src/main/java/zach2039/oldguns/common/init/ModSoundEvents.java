package zach2039.oldguns.common.init;

import java.util.AbstractMap;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import zach2039.oldguns.common.OldGuns;

@ObjectHolder(OldGuns.MODID)
public class ModSoundEvents
{
	@ObjectHolder("bullet.ricochet")
	public static final SoundEvent BULLET_RICOCHET = null;
	
	@ObjectHolder("bullet.hit.block")
	public static final SoundEvent BULLET_HIT_BLOCK = null;
	
	@ObjectHolder("bullet.hit.mob")
	public static final SoundEvent BULLET_HIT_MOB = null;
	
	@Mod.EventBusSubscriber(modid = OldGuns.MODID)
	public static class RegistrationHandler {
		private static Map<ResourceLocation, SoundEvent> SOUND_EVENTS;

		/**
		 * Get the {@link SoundEvent} with the specified ID.
		 *
		 * @param soundID The sound event's name
		 * @return The sound event
		 * @throws IllegalStateException If called before the sound events have been initialised
		 * @throws IllegalStateException If the specified sound event doesn't exist
		 */
		public static SoundEvent getSoundEvent(final ResourceLocation soundID) {
			Preconditions.checkState(SOUND_EVENTS != null, "Attempt to get Sound Events before initialisation");
			Preconditions.checkState(SOUND_EVENTS.containsKey(soundID), "Attempt to get non-existent Sound Event %s", soundID);

			return SOUND_EVENTS.get(soundID);
		}

		/**
		 * Initialize this mod's {@link SoundEvent}s.
		 * <p>
		 * This needs to be done before {@link RegistryEvent.Register<SoundEvent>} is fired so that the sound events
		 * can be used by record item constructors in {@link RegistryEvent.Register<Item>}.
		 */
		public static void initializeSoundEvents() {
			Preconditions.checkState(SOUND_EVENTS == null, "Attempt to re-initialise Sound Events");

			SOUND_EVENTS = new ImmutableMap.Builder<ResourceLocation, SoundEvent>()
					.put(createSoundEvent("bullet.ricochet"))
					.put(createSoundEvent("bullet.hit.block"))
					.put(createSoundEvent("bullet.hit.mob"))
					.build();
		}

		/**
		 * Register this mod's {@link SoundEvent}s.
		 *
		 * @param event The event
		 */
		@SubscribeEvent
		public static void registerSoundEvents(final RegistryEvent.Register<SoundEvent> event) {
			Preconditions.checkState(SOUND_EVENTS != null, "Sound Events weren't initialised before registration");
			SOUND_EVENTS.values().forEach(event.getRegistry()::register);
		}

		/**
		 * Create a {@link SoundEvent}.
		 *
		 * @param soundName The sound event's name without the testmod3 prefix
		 * @return A pair of the sound event's ID and the sound event itself
		 */
		private static Map.Entry<ResourceLocation, SoundEvent> createSoundEvent(final String soundName) {
			final ResourceLocation soundID = new ResourceLocation(OldGuns.MODID, soundName);
			final SoundEvent soundEvent = new SoundEvent(soundID).setRegistryName(soundID);
			return new AbstractMap.SimpleEntry<ResourceLocation, SoundEvent>(soundID, soundEvent);
		}
	}
}
