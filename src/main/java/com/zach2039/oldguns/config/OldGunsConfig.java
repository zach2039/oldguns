package com.zach2039.oldguns.config;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class OldGunsConfig {
	
	public static class Common {
		public final BooleanValue patchRecipeBook;
		public final BooleanValue printDebugMessages;
		public final RecipeSettings recipeSettings;
		public final LootSettings lootSettings;
		public final FirearmSettings firearmSettings;
		
		Common(final ForgeConfigSpec.Builder builder) {
			builder.comment("Common config settings")
					.push("common");			

			printDebugMessages = builder
					.comment("Print debug messages to console")
					.define("printDebugMessages", false);
			
			patchRecipeBook = builder
					.comment("Patch vanilla recipe book to handle NBT items")
					.define("patchRecipeBook", true);
			
			recipeSettings = new RecipeSettings(
					builder,
					"Recipe enable and disable and other crafting settings",
					"recipeSettings");
			
			lootSettings = new LootSettings(
					builder,
					"Loot enable and disable settings",
					"lootSettings");
			
			firearmSettings = new FirearmSettings(
					builder,
					"Attributes of firearms",
					"firearmSettings");
			
			builder.pop();
		}
	}
	
	public static class Client {
		Client(final ForgeConfigSpec.Builder builder) {
			builder.comment("Client-only settings")
					.push("client");

			builder.pop();
		}
	}
	
	public static class LootSettings {
		public final BooleanValue allowExoticFirearmsInLoot;
		public final BooleanValue allowAmmoInLoot;
		
		LootSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			allowExoticFirearmsInLoot = builder
					.comment("Enable/disable exotic firearm spawning in dungeon loot")
					.define("allowExoticFirearmsInLoot", true);
			
			allowAmmoInLoot = builder
					.comment("Enable/disable firearm ammo spawning in dungeon loot")
					.define("allowAmmoInLoot", true);
			
			builder.pop();
		}
	}
	
	public static class RecipeSettings {
		public final BooleanValue allowExoticFirearmCrafting;
		public final BooleanValue allowMatchlockWeaponsCrafting;
		public final BooleanValue allowWheellockWeaponsCrafting;
		public final BooleanValue allowFlintlockWeaponsCrafting;
		public final BlackPowderManufactureSettings blackPowderManufactureSettings;
		
		RecipeSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			allowExoticFirearmCrafting = builder
					.comment("Enable/disable exotic firearm crafts which will force players to find and maintain exotic weapons via loot")
					.define("allowExoticFirearmCrafting", false);
			
			allowMatchlockWeaponsCrafting = builder
					.comment("Enable/disable matchlock firearm crafts")
					.define("allowMatchlockWeaponsCrafting", true);
			
			allowWheellockWeaponsCrafting = builder
					.comment("Enable/disable wheellock firearm crafts")
					.define("allowWheellockWeaponsCrafting", true);
			
			allowFlintlockWeaponsCrafting = builder
					.comment("Enable/disable flintlock firearm crafts")
					.define("allowFlintlockWeaponsCrafting", true);
			
			blackPowderManufactureSettings = new BlackPowderManufactureSettings(
					builder,
					"Black Powder manufacture settings",
					"blackPowderManufactureSettings");
			
			builder.pop();
		}
	}
	
	public static class BlackPowderManufactureSettings {
		public final NiterProductionSettings niterProductionSettings;
		public final CorningProcessSettings corningProcessSettings;
		
		BlackPowderManufactureSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			niterProductionSettings = new NiterProductionSettings(
					builder,
					"Nitre production settings",
					"niterProductionSettings");
			
			corningProcessSettings = new CorningProcessSettings(
					builder,
					"Corning process settings",
					"corningProcessSettings");
			
			builder.pop();
		}
	}
	
	public static class CorningProcessSettings {
		public final DoubleValue wetBlackPowderSunDryingDifficulty;
		public final DoubleValue blackPowderRainWettingDifficulty;
		
		CorningProcessSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			wetBlackPowderSunDryingDifficulty = builder
					.comment("How difficult it is for wet black powder cakes or blocks to dry in direct sunlight")
					.comment("The chance for drying is 1 in (difficulty+1)*2, calculated when the block randomly ticks")
					.defineInRange("wetBlackPowderCakeSunDryingDifficulty", 15f, 0f, Float.MAX_VALUE);
			
			blackPowderRainWettingDifficulty = builder
					.comment("How difficult it is for dry black powder blocks to get wet in rain")
					.comment("The chance for drying is 1 in (difficulty+1)*2, calculated when the block randomly ticks")
					.defineInRange("blackPowderRainWettingDifficulty", 5f, 0f, Float.MAX_VALUE);
			
			builder.pop();
		}
	}
	
	public static class NiterProductionSettings {
		public final IntValue niterCrystalizationAmountMin;
		public final IntValue niterCrystalizationAmountMax;
		public final DoubleValue niterCrystalizationDifficulty;
		public final IntValue niterBeddingAnimalRadius;
		public final IntValue niterBeddingHarvestAmount;
		public final DoubleValue niterBeddingDripstoneGenerationDifficulty;
		public final DoubleValue niterBeddingRefuseGenerationDifficulty;
		public final DoubleValue lowRefuseAnimalGeneratedAmount;
		public final DoubleValue highRefuseAnimalGeneratedAmount;
		public final ConfigValue<List<String>> lowRefuseAnimals;
		public final ConfigValue<List<String>> highRefuseAnimals;
		
		NiterProductionSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			niterCrystalizationAmountMin = builder
					.comment("The min amount of Niter gained from crystalization of liquid niter")
					.defineInRange("niterCrystalizationAmountMin", 8, 1, 64);
			
			niterCrystalizationAmountMax = builder
					.comment("The max amount of Niter gained from crystalization of liquid niter")
					.defineInRange("niterCrystalizationAmountMax", 16, 1, 64);
			
			niterCrystalizationDifficulty = builder
					.comment("How difficult it is for niter to crystalize from a boiling cauldron of liquid niter")
					.comment("The chance for crystalization is 1 in (difficulty+1)*2, calculated when the block randomly ticks")
					.defineInRange("niterCrystalizationDifficulty", 3f, 0f, Float.MAX_VALUE);
			
			niterBeddingDripstoneGenerationDifficulty = builder
					.comment("How difficult it is for niter bedding to advance through its 10 refuse levels when under dripping water dripstone")
					.comment("The chance for advancement is 1 in (difficulty)*2, calculated when the block randomly ticks")
					.defineInRange("niterBeddingDripstoneGenerationDifficulty", 15f, 0f, Float.MAX_VALUE);
			
			niterBeddingRefuseGenerationDifficulty = builder
					.comment("How difficult it is for niter bedding to advance through its 10 refuse levels when under animals")
					.comment("The chance for advancement is 1 in (difficulty/localRefuse+1)*2, calculated when the block randomly ticks")
					.defineInRange("niterBeddingRefuseGenerationDifficulty", 30f, 0f, Float.MAX_VALUE);
			
			niterBeddingAnimalRadius = builder
					.comment("The radius in which refuse-producting animals must be around the top of a niter bedding block for it to produce Nitrated Soil")
					.comment("Set -1 to disable nitrated soil generation from animals")
					.defineInRange("niterBeddingAnimalRadius", 3, -1, 32);
			
			niterBeddingHarvestAmount = builder
					.comment("The amount of nitrated soil gained from right-clicking a ripe Niter Bedding with a shovel")
					.defineInRange("niterBeddingAnimalRadius", 3, 1, 64);
			
			lowRefuseAnimalGeneratedAmount = builder
					.comment("How much refuse a low refuse animal can contribute to nitrated soil production")
					.defineInRange("lowRefuseAnimalGeneratedAmount", 1f, 0, Float.MAX_VALUE);
			
			highRefuseAnimalGeneratedAmount = builder
					.comment("How much refuse a high refuse animal can contribute to nitrated soil production")
					.defineInRange("highRefuseAnimalGeneratedAmount", 2f, 0, Float.MAX_VALUE);
			
			lowRefuseAnimals = builder
					.comment("A list of animal entities that will produce a modest amount of refuse for nitrated soil production")
					.define("lowRefuseAnimals", Arrays.asList(new String[] { "entity.minecraft.pig", "entity.minecraft.sheep", "entity.minecraft.llama" }));
			
			highRefuseAnimals = builder
					.comment("A list of animal entities that will produce a large amount of refuse for nitrated soil production")
					.define("highRefuseAnimals", Arrays.asList(new String[] { "entity.minecraft.horse", "entity.minecraft.cow" }));
			
			builder.pop();
		}
	}
	
	public static class FirearmSettings {
		public final BooleanValue hugeFirearmDebuffs;
		
		public final MuzzleloadingFirearmAttributes matchlock_derringer;
		public final MuzzleloadingFirearmAttributes matchlock_pistol;
		public final MuzzleloadingFirearmAttributes matchlock_arquebus;
		
		public final MuzzleloadingFirearmAttributes matchlock_caliver;
		public final MuzzleloadingFirearmAttributes matchlock_musketoon;
		
		public final MuzzleloadingFirearmAttributes matchlock_musket;
		public final MuzzleloadingFirearmAttributes matchlock_long_musket;	
		
		public final MuzzleloadingFirearmAttributes matchlock_blunderbuss_pistol;
		public final MuzzleloadingFirearmAttributes matchlock_blunderbuss;
		
		public final MuzzleloadingFirearmAttributes flintlock_derringer;
		public final MuzzleloadingFirearmAttributes flintlock_duckfoot_derringer;
		public final MuzzleloadingFirearmAttributes flintlock_pistol;
		public final MuzzleloadingFirearmAttributes flintlock_pepperbox_pistol;
		public final MuzzleloadingFirearmAttributes flintlock_arquebus;
		
		public final MuzzleloadingFirearmAttributes flintlock_caliver;
		public final MuzzleloadingFirearmAttributes flintlock_musketoon;
		
		public final MuzzleloadingFirearmAttributes flintlock_musket;
		public final MuzzleloadingFirearmAttributes flintlock_nock_gun;
		public final MuzzleloadingFirearmAttributes flintlock_long_musket;		
		
		public final MuzzleloadingFirearmAttributes flintlock_blunderbuss_pistol;
		public final MuzzleloadingFirearmAttributes flintlock_blunderbuss;
		public final MuzzleloadingFirearmAttributes flintlock_doublebarrel_blunderbuss;	
		
		public final FirearmAmmoAttributes small_stone_musket_ball;
		public final FirearmAmmoAttributes medium_stone_musket_ball;
		public final FirearmAmmoAttributes large_stone_musket_ball;
		
		public final FirearmAmmoAttributes small_stone_birdshot;
		public final FirearmAmmoAttributes medium_stone_birdshot;
		public final FirearmAmmoAttributes large_stone_birdshot;
		
		public final FirearmAmmoAttributes small_iron_musket_ball;
		public final FirearmAmmoAttributes medium_iron_musket_ball;
		public final FirearmAmmoAttributes large_iron_musket_ball;
		
		public final FirearmAmmoAttributes small_iron_buckshot;
		public final FirearmAmmoAttributes medium_iron_buckshot;
		public final FirearmAmmoAttributes large_iron_buckshot;
		
		public final FirearmAmmoAttributes small_iron_birdshot;
		public final FirearmAmmoAttributes medium_iron_birdshot;
		public final FirearmAmmoAttributes large_iron_birdshot;
		
		public final FirearmAmmoAttributes small_lead_musket_ball;
		public final FirearmAmmoAttributes medium_lead_musket_ball;
		public final FirearmAmmoAttributes large_lead_musket_ball;
		
		public final FirearmAmmoAttributes small_lead_buckshot;
		public final FirearmAmmoAttributes medium_lead_buckshot;
		public final FirearmAmmoAttributes large_lead_buckshot;
		
		public final FirearmAmmoAttributes small_lead_birdshot;
		public final FirearmAmmoAttributes medium_lead_birdshot;
		public final FirearmAmmoAttributes large_lead_birdshot;
		
		FirearmSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			hugeFirearmDebuffs = builder
					.comment("Apply slowness effect while wielding huge firearms, like the nock gun")
					.define("hugeFirearmDebuffs", true);
			
			// Matchlock
			matchlock_derringer = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Matchlock Derringers",
					"matchlock_derringer",
					8,
					3.0f,
					0.5f,
					5.0f,
					1.0f
					);
			
			matchlock_pistol = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Matchlock Pistols",
					"matchlock_pistol",
					12,
					3.25f,
					1.0f,
					2.0f,
					1.0f
					);
			
			matchlock_arquebus = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Matchlock Arquebus",
					"matchlock_arquebus",
					16,
					3.5f,
					1.2f,
					1.8f,
					1.0f
					);
			
			matchlock_caliver = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Matchlock Calivers",
					"matchlock_caliver",
					20,
					3.75f,
					1.2f,
					1.6f,
					1.0f
					);
			
			matchlock_musketoon = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Matchlock Musketoons",
					"matchlock_musketoon",
					24,
					3.75f,
					0.8f,
					1.5f,
					1.0f
					);
			
			matchlock_musket = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Matchlock Muskets",
					"matchlock_musket",
					24,
					4.0f,
					1.0f,
					1.2f,
					1.0f
					);
			
			matchlock_long_musket = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Matchlock Long Muskets",
					"matchlock_long_musket",
					28,
					4.25f,
					1.2f,
					1.2f,
					1.0f
					);
			
			matchlock_blunderbuss_pistol = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Matchlock Blunderbuss Pistols",
					"matchlock_blunderbuss_pistol",
					8,
					2.75f,
					0.4f,
					3.5f,
					1.0f
					);
			
			matchlock_blunderbuss = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Matchlock Blunderbusses",
					"matchlock_blunderbuss",
					24,
					3.5f,
					1.0f,
					1.7f,
					1.0f
					);
			
			// Flintlock
			flintlock_derringer = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Flintlock Derringers",
					"flintlock_derringer",
					28,
					3.25f,
					0.5f,
					5.0f,
					1.0f
					);
			
			flintlock_duckfoot_derringer = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Flintlock Duckfoot Derringers",
					"flintlock_duckfoot_derringers",
					24,
					3.0f,
					0.3f,
					10.0f,
					0.8f
					);
			
			flintlock_pistol = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Flintlock Pistols",
					"flintlock_pistol",
					32,
					3.5f,
					1.0f,
					2.0f,
					1.0f
					);
			
			flintlock_pepperbox_pistol = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Flintlock Pepperbox Pistols",
					"flintlock_pepperbox_pistol",
					28,
					3.4f,
					0.8f,
					3.0f,
					0.8f
					);
			
			flintlock_arquebus = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Flintlock Arquebus",
					"flintlock_arquebus",
					36,
					3.75f,
					1.2f,
					1.8f,
					1.0f
					);
			
			flintlock_caliver = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Flintlock Calivers",
					"flintlock_caliver",
					40,
					4.0f,
					1.2f,
					1.6f,
					1.0f
					);
			
			flintlock_musketoon = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Flintlock Musketoons",
					"flintlock_musketoon",
					44,
					4.0f,
					0.8f,
					1.5f,
					1.0f
					);
			
			flintlock_musket = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Flintlock Muskets",
					"flintlock_musket",
					44,
					4.25f,
					1.0f,
					1.2f,
					1.0f
					);
			
			flintlock_nock_gun = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Flintlock Nock Guns",
					"flintlock_nock_gun",
					40,
					4.0f,
					0.8f,
					1.8f,
					0.8f
					);
			
			flintlock_long_musket = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Flintlock Long Muskets",
					"flintlock_long_musket",
					48,
					4.5f,
					1.2f,
					1.2f,
					1.0f
					);
			
			flintlock_blunderbuss_pistol = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Flintlock Blunderbuss Pistols",
					"flintlock_blunderbuss_pistol",
					28,
					3.0f,
					0.4f,
					3.5f,
					1.0f
					);
			
			flintlock_blunderbuss = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Flintlock Blunderbusses",
					"flintlock_blunderbuss",
					44,
					3.5f,
					1.0f,
					1.7f,
					1.0f
					);
			
			flintlock_doublebarrel_blunderbuss = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Flintlock Doublebarrel Blunderbusses",
					"flintlock_doublebarrel_blunderbuss",
					40,
					3.5f,
					0.8f,
					1.7f,
					0.8f
					);
			
			// Stone ammo
			small_stone_musket_ball = new FirearmAmmoAttributes(
					builder,
					"Attributes of Small Stone Musket Ball Ammo",
					"small_stone_musket_ball",
					8,
					1,
					10.0f,
					0.3f,
					15.0f,
					1.0f
					);
			
			medium_stone_musket_ball = new FirearmAmmoAttributes(
					builder,
					"Attributes of Medium Stone Musket Ball Ammo",
					"medium_stone_musket_ball",
					6,
					1,
					13.0f,
					0.4f,
					30.0f,
					1.0f
					);
			
			large_stone_musket_ball = new FirearmAmmoAttributes(
					builder,
					"Attributes of Large Stone Musket Ball Ammo",
					"large_stone_musket_ball",
					4,
					1,
					16.0f,
					0.5f,
					55.0f,
					1.0f
					);
			
			small_stone_birdshot = new FirearmAmmoAttributes(
					builder,
					"Attributes of Small Stone Birdshot Ammo",
					"small_stone_birdshot",
					8,
					6,
					2.0f,
					0.1f,
					5.0f,
					3.0f
					);
			
			medium_stone_birdshot = new FirearmAmmoAttributes(
					builder,
					"Attributes of Medium Stone Birdshot Ammo",
					"medium_stone_birdshot",
					6,
					8,
					2.0f,
					0.1f,
					5.0f,
					3.0f
					);
			
			large_stone_birdshot = new FirearmAmmoAttributes(
					builder,
					"Attributes of Large Stone Birdshot Ammo",
					"large_stone_birdshot",
					4,
					10,
					2.0f,
					0.1f,
					5.0f,
					3.0f
					);
			
			// Iron ammo
			small_iron_musket_ball = new FirearmAmmoAttributes(
					builder,
					"Attributes of Small Iron Musket Ball Ammo",
					"small_iron_musket_ball",
					8,
					1,
					20.0f,
					0.3f,
					25.0f,
					1.0f
					);
			
			medium_iron_musket_ball = new FirearmAmmoAttributes(
					builder,
					"Attributes of Medium Iron Musket Ball Ammo",
					"medium_iron_musket_ball",
					6,
					1,
					23.0f,
					0.4f,
					50.0f,
					1.0f
					);
			
			large_iron_musket_ball = new FirearmAmmoAttributes(
					builder,
					"Attributes of Large Iron Musket Ball Ammo",
					"large_iron_musket_ball",
					4,
					1,
					26.0f,
					0.5f,
					75.0f,
					1.0f
					);
			
			small_iron_buckshot = new FirearmAmmoAttributes(
					builder,
					"Attributes of Small Iron Buckshot Ammo",
					"small_iron_buckshot",
					8,
					3,
					15.0f,
					0.3f,
					30.0f,
					1.5f
					);
			
			medium_iron_buckshot = new FirearmAmmoAttributes(
					builder,
					"Attributes of Medium Iron Buckshot Ammo",
					"medium_iron_buckshot",
					6,
					5,
					15.0f,
					0.3f,
					30.0f,
					1.5f
					);
			
			large_iron_buckshot = new FirearmAmmoAttributes(
					builder,
					"Attributes of Large Iron Buckshot Ammo",
					"large_iron_buckshot",
					4,
					7,
					15.0f,
					0.3f,
					30.0f,
					1.5f
					);
			
			small_iron_birdshot = new FirearmAmmoAttributes(
					builder,
					"Attributes of Small Iron Birdshot Ammo",
					"small_iron_birdshot",
					8,
					6,
					3.0f,
					0.2f,
					7.0f,
					3.0f
					);
			
			medium_iron_birdshot = new FirearmAmmoAttributes(
					builder,
					"Attributes of Medium Iron Birdshot Ammo",
					"medium_iron_birdshot",
					6,
					8,
					3.0f,
					0.2f,
					7.0f,
					3.0f
					);
			
			large_iron_birdshot = new FirearmAmmoAttributes(
					builder,
					"Attributes of Large Iron Birdshot Ammo",
					"large_iron_birdshot",
					4,
					10,
					3.0f,
					0.2f,
					7.0f,
					3.0f
					);
			
			small_lead_musket_ball = new FirearmAmmoAttributes(
					builder,
					"Attributes of Small Lead Musket Ball Ammo",
					"small_lead_musket_ball",
					8,
					1,
					20.0f,
					0.3f,
					25.0f,
					1.0f
					);
			
			medium_lead_musket_ball = new FirearmAmmoAttributes(
					builder,
					"Attributes of Medium Lead Musket Ball Ammo",
					"medium_lead_musket_ball",
					6,
					1,
					23.0f,
					0.4f,
					50.0f,
					1.0f
					);
			
			large_lead_musket_ball = new FirearmAmmoAttributes(
					builder,
					"Attributes of Large Lead Musket Ball Ammo",
					"large_lead_musket_ball",
					4,
					1,
					26.0f,
					0.5f,
					75.0f,
					1.0f
					);
			
			small_lead_buckshot = new FirearmAmmoAttributes(
					builder,
					"Attributes of Small Lead Buckshot Ammo",
					"small_lead_buckshot",
					8,
					3,
					15.0f,
					0.3f,
					30.0f,
					1.5f
					);
			
			medium_lead_buckshot = new FirearmAmmoAttributes(
					builder,
					"Attributes of Medium Lead Buckshot Ammo",
					"medium_lead_buckshot",
					6,
					5,
					15.0f,
					0.3f,
					30.0f,
					1.5f
					);
			
			large_lead_buckshot = new FirearmAmmoAttributes(
					builder,
					"Attributes of Large Lead Buckshot Ammo",
					"large_lead_buckshot",
					4,
					7,
					15.0f,
					0.3f,
					30.0f,
					1.5f
					);
			
			small_lead_birdshot = new FirearmAmmoAttributes(
					builder,
					"Attributes of Small Lead Birdshot Ammo",
					"small_lead_birdshot",
					8,
					6,
					3.0f,
					0.2f,
					7.0f,
					3.0f
					);
			
			medium_lead_birdshot = new FirearmAmmoAttributes(
					builder,
					"Attributes of Medium Lead Birdshot Ammo",
					"medium_lead_birdshot",
					6,
					8,
					3.0f,
					0.2f,
					7.0f,
					3.0f
					);
			
			large_lead_birdshot = new FirearmAmmoAttributes(
					builder,
					"Attributes of Large Lead Birdshot Ammo",
					"large_lead_birdshot",
					4,
					10,
					3.0f,
					0.2f,
					7.0f,
					3.0f
					);
			
			builder.pop();
		}
	}

	public static class MuzzleloadingFirearmAttributes {
		public final IntValue durability;
		public final DoubleValue effectiveRangeModifier;
		public final DoubleValue shotDamageModifier;
		public final DoubleValue shotDeviationModifier;
		public final DoubleValue projectileSpeed;
		
		MuzzleloadingFirearmAttributes(final ForgeConfigSpec.Builder builder, final String comment, final String path, 
				final int defaultDurability, final float defaultProjectileSpeed, final float defaultEffectiveRangeModifier, 
				final float defaultShotDeviaitionModifier, final float defaultShotDamageModifier) {
			builder.comment(comment).push(path);
			
			durability = builder
					.comment("How many uses before breaking")
					.defineInRange("durability", defaultDurability, 1, Integer.MAX_VALUE);
			
			effectiveRangeModifier = builder
					.comment("How the firearm modifies the base range of ammo shot")
					.defineInRange("effectiveRangeModifier", defaultEffectiveRangeModifier, 0.001f, Float.MAX_VALUE);
			
			shotDamageModifier = builder
					.comment("How the firearm modifies the base damage of ammo shot")
					.defineInRange("shotDamageModifier", defaultShotDamageModifier, 0.001f, Float.MAX_VALUE);
			
			shotDeviationModifier = builder
					.comment("How the firearm modifies the base deviation of ammo shot")
					.defineInRange("shotDeviaitionModifier", defaultShotDeviaitionModifier, 0.001f, Float.MAX_VALUE);
			
			projectileSpeed = builder
					.comment("How fast projectiles shot from the firearm are")
					.defineInRange("projectileSpeed", defaultProjectileSpeed, 0.001f, Float.MAX_VALUE);			
			
			builder.pop();
		}
	}
	
	public static class FirearmAmmoAttributes {
		public final IntValue maxStackSize;
		public final IntValue projectileCount;
		public final DoubleValue projectileDamage;
		public final DoubleValue projectileSize;		
		public final DoubleValue projectileEffectiveRange;
		public final DoubleValue projectileDeviationModifier;
		
		FirearmAmmoAttributes(final ForgeConfigSpec.Builder builder, final String comment, final String path, 
				final int defaultMaxStackSize, final int defaultProjectileCount, final float defaultProjectileDamage, final float defaultProjectileSize, final float defaultProjectileEffectiveRange,
				final float defaultProjectileDeviationModifier) {
			builder.comment(comment).push(path);
			
			maxStackSize = builder
					.comment("How much ammo can be stored per stack")
					.defineInRange("maxStackSize", defaultMaxStackSize, 1, Integer.MAX_VALUE);
			
			projectileCount = builder
					.comment("How many projectiles launched")
					.defineInRange("projectileCount", defaultProjectileCount, 1, Integer.MAX_VALUE);
			
			projectileDamage = builder
					.comment("How much damage each projectile does")
					.defineInRange("projectileDamage", defaultProjectileDamage, 0.001f, Float.MAX_VALUE);
			
			projectileSize = builder
					.comment("How large the each projectile is")
					.defineInRange("projectileSize", defaultProjectileSize, 0.001f, Float.MAX_VALUE);
			
			projectileEffectiveRange = builder
					.comment("How far each projectile can travel before applying damage falloff")
					.defineInRange("projectileEffectiveRange", defaultProjectileEffectiveRange, 0.001f, Float.MAX_VALUE);
			
			projectileDeviationModifier = builder
					.comment("How the ammo modifies the base deviation of the firearm")
					.defineInRange("projectileDeviationModifier", defaultProjectileDeviationModifier, 0.001f, Float.MAX_VALUE);			
			
			builder.pop();
		}
	}
	
	private static final ForgeConfigSpec commonSpec;
	public static final Common COMMON;

	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		commonSpec = specPair.getRight();
		COMMON = specPair.getLeft();
	}


	private static final ForgeConfigSpec clientSpec;
	public static final Client CLIENT;

	static {
		final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
		clientSpec = specPair.getRight();
		CLIENT = specPair.getLeft();
	}
	
	public static void register(final ModLoadingContext context) {
		context.registerConfig(ModConfig.Type.COMMON, commonSpec);
		context.registerConfig(ModConfig.Type.CLIENT, clientSpec);
	}
}
