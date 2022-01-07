package com.zach2039.oldguns.data;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Supplier;
import com.zach2039.oldguns.OldGuns;
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
 * Generates this mod's en-US language file.
 *
 * @author Choonster
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
		addMisc();
	}

	@Override
	public String getName() {
		return "OldGuns " + super.getName();
	}

	private void addBlocks() {
		addBlock(ModBlocks.GUNSMITHS_BENCH, "Gunsmith's Bench");
	}

	private void addItems() {
		addItem(ModItems.FLINTLOCK_PISTOL, "Flintlock Pistol");
		
		addItem(ModItems.SMALL_IRON_MUSKET_BALL, "Small Iron Musket Ball");
		
		addItem(ModItems.FLINTLOCK_MECHANISM, "Flintlock Mechanism");
		
		addItem(ModItems.SMALL_WOODEN_HANDLE, "Small Wooden Handle");
		addItem(ModItems.MEDIUM_WOODEN_HANDLE, "Medium Wooden Handle");
		addItem(ModItems.LARGE_WOODEN_HANDLE, "Large Wooden Handle");
		
		addItem(ModItems.SMALL_WOODEN_STOCK, "Small Wooden Stock");
		addItem(ModItems.MEDIUM_WOODEN_STOCK, "Medium Wooden Stock");
		addItem(ModItems.LARGE_WOODEN_STOCK, "Large Wooden Stock");
		
		addItem(ModItems.SMALL_IRON_BARREL, "Small Iron Barrel");
		addItem(ModItems.MEDIUM_IRON_BARREL, "Medium Iron Barrel");
		addItem(ModItems.LARGE_IRON_BARREL, "Large Iron Barrel");
		
		addItem(ModItems.LEAD_BITS, "Lead Bits");
		addItem(ModItems.IRON_BITS, "Iron Bits");
		
		addItem(ModItems.REPAIR_KIT, "Firearm Repair Kit");
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
