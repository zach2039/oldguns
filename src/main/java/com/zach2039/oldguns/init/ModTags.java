package com.zach2039.oldguns.init;

import java.util.List;

import com.zach2039.oldguns.OldGuns;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.versions.forge.ForgeVersion;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author zach2039
 */
public class ModTags {
	public static class Blocks {
		
		private static TagKey<Block> tag(final String name) {
			return BlockTags.create(new ResourceLocation(OldGuns.MODID, name));
		}
	}
	
	public static class Items {
		
		// Artillery
		// Ammo
		public static final TagKey<Item> SMALL_POWDER_CHARGE = tag("small_powder_charge");
		public static final TagKey<Item> MEDIUM_POWDER_CHARGE = tag("medium_powder_charge");
		public static final TagKey<Item> LARGE_POWDER_CHARGE = tag("large_powder_charge");

		// Parts
		public static final TagKey<Item> SMALL_ROCK_CANNON_BARREL = tag("small_rock_cannon_barrel");
		public static final TagKey<Item> MEDIUM_ROCK_CANNON_BARREL = tag("medium_rock_cannon_barrel");
		public static final TagKey<Item> LARGE_ROCK_CANNON_BARREL = tag("large_rock_cannon_barrel");

		public static final TagKey<Item> SMALL_METAL_CANNON_BARREL = tag("small_metal_cannon_barrel");
		public static final TagKey<Item> MEDIUM_METAL_CANNON_BARREL = tag("medium_metal_cannon_barrel");
		public static final TagKey<Item> LARGE_METAL_CANNON_BARREL = tag("large_metal_cannon_barrel");

		public static final TagKey<Item> SMALL_NAVAL_CARRIAGE = tag("small_naval_carriage");
		public static final TagKey<Item> MEDIUM_NAVAL_CARRIAGE = tag("medium_naval_carriage");
		public static final TagKey<Item> LARGE_NAVAL_CARRIAGE = tag("large_naval_carriage");

		public static final TagKey<Item> TINY_CARRIAGE_WHEEL = tag("tiny_carriage_wheel");
		public static final TagKey<Item> SMALL_CARRIAGE_WHEEL = tag("small_carriage_wheel");
		public static final TagKey<Item> MEDIUM_CARRIAGE_WHEEL = tag("medium_carriage_wheel");
		public static final TagKey<Item> LARGE_CARRIAGE_WHEEL = tag("large_carriage_wheel");

		
		// Firearms
		public static final TagKey<Item> FIREARM = tag("firearm");

		public static final TagKey<Item> MATCHLOCK_FIREARM = tag("matchlock_firearm");
		public static final TagKey<Item> SMALL_MATCHLOCK_FIREARM = tag("small_matchlock_firearm");
		public static final TagKey<Item> MEDIUM_MATCHLOCK_FIREARM = tag("medium_matchlock_firearm");
		public static final TagKey<Item> LARGE_MATCHLOCK_FIREARM = tag("large_matchlock_firearm");

		public static final TagKey<Item> WHEELLOCK_FIREARM = tag("wheellock_firearm");
		public static final TagKey<Item> SMALL_WHEELLOCK_FIREARM = tag("small_wheellock_firearm");
		public static final TagKey<Item> MEDIUM_WHEELLOCK_FIREARM = tag("medium_wheellock_firearm");
		public static final TagKey<Item> LARGE_WHEELLOCK_FIREARM = tag("large_wheellock_firearm");
		public static final TagKey<Item> HUGE_WHEELLOCK_FIREARM = tag("huge_wheellock_firearm");

		public static final TagKey<Item> FLINTLOCK_FIREARM = tag("flintlock_firearm");
		public static final TagKey<Item> SMALL_FLINTLOCK_FIREARM = tag("small_flintlock_firearm");
		public static final TagKey<Item> MEDIUM_FLINTLOCK_FIREARM = tag("medium_flintlock_firearm");
		public static final TagKey<Item> LARGE_FLINTLOCK_FIREARM = tag("large_flintlock_firearm");
		public static final TagKey<Item> HUGE_FLINTLOCK_FIREARM = tag("huge_flintlock_firearm");

		public static final TagKey<Item> CAPLOCK_FIREARM = tag("caplock_firearm");
		public static final TagKey<Item> SMALL_CAPLOCK_FIREARM = tag("small_caplock_firearm");
		public static final TagKey<Item> MEDIUM_CAPLOCK_FIREARM = tag("medium_caplock_firearm");
		public static final TagKey<Item> LARGE_CAPLOCK_FIREARM = tag("large_caplock_firearm");
		public static final TagKey<Item> HUGE_CAPLOCK_FIREARM = tag("huge_caplock_firearm");
		
		// Ammo
		public static final TagKey<Item> ANY_BULLET = tag("any_bullet");
		public static final TagKey<Item> ANY_FIREARM_BULLET = tag("any_firearm_bullet");
		public static final TagKey<Item> ANY_FIREARM_CARTRIDGE = tag("any_firearm_cartridge");
		
		public static final TagKey<Item> SMALL_ROCK_MUSKET_BALL = tag("small_rock_musket_ball");
		public static final TagKey<Item> MEDIUM_ROCK_MUSKET_BALL = tag("medium_rock_musket_ball");
		public static final TagKey<Item> LARGE_ROCK_MUSKET_BALL = tag("large_rock_musket_ball");

		public static final TagKey<Item> SMALL_ROCK_BIRDSHOT = tag("small_rock_birdshot");
		public static final TagKey<Item> MEDIUM_ROCK_BIRDSHOT = tag("medium_rock_birdshot");
		public static final TagKey<Item> LARGE_ROCK_BIRDSHOT = tag("large_rock_birdshot");

		public static final TagKey<Item> SMALL_METAL_MUSKET_BALL = tag("small_metal_musket_ball");
		public static final TagKey<Item> MEDIUM_METAL_MUSKET_BALL = tag("medium_metal_musket_ball");
		public static final TagKey<Item> LARGE_METAL_MUSKET_BALL = tag("large_metal_musket_ball");

		public static final TagKey<Item> SMALL_METAL_BUCKSHOT = tag("small_metal_buckshot");
		public static final TagKey<Item> MEDIUM_METAL_BUCKSHOT = tag("medium_metal_buckshot");
		public static final TagKey<Item> LARGE_METAL_BUCKSHOT = tag("large_metal_buckshot");

		public static final TagKey<Item> SMALL_METAL_BIRDSHOT = tag("small_metal_birdshot");
		public static final TagKey<Item> MEDIUM_METAL_BIRDSHOT = tag("medium_metal_birdshot");
		public static final TagKey<Item> LARGE_METAL_BIRDSHOT = tag("large_metal_birdshot");

		public static final TagKey<Item> SMALL_METAL_CANNONBALL = tag("small_metal_cannonball");
		public static final TagKey<Item> MEDIUM_METAL_CANNONBALL = tag("medium_metal_cannonball");
		public static final TagKey<Item> LARGE_METAL_CANNONBALL = tag("large_metal_cannonball");
		
		public static final TagKey<Item> SMALL_METAL_CANISTER_SHOT = tag("small_metal_canister_shot");
		public static final TagKey<Item> MEDIUM_METAL_CANISTER_SHOT = tag("medium_metal_canister_shot");
		public static final TagKey<Item> LARGE_METAL_CANISTER_SHOT = tag("large_metal_canister_shot");
		
		public static final TagKey<Item> SMALL_METAL_GRAPESHOT = tag("small_metal_grapeshot");
		public static final TagKey<Item> MEDIUM_METAL_GRAPESHOT = tag("medium_metal_grapeshot");
		public static final TagKey<Item> LARGE_METAL_GRAPESHOT = tag("large_metal_grapeshot");
		
		public static final TagKey<Item> SMALL_METAL_EXPLOSIVE_SHELL = tag("small_metal_explosive_shell");
		public static final TagKey<Item> MEDIUM_METAL_EXPLOSIVE_SHELL = tag("medium_metal_explosive_shell");
		public static final TagKey<Item> LARGE_METAL_EXPLOSIVE_SHELL = tag("large_metal_explosive_shell");
		
		// Cartridge
		public static final TagKey<Item> SMALL_MATCHLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE = tag("small_matchlock_suitable_metal_musket_ball_cartridge");
		public static final TagKey<Item> MEDIUM_MATCHLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE = tag("medium_matchlock_suitable_metal_musket_ball_cartridge");
		public static final TagKey<Item> LARGE_MATCHLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE = tag("large_matchlock_suitable_metal_musket_ball_cartridge");

		public static final TagKey<Item> SMALL_WHEELLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE = tag("small_wheellock_suitable_metal_musket_ball_cartridge");
		public static final TagKey<Item> MEDIUM_WHEELLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE = tag("medium_wheellock_suitable_metal_musket_ball_cartridge");
		public static final TagKey<Item> LARGE_WHEELLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE = tag("large_wheellock_suitable_metal_musket_ball_cartridge");

		public static final TagKey<Item> SMALL_FLINTLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE = tag("small_flintlock_suitable_metal_musket_ball_cartridge");
		public static final TagKey<Item> MEDIUM_FLINTLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE = tag("medium_flintlock_suitable_metal_musket_ball_cartridge");
		public static final TagKey<Item> LARGE_FLINTLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE = tag("large_flintlock_suitable_metal_musket_ball_cartridge");

		public static final TagKey<Item> SMALL_CAPLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE = tag("small_caplock_suitable_metal_musket_ball_cartridge");
		public static final TagKey<Item> MEDIUM_CAPLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE = tag("medium_caplock_suitable_metal_musket_ball_cartridge");
		public static final TagKey<Item> LARGE_CAPLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE = tag("large_caplock_suitable_metal_musket_ball_cartridge");
		
		// Reloading
		public static final TagKey<Item> PERCUSSION_CAP = tag("percussion_cap");
		
		// Powder
		public static final TagKey<Item> ANY_GUNPOWDER = tag("any_gunpowder");
		public static final TagKey<Item> ANY_GUNPOWDER_BLOCK = tag("any_gunpowder_block");
		public static final TagKey<Item> ANY_BLACK_POWDER = tag("any_black_powder");
		public static final TagKey<Item> LOW_GRADE_BLACK_POWDER = tag("low_grade_black_powder");
		public static final TagKey<Item> MEDIUM_GRADE_BLACK_POWDER = tag("medium_grade_black_powder");
		public static final TagKey<Item> HIGH_GRADE_BLACK_POWDER = tag("high_grade_black_powder");
		public static final TagKey<Item> MATCHLOCK_SUITABLE_POWDER = tag("matchlock_suitable_powder");
		public static final TagKey<Item> WHEELLOCK_SUITABLE_POWDER = tag("wheellock_suitable_powder");
		public static final TagKey<Item> FLINTLOCK_SUITABLE_POWDER = tag("flintlock_suitable_powder");
		public static final TagKey<Item> CAPLOCK_SUITABLE_POWDER = tag("caplock_suitable_powder");
		public static final TagKey<Item> RIMFIRE_SUITABLE_POWDER = tag("rimfire_suitable_powder");

		// Parts
		public static final TagKey<Item> MATCHLOCK_MECHANISM = tag("matchlock_mechanism");
		public static final TagKey<Item> WHEELLOCK_MECHANISM = tag("wheellock_mechanism");
		public static final TagKey<Item> FLINTLOCK_MECHANISM = tag("flintlock_mechanism");
		public static final TagKey<Item> CAPLOCK_MECHANISM = tag("caplock_mechanism");

		public static final TagKey<Item> TINY_ROCK_BARREL = tag("tiny_rock_barrel");
		public static final TagKey<Item> SMALL_ROCK_BARREL = tag("small_rock_barrel");
		public static final TagKey<Item> MEDIUM_ROCK_BARREL = tag("medium_rock_barrel");
		public static final TagKey<Item> LARGE_ROCK_BARREL = tag("large_rock_barrel");

		public static final TagKey<Item> SMALL_ROCK_FLARED_BARREL = tag("small_rock_flared_barrel");
		public static final TagKey<Item> MEDIUM_ROCK_FLARED_BARREL = tag("medium_rock_flared_barrel");
		public static final TagKey<Item> LARGE_ROCK_FLARED_BARREL = tag("large_rock_flared_barrel");

		public static final TagKey<Item> TINY_METAL_BARREL = tag("tiny_metal_barrel");
		public static final TagKey<Item> SMALL_METAL_BARREL = tag("small_metal_barrel");
		public static final TagKey<Item> MEDIUM_METAL_BARREL = tag("medium_metal_barrel");
		public static final TagKey<Item> LARGE_METAL_BARREL = tag("large_metal_barrel");

		public static final TagKey<Item> SMALL_METAL_FLARED_BARREL = tag("small_metal_flared_barrel");
		public static final TagKey<Item> MEDIUM_METAL_FLARED_BARREL = tag("medium_metal_flared_barrel");
		public static final TagKey<Item> LARGE_METAL_FLARED_BARREL = tag("large_metal_flared_barrel");

		public static final TagKey<Item> SMALL_HANDLE = tag("small_handle");
		public static final TagKey<Item> MEDIUM_HANDLE = tag("medium_handle");
		public static final TagKey<Item> LARGE_HANDLE = tag("large_handle");

		public static final TagKey<Item> SMALL_STOCK = tag("small_stock");
		public static final TagKey<Item> MEDIUM_STOCK = tag("medium_stock");
		public static final TagKey<Item> LARGE_STOCK = tag("large_stock");

		public static final TagKey<Item> WOOD_GEAR_SET = tag("wood_gear_set");
		public static final TagKey<Item> IRON_GEAR_SET = tag("iron_gear_set");
		public static final TagKey<Item> GOLD_GEAR_SET = tag("gold_gear_set");
		public static final TagKey<Item> DIAMOND_GEAR_SET = tag("diamond_gear_set");

		public static final TagKey<Item> WOOD_TRIGGER_ASSEMBLY = tag("wood_trigger_assembly");
		public static final TagKey<Item> IRON_TRIGGER_ASSEMBLY = tag("iron_trigger_assembly");
		public static final TagKey<Item> GOLD_TRIGGER_ASSEMBLY = tag("gold_trigger_assembly");
		public static final TagKey<Item> DIAMOND_TRIGGER_ASSEMBLY = tag("diamond_trigger_assembly");
		
		public static final TagKey<Item> PERCUSSION_CAP_CONE = tag("percussion_cap_cone");

		public static final TagKey<Item> MATCH_CORD = tag("match_cord");

		// Forge Tags
		public static final TagKey<Item> NUGGETS_LEAD = forgeTag("nuggets/lead");
		public static final TagKey<Item> INGOTS_LEAD = forgeTag("ingots/lead");
		public static final TagKey<Item> NUGGETS_BRASS = forgeTag("nuggets/brass");
		public static final TagKey<Item> INGOTS_BRASS = forgeTag("ingots/brass");
		public static final TagKey<Item> INGOTS_MERCURY = forgeTag("ingots/mercury");
		public static final TagKey<Item> NUGGETS_MERCURY = forgeTag("nuggets/mercury");
		public static final TagKey<Item> INGOTS_QUICKSILVER = forgeTag("ingots/quicksilver");
		public static final TagKey<Item> NUGGETS_QUICKSILVER = forgeTag("nuggets/quicksilver");
		public static final TagKey<Item> DUST_NITER = forgeTag("dusts/niter");
		public static final TagKey<Item> DUST_NITRE = forgeTag("dusts/nitre");
		public static final TagKey<Item> DUST_SALTPETER = forgeTag("dusts/saltpeter");
		public static final TagKey<Item> DUST_SULFUR = forgeTag("dusts/sulfur");
		
		private static TagKey<Item> tag(final String name) {
			return ItemTags.create(new ResourceLocation(OldGuns.MODID, name));
		}
		
		private static TagKey<Item> forgeTag(final String name) {
			return ItemTags.create(new ResourceLocation(ForgeVersion.MOD_ID, name));
		}
	}
}
