package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.versions.forge.ForgeVersion;

public class ModTags {
	public static class Blocks {
		private static Tag.Named<Block> tag(final String name) {
			return BlockTags.bind(new ResourceLocation(OldGuns.MODID, name).toString());
		}
	}
	
	public static class Items {
		
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
		
		// Parts
		public static final Tag.Named<Item> FLINTLOCK_MECHANISM = tag("flintlock_mechanism");
		
		public static final Tag.Named<Item> SMALL_METAL_BARREL = tag("small_metal_barrel");
		public static final Tag.Named<Item> MEDIUM_METAL_BARREL = tag("medium_metal_barrel");
		public static final Tag.Named<Item> LARGE_METAL_BARREL = tag("large_metal_barrel");
		public static final Tag.Named<Item> HUGE_METAL_BARREL = tag("huge_metal_barrel");
		
		public static final Tag.Named<Item> SMALL_HANDLE = tag("small_handle");
		public static final Tag.Named<Item> MEDIUM_HANDLE = tag("medium_handle");
		public static final Tag.Named<Item> LARGE_HANDLE = tag("large_handle");

		public static final Tag.Named<Item> SMALL_STOCK = tag("small_stock");
		public static final Tag.Named<Item> MEDIUM_STOCK = tag("medium_stock");
		public static final Tag.Named<Item> LARGE_STOCK = tag("large_stock");
		
		// Forge Tags
		public static final Tag.Named<Item> NUGGETS_LEAD = forgeTag("nuggets/lead");
		public static final Tag.Named<Item> INGOTS_LEAD = forgeTag("ingots/lead");
		
		private static Tag.Named<Item> tag(final String name) {
			return ItemTags.bind(new ResourceLocation(OldGuns.MODID, name).toString());
		}
		
		private static Tag.Named<Item> forgeTag(final String name) {
			return ItemTags.bind(new ResourceLocation(ForgeVersion.MOD_ID, name).toString());
		}
	}
}
