package com.zach2039.oldguns.init;

import com.mojang.serialization.Codec;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.level.storage.loot.modifiers.DesignNotesFlintlockLootModifier;
import com.zach2039.oldguns.world.level.storage.loot.modifiers.DesignNotesMatchlockLootModifier;
import com.zach2039.oldguns.world.level.storage.loot.modifiers.DesignNotesMechanismLootModifier;
import com.zach2039.oldguns.world.level.storage.loot.modifiers.DesignNotesWheellockLootModifier;
import com.zach2039.oldguns.world.level.storage.loot.modifiers.LootTableLootModifier;
import com.zach2039.oldguns.world.level.storage.loot.modifiers.MatchlockLootModifier;
import com.zach2039.oldguns.world.level.storage.loot.modifiers.MechanismLootModifier;

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
public class ModLootModifierCodecs {
	private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, OldGuns.MODID);

	private static boolean isInitialized;

	public static final RegistryObject<Codec<LootTableLootModifier>> MODIFIER_MATCHLOCK = SERIALIZERS.register(
			"modifier_matchlock",
			MatchlockLootModifier.CODEC
	);
	public static final RegistryObject<Codec<LootTableLootModifier>> MODIFIER_MECHANSIM = SERIALIZERS.register(
			"modifier_mechanism",
			MechanismLootModifier.CODEC	
	);
	public static final RegistryObject<Codec<LootTableLootModifier>> MODIFIER_DESIGN_NOTES_MECHANSIM = SERIALIZERS.register(
			"modifier_design_notes_mechansim",
			DesignNotesMechanismLootModifier.CODEC
	);
	public static final RegistryObject<Codec<LootTableLootModifier>> MODIFIER_DESIGN_NOTES_MATCHLOCK = SERIALIZERS.register(
			"modifier_design_notes_matchlock",
			DesignNotesMatchlockLootModifier.CODEC
	);
	public static final RegistryObject<Codec<LootTableLootModifier>> MODIFIER_DESIGN_NOTES_WHEELLOCK = SERIALIZERS.register(
			"modifier_design_notes_wheellock",
			DesignNotesWheellockLootModifier.CODEC
	);
	public static final RegistryObject<Codec<LootTableLootModifier>> MODIFIER_DESIGN_NOTES_FLINTLOCK = SERIALIZERS.register(
			"modifier_design_notes_flintlock",
			DesignNotesFlintlockLootModifier.CODEC
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
