package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author zach2039
 */
public class ModSoundEvents {
	private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, OldGuns.MODID);

	private static boolean isInitialized;

	public static final RegistryObject<SoundEvent> BULLET_RICOCHET = registerSoundEvent("bullet.ricochet");
	
	public static final RegistryObject<SoundEvent> BULLET_HIT_BLOCK = registerSoundEvent("bullet.hit.block");
	
	public static final RegistryObject<SoundEvent> BULLET_HIT_MOB = registerSoundEvent("bullet.hit.mob");
	
	public static final RegistryObject<SoundEvent> BULLET_SHOOT= registerSoundEvent("bullet.shoot");

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
	private static RegistryObject<SoundEvent> registerSoundEvent(final String soundName) {
		return SOUND_EVENTS.register(soundName, () -> new SoundEvent(new ResourceLocation(OldGuns.MODID, soundName)));
	}
}
