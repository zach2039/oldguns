package com.zach2039.oldguns.init;

import com.mojang.serialization.Codec;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.level.storage.loot.modifiers.LootTableLootModifier;

import net.minecraftforge.common.loot.IGlobalLootModifier;
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
public class ModLootModifierSerializers {
	private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, OldGuns.MODID);

	private static boolean isInitialized;

	public static final RegistryObject<Codec<LootTableLootModifier>> LOOT_TABLE = SERIALIZERS.register(
			"loot_table",
			LootTableLootModifier.CODEC
	);

	/**
	 * Registers the {@link DeferredRegister} instance with the mod event bus.
	 * <p>
	 * This should be called during mod construction.
	 *
	 * @param modEventBus The mod event bus
	 */
	public static void initialize(final IEventBus modEventBus) {
		if (isInitialized) {
			throw new IllegalStateException("Already initialised");
		}

		SERIALIZERS.register(modEventBus);

		isInitialized = true;
	}
}
