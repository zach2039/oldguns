package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author zach2039
 */
public class ModSoundEvents {
	private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(Registries.SOUND_EVENT, OldGuns.MODID);

	private static boolean isInitialized;

	public static final DeferredHolder<SoundEvent, ? extends SoundEvent> BULLET_RICOCHET = registerSoundEvent("bullet.ricochet");
	
	public static final DeferredHolder<SoundEvent, ? extends SoundEvent> BULLET_HIT_BLOCK = registerSoundEvent("bullet.hit.block");
	
	public static final DeferredHolder<SoundEvent, ? extends SoundEvent> BULLET_HIT_MOB = registerSoundEvent("bullet.hit.mob");
	
	public static final DeferredHolder<SoundEvent, ? extends SoundEvent> BULLET_SHOOT= registerSoundEvent("bullet.shoot");

	/**
	 * Registers the {@link DeferredRegister} instance with the mod event bus.
	 * <p>
	 * This should be called during mod construction.
	 *
	 * @author choonster
	 * @param modEventBus The mod event bus
	 */
	public static void initialize(final IEventBus modEventBus) {
		if (isInitialized) {
			throw new IllegalStateException("Already initialized");
		}

		SOUND_EVENTS.register(modEventBus);

		isInitialized = true;
	}

	/**
	 * Registers a sound event.
	 *
	 * @author choonster
	 * @param soundName The sound event's name, without the testmod3 prefix
	 * @return A RegistryObject reference to the SoundEvent
	 */
	private static DeferredHolder<SoundEvent, ? extends SoundEvent> registerSoundEvent(final String soundName) {
		return SOUND_EVENTS.register(soundName, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(OldGuns.MODID, soundName)));
	}
}
