package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.versions.forge.ForgeVersion;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author grilled-salmon
 */
public class ModTags {
	public static class Blocks {
		private static ITag.INamedTag<Block> tag(final String name) {
			return BlockTags.bind(new ResourceLocation(OldGuns.MODID, name).toString());
		}
	}
	
	public static class Items {
		
		// Firearms
		public static final ITag.INamedTag<Item> FIREARM = tag("firearm");
		
		// Ammo
		public static final ITag.INamedTag<Item> SMALL_ROCK_MUSKET_BALL = tag("small_rock_musket_ball");
		public static final ITag.INamedTag<Item> MEDIUM_ROCK_MUSKET_BALL = tag("medium_rock_musket_ball");
		public static final ITag.INamedTag<Item> LARGE_ROCK_MUSKET_BALL = tag("large_rock_musket_ball");
		
		public static final ITag.INamedTag<Item> SMALL_ROCK_BIRDSHOT = tag("small_rock_birdshot");
		public static final ITag.INamedTag<Item> MEDIUM_ROCK_BIRDSHOT = tag("medium_rock_birdshot");
		public static final ITag.INamedTag<Item> LARGE_ROCK_BIRDSHOT = tag("large_rock_birdshot");
		
		public static final ITag.INamedTag<Item> SMALL_METAL_MUSKET_BALL = tag("small_metal_musket_ball");
		public static final ITag.INamedTag<Item> MEDIUM_METAL_MUSKET_BALL = tag("medium_metal_musket_ball");
		public static final ITag.INamedTag<Item> LARGE_METAL_MUSKET_BALL = tag("large_metal_musket_ball");
		
		public static final ITag.INamedTag<Item> SMALL_METAL_BUCKSHOT = tag("small_metal_buckshot");
		public static final ITag.INamedTag<Item> MEDIUM_METAL_BUCKSHOT = tag("medium_metal_buckshot");
		public static final ITag.INamedTag<Item> LARGE_METAL_BUCKSHOT = tag("large_metal_buckshot");
		
		public static final ITag.INamedTag<Item> SMALL_METAL_BIRDSHOT = tag("small_metal_birdshot");
		public static final ITag.INamedTag<Item> MEDIUM_METAL_BIRDSHOT = tag("medium_metal_birdshot");
		public static final ITag.INamedTag<Item> LARGE_METAL_BIRDSHOT = tag("large_metal_birdshot");
		
		// Powder
		public static final ITag.INamedTag<Item> ANY_GUNPOWDER = tag("any_gunpowder");
		public static final ITag.INamedTag<Item> ANY_BLACK_POWDER = tag("any_black_powder");
		public static final ITag.INamedTag<Item> MEDIUM_GRADE_BLACK_POWDER = tag("medium_grade_black_powder");
		public static final ITag.INamedTag<Item> HIGH_GRADE_BLACK_POWDER = tag("high_grade_black_powder");
		public static final ITag.INamedTag<Item> MATCHLOCK_SUITABLE_POWDER = tag("matchlock_suitable_powder");
		public static final ITag.INamedTag<Item> WHEELLOCK_SUITABLE_POWDER = tag("wheellock_suitable_powder");
		public static final ITag.INamedTag<Item> FLINTLOCK_SUITABLE_POWDER = tag("flintlock_suitable_powder");
		public static final ITag.INamedTag<Item> CAPLOCK_SUITABLE_POWDER = tag("caplock_suitable_powder");
		public static final ITag.INamedTag<Item> NEEDLEFIRE_SUITABLE_POWDER = tag("needlefire_suitable_powder");
		
		// Parts
		public static final ITag.INamedTag<Item> MATCHLOCK_MECHANISM = tag("matchlock_mechanism");
		public static final ITag.INamedTag<Item> WHEELLOCK_MECHANISM = tag("wheellock_mechanism");
		public static final ITag.INamedTag<Item> FLINTLOCK_MECHANISM = tag("flintlock_mechanism");
		public static final ITag.INamedTag<Item> CAPLOCK_MECHANISM = tag("caplock_mechanism");
		
		public static final ITag.INamedTag<Item> TINY_ROCK_BARREL = tag("tiny_rock_barrel");
		public static final ITag.INamedTag<Item> SMALL_ROCK_BARREL = tag("small_rock_barrel");
		public static final ITag.INamedTag<Item> MEDIUM_ROCK_BARREL = tag("medium_rock_barrel");
		public static final ITag.INamedTag<Item> LARGE_ROCK_BARREL = tag("large_rock_barrel");
		
		public static final ITag.INamedTag<Item> SMALL_ROCK_FLARED_BARREL = tag("small_rock_flared_barrel");
		public static final ITag.INamedTag<Item> MEDIUM_ROCK_FLARED_BARREL = tag("medium_rock_flared_barrel");
		public static final ITag.INamedTag<Item> LARGE_ROCK_FLARED_BARREL = tag("large_rock_flared_barrel");
		
		public static final ITag.INamedTag<Item> TINY_METAL_BARREL = tag("tiny_metal_barrel");
		public static final ITag.INamedTag<Item> SMALL_METAL_BARREL = tag("small_metal_barrel");
		public static final ITag.INamedTag<Item> MEDIUM_METAL_BARREL = tag("medium_metal_barrel");
		public static final ITag.INamedTag<Item> LARGE_METAL_BARREL = tag("large_metal_barrel");
		
		public static final ITag.INamedTag<Item> SMALL_METAL_FLARED_BARREL = tag("small_metal_flared_barrel");
		public static final ITag.INamedTag<Item> MEDIUM_METAL_FLARED_BARREL = tag("medium_metal_flared_barrel");
		public static final ITag.INamedTag<Item> LARGE_METAL_FLARED_BARREL = tag("large_metal_flared_barrel");
		
		public static final ITag.INamedTag<Item> SMALL_HANDLE = tag("small_handle");
		public static final ITag.INamedTag<Item> MEDIUM_HANDLE = tag("medium_handle");
		public static final ITag.INamedTag<Item> LARGE_HANDLE = tag("large_handle");

		public static final ITag.INamedTag<Item> SMALL_STOCK = tag("small_stock");
		public static final ITag.INamedTag<Item> MEDIUM_STOCK = tag("medium_stock");
		public static final ITag.INamedTag<Item> LARGE_STOCK = tag("large_stock");
		
		public static final ITag.INamedTag<Item> WOOD_GEAR_SET = tag("wood_gear_set");
		public static final ITag.INamedTag<Item> IRON_GEAR_SET = tag("iron_gear_set");
		public static final ITag.INamedTag<Item> GOLD_GEAR_SET = tag("gold_gear_set");
		
		public static final ITag.INamedTag<Item> WOOD_TRIGGER_ASSEMBLY = tag("wood_trigger_assembly");
		public static final ITag.INamedTag<Item> IRON_TRIGGER_ASSEMBLY = tag("iron_trigger_assembly");
		public static final ITag.INamedTag<Item> GOLD_TRIGGER_ASSEMBLY = tag("gold_trigger_assembly");
		
		public static final ITag.INamedTag<Item> MATCH_CORD = tag("match_cord");
		
		// Forge Tags
		public static final ITag.INamedTag<Item> NUGGETS_LEAD = forgeTag("nuggets/lead");
		public static final ITag.INamedTag<Item> INGOTS_LEAD = forgeTag("ingots/lead");
		public static final ITag.INamedTag<Item> NUGGETS_BRASS = forgeTag("nuggets/brass");
		public static final ITag.INamedTag<Item> INGOTS_BRASS = forgeTag("ingots/brass");
		public static final ITag.INamedTag<Item> DUST_NITER = forgeTag("dust/niter");
		public static final ITag.INamedTag<Item> DUST_NITRE = forgeTag("dust/nitre");
		public static final ITag.INamedTag<Item> DUST_SALTPETER = forgeTag("dust/saltpeter");
		public static final ITag.INamedTag<Item> DUST_SULFUR = forgeTag("dust/sulfur");
		
		private static ITag.INamedTag<Item> tag(final String name) {
			return ItemTags.bind(new ResourceLocation(OldGuns.MODID, name).toString());
		}
		
		private static ITag.INamedTag<Item> forgeTag(final String name) {
			return ItemTags.bind(new ResourceLocation(ForgeVersion.MOD_ID, name).toString());
		}
	}
}
