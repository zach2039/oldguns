package com.zach2039.oldguns.data;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Supplier;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.block.GunsmithsBenchBlock;
import com.zach2039.oldguns.data.lang.OldGunsCompendiumLang;
import com.zach2039.oldguns.init.ModBlocks;
import com.zach2039.oldguns.init.ModEntities;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.text.OldGunsLang;

import net.minecraft.client.resources.I18n;
import net.minecraft.data.DataGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.common.data.LanguageProvider;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author grilled-salmon
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
	}

	private void addItems() {
		//addItem(ModItems.BOMBARD, "Bombard [WIP]");
		
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
	
		addItem(ModItems.SMALL_STONE_MUSKET_BALL, "Small Stone Musket Ball");
		addItem(ModItems.MEDIUM_STONE_MUSKET_BALL, "Medium Stone Musket Ball");
		addItem(ModItems.LARGE_STONE_MUSKET_BALL, "Large Stone Musket Ball");
		
		addItem(ModItems.SMALL_STONE_BIRDSHOT, "Small Stone Birdshot");
		addItem(ModItems.MEDIUM_STONE_BIRDSHOT, "Medium Stone Birdshot");
		addItem(ModItems.LARGE_STONE_BIRDSHOT, "Large Stone Birdshot");
		
		addItem(ModItems.SMALL_IRON_MUSKET_BALL, "Small Iron Musket Ball");
		addItem(ModItems.MEDIUM_IRON_MUSKET_BALL, "Medium Iron Musket Ball");
		addItem(ModItems.LARGE_IRON_MUSKET_BALL, "Large Iron Musket Ball");
		
		addItem(ModItems.SMALL_IRON_BUCKSHOT, "Small Iron Buckshot");
		addItem(ModItems.MEDIUM_IRON_BUCKSHOT, "Medium Iron Buckshot");
		addItem(ModItems.LARGE_IRON_BUCKSHOT, "Large Iron Buckshot");
		
		addItem(ModItems.SMALL_IRON_BIRDSHOT, "Small Iron Birdshot");
		addItem(ModItems.MEDIUM_IRON_BIRDSHOT, "Medium Iron Birdshot");
		addItem(ModItems.LARGE_IRON_BIRDSHOT, "Large Iron Birdshot");
		
		addItem(ModItems.SMALL_LEAD_MUSKET_BALL, "Small Lead Musket Ball");
		addItem(ModItems.MEDIUM_LEAD_MUSKET_BALL, "Medium Lead Musket Ball");
		addItem(ModItems.LARGE_LEAD_MUSKET_BALL, "Large Lead Musket Ball");
		
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
		
		addItem(ModItems.WOOD_TRIGGER_ASSEMBLY, "Wood Trigger Assembly");
		addItem(ModItems.IRON_TRIGGER_ASSEMBLY, "Iron Trigger Assembly");
		addItem(ModItems.GOLD_TRIGGER_ASSEMBLY, "Gold Trigger Assembly");
		
		addItem(ModItems.MATCH_CORD, "Match Cord");
		
		addItem(ModItems.IRON_BITS, "Iron Bits");
		
		addItem(ModItems.LEAD_INGOT, "Lead Ingot");
		addItem(ModItems.LEAD_NUGGET, "Lead Nugget");
		addItem(ModItems.LEAD_BITS, "Lead Bits");
		
		addItem(ModItems.BRASS_INGOT, "Brass Ingot");
		addItem(ModItems.BRASS_NUGGET, "Brass Nugget");
		
		addItem(ModItems.NITRATE_SOIL, "Nitrate Soil");
		addItem(ModItems.LIQUID_NITER, "Bottle of Liquid Niter");
		addItem(ModItems.NITER, "Niter");
		addItem(ModItems.SULFUR, "Sulfur");
		addItem(ModItems.MEDIUM_GRADE_BLACK_POWDER, "Medium-grade Black Powder");
		addItem(ModItems.HIGH_GRADE_BLACK_POWDER, "High-grade Black Powder");
		
		addItem(ModItems.REPAIR_KIT, "Firearm Repair Kit");
		addItem(ModItems.MORTAR_AND_PESTLE, "Mortar and Pestle");
		addItem(ModItems.DESIGN_NOTES, "Design Notes");
		
		add("item.oldguns.compendium", "Old Guns Compendium");
	}

	private void addFluids() {
		
	}

	private void addEntities() {
		addEntityType(ModEntities.BULLET_PROJECTILE, "Projectile");
		//addEntityType(ModEntities.BOMBARD, "Bombard");
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
		add(OldGunsLang.MESSAGE_DEATH_FIREARM, "%1$s was shot to death.");
		add(OldGunsLang.MESSAGE_DEATH_FIREARM_PLAYER, "%1$s was shot to death by %2$s.");
		add(OldGunsLang.MESSAGE_DEATH_ARTILLERY, "%1$s was blown apart by artillery fire.");
		add(OldGunsLang.MESSAGE_DEATH_ARTILLERY_PLAYER, "%1$s was blown apart by artillery fire by %2$s.");
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
		addPatchouliBookEntry("basics.high_grade", OldGunsCompendiumLang.BlackPowderProduction.HighGrade.ENTRY);
		
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
		
	}

	private void addMisc() {
		add("itemGroup." + OldGuns.MODID, "Old Guns");
	}

	public void addEntityType(Supplier<? extends EntityType<?>> key, String name) {
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
	
	private void add(final OldGunsLang prefix, final IStringSerializable enumValue, final String name) {
		add(prefix.getTranslationKey() + "." + enumValue.getSerializedName(), name);
	}

	private String translate(final DyeColor colour) {
		return translate("color.minecraft." + colour.getName());
	}

	private String translate(final String key) {
		return I18n.get(key);
	}
}
