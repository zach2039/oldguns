package com.zach2039.oldguns.data;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Supplier;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.data.lang.OldGunsCompendiumLang;
import com.zach2039.oldguns.init.ModBlocks;
import com.zach2039.oldguns.init.ModEntities;
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
		
		addBlock(ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_CAKE, "Wet High Grade Black Powder Cake");
		
		addBlock(ModBlocks.HIGH_GRADE_BLACK_POWDER_CAKE, "High Grade Black Powder Cake");
		
		addBlock(ModBlocks.HIGH_GRADE_BLACK_POWDER_BLOCK, "Block of High Grade Black Powder");
		
		addBlock(ModBlocks.WET_HIGH_GRADE_BLACK_POWDER_BLOCK, "Block of Wet High Grade Black Powder");
		
		addBlock(ModBlocks.MEDIUM_GRADE_BLACK_POWDER_BLOCK, "Block of Medium Grade Black Powder");
		
		addBlock(ModBlocks.WET_MEDIUM_GRADE_BLACK_POWDER_BLOCK, "Block of Wet Medium Grade Black Powder");
	}

	private void addItems() {
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
		
		addItem(ModItems.FLINTLOCK_MECHANISM, "Flintlock Mechanism");
		
		addItem(ModItems.SMALL_WOODEN_HANDLE, "Small Wooden Handle");
		addItem(ModItems.MEDIUM_WOODEN_HANDLE, "Medium Wooden Handle");
		addItem(ModItems.LARGE_WOODEN_HANDLE, "Large Wooden Handle");
		
		addItem(ModItems.SMALL_WOODEN_STOCK, "Small Wooden Stock");
		addItem(ModItems.MEDIUM_WOODEN_STOCK, "Medium Wooden Stock");
		addItem(ModItems.LARGE_WOODEN_STOCK, "Large Wooden Stock");
		
		addItem(ModItems.TINY_IRON_BARREL, "Tiny Iron Barrel");
		addItem(ModItems.SMALL_IRON_BARREL, "Small Iron Barrel");
		addItem(ModItems.MEDIUM_IRON_BARREL, "Medium Iron Barrel");
		addItem(ModItems.LARGE_IRON_BARREL, "Large Iron Barrel");
		
		addItem(ModItems.TINY_BRASS_BARREL, "Tiny Brass Barrel");
		addItem(ModItems.SMALL_BRASS_BARREL, "Small Brass Barrel");
		addItem(ModItems.MEDIUM_BRASS_BARREL, "Medium Brass Barrel");
		addItem(ModItems.LARGE_BRASS_BARREL, "Large Brass Barrel");
		
		addItem(ModItems.SMALL_IRON_FLARED_BARREL, "Small Iron Flared Barrel");
		addItem(ModItems.MEDIUM_IRON_FLARED_BARREL, "Medium Iron Flared Barrel");
		addItem(ModItems.LARGE_IRON_FLARED_BARREL, "Large Iron Flared Barrel");
		
		addItem(ModItems.SMALL_BRASS_FLARED_BARREL, "Small Brass Flared Barrel");
		addItem(ModItems.MEDIUM_BRASS_FLARED_BARREL, "Medium Brass Flared Barrel");
		addItem(ModItems.LARGE_BRASS_FLARED_BARREL, "Large Brass Flared Barrel");
		
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
		addItem(ModItems.MEDIUM_GRADE_BLACK_POWDER, "Medium Grade Black Powder");
		addItem(ModItems.HIGH_GRADE_BLACK_POWDER, "High Grade Black Powder");
		
		addItem(ModItems.REPAIR_KIT, "Firearm Repair Kit");
		addItem(ModItems.MORTAR_AND_PESTLE, "Mortar and Pestle");
		
		add("item.oldguns.compendium", "Old Guns Compendium");
	}

	private void addFluids() {
		
	}

	private void addEntities() {
		addEntityType(ModEntities.BULLET_PROJECTILE, "Projectile");
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
		
		addPatchouliBookEntry("basics.welcome", OldGunsCompendiumLang.Basics.Welcome.ENTRY);
		addPatchouliBookPage("basics.welcome0", OldGunsCompendiumLang.Basics.Welcome.PAGE0);
		addPatchouliBookPage("basics.welcome1", OldGunsCompendiumLang.Basics.Welcome.PAGE1);
		
		addPatchouliBookEntry("basics.gunsmiths_bench", OldGunsCompendiumLang.Basics.GunsmithsBench.ENTRY);
		addPatchouliBookPage("basics.gunsmiths_bench0", OldGunsCompendiumLang.Basics.GunsmithsBench.PAGE0);
		addPatchouliBookPage("basics.gunsmiths_bench1", OldGunsCompendiumLang.Basics.GunsmithsBench.PAGE1);
		
		addPatchouliBookEntry("basics.mortar_and_pestle", OldGunsCompendiumLang.Basics.MortarAndPestle.ENTRY);
		addPatchouliBookPage("basics.mortar_and_pestle0", OldGunsCompendiumLang.Basics.MortarAndPestle.PAGE0);
		addPatchouliBookPage("basics.mortar_and_pestle1", OldGunsCompendiumLang.Basics.MortarAndPestle.PAGE1);
		
		addPatchouliBookEntry("basics.black_powder", OldGunsCompendiumLang.Basics.BlackPowder.ENTRY);
		addPatchouliBookPage("basics.black_powder0", OldGunsCompendiumLang.Basics.BlackPowder.PAGE0);
		addPatchouliBookPage("basics.black_powder1.heading", OldGunsCompendiumLang.Basics.BlackPowder.PAGE1_HEADING);
		addPatchouliBookPage("basics.black_powder1.heading2", OldGunsCompendiumLang.Basics.BlackPowder.PAGE1_HEADING2);
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
