package com.zach2039.oldguns.init;

import java.util.function.Supplier;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.level.block.BlastingPowderStickBlock;
import com.zach2039.oldguns.world.level.block.CongreveRocketStandBlock;
import com.zach2039.oldguns.world.level.block.GunsmithsBenchBlock;
import com.zach2039.oldguns.world.level.block.HighGradeBlackPowderBlock;
import com.zach2039.oldguns.world.level.block.HighGradeBlackPowderCakeBlock;
import com.zach2039.oldguns.world.level.block.LiquidNiterCauldronBlock;
import com.zach2039.oldguns.world.level.block.MediumGradeBlackPowderBlock;
import com.zach2039.oldguns.world.level.block.MediumNavalCannonBlock;
import com.zach2039.oldguns.world.level.block.NiterBeddingBlock;
import com.zach2039.oldguns.world.level.block.WallBlastingPowderStickBlock;
import com.zach2039.oldguns.world.level.block.WetHighGradeBlackPowderBlock;
import com.zach2039.oldguns.world.level.block.WetHighGradeBlackPowderCakeBlock;
import com.zach2039.oldguns.world.level.block.WetMediumGradeBlackPowderBlock;
import com.zach2039.oldguns.world.level.storage.loot.predicates.LootSpawnDesignNotesLootCondition;
import com.zach2039.oldguns.world.level.storage.loot.predicates.LootSpawnFirearmsLootCondition;
import com.zach2039.oldguns.world.level.storage.loot.predicates.LootSpawnMechanismsLootCondition;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
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
public class ModLootConditionTypes {
	private static final DeferredRegister<LootItemConditionType> LOOT_ITEM_CONDITION_TYPES = DeferredRegister.create(Registries.LOOT_CONDITION_TYPE, OldGuns.MODID);
	
	public static final RegistryObject<LootItemConditionType>ALLOW_DESIGN_NOTES_LOOT = register("can_spawn_design_notes_in_loot", LootSpawnDesignNotesLootCondition.ConditionSerializer::new);
	public static final RegistryObject<LootItemConditionType> ALLOW_MECHANISMS_LOOT = register("can_spawn_mechansims_in_loot", LootSpawnMechanismsLootCondition.ConditionSerializer::new);
	public static final RegistryObject<LootItemConditionType> ALLOW_FIREARMS_LOOT = register("can_spawn_firearms_in_loot", LootSpawnFirearmsLootCondition.ConditionSerializer::new);

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
	
	private static RegistryObject<LootItemConditionType> register(final String name, final Supplier<Serializer<? extends LootItemCondition>> serializerFactory) {
		return LOOT_ITEM_CONDITION_TYPES.register(name, () -> new LootItemConditionType(serializerFactory.get()));
	}
}
