package com.zach2039.oldguns.init;

import com.mojang.serialization.Codec;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.item.crafting.conditions.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModConditions {

	private static final DeferredRegister<Codec<? extends ICondition>> CONDITIONS = DeferredRegister.create(NeoForgeRegistries.CONDITION_SERIALIZERS, OldGuns.MODID);

	private static boolean isInitialized = false;

	public static final DeferredHolder<Codec<? extends ICondition>, ? extends Codec<? extends ICondition>> CAN_CRAFT_MATCHLOCK_WEAPONS = CONDITIONS.register(
			CanCraftMatchlockWeaponsCondition.INSTANCE.toString(),
			() -> CanCraftMatchlockWeaponsCondition.CODEC
	);

	public static final DeferredHolder<Codec<? extends ICondition>, ? extends Codec<? extends ICondition>> CAN_CRAFT_WHEELLOCK_WEAPONS = CONDITIONS.register(
			CanCraftWheellockWeaponsCondition.INSTANCE.toString(),
			() -> CanCraftWheellockWeaponsCondition.CODEC
	);

	public static final DeferredHolder<Codec<? extends ICondition>, ? extends Codec<? extends ICondition>> CAN_CRAFT_FLINTLOCK_WEAPONS = CONDITIONS.register(
			CanCraftFlintlockWeaponsCondition.INSTANCE.toString(),
			() -> CanCraftFlintlockWeaponsCondition.CODEC
	);

	public static final DeferredHolder<Codec<? extends ICondition>, ? extends Codec<? extends ICondition>> CAN_CRAFT_CAPLOCK_WEAPONS = CONDITIONS.register(
			CanCraftCaplockWeaponsCondition.INSTANCE.toString(),
			() -> CanCraftCaplockWeaponsCondition.CODEC
	);

	public static final DeferredHolder<Codec<? extends ICondition>, ? extends Codec<? extends ICondition>> CAN_CRAFT_PAPER_CARTRIDGES = CONDITIONS.register(
			CanCraftPaperCartridgesCondition.INSTANCE.toString(),
			() -> CanCraftPaperCartridgesCondition.CODEC
	);

	public static final DeferredHolder<Codec<? extends ICondition>, ? extends Codec<? extends ICondition>> CAN_CRAFT_IRON_FIREARM_AMMO = CONDITIONS.register(
			CanCraftIronFirearmAmmoCondition.INSTANCE.toString(),
			() -> CanCraftIronFirearmAmmoCondition.CODEC
	);

	public static final DeferredHolder<Codec<? extends ICondition>, ? extends Codec<? extends ICondition>> CAN_CRAFT_LEAD_FIREARM_AMMO = CONDITIONS.register(
			CanCraftLeadFirearmAmmoCondition.INSTANCE.toString(),
			() -> CanCraftLeadFirearmAmmoCondition.CODEC
	);

	public static final DeferredHolder<Codec<? extends ICondition>, ? extends Codec<? extends ICondition>> CAN_CRAFT_STONE_FIREARM_AMMO = CONDITIONS.register(
			CanCraftStoneFirearmAmmoCondition.INSTANCE.toString(),
			() -> CanCraftStoneFirearmAmmoCondition.CODEC
	);

	public static final DeferredHolder<Codec<? extends ICondition>, ? extends Codec<? extends ICondition>> CAN_CRAFT_NAVAL_CANNON_ARTILLERY = CONDITIONS.register(
			CanCraftNavalCannonArtilleryCondition.INSTANCE.toString(),
			() -> CanCraftNavalCannonArtilleryCondition.CODEC
	);

	public static final DeferredHolder<Codec<? extends ICondition>, ? extends Codec<? extends ICondition>> CAN_CRAFT_CONGREVE_ROCKET_STAND_ARTILLERY = CONDITIONS.register(
			CanCraftCongreveRocketStandArtilleryCondition.INSTANCE.toString(),
			() -> CanCraftCongreveRocketStandArtilleryCondition.CODEC
	);

	public static final DeferredHolder<Codec<? extends ICondition>, ? extends Codec<? extends ICondition>> CAN_CRAFT_ARTILLERY_POWDER_CHARGES = CONDITIONS.register(
			CanCraftArtilleryPowderChargesCondition.INSTANCE.toString(),
			() -> CanCraftArtilleryPowderChargesCondition.CODEC
	);

	public static final DeferredHolder<Codec<? extends ICondition>, ? extends Codec<? extends ICondition>> CAN_CRAFT_IRON_ARTILLERY_AMMO = CONDITIONS.register(
			CanCraftIronArtilleryAmmoCondition.INSTANCE.toString(),
			() -> CanCraftIronArtilleryAmmoCondition.CODEC
	);

	public static final DeferredHolder<Codec<? extends ICondition>, ? extends Codec<? extends ICondition>> CAN_CRAFT_LEAD_ARTILLERY_AMMO = CONDITIONS.register(
			CanCraftLeadArtilleryAmmoCondition.INSTANCE.toString(),
			() -> CanCraftLeadArtilleryAmmoCondition.CODEC
	);

	public static final DeferredHolder<Codec<? extends ICondition>, ? extends Codec<? extends ICondition>> CAN_CRAFT_STONE_ARTILLERY_AMMO = CONDITIONS.register(
			CanCraftStoneArtilleryAmmoCondition.INSTANCE.toString(),
			() -> CanCraftStoneArtilleryAmmoCondition.CODEC
	);

	public static final DeferredHolder<Codec<? extends ICondition>, ? extends Codec<? extends ICondition>> CAN_CRAFT_BLASTING_POWDER_STICKS = CONDITIONS.register(
			CanCraftBlastingPowderSticksCondition.INSTANCE.toString(),
			() -> CanCraftBlastingPowderSticksCondition.CODEC
	);

	public static final DeferredHolder<Codec<? extends ICondition>, ? extends Codec<? extends ICondition>> CAN_CRAFT_TNT_FROM_BLASTING_POWDER_STICKS = CONDITIONS.register(
			CanCraftTntFromBlastingPowderSticksCondition.INSTANCE.toString(),
			() -> CanCraftTntFromBlastingPowderSticksCondition.CODEC
	);

	public static final DeferredHolder<Codec<? extends ICondition>, ? extends Codec<? extends ICondition>> CAN_CRAFT_MATCH_CORD_FROM_BARK_STRANDS = CONDITIONS.register(
			CanCraftMatchCordFromBarkStrandsCondition.INSTANCE.toString(),
			() -> CanCraftMatchCordFromBarkStrandsCondition.CODEC
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
			throw new IllegalStateException("Already initialized");
		}

		CONDITIONS.register(modEventBus);

		isInitialized = true;
	}
}
