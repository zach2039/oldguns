package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.item.ammo.FirearmAmmoItem;
import com.zach2039.oldguns.world.item.ammo.SmallIronMusketBallItem;
import com.zach2039.oldguns.world.item.firearm.FirearmItem;
import com.zach2039.oldguns.world.item.firearm.FlintlockPistolItem;
import com.zach2039.oldguns.world.item.material.IronBitsItem;
import com.zach2039.oldguns.world.item.material.LeadBitsItem;
import com.zach2039.oldguns.world.item.part.FlintlockMechanismItem;
import com.zach2039.oldguns.world.item.part.LargeIronBarrelItem;
import com.zach2039.oldguns.world.item.part.LargeWoodenHandleItem;
import com.zach2039.oldguns.world.item.part.LargeWoodenStockItem;
import com.zach2039.oldguns.world.item.part.MediumIronBarrelItem;
import com.zach2039.oldguns.world.item.part.MediumWoodenHandleItem;
import com.zach2039.oldguns.world.item.part.MediumWoodenStockItem;
import com.zach2039.oldguns.world.item.part.SmallIronBarrelItem;
import com.zach2039.oldguns.world.item.part.SmallWoodenHandleItem;
import com.zach2039.oldguns.world.item.part.SmallWoodenStockItem;
import com.zach2039.oldguns.world.item.tools.RepairKitItem;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item.Properties;
import net.minecraftforge.client.event.ColorHandlerEvent.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Taken from TestMod3 on Github
 * @author grilled-salmon
 * @author Choonster
 */
public class ModItems {
	private static final DeferredRegister<net.minecraft.world.item.Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OldGuns.MODID);
	
	private static boolean isInitialized;
	
	// Firearms
	public static final RegistryObject<FirearmItem> FLINTLOCK_PISTOL = ITEMS.register("flintlock_pistol",
			() -> new FlintlockPistolItem()
		);
	
	// Ammo
	public static final RegistryObject<FirearmAmmoItem> SMALL_IRON_MUSKET_BALL = ITEMS.register("small_iron_musket_ball",
			() -> new SmallIronMusketBallItem()
		);
	
	// Parts
	public static final RegistryObject<FlintlockMechanismItem> FLINTLOCK_MECHANISM = ITEMS.register("flintlock_mechanism",
			() -> new FlintlockMechanismItem()
		);
	
	public static final RegistryObject<SmallWoodenHandleItem> SMALL_WOODEN_HANDLE = ITEMS.register("small_wooden_handle",
			() -> new SmallWoodenHandleItem()
		);
	
	public static final RegistryObject<MediumWoodenHandleItem> MEDIUM_WOODEN_HANDLE = ITEMS.register("medium_wooden_handle",
			() -> new MediumWoodenHandleItem()
		);
	
	public static final RegistryObject<LargeWoodenHandleItem> LARGE_WOODEN_HANDLE = ITEMS.register("large_wooden_handle",
			() -> new LargeWoodenHandleItem()
		);
	
	public static final RegistryObject<SmallWoodenStockItem> SMALL_WOODEN_STOCK = ITEMS.register("small_wooden_stock",
			() -> new SmallWoodenStockItem()
		);
	
	public static final RegistryObject<MediumWoodenStockItem> MEDIUM_WOODEN_STOCK = ITEMS.register("medium_wooden_stock",
			() -> new MediumWoodenStockItem()
		);
	
	public static final RegistryObject<LargeWoodenStockItem> LARGE_WOODEN_STOCK = ITEMS.register("large_wooden_stock",
			() -> new LargeWoodenStockItem()
		);
	
	public static final RegistryObject<SmallIronBarrelItem> SMALL_IRON_BARREL = ITEMS.register("small_iron_barrel",
			() -> new SmallIronBarrelItem()
		);
	
	public static final RegistryObject<MediumIronBarrelItem> MEDIUM_IRON_BARREL = ITEMS.register("medium_iron_barrel",
			() -> new MediumIronBarrelItem()
		);
	
	public static final RegistryObject<LargeIronBarrelItem> LARGE_IRON_BARREL = ITEMS.register("large_iron_barrel",
			() -> new LargeIronBarrelItem()
		);
	
	// Materials
	public static final RegistryObject<IronBitsItem> IRON_BITS = ITEMS.register("iron_bits",
			() -> new IronBitsItem()
		);
	
	public static final RegistryObject<LeadBitsItem> LEAD_BITS = ITEMS.register("lead_bits",
			() -> new LeadBitsItem()
		);
	
	// Tools
	public static final RegistryObject<RepairKitItem> REPAIR_KIT = ITEMS.register("repair_kit",
			() -> new RepairKitItem()
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
