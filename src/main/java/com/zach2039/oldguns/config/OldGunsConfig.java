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
	
	public static class Client {
		Client(final ForgeConfigSpec.Builder builder) {
			builder.comment("Client-only settings")
					.push("client");

			builder.pop();
		}
	}
	
	public static class Common {
		public final BooleanValue printDebugMessages;
		public final BooleanValue patchRecipeBook;
		
		Common(final ForgeConfigSpec.Builder builder) {
			builder.comment("Common config settings")
					.push("common");
			
			builder.comment(
					"IMPORTANT NOTICE:",
					"THIS IS ONLY THE COMMON CONFIG. It does not contain all the values adjustable for Old Guns.",
					"All modifiers for recipes, firearms, and other things reside in oldguns-server.toml.",
					"That file is PER WORLD, meaning you have to go into 'saves/<world name>/serverconfig' to adjust it. Those changes will then only apply for THAT WORLD.",
					"You can then take that config file and put it in the 'defaultconfigs' folder to make it apply automatically to all NEW worlds you generate FROM THERE ON.",
					"This is a sensible way to handle configuration, because the server configuration is synced when playing multiplayer."
			).define("importantInfo", true);

			printDebugMessages = builder
					.comment("Print debug messages to console")
					.define("printDebugMessages", false);
			
			patchRecipeBook = builder
					.comment("Patch vanilla recipe book to handle NBT items")
					.define("patchRecipeBook", true);
			
			builder.pop();
		}
	}
	
	public static class Server {
		public final RecipeSettings recipeSettings;
		public final LootSettings lootSettings;
		public final FirearmSettings firearmSettings;
		public final ArtillerySettings artillerySettings;
		
		Server(final ForgeConfigSpec.Builder builder) {
			builder.comment("Server config settings")
					.push("server");			

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
			
			artillerySettings = new ArtillerySettings(
					builder,
					"Attributes of artillery",
					"artillerySettings");
			
			builder.pop();
		}
	}
	
	
	
	public static class LootSettings {
		public final BooleanValue allowDesignNotesInLoot;
		public final BooleanValue allowMechanismsInLoot;
		public final BooleanValue allowFirearmsInLoot;
		
		LootSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			allowDesignNotesInLoot = builder
					.comment("Enable/disable design notes built-in loot tables")
					.define("allowDesignNotesInLoot", true);
			
			allowMechanismsInLoot = builder
					.comment("Enable/disable mechanisms built-in loot tables")
					.define("allowMechanismsInLoot", true);
			
			allowFirearmsInLoot = builder
					.comment("Enable/disable firearms built-in loot tables")
					.define("allowFirearmsInLoot", true);
			
			builder.pop();
		}
	}
	
	public static class RecipeSettings {
		public final FirearmRecipeSettings firearmRecipeSettings;
		public final ArtilleryRecipeSettings artilleryRecipeSettings;
		public final DesignNotesSettings designNotesSettings;
		public final BlackPowderManufactureSettings blackPowderManufactureSettings;
		
		RecipeSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			firearmRecipeSettings = new FirearmRecipeSettings(
					builder,
					"Firearm recipe settings",
					"firearmRecipeSettings");
			
			artilleryRecipeSettings = new ArtilleryRecipeSettings(
					builder,
					"Artillery recipe settings",
					"artilleryRecipeSettings");
			
			designNotesSettings = new DesignNotesSettings(
					builder,
					"Design notes settings",
					"designNotesSettings");
			
			blackPowderManufactureSettings = new BlackPowderManufactureSettings(
					builder,
					"Black Powder manufacture settings",
					"blackPowderManufactureSettings");
			
			builder.pop();
		}
	}
	
	public static class FirearmRecipeSettings {
		public final BooleanValue allowMatchlockWeaponsCrafting;
		public final BooleanValue allowWheellockWeaponsCrafting;
		public final BooleanValue allowFlintlockWeaponsCrafting;
		public final BooleanValue allowStoneFirearmAmmoCrafting;
		public final BooleanValue allowIronFirearmAmmoCrafting;
		public final BooleanValue allowLeadFirearmAmmoCrafting;
		
		FirearmRecipeSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			allowMatchlockWeaponsCrafting = builder
					.comment("Enable/disable matchlock firearm crafts")
					.define("allowMatchlockWeaponsCrafting", true);
			
			allowWheellockWeaponsCrafting = builder
					.comment("Enable/disable wheellock firearm crafts")
					.define("allowWheellockWeaponsCrafting", true);
			
			allowFlintlockWeaponsCrafting = builder
					.comment("Enable/disable flintlock firearm crafts")
					.define("allowFlintlockWeaponsCrafting", true);
			
			allowStoneFirearmAmmoCrafting = builder
					.comment("Enable/disable stone firearm ammo crafts")
					.define("allowStoneFirearmAmmoCrafting", true);
			
			allowIronFirearmAmmoCrafting = builder
					.comment("Enable/disable iron firearm ammo crafts")
					.define("allowIronFirearmAmmoCrafting", true);
			
			allowLeadFirearmAmmoCrafting = builder
					.comment("Enable/disable lead firearm ammo crafts")
					.define("allowLeadFirearmAmmoCrafting", true);
			
			builder.pop();
		}
	}
	
	public static class ArtilleryRecipeSettings {
		public final BooleanValue allowNavalCannonArtilleryCrafting;
		public final BooleanValue allowArtilleryPowderChargesCrafting;
		public final BooleanValue allowStoneArtilleryAmmoCrafting;
		public final BooleanValue allowIronArtilleryAmmoCrafting;
		public final BooleanValue allowLeadArtilleryAmmoCrafting;
		
		ArtilleryRecipeSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			allowNavalCannonArtilleryCrafting = builder
					.comment("Enable/disable naval cannon artillery craft")
					.define("allowNavalCannonCrafting", true);
			
			allowArtilleryPowderChargesCrafting = builder
					.comment("Enable/disable powder charge crafts")
					.define("allowArtilleryPowderChargeCrafting", true);
			
			allowStoneArtilleryAmmoCrafting = builder
					.comment("Enable/disable stone artillery ammo crafts")
					.define("allowStoneFirearmAmmoCrafting", true);
			
			allowIronArtilleryAmmoCrafting = builder
					.comment("Enable/disable iron artillery ammo crafts")
					.define("allowIronFirearmAmmoCrafting", true);
			
			allowLeadArtilleryAmmoCrafting = builder
					.comment("Enable/disable lead artillery ammo crafts")
					.define("allowLeadFirearmAmmoCrafting", true);
			
			builder.pop();
		}
	}
	
	public static class DesignNotesSettings {
		public final ConfigValue<List<String>> designNotesRequiredItems;
		
		DesignNotesSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			designNotesRequiredItems = builder
					.comment("A list of items that require design notes to be crafted in the gunsmith's bench")
					.comment("Design Notes can be found in dungeon chests, villager chests, and mob loot")
					.define("designNotesRequiredItems", Arrays.asList(new String[] {
							"oldguns:flintlock_mechanism",
							"oldguns:caplock_mechanism",
							"oldguns:matchlock_musketoon",
							"oldguns:matchlock_blunderbuss_pistol",
							"oldguns:wheellock_doublebarrel_pistol",
							"oldguns:wheellock_musketoon",
							"oldguns:wheellock_blunderbuss_pistol",
							"oldguns:flintlock_duckfoot_derringer",
							"oldguns:flintlock_pepperbox_pistol",
							"oldguns:flintlock_musketoon",
							"oldguns:flintlock_nock_gun",
							"oldguns:flintlock_blunderbuss_pistol"						
							}));
			
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
					.define("lowRefuseAnimals", Arrays.asList(new String[] { "entity.minecraft.chicken", "entity.minecraft.pig", "entity.minecraft.sheep", "entity.minecraft.llama" }));
			
			highRefuseAnimals = builder
					.comment("A list of animal entities that will produce a large amount of refuse for nitrated soil production")
					.define("highRefuseAnimals", Arrays.asList(new String[] { "entity.minecraft.horse", "entity.minecraft.cow" }));
			
			builder.pop();
		}
	}
	
	public static class FirearmSettings {
		public final BooleanValue hugeFirearmDebuffs;	
		public final FirearmAmmoSettings ammoSettings;
		public final MatchlockSettings matchlockSettings;
		public final WheellockSettings wheellockSettings;
		public final FlintlockSettings flintlockSettings;
		
		FirearmSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			hugeFirearmDebuffs = builder
					.comment("Apply slowness effect while wielding huge firearms, like the nock gun")
					.define("hugeFirearmDebuffs", true);
			
			ammoSettings = new FirearmAmmoSettings(
					builder,
					"Firearm ammo settings",
					"ammoSettings");
			
			matchlockSettings = new MatchlockSettings(
					builder,
					"Matchlock firearm settings",
					"matchlockSettings");
			
			wheellockSettings = new WheellockSettings(
					builder,
					"Wheellock firearm settings",
					"wheellockSettings");
			
			flintlockSettings = new FlintlockSettings(
					builder,
					"Flintlock firearm settings",
					"flintlockSettings");
			
			builder.pop();
		}
	}
	
	public static class FirearmAmmoSettings {	
		public final BooleanValue allowArmorBypass;	
		
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
		
		FirearmAmmoSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			allowArmorBypass = builder
					.comment("Allow firearm projectiles to bypass a percentage of armor as defined in their attributes")
					.define("allowArmorBypass", true);
			
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
					1.0f,
					0.10f
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
					1.0f,
					0.12f
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
					1.0f,
					0.14f
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
					3.0f,
					0.0f
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
					3.0f,
					0.0f
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
					3.0f,
					0.0f
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
					1.0f,
					0.16f
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
					1.0f,
					0.18f
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
					1.0f,
					0.20f
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
					1.5f,
					0.10f
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
					1.5f,
					0.10f
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
					1.5f,
					0.10f
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
					3.0f,
					0.0f
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
					3.0f,
					0.0f
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
					3.0f,
					0.0f
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
					1.0f,
					0.16f
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
					1.0f,
					0.18f
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
					1.0f,
					0.20f
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
					1.5f,
					0.10f
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
					1.5f,
					0.10f
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
					1.5f,
					0.10f
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
					3.0f,
					0.0f
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
					3.0f,
					0.0f
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
					3.0f,
					0.0f
					);
			
			builder.pop();
		}
	}

	public static class MatchlockSettings {		
		public final MuzzleloadingFirearmAttributes matchlock_derringer;
		public final MuzzleloadingFirearmAttributes matchlock_pistol;
		public final MuzzleloadingFirearmAttributes matchlock_arquebus;
		
		public final MuzzleloadingFirearmAttributes matchlock_caliver;
		public final MuzzleloadingFirearmAttributes matchlock_musketoon;
		
		public final MuzzleloadingFirearmAttributes matchlock_musket;
		public final MuzzleloadingFirearmAttributes matchlock_long_musket;	
		
		public final MuzzleloadingFirearmAttributes matchlock_blunderbuss_pistol;
		public final MuzzleloadingFirearmAttributes matchlock_blunderbuss;
		
		MatchlockSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			// Matchlock
			matchlock_derringer = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Matchlock Derringers",
					"matchlock_derringer",
					8,
					3.0f,
					0.5f,
					5.8f,
					1.0f
					);
			
			matchlock_pistol = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Matchlock Pistols",
					"matchlock_pistol",
					12,
					3.25f,
					1.0f,
					2.8f,
					1.0f
					);
			
			matchlock_arquebus = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Matchlock Arquebus",
					"matchlock_arquebus",
					16,
					3.5f,
					1.2f,
					2.6f,
					1.0f
					);
			
			matchlock_caliver = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Matchlock Calivers",
					"matchlock_caliver",
					20,
					3.75f,
					1.2f,
					2.4f,
					1.0f
					);
			
			matchlock_musketoon = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Matchlock Musketoons",
					"matchlock_musketoon",
					24,
					3.75f,
					0.8f,
					2.2f,
					1.0f
					);
			
			matchlock_musket = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Matchlock Muskets",
					"matchlock_musket",
					24,
					4.0f,
					1.0f,
					2.0f,
					1.0f
					);
			
			matchlock_long_musket = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Matchlock Long Muskets",
					"matchlock_long_musket",
					28,
					4.25f,
					1.2f,
					1.8f,
					1.0f
					);
			
			matchlock_blunderbuss_pistol = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Matchlock Blunderbuss Pistols",
					"matchlock_blunderbuss_pistol",
					8,
					2.75f,
					0.4f,
					4.1f,
					1.0f
					);
			
			matchlock_blunderbuss = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Matchlock Blunderbusses",
					"matchlock_blunderbuss",
					24,
					3.5f,
					1.0f,
					2.3f,
					1.0f
					);
			
			builder.pop();
		}
	}
	
	public static class WheellockSettings {		
		public final MuzzleloadingFirearmAttributes wheellock_derringer;
		public final MuzzleloadingFirearmAttributes wheellock_pistol;
		public final MuzzleloadingFirearmAttributes wheellock_doublebarrel_pistol;
		public final MuzzleloadingFirearmAttributes wheellock_arquebus;
		
		public final MuzzleloadingFirearmAttributes wheellock_caliver;
		public final MuzzleloadingFirearmAttributes wheellock_musketoon;
		
		public final MuzzleloadingFirearmAttributes wheellock_musket;
		public final MuzzleloadingFirearmAttributes wheellock_long_musket;	
		
		public final MuzzleloadingFirearmAttributes wheellock_blunderbuss_pistol;
		public final MuzzleloadingFirearmAttributes wheellock_blunderbuss;
		
		public final MuzzleloadingFirearmAttributes wheellock_hand_mortar;
		
		WheellockSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			// Wheellock
			wheellock_derringer = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Wheellock Derringers",
					"wheellock_derringer",
					16,
					3.125f,
					0.5f,
					5.5f,
					1.0f
					);
			
			wheellock_pistol = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Wheellock Pistols",
					"wheellock_pistol",
					20,
					3.375f,
					1.0f,
					2.7f,
					1.0f
					);
			
			wheellock_doublebarrel_pistol = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Wheellock Double Barrel Pistols",
					"wheellock_double_barrel_pistol",
					20,
					3.275f,
					0.8f,
					3.7f,
					0.8f
					);
			
			wheellock_arquebus = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Wheellock Arquebus",
					"wheellock_arquebus",
					24,
					3.625f,
					1.2f,
					2.4f,
					1.0f
					);
			
			wheellock_caliver = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Wheellock Calivers",
					"wheellock_caliver",
					28,
					3.875f,
					1.2f,
					2.1f,
					1.0f
					);
			
			wheellock_musketoon = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Wheellock Musketoons",
					"wheellock_musketoon",
					32,
					3.875f,
					0.8f,
					1.8f,
					1.0f
					);
			
			wheellock_musket = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Wheellock Muskets",
					"wheellock_musket",
					32,
					4.125f,
					1.0f,
					1.8f,
					1.0f
					);
			
			wheellock_long_musket = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Wheellock Long Muskets",
					"wheellock_long_musket",
					36,
					4.375f,
					1.2f,
					1.5f,
					1.0f
					);
			
			wheellock_blunderbuss_pistol = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Flintlock Blunderbuss Pistols",
					"flintlock_blunderbuss_pistol",
					28,
					3.875f,
					0.4f,
					4.2f,
					1.0f
					);
			
			wheellock_blunderbuss = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Wheellock Blunderbusses",
					"wheellock_blunderbuss",
					32,
					3.625f,
					1.0f,
					2.0f,
					1.0f
					);
			
			wheellock_hand_mortar = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Wheellock Hand Mortars",
					"wheellock_hand_mortar",
					18,
					2.5f,
					1.0f,
					7.0f,
					1.0f
					);
			
			builder.pop();
		}
	}
	
	public static class FlintlockSettings {		
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
		
		FlintlockSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
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
					1.4f,
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
	
	public static class BreechloadingFirearmAttributes {
		public final IntValue durability;
		public final IntValue reloadTicks;
		public final DoubleValue effectiveRangeModifier;
		public final DoubleValue shotDamageModifier;
		public final DoubleValue shotDeviationModifier;
		public final DoubleValue projectileSpeed;
		
		BreechloadingFirearmAttributes(final ForgeConfigSpec.Builder builder, final String comment, final String path, 
				final int defaultDurability, final int defaultReloadTicks, final float defaultProjectileSpeed, 
				final float defaultEffectiveRangeModifier, final float defaultShotDeviaitionModifier, final float defaultShotDamageModifier) {
			builder.comment(comment).push(path);
			
			durability = builder
					.comment("How many uses before breaking")
					.defineInRange("durability", defaultDurability, 1, Integer.MAX_VALUE);
			
			reloadTicks = builder
					.comment("How long it takes to fully reload via breechloading in ticks")
					.defineInRange("reloadTicks", defaultReloadTicks, 1, Integer.MAX_VALUE);
			
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
		public final DoubleValue projectileArmorBypassPercentage;
		
		FirearmAmmoAttributes(final ForgeConfigSpec.Builder builder, final String comment, final String path, 
				final int defaultMaxStackSize, final int defaultProjectileCount, final float defaultProjectileDamage,
				final float defaultProjectileSize, final float defaultProjectileEffectiveRange,
				final float defaultProjectileDeviationModifier, final float deafultProjectileArmorBypassPercentage) {
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

			projectileArmorBypassPercentage = builder
					.comment("How much damage done by each projectile bypasses armor")
					.defineInRange("projectileArmorBypassPercentage", deafultProjectileArmorBypassPercentage, 0.0f, 1.0f);
			
			builder.pop();
		}
	}
	
	public static class ArtillerySettings {
		public final ArtilleryAttributes naval_cannon;
		public final ArtilleryAmmoSettings artilleryAmmoSettings;
		
		ArtillerySettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			naval_cannon = new ArtilleryAttributes(
					builder,
					"Attributes of Naval Cannons",
					"naval_cannon",
					1.5f,
					0.8f,
					1.7f,
					0.8f
					);
			
			artilleryAmmoSettings = new ArtilleryAmmoSettings(
					builder,
					"Artillery ammo settings",
					"artilleryAmmoSettings");
			
			builder.pop();
		}
	}
	
	public static class ArtilleryAttributes {
		public final DoubleValue effectiveRangeModifier;
		public final DoubleValue damageModifier;
		public final DoubleValue projectileDeviation;
		public final DoubleValue projectileSpeed;
		
		ArtilleryAttributes(final ForgeConfigSpec.Builder builder, final String comment, final String path, 
				final float defaultProjectileSpeed, final float defaultEffectiveRangeModifier, 
				final float defaultShotDeviaitionModifier, final float defaultShotDamageModifier) {
			builder.comment(comment).push(path);
			
			effectiveRangeModifier = builder
					.comment("How the artillery modifies the base range of ammo shot")
					.defineInRange("effectiveRangeModifier", defaultEffectiveRangeModifier, 0.001f, Float.MAX_VALUE);
			
			damageModifier = builder
					.comment("How the artillery modifies the base damage of ammo shot")
					.defineInRange("damageModifier", defaultShotDamageModifier, 0.001f, Float.MAX_VALUE);
			
			projectileDeviation = builder
					.comment("How much projectiles shot from the artillery deviate")
					.defineInRange("projectileDeviation", defaultShotDeviaitionModifier, 0.001f, Float.MAX_VALUE);
			
			projectileSpeed = builder
					.comment("How fast projectiles shot from the artillery are")
					.defineInRange("projectileSpeed", defaultProjectileSpeed, 0.001f, Float.MAX_VALUE);			
			
			builder.pop();
		}
	}
	
	public static class ArtilleryAmmoSettings {		
		public final BooleanValue allowArmorBypass;
		public final ArtilleryAmmoAttributes small_iron_cannonball;
		public final ArtilleryAmmoAttributes medium_iron_cannonball;
		public final ArtilleryAmmoAttributes large_iron_cannonball;
		public final ArtilleryAmmoAttributes small_iron_grapeshot;
		public final ArtilleryAmmoAttributes medium_iron_grapeshot;
		public final ArtilleryAmmoAttributes large_iron_grapeshot;
		public final ArtilleryAmmoAttributes small_iron_canister_shot;
		public final ArtilleryAmmoAttributes medium_iron_canister_shot;
		public final ArtilleryAmmoAttributes large_iron_canister_shot;
		
		ArtilleryAmmoSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			allowArmorBypass = builder
					.comment("Allow artillery projectiles to bypass a percentage of armor as defined in their attributes")
					.define("allowArmorBypass", true);
			
			// Iron ammo
			small_iron_cannonball = new ArtilleryAmmoAttributes(
					builder,
					"Attributes of Small Iron Cannonball ammo",
					"small_iron_cannonball",
					1,
					1,
					30.0f,
					0.4f,
					100.0f,
					1.0f,
					0.7f,
					0,
					2f
					);
			
			medium_iron_cannonball = new ArtilleryAmmoAttributes(
					builder,
					"Attributes of Medium Iron Cannonball ammo",
					"medium_iron_cannonball",
					1,
					1,
					40.0f,
					0.6f,
					300.0f,
					1.0f,
					0.8f,
					0,
					3f
					);
			
			large_iron_cannonball = new ArtilleryAmmoAttributes(
					builder,
					"Attributes of Large Iron Cannonball ammo",
					"medium_iron_cannonball",
					1,
					1,
					50.0f,
					0.8f,
					500.0f,
					1.0f,
					0.9f,
					0,
					4f
					);
		
			small_iron_grapeshot = new ArtilleryAmmoAttributes(
					builder,
					"Attributes of Small Iron Grapeshot ammo",
					"small_iron_grapeshot",
					1,
					7,
					20.0f,
					0.2f,
					30.0f,
					6.0f,
					0.6f,
					0,
					0f
					);
			
			medium_iron_grapeshot = new ArtilleryAmmoAttributes(
					builder,
					"Attributes of Medium Iron Grapeshot ammo",
					"medium_iron_grapeshot",
					1,
					10,
					20.0f,
					0.2f,
					20.0f,
					6.0f,
					0.6f,
					0,
					0f
					);
			
			large_iron_grapeshot = new ArtilleryAmmoAttributes(
					builder,
					"Attributes of Large Iron Grapeshot ammo",
					"large_iron_grapeshot",
					1,
					13,
					20.0f,
					0.2f,
					20.0f,
					6.0f,
					0.6f,
					0,
					0f
					);
	
			small_iron_canister_shot = new ArtilleryAmmoAttributes(
					builder,
					"Attributes of Small Iron Canister Shot ammo",
					"small_iron_canister_shot",
					1,
					1,
					15.0f,
					0.4f,
					100.0f,
					1.0f,
					0.2f,
					20,
					10f
					);
			
			medium_iron_canister_shot = new ArtilleryAmmoAttributes(
					builder,
					"Attributes of Medium Iron Canister Shot ammo",
					"medium_iron_canister_shot",
					1,
					1,
					15.0f,
					0.6f,
					100.0f,
					1.0f,
					0.2f,
					20,
					15f
					);
			
			large_iron_canister_shot = new ArtilleryAmmoAttributes(
					builder,
					"Attributes of Large Iron Canister Shot ammo",
					"large_iron_canister_shot",
					1,
					1,
					15.0f,
					0.8f,
					100.0f,
					1.0f,
					0.2f,
					20,
					20f
					);
			
			builder.pop();
		}
	}
	
	public static class ArtilleryAmmoAttributes {
		public final IntValue maxStackSize;
		public final IntValue projectileCount;
		public final DoubleValue projectileDamage;
		public final DoubleValue projectileSize;		
		public final DoubleValue projectileEffectiveRange;
		public final DoubleValue projectileDeviationModifier;
		public final DoubleValue projectileArmorBypassPercentage;
		public final IntValue effectTicks;
		public final DoubleValue effectPotency;
		
		ArtilleryAmmoAttributes(final ForgeConfigSpec.Builder builder, final String comment, final String path, 
				final int defaultMaxStackSize, final int defaultProjectileCount, final float defaultProjectileDamage,
				final float defaultProjectileSize, final float defaultProjectileEffectiveRange, 
				final float defaultProjectileDeviationModifier, final float defaultProjectileArmorBypassPercentage, 
				final int defaultEffectTicks, final float defaultEffectPotency) {
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
			
			projectileArmorBypassPercentage = builder
					.comment("How much damage done by the projectile bypasses armor")
					.defineInRange("projectileArmorBypassPercentage", defaultProjectileDeviationModifier, 0.0f, 1.0f);		
			
			effectTicks = builder
					.comment("How long do any effects from this projectile last in ticks")
					.defineInRange("effectTicks", defaultEffectTicks, 1, Integer.MAX_VALUE);
			
			effectPotency = builder
					.comment("How potent are any effects from this projectile")
					.defineInRange("effectPotency", defaultEffectPotency, 0f, Float.MAX_VALUE);
			
			builder.pop();
		}
	}
	
	private static final ForgeConfigSpec serverSpec;
	public static final Server SERVER;

	static {
		final Pair<Server, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Server::new);
		serverSpec = specPair.getRight();
		SERVER = specPair.getLeft();
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
		context.registerConfig(ModConfig.Type.SERVER, serverSpec);
		context.registerConfig(ModConfig.Type.COMMON, commonSpec);
		context.registerConfig(ModConfig.Type.CLIENT, clientSpec);
	}
}
