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
		public final MobSettings mobSettings;
		public final EquipmentSettings equipmentSettings;
		
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
					"Attributes of firearms and firearm ammo",
					"firearmSettings");
			
			artillerySettings = new ArtillerySettings(
					builder,
					"Attributes of artillery and artillery ammo",
					"artillerySettings");
			
			mobSettings = new MobSettings(
					builder,
					"Settings for mob spawns",
					"mobSettings");
			
			equipmentSettings = new EquipmentSettings(
					builder,
					"Equipment effects and other settings",
					"equipmentSettings");
			
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
		public final WorldInteractionSettings worldInteractionSettings;
		public final FirearmRecipeSettings firearmRecipeSettings;
		public final ArtilleryRecipeSettings artilleryRecipeSettings;
		public final DesignNotesSettings designNotesSettings;
		public final BlackPowderManufactureSettings blackPowderManufactureSettings;
		public final EquipmentRecipeSettings equipmentRecipeSettings;
		public final VanillaAlternativeRecipeSettings vanillaAlternativeRecipeSettings;
		public final MiscRecipeSettings miscRecipeSettings;
		
		RecipeSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			worldInteractionSettings = new WorldInteractionSettings(
					builder,
					"World interaction and recipe settings",
					"worldInteractionSettings");
			
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
			
			equipmentRecipeSettings = new EquipmentRecipeSettings(
					builder,
					"Equipment recipe settings",
					"equipmentRecipeSettings");
			
			vanillaAlternativeRecipeSettings = new VanillaAlternativeRecipeSettings(
					builder,
					"Vanilla alternative recipe settings",
					"vanillaAlternativeRecipeSettings");
			
			miscRecipeSettings = new MiscRecipeSettings(
					builder,
					"Miscellaneous recipe settings",
					"miscRecipeSettings");
			
			builder.pop();
		}
	}
	
	public static class MiscRecipeSettings {
		public final BooleanValue allowMatchCordFromBarkStrandsCrafting;
		public final BooleanValue allowMatchCordFromStringAtNiterCauldronCrafting;
		
		MiscRecipeSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			allowMatchCordFromBarkStrandsCrafting = builder
					.comment("Enable/disable crafting match cords from bark strands")
					.define("allowMatchCordFromBarkStrandsCrafting", true);
			
			allowMatchCordFromStringAtNiterCauldronCrafting = builder
					.comment("Enable/disable crafting match cords from string via right-clicking a liquid niter cauldron")
					.define("allowMatchCordFromStringAtNiterCauldronCrafting", true);
			
			builder.pop();
		}
	}
	
	public static class VanillaAlternativeRecipeSettings {
		public final BooleanValue allowTntFromBlastingPowderSticksCrafting;
		
		VanillaAlternativeRecipeSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			allowTntFromBlastingPowderSticksCrafting = builder
					.comment("Enable/disable crafting vanilla TNT from blasting powder sticks")
					.define("allowTntFromBlastingPowderSticksCrafting", true);
			
			builder.pop();
		}
	}
			
	public static class EquipmentRecipeSettings {
		public final BooleanValue allowBlastingPowderStickCrafting;
		
		EquipmentRecipeSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			allowBlastingPowderStickCrafting = builder
					.comment("Enable/disable crafting blasting powder sticks")
					.define("allowBlastingPowderStickCrafting", true);
			
			builder.pop();
		}
	}
	
	public static class WorldInteractionSettings {
		public final BooleanValue allowShearsToStripBark;
		public final IntValue barkStrandHarvestAmount;
		
		WorldInteractionSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			allowShearsToStripBark = builder
					.comment("Enable/disable shears stripping bark from trees")
					.define("allowShearsToStripBark", true);
			
			barkStrandHarvestAmount = builder
					.comment("The amount of bark strands gained from right-clicking an unstripped log with shears")
					.defineInRange("barkStrandHarvestAmount", 3, 1, 64);
			
			builder.pop();
		}
	}
	
	public static class FirearmRecipeSettings {
		public final BooleanValue allowMatchlockWeaponsCrafting;
		public final BooleanValue allowWheellockWeaponsCrafting;
		public final BooleanValue allowFlintlockWeaponsCrafting;
		public final BooleanValue allowCaplockWeaponsCrafting;
		public final BooleanValue allowPaperCartridgeCrafting;
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
			
			allowCaplockWeaponsCrafting = builder
					.comment("Enable/disable caplock firearm crafts")
					.define("allowCaplockWeaponsCrafting", true);
			
			allowPaperCartridgeCrafting = builder
					.comment("Enable/disable paper cartridge ammo crafts")
					.define("allowPaperCartridgeCrafting", true);
			
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
		public final BooleanValue allowCongreveRocketStandArtilleryCrafting;
		public final BooleanValue allowNavalCannonArtilleryCrafting;
		public final BooleanValue allowArtilleryPowderChargesCrafting;
		public final BooleanValue allowStoneArtilleryAmmoCrafting;
		public final BooleanValue allowIronArtilleryAmmoCrafting;
		public final BooleanValue allowLeadArtilleryAmmoCrafting;
		
		ArtilleryRecipeSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			allowCongreveRocketStandArtilleryCrafting = builder
					.comment("Enable/disable congreve rocket stand artillery craft")
					.define("allowCongreveRocketStandArtilleryCrafting", true);
			
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
		public final ConfigValue<List<? extends String>> designNotesRequiredItems;
		
		DesignNotesSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			designNotesRequiredItems = builder
					.comment("A list of items that require design notes to be crafted in the gunsmith's bench")
					.comment("Design Notes can be found in dungeon chests, villager chests, and mob loot")
					.defineList("designNotesRequiredItems", Arrays.asList(
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
							"oldguns:flintlock_blunderbuss_pistol",
							"oldguns:caplock_duckfoot_derringer",
							"oldguns:caplock_pepperbox_pistol",
							"oldguns:caplock_musketoon",
							"oldguns:caplock_blunderbuss_pistol"
							), s -> s instanceof String);
			
			builder.pop();
		}
	}
	
	public static class BlackPowderManufactureSettings {
		public final NiterProductionSettings niterProductionSettings;
		public final CorningProcessSettings corningProcessSettings;
		public final MercuryProductionSettings mercuryProductionSettings;
		
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
			
			mercuryProductionSettings = new MercuryProductionSettings(
					builder,
					"Mercury process settings",
					"mercuryProductionSettings");
			
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
		public final IntValue niterCrystallizationAmountMin;
		public final IntValue niterCrystallizationAmountMax;
		public final DoubleValue niterCrystallizationDifficulty;
		public final ConfigValue<List<? extends String>> niterCauldronValidHeatSources;
		public final IntValue niterBeddingAnimalRadius;
		public final IntValue niterBeddingHarvestAmount;
		public final DoubleValue niterBeddingDripstoneGenerationDifficulty;
		public final DoubleValue niterBeddingRefuseGenerationDifficulty;
		public final DoubleValue lowRefuseAnimalGeneratedAmount;
		public final DoubleValue highRefuseAnimalGeneratedAmount;
		public final ConfigValue<List<? extends String>> lowRefuseAnimals;
		public final ConfigValue<List<? extends String>> highRefuseAnimals;
		
		NiterProductionSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			niterCrystallizationAmountMin = builder
					.comment("The min amount of Niter gained from crystallization of liquid niter")
					.defineInRange("niterCrystallizationAmountMin", 8, 1, 64);
			
			niterCrystallizationAmountMax = builder
					.comment("The max amount of Niter gained from crystallization of liquid niter")
					.defineInRange("niterCrystallizationAmountMax", 16, 1, 64);
			
			niterCrystallizationDifficulty = builder
					.comment("How difficult it is for niter to crystallize from a boiling cauldron of liquid niter")
					.comment("The chance for crystalization is 1 in (difficulty+1)*2, calculated when the block randomly ticks")
					.defineInRange("niterCrystallizationDifficulty", 3f, 0f, Float.MAX_VALUE);
			
			niterCauldronValidHeatSources = builder
					.comment("A list of blocks that can be used as heat sources for niter crystallization")
					.defineList("niterCauldronValidHeatSources", Arrays.asList("minecraft:fire", "minecraft:lava", "minecraft:campfire", "minecraft:soul_campfire"), s -> s instanceof String);
			
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
					.defineList("lowRefuseAnimals", Arrays.asList("entity.minecraft.chicken", "entity.minecraft.pig", "entity.minecraft.sheep", "entity.minecraft.llama"), s -> s instanceof String);
			
			highRefuseAnimals = builder
					.comment("A list of animal entities that will produce a large amount of refuse for nitrated soil production")
					.defineList("highRefuseAnimals", Arrays.asList("entity.minecraft.horse", "entity.minecraft.cow"), s -> s instanceof String);
			
			builder.pop();
		}
	}
	
	public static class MercuryProductionSettings {
		public final BooleanValue allowMercuryFromGoldAtNiterCauldronCrafting;
		public final BooleanValue allowMercuryFromRedstoneAtNiterCauldronCrafting;
		
		MercuryProductionSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			allowMercuryFromGoldAtNiterCauldronCrafting = builder
					.comment("Enable/disable creating mercury from gold ore via a liquid niter cauldron")
					.define("allowMercuryFromGoldAtNiterCauldronCrafting", true);
			
			allowMercuryFromRedstoneAtNiterCauldronCrafting = builder
					.comment("Enable/disable creating mercury from redstone dust via a liquid niter cauldron")
					.define("allowMercuryFromRedstoneAtNiterCauldronCrafting", true);

			builder.pop();
		}
	}
	
	public static class FirearmSettings {
		public final BooleanValue hugeFirearmDebuffs;	
		public final FirearmAmmoSettings ammoSettings;
		public final MatchlockSettings matchlockSettings;
		public final WheellockSettings wheellockSettings;
		public final FlintlockSettings flintlockSettings;
		public final CaplockSettings caplockSettings;
		
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
			
			caplockSettings = new CaplockSettings(
					builder,
					"Caplock firearm settings",
					"caplockSettings");
			
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
		public final FirearmAttributes matchlock_derringer;
		public final FirearmAttributes matchlock_pistol;
		public final FirearmAttributes matchlock_arquebus;
		
		public final FirearmAttributes matchlock_caliver;
		public final FirearmAttributes matchlock_musketoon;
		
		public final FirearmAttributes matchlock_musket;
		public final FirearmAttributes matchlock_long_musket;
		
		public final FirearmAttributes matchlock_blunderbuss_pistol;
		public final FirearmAttributes matchlock_blunderbuss;
		
		MatchlockSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			// Matchlock
			matchlock_derringer = new FirearmAttributes(
					builder,
					"Attributes of Matchlock Derringers",
					"matchlock_derringer",
					8,
					3.0f,
					0.5f,
					5.8f,
					1.0f,
					80
					);
			
			matchlock_pistol = new FirearmAttributes(
					builder,
					"Attributes of Matchlock Pistols",
					"matchlock_pistol",
					12,
					3.25f,
					1.0f,
					2.8f,
					1.0f,
					80
					);
			
			matchlock_arquebus = new FirearmAttributes(
					builder,
					"Attributes of Matchlock Arquebus",
					"matchlock_arquebus",
					16,
					3.5f,
					1.2f,
					2.6f,
					1.0f,
					80
					);
			
			matchlock_caliver = new FirearmAttributes(
					builder,
					"Attributes of Matchlock Calivers",
					"matchlock_caliver",
					20,
					3.75f,
					1.2f,
					2.4f,
					1.0f,
					80
					);
			
			matchlock_musketoon = new FirearmAttributes(
					builder,
					"Attributes of Matchlock Musketoons",
					"matchlock_musketoon",
					24,
					3.75f,
					0.8f,
					2.2f,
					1.0f,
					80
					);
			
			matchlock_musket = new FirearmAttributes(
					builder,
					"Attributes of Matchlock Muskets",
					"matchlock_musket",
					24,
					4.0f,
					1.0f,
					2.0f,
					1.0f,
					80
					);
			
			matchlock_long_musket = new FirearmAttributes(
					builder,
					"Attributes of Matchlock Long Muskets",
					"matchlock_long_musket",
					28,
					4.25f,
					1.2f,
					1.8f,
					1.0f,
					80
					);
			
			matchlock_blunderbuss_pistol = new FirearmAttributes(
					builder,
					"Attributes of Matchlock Blunderbuss Pistols",
					"matchlock_blunderbuss_pistol",
					8,
					2.75f,
					0.4f,
					4.1f,
					1.0f,
					80
					);
			
			matchlock_blunderbuss = new FirearmAttributes(
					builder,
					"Attributes of Matchlock Blunderbusses",
					"matchlock_blunderbuss",
					24,
					3.5f,
					1.0f,
					2.3f,
					1.0f,
					80
					);
			
			builder.pop();
		}
	}
	
	public static class WheellockSettings {		
		public final FirearmAttributes wheellock_derringer;
		public final FirearmAttributes wheellock_pistol;
		public final FirearmAttributes wheellock_doublebarrel_pistol;
		public final FirearmAttributes wheellock_arquebus;
		
		public final FirearmAttributes wheellock_caliver;
		public final FirearmAttributes wheellock_musketoon;
		
		public final FirearmAttributes wheellock_musket;
		public final FirearmAttributes wheellock_long_musket;
		
		public final FirearmAttributes wheellock_blunderbuss_pistol;
		public final FirearmAttributes wheellock_blunderbuss;
		
		public final FirearmAttributes wheellock_hand_mortar;
		
		WheellockSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			// Wheellock
			wheellock_derringer = new FirearmAttributes(
					builder,
					"Attributes of Wheellock Derringers",
					"wheellock_derringer",
					16,
					3.125f,
					0.5f,
					5.5f,
					1.0f,
					80
					);
			
			wheellock_pistol = new FirearmAttributes(
					builder,
					"Attributes of Wheellock Pistols",
					"wheellock_pistol",
					20,
					3.375f,
					1.0f,
					2.7f,
					1.0f,
					80
					);
			
			wheellock_doublebarrel_pistol = new FirearmAttributes(
					builder,
					"Attributes of Wheellock Double Barrel Pistols",
					"wheellock_double_barrel_pistol",
					20,
					3.275f,
					0.8f,
					3.7f,
					0.8f,
					80
					);
			
			wheellock_arquebus = new FirearmAttributes(
					builder,
					"Attributes of Wheellock Arquebus",
					"wheellock_arquebus",
					24,
					3.625f,
					1.2f,
					2.4f,
					1.0f,
					80
					);
			
			wheellock_caliver = new FirearmAttributes(
					builder,
					"Attributes of Wheellock Calivers",
					"wheellock_caliver",
					28,
					3.875f,
					1.2f,
					2.1f,
					1.0f,
					80
					);
			
			wheellock_musketoon = new FirearmAttributes(
					builder,
					"Attributes of Wheellock Musketoons",
					"wheellock_musketoon",
					32,
					3.875f,
					0.8f,
					1.8f,
					1.0f,
					80
					);
			
			wheellock_musket = new FirearmAttributes(
					builder,
					"Attributes of Wheellock Muskets",
					"wheellock_musket",
					32,
					4.125f,
					1.0f,
					1.8f,
					1.0f,
					80
					);
			
			wheellock_long_musket = new FirearmAttributes(
					builder,
					"Attributes of Wheellock Long Muskets",
					"wheellock_long_musket",
					36,
					4.375f,
					1.2f,
					1.5f,
					1.0f,
					80
					);
			
			wheellock_blunderbuss_pistol = new FirearmAttributes(
					builder,
					"Attributes of Flintlock Blunderbuss Pistols",
					"flintlock_blunderbuss_pistol",
					28,
					3.875f,
					0.4f,
					4.2f,
					1.0f,
					80
					);
			
			wheellock_blunderbuss = new FirearmAttributes(
					builder,
					"Attributes of Wheellock Blunderbusses",
					"wheellock_blunderbuss",
					32,
					3.625f,
					1.0f,
					2.0f,
					1.0f,
					80
					);
			
			wheellock_hand_mortar = new FirearmAttributes(
					builder,
					"Attributes of Wheellock Hand Mortars",
					"wheellock_hand_mortar",
					18,
					1.5f,
					1.0f,
					7.0f,
					1.0f,
					80
					);
			
			builder.pop();
		}
	}
	
	public static class FlintlockSettings {		
		public final FirearmAttributes flintlock_derringer;
		public final FirearmAttributes flintlock_duckfoot_derringer;
		public final FirearmAttributes flintlock_pistol;
		public final FirearmAttributes flintlock_pepperbox_pistol;
		public final FirearmAttributes flintlock_arquebus;
		
		public final FirearmAttributes flintlock_caliver;
		public final FirearmAttributes flintlock_musketoon;
		
		public final FirearmAttributes flintlock_musket;
		public final FirearmAttributes flintlock_nock_gun;
		public final FirearmAttributes flintlock_long_musket;
		
		public final FirearmAttributes flintlock_blunderbuss_pistol;
		public final FirearmAttributes flintlock_blunderbuss;
		public final FirearmAttributes flintlock_doublebarrel_blunderbuss;
		
		FlintlockSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			// Flintlock
			flintlock_derringer = new FirearmAttributes(
					builder,
					"Attributes of Flintlock Derringers",
					"flintlock_derringer",
					28,
					3.25f,
					0.5f,
					5.0f,
					1.0f,
					80
					);
			
			flintlock_duckfoot_derringer = new FirearmAttributes(
					builder,
					"Attributes of Flintlock Duckfoot Derringers",
					"flintlock_duckfoot_derringers",
					24,
					3.0f,
					0.3f,
					10.0f,
					0.8f,
					80
					);
			
			flintlock_pistol = new FirearmAttributes(
					builder,
					"Attributes of Flintlock Pistols",
					"flintlock_pistol",
					32,
					3.5f,
					1.0f,
					2.0f,
					1.0f,
					80
					);
			
			flintlock_pepperbox_pistol = new FirearmAttributes(
					builder,
					"Attributes of Flintlock Pepperbox Pistols",
					"flintlock_pepperbox_pistol",
					28,
					3.4f,
					0.8f,
					3.0f,
					0.8f,
					80
					);
			
			flintlock_arquebus = new FirearmAttributes(
					builder,
					"Attributes of Flintlock Arquebus",
					"flintlock_arquebus",
					36,
					3.75f,
					1.2f,
					1.8f,
					1.0f,
					80
					);
			
			flintlock_caliver = new FirearmAttributes(
					builder,
					"Attributes of Flintlock Calivers",
					"flintlock_caliver",
					40,
					4.0f,
					1.2f,
					1.6f,
					1.0f,
					80
					);
			
			flintlock_musketoon = new FirearmAttributes(
					builder,
					"Attributes of Flintlock Musketoons",
					"flintlock_musketoon",
					44,
					4.0f,
					0.8f,
					1.5f,
					1.0f,
					80
					);
			
			flintlock_musket = new FirearmAttributes(
					builder,
					"Attributes of Flintlock Muskets",
					"flintlock_musket",
					44,
					4.25f,
					1.0f,
					1.4f,
					1.0f,
					80
					);
			
			flintlock_nock_gun = new FirearmAttributes(
					builder,
					"Attributes of Flintlock Nock Guns",
					"flintlock_nock_gun",
					40,
					4.0f,
					0.8f,
					1.8f,
					0.8f,
					80
					);
			
			flintlock_long_musket = new FirearmAttributes(
					builder,
					"Attributes of Flintlock Long Muskets",
					"flintlock_long_musket",
					48,
					4.5f,
					1.2f,
					1.2f,
					1.0f,
					80
					);
			
			flintlock_blunderbuss_pistol = new FirearmAttributes(
					builder,
					"Attributes of Flintlock Blunderbuss Pistols",
					"flintlock_blunderbuss_pistol",
					28,
					3.0f,
					0.4f,
					3.5f,
					1.0f,
					80
					);
			
			flintlock_blunderbuss = new FirearmAttributes(
					builder,
					"Attributes of Flintlock Blunderbusses",
					"flintlock_blunderbuss",
					44,
					3.5f,
					1.0f,
					1.7f,
					1.0f,
					80
					);
			
			flintlock_doublebarrel_blunderbuss = new FirearmAttributes(
					builder,
					"Attributes of Flintlock Doublebarrel Blunderbusses",
					"flintlock_doublebarrel_blunderbuss",
					40,
					3.5f,
					0.8f,
					1.7f,
					0.8f,
					80
					);
			
			builder.pop();
		}
	}
	
	public static class CaplockSettings {		
		public final FirearmAttributes caplock_derringer;
		public final FirearmAttributes caplock_duckfoot_derringer;
		public final FirearmAttributes caplock_pistol;
		public final FirearmAttributes caplock_pepperbox_pistol;
		public final FirearmAttributes caplock_arquebus;
		                                            
		public final FirearmAttributes caplock_caliver;
		public final FirearmAttributes caplock_musketoon;
		                                            
		public final FirearmAttributes caplock_musket;
		public final FirearmAttributes caplock_long_musket;
		                                            
		public final FirearmAttributes caplock_blunderbuss_pistol;
		public final FirearmAttributes caplock_blunderbuss;
		public final FirearmAttributes caplock_doublebarrel_blunderbuss;
		
		CaplockSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			// Caplock
			caplock_derringer = new FirearmAttributes(
					builder,
					"Attributes of Caplock Derringers",
					"caplock_derringer",
					40,
					3.375f,
					0.6f,
					5.0f,
					1.0f,
					80
					);
			
			caplock_duckfoot_derringer = new FirearmAttributes(
					builder,
					"Attributes of Caplock Duckfoot Derringers",
					"caplock_duckfoot_derringer",
					36,
					3.125f,
					0.4f,
					10.0f,
					0.8f,
					80
					);
			
			caplock_pistol = new FirearmAttributes(
					builder,
					"Attributes of Caplock Pistols",
					"caplock_pistol",
					44,
					3.625f,
					1.1f,
					2.0f,
					1.0f,
					80
					);
			
			caplock_pepperbox_pistol = new FirearmAttributes(
					builder,
					"Attributes of Caplock Pepperbox Pistols",
					"caplock_pepperbox_pistol",
					40,
					3.525f,
					0.9f,
					3.0f,
					0.8f,
					80
					);
			
			caplock_arquebus = new FirearmAttributes(
					builder,
					"Attributes of Caplock Arquebus",
					"caplock_arquebus",
					48,
					3.875f,
					1.3f,
					1.8f,
					1.0f,
					80
					);
			
			caplock_caliver = new FirearmAttributes(
					builder,
					"Attributes of Caplock Calivers",
					"caplock_caliver",
					52,
					4.125f,
					1.3f,
					1.6f,
					1.0f,
					80
					);
			
			caplock_musketoon = new FirearmAttributes(
					builder,
					"Attributes of Caplock Musketoons",
					"caplock_musketoon",
					44,
					4.125f,
					0.9f,
					1.5f,
					1.0f,
					80
					);
			
			caplock_musket = new FirearmAttributes(
					builder,
					"Attributes of Caplock Muskets",
					"caplock_musket",
					56,
					4.375f,
					1.1f,
					1.4f,
					1.0f,
					80
					);
			
			caplock_long_musket = new FirearmAttributes(
					builder,
					"Attributes of Caplock Long Muskets",
					"caplock_long_musket",
					60,
					4.625f,
					1.3f,
					1.2f,
					1.0f,
					80
					);
			
			caplock_blunderbuss_pistol = new FirearmAttributes(
					builder,
					"Attributes of Caplock Blunderbuss Pistols",
					"caplock_blunderbuss_pistol",
					40,
					3.125f,
					0.5f,
					3.5f,
					1.0f,
					80
					);
			
			caplock_blunderbuss = new FirearmAttributes(
					builder,
					"Attributes of Caplock Blunderbusses",
					"caplock_blunderbuss",
					56,
					3.625f,
					1.1f,
					1.7f,
					1.0f,
					80
					);
			
			caplock_doublebarrel_blunderbuss = new FirearmAttributes(
					builder,
					"Attributes of Caplock Doublebarrel Blunderbusses",
					"caplock_doublebarrel_blunderbuss",
					52,
					3.625f,
					0.9f,
					1.7f,
					0.8f,
					80
					);
			
			builder.pop();
		}
	}


	public static class FirearmAttributes {
		public final IntValue durability;
		public final IntValue reloadTicks;
		public final DoubleValue effectiveRangeModifier;
		public final DoubleValue shotDamageModifier;
		public final DoubleValue shotDeviationModifier;
		public final DoubleValue projectileSpeed;
		
		FirearmAttributes(final ForgeConfigSpec.Builder builder, final String comment, final String path,
						  final int defaultDurability, final float defaultProjectileSpeed,
						  final float defaultEffectiveRangeModifier, final float defaultShotDeviaitionModifier, final float defaultShotDamageModifier,
						  final int defaultReloadTicks) {
			builder.comment(comment).push(path);
			
			durability = builder
					.comment("How many uses before breaking")
					.defineInRange("durability", defaultDurability, 1, Integer.MAX_VALUE);
			
			reloadTicks = builder
					.comment("How long it takes to fully reload via breechloading in ticks, if weapon is a breeachloader")
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
		public final ArtilleryAmmoSettings artilleryAmmoSettings;
		public final CannonArtilleryAttributes medium_naval_cannon;
		public final CannonArtilleryAttributes bombard;
		public final RocketArtilleryAttributes congreve_rocket_stand;
		
		
		ArtillerySettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			artilleryAmmoSettings = new ArtilleryAmmoSettings(
					builder,
					"Artillery ammo settings",
					"artilleryAmmoSettings");
			
			medium_naval_cannon = new CannonArtilleryAttributes(
					builder,
					"Attributes of Naval Cannons",
					"medium_naval_cannon",
					1.5f,
					0.8f,
					1.7f,
					0.8f
					);
			
			bombard = new CannonArtilleryAttributes(
					builder,
					"Attributes of Bombards",
					"bombard",
					1.5f,
					0.8f,
					1.7f,
					0.8f
					);
			
			congreve_rocket_stand = new RocketArtilleryAttributes(
					builder,
					"Attributes of Congreve Rocket Stands",
					"congreve_rocket_stand",
					4.0f
					);
		
			builder.pop();
		}
	}
	
	public static class RocketArtilleryAttributes {
		public final DoubleValue rocketDeviation;
		
		RocketArtilleryAttributes(final ForgeConfigSpec.Builder builder, final String comment, final String path, 
				final float defaultRocketDeviation) {
			builder.comment(comment).push(path);
			
			rocketDeviation = builder
					.comment("How much deviation rockets have when shot from this artillery")
					.defineInRange("rocketDeviation", defaultRocketDeviation, 0.001f, Float.MAX_VALUE);	
			
			builder.pop();
		}
	}
	
	public static class CannonArtilleryAttributes {
		public final DoubleValue effectiveRangeModifier;
		public final DoubleValue damageModifier;
		public final DoubleValue projectileDeviation;
		public final DoubleValue projectileSpeed;
		
		CannonArtilleryAttributes(final ForgeConfigSpec.Builder builder, final String comment, final String path, 
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
		public final ArtilleryAmmoAttributes medium_iron_explosive_rocket;
		public final ArtilleryAmmoAttributes small_iron_cannonball;
		public final ArtilleryAmmoAttributes medium_iron_cannonball;
		public final ArtilleryAmmoAttributes large_iron_cannonball;
		public final ArtilleryAmmoAttributes small_iron_explosive_shell;
		public final ArtilleryAmmoAttributes medium_iron_explosive_shell;
		public final ArtilleryAmmoAttributes large_iron_explosive_shell;
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
			
			medium_iron_explosive_rocket = new ArtilleryAmmoAttributes(
					builder,
					"Attributes of Medium Iron Explosive Rocket ammo",
					"medium_iron_explosive_rocket",
					1,
					1,
					15.0f,
					0.6f,
					100.0f,
					1.0f,
					0.2f,
					0,
					3f
					);
			
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
		
			small_iron_explosive_shell = new ArtilleryAmmoAttributes(
					builder,
					"Attributes of Small Iron Explosive Shell ammo",
					"small_iron_explosive_shell",
					1,
					1,
					15.0f,
					0.4f,
					100.0f,
					1.0f,
					0.2f,
					0,
					2f
					);
			
			medium_iron_explosive_shell = new ArtilleryAmmoAttributes(
					builder,
					"Attributes of Medium Iron Explosive Shell ammo",
					"medium_iron_explosive_shell",
					1,
					1,
					15.0f,
					0.6f,
					100.0f,
					1.0f,
					0.2f,
					20,
					3f
					);
			
			large_iron_explosive_shell = new ArtilleryAmmoAttributes(
					builder,
					"Attributes of Large Iron Explosive Shell ammo",
					"large_iron_explosive_shell",
					1,
					1,
					15.0f,
					0.8f,
					100.0f,
					1.0f,
					0.2f,
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
	
	public static class MobSettings {
		public final DoubleValue mobFirearmEquipChance;
		public final DoubleValue mobArmorEquipChance;
		public final BooleanValue resetMobShotTimerOnHit;
		public final IntValue firearmMobShotTime;
		public final IntValue firearmMobShotTimeHard;
		public final DoubleValue firearmMobBaseProjectileDeviation;
		
		MobSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			mobFirearmEquipChance = builder
					.comment("The chance mobs that can use firearms will spawn with firearms. Set 0.0 to disable")
					.defineInRange("mobFirearmEquipChance", 0.05f, 0.0f, 1.0f);
			
			mobArmorEquipChance = builder
					.comment("The chance mobs that can use firearms will spawn with armor, after being equiped with a firearm. Set 0.0 to disable")
					.defineInRange("mobArmorEquipChance", 0.8f, 0.0f, 1.0f);
			
			resetMobShotTimerOnHit = builder
					.comment("Whether mobs have their shot timer reset when struck")
					.define("resetMobShotTimerOnHit", false);
			
			firearmMobShotTime = builder
					.comment("How long of an interval in ticks mobs shoot their weapons")
					.defineInRange("firearmMobShotTime", 140, 1, Integer.MAX_VALUE);
			
			firearmMobShotTimeHard = builder
					.comment("How long of an interval in ticks mobs shoot their weapons on Hard difficulty")
					.defineInRange("firearmMobShotTimeHard", 100, 1, Integer.MAX_VALUE);
			
			firearmMobBaseProjectileDeviation = builder
					.comment("The base projectile deviation of mobs when shooting their weapon")
					.defineInRange("firearmMobBaseProjectileDeviation", 3.0f, 0.1f, Float.MAX_VALUE);
			
			builder.pop();
		}
	}
	
	public static class GenericMobSettings {
		public final BooleanValue canSpawn;
		public final IntValue spawnWeight;
		public final IntValue minCount;
		public final IntValue maxCount;
		public final ConfigValue<List<String>> validBiomeCategories;
		public final ConfigValue<List<String>> validBiomes;
		
		GenericMobSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path,
				final boolean defaultCanSpawn, final int defaultSpawnWeight, final int defaultMinCount, final int defaultMaxCount,
				final List<String> defaultValidBiomeCategories, final List<String> defaultValidBiomes) {
			builder.comment(comment).push(path);
			
			canSpawn = builder
					.comment("Whether this mob is allowed to spawn")
					.define("canSpawn", true);
			
			spawnWeight = builder
					.comment("The spawn chance of this mob in relation to other mobs")
					.defineInRange("spawnWeight", defaultSpawnWeight, 0, Integer.MAX_VALUE);
			
			minCount = builder
					.comment("The minimum amount of this mob to spawn")
					.defineInRange("minCount", defaultMinCount, 0, Integer.MAX_VALUE);
			
			maxCount = builder
					.comment("The maximum amount of this mob to spawn")
					.defineInRange("maxCount", defaultMaxCount, 0, Integer.MAX_VALUE);
			
			validBiomeCategories = builder
					.comment("A list of valid biome categories that this mob can spawn in")
					.define("validBiomeCategories", defaultValidBiomeCategories);
			
			validBiomes = builder
					.comment("A list of valid biomes that this mob can spawn in")
					.define("validBiomes", defaultValidBiomes);
			
			builder.pop();
		}
	}
	
	public static class EquipmentSettings {
		public final BooleanValue allowEquipmentEffects;
		public final MusketeerHatSettings musketeerHatSettings;
		public final HorsemansPotHelmSettings horsemansPotHelmSettings;
		
		EquipmentSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			allowEquipmentEffects = builder
					.comment("Allow equipment to impart special effects")
					.define("allowEquipmentEffects", true);
			
			musketeerHatSettings = new MusketeerHatSettings(builder, "Musketeer Hats", "musketeerHatSettings",
					true,
					0.1D
					);
			
			horsemansPotHelmSettings = new HorsemansPotHelmSettings(builder, "Horseman's Pot Helms", "horsemansPotHelmSettings",
					true,
					1.0D
					);
			
			builder.pop();
		}
	}
	
	public static class MusketeerHatSettings {
		public final BooleanValue allowEffects;
		public final DoubleValue percentArmorBypassIncrease;
		
		MusketeerHatSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path,
				final boolean defaultAllowEffects, final double defaultPercentArmorBypassIncrease) {
			builder.comment(comment).push(path);
			
			allowEffects = builder
					.comment("Allow Musketeer Hat to increase armor bypass percentage for damage when worn")
					.define("allowEffects", defaultAllowEffects);

			percentArmorBypassIncrease = builder
					.comment("The additional percentage of armor bypass that wearing a musketeer hat gives to firearm projectiles")
					.defineInRange("percentArmorBypassIncrease", defaultPercentArmorBypassIncrease, 0.0D, 10.0D);
			
			builder.pop();
		}
	}
	
	public static class HorsemansPotHelmSettings {
		public final BooleanValue allowEffects;
		public final DoubleValue percentMountedFirearmAccuracyIncrease;
		
		HorsemansPotHelmSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path,
				final boolean defaultAllowEffects, final double defaultPercentMountedFirearmAccuracyIncrease) {
			builder.comment(comment).push(path);
			
			allowEffects = builder
					.comment("Allow Horseman's Pot Helm to decrease accuracy debuff for firearms while mounted")
					.define("allowEffects", defaultAllowEffects);

			percentMountedFirearmAccuracyIncrease = builder
					.comment("The percentage of accuracy increase to firearm projectiles while mounted while wearing a horseman's pot helm")
					.defineInRange("percentMountedInaccuracyDecrease", defaultPercentMountedFirearmAccuracyIncrease, 0.0D, 10.0D);
			
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
	
	public static Object getClient(ConfigValue<?> value)
	{
		return clientSpec.isLoaded() ? value.get(): value.getDefault();
	}
	
	public static Object getCommon(ConfigValue<?> value)
	{
		return commonSpec.isLoaded() ? value.get(): value.getDefault();
	}
	
	public static Object getServer(ConfigValue<?> value)
	{
		return serverSpec.isLoaded() ? value.get(): value.getDefault();
	}
}
