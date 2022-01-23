package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.item.ammo.artillery.ArtilleryAmmoItem;
import com.zach2039.oldguns.world.item.ammo.artillery.MediumIronCannonball;
import com.zach2039.oldguns.world.item.ammo.artillery.MediumIronGrapeshot;
import com.zach2039.oldguns.world.item.ammo.firearm.FirearmAmmoItem;
import com.zach2039.oldguns.world.item.ammo.firearm.LargeIronBirdshotItem;
import com.zach2039.oldguns.world.item.ammo.firearm.LargeIronBuckshotItem;
import com.zach2039.oldguns.world.item.ammo.firearm.LargeIronMusketBallItem;
import com.zach2039.oldguns.world.item.ammo.firearm.LargeLeadBirdshotItem;
import com.zach2039.oldguns.world.item.ammo.firearm.LargeLeadBuckshotItem;
import com.zach2039.oldguns.world.item.ammo.firearm.LargeLeadMusketBallItem;
import com.zach2039.oldguns.world.item.ammo.firearm.LargeStoneBirdshotItem;
import com.zach2039.oldguns.world.item.ammo.firearm.LargeStoneMusketBallItem;
import com.zach2039.oldguns.world.item.ammo.firearm.MediumIronBirdshotItem;
import com.zach2039.oldguns.world.item.ammo.firearm.MediumIronBuckshotItem;
import com.zach2039.oldguns.world.item.ammo.firearm.MediumIronMusketBallItem;
import com.zach2039.oldguns.world.item.ammo.firearm.MediumLeadBirdshotItem;
import com.zach2039.oldguns.world.item.ammo.firearm.MediumLeadBuckshotItem;
import com.zach2039.oldguns.world.item.ammo.firearm.MediumLeadMusketBallItem;
import com.zach2039.oldguns.world.item.ammo.firearm.MediumStoneBirdshotItem;
import com.zach2039.oldguns.world.item.ammo.firearm.MediumStoneMusketBallItem;
import com.zach2039.oldguns.world.item.ammo.firearm.SmallIronBirdshotItem;
import com.zach2039.oldguns.world.item.ammo.firearm.SmallIronBuckshotItem;
import com.zach2039.oldguns.world.item.ammo.firearm.SmallIronMusketBallItem;
import com.zach2039.oldguns.world.item.ammo.firearm.SmallLeadBirdshotItem;
import com.zach2039.oldguns.world.item.ammo.firearm.SmallLeadBuckshotItem;
import com.zach2039.oldguns.world.item.ammo.firearm.SmallLeadMusketBallItem;
import com.zach2039.oldguns.world.item.ammo.firearm.SmallStoneBirdshotItem;
import com.zach2039.oldguns.world.item.ammo.firearm.SmallStoneMusketBallItem;
import com.zach2039.oldguns.world.item.artillery.LargePowderCharge;
import com.zach2039.oldguns.world.item.artillery.MediumPowderCharge;
import com.zach2039.oldguns.world.item.artillery.SmallPowderCharge;
import com.zach2039.oldguns.world.item.firearm.FirearmItem;
import com.zach2039.oldguns.world.item.firearm.FlintlockArquebusItem;
import com.zach2039.oldguns.world.item.firearm.FlintlockBlunderbussItem;
import com.zach2039.oldguns.world.item.firearm.FlintlockBlunderbussPistolItem;
import com.zach2039.oldguns.world.item.firearm.FlintlockCaliverItem;
import com.zach2039.oldguns.world.item.firearm.FlintlockDerringerItem;
import com.zach2039.oldguns.world.item.firearm.FlintlockDoublebarrelBlunderbussItem;
import com.zach2039.oldguns.world.item.firearm.FlintlockDuckfootDerringerItem;
import com.zach2039.oldguns.world.item.firearm.FlintlockLongMusketItem;
import com.zach2039.oldguns.world.item.firearm.FlintlockMusketItem;
import com.zach2039.oldguns.world.item.firearm.FlintlockMusketoonItem;
import com.zach2039.oldguns.world.item.firearm.FlintlockNockGunItem;
import com.zach2039.oldguns.world.item.firearm.FlintlockPepperboxPistolItem;
import com.zach2039.oldguns.world.item.firearm.FlintlockPistolItem;
import com.zach2039.oldguns.world.item.firearm.MatchlockArquebusItem;
import com.zach2039.oldguns.world.item.firearm.MatchlockBlunderbussItem;
import com.zach2039.oldguns.world.item.firearm.MatchlockBlunderbussPistolItem;
import com.zach2039.oldguns.world.item.firearm.MatchlockCaliverItem;
import com.zach2039.oldguns.world.item.firearm.MatchlockDerringerItem;
import com.zach2039.oldguns.world.item.firearm.MatchlockLongMusketItem;
import com.zach2039.oldguns.world.item.firearm.MatchlockMusketItem;
import com.zach2039.oldguns.world.item.firearm.MatchlockMusketoonItem;
import com.zach2039.oldguns.world.item.firearm.MatchlockPistolItem;
import com.zach2039.oldguns.world.item.firearm.WheellockArquebusItem;
import com.zach2039.oldguns.world.item.firearm.WheellockBlunderbussItem;
import com.zach2039.oldguns.world.item.firearm.WheellockBlunderbussPistolItem;
import com.zach2039.oldguns.world.item.firearm.WheellockCaliverItem;
import com.zach2039.oldguns.world.item.firearm.WheellockDerringerItem;
import com.zach2039.oldguns.world.item.firearm.WheellockDoublebarrelPistolItem;
import com.zach2039.oldguns.world.item.firearm.WheellockHandMortarItem;
import com.zach2039.oldguns.world.item.firearm.WheellockLongMusketItem;
import com.zach2039.oldguns.world.item.firearm.WheellockMusketItem;
import com.zach2039.oldguns.world.item.firearm.WheellockMusketoonItem;
import com.zach2039.oldguns.world.item.firearm.WheellockPistolItem;
import com.zach2039.oldguns.world.item.material.BrassIngotItem;
import com.zach2039.oldguns.world.item.material.BrassNuggetItem;
import com.zach2039.oldguns.world.item.material.HighGradeBlackPowderItem;
import com.zach2039.oldguns.world.item.material.IronBitsItem;
import com.zach2039.oldguns.world.item.material.LeadBitsItem;
import com.zach2039.oldguns.world.item.material.LeadIngotItem;
import com.zach2039.oldguns.world.item.material.LeadNuggetItem;
import com.zach2039.oldguns.world.item.material.LiquidNiterItem;
import com.zach2039.oldguns.world.item.material.MediumGradeBlackPowderItem;
import com.zach2039.oldguns.world.item.material.NiterItem;
import com.zach2039.oldguns.world.item.material.NitrateSoilItem;
import com.zach2039.oldguns.world.item.material.SulfurItem;
import com.zach2039.oldguns.world.item.part.ArtilleryPartItem;
import com.zach2039.oldguns.world.item.part.CaplockMechanismItem;
import com.zach2039.oldguns.world.item.part.FirearmPartItem;
import com.zach2039.oldguns.world.item.part.FlintlockMechanismItem;
import com.zach2039.oldguns.world.item.part.GoldGearSetItem;
import com.zach2039.oldguns.world.item.part.GoldTriggerAssemblyItem;
import com.zach2039.oldguns.world.item.part.IronGearSetItem;
import com.zach2039.oldguns.world.item.part.IronTriggerAssemblyItem;
import com.zach2039.oldguns.world.item.part.LargeBrassBarrelItem;
import com.zach2039.oldguns.world.item.part.LargeBrassFlaredBarrelItem;
import com.zach2039.oldguns.world.item.part.LargeIronBarrelItem;
import com.zach2039.oldguns.world.item.part.LargeIronCannonBarrelItem;
import com.zach2039.oldguns.world.item.part.LargeIronFlaredBarrelItem;
import com.zach2039.oldguns.world.item.part.LargeStoneBarrelItem;
import com.zach2039.oldguns.world.item.part.LargeStoneFlaredBarrelItem;
import com.zach2039.oldguns.world.item.part.LargeWoodenCarriageWheelItem;
import com.zach2039.oldguns.world.item.part.LargeWoodenHandleItem;
import com.zach2039.oldguns.world.item.part.LargeWoodenNavalCarriageItem;
import com.zach2039.oldguns.world.item.part.LargeWoodenStockItem;
import com.zach2039.oldguns.world.item.part.MatchCordItem;
import com.zach2039.oldguns.world.item.part.MatchlockMechanismItem;
import com.zach2039.oldguns.world.item.part.MediumBrassBarrelItem;
import com.zach2039.oldguns.world.item.part.MediumBrassFlaredBarrelItem;
import com.zach2039.oldguns.world.item.part.MediumIronBarrelItem;
import com.zach2039.oldguns.world.item.part.MediumIronCannonBarrelItem;
import com.zach2039.oldguns.world.item.part.MediumIronFlaredBarrelItem;
import com.zach2039.oldguns.world.item.part.MediumStoneBarrelItem;
import com.zach2039.oldguns.world.item.part.MediumStoneFlaredBarrelItem;
import com.zach2039.oldguns.world.item.part.MediumWoodenCarriageWheelItem;
import com.zach2039.oldguns.world.item.part.MediumWoodenHandleItem;
import com.zach2039.oldguns.world.item.part.MediumWoodenNavalCarriageItem;
import com.zach2039.oldguns.world.item.part.MediumWoodenStockItem;
import com.zach2039.oldguns.world.item.part.SmallBrassBarrelItem;
import com.zach2039.oldguns.world.item.part.SmallBrassFlaredBarrelItem;
import com.zach2039.oldguns.world.item.part.SmallIronBarrelItem;
import com.zach2039.oldguns.world.item.part.SmallIronCannonBarrelItem;
import com.zach2039.oldguns.world.item.part.SmallIronFlaredBarrelItem;
import com.zach2039.oldguns.world.item.part.SmallStoneBarrelItem;
import com.zach2039.oldguns.world.item.part.SmallStoneFlaredBarrelItem;
import com.zach2039.oldguns.world.item.part.SmallWoodenCarriageWheelItem;
import com.zach2039.oldguns.world.item.part.SmallWoodenHandleItem;
import com.zach2039.oldguns.world.item.part.SmallWoodenNavalCarriageItem;
import com.zach2039.oldguns.world.item.part.SmallWoodenStockItem;
import com.zach2039.oldguns.world.item.part.TinyBrassBarrelItem;
import com.zach2039.oldguns.world.item.part.TinyIronBarrelItem;
import com.zach2039.oldguns.world.item.part.TinyStoneBarrelItem;
import com.zach2039.oldguns.world.item.part.TinyWoodenCarriageWheelItem;
import com.zach2039.oldguns.world.item.part.WheellockMechanismItem;
import com.zach2039.oldguns.world.item.part.WoodGearSetItem;
import com.zach2039.oldguns.world.item.part.WoodTriggerAssemblyItem;
import com.zach2039.oldguns.world.item.tools.DesignNotesItem;
import com.zach2039.oldguns.world.item.tools.GunnersQuadrantItem;
import com.zach2039.oldguns.world.item.tools.LongMatchItem;
import com.zach2039.oldguns.world.item.tools.MortarAndPestleItem;
import com.zach2039.oldguns.world.item.tools.RamRodItem;
import com.zach2039.oldguns.world.item.tools.RepairKitItem;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item.Properties;
import net.minecraftforge.client.event.ColorHandlerEvent.Item;
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
 * @author grilled-salmon
 */
public class ModItems {
	private static final DeferredRegister<net.minecraft.world.item.Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OldGuns.MODID);
	
	private static boolean isInitialized;
	
	// Artillery	
	// Pieces

	// Ammo
	public static final RegistryObject<ArtilleryAmmoItem> MEDIUM_IRON_CANNONBALL = ITEMS.register("medium_iron_cannonball",
			() -> new MediumIronCannonball()
		);
	public static final RegistryObject<ArtilleryAmmoItem> MEDIUM_IRON_GRAPESHOT = ITEMS.register("medium_iron_grapeshot",
			() -> new MediumIronGrapeshot()
		);
	
	public static final RegistryObject<SmallPowderCharge> SMALL_POWDER_CHARGE = ITEMS.register("small_powder_charge",
			() -> new SmallPowderCharge()
		);
	public static final RegistryObject<MediumPowderCharge> MEDIUM_POWDER_CHARGE = ITEMS.register("medium_powder_charge",
			() -> new MediumPowderCharge()
		);
	public static final RegistryObject<LargePowderCharge> LARGE_POWDER_CHARGE = ITEMS.register("large_powder_charge",
			() -> new LargePowderCharge()
		);
	
	// Parts
	public static final RegistryObject<ArtilleryPartItem> SMALL_IRON_CANNON_BARREL = ITEMS.register("small_iron_cannon_barrel",
			() -> new SmallIronCannonBarrelItem()
		);
	public static final RegistryObject<ArtilleryPartItem> MEDIUM_IRON_CANNON_BARREL = ITEMS.register("medium_iron_cannon_barrel",
			() -> new MediumIronCannonBarrelItem()
		);
	public static final RegistryObject<ArtilleryPartItem> LARGE_IRON_CANNON_BARREL = ITEMS.register("large_iron_cannon_barrel",
			() -> new LargeIronCannonBarrelItem()
		);
	
	public static final RegistryObject<ArtilleryPartItem> SMALL_WOODEN_NAVAL_CARRIAGE = ITEMS.register("small_wooden_naval_carriage",
			() -> new SmallWoodenNavalCarriageItem()
		);
	public static final RegistryObject<ArtilleryPartItem> MEDIUM_WOODEN_NAVAL_CARRIAGE = ITEMS.register("medium_wooden_naval_carriage",
			() -> new MediumWoodenNavalCarriageItem()
		);
	public static final RegistryObject<ArtilleryPartItem> LARGE_WOODEN_NAVAL_CARRIAGE = ITEMS.register("large_wooden_naval_carriage",
			() -> new LargeWoodenNavalCarriageItem()
		);
	
	public static final RegistryObject<ArtilleryPartItem> TINY_WOODEN_CARRIAGE_WHEEL = ITEMS.register("tiny_wooden_carriage_wheel",
			() -> new TinyWoodenCarriageWheelItem()
		);
	public static final RegistryObject<ArtilleryPartItem> SMALL_WOODEN_CARRIAGE_WHEEL = ITEMS.register("small_wooden_carriage_wheel",
			() -> new SmallWoodenCarriageWheelItem()
		);
	public static final RegistryObject<ArtilleryPartItem> MEDIUM_WOODEN_CARRIAGE_WHEEL = ITEMS.register("medium_wooden_carriage_wheel",
			() -> new MediumWoodenCarriageWheelItem()
		);
	public static final RegistryObject<ArtilleryPartItem> LARGE_WOODEN_CARRIAGE_WHEEL = ITEMS.register("large_wooden_carriage_wheel",
			() -> new LargeWoodenCarriageWheelItem()
		);
	
	// Firearms
	// Matchlock
	public static final RegistryObject<FirearmItem> MATCHLOCK_DERRINGER = ITEMS.register("matchlock_derringer",
			() -> new MatchlockDerringerItem()
		);
	public static final RegistryObject<FirearmItem> MATCHLOCK_PISTOL = ITEMS.register("matchlock_pistol",
			() -> new MatchlockPistolItem()
		);
	public static final RegistryObject<FirearmItem> MATCHLOCK_ARQUEBUS = ITEMS.register("matchlock_arquebus",
			() -> new MatchlockArquebusItem()
		);
	public static final RegistryObject<FirearmItem> MATCHLOCK_CALIVER = ITEMS.register("matchlock_caliver",
			() -> new MatchlockCaliverItem()
		);
	public static final RegistryObject<FirearmItem> MATCHLOCK_MUSKETOON = ITEMS.register("matchlock_musketoon",
			() -> new MatchlockMusketoonItem()
		);
	public static final RegistryObject<FirearmItem>MATCHLOCK_MUSKET = ITEMS.register("matchlock_musket",
			() -> new MatchlockMusketItem()
		);
	public static final RegistryObject<FirearmItem> MATCHLOCK_LONG_MUSKET = ITEMS.register("matchlock_long_musket",
			() -> new MatchlockLongMusketItem()
		);
	public static final RegistryObject<FirearmItem> MATCHLOCK_BLUNDERBUSS_PISTOL = ITEMS.register("matchlock_blunderbuss_pistol",
			() -> new MatchlockBlunderbussPistolItem()
		);
	public static final RegistryObject<FirearmItem> MATCHLOCK_BLUNDERBUSS = ITEMS.register("matchlock_blunderbuss",
			() -> new MatchlockBlunderbussItem()
		);
	
	// Wheelock
	public static final RegistryObject<FirearmItem> WHEELLOCK_DERRINGER = ITEMS.register("wheellock_derringer",
			() -> new WheellockDerringerItem()
		);
	public static final RegistryObject<FirearmItem> WHEELLOCK_PISTOL = ITEMS.register("wheellock_pistol",
			() -> new WheellockPistolItem()
		);
	public static final RegistryObject<FirearmItem> WHEELLOCK_DOUBLEBARREL_PISTOL = ITEMS.register("wheellock_doublebarrel_pistol",
			() -> new WheellockDoublebarrelPistolItem()
		);
	public static final RegistryObject<FirearmItem> WHEELLOCK_ARQUEBUS = ITEMS.register("wheellock_arquebus",
			() -> new WheellockArquebusItem()
		);
	public static final RegistryObject<FirearmItem> WHEELLOCK_CALIVER = ITEMS.register("wheellock_caliver",
			() -> new WheellockCaliverItem()
		);
	public static final RegistryObject<FirearmItem> WHEELLOCK_MUSKETOON = ITEMS.register("wheellock_musketoon",
			() -> new WheellockMusketoonItem()
		);
	public static final RegistryObject<FirearmItem> WHEELLOCK_MUSKET = ITEMS.register("wheellock_musket",
			() -> new WheellockMusketItem()
		);
	public static final RegistryObject<FirearmItem> WHEELLOCK_LONG_MUSKET = ITEMS.register("wheellock_long_musket",
			() -> new WheellockLongMusketItem()
		);
	public static final RegistryObject<FirearmItem> WHEELLOCK_BLUNDERBUSS_PISTOL = ITEMS.register("wheellock_blunderbuss_pistol",
			() -> new WheellockBlunderbussPistolItem()
		);
	public static final RegistryObject<FirearmItem> WHEELLOCK_BLUNDERBUSS = ITEMS.register("wheellock_blunderbuss",
			() -> new WheellockBlunderbussItem()
		);
	public static final RegistryObject<FirearmItem> WHEELLOCK_HAND_MORTAR = ITEMS.register("wheellock_hand_mortar",
			() -> new WheellockHandMortarItem()
		);
	
	// Flintlock
	public static final RegistryObject<FirearmItem> FLINTLOCK_DERRINGER = ITEMS.register("flintlock_derringer",
			() -> new FlintlockDerringerItem()
		);
	public static final RegistryObject<FirearmItem> FLINTLOCK_DUCKFOOT_DERRINGER = ITEMS.register("flintlock_duckfoot_derringer",
			() -> new FlintlockDuckfootDerringerItem()
		);
	public static final RegistryObject<FirearmItem> FLINTLOCK_PISTOL = ITEMS.register("flintlock_pistol",
			() -> new FlintlockPistolItem()
		);
	public static final RegistryObject<FirearmItem> FLINTLOCK_PEPPERBOX_PISTOL = ITEMS.register("flintlock_pepperbox_pistol",
			() -> new FlintlockPepperboxPistolItem()
		);
	public static final RegistryObject<FirearmItem> FLINTLOCK_ARQUEBUS = ITEMS.register("flintlock_arquebus",
			() -> new FlintlockArquebusItem()
		);
	public static final RegistryObject<FirearmItem> FLINTLOCK_CALIVER = ITEMS.register("flintlock_caliver",
			() -> new FlintlockCaliverItem()
		);
	public static final RegistryObject<FirearmItem> FLINTLOCK_MUSKETOON = ITEMS.register("flintlock_musketoon",
			() -> new FlintlockMusketoonItem()
		);
	public static final RegistryObject<FirearmItem> FLINTLOCK_MUSKET = ITEMS.register("flintlock_musket",
			() -> new FlintlockMusketItem()
		);
	public static final RegistryObject<FirearmItem> FLINTLOCK_NOCK_GUN = ITEMS.register("flintlock_nock_gun",
			() -> new FlintlockNockGunItem()
		);
	public static final RegistryObject<FirearmItem> FLINTLOCK_LONG_MUSKET = ITEMS.register("flintlock_long_musket",
			() -> new FlintlockLongMusketItem()
		);
	public static final RegistryObject<FirearmItem> FLINTLOCK_BLUNDERBUSS_PISTOL = ITEMS.register("flintlock_blunderbuss_pistol",
			() -> new FlintlockBlunderbussPistolItem()
		);
	public static final RegistryObject<FirearmItem> FLINTLOCK_BLUNDERBUSS = ITEMS.register("flintlock_blunderbuss",
			() -> new FlintlockBlunderbussItem()
		);
	public static final RegistryObject<FirearmItem> FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS = ITEMS.register("flintlock_doublebarrel_blunderbuss",
			() -> new FlintlockDoublebarrelBlunderbussItem()
		);
	
	// Ammo
	// Stone
	public static final RegistryObject<FirearmAmmoItem> SMALL_STONE_MUSKET_BALL = ITEMS.register("small_stone_musket_ball",
			() -> new SmallStoneMusketBallItem()
		);
	public static final RegistryObject<FirearmAmmoItem> MEDIUM_STONE_MUSKET_BALL = ITEMS.register("medium_stone_musket_ball",
			() -> new MediumStoneMusketBallItem()
		);
	public static final RegistryObject<FirearmAmmoItem> LARGE_STONE_MUSKET_BALL = ITEMS.register("large_stone_musket_ball",
			() -> new LargeStoneMusketBallItem()
		);
	public static final RegistryObject<FirearmAmmoItem> SMALL_STONE_BIRDSHOT = ITEMS.register("small_stone_birdshot",
			() -> new SmallStoneBirdshotItem()
		);
	public static final RegistryObject<FirearmAmmoItem> MEDIUM_STONE_BIRDSHOT = ITEMS.register("medium_stone_birdshot",
			() -> new MediumStoneBirdshotItem()
		);
	public static final RegistryObject<FirearmAmmoItem> LARGE_STONE_BIRDSHOT = ITEMS.register("large_stone_birdshot",
			() -> new LargeStoneBirdshotItem()
		);
	
	// Iron
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
	
	// Lead
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
	public static final RegistryObject<FirearmPartItem> MATCHLOCK_MECHANISM = ITEMS.register("matchlock_mechanism",
			() -> new MatchlockMechanismItem()
		);
	public static final RegistryObject<FirearmPartItem> WHEELLOCK_MECHANISM = ITEMS.register("wheellock_mechanism",
			() -> new WheellockMechanismItem()
		);
	public static final RegistryObject<FirearmPartItem> FLINTLOCK_MECHANISM = ITEMS.register("flintlock_mechanism",
			() -> new FlintlockMechanismItem()
		);
	public static final RegistryObject<FirearmPartItem> CAPLOCK_MECHANISM = ITEMS.register("caplock_mechanism",
			() -> new CaplockMechanismItem()
		);
	
	public static final RegistryObject<FirearmPartItem> SMALL_WOODEN_HANDLE = ITEMS.register("small_wooden_handle",
			() -> new SmallWoodenHandleItem()
		);
	public static final RegistryObject<FirearmPartItem> MEDIUM_WOODEN_HANDLE = ITEMS.register("medium_wooden_handle",
			() -> new MediumWoodenHandleItem()
		);
	public static final RegistryObject<FirearmPartItem> LARGE_WOODEN_HANDLE = ITEMS.register("large_wooden_handle",
			() -> new LargeWoodenHandleItem()
		);
	
	public static final RegistryObject<FirearmPartItem> SMALL_WOODEN_STOCK = ITEMS.register("small_wooden_stock",
			() -> new SmallWoodenStockItem()
		);
	public static final RegistryObject<FirearmPartItem> MEDIUM_WOODEN_STOCK = ITEMS.register("medium_wooden_stock",
			() -> new MediumWoodenStockItem()
		);
	public static final RegistryObject<FirearmPartItem> LARGE_WOODEN_STOCK = ITEMS.register("large_wooden_stock",
			() -> new LargeWoodenStockItem()
		);
	
	public static final RegistryObject<FirearmPartItem> TINY_STONE_BARREL = ITEMS.register("tiny_stone_barrel",
			() -> new TinyStoneBarrelItem()
		);
	public static final RegistryObject<FirearmPartItem> SMALL_STONE_BARREL = ITEMS.register("small_stone_barrel",
			() -> new SmallStoneBarrelItem()
		);
	public static final RegistryObject<FirearmPartItem> MEDIUM_STONE_BARREL = ITEMS.register("medium_stone_barrel",
			() -> new MediumStoneBarrelItem()
		);
	public static final RegistryObject<FirearmPartItem> LARGE_STONE_BARREL = ITEMS.register("large_stone_barrel",
			() -> new LargeStoneBarrelItem()
		);
	
	public static final RegistryObject<FirearmPartItem> TINY_IRON_BARREL = ITEMS.register("tiny_iron_barrel",
			() -> new TinyIronBarrelItem()
		);
	public static final RegistryObject<FirearmPartItem> SMALL_IRON_BARREL = ITEMS.register("small_iron_barrel",
			() -> new SmallIronBarrelItem()
		);
	public static final RegistryObject<FirearmPartItem> MEDIUM_IRON_BARREL = ITEMS.register("medium_iron_barrel",
			() -> new MediumIronBarrelItem()
		);
	public static final RegistryObject<FirearmPartItem> LARGE_IRON_BARREL = ITEMS.register("large_iron_barrel",
			() -> new LargeIronBarrelItem()
		);
	
	public static final RegistryObject<FirearmPartItem> TINY_BRASS_BARREL = ITEMS.register("tiny_brass_barrel",
			() -> new TinyBrassBarrelItem()
		);
	public static final RegistryObject<FirearmPartItem> SMALL_BRASS_BARREL = ITEMS.register("small_brass_barrel",
			() -> new SmallBrassBarrelItem()
		);
	public static final RegistryObject<FirearmPartItem> MEDIUM_BRASS_BARREL = ITEMS.register("medium_brass_barrel",
			() -> new MediumBrassBarrelItem()
		);
	public static final RegistryObject<FirearmPartItem> LARGE_BRASS_BARREL = ITEMS.register("large_brass_barrel",
			() -> new LargeBrassBarrelItem()
		);
	
	public static final RegistryObject<FirearmPartItem> SMALL_STONE_FLARED_BARREL = ITEMS.register("small_stone_flared_barrel",
			() -> new SmallStoneFlaredBarrelItem()
		);
	public static final RegistryObject<FirearmPartItem> MEDIUM_STONE_FLARED_BARREL = ITEMS.register("medium_stone_flared_barrel",
			() -> new MediumStoneFlaredBarrelItem()
		);
	public static final RegistryObject<FirearmPartItem> LARGE_STONE_FLARED_BARREL = ITEMS.register("large_stone_flared_barrel",
			() -> new LargeStoneFlaredBarrelItem()
		);
	
	public static final RegistryObject<FirearmPartItem> SMALL_IRON_FLARED_BARREL = ITEMS.register("small_iron_flared_barrel",
			() -> new SmallIronFlaredBarrelItem()
		);
	public static final RegistryObject<FirearmPartItem> MEDIUM_IRON_FLARED_BARREL = ITEMS.register("medium_iron_flared_barrel",
			() -> new MediumIronFlaredBarrelItem()
		);
	public static final RegistryObject<FirearmPartItem> LARGE_IRON_FLARED_BARREL = ITEMS.register("large_iron_flared_barrel",
			() -> new LargeIronFlaredBarrelItem()
		);
	
	public static final RegistryObject<FirearmPartItem> SMALL_BRASS_FLARED_BARREL = ITEMS.register("small_brass_flared_barrel",
			() -> new SmallBrassFlaredBarrelItem()
		);
	public static final RegistryObject<FirearmPartItem> MEDIUM_BRASS_FLARED_BARREL = ITEMS.register("medium_brass_flared_barrel",
			() -> new MediumBrassFlaredBarrelItem()
		);
	public static final RegistryObject<FirearmPartItem> LARGE_BRASS_FLARED_BARREL = ITEMS.register("large_brass_flared_barrel",
			() -> new LargeBrassFlaredBarrelItem()
		);
	
	public static final RegistryObject<FirearmPartItem> WOOD_GEAR_SET = ITEMS.register("wood_gear_set",
			() -> new WoodGearSetItem()
		);
	public static final RegistryObject<FirearmPartItem> IRON_GEAR_SET = ITEMS.register("iron_gear_set",
			() -> new IronGearSetItem()
		);
	public static final RegistryObject<FirearmPartItem> GOLD_GEAR_SET = ITEMS.register("gold_gear_set",
			() -> new GoldGearSetItem()
		);
	
	public static final RegistryObject<FirearmPartItem> WOOD_TRIGGER_ASSEMBLY = ITEMS.register("wood_trigger_assembly",
			() -> new WoodTriggerAssemblyItem()
		);
	public static final RegistryObject<FirearmPartItem> IRON_TRIGGER_ASSEMBLY = ITEMS.register("iron_trigger_assembly",
			() -> new IronTriggerAssemblyItem()
		);
	public static final RegistryObject<FirearmPartItem> GOLD_TRIGGER_ASSEMBLY = ITEMS.register("gold_trigger_assembly",
			() -> new GoldTriggerAssemblyItem()
		);
	
	public static final RegistryObject<FirearmPartItem> MATCH_CORD = ITEMS.register("match_cord",
			() -> new MatchCordItem()
		);
	
	// Materials
	public static final RegistryObject<IronBitsItem> IRON_BITS = ITEMS.register("iron_bits",
			() -> new IronBitsItem()
		);
	
	public static final RegistryObject<LeadIngotItem> LEAD_INGOT = ITEMS.register("lead_ingot",
			() -> new LeadIngotItem()
		);
	public static final RegistryObject<LeadNuggetItem> LEAD_NUGGET = ITEMS.register("lead_nugget",
			() -> new LeadNuggetItem()
		);
	public static final RegistryObject<LeadBitsItem> LEAD_BITS = ITEMS.register("lead_bits",
			() -> new LeadBitsItem()
		);
	
	public static final RegistryObject<BrassIngotItem> BRASS_INGOT = ITEMS.register("brass_ingot",
			() -> new BrassIngotItem()
		);
	public static final RegistryObject<BrassNuggetItem> BRASS_NUGGET = ITEMS.register("brass_nugget",
			() -> new BrassNuggetItem()
		);
	
	public static final RegistryObject<NitrateSoilItem> NITRATE_SOIL = ITEMS.register("nitrate_soil",
			() -> new NitrateSoilItem()
		);	
	public static final RegistryObject<LiquidNiterItem> LIQUID_NITER = ITEMS.register("liquid_niter",
			() -> new LiquidNiterItem()
		);
	public static final RegistryObject<NiterItem> NITER = ITEMS.register("niter",
			() -> new NiterItem()
		);
	public static final RegistryObject<SulfurItem> SULFUR = ITEMS.register("sulfur",
			() -> new SulfurItem()
		);
	public static final RegistryObject<MediumGradeBlackPowderItem> MEDIUM_GRADE_BLACK_POWDER = ITEMS.register("medium_grade_black_powder",
			() -> new MediumGradeBlackPowderItem()
		);
	public static final RegistryObject<HighGradeBlackPowderItem> HIGH_GRADE_BLACK_POWDER = ITEMS.register("high_grade_black_powder",
			() -> new HighGradeBlackPowderItem()
		);
	
	// Tools
	public static final RegistryObject<RepairKitItem> REPAIR_KIT = ITEMS.register("repair_kit",
			() -> new RepairKitItem()
		);
	
	public static final RegistryObject<MortarAndPestleItem> MORTAR_AND_PESTLE = ITEMS.register("mortar_and_pestle",
			() -> new MortarAndPestleItem()
		);
	
	public static final RegistryObject<DesignNotesItem> DESIGN_NOTES = ITEMS.register("design_notes",
			() -> new DesignNotesItem()
		);
	
	public static final RegistryObject<RamRodItem> RAM_ROD = ITEMS.register("ram_rod",
			() -> new RamRodItem()
		);
	
	public static final RegistryObject<LongMatchItem> LONG_MATCH = ITEMS.register("long_match",
			() -> new LongMatchItem()
		);
	
	public static final RegistryObject<GunnersQuadrantItem> GUNNERS_QUADRANT = ITEMS.register("gunners_quadrant",
			() -> new GunnersQuadrantItem()
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
