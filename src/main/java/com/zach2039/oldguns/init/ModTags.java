package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
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
		private static Tag.Named<Block> tag(final String name) {
			return BlockTags.bind(new ResourceLocation(OldGuns.MODID, name).toString());
		}
	}
	
	public static class Items {
		
		// Firearms
		public static final Tag.Named<Item> FIREARM = tag("firearm");
		
		// Ammo
		public static final Tag.Named<Item> SMALL_METAL_MUSKET_BALL = tag("small_metal_musket_ball");
		public static final Tag.Named<Item> MEDIUM_METAL_MUSKET_BALL = tag("medium_metal_musket_ball");
		public static final Tag.Named<Item> LARGE_METAL_MUSKET_BALL = tag("large_metal_musket_ball");
		
		public static final Tag.Named<Item> SMALL_METAL_BUCKSHOT = tag("small_metal_buckshot");
		public static final Tag.Named<Item> MEDIUM_METAL_BUCKSHOT = tag("medium_metal_buckshot");
		public static final Tag.Named<Item> LARGE_METAL_BUCKSHOT = tag("large_metal_buckshot");
		
		public static final Tag.Named<Item> SMALL_METAL_BIRDSHOT = tag("small_metal_birdshot");
		public static final Tag.Named<Item> MEDIUM_METAL_BIRDSHOT = tag("medium_metal_birdshot");
		public static final Tag.Named<Item> LARGE_METAL_BIRDSHOT = tag("large_metal_birdshot");
		
		// Powder
		public static final Tag.Named<Item> ANY_GUNPOWDER = tag("any_gunpowder");
		public static final Tag.Named<Item> ANY_BLACK_POWDER = tag("any_black_powder");
		public static final Tag.Named<Item> MEDIUM_GRADE_BLACK_POWDER = tag("medium_grade_black_powder");
		public static final Tag.Named<Item> HIGH_GRADE_BLACK_POWDER = tag("high_grade_black_powder");
		public static final Tag.Named<Item> MATCHLOCK_SUITABLE_POWDER = tag("matchlock_suitable_powder");
		public static final Tag.Named<Item> WHEELLOCK_SUITABLE_POWDER = tag("wheellock_suitable_powder");
		public static final Tag.Named<Item> FLINTLOCK_SUITABLE_POWDER = tag("flintlock_suitable_powder");
		public static final Tag.Named<Item> CAPLOCK_SUITABLE_POWDER = tag("caplock_suitable_powder");
		public static final Tag.Named<Item> NEEDLEFIRE_SUITABLE_POWDER = tag("needlefire_suitable_powder");
		
		// Parts
		public static final Tag.Named<Item> FLINTLOCK_MECHANISM = tag("flintlock_mechanism");
		
		public static final Tag.Named<Item> TINY_METAL_BARREL = tag("tiny_metal_barrel");
		public static final Tag.Named<Item> SMALL_METAL_BARREL = tag("small_metal_barrel");
		public static final Tag.Named<Item> MEDIUM_METAL_BARREL = tag("medium_metal_barrel");
		public static final Tag.Named<Item> LARGE_METAL_BARREL = tag("large_metal_barrel");
		
		public static final Tag.Named<Item> SMALL_METAL_FLARED_BARREL = tag("small_metal_flared_barrel");
		public static final Tag.Named<Item> MEDIUM_METAL_FLARED_BARREL = tag("medium_metal_flared_barrel");
		public static final Tag.Named<Item> LARGE_METAL_FLARED_BARREL = tag("large_metal_flared_barrel");
		
		public static final Tag.Named<Item> SMALL_HANDLE = tag("small_handle");
		public static final Tag.Named<Item> MEDIUM_HANDLE = tag("medium_handle");
		public static final Tag.Named<Item> LARGE_HANDLE = tag("large_handle");

		public static final Tag.Named<Item> SMALL_STOCK = tag("small_stock");
		public static final Tag.Named<Item> MEDIUM_STOCK = tag("medium_stock");
		public static final Tag.Named<Item> LARGE_STOCK = tag("large_stock");
		
		// Forge Tags
		public static final Tag.Named<Item> NUGGETS_LEAD = forgeTag("nuggets/lead");
		public static final Tag.Named<Item> INGOTS_LEAD = forgeTag("ingots/lead");
		public static final Tag.Named<Item> NUGGETS_BRASS = forgeTag("nuggets/brass");
		public static final Tag.Named<Item> INGOTS_BRASS = forgeTag("ingots/brass");
		public static final Tag.Named<Item> DUST_NITER = forgeTag("dust/niter");
		public static final Tag.Named<Item> DUST_NITRE = forgeTag("dust/nitre");
		public static final Tag.Named<Item> DUST_SALTPETER = forgeTag("dust/saltpeter");
		public static final Tag.Named<Item> DUST_SULFUR = forgeTag("dust/sulfur");
		
		private static Tag.Named<Item> tag(final String name) {
			return ItemTags.bind(new ResourceLocation(OldGuns.MODID, name).toString());
		}
		
		private static Tag.Named<Item> forgeTag(final String name) {
			return ItemTags.bind(new ResourceLocation(ForgeVersion.MOD_ID, name).toString());
		}
	}
}
