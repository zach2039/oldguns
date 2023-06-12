package com.zach2039.oldguns.data;

import javax.annotation.Nullable;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.init.ModBlocks;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.init.ModTags;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author zach2039
 */
public class OldGunsItemTagsProvider extends ItemTagsProvider {

	public OldGunsItemTagsProvider(
			final PackOutput output,
			final CompletableFuture<HolderLookup.Provider> lookupProvider,
			final TagsProvider<Block> blockTags,
			@Nullable final ExistingFileHelper existingFileHelper
	) {
		super(output, lookupProvider, blockTags, OldGuns.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(final HolderLookup.Provider provider) {
		
		// Artillery
		// Pieces
		
		// Ammo
		tag(ModTags.Items.SMALL_POWDER_CHARGE)
			.add(ModItems.SMALL_POWDER_CHARGE.get());
		
		tag(ModTags.Items.MEDIUM_POWDER_CHARGE)
			.add(ModItems.MEDIUM_POWDER_CHARGE.get());
		
		tag(ModTags.Items.LARGE_POWDER_CHARGE)
			.add(ModItems.LARGE_POWDER_CHARGE.get());
	
		// Parts
		tag(ModTags.Items.MEDIUM_METAL_CANNON_BARREL)
			.add(ModItems.LARGE_IRON_CANNON_BARREL.get());
		
		tag(ModTags.Items.SMALL_NAVAL_CARRIAGE)
			.add(ModItems.SMALL_WOODEN_NAVAL_CARRIAGE.get());
		
		tag(ModTags.Items.MEDIUM_NAVAL_CARRIAGE)
			.add(ModItems.MEDIUM_WOODEN_NAVAL_CARRIAGE.get());
		
		tag(ModTags.Items.LARGE_NAVAL_CARRIAGE)
			.add(ModItems.LARGE_WOODEN_NAVAL_CARRIAGE.get());
		
		tag(ModTags.Items.TINY_CARRIAGE_WHEEL)
			.add(ModItems.TINY_WOODEN_CARRIAGE_WHEEL.get());
		
		tag(ModTags.Items.SMALL_CARRIAGE_WHEEL)
			.add(ModItems.SMALL_WOODEN_CARRIAGE_WHEEL.get());
		
		tag(ModTags.Items.MEDIUM_CARRIAGE_WHEEL)
			.add(ModItems.MEDIUM_WOODEN_CARRIAGE_WHEEL.get());
		
		tag(ModTags.Items.LARGE_CARRIAGE_WHEEL)
			.add(ModItems.LARGE_WOODEN_CARRIAGE_WHEEL.get());
		
		// Firearms
		// Weapons
		tag(ModTags.Items.FIREARM)
			.add(ModItems.MATCHLOCK_DERRINGER.get())
			.add(ModItems.MATCHLOCK_PISTOL.get())
			.add(ModItems.MATCHLOCK_ARQUEBUS.get())
			.add(ModItems.MATCHLOCK_CALIVER.get())
			.add(ModItems.MATCHLOCK_MUSKETOON.get())
			.add(ModItems.MATCHLOCK_MUSKET.get())
			.add(ModItems.MATCHLOCK_LONG_MUSKET.get())
			.add(ModItems.MATCHLOCK_BLUNDERBUSS_PISTOL.get())
			.add(ModItems.MATCHLOCK_BLUNDERBUSS.get())
	
			.add(ModItems.WHEELLOCK_DERRINGER.get())
			.add(ModItems.WHEELLOCK_PISTOL.get())
			.add(ModItems.WHEELLOCK_DOUBLEBARREL_PISTOL.get())
			.add(ModItems.WHEELLOCK_ARQUEBUS.get())
			.add(ModItems.WHEELLOCK_CALIVER.get())
			.add(ModItems.WHEELLOCK_MUSKETOON.get())
			.add(ModItems.WHEELLOCK_MUSKET.get())
			.add(ModItems.WHEELLOCK_LONG_MUSKET.get())
			.add(ModItems.WHEELLOCK_BLUNDERBUSS_PISTOL.get())
			.add(ModItems.WHEELLOCK_BLUNDERBUSS.get())
			.add(ModItems.WHEELLOCK_HAND_MORTAR.get())
			
			.add(ModItems.FLINTLOCK_DERRINGER.get())
			.add(ModItems.FLINTLOCK_DUCKFOOT_DERRINGER.get())
			.add(ModItems.FLINTLOCK_PISTOL.get())
			.add(ModItems.FLINTLOCK_PEPPERBOX_PISTOL.get())
			.add(ModItems.FLINTLOCK_ARQUEBUS.get())
			.add(ModItems.FLINTLOCK_CALIVER.get())
			.add(ModItems.FLINTLOCK_MUSKETOON.get())
			.add(ModItems.FLINTLOCK_MUSKET.get())
			.add(ModItems.FLINTLOCK_NOCK_GUN.get())
			.add(ModItems.FLINTLOCK_LONG_MUSKET.get())
			.add(ModItems.FLINTLOCK_BLUNDERBUSS_PISTOL.get())
			.add(ModItems.FLINTLOCK_BLUNDERBUSS.get())
			.add(ModItems.FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS.get())
			
			.add(ModItems.CAPLOCK_DERRINGER.get())
			.add(ModItems.CAPLOCK_DUCKFOOT_DERRINGER.get())
			.add(ModItems.CAPLOCK_PISTOL.get())
			.add(ModItems.CAPLOCK_PEPPERBOX_PISTOL.get())
			.add(ModItems.CAPLOCK_ARQUEBUS.get())
			.add(ModItems.CAPLOCK_CALIVER.get())
			.add(ModItems.CAPLOCK_MUSKETOON.get())
			.add(ModItems.CAPLOCK_MUSKET.get())
			.add(ModItems.CAPLOCK_LONG_MUSKET.get())
			.add(ModItems.CAPLOCK_BLUNDERBUSS_PISTOL.get())
			.add(ModItems.CAPLOCK_BLUNDERBUSS.get())
			.add(ModItems.CAPLOCK_DOUBLEBARREL_BLUNDERBUSS.get())
			;
		
		tag(ModTags.Items.MATCHLOCK_FIREARM)
			.add(ModItems.MATCHLOCK_DERRINGER.get())
			.add(ModItems.MATCHLOCK_PISTOL.get())
			.add(ModItems.MATCHLOCK_BLUNDERBUSS_PISTOL.get())
			.add(ModItems.MATCHLOCK_ARQUEBUS.get())
			.add(ModItems.MATCHLOCK_CALIVER.get())
			.add(ModItems.MATCHLOCK_MUSKETOON.get())
			.add(ModItems.MATCHLOCK_MUSKET.get())
			.add(ModItems.MATCHLOCK_LONG_MUSKET.get())
			.add(ModItems.MATCHLOCK_BLUNDERBUSS.get())
			;
		
		tag(ModTags.Items.SMALL_MATCHLOCK_FIREARM)
			.add(ModItems.MATCHLOCK_DERRINGER.get())
			.add(ModItems.MATCHLOCK_PISTOL.get())
			.add(ModItems.MATCHLOCK_BLUNDERBUSS_PISTOL.get())
			.add(ModItems.MATCHLOCK_ARQUEBUS.get())
			;
		
		tag(ModTags.Items.MEDIUM_MATCHLOCK_FIREARM)
			.add(ModItems.MATCHLOCK_CALIVER.get())
			.add(ModItems.MATCHLOCK_MUSKETOON.get())
			;
		
		tag(ModTags.Items.LARGE_MATCHLOCK_FIREARM)
			.add(ModItems.MATCHLOCK_MUSKET.get())
			.add(ModItems.MATCHLOCK_LONG_MUSKET.get())
			.add(ModItems.MATCHLOCK_BLUNDERBUSS.get())
			;
			
		tag(ModTags.Items.WHEELLOCK_FIREARM)
			.add(ModItems.WHEELLOCK_DERRINGER.get())
			.add(ModItems.WHEELLOCK_PISTOL.get())
			.add(ModItems.WHEELLOCK_DOUBLEBARREL_PISTOL.get())
			.add(ModItems.WHEELLOCK_BLUNDERBUSS_PISTOL.get())
			.add(ModItems.WHEELLOCK_ARQUEBUS.get())
			.add(ModItems.WHEELLOCK_CALIVER.get())
			.add(ModItems.WHEELLOCK_MUSKETOON.get())
			.add(ModItems.WHEELLOCK_MUSKET.get())
			.add(ModItems.WHEELLOCK_LONG_MUSKET.get())
			.add(ModItems.WHEELLOCK_BLUNDERBUSS.get())
			.add(ModItems.WHEELLOCK_HAND_MORTAR.get())
			;
		
		tag(ModTags.Items.SMALL_WHEELLOCK_FIREARM)
			.add(ModItems.WHEELLOCK_DERRINGER.get())
			.add(ModItems.WHEELLOCK_PISTOL.get())
			.add(ModItems.WHEELLOCK_DOUBLEBARREL_PISTOL.get())
			.add(ModItems.WHEELLOCK_BLUNDERBUSS_PISTOL.get())
			.add(ModItems.WHEELLOCK_ARQUEBUS.get())
			;
		
		tag(ModTags.Items.MEDIUM_WHEELLOCK_FIREARM)
			.add(ModItems.WHEELLOCK_CALIVER.get())
			.add(ModItems.WHEELLOCK_MUSKETOON.get())
			;
			
		tag(ModTags.Items.LARGE_WHEELLOCK_FIREARM)
			.add(ModItems.WHEELLOCK_MUSKET.get())
			.add(ModItems.WHEELLOCK_LONG_MUSKET.get())
			.add(ModItems.WHEELLOCK_BLUNDERBUSS.get())
			;
		
		tag(ModTags.Items.HUGE_WHEELLOCK_FIREARM)
			.add(ModItems.WHEELLOCK_HAND_MORTAR.get())
			;
			
		tag(ModTags.Items.FLINTLOCK_FIREARM)	
			.add(ModItems.FLINTLOCK_DERRINGER.get())
			.add(ModItems.FLINTLOCK_DUCKFOOT_DERRINGER.get())
			.add(ModItems.FLINTLOCK_PISTOL.get())
			.add(ModItems.FLINTLOCK_PEPPERBOX_PISTOL.get())
			.add(ModItems.FLINTLOCK_BLUNDERBUSS_PISTOL.get())
			.add(ModItems.FLINTLOCK_ARQUEBUS.get())
			.add(ModItems.FLINTLOCK_CALIVER.get())
			.add(ModItems.FLINTLOCK_MUSKETOON.get())
			.add(ModItems.FLINTLOCK_MUSKET.get())
			.add(ModItems.FLINTLOCK_LONG_MUSKET.get())
			.add(ModItems.FLINTLOCK_BLUNDERBUSS.get())
			.add(ModItems.FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS.get())
			.add(ModItems.FLINTLOCK_NOCK_GUN.get())
			;
		
		tag(ModTags.Items.SMALL_FLINTLOCK_FIREARM)	
			.add(ModItems.FLINTLOCK_DERRINGER.get())
			.add(ModItems.FLINTLOCK_DUCKFOOT_DERRINGER.get())
			.add(ModItems.FLINTLOCK_PISTOL.get())
			.add(ModItems.FLINTLOCK_PEPPERBOX_PISTOL.get())
			.add(ModItems.FLINTLOCK_BLUNDERBUSS_PISTOL.get())
			;
		
		tag(ModTags.Items.MEDIUM_FLINTLOCK_FIREARM)
			.add(ModItems.FLINTLOCK_ARQUEBUS.get())
			.add(ModItems.FLINTLOCK_CALIVER.get())
			.add(ModItems.FLINTLOCK_MUSKETOON.get())
			;
		
		tag(ModTags.Items.LARGE_FLINTLOCK_FIREARM)
			.add(ModItems.FLINTLOCK_MUSKET.get())
			.add(ModItems.FLINTLOCK_LONG_MUSKET.get())
			.add(ModItems.FLINTLOCK_BLUNDERBUSS.get())
			.add(ModItems.FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS.get())
			;
		
		tag(ModTags.Items.HUGE_FLINTLOCK_FIREARM)
			.add(ModItems.FLINTLOCK_NOCK_GUN.get())
			;
			
		tag(ModTags.Items.CAPLOCK_FIREARM)	
			.add(ModItems.CAPLOCK_DERRINGER.get())
			.add(ModItems.CAPLOCK_DUCKFOOT_DERRINGER.get())
			.add(ModItems.CAPLOCK_PISTOL.get())
			.add(ModItems.CAPLOCK_PEPPERBOX_PISTOL.get())
			.add(ModItems.CAPLOCK_BLUNDERBUSS_PISTOL.get())
			.add(ModItems.CAPLOCK_ARQUEBUS.get())
			.add(ModItems.CAPLOCK_CALIVER.get())
			.add(ModItems.CAPLOCK_MUSKETOON.get())
			.add(ModItems.CAPLOCK_MUSKET.get())
			.add(ModItems.CAPLOCK_LONG_MUSKET.get())
			.add(ModItems.CAPLOCK_BLUNDERBUSS.get())
			.add(ModItems.CAPLOCK_DOUBLEBARREL_BLUNDERBUSS.get())
			;
	
	tag(ModTags.Items.SMALL_CAPLOCK_FIREARM)	
		.add(ModItems.CAPLOCK_DERRINGER.get())
		.add(ModItems.CAPLOCK_DUCKFOOT_DERRINGER.get())
		.add(ModItems.CAPLOCK_PISTOL.get())
		.add(ModItems.CAPLOCK_PEPPERBOX_PISTOL.get())
		.add(ModItems.CAPLOCK_BLUNDERBUSS_PISTOL.get())
		;
	
	tag(ModTags.Items.MEDIUM_CAPLOCK_FIREARM)
		.add(ModItems.CAPLOCK_ARQUEBUS.get())
		.add(ModItems.CAPLOCK_CALIVER.get())
		.add(ModItems.CAPLOCK_MUSKETOON.get())
		;
	
	tag(ModTags.Items.LARGE_CAPLOCK_FIREARM)
		.add(ModItems.CAPLOCK_MUSKET.get())
		.add(ModItems.CAPLOCK_LONG_MUSKET.get())
		.add(ModItems.CAPLOCK_BLUNDERBUSS.get())
		.add(ModItems.CAPLOCK_DOUBLEBARREL_BLUNDERBUSS.get())
		;
		
		// Ammo		
		tag(ModTags.Items.MEDIUM_METAL_CANNONBALL)
			.add(ModItems.MEDIUM_IRON_CANNONBALL.get())
			;
		
		tag(ModTags.Items.MEDIUM_METAL_CANISTER_SHOT)
			.add(ModItems.MEDIUM_IRON_CANISTER_SHOT.get())
			;
	
		tag(ModTags.Items.MEDIUM_METAL_GRAPESHOT)
			.add(ModItems.MEDIUM_IRON_GRAPESHOT.get())
			;
		
		tag(ModTags.Items.MEDIUM_METAL_EXPLOSIVE_SHELL)
			.add(ModItems.MEDIUM_IRON_EXPLOSIVE_SHELL.get())
			;
		
		tag(ModTags.Items.SMALL_ROCK_MUSKET_BALL)
			.add(ModItems.SMALL_STONE_MUSKET_BALL.get())
			;
	
		tag(ModTags.Items.MEDIUM_ROCK_MUSKET_BALL)
			.add(ModItems.MEDIUM_STONE_MUSKET_BALL.get());
	
		tag(ModTags.Items.LARGE_ROCK_MUSKET_BALL)
			.add(ModItems.LARGE_STONE_MUSKET_BALL.get());
	
		tag(ModTags.Items.SMALL_ROCK_BIRDSHOT)
			.add(ModItems.SMALL_STONE_BIRDSHOT.get());

		tag(ModTags.Items.MEDIUM_ROCK_BIRDSHOT)
			.add(ModItems.MEDIUM_STONE_BIRDSHOT.get());

		tag(ModTags.Items.LARGE_ROCK_BIRDSHOT)
			.add(ModItems.LARGE_STONE_BIRDSHOT.get());
		
		tag(ModTags.Items.SMALL_METAL_MUSKET_BALL)
			.add(ModItems.SMALL_LEAD_MUSKET_BALL.get())
			.add(ModItems.SMALL_IRON_MUSKET_BALL.get());
		
		tag(ModTags.Items.MEDIUM_METAL_MUSKET_BALL)
			.add(ModItems.MEDIUM_LEAD_MUSKET_BALL.get())
			.add(ModItems.MEDIUM_IRON_MUSKET_BALL.get());
		
		tag(ModTags.Items.LARGE_METAL_MUSKET_BALL)
			.add(ModItems.LARGE_LEAD_MUSKET_BALL.get())
			.add(ModItems.LARGE_IRON_MUSKET_BALL.get());
		
		tag(ModTags.Items.SMALL_METAL_BUCKSHOT)
			.add(ModItems.SMALL_LEAD_BUCKSHOT.get())
			.add(ModItems.SMALL_IRON_BUCKSHOT.get());

		tag(ModTags.Items.MEDIUM_METAL_BUCKSHOT)
			.add(ModItems.MEDIUM_LEAD_BUCKSHOT.get())
			.add(ModItems.MEDIUM_IRON_BUCKSHOT.get());

		tag(ModTags.Items.LARGE_METAL_BUCKSHOT)
			.add(ModItems.LARGE_LEAD_BUCKSHOT.get())
			.add(ModItems.LARGE_IRON_BUCKSHOT.get());
		
		tag(ModTags.Items.SMALL_METAL_BIRDSHOT)
			.add(ModItems.SMALL_LEAD_BIRDSHOT.get())
			.add(ModItems.SMALL_IRON_BIRDSHOT.get());

		tag(ModTags.Items.MEDIUM_METAL_BIRDSHOT)
			.add(ModItems.MEDIUM_LEAD_BIRDSHOT.get())
			.add(ModItems.MEDIUM_IRON_BIRDSHOT.get());

		tag(ModTags.Items.LARGE_METAL_BIRDSHOT)
			.add(ModItems.LARGE_LEAD_BIRDSHOT.get())
			.add(ModItems.LARGE_IRON_BIRDSHOT.get());		
		
		tag(ModTags.Items.MEDIUM_METAL_CANNONBALL)
			.add(ModItems.MEDIUM_IRON_CANNONBALL.get())
			;	
		
		tag(ModTags.Items.MEDIUM_METAL_CANISTER_SHOT)
			.add(ModItems.MEDIUM_IRON_CANISTER_SHOT.get())
			;	
		
		// Cartridge
		tag(ModTags.Items.SMALL_MATCHLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE)
			.add(ModItems.SMALL_STONE_MUSKET_BALL_LOW_GRADE_PAPER_CARTRIDGE.get())
			.add(ModItems.SMALL_STONE_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get())
			;
		
		tag(ModTags.Items.MEDIUM_MATCHLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE)
			.add(ModItems.MEDIUM_STONE_MUSKET_BALL_LOW_GRADE_PAPER_CARTRIDGE.get())
			.add(ModItems.MEDIUM_STONE_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get())
			;
		
		tag(ModTags.Items.LARGE_MATCHLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE)
			.add(ModItems.LARGE_STONE_MUSKET_BALL_LOW_GRADE_PAPER_CARTRIDGE.get())
			.add(ModItems.LARGE_STONE_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get())
			;
		
		tag(ModTags.Items.SMALL_WHEELLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE)
			.add(ModItems.SMALL_IRON_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get())
			.add(ModItems.SMALL_IRON_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get())
			.add(ModItems.SMALL_LEAD_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get())
			.add(ModItems.SMALL_LEAD_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get())
			;
		
		tag(ModTags.Items.MEDIUM_WHEELLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE)
			.add(ModItems.MEDIUM_IRON_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get())
			.add(ModItems.MEDIUM_IRON_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get())
			.add(ModItems.MEDIUM_LEAD_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get())
			.add(ModItems.MEDIUM_LEAD_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get())
			;
		
		tag(ModTags.Items.LARGE_WHEELLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE)
			.add(ModItems.LARGE_IRON_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get())
			.add(ModItems.LARGE_IRON_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get())
			.add(ModItems.LARGE_LEAD_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get())
			.add(ModItems.LARGE_LEAD_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get())
			;
		
		tag(ModTags.Items.SMALL_FLINTLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE)
			.add(ModItems.SMALL_IRON_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get())
			.add(ModItems.SMALL_LEAD_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get())
			;
		
		tag(ModTags.Items.MEDIUM_FLINTLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE)
			.add(ModItems.MEDIUM_IRON_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get())
			.add(ModItems.MEDIUM_LEAD_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get())
			;
		
		tag(ModTags.Items.LARGE_FLINTLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE)
			.add(ModItems.LARGE_IRON_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get())
			.add(ModItems.LARGE_LEAD_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get())
			;
		
		tag(ModTags.Items.SMALL_CAPLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE)
			.add(ModItems.SMALL_IRON_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get())
			.add(ModItems.SMALL_LEAD_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get())
			;
		
		tag(ModTags.Items.MEDIUM_CAPLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE)
			.add(ModItems.MEDIUM_IRON_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get())
			.add(ModItems.MEDIUM_LEAD_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get())
			;
		
		tag(ModTags.Items.LARGE_CAPLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE)
			.add(ModItems.LARGE_IRON_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get())
			.add(ModItems.LARGE_LEAD_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get())
			;
		
		// General ammunition
		tag(ModTags.Items.ANY_BULLET)
			.addTag(ModTags.Items.SMALL_ROCK_MUSKET_BALL)
			.addTag(ModTags.Items.SMALL_ROCK_BIRDSHOT)			
			.addTag(ModTags.Items.MEDIUM_ROCK_MUSKET_BALL)
			.addTag(ModTags.Items.MEDIUM_ROCK_BIRDSHOT)
			.addTag(ModTags.Items.LARGE_ROCK_MUSKET_BALL)
			.addTag(ModTags.Items.LARGE_ROCK_BIRDSHOT)
			.addTag(ModTags.Items.SMALL_METAL_MUSKET_BALL)
			.addTag(ModTags.Items.SMALL_METAL_BIRDSHOT)
			.addTag(ModTags.Items.SMALL_METAL_BUCKSHOT)
			//.addTag(ModTags.Items.SMALL_METAL_CANNONBALL)
			//.addTag(ModTags.Items.SMALL_METAL_CANISTER_SHOT)
			//.addTag(ModTags.Items.SMALL_METAL_GRAPESHOT)
			//.addTag(ModTags.Items.SMALL_METAL_EXPLOSIVE_SHELL)
			.addTag(ModTags.Items.MEDIUM_METAL_MUSKET_BALL)
			.addTag(ModTags.Items.MEDIUM_METAL_BIRDSHOT)
			.addTag(ModTags.Items.MEDIUM_METAL_BUCKSHOT)
			.addTag(ModTags.Items.MEDIUM_METAL_CANNONBALL)
			.addTag(ModTags.Items.MEDIUM_METAL_CANISTER_SHOT)
			.addTag(ModTags.Items.MEDIUM_METAL_GRAPESHOT)
			.addTag(ModTags.Items.MEDIUM_METAL_EXPLOSIVE_SHELL)
			.addTag(ModTags.Items.LARGE_METAL_MUSKET_BALL)
			.addTag(ModTags.Items.LARGE_METAL_BIRDSHOT)
			.addTag(ModTags.Items.LARGE_METAL_BUCKSHOT)
			//.addTag(ModTags.Items.LARGE_METAL_CANNONBALL)
			//.addTag(ModTags.Items.LARGE_METAL_CANISTER_SHOT)
			//.addTag(ModTags.Items.LARGE_METAL_GRAPESHOT)
			//.addTag(ModTags.Items.LARGE_METAL_EXPLOSIVE_SHELL)
			;
		
		tag(ModTags.Items.ANY_FIREARM_BULLET)
			.addTag(ModTags.Items.SMALL_ROCK_MUSKET_BALL)
			.addTag(ModTags.Items.SMALL_ROCK_BIRDSHOT)			
			.addTag(ModTags.Items.MEDIUM_ROCK_MUSKET_BALL)
			.addTag(ModTags.Items.MEDIUM_ROCK_BIRDSHOT)
			.addTag(ModTags.Items.LARGE_ROCK_MUSKET_BALL)
			.addTag(ModTags.Items.LARGE_ROCK_BIRDSHOT)
			.addTag(ModTags.Items.SMALL_METAL_MUSKET_BALL)
			.addTag(ModTags.Items.SMALL_METAL_BIRDSHOT)
			.addTag(ModTags.Items.SMALL_METAL_BUCKSHOT)
			.addTag(ModTags.Items.MEDIUM_METAL_MUSKET_BALL)
			.addTag(ModTags.Items.MEDIUM_METAL_BIRDSHOT)
			.addTag(ModTags.Items.MEDIUM_METAL_BUCKSHOT)
			.addTag(ModTags.Items.LARGE_METAL_MUSKET_BALL)
			.addTag(ModTags.Items.LARGE_METAL_BIRDSHOT)
			.addTag(ModTags.Items.LARGE_METAL_BUCKSHOT)
			;
		
		tag(ModTags.Items.ANY_FIREARM_CARTRIDGE)
			.addTag(ModTags.Items.SMALL_MATCHLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE)
			.addTag(ModTags.Items.MEDIUM_MATCHLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE)
			.addTag(ModTags.Items.LARGE_MATCHLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE)
			.addTag(ModTags.Items.SMALL_WHEELLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE)
			.addTag(ModTags.Items.MEDIUM_WHEELLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE)
			.addTag(ModTags.Items.LARGE_WHEELLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE)
			.addTag(ModTags.Items.SMALL_FLINTLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE)
			.addTag(ModTags.Items.MEDIUM_FLINTLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE)
			.addTag(ModTags.Items.LARGE_FLINTLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE) 
			.addTag(ModTags.Items.SMALL_CAPLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE)
			.addTag(ModTags.Items.MEDIUM_CAPLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE)
			.addTag(ModTags.Items.LARGE_CAPLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE)
			;
		
		// Reloading
		tag(ModTags.Items.PERCUSSION_CAP)
			.add(ModItems.PERCUSSION_CAP.get());
		
		// Powder
		tag(ModTags.Items.ANY_GUNPOWDER)
			.add(Items.GUNPOWDER)
			.add(ModItems.HIGH_GRADE_BLACK_POWDER.get())
			.add(ModItems.MEDIUM_GRADE_BLACK_POWDER.get());
		
		tag(ModTags.Items.ANY_GUNPOWDER_BLOCK)
			.add(ModBlocks.LOW_GRADE_BLACK_POWDER_BLOCK.get().asItem())
			.add(ModBlocks.MEDIUM_GRADE_BLACK_POWDER_BLOCK.get().asItem())
			.add(ModBlocks.HIGH_GRADE_BLACK_POWDER_BLOCK.get().asItem())
			;
		
		tag(ModTags.Items.ANY_BLACK_POWDER)
			.add(ModItems.HIGH_GRADE_BLACK_POWDER.get())
			.add(ModItems.MEDIUM_GRADE_BLACK_POWDER.get());
		
		tag(ModTags.Items.HIGH_GRADE_BLACK_POWDER)
			.add(ModItems.HIGH_GRADE_BLACK_POWDER.get());
		
		tag(ModTags.Items.MEDIUM_GRADE_BLACK_POWDER)
			.add(ModItems.MEDIUM_GRADE_BLACK_POWDER.get());
		
		tag(ModTags.Items.LOW_GRADE_BLACK_POWDER)
			.add(Items.GUNPOWDER);
		
		tag(ModTags.Items.MATCHLOCK_SUITABLE_POWDER)
			.add(ModItems.MEDIUM_GRADE_BLACK_POWDER.get())
			.add(Items.GUNPOWDER);
		
		tag(ModTags.Items.WHEELLOCK_SUITABLE_POWDER)
			.add(ModItems.HIGH_GRADE_BLACK_POWDER.get())
			.add(ModItems.MEDIUM_GRADE_BLACK_POWDER.get());
		
		tag(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
			.add(ModItems.HIGH_GRADE_BLACK_POWDER.get())
			.add(ModItems.MEDIUM_GRADE_BLACK_POWDER.get());
	
		tag(ModTags.Items.CAPLOCK_SUITABLE_POWDER)
			.add(ModItems.HIGH_GRADE_BLACK_POWDER.get());
		
		tag(ModTags.Items.RIMFIRE_SUITABLE_POWDER)
			.add(ModItems.HIGH_GRADE_BLACK_POWDER.get());
		
		// Parts
		tag(ModTags.Items.MATCHLOCK_MECHANISM)
			.add(ModItems.MATCHLOCK_MECHANISM.get());
		
		tag(ModTags.Items.WHEELLOCK_MECHANISM)
			.add(ModItems.WHEELLOCK_MECHANISM.get());
		
		tag(ModTags.Items.FLINTLOCK_MECHANISM)
			.add(ModItems.FLINTLOCK_MECHANISM.get());
		
		tag(ModTags.Items.CAPLOCK_MECHANISM)
			.add(ModItems.CAPLOCK_MECHANISM.get());
		
		tag(ModTags.Items.TINY_ROCK_BARREL)
			.add(ModItems.TINY_STONE_BARREL.get());		
	
		tag(ModTags.Items.SMALL_ROCK_BARREL)
			.add(ModItems.SMALL_STONE_BARREL.get());

		tag(ModTags.Items.MEDIUM_ROCK_BARREL)
			.add(ModItems.MEDIUM_STONE_BARREL.get());
	
		tag(ModTags.Items.LARGE_ROCK_BARREL)
			.add(ModItems.LARGE_STONE_BARREL.get());
	
		tag(ModTags.Items.SMALL_ROCK_FLARED_BARREL)
			.add(ModItems.SMALL_STONE_FLARED_BARREL.get());

		tag(ModTags.Items.MEDIUM_ROCK_FLARED_BARREL)
			.add(ModItems.MEDIUM_STONE_FLARED_BARREL.get());
		
		tag(ModTags.Items.LARGE_ROCK_FLARED_BARREL)
			.add(ModItems.LARGE_STONE_FLARED_BARREL.get());
		
		tag(ModTags.Items.TINY_METAL_BARREL)
			.add(ModItems.TINY_BRASS_BARREL.get())
			.add(ModItems.TINY_IRON_BARREL.get());		
		
		tag(ModTags.Items.SMALL_METAL_BARREL)
			.add(ModItems.SMALL_BRASS_BARREL.get())
			.add(ModItems.SMALL_IRON_BARREL.get());

		tag(ModTags.Items.MEDIUM_METAL_BARREL)
			.add(ModItems.MEDIUM_BRASS_BARREL.get())
			.add(ModItems.MEDIUM_IRON_BARREL.get());
		
		tag(ModTags.Items.LARGE_METAL_BARREL)
			.add(ModItems.LARGE_BRASS_BARREL.get())
			.add(ModItems.LARGE_IRON_BARREL.get());
		
		tag(ModTags.Items.SMALL_METAL_FLARED_BARREL)
			.add(ModItems.SMALL_BRASS_FLARED_BARREL.get())
			.add(ModItems.SMALL_IRON_FLARED_BARREL.get());
		
		tag(ModTags.Items.MEDIUM_METAL_FLARED_BARREL)
			.add(ModItems.MEDIUM_BRASS_FLARED_BARREL.get())
			.add(ModItems.MEDIUM_IRON_FLARED_BARREL.get());
		
		tag(ModTags.Items.LARGE_METAL_FLARED_BARREL)
			.add(ModItems.LARGE_BRASS_FLARED_BARREL.get())
			.add(ModItems.LARGE_IRON_FLARED_BARREL.get());
		
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
		
		tag(ModTags.Items.WOOD_GEAR_SET)
			.add(ModItems.WOOD_GEAR_SET.get());
		
		tag(ModTags.Items.IRON_GEAR_SET)
			.add(ModItems.IRON_GEAR_SET.get());
		
		tag(ModTags.Items.GOLD_GEAR_SET)
			.add(ModItems.GOLD_GEAR_SET.get());
		
		tag(ModTags.Items.DIAMOND_GEAR_SET)
			.add(ModItems.DIAMOND_GEAR_SET.get());
		
		tag(ModTags.Items.WOOD_TRIGGER_ASSEMBLY)
			.add(ModItems.WOOD_TRIGGER_ASSEMBLY.get());
	
		tag(ModTags.Items.IRON_TRIGGER_ASSEMBLY)
			.add(ModItems.IRON_TRIGGER_ASSEMBLY.get());
		
		tag(ModTags.Items.GOLD_TRIGGER_ASSEMBLY)
			.add(ModItems.GOLD_TRIGGER_ASSEMBLY.get());
		
		tag(ModTags.Items.DIAMOND_TRIGGER_ASSEMBLY)
			.add(ModItems.DIAMOND_TRIGGER_ASSEMBLY.get());
		
		tag(ModTags.Items.PERCUSSION_CAP_CONE)
			.add(ModItems.PERCUSSION_CAP_CONE.get());
		
		tag(ModTags.Items.MATCH_CORD)
			.add(ModItems.MATCH_CORD.get());
		
		// Materials
		tag(ModTags.Items.INGOTS_LEAD)
			.add(ModItems.LEAD_INGOT.get());
		
		tag(ModTags.Items.NUGGETS_LEAD)
			.add(ModItems.LEAD_NUGGET.get());
		
		tag(ModTags.Items.INGOTS_BRASS)
			.add(ModItems.BRASS_INGOT.get());

		tag(ModTags.Items.NUGGETS_BRASS)
			.add(ModItems.BRASS_NUGGET.get());
		
		tag(ModTags.Items.INGOTS_MERCURY)
			.add(ModItems.MERCURY_INGOT.get());
	
		tag(ModTags.Items.NUGGETS_MERCURY)
			.add(ModItems.MERCURY_NUGGET.get());
		
		tag(ModTags.Items.INGOTS_QUICKSILVER)
			.add(ModItems.MERCURY_INGOT.get());
	
		tag(ModTags.Items.NUGGETS_QUICKSILVER)
			.add(ModItems.MERCURY_NUGGET.get());
		
		tag(ModTags.Items.DUST_NITER)
			.add(ModItems.NITER.get());
		
		tag(ModTags.Items.DUST_NITRE)
			.add(ModItems.NITER.get());
		
		tag(ModTags.Items.DUST_SALTPETER)
			.add(ModItems.NITER.get());
		
		tag(ModTags.Items.DUST_SULFUR)
			.add(ModItems.SULFUR.get());
	}
}
