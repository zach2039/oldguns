package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.ammo.AmmoTypes;
import com.zach2039.oldguns.api.ammo.ProjectilePowderType;
import com.zach2039.oldguns.api.artillery.ArtilleryPart;
import com.zach2039.oldguns.api.firearm.FirearmPart;
import com.zach2039.oldguns.api.firearm.FirearmTypes;
import com.zach2039.oldguns.world.item.ammo.artillery.ArtilleryAmmoItem;
import com.zach2039.oldguns.world.item.ammo.artillery.ArtilleryRocketAmmoItem;
import com.zach2039.oldguns.world.item.ammo.firearm.FirearmAmmoItem;
import com.zach2039.oldguns.world.item.ammo.firearm.FirearmPaperCartridgeItem;
import com.zach2039.oldguns.world.item.artillery.BombardItem;
import com.zach2039.oldguns.world.item.artillery.LargePowderCharge;
import com.zach2039.oldguns.world.item.artillery.MediumPowderCharge;
import com.zach2039.oldguns.world.item.artillery.SmallPowderCharge;
import com.zach2039.oldguns.world.item.equipment.BlastingPowderStickBlockItem;
import com.zach2039.oldguns.world.item.equipment.HorsemansPotHelmItem;
import com.zach2039.oldguns.world.item.equipment.MusketeerHatItem;
import com.zach2039.oldguns.world.item.firearm.FirearmItem;
import com.zach2039.oldguns.world.item.material.BurnableMaterialItem;
import com.zach2039.oldguns.world.item.material.MaterialItem;
import com.zach2039.oldguns.world.item.material.RepairPartItem;
import com.zach2039.oldguns.world.item.part.ArtilleryPartItem;
import com.zach2039.oldguns.world.item.part.FirearmPartItem;
import com.zach2039.oldguns.world.item.tools.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;
import java.util.Optional;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author zach2039
 */
public class ModItems {
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, OldGuns.MODID);
	
	private static boolean isInitialized;
	
	// Artillery	
	// Pieces
	public static final DeferredHolder<Item, ? extends BombardItem> BOMBARD = ITEMS.register("bombard", () -> new BombardItem(ModItems.defaultItemProperties().stacksTo(1)));
	
	// Ammo
	// Rockets
	public static final DeferredHolder<Item, ? extends ArtilleryRocketAmmoItem> MEDIUM_IRON_EXPLOSIVE_ROCKET = ITEMS.register("medium_iron_explosive_rocket", () -> new ArtilleryRocketAmmoItem(AmmoTypes.ArtilleryAmmo.MEDIUM_IRON_EXPLOSIVE_ROCKET));
	// Iron
	public static final DeferredHolder<Item, ? extends ArtilleryAmmoItem> MEDIUM_IRON_CANNONBALL = ITEMS.register("medium_iron_cannonball", () -> new ArtilleryAmmoItem(AmmoTypes.ArtilleryAmmo.MEDIUM_IRON_CANNONBALL));
	public static final DeferredHolder<Item, ? extends ArtilleryAmmoItem> MEDIUM_IRON_EXPLOSIVE_SHELL = ITEMS.register("medium_iron_explosive_shell", () -> new ArtilleryAmmoItem(AmmoTypes.ArtilleryAmmo.MEDIUM_IRON_EXPLOSIVE_SHELL));
	public static final DeferredHolder<Item, ? extends ArtilleryAmmoItem> MEDIUM_IRON_GRAPESHOT = ITEMS.register("medium_iron_grapeshot", () -> new ArtilleryAmmoItem(AmmoTypes.ArtilleryAmmo.MEDIUM_IRON_GRAPESHOT));
	public static final DeferredHolder<Item, ? extends ArtilleryAmmoItem> MEDIUM_IRON_CANISTER_SHOT = ITEMS.register("medium_iron_canister_shot", () -> new ArtilleryAmmoItem(AmmoTypes.ArtilleryAmmo.MEDIUM_IRON_CANISTER_SHOT));
	// Charges
	public static final DeferredHolder<Item, ? extends SmallPowderCharge> SMALL_POWDER_CHARGE = ITEMS.register("small_powder_charge", () -> new SmallPowderCharge());
	public static final DeferredHolder<Item, ? extends MediumPowderCharge> MEDIUM_POWDER_CHARGE = ITEMS.register("medium_powder_charge", () -> new MediumPowderCharge());
	public static final DeferredHolder<Item, ? extends LargePowderCharge> LARGE_POWDER_CHARGE = ITEMS.register("large_powder_charge", () -> new LargePowderCharge());
	
	// Parts
	// Cannon Barrel
	// Iron
	public static final DeferredHolder<Item, ? extends ArtilleryPartItem> SMALL_IRON_CANNON_BARREL = ITEMS.register("small_iron_cannon_barrel", () -> new ArtilleryPartItem(ArtilleryPart.SMALL_METAL_CANNON_BARREL));
	public static final DeferredHolder<Item, ? extends ArtilleryPartItem> MEDIUM_IRON_CANNON_BARREL = ITEMS.register("medium_iron_cannon_barrel", () -> new ArtilleryPartItem(ArtilleryPart.MEDIUM_METAL_CANNON_BARREL));
	public static final DeferredHolder<Item, ? extends ArtilleryPartItem> LARGE_IRON_CANNON_BARREL = ITEMS.register("large_iron_cannon_barrel", () -> new ArtilleryPartItem(ArtilleryPart.LARGE_METAL_CANNON_BARREL));
	// Naval Carriage
	// Wooden
	public static final DeferredHolder<Item, ? extends ArtilleryPartItem> SMALL_WOODEN_NAVAL_CARRIAGE = ITEMS.register("small_wooden_naval_carriage", () -> new ArtilleryPartItem(ArtilleryPart.SMALL_NAVAL_CARRIAGE));
	public static final DeferredHolder<Item, ? extends ArtilleryPartItem> MEDIUM_WOODEN_NAVAL_CARRIAGE = ITEMS.register("medium_wooden_naval_carriage", () -> new ArtilleryPartItem(ArtilleryPart.MEDIUM_NAVAL_CARRIAGE));
	public static final DeferredHolder<Item, ? extends ArtilleryPartItem> LARGE_WOODEN_NAVAL_CARRIAGE = ITEMS.register("large_wooden_naval_carriage", () -> new ArtilleryPartItem(ArtilleryPart.LARGE_NAVAL_CARRIAGE));
	// Carriage Wheel
	// Wooden
	public static final DeferredHolder<Item, ? extends ArtilleryPartItem> TINY_WOODEN_CARRIAGE_WHEEL = ITEMS.register("tiny_wooden_carriage_wheel", () -> new ArtilleryPartItem(ArtilleryPart.TINY_CARRIAGE_WHEEL));
	public static final DeferredHolder<Item, ? extends ArtilleryPartItem> SMALL_WOODEN_CARRIAGE_WHEEL = ITEMS.register("small_wooden_carriage_wheel", () -> new ArtilleryPartItem(ArtilleryPart.SMALL_CARRIAGE_WHEEL));
	public static final DeferredHolder<Item, ? extends ArtilleryPartItem> MEDIUM_WOODEN_CARRIAGE_WHEEL = ITEMS.register("medium_wooden_carriage_wheel", () -> new ArtilleryPartItem(ArtilleryPart.MEDIUM_CARRIAGE_WHEEL));
	public static final DeferredHolder<Item, ? extends ArtilleryPartItem> LARGE_WOODEN_CARRIAGE_WHEEL = ITEMS.register("large_wooden_carriage_wheel", () -> new ArtilleryPartItem(ArtilleryPart.LARGE_CARRIAGE_WHEEL));
	
	// Firearms
	// Matchlock
	public static final DeferredHolder<Item, ? extends FirearmItem> MATCHLOCK_DERRINGER = ITEMS.register("matchlock_derringer", () -> new FirearmItem(FirearmTypes.Muzzleloaders.MATCHLOCK_DERRINGER));
	public static final DeferredHolder<Item, ? extends FirearmItem> MATCHLOCK_PISTOL = ITEMS.register("matchlock_pistol", () -> new FirearmItem(FirearmTypes.Muzzleloaders.MATCHLOCK_PISTOL));
	public static final DeferredHolder<Item, ? extends FirearmItem> MATCHLOCK_BLUNDERBUSS_PISTOL = ITEMS.register("matchlock_blunderbuss_pistol", () -> new FirearmItem(FirearmTypes.Muzzleloaders.MATCHLOCK_BLUNDERBUSS_PISTOL));
	public static final DeferredHolder<Item, ? extends FirearmItem> MATCHLOCK_ARQUEBUS = ITEMS.register("matchlock_arquebus", () -> new FirearmItem(FirearmTypes.Muzzleloaders.MATCHLOCK_ARQUEBUS));
	public static final DeferredHolder<Item, ? extends FirearmItem> MATCHLOCK_CALIVER = ITEMS.register("matchlock_caliver", () -> new FirearmItem(FirearmTypes.Muzzleloaders.MATCHLOCK_CALIVER));
	public static final DeferredHolder<Item, ? extends FirearmItem> MATCHLOCK_MUSKETOON = ITEMS.register("matchlock_musketoon", () -> new FirearmItem(FirearmTypes.Muzzleloaders.MATCHLOCK_MUSKETOON));
	public static final DeferredHolder<Item, ? extends FirearmItem> MATCHLOCK_MUSKET = ITEMS.register("matchlock_musket", () -> new FirearmItem(FirearmTypes.Muzzleloaders.MATCHLOCK_MUSKET));
	public static final DeferredHolder<Item, ? extends FirearmItem> MATCHLOCK_LONG_MUSKET = ITEMS.register("matchlock_long_musket", () -> new FirearmItem(FirearmTypes.Muzzleloaders.MATCHLOCK_LONG_MUSKET));
	public static final DeferredHolder<Item, ? extends FirearmItem> MATCHLOCK_BLUNDERBUSS = ITEMS.register("matchlock_blunderbuss", () -> new FirearmItem(FirearmTypes.Muzzleloaders.MATCHLOCK_BLUNDERBUSS));
	
	// Wheelock
	public static final DeferredHolder<Item, ? extends FirearmItem> WHEELLOCK_DERRINGER = ITEMS.register("wheellock_derringer", () -> new FirearmItem(FirearmTypes.Muzzleloaders.WHEELLOCK_DERRINGER));
	public static final DeferredHolder<Item, ? extends FirearmItem> WHEELLOCK_PISTOL = ITEMS.register("wheellock_pistol", () -> new FirearmItem(FirearmTypes.Muzzleloaders.WHEELLOCK_PISTOL));
	public static final DeferredHolder<Item, ? extends FirearmItem> WHEELLOCK_DOUBLEBARREL_PISTOL = ITEMS.register("wheellock_doublebarrel_pistol", () -> new FirearmItem(FirearmTypes.Muzzleloaders.WHEELLOCK_DOUBLEBARREL_PISTOL));
	public static final DeferredHolder<Item, ? extends FirearmItem> WHEELLOCK_ARQUEBUS = ITEMS.register("wheellock_arquebus", () -> new FirearmItem(FirearmTypes.Muzzleloaders.WHEELLOCK_ARQUEBUS));
	public static final DeferredHolder<Item, ? extends FirearmItem> WHEELLOCK_CALIVER = ITEMS.register("wheellock_caliver", () -> new FirearmItem(FirearmTypes.Muzzleloaders.WHEELLOCK_CALIVER));
	public static final DeferredHolder<Item, ? extends FirearmItem> WHEELLOCK_MUSKETOON = ITEMS.register("wheellock_musketoon", () -> new FirearmItem(FirearmTypes.Muzzleloaders.WHEELLOCK_MUSKETOON));
	public static final DeferredHolder<Item, ? extends FirearmItem> WHEELLOCK_MUSKET = ITEMS.register("wheellock_musket", () -> new FirearmItem(FirearmTypes.Muzzleloaders.WHEELLOCK_MUSKET));
	public static final DeferredHolder<Item, ? extends FirearmItem> WHEELLOCK_LONG_MUSKET = ITEMS.register("wheellock_long_musket", () -> new FirearmItem(FirearmTypes.Muzzleloaders.WHEELLOCK_LONG_MUSKET));
	public static final DeferredHolder<Item, ? extends FirearmItem> WHEELLOCK_BLUNDERBUSS_PISTOL = ITEMS.register("wheellock_blunderbuss_pistol", () -> new FirearmItem(FirearmTypes.Muzzleloaders.WHEELLOCK_BLUNDERBUSS_PISTOL));
	public static final DeferredHolder<Item, ? extends FirearmItem> WHEELLOCK_BLUNDERBUSS = ITEMS.register("wheellock_blunderbuss", () -> new FirearmItem(FirearmTypes.Muzzleloaders.WHEELLOCK_BLUNDERBUSS));
	public static final DeferredHolder<Item, ? extends FirearmItem> WHEELLOCK_HAND_MORTAR = ITEMS.register("wheellock_hand_mortar", () -> new FirearmItem(FirearmTypes.Muzzleloaders.WHEELLOCK_HAND_MORTAR));
	
	// Flintlock
	public static final DeferredHolder<Item, ? extends FirearmItem> FLINTLOCK_DERRINGER = ITEMS.register("flintlock_derringer",	() -> new FirearmItem(FirearmTypes.Muzzleloaders.FLINTLOCK_DERRINGER));
	public static final DeferredHolder<Item, ? extends FirearmItem> FLINTLOCK_DUCKFOOT_DERRINGER = ITEMS.register("flintlock_duckfoot_derringer", () -> new FirearmItem(FirearmTypes.Muzzleloaders.FLINTLOCK_DUCKFOOT_DERRINGER));
	public static final DeferredHolder<Item, ? extends FirearmItem> FLINTLOCK_PISTOL = ITEMS.register("flintlock_pistol", () -> new FirearmItem(FirearmTypes.Muzzleloaders.FLINTLOCK_PISTOL));
	public static final DeferredHolder<Item, ? extends FirearmItem> FLINTLOCK_PEPPERBOX_PISTOL = ITEMS.register("flintlock_pepperbox_pistol", () -> new FirearmItem(FirearmTypes.Muzzleloaders.FLINTLOCK_PEPPERBOX_PISTOL));
	public static final DeferredHolder<Item, ? extends FirearmItem> FLINTLOCK_BLUNDERBUSS_PISTOL = ITEMS.register("flintlock_blunderbuss_pistol", () -> new FirearmItem(FirearmTypes.Muzzleloaders.FLINTLOCK_BLUNDERBUSS_PISTOL));
	public static final DeferredHolder<Item, ? extends FirearmItem> FLINTLOCK_ARQUEBUS = ITEMS.register("flintlock_arquebus", () -> new FirearmItem(FirearmTypes.Muzzleloaders.FLINTLOCK_ARQUEBUS));
	public static final DeferredHolder<Item, ? extends FirearmItem> FLINTLOCK_CALIVER = ITEMS.register("flintlock_caliver", () -> new FirearmItem(FirearmTypes.Muzzleloaders.FLINTLOCK_CALIVER));
	public static final DeferredHolder<Item, ? extends FirearmItem> FLINTLOCK_MUSKETOON = ITEMS.register("flintlock_musketoon", () -> new FirearmItem(FirearmTypes.Muzzleloaders.FLINTLOCK_MUSKETOON));
	public static final DeferredHolder<Item, ? extends FirearmItem> FLINTLOCK_MUSKET = ITEMS.register("flintlock_musket", () -> new FirearmItem(FirearmTypes.Muzzleloaders.FLINTLOCK_MUSKET));
	public static final DeferredHolder<Item, ? extends FirearmItem> FLINTLOCK_LONG_MUSKET = ITEMS.register("flintlock_long_musket", () -> new FirearmItem(FirearmTypes.Muzzleloaders.FLINTLOCK_LONG_MUSKET));
	public static final DeferredHolder<Item, ? extends FirearmItem> FLINTLOCK_NOCK_GUN = ITEMS.register("flintlock_nock_gun", () -> new FirearmItem(FirearmTypes.Muzzleloaders.FLINTLOCK_NOCK_GUN));
	public static final DeferredHolder<Item, ? extends FirearmItem> FLINTLOCK_BLUNDERBUSS = ITEMS.register("flintlock_blunderbuss", () -> new FirearmItem(FirearmTypes.Muzzleloaders.FLINTLOCK_BLUNDERBUSS));
	public static final DeferredHolder<Item, ? extends FirearmItem> FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS = ITEMS.register("flintlock_doublebarrel_blunderbuss", () -> new FirearmItem(FirearmTypes.Muzzleloaders.FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS));
	
	// Caplock
	public static final DeferredHolder<Item, ? extends FirearmItem> CAPLOCK_DERRINGER = ITEMS.register("caplock_derringer",	() -> new FirearmItem(FirearmTypes.Muzzleloaders.CAPLOCK_DERRINGER));
	public static final DeferredHolder<Item, ? extends FirearmItem> CAPLOCK_DUCKFOOT_DERRINGER = ITEMS.register("caplock_duckfoot_derringer", () -> new FirearmItem(FirearmTypes.Muzzleloaders.CAPLOCK_DUCKFOOT_DERRINGER));
	public static final DeferredHolder<Item, ? extends FirearmItem> CAPLOCK_PISTOL = ITEMS.register("caplock_pistol", () -> new FirearmItem(FirearmTypes.Muzzleloaders.CAPLOCK_PISTOL));
	public static final DeferredHolder<Item, ? extends FirearmItem> CAPLOCK_PEPPERBOX_PISTOL = ITEMS.register("caplock_pepperbox_pistol", () -> new FirearmItem(FirearmTypes.Muzzleloaders.CAPLOCK_PEPPERBOX_PISTOL));
	public static final DeferredHolder<Item, ? extends FirearmItem> CAPLOCK_BLUNDERBUSS_PISTOL = ITEMS.register("caplock_blunderbuss_pistol", () -> new FirearmItem(FirearmTypes.Muzzleloaders.CAPLOCK_BLUNDERBUSS_PISTOL));
	public static final DeferredHolder<Item, ? extends FirearmItem> CAPLOCK_ARQUEBUS = ITEMS.register("caplock_arquebus", () -> new FirearmItem(FirearmTypes.Muzzleloaders.CAPLOCK_ARQUEBUS));
	public static final DeferredHolder<Item, ? extends FirearmItem> CAPLOCK_CALIVER = ITEMS.register("caplock_caliver", () -> new FirearmItem(FirearmTypes.Muzzleloaders.CAPLOCK_CALIVER));
	public static final DeferredHolder<Item, ? extends FirearmItem> CAPLOCK_MUSKETOON = ITEMS.register("caplock_musketoon", () -> new FirearmItem(FirearmTypes.Muzzleloaders.CAPLOCK_MUSKETOON));
	public static final DeferredHolder<Item, ? extends FirearmItem> CAPLOCK_MUSKET = ITEMS.register("caplock_musket", () -> new FirearmItem(FirearmTypes.Muzzleloaders.CAPLOCK_MUSKET));
	public static final DeferredHolder<Item, ? extends FirearmItem> CAPLOCK_LONG_MUSKET = ITEMS.register("caplock_long_musket", () -> new FirearmItem(FirearmTypes.Muzzleloaders.CAPLOCK_LONG_MUSKET));
	public static final DeferredHolder<Item, ? extends FirearmItem> CAPLOCK_BLUNDERBUSS = ITEMS.register("caplock_blunderbuss", () -> new FirearmItem(FirearmTypes.Muzzleloaders.CAPLOCK_BLUNDERBUSS));
	public static final DeferredHolder<Item, ? extends FirearmItem> CAPLOCK_DOUBLEBARREL_BLUNDERBUSS = ITEMS.register("caplock_doublebarrel_blunderbuss", () -> new FirearmItem(FirearmTypes.Muzzleloaders.CAPLOCK_DOUBLEBARREL_BLUNDERBUSS));
	
	// Ammo
	// Stone
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> SMALL_STONE_MUSKET_BALL = ITEMS.register("small_stone_musket_ball", () -> new FirearmAmmoItem(AmmoTypes.FirearmAmmo.SMALL_STONE_MUSKET_BALL));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> SMALL_STONE_MUSKET_BALL_LOW_GRADE_PAPER_CARTRIDGE = ITEMS.register("small_stone_musket_ball_low_grade_paper_cartridge", () -> new FirearmPaperCartridgeItem(AmmoTypes.FirearmAmmo.SMALL_STONE_MUSKET_BALL, ProjectilePowderType.LOW_GRADE));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> SMALL_STONE_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE = ITEMS.register("small_stone_musket_ball_medium_grade_paper_cartridge", () -> new FirearmPaperCartridgeItem(AmmoTypes.FirearmAmmo.SMALL_STONE_MUSKET_BALL, ProjectilePowderType.MEDIUM_GRADE));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> MEDIUM_STONE_MUSKET_BALL = ITEMS.register("medium_stone_musket_ball", () -> new FirearmAmmoItem(AmmoTypes.FirearmAmmo.MEDIUM_STONE_MUSKET_BALL));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> MEDIUM_STONE_MUSKET_BALL_LOW_GRADE_PAPER_CARTRIDGE = ITEMS.register("medium_stone_musket_ball_low_grade_paper_cartridge", () -> new FirearmPaperCartridgeItem(AmmoTypes.FirearmAmmo.MEDIUM_STONE_MUSKET_BALL, ProjectilePowderType.LOW_GRADE));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> MEDIUM_STONE_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE = ITEMS.register("medium_stone_musket_ball_medium_grade_paper_cartridge", () -> new FirearmPaperCartridgeItem(AmmoTypes.FirearmAmmo.MEDIUM_STONE_MUSKET_BALL, ProjectilePowderType.MEDIUM_GRADE));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> LARGE_STONE_MUSKET_BALL = ITEMS.register("large_stone_musket_ball", () -> new FirearmAmmoItem(AmmoTypes.FirearmAmmo.LARGE_STONE_MUSKET_BALL));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> LARGE_STONE_MUSKET_BALL_LOW_GRADE_PAPER_CARTRIDGE = ITEMS.register("large_stone_musket_ball_low_grade_paper_cartridge", () -> new FirearmPaperCartridgeItem(AmmoTypes.FirearmAmmo.LARGE_STONE_MUSKET_BALL, ProjectilePowderType.LOW_GRADE));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> LARGE_STONE_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE = ITEMS.register("large_stone_musket_ball_medium_grade_paper_cartridge", () -> new FirearmPaperCartridgeItem(AmmoTypes.FirearmAmmo.LARGE_STONE_MUSKET_BALL, ProjectilePowderType.MEDIUM_GRADE));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> SMALL_STONE_BIRDSHOT = ITEMS.register("small_stone_birdshot", () -> new FirearmAmmoItem(AmmoTypes.FirearmAmmo.SMALL_STONE_BIRDSHOT));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> MEDIUM_STONE_BIRDSHOT = ITEMS.register("medium_stone_birdshot", () -> new FirearmAmmoItem(AmmoTypes.FirearmAmmo.MEDIUM_STONE_BIRDSHOT));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> LARGE_STONE_BIRDSHOT = ITEMS.register("large_stone_birdshot", () -> new FirearmAmmoItem(AmmoTypes.FirearmAmmo.LARGE_STONE_BIRDSHOT));
	
	// Iron
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> SMALL_IRON_MUSKET_BALL = ITEMS.register("small_iron_musket_ball", () -> new FirearmAmmoItem(AmmoTypes.FirearmAmmo.SMALL_IRON_MUSKET_BALL));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> SMALL_IRON_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE = ITEMS.register("small_iron_musket_ball_medium_grade_paper_cartridge", () -> new FirearmPaperCartridgeItem(AmmoTypes.FirearmAmmo.SMALL_IRON_MUSKET_BALL, ProjectilePowderType.MEDIUM_GRADE));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> SMALL_IRON_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE = ITEMS.register("small_iron_musket_ball_high_grade_paper_cartridge", () -> new FirearmPaperCartridgeItem(AmmoTypes.FirearmAmmo.SMALL_IRON_MUSKET_BALL, ProjectilePowderType.HIGH_GRADE));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> MEDIUM_IRON_MUSKET_BALL = ITEMS.register("medium_iron_musket_ball", () -> new FirearmAmmoItem(AmmoTypes.FirearmAmmo.MEDIUM_IRON_MUSKET_BALL));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> MEDIUM_IRON_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE = ITEMS.register("medium_iron_musket_ball_medium_grade_paper_cartridge", () -> new FirearmPaperCartridgeItem(AmmoTypes.FirearmAmmo.MEDIUM_IRON_MUSKET_BALL, ProjectilePowderType.MEDIUM_GRADE));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> MEDIUM_IRON_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE = ITEMS.register("medium_iron_musket_ball_high_grade_paper_cartridge", () -> new FirearmPaperCartridgeItem(AmmoTypes.FirearmAmmo.MEDIUM_IRON_MUSKET_BALL, ProjectilePowderType.HIGH_GRADE));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> LARGE_IRON_MUSKET_BALL = ITEMS.register("large_iron_musket_ball", () -> new FirearmAmmoItem(AmmoTypes.FirearmAmmo.LARGE_IRON_MUSKET_BALL));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> LARGE_IRON_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE = ITEMS.register("large_iron_musket_ball_medium_grade_paper_cartridge", () -> new FirearmPaperCartridgeItem(AmmoTypes.FirearmAmmo.LARGE_IRON_MUSKET_BALL, ProjectilePowderType.MEDIUM_GRADE));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> LARGE_IRON_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE = ITEMS.register("large_iron_musket_ball_high_grade_paper_cartridge", () -> new FirearmPaperCartridgeItem(AmmoTypes.FirearmAmmo.LARGE_IRON_MUSKET_BALL, ProjectilePowderType.HIGH_GRADE));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> SMALL_IRON_BUCKSHOT = ITEMS.register("small_iron_buckshot", () -> new FirearmAmmoItem(AmmoTypes.FirearmAmmo.SMALL_IRON_BUCKSHOT));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> MEDIUM_IRON_BUCKSHOT = ITEMS.register("medium_iron_buckshot", () -> new FirearmAmmoItem(AmmoTypes.FirearmAmmo.MEDIUM_IRON_BUCKSHOT));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> LARGE_IRON_BUCKSHOT = ITEMS.register("large_iron_buckshot", () -> new FirearmAmmoItem(AmmoTypes.FirearmAmmo.LARGE_IRON_BUCKSHOT));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> SMALL_IRON_BIRDSHOT = ITEMS.register("small_iron_birdshot", () -> new FirearmAmmoItem(AmmoTypes.FirearmAmmo.SMALL_IRON_BIRDSHOT));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> MEDIUM_IRON_BIRDSHOT = ITEMS.register("medium_iron_birdshot", () -> new FirearmAmmoItem(AmmoTypes.FirearmAmmo.MEDIUM_IRON_BIRDSHOT));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> LARGE_IRON_BIRDSHOT = ITEMS.register("large_iron_birdshot", () -> new FirearmAmmoItem(AmmoTypes.FirearmAmmo.LARGE_IRON_BIRDSHOT));
	
	// Lead
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> SMALL_LEAD_MUSKET_BALL = ITEMS.register("small_lead_musket_ball", () -> new FirearmAmmoItem(AmmoTypes.FirearmAmmo.SMALL_LEAD_MUSKET_BALL));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> SMALL_LEAD_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE = ITEMS.register("small_lead_musket_ball_medium_grade_paper_cartridge", () -> new FirearmPaperCartridgeItem(AmmoTypes.FirearmAmmo.SMALL_LEAD_MUSKET_BALL, ProjectilePowderType.MEDIUM_GRADE));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> SMALL_LEAD_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE = ITEMS.register("small_lead_musket_ball_high_grade_paper_cartridge", () -> new FirearmPaperCartridgeItem(AmmoTypes.FirearmAmmo.SMALL_LEAD_MUSKET_BALL, ProjectilePowderType.HIGH_GRADE));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> MEDIUM_LEAD_MUSKET_BALL = ITEMS.register("medium_lead_musket_ball", () -> new FirearmAmmoItem(AmmoTypes.FirearmAmmo.MEDIUM_LEAD_MUSKET_BALL));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> MEDIUM_LEAD_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE = ITEMS.register("medium_lead_musket_ball_medium_grade_paper_cartridge", () -> new FirearmPaperCartridgeItem(AmmoTypes.FirearmAmmo.MEDIUM_LEAD_MUSKET_BALL, ProjectilePowderType.MEDIUM_GRADE));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> MEDIUM_LEAD_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE = ITEMS.register("medium_lead_musket_ball_high_grade_paper_cartridge", () -> new FirearmPaperCartridgeItem(AmmoTypes.FirearmAmmo.MEDIUM_LEAD_MUSKET_BALL, ProjectilePowderType.HIGH_GRADE));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> LARGE_LEAD_MUSKET_BALL = ITEMS.register("large_lead_musket_ball", () -> new FirearmAmmoItem(AmmoTypes.FirearmAmmo.LARGE_LEAD_MUSKET_BALL));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> LARGE_LEAD_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE = ITEMS.register("large_lead_musket_ball_medium_grade_paper_cartridge", () -> new FirearmPaperCartridgeItem(AmmoTypes.FirearmAmmo.LARGE_LEAD_MUSKET_BALL, ProjectilePowderType.MEDIUM_GRADE));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> LARGE_LEAD_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE = ITEMS.register("large_lead_musket_ball_high_grade_paper_cartridge", () -> new FirearmPaperCartridgeItem(AmmoTypes.FirearmAmmo.LARGE_LEAD_MUSKET_BALL, ProjectilePowderType.HIGH_GRADE));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> SMALL_LEAD_BUCKSHOT = ITEMS.register("small_lead_buckshot", () -> new FirearmAmmoItem(AmmoTypes.FirearmAmmo.SMALL_LEAD_BUCKSHOT));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> MEDIUM_LEAD_BUCKSHOT = ITEMS.register("medium_lead_buckshot", () -> new FirearmAmmoItem(AmmoTypes.FirearmAmmo.MEDIUM_LEAD_BUCKSHOT));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> LARGE_LEAD_BUCKSHOT = ITEMS.register("large_lead_buckshot", () -> new FirearmAmmoItem(AmmoTypes.FirearmAmmo.LARGE_LEAD_BUCKSHOT));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> SMALL_LEAD_BIRDSHOT = ITEMS.register("small_lead_birdshot", () -> new FirearmAmmoItem(AmmoTypes.FirearmAmmo.SMALL_LEAD_BIRDSHOT));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> MEDIUM_LEAD_BIRDSHOT = ITEMS.register("medium_lead_birdshot", () -> new FirearmAmmoItem(AmmoTypes.FirearmAmmo.MEDIUM_LEAD_BIRDSHOT));
	public static final DeferredHolder<Item, ? extends FirearmAmmoItem> LARGE_LEAD_BIRDSHOT = ITEMS.register("large_lead_birdshot", () -> new FirearmAmmoItem(AmmoTypes.FirearmAmmo.LARGE_LEAD_BIRDSHOT));
	
	// Parts
	// Mechanism
	public static final DeferredHolder<Item, ? extends FirearmPartItem> MATCHLOCK_MECHANISM = ITEMS.register("matchlock_mechanism", () -> new FirearmPartItem(FirearmPart.MATCHLOCK_MECHANISM));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> WHEELLOCK_MECHANISM = ITEMS.register("wheellock_mechanism", () -> new FirearmPartItem(FirearmPart.WHEELLOCK_MECHANISM));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> FLINTLOCK_MECHANISM = ITEMS.register("flintlock_mechanism", () -> new FirearmPartItem(FirearmPart.FLINTLOCK_MECHANISM));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> CAPLOCK_MECHANISM = ITEMS.register("caplock_mechanism", () -> new FirearmPartItem(FirearmPart.CAPLOCK_MECHANISM));
	// Handle
	public static final DeferredHolder<Item, ? extends FirearmPartItem> SMALL_WOODEN_HANDLE = ITEMS.register("small_wooden_handle", () -> new FirearmPartItem(FirearmPart.SMALL_HANDLE));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> MEDIUM_WOODEN_HANDLE = ITEMS.register("medium_wooden_handle", () -> new FirearmPartItem(FirearmPart.MEDIUM_HANDLE));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> LARGE_WOODEN_HANDLE = ITEMS.register("large_wooden_handle", () -> new FirearmPartItem(FirearmPart.LARGE_HANDLE));
	// Stock
	public static final DeferredHolder<Item, ? extends FirearmPartItem> SMALL_WOODEN_STOCK = ITEMS.register("small_wooden_stock", () -> new FirearmPartItem(FirearmPart.SMALL_STOCK));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> MEDIUM_WOODEN_STOCK = ITEMS.register("medium_wooden_stock", () -> new FirearmPartItem(FirearmPart.MEDIUM_STOCK));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> LARGE_WOODEN_STOCK = ITEMS.register("large_wooden_stock", () -> new FirearmPartItem(FirearmPart.LARGE_STOCK));
	// Barrel
	// Stone
	public static final DeferredHolder<Item, ? extends FirearmPartItem> TINY_STONE_BARREL = ITEMS.register("tiny_stone_barrel", () -> new FirearmPartItem(FirearmPart.TINY_ROCK_BARREL));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> SMALL_STONE_BARREL = ITEMS.register("small_stone_barrel", () -> new FirearmPartItem(FirearmPart.SMALL_ROCK_BARREL));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> MEDIUM_STONE_BARREL = ITEMS.register("medium_stone_barrel", () -> new FirearmPartItem(FirearmPart.MEDIUM_ROCK_BARREL));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> LARGE_STONE_BARREL = ITEMS.register("large_stone_barrel", () -> new FirearmPartItem(FirearmPart.LARGE_ROCK_BARREL));
	// Iron
	public static final DeferredHolder<Item, ? extends FirearmPartItem> TINY_IRON_BARREL = ITEMS.register("tiny_iron_barrel", () -> new FirearmPartItem(FirearmPart.TINY_METAL_BARREL));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> SMALL_IRON_BARREL = ITEMS.register("small_iron_barrel", () -> new FirearmPartItem(FirearmPart.SMALL_METAL_BARREL));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> MEDIUM_IRON_BARREL = ITEMS.register("medium_iron_barrel", () -> new FirearmPartItem(FirearmPart.MEDIUM_METAL_BARREL));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> LARGE_IRON_BARREL = ITEMS.register("large_iron_barrel", () -> new FirearmPartItem(FirearmPart.LARGE_METAL_BARREL));
	// Brass
	public static final DeferredHolder<Item, ? extends FirearmPartItem> TINY_BRASS_BARREL = ITEMS.register("tiny_brass_barrel", () -> new FirearmPartItem(FirearmPart.TINY_METAL_BARREL));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> SMALL_BRASS_BARREL = ITEMS.register("small_brass_barrel", () -> new FirearmPartItem(FirearmPart.SMALL_METAL_BARREL));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> MEDIUM_BRASS_BARREL = ITEMS.register("medium_brass_barrel", () -> new FirearmPartItem(FirearmPart.MEDIUM_METAL_BARREL));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> LARGE_BRASS_BARREL = ITEMS.register("large_brass_barrel", () -> new FirearmPartItem(FirearmPart.LARGE_METAL_BARREL));
	// Flared Barrel
	// Stone
	public static final DeferredHolder<Item, ? extends FirearmPartItem> SMALL_STONE_FLARED_BARREL = ITEMS.register("small_stone_flared_barrel", () -> new FirearmPartItem(FirearmPart.SMALL_ROCK_FLARED_BARREL));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> MEDIUM_STONE_FLARED_BARREL = ITEMS.register("medium_stone_flared_barrel", () -> new FirearmPartItem(FirearmPart.MEDIUM_ROCK_FLARED_BARREL));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> LARGE_STONE_FLARED_BARREL = ITEMS.register("large_stone_flared_barrel", () -> new FirearmPartItem(FirearmPart.LARGE_ROCK_FLARED_BARREL));
	// Iron
	public static final DeferredHolder<Item, ? extends FirearmPartItem> SMALL_IRON_FLARED_BARREL = ITEMS.register("small_iron_flared_barrel", () -> new FirearmPartItem(FirearmPart.SMALL_METAL_FLARED_BARREL));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> MEDIUM_IRON_FLARED_BARREL = ITEMS.register("medium_iron_flared_barrel", () -> new FirearmPartItem(FirearmPart.MEDIUM_METAL_FLARED_BARREL));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> LARGE_IRON_FLARED_BARREL = ITEMS.register("large_iron_flared_barrel", () -> new FirearmPartItem(FirearmPart.LARGE_METAL_FLARED_BARREL));	
	public static final DeferredHolder<Item, ? extends FirearmPartItem> SMALL_BRASS_FLARED_BARREL = ITEMS.register("small_brass_flared_barrel", () -> new FirearmPartItem(FirearmPart.SMALL_METAL_FLARED_BARREL));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> MEDIUM_BRASS_FLARED_BARREL = ITEMS.register("medium_brass_flared_barrel", () -> new FirearmPartItem(FirearmPart.MEDIUM_METAL_FLARED_BARREL));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> LARGE_BRASS_FLARED_BARREL = ITEMS.register("large_brass_flared_barrel", () -> new FirearmPartItem(FirearmPart.LARGE_METAL_FLARED_BARREL));
	// Gear Set
	public static final DeferredHolder<Item, ? extends FirearmPartItem> WOOD_GEAR_SET = ITEMS.register("wood_gear_set", () -> new FirearmPartItem(FirearmPart.WOOD_GEAR_SET));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> IRON_GEAR_SET = ITEMS.register("iron_gear_set", () -> new FirearmPartItem(FirearmPart.IRON_GEAR_SET));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> GOLD_GEAR_SET = ITEMS.register("gold_gear_set", () -> new FirearmPartItem(FirearmPart.GOLD_GEAR_SET));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> DIAMOND_GEAR_SET = ITEMS.register("diamond_gear_set", () -> new FirearmPartItem(FirearmPart.DIAMOND_GEAR_SET));
	// Trigger Assembly
	public static final DeferredHolder<Item, ? extends FirearmPartItem> WOOD_TRIGGER_ASSEMBLY = ITEMS.register("wood_trigger_assembly", () -> new FirearmPartItem(FirearmPart.WOOD_TRIGGER_ASSEMBLY));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> IRON_TRIGGER_ASSEMBLY = ITEMS.register("iron_trigger_assembly", () -> new FirearmPartItem(FirearmPart.IRON_TRIGGER_ASSEMBLY));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> GOLD_TRIGGER_ASSEMBLY = ITEMS.register("gold_trigger_assembly", () -> new FirearmPartItem(FirearmPart.GOLD_TRIGGER_ASSEMBLY));
	public static final DeferredHolder<Item, ? extends FirearmPartItem> DIAMOND_TRIGGER_ASSEMBLY = ITEMS.register("diamond_trigger_assembly", () -> new FirearmPartItem(FirearmPart.DIAMOND_TRIGGER_ASSEMBLY));
	// Percussion Cap Cone
	public static final DeferredHolder<Item, ? extends FirearmPartItem> PERCUSSION_CAP_CONE = ITEMS.register("percussion_cap_cone", () -> new FirearmPartItem(FirearmPart.PERCUSSION_CAP_CONE));
	
	// Materials
	// Iron
	public static final DeferredHolder<Item, ? extends MaterialItem> IRON_BITS = ITEMS.register("iron_bits", () -> new MaterialItem());
	
	// Lead
	public static final DeferredHolder<Item, ? extends MaterialItem> LEAD_INGOT = ITEMS.register("lead_ingot", () -> new MaterialItem());
	public static final DeferredHolder<Item, ? extends MaterialItem> LEAD_NUGGET = ITEMS.register("lead_nugget", () -> new MaterialItem());
	public static final DeferredHolder<Item, ? extends MaterialItem> LEAD_BITS = ITEMS.register("lead_bits", () -> new MaterialItem());
	
	// Brass
	public static final DeferredHolder<Item, ? extends MaterialItem> BRASS_INGOT = ITEMS.register("brass_ingot", () -> new MaterialItem());
	public static final DeferredHolder<Item, ? extends MaterialItem> BRASS_NUGGET = ITEMS.register("brass_nugget", () -> new MaterialItem());
	
	// Mercury
	public static final DeferredHolder<Item, ? extends MaterialItem> MERCURY_INGOT = ITEMS.register("mercury_ingot", () -> new MaterialItem());
	public static final DeferredHolder<Item, ? extends MaterialItem> MERCURY_NUGGET = ITEMS.register("mercury_nugget", () -> new MaterialItem());
	
	// Niter
	public static final DeferredHolder<Item, ? extends MaterialItem> NITRATE_SOIL = ITEMS.register("nitrate_soil", () -> new MaterialItem());	
	public static final DeferredHolder<Item, ? extends MaterialItem> LIQUID_NITER_BOTTLE = ITEMS.register("liquid_niter_bottle", () -> new MaterialItem(16) {
		/**
	    * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see
	    * {@link #useOn}.
	    */
		@Override
		public InteractionResult useOn(UseOnContext ctx) {
			Level level = ctx.getLevel();
			BlockPos blockpos = ctx.getClickedPos();
			Player player = ctx.getPlayer();
			ItemStack itemstack = ctx.getItemInHand();
			BlockState blockstate = level.getBlockState(blockpos);
			if (ctx.getClickedFace() != Direction.DOWN && blockstate.getBlock() instanceof WeatheringCopper) {
				Optional<BlockState> optionalBlockState = ((WeatheringCopper)blockstate.getBlock()).getNext(blockstate);
				if (optionalBlockState.isPresent()) {
					
					player.setItemInHand(ctx.getHand(), ItemUtils.createFilledResult(itemstack, player, new ItemStack(Items.GLASS_BOTTLE)));
					player.awardStat(Stats.ITEM_USED.get(itemstack.getItem()));
					
					level.playSound((Player)null, blockpos, SoundEvents.BOTTLE_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
					level.gameEvent((Entity)null, GameEvent.FLUID_PLACE, blockpos);
					level.setBlockAndUpdate(blockpos, ((WeatheringCopper)blockstate.getBlock()).getNext(blockstate).get());
					
					//this.fizz(level, blockpos);
					level.levelEvent(1501, blockpos, 0);
					return InteractionResult.sidedSuccess(level.isClientSide);
				}
			}
			return InteractionResult.PASS;
		}
	});
	public static final DeferredHolder<Item, ? extends MaterialItem> NITER = ITEMS.register("niter", () -> new MaterialItem());
	
	// Sulfur
	public static final DeferredHolder<Item, ? extends MaterialItem> SULFUR = ITEMS.register("sulfur", () -> new MaterialItem());
	
	// Black Powder
	public static final DeferredHolder<Item, ? extends MaterialItem> ROCKET_POWDER = ITEMS.register("rocket_powder", () -> new MaterialItem());
	public static final DeferredHolder<Item, ? extends MaterialItem> BLASTING_POWDER = ITEMS.register("blasting_powder", () -> new MaterialItem());
	public static final DeferredHolder<Item, ? extends MaterialItem> PERCUSSION_POWDER = ITEMS.register("percussion_powder", () -> new MaterialItem());
	public static final DeferredHolder<Item, ? extends MaterialItem> MEDIUM_GRADE_BLACK_POWDER = ITEMS.register("medium_grade_black_powder", () -> new MaterialItem());
	public static final DeferredHolder<Item, ? extends MaterialItem> HIGH_GRADE_BLACK_POWDER = ITEMS.register("high_grade_black_powder", () -> new MaterialItem());
	
	// Fuses
	public static final DeferredHolder<Item, ? extends MaterialItem> BARK_STRANDS = ITEMS.register("bark_strands", () -> new BurnableMaterialItem());
	public static final DeferredHolder<Item, ? extends MaterialItem> MATCH_CORD = ITEMS.register("match_cord", () -> new MaterialItem());
	public static final DeferredHolder<Item, ? extends MaterialItem> FUSE = ITEMS.register("fuse", () -> new MaterialItem());
	
	// Ammo Crafting
	public static final DeferredHolder<Item, ? extends MaterialItem> WAXED_PAPER = ITEMS.register("waxed_paper", () -> new MaterialItem());
	public static final DeferredHolder<Item, ? extends MaterialItem> PERCUSSION_CAP = ITEMS.register("percussion_cap", () -> new MaterialItem(32));
	
	// Repair
	public static final DeferredHolder<Item, ? extends RepairPartItem> MATCHLOCK_REPAIR_PARTS = ITEMS.register("matchlock_repair_parts", () -> new RepairPartItem());
	public static final DeferredHolder<Item, ? extends RepairPartItem> WHEELLOCK_REPAIR_PARTS = ITEMS.register("wheellock_repair_parts", () -> new RepairPartItem());
	public static final DeferredHolder<Item, ? extends RepairPartItem> FLINTLOCK_REPAIR_PARTS = ITEMS.register("flintlock_repair_parts", () -> new RepairPartItem());
	public static final DeferredHolder<Item, ? extends RepairPartItem> CAPLOCK_REPAIR_PARTS = ITEMS.register("caplock_repair_parts", () -> new RepairPartItem());
	
	// Crafting Tools
	public static final DeferredHolder<Item, ? extends RepairKitItem> REPAIR_KIT = ITEMS.register("repair_kit", () -> new RepairKitItem());
	public static final DeferredHolder<Item, ? extends MortarAndPestleItem> MORTAR_AND_PESTLE = ITEMS.register("mortar_and_pestle", () -> new MortarAndPestleItem());
	public static final DeferredHolder<Item, ? extends HacksawItem> HACKSAW = ITEMS.register("hacksaw", () -> new HacksawItem());
	public static final DeferredHolder<Item, ? extends DesignNotesItem> DESIGN_NOTES = ITEMS.register("design_notes", () -> new DesignNotesItem());
	
	// Reloading
	public static final DeferredHolder<Item, ? extends PowderHornItem> POWDER_HORN = ITEMS.register("powder_horn", () -> new PowderHornItem());
	
	// Artillery Tools
	public static final DeferredHolder<Item, ? extends RamRodItem> RAM_ROD = ITEMS.register("ram_rod", () -> new RamRodItem());
	public static final DeferredHolder<Item, ? extends LongMatchItem> LONG_MATCH = ITEMS.register("long_match", () -> new LongMatchItem());
	public static final DeferredHolder<Item, ? extends GunnersQuadrantItem> GUNNERS_QUADRANT = ITEMS.register("gunners_quadrant", () -> new GunnersQuadrantItem());
	
	// Equipment
	public static final DeferredHolder<Item, ? extends BlastingPowderStickBlockItem> BLASTING_POWDER_STICK = ITEMS.register("blasting_powder_stick", () -> new BlastingPowderStickBlockItem());
	public static final DeferredHolder<Item, ? extends ArmorItem> MUSKETEER_HAT = ITEMS.register("musketeer_hat", () -> new MusketeerHatItem());
	public static final DeferredHolder<Item, ? extends ArmorItem> HORSEMANS_POT_HELM = ITEMS.register("horsemans_pot_helm", () -> new HorsemansPotHelmItem());
	
	// Spawn Eggs
	//public static final DeferredHolder<Item, ? extends ForgeSpawnEggItem> MUSKETEER_SKELETON_SPAWN_EGG = ITEMS.register("musketeer_skeleton_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.MUSKETEER_SKELETON, 0x636566, 0, defaultItemProperties()));
	//public static final DeferredHolder<Item, ? extends ForgeSpawnEggItem> HARQUEBUSIER_SKELETON_SPAWN_EGG = ITEMS.register("harquebusier_skeleton_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.HARQUEBUSIER_SKELETON, 0x282931, 0, defaultItemProperties()));
	
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
	 * Gets an {@link Item.Properties} instance with the {@link CreativeModeTab} set to {@link OldGuns#CREATIVE_MODE_TAB}.
	 *
	 * @author choonster
	 * @return The item properties
	 */
	private static Properties defaultItemProperties() {
		return new Properties();
	}

	static Collection<DeferredHolder<Item, ? extends Item>> orderedItems() {
		return ITEMS.getEntries();
	}
}
