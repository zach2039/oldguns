package com.zach2039.oldguns.data;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Supplier;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.init.ModEntities;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.text.OldGunsLang;

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
		return "TestMo3 " + super.getName();
	}

	private void addBlocks() {
		
	}

	private void addItems() {
		addItem(ModItems.FLINTLOCK_PISTOL, "Flintlock Pistol");
		
		addItem(ModItems.SMALL_IRON_MUSKET_BALL, "Small Iron Musket Ball");
	}

	private void addFluids() {
		
	}

	private void addEntities() {
		addEntityType(ModEntities.BULLET_PROJECTILE, "Projectile");
	}

	private void addPotions() {
		
	}

	private void addContainers() {
		
	}

	private void addCommands() {
		
	}

	private void addCapabilities() {
		
	}

	private void addChatMessages() {
		
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
