package com.zach2039.oldguns.init;

import com.mojang.serialization.Codec;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.level.storage.loot.predicates.LootSpawnDesignNotesLootCondition;
import com.zach2039.oldguns.world.level.storage.loot.predicates.LootSpawnFirearmsLootCondition;
import com.zach2039.oldguns.world.level.storage.loot.predicates.LootSpawnMechanismsLootCondition;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
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
public class ModLootConditionTypes {
	private static final DeferredRegister<LootItemConditionType> LOOT_ITEM_CONDITION_TYPES = DeferredRegister.create(Registries.LOOT_CONDITION_TYPE, OldGuns.MODID);
	
	public static final DeferredHolder<LootItemConditionType, ? extends LootItemConditionType> ALLOW_DESIGN_NOTES_LOOT = register("can_spawn_design_notes_in_loot", LootSpawnDesignNotesLootCondition.CODEC);
	public static final DeferredHolder<LootItemConditionType, ? extends LootItemConditionType> ALLOW_MECHANISMS_LOOT = register("can_spawn_mechansims_in_loot", LootSpawnMechanismsLootCondition.CODEC);
	public static final DeferredHolder<LootItemConditionType, ? extends LootItemConditionType> ALLOW_FIREARMS_LOOT = register("can_spawn_firearms_in_loot", LootSpawnFirearmsLootCondition.CODEC);

	private static boolean isInitialized = false;

	/**
	 * Registers the {@link DeferredRegister} instances with the mod event bus.
	 * <p>
	 * This should be called during mod construction.
	 *
	 * @param modEventBus The mod event bus
	 */
	public static void initialize(final IEventBus modEventBus) {
		if (isInitialized) {
			throw new IllegalStateException("Already initialized");
		}

		LOOT_ITEM_CONDITION_TYPES.register(modEventBus);

		isInitialized = true;
	}
	
	private static DeferredHolder<LootItemConditionType, ? extends LootItemConditionType> register(final String name, final Codec<? extends LootItemCondition> codec) {
		return LOOT_ITEM_CONDITION_TYPES.register(name, () -> new LootItemConditionType(codec));
	}
}
