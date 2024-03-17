package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import javax.annotation.Nullable;
import java.util.function.Supplier;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author zach2039
 */
public class ModPotions {
	private static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(Registries.POTION, OldGuns.MODID);

	private static boolean isInitialized;

	/**
	 * Registers the {@link DeferredRegister} instance with the mod event bus.
	 * <p>
	 * This should be called during mod construction.
	 *
	 * @param modEventBus The mod event bus
	 */
	public static void initialize(final IEventBus modEventBus) {
		if (isInitialized) {
			throw new IllegalStateException("Already initialized");
		}

		POTIONS.register(modEventBus);

		isInitialized = true;
	}

	/**
	 * Registers a {@link Potion} from the specified {@link MobEffectInstance}.
	 * <p>
	 * Uses the specified name as the {@link Potion}'s registry name and base name.
	 *
	 * @param name                  The base name of the potion
	 * @param effectInstanceFactory The factory used to create the potion's effect instance
	 * @return A RegistryObject reference to the potion
	 */
	private static DeferredHolder<Potion, ? extends Potion> registerPotion(final String name, final Supplier<MobEffectInstance> effectInstanceFactory) {
		return registerPotion(name, effectInstanceFactory, null);
	}

	/**
	 * Registers a {@link Potion} from the specified {@link MobEffectInstance}
	 * <p>
	 * Uses the {@link MobEffect}'s registry name as the {@link Potion}'s registry name (with an optional prefix) and base name (with no prefix).
	 *
	 * @param name                  The base name of the potion
	 * @param effectInstanceFactory The factory used to create the potion's effect instance
	 * @param namePrefix            The name prefix, if any
	 * @return The PotionType
	 */
	private static DeferredHolder<Potion, ? extends Potion> registerPotion(final String name, final Supplier<MobEffectInstance> effectInstanceFactory, @Nullable final String namePrefix) {
		final String fullName = namePrefix != null ? namePrefix + name : name;

		return POTIONS.register(fullName, () -> {
			// Based on net.minecraft.util.Util.makeTranslationKey. This ensures that the base name is valid in ResourceLocation paths.
			final String potionBaseName = OldGuns.MODID + "." + name.replace('/', '.');

			if (effectInstanceFactory == null)
				return new Potion(potionBaseName);
			
			return new Potion(potionBaseName, effectInstanceFactory.get());
		});
	}
}
