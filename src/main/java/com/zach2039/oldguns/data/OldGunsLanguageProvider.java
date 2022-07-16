package com.zach2039.oldguns.data;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.data.lang.OldGunsCompendiumLang;
import com.zach2039.oldguns.fluid.group.FluidGroup;
import com.zach2039.oldguns.init.ModBlocks;
import com.zach2039.oldguns.init.ModEntities;
import com.zach2039.oldguns.init.ModFluids;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.text.OldGunsLang;
import com.zach2039.oldguns.world.level.block.GunsmithsBenchBlock;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fluids.FluidType;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author zach2039
 */
public class OldGunsLanguageProvider extends LanguageProvider {
	private final Map<EntityType<?>, String> ENTITY_TYPE_NAMES = new HashMap<>();

	public OldGunsLanguageProvider(final DataGenerator gen) {
		super(gen, OldGuns.MODID, "en_us");
	}

	@Override
	protected void addTranslations() {
		addEntities();
		addBlocks();
		addItems();
		addFluids();
		addPotions();
		addContainers();
		addCommands();
		addCapabilities();
		addKeyBindings();
		addConfig();
		addChatMessages();
		addSubtitles();
		addPatchouliEntries();
		addMisc();
	}

	@Override
	public String getName() {
		return "OldGuns " + super.getName();
	}

	private void addBlocks() {
		addBlock(ModBlocks.GUNSMITHS_BENCH, "Gunsmith's Bench");
		
		addBlock(ModBlocks.NITER_BEDDING, "Niter Bedding");
		
		addBlock(ModBlocks.LIQUID_NITER_CAULDRON, "Cauldron of Liquid Niter");
		
		addBlock(ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_CAKE, "Wet High-grade Black Powder Cake");
		
		addBlock(ModBlocks.HIGH_GRADE_BLACK_POWDER_CAKE, "High-grade Black Powder Cake");
		
		addBlock(ModBlocks.HIGH_GRADE_BLACK_POWDER_BLOCK, "Block of High-grade Black Powder");
		
		addBlock(ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_BLOCK, "Block of Wet High-grade Black Powder");
		
		addBlock(ModBlocks.MEDIUM_GRADE_BLACK_POWDER_BLOCK, "Block of Medium-grade Black Powder");
		
		addBlock(ModBlocks.WET_MEDIUM_GRADE_BLACK_POWDER_BLOCK, "Block of Wet Medium-grade Black Powder");
		
		addBlock(ModBlocks.LOW_GRADE_BLACK_POWDER_BLOCK, "Block of Low-grade Black Powder");
		
		addBlock(ModBlocks.WET_LOW_GRADE_BLACK_POWDER_BLOCK, "Block of Wet Low-grade Black Powder");
		
		addBlock(ModBlocks.CONGREVE_ROCKET_STAND, "Congreve Rocket Stand");
		
		addBlock(ModBlocks.MEDIUM_NAVAL_CANNON, "Medium Naval Cannon");
	}

	private void addItems() {
		addItem(ModItems.MEDIUM_IRON_EXPLOSIVE_ROCKET, "Medium Iron Explosive Rocket");
		
		addItem(ModItems.MEDIUM_IRON_CANNONBALL, "Medium Iron Cannonball");
		addItem(ModItems.MEDIUM_IRON_EXPLOSIVE_SHELL, "Medium Iron Explosive Shell");
		addItem(ModItems.MEDIUM_IRON_GRAPESHOT, "Medium Iron Grapeshot");
		addItem(ModItems.MEDIUM_IRON_CANISTER_SHOT, "Medium Iron Canister Shot");
		
		addItem(ModItems.SMALL_POWDER_CHARGE, "Small Powder Charge");
		addItem(ModItems.MEDIUM_POWDER_CHARGE, "Medium Powder Charge");
		addItem(ModItems.LARGE_POWDER_CHARGE, "Large Powder Charge");
		
		addItem(ModItems.SMALL_IRON_CANNON_BARREL, "Small Iron Cannon Barrel");
		addItem(ModItems.MEDIUM_IRON_CANNON_BARREL, "Medium Iron Cannon Barrel");
		addItem(ModItems.LARGE_IRON_CANNON_BARREL, "Large Iron Cannon Barrel");
		
		addItem(ModItems.SMALL_WOODEN_NAVAL_CARRIAGE, "Small Wooden Naval Carriage");
		addItem(ModItems.MEDIUM_WOODEN_NAVAL_CARRIAGE, "Medium Wooden Naval Carriage");
		addItem(ModItems.LARGE_WOODEN_NAVAL_CARRIAGE, "Large Wooden Naval Carriage");
		
		addItem(ModItems.TINY_WOODEN_CARRIAGE_WHEEL, "Tiny Wooden Carriage Wheel");
		addItem(ModItems.SMALL_WOODEN_CARRIAGE_WHEEL, "Small Wooden Carriage Wheel");
		addItem(ModItems.MEDIUM_WOODEN_CARRIAGE_WHEEL, "Medium Wooden Carriage Wheel");
		addItem(ModItems.LARGE_WOODEN_CARRIAGE_WHEEL, "Large Wooden Carriage Wheel");
		
		addItem(ModItems.MATCHLOCK_DERRINGER, "Matchlock Derringer");
		addItem(ModItems.MATCHLOCK_PISTOL, "Matchlock Pistol");
		addItem(ModItems.MATCHLOCK_ARQUEBUS, "Matchlock Arquebus");
		addItem(ModItems.MATCHLOCK_CALIVER, "Matchlock Caliver");
		addItem(ModItems.MATCHLOCK_MUSKETOON, "Matchlock Musketoon");
		addItem(ModItems.MATCHLOCK_MUSKET, "Matchlock Musket");
		addItem(ModItems.MATCHLOCK_LONG_MUSKET, "Matchlock Long Musket");
		addItem(ModItems.MATCHLOCK_BLUNDERBUSS_PISTOL, "Matchlock Blunderbuss Pistol");
		addItem(ModItems.MATCHLOCK_BLUNDERBUSS, "Matchlock Blunderbuss");
		
		addItem(ModItems.WHEELLOCK_DERRINGER, "Wheellock Derringer");
		addItem(ModItems.WHEELLOCK_PISTOL, "Wheellock Pistol");
		addItem(ModItems.WHEELLOCK_DOUBLEBARREL_PISTOL, "Wheellock Doublebarrel Pistol");
		addItem(ModItems.WHEELLOCK_ARQUEBUS, "Wheellock Arquebus");
		addItem(ModItems.WHEELLOCK_CALIVER, "Wheellock Caliver");
		addItem(ModItems.WHEELLOCK_MUSKETOON, "Wheellock Musketoon");
		addItem(ModItems.WHEELLOCK_MUSKET, "Wheellock Musket");
		addItem(ModItems.WHEELLOCK_LONG_MUSKET, "Wheellock Long Musket");
		addItem(ModItems.WHEELLOCK_BLUNDERBUSS, "Wheellock Blunderbuss");
		addItem(ModItems.WHEELLOCK_BLUNDERBUSS_PISTOL, "Wheellock Blunderbuss Pistol");
		addItem(ModItems.WHEELLOCK_HAND_MORTAR, "Wheellock Hand Mortar");
		
		addItem(ModItems.FLINTLOCK_DERRINGER, "Flintlock Derringer");
		addItem(ModItems.FLINTLOCK_DUCKFOOT_DERRINGER, "Flintlock Duckfoot Derringer");
		addItem(ModItems.FLINTLOCK_PISTOL, "Flintlock Pistol");
		addItem(ModItems.FLINTLOCK_PEPPERBOX_PISTOL, "Flintlock Pepperbox Pistol");
		addItem(ModItems.FLINTLOCK_ARQUEBUS, "Flintlock Arquebus");
		addItem(ModItems.FLINTLOCK_CALIVER, "Flintlock Caliver");
		addItem(ModItems.FLINTLOCK_MUSKETOON, "Flintlock Musketoon");
		addItem(ModItems.FLINTLOCK_MUSKET, "Flintlock Musket");
		addItem(ModItems.FLINTLOCK_NOCK_GUN, "Flintlock Nock Gun");
		addItem(ModItems.FLINTLOCK_LONG_MUSKET, "Flintlock Long Musket");
		addItem(ModItems.FLINTLOCK_BLUNDERBUSS_PISTOL, "Flintlock Blunderbuss Pistol");
		addItem(ModItems.FLINTLOCK_BLUNDERBUSS, "Flintlock Blunderbuss");
		addItem(ModItems.FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS, "Flintlock Doublebarrel Blunderbuss");
		
		addItem(ModItems.CAPLOCK_DERRINGER, "Caplock Derringer");
		addItem(ModItems.CAPLOCK_DUCKFOOT_DERRINGER, "Caplock Duckfoot Derringer");
		addItem(ModItems.CAPLOCK_PISTOL, "Caplock Pistol");
		addItem(ModItems.CAPLOCK_PEPPERBOX_PISTOL, "Caplock Pepperbox Pistol");
		addItem(ModItems.CAPLOCK_ARQUEBUS, "Caplock Arquebus");
		addItem(ModItems.CAPLOCK_CALIVER, "Caplock Caliver");
		addItem(ModItems.CAPLOCK_MUSKETOON, "Caplock Musketoon");
		addItem(ModItems.CAPLOCK_MUSKET, "Caplock Musket");
		addItem(ModItems.CAPLOCK_LONG_MUSKET, "Caplock Long Musket");
		addItem(ModItems.CAPLOCK_BLUNDERBUSS_PISTOL, "Caplock Blunderbuss Pistol");
		addItem(ModItems.CAPLOCK_BLUNDERBUSS, "Caplock Blunderbuss");
		addItem(ModItems.CAPLOCK_DOUBLEBARREL_BLUNDERBUSS, "Caplock Doublebarrel Blunderbuss");
	
		addItem(ModItems.SMALL_STONE_MUSKET_BALL, "Small Stone Musket Ball");
		addItem(ModItems.SMALL_STONE_MUSKET_BALL_LOW_GRADE_PAPER_CARTRIDGE, "Small Stone Musket Ball Paper Cartridge");
		addItem(ModItems.SMALL_STONE_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE, "Small Stone Musket Ball Paper Cartridge");
		addItem(ModItems.MEDIUM_STONE_MUSKET_BALL, "Medium Stone Musket Ball");
		addItem(ModItems.MEDIUM_STONE_MUSKET_BALL_LOW_GRADE_PAPER_CARTRIDGE, "Medium Stone Musket Ball Paper Cartridge");
		addItem(ModItems.MEDIUM_STONE_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE, "Medium Stone Musket Ball Paper Cartridge");
		addItem(ModItems.LARGE_STONE_MUSKET_BALL, "Large Stone Musket Ball");
		addItem(ModItems.LARGE_STONE_MUSKET_BALL_LOW_GRADE_PAPER_CARTRIDGE, "Large Stone Musket Ball Paper Cartridge");
		addItem(ModItems.LARGE_STONE_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE, "Large Stone Musket Ball Paper Cartridge");
		
		addItem(ModItems.SMALL_STONE_BIRDSHOT, "Small Stone Birdshot");
		addItem(ModItems.MEDIUM_STONE_BIRDSHOT, "Medium Stone Birdshot");
		addItem(ModItems.LARGE_STONE_BIRDSHOT, "Large Stone Birdshot");
		
		addItem(ModItems.SMALL_IRON_MUSKET_BALL, "Small Iron Musket Ball");
		addItem(ModItems.SMALL_IRON_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE, "Small Iron Musket Ball Paper Cartridge");
		addItem(ModItems.SMALL_IRON_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE, "Small Iron Musket Ball Paper Cartridge");
		addItem(ModItems.MEDIUM_IRON_MUSKET_BALL, "Medium Iron Musket Ball");
		addItem(ModItems.MEDIUM_IRON_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE, "Medium Iron Musket Ball Paper Cartridge");
		addItem(ModItems.MEDIUM_IRON_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE, "Medium Iron Musket Ball Paper Cartridge");
		addItem(ModItems.LARGE_IRON_MUSKET_BALL, "Large Iron Musket Ball");
		addItem(ModItems.LARGE_IRON_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE, "Large Iron Musket Ball Paper Cartridge");
		addItem(ModItems.LARGE_IRON_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE, "Large Iron Musket Ball Paper Cartridge");
		
		addItem(ModItems.SMALL_IRON_BUCKSHOT, "Small Iron Buckshot");
		addItem(ModItems.MEDIUM_IRON_BUCKSHOT, "Medium Iron Buckshot");
		addItem(ModItems.LARGE_IRON_BUCKSHOT, "Large Iron Buckshot");
		
		addItem(ModItems.SMALL_IRON_BIRDSHOT, "Small Iron Birdshot");
		addItem(ModItems.MEDIUM_IRON_BIRDSHOT, "Medium Iron Birdshot");
		addItem(ModItems.LARGE_IRON_BIRDSHOT, "Large Iron Birdshot");
		
		addItem(ModItems.SMALL_LEAD_MUSKET_BALL, "Small Lead Musket Ball");
		addItem(ModItems.SMALL_LEAD_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE, "Small Lead Musket Ball Paper Cartridge");
		addItem(ModItems.SMALL_LEAD_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE, "Small Lead Musket Ball Paper Cartridge");
		addItem(ModItems.MEDIUM_LEAD_MUSKET_BALL, "Medium Lead Musket Ball");
		addItem(ModItems.MEDIUM_LEAD_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE, "Medium Lead Musket Ball Paper Cartridge");
		addItem(ModItems.MEDIUM_LEAD_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE, "Medium Lead Musket Ball Paper Cartridge");
		addItem(ModItems.LARGE_LEAD_MUSKET_BALL, "Large Lead Musket Ball");
		addItem(ModItems.LARGE_LEAD_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE, "Large Lead Musket Ball Paper Cartridge");
		addItem(ModItems.LARGE_LEAD_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE, "Large Lead Musket Ball Paper Cartridge");
		
		addItem(ModItems.SMALL_LEAD_BUCKSHOT, "Small Lead Buckshot");
		addItem(ModItems.MEDIUM_LEAD_BUCKSHOT, "Medium Lead Buckshot");
		addItem(ModItems.LARGE_LEAD_BUCKSHOT, "Large Lead Buckshot");
		
		addItem(ModItems.SMALL_LEAD_BIRDSHOT, "Small Lead Birdshot");
		addItem(ModItems.MEDIUM_LEAD_BIRDSHOT, "Medium Lead Birdshot");
		addItem(ModItems.LARGE_LEAD_BIRDSHOT, "Large Lead Birdshot");
		
		addItem(ModItems.MATCHLOCK_MECHANISM, "Matchlock Mechanism");
		addItem(ModItems.WHEELLOCK_MECHANISM, "Wheellock Mechanism");
		addItem(ModItems.FLINTLOCK_MECHANISM, "Flintlock Mechanism");
		addItem(ModItems.CAPLOCK_MECHANISM, "Caplock Mechanism");
		
		addItem(ModItems.SMALL_WOODEN_HANDLE, "Small Wooden Handle");
		addItem(ModItems.MEDIUM_WOODEN_HANDLE, "Medium Wooden Handle");
		addItem(ModItems.LARGE_WOODEN_HANDLE, "Large Wooden Handle");
		
		addItem(ModItems.SMALL_WOODEN_STOCK, "Small Wooden Stock");
		addItem(ModItems.MEDIUM_WOODEN_STOCK, "Medium Wooden Stock");
		addItem(ModItems.LARGE_WOODEN_STOCK, "Large Wooden Stock");

		addItem(ModItems.TINY_STONE_BARREL, "Tiny Stone Barrel");
		addItem(ModItems.SMALL_STONE_BARREL, "Small Stone Barrel");
		addItem(ModItems.MEDIUM_STONE_BARREL, "Medium Stone Barrel");
		addItem(ModItems.LARGE_STONE_BARREL, "Large Stone Barrel");
		
		addItem(ModItems.TINY_IRON_BARREL, "Tiny Iron Barrel");
		addItem(ModItems.SMALL_IRON_BARREL, "Small Iron Barrel");
		addItem(ModItems.MEDIUM_IRON_BARREL, "Medium Iron Barrel");
		addItem(ModItems.LARGE_IRON_BARREL, "Large Iron Barrel");
		
		addItem(ModItems.TINY_BRASS_BARREL, "Tiny Brass Barrel");
		addItem(ModItems.SMALL_BRASS_BARREL, "Small Brass Barrel");
		addItem(ModItems.MEDIUM_BRASS_BARREL, "Medium Brass Barrel");
		addItem(ModItems.LARGE_BRASS_BARREL, "Large Brass Barrel");
		
		addItem(ModItems.SMALL_STONE_FLARED_BARREL, "Small Stone Flared Barrel");
		addItem(ModItems.MEDIUM_STONE_FLARED_BARREL, "Medium Stone Flared Barrel");
		addItem(ModItems.LARGE_STONE_FLARED_BARREL, "Large Stone Flared Barrel");
		
		addItem(ModItems.SMALL_IRON_FLARED_BARREL, "Small Iron Flared Barrel");
		addItem(ModItems.MEDIUM_IRON_FLARED_BARREL, "Medium Iron Flared Barrel");
		addItem(ModItems.LARGE_IRON_FLARED_BARREL, "Large Iron Flared Barrel");
		
		addItem(ModItems.SMALL_BRASS_FLARED_BARREL, "Small Brass Flared Barrel");
		addItem(ModItems.MEDIUM_BRASS_FLARED_BARREL, "Medium Brass Flared Barrel");
		addItem(ModItems.LARGE_BRASS_FLARED_BARREL, "Large Brass Flared Barrel");
		
		addItem(ModItems.WOOD_GEAR_SET, "Wood Gear Set");
		addItem(ModItems.IRON_GEAR_SET, "Iron Gear Set");
		addItem(ModItems.GOLD_GEAR_SET, "Gold Gear Set");
		addItem(ModItems.DIAMOND_GEAR_SET, "Diamond Gear Set");
		
		addItem(ModItems.WOOD_TRIGGER_ASSEMBLY, "Wood Trigger Assembly");
		addItem(ModItems.IRON_TRIGGER_ASSEMBLY, "Iron Trigger Assembly");
		addItem(ModItems.GOLD_TRIGGER_ASSEMBLY, "Gold Trigger Assembly");
		addItem(ModItems.DIAMOND_TRIGGER_ASSEMBLY, "Diamond Trigger Assembly");
		
		addItem(ModItems.PERCUSSION_CAP_CONE, "Percussion Cap Cone");
		
		addItem(ModItems.IRON_BITS, "Iron Bits");
		
		addItem(ModItems.LEAD_INGOT, "Lead Ingot");
		addItem(ModItems.LEAD_NUGGET, "Lead Nugget");
		addItem(ModItems.LEAD_BITS, "Lead Bits");
		
		addItem(ModItems.BRASS_INGOT, "Brass Ingot");
		addItem(ModItems.BRASS_NUGGET, "Brass Nugget");
		
		addItem(ModItems.MERCURY_INGOT, "Mercury Orb");
		addItem(ModItems.MERCURY_NUGGET, "Tiny Mercury Orb");
		
		addItem(ModItems.NITRATE_SOIL, "Nitrate Soil");
		addItem(ModItems.LIQUID_NITER_BOTTLE, "Liquid Niter Bottle");
		addItem(ModItems.NITER, "Niter");
		addItem(ModItems.SULFUR, "Sulfur");
		
		addItem(ModItems.BLASTING_POWDER, "Blasting Powder");
		addItem(ModItems.ROCKET_POWDER, "Rocket Powder");
		addItem(ModItems.PERCUSSION_POWDER, "Percussion Powder");
		add("item.oldguns.low_grade_black_powder", "Low-grade Black Powder");
		addItem(ModItems.MEDIUM_GRADE_BLACK_POWDER, "Medium-grade Black Powder");
		addItem(ModItems.HIGH_GRADE_BLACK_POWDER, "High-grade Black Powder");
		
		addItem(ModItems.MATCH_CORD, "Match Cord");
		addItem(ModItems.FUSE, "Fuse");
		
		addItem(ModItems.BARK_STRANDS, "Bark Strands");
		
		addItem(ModItems.WAXED_PAPER, "Waxed Paper");
		addItem(ModItems.PERCUSSION_CAP, "Percussion Cap");
		
		addItem(ModItems.MATCHLOCK_REPAIR_PARTS, "Matchlock Repair Parts");
		addItem(ModItems.WHEELLOCK_REPAIR_PARTS, "Wheellock Repair Parts");
		addItem(ModItems.FLINTLOCK_REPAIR_PARTS, "Flintlock Repair Parts");
		addItem(ModItems.CAPLOCK_REPAIR_PARTS, "Caplock Repair Parts");
		addItem(ModItems.REPAIR_KIT, "Firearm Repair Kit");
		addItem(ModItems.MORTAR_AND_PESTLE, "Mortar and Pestle");
		addItem(ModItems.HACKSAW, "Hacksaw");
		addItem(ModItems.POWDER_HORN, "Powder Horn");
		addItem(ModItems.DESIGN_NOTES, "Design Notes");
		
		addItem(ModItems.GUNNERS_QUADRANT, "Gunner's Quadrant");
		addItem(ModItems.LONG_MATCH, "Long Match");
		addItem(ModItems.RAM_ROD, "Ram Rod");
		
		addItem(ModItems.BLASTING_POWDER_STICK, "Blasting Powder Stick");
		addItem(ModItems.MUSKETEER_HAT, "Musketeer Hat");
		addItem(ModItems.HORSEMANS_POT_HELM, "Horseman's Pot Helm");
		
		//addItem(ModItems.MUSKETEER_SKELETON_SPAWN_EGG, "Musketeer Skeleton Spawn Egg");
		//addItem(ModItems.HARQUEBUSIER_SKELETON_SPAWN_EGG, "Harquebusier Skeleton Spawn Egg");
		
		add("item.oldguns.compendium", "Old Guns Compendium");
	}

	private void addFluids() {
		addFluidGroup(ModFluids.LIQUID_NITER, "Liquid Niter");
	}

	private void addEntities() {
		addEntityType(ModEntities.BULLET_PROJECTILE, "Projectile");
		//addEntityType(ModEntities.MUSKETEER_SKELETON, "Musketeer Skeleton");
		//addEntityType(ModEntities.HARQUEBUSIER_SKELETON, "Harquebusier Skeleton");
	}

	private void addPotions() {
		
	}

	private void addContainers() {
		add(GunsmithsBenchBlock.CONTAINER_TITLE.getString(), "Gunsmith's Bench");
	}

	private void addCommands() {
		
	}

	private void addCapabilities() {
		
	}

	private void addChatMessages() {
		add(OldGunsLang.MESSAGE_DEATH_FIREARM, "%1$s was shot to death by %2$s.");
		add(OldGunsLang.MESSAGE_DEATH_ARTILLERY, "%1$s was blown apart from artillery fire by %2$s.");
		
		add(OldGunsLang.MESSAGE_ARTILLERY_NAME, "This is a %1$s.");
		add(OldGunsLang.MESSAGE_ARTILLERY_MAX_SLOTS, "Max ammo slot count is: %1$s");
		add(OldGunsLang.MESSAGE_ARTILLERY_SLOT_STATE, "State of slot %1$s is: %2$s");
		
		add(OldGunsLang.MESSAGE_ARTILLERY_NOT_READY, "This artillery piece is not ready to fire.");
	}

	private void addKeyBindings() {

	}

	private void addConfig() {

	}

	private void addSubtitles() {

	}
	
	private void addPatchouliEntries() {
		addPatchouliBookLanding(OldGunsCompendiumLang.LANDING);
		
		addPatchouliBookCategory("basics", OldGunsCompendiumLang.Basics.NAME);
		addPatchouliBookCategoryDescription("basics", OldGunsCompendiumLang.Basics.DESCRIPTION);
		
		addPatchouliBookCategory("firearms", OldGunsCompendiumLang.Firearms.NAME);
		addPatchouliBookCategoryDescription("firearms", OldGunsCompendiumLang.Firearms.DESCRIPTION);
		
		addPatchouliBookCategory("artillery", OldGunsCompendiumLang.Artillery.NAME);
		addPatchouliBookCategoryDescription("artillery", OldGunsCompendiumLang.Artillery.DESCRIPTION);
		
		addPatchouliBookCategory("black_powder_production", OldGunsCompendiumLang.BlackPowderProduction.NAME);
		addPatchouliBookCategoryDescription("black_powder_production", OldGunsCompendiumLang.BlackPowderProduction.DESCRIPTION);
		
		addPatchouliBookEntry("basics.welcome", OldGunsCompendiumLang.Basics.Welcome.ENTRY);
		addPatchouliBookPage("basics.welcome0", OldGunsCompendiumLang.Basics.Welcome.PAGE0);
		addPatchouliBookPage("basics.welcome1", OldGunsCompendiumLang.Basics.Welcome.PAGE1);
		
		addPatchouliBookEntry("basics.gunsmiths_bench", OldGunsCompendiumLang.Basics.GunsmithsBench.ENTRY);
		addPatchouliBookPage("basics.gunsmiths_bench0", OldGunsCompendiumLang.Basics.GunsmithsBench.PAGE0);
		
		addPatchouliBookEntry("basics.mortar_and_pestle", OldGunsCompendiumLang.Basics.MortarAndPestle.ENTRY);
		addPatchouliBookPage("basics.mortar_and_pestle0", OldGunsCompendiumLang.Basics.MortarAndPestle.PAGE0);
		
		addPatchouliBookEntry("basics.black_powder", OldGunsCompendiumLang.Basics.BlackPowder.ENTRY);
		addPatchouliBookPage("basics.black_powder0", OldGunsCompendiumLang.Basics.BlackPowder.PAGE0);
		
		addPatchouliBookEntry("basics.firearms", OldGunsCompendiumLang.Basics.Firearms.ENTRY);
		addPatchouliBookPage("basics.firearms0", OldGunsCompendiumLang.Basics.Firearms.PAGE0);
		
		addPatchouliBookEntry("basics.artillery", OldGunsCompendiumLang.Basics.Artillery.ENTRY);
		addPatchouliBookPage("basics.artillery0", OldGunsCompendiumLang.Basics.Artillery.PAGE0);
		
		addPatchouliBookEntry("basics.design_notes", OldGunsCompendiumLang.Basics.DesignNotes.ENTRY);
		addPatchouliBookPage("basics.design_notes0", OldGunsCompendiumLang.Basics.DesignNotes.PAGE0);
		
		addPatchouliBookEntry("firearms.general", OldGunsCompendiumLang.Firearms.General.ENTRY);
		addPatchouliBookPage("firearms.general0", OldGunsCompendiumLang.Firearms.General.PAGE0);
		addPatchouliBookPage("firearms.general1", OldGunsCompendiumLang.Firearms.General.PAGE1);
		addPatchouliBookPage("firearms.general2", OldGunsCompendiumLang.Firearms.General.PAGE2);
		addPatchouliBookPage("firearms.general3", OldGunsCompendiumLang.Firearms.General.PAGE3);
		
		addPatchouliBookEntry("firearms.paper_cartridges", OldGunsCompendiumLang.Firearms.PaperCartridges.ENTRY);
		addPatchouliBookPage("firearms.paper_cartridges0", OldGunsCompendiumLang.Firearms.PaperCartridges.PAGE0);
		addPatchouliBookLang("firearms.stone_paper_cartridge", OldGunsCompendiumLang.Firearms.PaperCartridges.STONE_PAPER_CARTRIDGE);
		addPatchouliBookLang("firearms.iron_paper_cartridge", OldGunsCompendiumLang.Firearms.PaperCartridges.IRON_PAPER_CARTRIDGE);
		addPatchouliBookLang("firearms.lead_paper_cartridge", OldGunsCompendiumLang.Firearms.PaperCartridges.LEAD_PAPER_CARTRIDGE);
		
		addPatchouliBookEntry("artillery.general", OldGunsCompendiumLang.Artillery.General.ENTRY);
		addPatchouliBookPage("artillery.general0", OldGunsCompendiumLang.Artillery.General.PAGE0);
		
		addPatchouliBookEntry("artillery.artillery_tools", OldGunsCompendiumLang.Artillery.ArtilleryTools.ENTRY);
		addPatchouliBookLang("artillery.powder_charge", OldGunsCompendiumLang.Artillery.ArtilleryTools.POWDER_CHARGE);
		addPatchouliBookPage("artillery.artillery_tools0", OldGunsCompendiumLang.Artillery.ArtilleryTools.PAGE0);
		addPatchouliBookPage("artillery.artillery_tools1", OldGunsCompendiumLang.Artillery.ArtilleryTools.PAGE1);
		addPatchouliBookPage("artillery.artillery_tools2", OldGunsCompendiumLang.Artillery.ArtilleryTools.PAGE2);
		addPatchouliBookPage("artillery.artillery_tools3", OldGunsCompendiumLang.Artillery.ArtilleryTools.PAGE3);
		addPatchouliBookPage("artillery.artillery_tools4", OldGunsCompendiumLang.Artillery.ArtilleryTools.PAGE4);
		
		addPatchouliBookEntry("artillery.loading_and_firing", OldGunsCompendiumLang.Artillery.LoadingAndFiring.ENTRY);
		addPatchouliBookPage("artillery.loading_and_firing0", OldGunsCompendiumLang.Artillery.LoadingAndFiring.PAGE0);
		
		addPatchouliBookEntry("artillery.ammo_types", OldGunsCompendiumLang.Artillery.AmmoTypes.ENTRY);
		addPatchouliBookLang("artillery.cannonball", OldGunsCompendiumLang.Artillery.AmmoTypes.CANNONBALL);
		addPatchouliBookLang("artillery.grapeshot", OldGunsCompendiumLang.Artillery.AmmoTypes.GRAPESHOT);
		addPatchouliBookLang("artillery.canister_shot", OldGunsCompendiumLang.Artillery.AmmoTypes.CANISTER_SHOT);
		addPatchouliBookPage("artillery.ammo_types0", OldGunsCompendiumLang.Artillery.AmmoTypes.PAGE0);
		addPatchouliBookPage("artillery.ammo_types1", OldGunsCompendiumLang.Artillery.AmmoTypes.PAGE1);
		addPatchouliBookPage("artillery.ammo_types2", OldGunsCompendiumLang.Artillery.AmmoTypes.PAGE2);
		addPatchouliBookPage("artillery.ammo_types3", OldGunsCompendiumLang.Artillery.AmmoTypes.PAGE3);
		
		addPatchouliBookEntry("black_powder_production.low_grade", OldGunsCompendiumLang.BlackPowderProduction.LowGrade.ENTRY);
		addPatchouliBookPage("black_powder_production.low_grade0", OldGunsCompendiumLang.BlackPowderProduction.LowGrade.PAGE0);
		addPatchouliBookPage("black_powder_production.low_grade1", OldGunsCompendiumLang.BlackPowderProduction.LowGrade.PAGE1);
		
		addPatchouliBookEntry("black_powder_production.medium_grade", OldGunsCompendiumLang.BlackPowderProduction.MediumGrade.ENTRY);
		addPatchouliBookPage("black_powder_production.medium_grade0", OldGunsCompendiumLang.BlackPowderProduction.MediumGrade.PAGE0);
		addPatchouliBookPage("black_powder_production.medium_grade1", OldGunsCompendiumLang.BlackPowderProduction.MediumGrade.PAGE1);
		
		addPatchouliBookEntry("black_powder_production.high_grade", OldGunsCompendiumLang.BlackPowderProduction.HighGrade.ENTRY);
		addPatchouliBookLang("black_powder_production.pressing", OldGunsCompendiumLang.BlackPowderProduction.HighGrade.PRESSING);
		addPatchouliBookLang("black_powder_production.high_grade_cake", OldGunsCompendiumLang.BlackPowderProduction.HighGrade.HIGH_GRADE_CAKE);
		addPatchouliBookPage("black_powder_production.high_grade0", OldGunsCompendiumLang.BlackPowderProduction.HighGrade.PAGE0);
		addPatchouliBookPage("black_powder_production.high_grade1", OldGunsCompendiumLang.BlackPowderProduction.HighGrade.PAGE1);
		addPatchouliBookPage("black_powder_production.high_grade3", OldGunsCompendiumLang.BlackPowderProduction.HighGrade.PAGE3);
		addPatchouliBookPage("black_powder_production.high_grade4", OldGunsCompendiumLang.BlackPowderProduction.HighGrade.PAGE4);
		
		addPatchouliBookEntry("black_powder_production.niter", OldGunsCompendiumLang.BlackPowderProduction.Niter.ENTRY);
		addPatchouliBookLang("black_powder_production.crystalization", OldGunsCompendiumLang.BlackPowderProduction.Niter.CRYSTALIZATION);
		addPatchouliBookPage("black_powder_production.niter0", OldGunsCompendiumLang.BlackPowderProduction.Niter.PAGE0);
		addPatchouliBookPage("black_powder_production.niter2", OldGunsCompendiumLang.BlackPowderProduction.Niter.PAGE2);
		addPatchouliBookPage("black_powder_production.niter3", OldGunsCompendiumLang.BlackPowderProduction.Niter.PAGE3);
		addPatchouliBookPage("black_powder_production.niter4", OldGunsCompendiumLang.BlackPowderProduction.Niter.PAGE4);
		
		addPatchouliBookEntry("black_powder_production.sulfur", OldGunsCompendiumLang.BlackPowderProduction.Sulfur.ENTRY);
		addPatchouliBookPage("black_powder_production.sulfur0", OldGunsCompendiumLang.BlackPowderProduction.Sulfur.PAGE0);
		addPatchouliBookPage("black_powder_production.sulfur1", OldGunsCompendiumLang.BlackPowderProduction.Sulfur.PAGE1);
		
		addPatchouliBookEntry("black_powder_production.fuses", OldGunsCompendiumLang.BlackPowderProduction.Fuses.ENTRY);
		addPatchouliBookPage("black_powder_production.fuses0", OldGunsCompendiumLang.BlackPowderProduction.Fuses.PAGE0);
		addPatchouliBookPage("black_powder_production.fuses1", OldGunsCompendiumLang.BlackPowderProduction.Fuses.PAGE1);
	}

	private void addMisc() {
		add("itemGroup." + OldGuns.MODID, "Old Guns");
		
		add(OldGuns.MODID + ".medium_naval_cannon", "Medium Naval Cannon");
		
		add("attribute.name." + OldGuns.MODID + ".generic.bullet_armor_pierce", "Bullet Armor Pierce");
		add("attribute.name." + OldGuns.MODID + ".generic.mounted_firearm_accuracy", "Mounted Firearm Accuracy");
	}

	@Override
	public void addEntityType(final Supplier<? extends EntityType<?>> key, final String name) {
		super.addEntityType(key, name);
		ENTITY_TYPE_NAMES.put(key.get(), name);
	}

	private String getPotionItemTranslationKey(final Supplier<? extends Potion> potion, final Item item) {
		final ItemStack stack = PotionUtils.setPotion(new ItemStack(item), potion.get());
		return stack.getItem().getDescriptionId(stack);
	}

	private void add(final OldGunsLang lang, final String value) {
		add(lang.getTranslationKey(), value);
	}
	
	private <
	TYPE extends FluidType,
	STILL extends Fluid, FLOWING extends Fluid,
	BLOCK extends LiquidBlock, BUCKET extends Item,
	GROUP extends FluidGroup<TYPE, STILL, FLOWING, BLOCK, BUCKET>
	>
	void addFluidGroup(final GROUP group, final String name) {
		add(group.getType().get().getDescriptionId(), name);
		addBlock(group.getBlock(), name);
		addItem(group.getBucket(), String.format("%s Bucket", name));
	}
	
	private void addPatchouliBookPage(final String key, final String value) {		
		add("oldguns.page." + key, value);
	}
	
	private void addPatchouliBookLang(final String key, final String value) {		
		add("oldguns.lang." + key, value);
	}
	
	private void addPatchouliBookEntry(final String key, final String value) {
		add("oldguns.entry." + key, value);
	}
	
	private void addPatchouliBookCategory(final String key, final String value) {
		add("oldguns.category." + key, value);
	}
	
	private void addPatchouliBookCategoryDescription(final String key, final String value) {
		add("oldguns.description." + key, value);
	}

	private void addPatchouliBookLanding(final String value) {
		add("oldguns.landing", value);
	}
	
	private void add(final OldGunsLang prefix, final StringRepresentable enumValue, final String name) {
		add(prefix.getTranslationKey() + "." + enumValue.getSerializedName(), name);
	}
	
	private String translate(final DyeColor colour) {
		return translate("color.minecraft." + colour.getName());
	}

	private String translate(final String key) {
		return I18n.get(key);
	}
}
