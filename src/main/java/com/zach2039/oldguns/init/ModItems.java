package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.item.ammo.FirearmAmmoItem;
import com.zach2039.oldguns.world.item.ammo.SmallIronMusketBallItem;
import com.zach2039.oldguns.world.item.firearm.FirearmItem;
import com.zach2039.oldguns.world.item.firearm.FlintlockPistolItem;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item.Properties;
import net.minecraftforge.client.event.ColorHandlerEvent.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
	private static final DeferredRegister<net.minecraft.world.item.Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OldGuns.MODID);
	
	private static boolean isInitialized;
	
	public static final RegistryObject<FirearmItem> FLINTLOCK_PISTOL = ITEMS.register("flintlock_pistol",
			() -> new FlintlockPistolItem()
		);
	
	public static final RegistryObject<FirearmAmmoItem> SMALL_IRON_MUSKET_BALL = ITEMS.register("small_iron_musket_ball",
			() -> new SmallIronMusketBallItem()
		);
	
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

		ITEMS.register(modEventBus);

		isInitialized = true;
	}

	/**
	 * Gets an {@link Item.Properties} instance with the {@link CreativeModeTab} set to {@link TestMod3#CREATIVE_MODE_TAB}.
	 *
	 * @author choonster
	 * @return The item properties
	 */
	private static Properties defaultItemProperties() {
		return new Properties().tab(OldGuns.CREATIVE_MODE_TAB);
	}
}
