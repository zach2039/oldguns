package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
	public static class Blocks {
		private static Tag.Named<Block> tag(final String name) {
			return BlockTags.bind(new ResourceLocation(OldGuns.MODID, name).toString());
		}
	}
	
	public static class Items {
		
		public static final Tag.Named<Item> SMALL_BARREL = tag("small_barrel");
		public static final Tag.Named<Item> MEDIUM_BARREL = tag("medium_barrel");
		public static final Tag.Named<Item> LARGE_BARREL = tag("large_barrel");
		
		public static final Tag.Named<Item> SMALL_HANDLE = tag("small_handle");
		public static final Tag.Named<Item> MEDIUM_HANDLE = tag("medium_handle");
		public static final Tag.Named<Item> LARGE_HANDLE = tag("large_handle");

		public static final Tag.Named<Item> SMALL_STOCK = tag("small_stock");
		public static final Tag.Named<Item> MEDIUM_STOCK = tag("medium_stock");
		public static final Tag.Named<Item> LARGE_STOCK = tag("large_stock");
		
		public static final Tag.Named<Item> FLINTLOCK_MECHANISM = tag("flintlock_mechanism");
		
		private static Tag.Named<Item> tag(final String name) {
			return ItemTags.bind(new ResourceLocation(OldGuns.MODID, name).toString());
		}
	}
}
