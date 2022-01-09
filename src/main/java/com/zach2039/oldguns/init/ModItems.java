package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.item.ammo.FirearmAmmoItem;
import com.zach2039.oldguns.world.item.ammo.LargeIronBirdshotItem;
import com.zach2039.oldguns.world.item.ammo.LargeIronBuckshotItem;
import com.zach2039.oldguns.world.item.ammo.LargeIronMusketBallItem;
import com.zach2039.oldguns.world.item.ammo.LargeLeadBirdshotItem;
import com.zach2039.oldguns.world.item.ammo.LargeLeadBuckshotItem;
import com.zach2039.oldguns.world.item.ammo.LargeLeadMusketBallItem;
import com.zach2039.oldguns.world.item.ammo.MediumIronBirdshotItem;
import com.zach2039.oldguns.world.item.ammo.MediumIronBuckshotItem;
import com.zach2039.oldguns.world.item.ammo.MediumIronMusketBallItem;
import com.zach2039.oldguns.world.item.ammo.MediumLeadBirdshotItem;
import com.zach2039.oldguns.world.item.ammo.MediumLeadBuckshotItem;
import com.zach2039.oldguns.world.item.ammo.MediumLeadMusketBallItem;
import com.zach2039.oldguns.world.item.ammo.SmallIronBirdshotItem;
import com.zach2039.oldguns.world.item.ammo.SmallIronBuckshotItem;
import com.zach2039.oldguns.world.item.ammo.SmallIronMusketBallItem;
import com.zach2039.oldguns.world.item.ammo.SmallLeadBirdshotItem;
import com.zach2039.oldguns.world.item.ammo.SmallLeadBuckshotItem;
import com.zach2039.oldguns.world.item.ammo.SmallLeadMusketBallItem;
import com.zach2039.oldguns.world.item.firearm.FirearmItem;
import com.zach2039.oldguns.world.item.firearm.FlintlockBlunderbussItem;
import com.zach2039.oldguns.world.item.firearm.FlintlockCaliverItem;
import com.zach2039.oldguns.world.item.firearm.FlintlockLongMusketItem;
import com.zach2039.oldguns.world.item.firearm.FlintlockPistolItem;
import com.zach2039.oldguns.world.item.material.IronBitsItem;
import com.zach2039.oldguns.world.item.material.LeadBitsItem;
import com.zach2039.oldguns.world.item.material.LeadIngotItem;
import com.zach2039.oldguns.world.item.material.LeadNuggetItem;
import com.zach2039.oldguns.world.item.part.FlintlockMechanismItem;
import com.zach2039.oldguns.world.item.part.HugeIronBarrelItem;
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
	public static final RegistryObject<FirearmItem> FLINTLOCK_CALIVER = ITEMS.register("flintlock_caliver",
			() -> new FlintlockCaliverItem()
		);
	public static final RegistryObject<FirearmItem> FLINTLOCK_LONG_MUSKET = ITEMS.register("flintlock_long_musket",
			() -> new FlintlockLongMusketItem()
		);
	public static final RegistryObject<FirearmItem> FLINTLOCK_BLUNDERBUSS = ITEMS.register("flintlock_blunderbuss",
			() -> new FlintlockBlunderbussItem()
		);
	
	// Ammo
	public static final RegistryObject<FirearmAmmoItem> SMALL_IRON_MUSKET_BALL = ITEMS.register("small_iron_musket_ball",
			() -> new SmallIronMusketBallItem()
		);
	public static final RegistryObject<FirearmAmmoItem> MEDIUM_IRON_MUSKET_BALL = ITEMS.register("medium_iron_musket_ball",
			() -> new MediumIronMusketBallItem()
		);
	public static final RegistryObject<FirearmAmmoItem> LARGE_IRON_MUSKET_BALL = ITEMS.register("large_iron_musket_ball",
			() -> new LargeIronMusketBallItem()
		);
	public static final RegistryObject<FirearmAmmoItem> SMALL_IRON_BUCKSHOT = ITEMS.register("small_iron_buckshot",
			() -> new SmallIronBuckshotItem()
		);
	public static final RegistryObject<FirearmAmmoItem> MEDIUM_IRON_BUCKSHOT = ITEMS.register("medium_iron_buckshot",
			() -> new MediumIronBuckshotItem()
		);
	public static final RegistryObject<FirearmAmmoItem> LARGE_IRON_BUCKSHOT = ITEMS.register("large_iron_buckshot",
			() -> new LargeIronBuckshotItem()
		);
	public static final RegistryObject<FirearmAmmoItem> SMALL_IRON_BIRDSHOT = ITEMS.register("small_iron_birdshot",
			() -> new SmallIronBirdshotItem()
		);
	public static final RegistryObject<FirearmAmmoItem> MEDIUM_IRON_BIRDSHOT = ITEMS.register("medium_iron_birdshot",
			() -> new MediumIronBirdshotItem()
		);
	public static final RegistryObject<FirearmAmmoItem> LARGE_IRON_BIRDSHOT = ITEMS.register("large_iron_birdshot",
			() -> new LargeIronBirdshotItem()
		);
	
	public static final RegistryObject<FirearmAmmoItem> SMALL_LEAD_MUSKET_BALL = ITEMS.register("small_lead_musket_ball",
			() -> new SmallLeadMusketBallItem()
		);
	public static final RegistryObject<FirearmAmmoItem> MEDIUM_LEAD_MUSKET_BALL = ITEMS.register("medium_lead_musket_ball",
			() -> new MediumLeadMusketBallItem()
		);
	public static final RegistryObject<FirearmAmmoItem> LARGE_LEAD_MUSKET_BALL = ITEMS.register("large_lead_musket_ball",
			() -> new LargeLeadMusketBallItem()
		);
	public static final RegistryObject<FirearmAmmoItem> SMALL_LEAD_BUCKSHOT = ITEMS.register("small_lead_buckshot",
			() -> new SmallLeadBuckshotItem()
		);
	public static final RegistryObject<FirearmAmmoItem> MEDIUM_LEAD_BUCKSHOT = ITEMS.register("medium_lead_buckshot",
			() -> new MediumLeadBuckshotItem()
		);
	public static final RegistryObject<FirearmAmmoItem> LARGE_LEAD_BUCKSHOT = ITEMS.register("large_lead_buckshot",
			() -> new LargeLeadBuckshotItem()
		);
	public static final RegistryObject<FirearmAmmoItem> SMALL_LEAD_BIRDSHOT = ITEMS.register("small_lead_birdshot",
			() -> new SmallLeadBirdshotItem()
		);
	public static final RegistryObject<FirearmAmmoItem> MEDIUM_LEAD_BIRDSHOT = ITEMS.register("medium_lead_birdshot",
			() -> new MediumLeadBirdshotItem()
		);
	public static final RegistryObject<FirearmAmmoItem> LARGE_LEAD_BIRDSHOT = ITEMS.register("large_lead_birdshot",
			() -> new LargeLeadBirdshotItem()
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
	
	public static final RegistryObject<HugeIronBarrelItem> HUGE_IRON_BARREL = ITEMS.register("huge_iron_barrel",
			() -> new HugeIronBarrelItem()
		);
	
	// Materials
	public static final RegistryObject<IronBitsItem> IRON_BITS = ITEMS.register("iron_bits",
			() -> new IronBitsItem()
		);
	
	public static final RegistryObject<LeadBitsItem> LEAD_BITS = ITEMS.register("lead_bits",
			() -> new LeadBitsItem()
		);
	
	public static final RegistryObject<LeadIngotItem> LEAD_INGOT = ITEMS.register("lead_ingot",
			() -> new LeadIngotItem()
		);
	
	public static final RegistryObject<LeadNuggetItem> LEAD_NUGGET = ITEMS.register("lead_nugget",
			() -> new LeadNuggetItem()
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