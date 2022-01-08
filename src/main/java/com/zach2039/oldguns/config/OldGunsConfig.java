package com.zach2039.oldguns.config;

import org.apache.commons.lang3.tuple.Pair;
import org.checkerframework.common.value.qual.BoolVal;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class OldGunsConfig {
	
	public static class Common {
		public final BooleanValue patchRecipeBook;
		public final BooleanValue printDebugMessages;
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
	
	public static class FirearmSettings {
		public final MuzzleloadingFirearmAttributes flintlock_pistol;
		public final MuzzleloadingFirearmAttributes flintlock_caliver;
		public final MuzzleloadingFirearmAttributes flintlock_long_musket;
		public final MuzzleloadingFirearmAttributes flintlock_blunderbuss;
		
		public final FirearmAmmoAttributes small_iron_musket_ball;
		public final FirearmAmmoAttributes medium_iron_musket_ball;
		public final FirearmAmmoAttributes large_iron_musket_ball;
		
		public final FirearmAmmoAttributes small_iron_buckshot;
		public final FirearmAmmoAttributes medium_iron_buckshot;
		public final FirearmAmmoAttributes large_iron_buckshot;
		
		public final FirearmAmmoAttributes small_iron_birdshot;
		public final FirearmAmmoAttributes medium_iron_birdshot;
		public final FirearmAmmoAttributes large_iron_birdshot;
		
		FirearmSettings(final ForgeConfigSpec.Builder builder, final String comment, final String path) {
			builder.comment(comment).push(path);
			
			flintlock_pistol = new MuzzleloadingFirearmAttributes(
					builder,
					"Attributes of Flintlock Pistols",
					"flintlock_pistol",
					32,
					3.5f,
					1.2f,
					2.0f,
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
