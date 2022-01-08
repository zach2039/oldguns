package com.zach2039.oldguns.data;

import javax.annotation.Nullable;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.init.ModTags;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class OldGunsItemTagsProvider extends ItemTagsProvider {
	
	public OldGunsItemTagsProvider(final DataGenerator dataGenerator, final BlockTagsProvider blockTagProvider, @Nullable final ExistingFileHelper existingFileHelper) {
		super(dataGenerator, blockTagProvider, OldGuns.MODID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		
		// Ammo
		tag(ModTags.Items.SMALL_METAL_MUSKET_BALL)
				.add(ModItems.SMALL_IRON_MUSKET_BALL.get());
		
		tag(ModTags.Items.MEDIUM_METAL_MUSKET_BALL)
				.add(ModItems.MEDIUM_IRON_MUSKET_BALL.get());
		
		tag(ModTags.Items.LARGE_METAL_MUSKET_BALL)
				.add(ModItems.LARGE_IRON_MUSKET_BALL.get());
		
		tag(ModTags.Items.SMALL_METAL_BUCKSHOT)
				.add(ModItems.SMALL_IRON_BUCKSHOT.get());

		tag(ModTags.Items.MEDIUM_METAL_BUCKSHOT)
				.add(ModItems.MEDIUM_IRON_BUCKSHOT.get());

		tag(ModTags.Items.LARGE_METAL_BUCKSHOT)
				.add(ModItems.LARGE_IRON_BUCKSHOT.get());
		
		tag(ModTags.Items.SMALL_METAL_BIRDSHOT)
				.add(ModItems.SMALL_IRON_BIRDSHOT.get());

		tag(ModTags.Items.MEDIUM_METAL_BIRDSHOT)
				.add(ModItems.MEDIUM_IRON_BIRDSHOT.get());

		tag(ModTags.Items.LARGE_METAL_BIRDSHOT)
				.add(ModItems.LARGE_IRON_BIRDSHOT.get());
		
		// Parts
		tag(ModTags.Items.FLINTLOCK_MECHANISM)
				.add(ModItems.FLINTLOCK_MECHANISM.get());
		
		tag(ModTags.Items.SMALL_METAL_BARREL)
				.add(ModItems.SMALL_IRON_BARREL.get());

		tag(ModTags.Items.MEDIUM_METAL_BARREL)
				.add(ModItems.MEDIUM_IRON_BARREL.get());
		
		tag(ModTags.Items.LARGE_METAL_BARREL)
				.add(ModItems.LARGE_IRON_BARREL.get());
		
		tag(ModTags.Items.HUGE_METAL_BARREL)
				.add(ModItems.HUGE_IRON_BARREL.get());
		
		tag(ModTags.Items.SMALL_HANDLE)
				.add(ModItems.SMALL_WOODEN_HANDLE.get());
		
		tag(ModTags.Items.MEDIUM_HANDLE)
				.add(ModItems.MEDIUM_WOODEN_HANDLE.get());
		
		tag(ModTags.Items.LARGE_HANDLE)
				.add(ModItems.LARGE_WOODEN_HANDLE.get());
		
		tag(ModTags.Items.SMALL_STOCK)
				.add(ModItems.SMALL_WOODEN_STOCK.get());

		tag(ModTags.Items.MEDIUM_STOCK)
				.add(ModItems.MEDIUM_WOODEN_STOCK.get());
		
		tag(ModTags.Items.LARGE_STOCK)
				.add(ModItems.LARGE_WOODEN_STOCK.get());
		
	}
}
