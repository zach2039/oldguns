package com.zach2039.oldguns.data;

import java.util.function.Consumer;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.data.crafting.recipe.ShapedGunsmithsBenchRecipeBuilder;
import com.zach2039.oldguns.data.crafting.recipe.ShapelessFirearmMuzzleloaderReloadRecipeBuilder;
import com.zach2039.oldguns.data.crafting.recipe.ShapelessFirearmRepairRecipeBuilder;
import com.zach2039.oldguns.data.crafting.recipe.ShapelessGunsmithsBenchFirearmRepairRecipeBuilder;
import com.zach2039.oldguns.data.crafting.recipe.ShapelessGunsmithsBenchMortarAndPestleRecipeBuilder;
import com.zach2039.oldguns.data.crafting.recipe.ShapelessGunsmithsBenchRecipeBuilder;
import com.zach2039.oldguns.data.crafting.recipe.ShapelessVanillaMortarAndPestleRecipeBuilder;
import com.zach2039.oldguns.init.ModBlocks;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.init.ModTags;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author zach2039
 */
public class OldGunsRecipeProvider extends RecipeProvider {
	
	public OldGunsRecipeProvider(final DataGenerator dataGenerator) {
		super(dataGenerator);
	}

	@Override
	protected void buildCraftingRecipes(final Consumer<FinishedRecipe> recipeConsumer) {

		// Materials
		// Lead ingots from nuggets
		{
			ShapelessRecipeBuilder.shapeless(ModItems.LEAD_INGOT.get())
					.requires(ModItems.LEAD_NUGGET.get(), 9)
					.unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE))
					.unlockedBy("has_lead_nugget", has(ModTags.Items.NUGGETS_LEAD))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "lead_ingot_vanilla"));

			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.LEAD_INGOT.get())
					.requires(ModItems.LEAD_NUGGET.get(), 9)
					.unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE))
					.unlockedBy("has_lead_nugget", has(ModTags.Items.NUGGETS_LEAD))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "lead_ingot"));
		}
		
		// Brass ingots from nuggets
		{
			ShapelessRecipeBuilder.shapeless(ModItems.BRASS_INGOT.get())
					.requires(ModItems.BRASS_NUGGET.get(), 9)
					.unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE))
					.unlockedBy("has_brass_nugget", has(ModTags.Items.NUGGETS_BRASS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "brass_ingot_vanilla"));
			
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.BRASS_INGOT.get())
					.requires(ModItems.BRASS_NUGGET.get(), 9)
					.unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE))
					.unlockedBy("has_brass_nugget", has(ModTags.Items.NUGGETS_BRASS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "brass_ingot"));
		}
		
		// Lead nuggets from ingots
		{
			ShapelessRecipeBuilder.shapeless(ModItems.LEAD_NUGGET.get(), 9)
					.requires(ModTags.Items.INGOTS_LEAD)
					.unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE))
					.unlockedBy("has_lead_ingot", has(ModTags.Items.INGOTS_LEAD))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "lead_nugget_vanilla"));
			
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.LEAD_NUGGET.get(), 9)
					.requires(ModTags.Items.INGOTS_LEAD)
					.unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE))
					.unlockedBy("has_lead_ingot", has(ModTags.Items.INGOTS_LEAD))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "lead_nugget"));
		}
		
		// Brass nuggets from ingots
		{
			ShapelessRecipeBuilder.shapeless(ModItems.BRASS_NUGGET.get(), 9)
					.requires(ModTags.Items.INGOTS_BRASS)
					.unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE))
					.unlockedBy("has_brass_ingot", has(ModTags.Items.INGOTS_BRASS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "brass_nugget_vanilla"));
			
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.BRASS_NUGGET.get(), 9)
					.requires(ModTags.Items.INGOTS_BRASS)
					.unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE))
					.unlockedBy("has_brass_ingot", has(ModTags.Items.INGOTS_BRASS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "brass_nugget"));
		}

		// Nitre Bedding
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModBlocks.NITER_BEDDING.get(), 4)
					.pattern("CDC")
					.pattern("DWD")
					.pattern("CDC")
					.define('W', Tags.Items.CROPS_WHEAT)
					.define('C', Items.CLAY_BALL)
					.define('D', ItemTags.DIRT)	
					.unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "niter_bedding"));
		}
		
		// Sulfur from netherrack and netherquartz
		{
			ShapelessVanillaMortarAndPestleRecipeBuilder.shapeless(ModItems.SULFUR.get(), 1)
					.requires(Tags.Items.NETHERRACK)
					.requires(Tags.Items.NETHERRACK)
					.requires(Tags.Items.NETHERRACK)
					.requires(ModItems.MORTAR_AND_PESTLE.get())
					.unlockedBy("has_netherrack", has(Tags.Items.NETHERRACK))
					.unlockedBy("has_mortar_and_pestle", has(ModItems.MORTAR_AND_PESTLE.get()))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "sulfur_from_netherrack_vanilla"));
			
			ShapelessGunsmithsBenchMortarAndPestleRecipeBuilder.shapeless(ModItems.SULFUR.get(), 1)
					.requires(Tags.Items.NETHERRACK)
					.requires(Tags.Items.NETHERRACK)
					.requires(Tags.Items.NETHERRACK)
					.requires(ModItems.MORTAR_AND_PESTLE.get())
					.unlockedBy("has_netherrack", has(Tags.Items.NETHERRACK))
					.unlockedBy("has_mortar_and_pestle", has(ModItems.MORTAR_AND_PESTLE.get()))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "sulfur_from_netherrack"));
		}
		{
			ShapelessVanillaMortarAndPestleRecipeBuilder.shapeless(ModItems.SULFUR.get(), 2)
					.requires(Tags.Items.GEMS_QUARTZ)
					.requires(ModItems.MORTAR_AND_PESTLE.get())
					.unlockedBy("has_gem_quartz", has(Tags.Items.GEMS_QUARTZ))
					.unlockedBy("has_mortar_and_pestle", has(ModItems.MORTAR_AND_PESTLE.get()))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "sulfur_from_quartz_vanilla"));
			
			ShapelessGunsmithsBenchMortarAndPestleRecipeBuilder.shapeless(ModItems.SULFUR.get(), 2)
					.requires(Tags.Items.GEMS_QUARTZ)
					.requires(ModItems.MORTAR_AND_PESTLE.get())
					.unlockedBy("has_gem_quartz", has(Tags.Items.GEMS_QUARTZ))
					.unlockedBy("has_mortar_and_pestle", has(ModItems.MORTAR_AND_PESTLE.get()))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "sulfur_from_quartz"));
		}
				
		// Medium-grade black powder from niter, sulfur, and charcoal
		{
			ShapelessVanillaMortarAndPestleRecipeBuilder.shapeless(ModItems.MEDIUM_GRADE_BLACK_POWDER.get(), 3)
					.requires(ModTags.Items.DUST_SALTPETER)
					.requires(ModTags.Items.DUST_SALTPETER)
					.requires(ModTags.Items.DUST_SULFUR)					
					.requires(Items.CHARCOAL)
					.requires(ModItems.MORTAR_AND_PESTLE.get())
					.unlockedBy("has_saltpeter", has(ModTags.Items.DUST_SALTPETER))
					.unlockedBy("has_sulfur", has(ModTags.Items.DUST_SULFUR))			
					.unlockedBy("has_charcoal", has(Items.CHARCOAL))
					.unlockedBy("has_mortar_and_pestle", has(ModItems.MORTAR_AND_PESTLE.get()))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_grade_black_powder_vanilla"));

			ShapelessGunsmithsBenchMortarAndPestleRecipeBuilder.shapeless(ModItems.MEDIUM_GRADE_BLACK_POWDER.get(), 3)
					.requires(ModTags.Items.DUST_SALTPETER)
					.requires(ModTags.Items.DUST_SALTPETER)
					.requires(ModTags.Items.DUST_SULFUR)					
					.requires(Items.CHARCOAL)
					.requires(ModItems.MORTAR_AND_PESTLE.get())
					.unlockedBy("has_saltpeter", has(ModTags.Items.DUST_SALTPETER))
					.unlockedBy("has_sulfur", has(ModTags.Items.DUST_SULFUR))			
					.unlockedBy("has_charcoal", has(Items.CHARCOAL))
					.unlockedBy("has_mortar_and_pestle", has(ModItems.MORTAR_AND_PESTLE.get()))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_grade_black_powder"));
		}
		
		// High grade black powder from cake of high grade black powder
		{
			ShapelessVanillaMortarAndPestleRecipeBuilder.shapeless(ModItems.HIGH_GRADE_BLACK_POWDER.get(), 9)
					.requires(ModBlocks.HIGH_GRADE_BLACK_POWDER_CAKE.get())
					.requires(ModItems.MORTAR_AND_PESTLE.get())
					.unlockedBy("has_high_grade_black_powder_cake", has(ModBlocks.HIGH_GRADE_BLACK_POWDER_CAKE.get()))
					.unlockedBy("has_mortar_and_pestle", has(ModItems.MORTAR_AND_PESTLE.get()))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "high_grade_black_powder_from_cake_vanilla"));

			ShapelessGunsmithsBenchMortarAndPestleRecipeBuilder.shapeless(ModItems.HIGH_GRADE_BLACK_POWDER.get(), 9)
					.requires(ModBlocks.HIGH_GRADE_BLACK_POWDER_CAKE.get())
					.requires(ModItems.MORTAR_AND_PESTLE.get())
					.unlockedBy("has_high_grade_black_powder_cake", has(ModBlocks.HIGH_GRADE_BLACK_POWDER_CAKE.get()))
					.unlockedBy("has_mortar_and_pestle", has(ModItems.MORTAR_AND_PESTLE.get()))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "high_grade_black_powder_from_cake"));
		}
		
		// High grade black powder from block of high grade black powder
		{
			ShapelessVanillaMortarAndPestleRecipeBuilder.shapeless(ModItems.HIGH_GRADE_BLACK_POWDER.get(), 9)
					.requires(ModBlocks.HIGH_GRADE_BLACK_POWDER_BLOCK.get())
					.unlockedBy("has_high_grade_black_powder_block", has(ModBlocks.HIGH_GRADE_BLACK_POWDER_BLOCK.get()))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "high_grade_black_powder_from_block_vanilla"));

			ShapelessGunsmithsBenchMortarAndPestleRecipeBuilder.shapeless(ModItems.HIGH_GRADE_BLACK_POWDER.get(), 9)
					.requires(ModBlocks.HIGH_GRADE_BLACK_POWDER_BLOCK.get())
					.unlockedBy("has_high_grade_black_powder_block", has(ModBlocks.HIGH_GRADE_BLACK_POWDER_BLOCK.get()))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "high_grade_black_powder_from_block"));
		}
		
		// Medium grade black powder from block of medium grade black powder
		ShapelessVanillaMortarAndPestleRecipeBuilder.shapeless(ModItems.MEDIUM_GRADE_BLACK_POWDER.get(), 9)
				.requires(ModBlocks.MEDIUM_GRADE_BLACK_POWDER_BLOCK.get())
				.unlockedBy("has_medium_grade_black_powder_cake", has(ModBlocks.MEDIUM_GRADE_BLACK_POWDER_BLOCK.get()))
				.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_grade_black_powder_from_block_vanilla"));

		ShapelessGunsmithsBenchMortarAndPestleRecipeBuilder.shapeless(ModItems.MEDIUM_GRADE_BLACK_POWDER.get(), 9)
				.requires(ModBlocks.MEDIUM_GRADE_BLACK_POWDER_BLOCK.get())
				.unlockedBy("has_medium_grade_black_powder_cake", has(ModBlocks.MEDIUM_GRADE_BLACK_POWDER_BLOCK.get()))
				.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_grade_black_powder_from_block"));
		
		// High grade black powder block from high grade black powder
		ShapelessVanillaMortarAndPestleRecipeBuilder.shapeless(ModBlocks.HIGH_GRADE_BLACK_POWDER_BLOCK.get())
				.requires(ModItems.HIGH_GRADE_BLACK_POWDER.get(), 9)
				.unlockedBy("has_high_grade_black_powder", has(ModItems.HIGH_GRADE_BLACK_POWDER.get()))
				.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "high_grade_black_powder_block_vanilla"));

		ShapelessGunsmithsBenchMortarAndPestleRecipeBuilder.shapeless(ModBlocks.HIGH_GRADE_BLACK_POWDER_BLOCK.get())
				.requires(ModItems.HIGH_GRADE_BLACK_POWDER.get(), 9)
				.unlockedBy("has_high_grade_black_powder", has(ModItems.HIGH_GRADE_BLACK_POWDER.get()))
				.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "high_grade_black_powder_block"));
		
		// Medium grade black powder block from high grade black powder
		ShapelessVanillaMortarAndPestleRecipeBuilder.shapeless(ModBlocks.MEDIUM_GRADE_BLACK_POWDER_BLOCK.get())
				.requires(ModItems.MEDIUM_GRADE_BLACK_POWDER.get(), 9)
				.unlockedBy("has_medium_grade_black_powder", has(ModItems.MEDIUM_GRADE_BLACK_POWDER.get()))
				.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_grade_black_powder_block_vanilla"));

		ShapelessGunsmithsBenchMortarAndPestleRecipeBuilder.shapeless(ModBlocks.MEDIUM_GRADE_BLACK_POWDER_BLOCK.get())
				.requires(ModItems.MEDIUM_GRADE_BLACK_POWDER.get(), 9)
				.unlockedBy("has_medium_grade_black_powder", has(ModItems.MEDIUM_GRADE_BLACK_POWDER.get()))
				.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_grade_black_powder_block"));

		// Create waxed paper
		ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.WAXED_PAPER.get(), 8)
				.pattern("PPP")
				.pattern("PHP")
				.pattern("PPP")
				.define('P', Items.PAPER)
				.define('H', Items.HONEYCOMB)
				.unlockedBy("has_paper", has(Items.PAPER))
				.unlockedBy("has_honeycomb", has(Items.HONEYCOMB))
				.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "waxed_paper"));
		
		// Workshops 
		// Create gunsmiths bench
		ShapedRecipeBuilder.shaped(ModBlocks.GUNSMITHS_BENCH.get())
				.pattern("LLC")
				.pattern("PGP")
				.define('L', ItemTags.LOGS)
				.define('C', Tags.Items.COBBLESTONE)
				.define('P', ItemTags.PLANKS)
				.define('G', ModTags.Items.ANY_GUNPOWDER)	
				.unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE))
				.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "gunsmiths_bench"));
			
		// Firearm Repair
		ShapelessFirearmRepairRecipeBuilder.shapeless()
				.requires(ModTags.Items.FIREARM)
				.requires(ModItems.REPAIR_KIT.get())						
				.unlockedBy("has_firearm", has(ModTags.Items.FIREARM))
				.unlockedBy("has_repair_kit", has(ModItems.REPAIR_KIT.get()))
				.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "firearm_repair_vanilla"));
		
		ShapelessGunsmithsBenchFirearmRepairRecipeBuilder.shapeless()
				.requires(ModTags.Items.FIREARM)
				.requires(ModItems.REPAIR_KIT.get())						
				.unlockedBy("has_firearm", has(ModTags.Items.FIREARM))
				.unlockedBy("has_repair_kit", has(ModItems.REPAIR_KIT.get()))
				.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "firearm_repair"));
		
		// Firearm Reloading
		// Matchlocks
		// Reload matchlock derringer
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.MATCHLOCK_DERRINGER.get(), ModTags.Items.SMALL_ROCK_MUSKET_BALL, ModTags.Items.MATCHLOCK_SUITABLE_POWDER, 1);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.MATCHLOCK_DERRINGER.get(), ModTags.Items.SMALL_MATCHLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);
		// Reload matchlock pistol
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.MATCHLOCK_PISTOL.get(), ModTags.Items.SMALL_ROCK_MUSKET_BALL, ModTags.Items.MATCHLOCK_SUITABLE_POWDER, 1);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.MATCHLOCK_PISTOL.get(), ModTags.Items.SMALL_MATCHLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);
		// Reload matchlock arquebus
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.MATCHLOCK_ARQUEBUS.get(), ModTags.Items.SMALL_ROCK_MUSKET_BALL, ModTags.Items.MATCHLOCK_SUITABLE_POWDER, 1);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.MATCHLOCK_ARQUEBUS.get(), ModTags.Items.SMALL_MATCHLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);
		// Reload matchlock caliver
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.MATCHLOCK_CALIVER.get(), ModTags.Items.MEDIUM_ROCK_MUSKET_BALL, ModTags.Items.MATCHLOCK_SUITABLE_POWDER, 2);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.MATCHLOCK_CALIVER.get(), ModTags.Items.MEDIUM_MATCHLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);
		// Reload matchlock musketoon
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.MATCHLOCK_MUSKETOON.get(), ModTags.Items.MEDIUM_ROCK_MUSKET_BALL, ModTags.Items.MATCHLOCK_SUITABLE_POWDER, 2);
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.MATCHLOCK_MUSKETOON.get(), ModTags.Items.MEDIUM_ROCK_BIRDSHOT, ModTags.Items.MATCHLOCK_SUITABLE_POWDER, 2);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.MATCHLOCK_MUSKETOON.get(), ModTags.Items.MEDIUM_MATCHLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);
		// Reload matchlock musket
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.MATCHLOCK_MUSKET.get(), ModTags.Items.LARGE_ROCK_MUSKET_BALL, ModTags.Items.MATCHLOCK_SUITABLE_POWDER, 2);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.MATCHLOCK_MUSKET.get(), ModTags.Items.LARGE_MATCHLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);
		// Reload matchlock long musket
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.MATCHLOCK_LONG_MUSKET.get(), ModTags.Items.LARGE_ROCK_MUSKET_BALL, ModTags.Items.MATCHLOCK_SUITABLE_POWDER, 2);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.MATCHLOCK_LONG_MUSKET.get(), ModTags.Items.LARGE_MATCHLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);
		// Reload matchlock blunderbuss pistol birdshot
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.MATCHLOCK_BLUNDERBUSS_PISTOL.get(), ModTags.Items.SMALL_ROCK_BIRDSHOT, ModTags.Items.MATCHLOCK_SUITABLE_POWDER, 1);
		// Reload matchlock blunderbuss pistol birdshot
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.MATCHLOCK_BLUNDERBUSS.get(), ModTags.Items.LARGE_ROCK_BIRDSHOT, ModTags.Items.MATCHLOCK_SUITABLE_POWDER, 2);

		// Wheellock
		// Reload wheellock derringer
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.WHEELLOCK_DERRINGER.get(), ModTags.Items.SMALL_METAL_MUSKET_BALL, ModTags.Items.WHEELLOCK_SUITABLE_POWDER, 1);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.WHEELLOCK_DERRINGER.get(), ModTags.Items.SMALL_WHEELLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);
		// Reload wheellock pistol
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.WHEELLOCK_PISTOL.get(), ModTags.Items.SMALL_METAL_MUSKET_BALL, ModTags.Items.WHEELLOCK_SUITABLE_POWDER, 1);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.WHEELLOCK_PISTOL.get(), ModTags.Items.SMALL_WHEELLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);
		// Reload wheellock doublebarrel pistol
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.WHEELLOCK_DOUBLEBARREL_PISTOL.get(), ModTags.Items.SMALL_METAL_MUSKET_BALL, ModTags.Items.WHEELLOCK_SUITABLE_POWDER, 1);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.WHEELLOCK_DOUBLEBARREL_PISTOL.get(), ModTags.Items.SMALL_WHEELLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);
		// Reload wheellock arquebus
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.WHEELLOCK_ARQUEBUS.get(), ModTags.Items.SMALL_METAL_MUSKET_BALL, ModTags.Items.WHEELLOCK_SUITABLE_POWDER, 1);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.WHEELLOCK_ARQUEBUS.get(), ModTags.Items.SMALL_WHEELLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);		
		// Reload wheellock caliver
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.WHEELLOCK_CALIVER.get(), ModTags.Items.MEDIUM_METAL_MUSKET_BALL, ModTags.Items.WHEELLOCK_SUITABLE_POWDER, 2);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.WHEELLOCK_CALIVER.get(), ModTags.Items.MEDIUM_WHEELLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);				
		// Reload wheellock musketoon ball and birdshot
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.WHEELLOCK_MUSKETOON.get(), ModTags.Items.MEDIUM_METAL_MUSKET_BALL, ModTags.Items.WHEELLOCK_SUITABLE_POWDER, 2);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.WHEELLOCK_MUSKETOON.get(), ModTags.Items.MEDIUM_WHEELLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);		
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.WHEELLOCK_MUSKETOON.get(), ModTags.Items.MEDIUM_METAL_BIRDSHOT, ModTags.Items.WHEELLOCK_SUITABLE_POWDER, 2);
		// Reload wheellock musket
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.WHEELLOCK_MUSKET.get(), ModTags.Items.LARGE_METAL_MUSKET_BALL, ModTags.Items.WHEELLOCK_SUITABLE_POWDER, 2);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.WHEELLOCK_MUSKET.get(), ModTags.Items.LARGE_WHEELLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);
		// Reload wheellock long musket
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.WHEELLOCK_LONG_MUSKET.get(), ModTags.Items.LARGE_METAL_MUSKET_BALL, ModTags.Items.WHEELLOCK_SUITABLE_POWDER, 2);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.WHEELLOCK_LONG_MUSKET.get(), ModTags.Items.LARGE_WHEELLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);
		// Reload wheellock blunderbuss pistol buckshot and birdshot
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.WHEELLOCK_BLUNDERBUSS_PISTOL.get(), ModTags.Items.SMALL_METAL_BUCKSHOT, ModTags.Items.WHEELLOCK_SUITABLE_POWDER, 1);
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.WHEELLOCK_BLUNDERBUSS_PISTOL.get(), ModTags.Items.SMALL_METAL_BIRDSHOT, ModTags.Items.WHEELLOCK_SUITABLE_POWDER, 1);
		// Reload wheellock blunderbuss buckshot and birdshot
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.WHEELLOCK_BLUNDERBUSS.get(), ModTags.Items.LARGE_METAL_BUCKSHOT, ModTags.Items.WHEELLOCK_SUITABLE_POWDER, 2);
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.WHEELLOCK_BLUNDERBUSS.get(), ModTags.Items.LARGE_METAL_BIRDSHOT, ModTags.Items.WHEELLOCK_SUITABLE_POWDER, 2);		
		
		// Flintlocks
		// Reload flintlock derringer
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_DERRINGER.get(), ModTags.Items.SMALL_METAL_MUSKET_BALL, ModTags.Items.FLINTLOCK_SUITABLE_POWDER, 1);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_DERRINGER.get(), ModTags.Items.SMALL_FLINTLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);
		// Reload flintlock duckfoot derringer
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_DUCKFOOT_DERRINGER.get(), ModTags.Items.SMALL_METAL_MUSKET_BALL, ModTags.Items.FLINTLOCK_SUITABLE_POWDER, 1);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_DUCKFOOT_DERRINGER.get(), ModTags.Items.SMALL_FLINTLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);
		// Reload flintlock pistol
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_PISTOL.get(), ModTags.Items.SMALL_METAL_MUSKET_BALL, ModTags.Items.FLINTLOCK_SUITABLE_POWDER, 1);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_PISTOL.get(), ModTags.Items.SMALL_FLINTLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);
		// Reload flintlock doublebarrel pistol
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_PEPPERBOX_PISTOL.get(), ModTags.Items.SMALL_METAL_MUSKET_BALL, ModTags.Items.FLINTLOCK_SUITABLE_POWDER, 1);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_PEPPERBOX_PISTOL.get(), ModTags.Items.SMALL_FLINTLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);
		// Reload flintlock arquebus
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_ARQUEBUS.get(), ModTags.Items.SMALL_METAL_MUSKET_BALL, ModTags.Items.FLINTLOCK_SUITABLE_POWDER, 1);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_ARQUEBUS.get(), ModTags.Items.SMALL_FLINTLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);		
		// Reload flintlock caliver
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_CALIVER.get(), ModTags.Items.MEDIUM_METAL_MUSKET_BALL, ModTags.Items.FLINTLOCK_SUITABLE_POWDER, 2);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_CALIVER.get(), ModTags.Items.MEDIUM_FLINTLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);				
		// Reload flintlock musketoon ball and birdshot
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_MUSKETOON.get(), ModTags.Items.MEDIUM_METAL_MUSKET_BALL, ModTags.Items.FLINTLOCK_SUITABLE_POWDER, 2);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_MUSKETOON.get(), ModTags.Items.MEDIUM_FLINTLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);		
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_MUSKETOON.get(), ModTags.Items.MEDIUM_METAL_BIRDSHOT, ModTags.Items.FLINTLOCK_SUITABLE_POWDER, 2);
		// Reload flintlock musket
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_MUSKET.get(), ModTags.Items.LARGE_METAL_MUSKET_BALL, ModTags.Items.FLINTLOCK_SUITABLE_POWDER, 2);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_MUSKET.get(), ModTags.Items.LARGE_FLINTLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);
		// Reload flintlock nock gun
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_NOCK_GUN.get(), ModTags.Items.LARGE_METAL_MUSKET_BALL, ModTags.Items.FLINTLOCK_SUITABLE_POWDER, 2);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_NOCK_GUN.get(), ModTags.Items.LARGE_FLINTLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);
		// Reload flintlock long musket
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_LONG_MUSKET.get(), ModTags.Items.LARGE_METAL_MUSKET_BALL, ModTags.Items.FLINTLOCK_SUITABLE_POWDER, 2);
		shapelessMuzzleloaderSingleCartridgeReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_LONG_MUSKET.get(), ModTags.Items.LARGE_FLINTLOCK_SUITABLE_METAL_MUSKET_BALL_CARTRIDGE);
		// Reload flintlock blunderbuss pistol buckshot and birdshot
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_BLUNDERBUSS_PISTOL.get(), ModTags.Items.SMALL_METAL_BUCKSHOT, ModTags.Items.FLINTLOCK_SUITABLE_POWDER, 1);
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_BLUNDERBUSS_PISTOL.get(), ModTags.Items.SMALL_METAL_BIRDSHOT, ModTags.Items.FLINTLOCK_SUITABLE_POWDER, 1);
		// Reload flintlock blunderbuss buckshot and birdshot
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_BLUNDERBUSS.get(), ModTags.Items.LARGE_METAL_BUCKSHOT, ModTags.Items.FLINTLOCK_SUITABLE_POWDER, 2);
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_BLUNDERBUSS.get(), ModTags.Items.LARGE_METAL_BIRDSHOT, ModTags.Items.FLINTLOCK_SUITABLE_POWDER, 2);		
		// Reload flintlock doublebarrel blunderbuss buckshot and birdshot
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS.get(), ModTags.Items.LARGE_METAL_BUCKSHOT, ModTags.Items.FLINTLOCK_SUITABLE_POWDER, 2);
		shapelessMuzzleloaderSingleReloadRecipe(recipeConsumer, ModItems.FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS.get(), ModTags.Items.LARGE_METAL_BIRDSHOT, ModTags.Items.FLINTLOCK_SUITABLE_POWDER, 2);

		// Artillery
		// Create naval cannon
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModBlocks.MEDIUM_NAVAL_CANNON.get())
					.pattern(" B ")
					.pattern("WCW")
					.define('W', ModTags.Items.TINY_CARRIAGE_WHEEL)
					.define('C', ModTags.Items.MEDIUM_NAVAL_CARRIAGE)
					.define('B', ModTags.Items.MEDIUM_METAL_CANNON_BARREL)
					.unlockedBy("has_tiny_carriage_wheel", has(ModTags.Items.TINY_CARRIAGE_WHEEL))
					.unlockedBy("has_medium_naval_carriage", has(ModTags.Items.MEDIUM_NAVAL_CARRIAGE))
					.unlockedBy("has_medium_metal_cannon_barrel", has(ModTags.Items.MEDIUM_METAL_CANNON_BARREL))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_naval_cannon_artillery"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "naval_cannon"));
		}

		// Ammo
		// Create medium iron cannonball
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MEDIUM_IRON_CANNONBALL.get())
					.pattern(" i ")
					.pattern("iBi")
					.pattern(" i ")
					.define('i', Tags.Items.NUGGETS_IRON)
					.define('B', Tags.Items.STORAGE_BLOCKS_IRON)
					.unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
					.unlockedBy("has_iron_block", has(Tags.Items.STORAGE_BLOCKS_IRON))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_iron_artillery_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_iron_cannonball"));
		}
		
		// Create medium iron grapeshot
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MEDIUM_IRON_GRAPESHOT.get())
					.pattern(" L ")
					.pattern("bbb")
					.pattern(" I ")
					.define('L', Tags.Items.LEATHER)
					.define('b', ModItems.LARGE_IRON_MUSKET_BALL.get())
					.define('I', Tags.Items.INGOTS_IRON)
					.unlockedBy("has_leather", has(Tags.Items.LEATHER))
					.unlockedBy("has_small_iron_musket_ball", has( ModItems.LARGE_IRON_MUSKET_BALL.get()))
					.unlockedBy("has_iron_ingots", has(Tags.Items.INGOTS_IRON))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_iron_artillery_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_iron_grapeshot"));
		}
		
		// Create medium iron canister shot
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MEDIUM_IRON_CANISTER_SHOT.get())
					.pattern("GG")
					.pattern("bb")
					.pattern("ii")
					.define('G', ModTags.Items.ANY_GUNPOWDER)
					.define('i', Tags.Items.NUGGETS_IRON)
					.define('b', ModItems.LARGE_IRON_BUCKSHOT.get())
					.unlockedBy("has_any_gunpowder", has(ModTags.Items.ANY_GUNPOWDER))
					.unlockedBy("has_large_iron_buckshot", has( ModItems.LARGE_IRON_BUCKSHOT.get()))
					.unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_iron_artillery_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_iron_canister_shot"));
		}
				
		// Create small powder charge
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.SMALL_POWDER_CHARGE.get(), 1)
					.requires(ModTags.Items.ANY_GUNPOWDER)
					.requires(ModTags.Items.ANY_GUNPOWDER)
					.requires(ModTags.Items.ANY_GUNPOWDER)
					.requires(Items.PAPER)
					.unlockedBy("has_any_gunpowder", has(ModTags.Items.ANY_GUNPOWDER))
					.unlockedBy("has_paper", has(Items.PAPER))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_artillery_powder_charges"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_powder_charge"));
		}
		
		// Create medium powder charge
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.MEDIUM_POWDER_CHARGE.get(), 1)
					.requires(ModTags.Items.ANY_GUNPOWDER)
					.requires(ModTags.Items.ANY_GUNPOWDER)
					.requires(ModTags.Items.ANY_GUNPOWDER)
					.requires(ModTags.Items.ANY_GUNPOWDER)
					.requires(Items.PAPER)
					.requires(Items.PAPER)
					.unlockedBy("has_any_gunpowder", has(ModTags.Items.ANY_GUNPOWDER))
					.unlockedBy("has_paper", has(Items.PAPER))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_artillery_powder_charges"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_powder_charge"));
		}
		
		// Create large powder charge
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.LARGE_POWDER_CHARGE.get(), 1)
					.requires(ModTags.Items.ANY_GUNPOWDER)
					.requires(ModTags.Items.ANY_GUNPOWDER)
					.requires(ModTags.Items.ANY_GUNPOWDER)
					.requires(ModTags.Items.ANY_GUNPOWDER)
					.requires(ModTags.Items.ANY_GUNPOWDER)
					.requires(Items.PAPER)
					.requires(Items.PAPER)
					.requires(Items.PAPER)
					.unlockedBy("has_any_gunpowder", has(ModTags.Items.ANY_GUNPOWDER))
					.unlockedBy("has_paper", has(Items.PAPER))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_artillery_powder_charges"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_powder_charge"));
		}
		
		// Parts
		// Create small iron cannon barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.SMALL_IRON_CANNON_BARREL.get())
					.pattern("IIi")
					.pattern("  I")
					.pattern("IIi")
					.define('i', Tags.Items.NUGGETS_IRON)
					.define('I', Tags.Items.INGOTS_IRON)
					.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
					.unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_iron_cannon_barrel"));
		}
		
		// Create medium iron cannon barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MEDIUM_IRON_CANNON_BARREL.get())
					.pattern("IIi")
					.pattern("  R")
					.pattern("IIi")
					.define('i', Tags.Items.NUGGETS_IRON)
					.define('I', Tags.Items.INGOTS_IRON)
					.define('R', Tags.Items.STORAGE_BLOCKS_IRON)
					.unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
					.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
					.unlockedBy("has_iron_block", has(Tags.Items.STORAGE_BLOCKS_IRON))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_iron_cannon_barrel"));
		}

		// Create large iron cannon barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.LARGE_IRON_CANNON_BARREL.get())
					.pattern("IRI")
					.pattern("  R")
					.pattern("IRI")
					.define('I', Tags.Items.INGOTS_IRON)
					.define('R', Tags.Items.STORAGE_BLOCKS_IRON)
					.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
					.unlockedBy("has_iron_block", has(Tags.Items.STORAGE_BLOCKS_IRON))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_iron_cannon_barrel"));
		}
		
		// Create small wooden naval carriage
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.SMALL_WOODEN_NAVAL_CARRIAGE.get())
					.pattern("  S")
					.pattern("SPL")
					.define('P', ItemTags.PLANKS)
					.define('L', ItemTags.LOGS)
					.define('S', ItemTags.WOODEN_SLABS)
					.unlockedBy("has_planks", has(ItemTags.PLANKS))
					.unlockedBy("has_wooden_slab", has(ItemTags.WOODEN_SLABS))
					.unlockedBy("has_log", has(ItemTags.LOGS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_wooden_naval_carriage"));
		}
		
		// Create medium wooden naval carriage
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MEDIUM_WOODEN_NAVAL_CARRIAGE.get())
					.pattern(" SS")
					.pattern("PLL")
					.define('P', ItemTags.PLANKS)
					.define('L', ItemTags.LOGS)
					.define('S', ItemTags.WOODEN_SLABS)
					.unlockedBy("has_planks", has(ItemTags.PLANKS))
					.unlockedBy("has_wooden_slab", has(ItemTags.WOODEN_SLABS))
					.unlockedBy("has_log", has(ItemTags.LOGS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_wooden_naval_carriage"));
		}
		
		// Create large wooden naval carriage
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.LARGE_WOODEN_NAVAL_CARRIAGE.get())
					.pattern("  S")
					.pattern("SPP")
					.pattern("LPL")
					.define('P', ItemTags.PLANKS)
					.define('L', ItemTags.LOGS)
					.define('S', ItemTags.WOODEN_SLABS)
					.unlockedBy("has_planks", has(ItemTags.PLANKS))
					.unlockedBy("has_wooden_slab", has(ItemTags.WOODEN_SLABS))
					.unlockedBy("has_log", has(ItemTags.LOGS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_wooden_naval_carriage"));
		}
		
		// Create tiny wooden carriage wheel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.TINY_WOODEN_CARRIAGE_WHEEL.get())
					.pattern(" s ")
					.pattern("sbs")
					.pattern(" s ")
					.define('s', Tags.Items.RODS_WOODEN)
					.define('b', Items.STONE_BUTTON)
					.unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
					.unlockedBy("has_stone_button", has(Items.STONE_BUTTON))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "tiny_wooden_carriage_wheel"));
		}
		
		// Create small wooden carriage wheel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.SMALL_WOODEN_CARRIAGE_WHEEL.get())
					.pattern(" S ")
					.pattern("SbS")
					.pattern(" S ")
					.define('S', ItemTags.WOODEN_SLABS)
					.define('b', Items.STONE_BUTTON)
					.unlockedBy("has_wooden_slab", has(ItemTags.WOODEN_SLABS))
					.unlockedBy("has_stone_button", has(Items.STONE_BUTTON))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_wooden_carriage_wheel"));
		}
		
		// Create medium wooden carriage wheel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MEDIUM_WOODEN_CARRIAGE_WHEEL.get())
					.pattern(" P ")
					.pattern("PBP")
					.pattern(" P ")
					.define('P', ItemTags.PLANKS)
					.define('B', Items.STONE)
					.unlockedBy("has_planks", has(ItemTags.PLANKS))
					.unlockedBy("has_stone", has(Items.STONE))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_wooden_carriage_wheel"));
		}
		
		// Create large wooden carriage wheel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.LARGE_WOODEN_CARRIAGE_WHEEL.get())
					.pattern("SPS")
					.pattern("PBP")
					.pattern("SPS")
					.define('P', ItemTags.PLANKS)
					.define('B', Items.STONE)
					.define('S', ItemTags.WOODEN_SLABS)
					.unlockedBy("has_planks", has(ItemTags.PLANKS))
					.unlockedBy("has_wooden_slab", has(ItemTags.WOODEN_SLABS))
					.unlockedBy("has_stone", has(Items.STONE))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_wooden_carriage_wheel"));
		}
				
		// Tools
		// Create ram rod
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.RAM_ROD.get())
					.pattern("W  ")
					.pattern(" S ")
					.pattern("  S")
					.define('W', ItemTags.WOOL)
					.define('S', Tags.Items.RODS_WOODEN)
					.unlockedBy("has_wool", has(ItemTags.WOOL))
					.unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "ram_rod"));
		}
		
		// Create long match
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.LONG_MATCH.get())
					.pattern("M  ")
					.pattern(" S ")
					.pattern("  S")
					.define('M', ModItems.MATCH_CORD.get())
					.define('S', Tags.Items.RODS_WOODEN)
					.unlockedBy("has_match_cord", has(ModItems.MATCH_CORD.get()))
					.unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "long_match"));
		}
		
		// Create gunners quadrant
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.GUNNERS_QUADRANT.get())
					.pattern("SS ")
					.pattern(" CS")
					.pattern("  S")
					.define('C', Items.COMPASS)
					.define('S', Tags.Items.RODS_WOODEN)
					.unlockedBy("has_compass", has(Items.COMPASS))
					.unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "gunners_quadrant"));
		}
		
		// Firearms
		// Matchlock
		// Create matchlock derringer
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MATCHLOCK_DERRINGER.get())
					.pattern("BMH")
					.define('B', ModTags.Items.TINY_ROCK_BARREL)
					.define('M', ModTags.Items.MATCHLOCK_MECHANISM)
					.define('H', ModTags.Items.SMALL_HANDLE)
					.unlockedBy("has_tiny_rock_barrel", has(ModTags.Items.TINY_ROCK_BARREL))
					.unlockedBy("has_matchlock_mechanism", has(ModTags.Items.MATCHLOCK_MECHANISM))
					.unlockedBy("has_small_handle", has(ModTags.Items.SMALL_HANDLE))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_matchlock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "matchlock_derringer"));
		}
				
		// Create matchlock pistol
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MATCHLOCK_PISTOL.get())
					.pattern("BMH")
					.define('B', ModTags.Items.SMALL_ROCK_BARREL)
					.define('M', ModTags.Items.MATCHLOCK_MECHANISM)
					.define('H', ModTags.Items.MEDIUM_HANDLE)
					.unlockedBy("has_small_rock_barrel", has(ModTags.Items.SMALL_ROCK_BARREL))
					.unlockedBy("has_matchlock_mechanism", has(ModTags.Items.MATCHLOCK_MECHANISM))
					.unlockedBy("has_medium_handle", has(ModTags.Items.MEDIUM_HANDLE))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_matchlock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "matchlock_pistol"));
		}
		
		// Create matchlock arquebus
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MATCHLOCK_ARQUEBUS.get())
					.pattern("BMH")
					.define('B', ModTags.Items.SMALL_ROCK_BARREL)
					.define('M', ModTags.Items.MATCHLOCK_MECHANISM)
					.define('H', ModTags.Items.LARGE_HANDLE)
					.unlockedBy("has_small_rock_barrel", has(ModTags.Items.SMALL_ROCK_BARREL))
					.unlockedBy("has_matchlock_mechanism", has(ModTags.Items.MATCHLOCK_MECHANISM))
					.unlockedBy("has_large_handle", has(ModTags.Items.LARGE_HANDLE))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_matchlock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "matchlock_arquebus"));
		}
		
		// Create matchlock caliver
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MATCHLOCK_CALIVER.get())
					.pattern("BMH")
					.define('B', ModTags.Items.MEDIUM_ROCK_BARREL)
					.define('M', ModTags.Items.MATCHLOCK_MECHANISM)
					.define('H', ModTags.Items.SMALL_STOCK)
					.unlockedBy("has_medium_rock_barrel", has(ModTags.Items.MEDIUM_ROCK_BARREL))
					.unlockedBy("has_matchlock_mechanism", has(ModTags.Items.MATCHLOCK_MECHANISM))
					.unlockedBy("has_small_stock", has(ModTags.Items.SMALL_STOCK))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_matchlock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "matchlock_caliver"));
		}
		
		// Create matchlock musketoon
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MATCHLOCK_MUSKETOON.get())
					.pattern("BMH")
					.define('B', ModTags.Items.MEDIUM_ROCK_FLARED_BARREL)
					.define('M', ModTags.Items.MATCHLOCK_MECHANISM)
					.define('H', ModTags.Items.MEDIUM_STOCK)
					.unlockedBy("has_medium_rock_flared_barrel", has(ModTags.Items.MEDIUM_ROCK_FLARED_BARREL))
					.unlockedBy("has_matchlock_mechanism", has(ModTags.Items.MATCHLOCK_MECHANISM))
					.unlockedBy("has_medium_stock", has(ModTags.Items.MEDIUM_STOCK))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_matchlock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "matchlock_musketoon"));
		}
		
		// Create matchlock musket
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MATCHLOCK_MUSKET.get())
					.pattern("BMH")
					.define('B', ModTags.Items.LARGE_ROCK_BARREL)
					.define('M', ModTags.Items.MATCHLOCK_MECHANISM)
					.define('H', ModTags.Items.MEDIUM_STOCK)
					.unlockedBy("has_large_rock_barrel", has(ModTags.Items.LARGE_ROCK_BARREL))
					.unlockedBy("has_matchlock_mechanism", has(ModTags.Items.MATCHLOCK_MECHANISM))
					.unlockedBy("has_medium_stock", has(ModTags.Items.MEDIUM_STOCK))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_matchlock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "matchlock_musket"));
		}
		
		// Create matchlock long musket
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MATCHLOCK_LONG_MUSKET.get())
					.pattern("BMH")
					.define('B', ModTags.Items.LARGE_ROCK_BARREL)
					.define('M', ModTags.Items.MATCHLOCK_MECHANISM)
					.define('H', ModTags.Items.LARGE_STOCK)
					.unlockedBy("has_large_rock_barrel", has(ModTags.Items.LARGE_ROCK_BARREL))
					.unlockedBy("has_matchlock_mechanism", has(ModTags.Items.MATCHLOCK_MECHANISM))
					.unlockedBy("has_large_stock", has(ModTags.Items.LARGE_STOCK))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_matchlock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "matchlock_long_musket"));
		}
		
		// Create matchlock blunderbuss pistol
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MATCHLOCK_BLUNDERBUSS_PISTOL.get())
					.pattern("BMH")
					.define('B', ModTags.Items.SMALL_ROCK_FLARED_BARREL)
					.define('M', ModTags.Items.MATCHLOCK_MECHANISM)
					.define('H', ModTags.Items.MEDIUM_HANDLE)
					.unlockedBy("has_small_rock_flared_barrel", has(ModTags.Items.SMALL_ROCK_FLARED_BARREL))
					.unlockedBy("has_matchlock_mechanism", has(ModTags.Items.MATCHLOCK_MECHANISM))
					.unlockedBy("has_medium_handle", has(ModTags.Items.MEDIUM_HANDLE))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_matchlock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "matchlock_blunderbuss_pistol"));
		}
		
		// Create matchlock blunderbuss
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MATCHLOCK_BLUNDERBUSS.get())
					.pattern("BMH")
					.define('B', ModTags.Items.LARGE_ROCK_FLARED_BARREL)
					.define('M', ModTags.Items.MATCHLOCK_MECHANISM)
					.define('H', ModTags.Items.LARGE_STOCK)
					.unlockedBy("has_large_rock_flared_barrel", has(ModTags.Items.LARGE_ROCK_FLARED_BARREL))
					.unlockedBy("has_matchlock_mechanism", has(ModTags.Items.MATCHLOCK_MECHANISM))
					.unlockedBy("has_large_stock", has(ModTags.Items.LARGE_STOCK))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_matchlock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "matchlock_blunderbuss"));
		}
		
		// Wheellock
		// Create wheellock derringer
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.WHEELLOCK_DERRINGER.get())
					.pattern("BMH")
					.define('B', ModTags.Items.TINY_METAL_BARREL)
					.define('M', ModTags.Items.WHEELLOCK_MECHANISM)
					.define('H', ModTags.Items.SMALL_HANDLE)
					.unlockedBy("has_tiny_metal_barrel", has(ModTags.Items.TINY_METAL_BARREL))
					.unlockedBy("has_wheellock_mechanism", has(ModTags.Items.WHEELLOCK_MECHANISM))
					.unlockedBy("has_small_handle", has(ModTags.Items.SMALL_HANDLE))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_wheellock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wheellock_derringer"));
		}
				
		// Create wheellock pistol
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.WHEELLOCK_PISTOL.get())
					.pattern("BMH")
					.define('B', ModTags.Items.SMALL_METAL_BARREL)
					.define('M', ModTags.Items.WHEELLOCK_MECHANISM)
					.define('H', ModTags.Items.MEDIUM_HANDLE)
					.unlockedBy("has_small_metal_barrel", has(ModTags.Items.SMALL_METAL_BARREL))
					.unlockedBy("has_wheellock_mechanism", has(ModTags.Items.WHEELLOCK_MECHANISM))
					.unlockedBy("has_medium_handle", has(ModTags.Items.MEDIUM_HANDLE))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_wheellock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wheellock_pistol"));
		}
		
		// Create wheellock doublebarrel pistol
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.WHEELLOCK_DOUBLEBARREL_PISTOL.get())
					.pattern("BM ")
					.pattern("BMH")
					.define('B', ModTags.Items.SMALL_METAL_BARREL)
					.define('M', ModTags.Items.WHEELLOCK_MECHANISM)
					.define('H', ModTags.Items.MEDIUM_HANDLE)
					.unlockedBy("has_small_metal_barrel", has(ModTags.Items.SMALL_METAL_BARREL))
					.unlockedBy("has_wheellock_mechanism", has(ModTags.Items.WHEELLOCK_MECHANISM))
					.unlockedBy("has_medium_handle", has(ModTags.Items.MEDIUM_HANDLE))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_wheellock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wheellock_doublebarrel_pistol"));
		}

		// Create wheellock arquebus
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.WHEELLOCK_ARQUEBUS.get())
					.pattern("BMH")
					.define('B', ModTags.Items.SMALL_METAL_BARREL)
					.define('M', ModTags.Items.WHEELLOCK_MECHANISM)
					.define('H', ModTags.Items.LARGE_HANDLE)
					.unlockedBy("has_small_metal_barrel", has(ModTags.Items.SMALL_METAL_BARREL))
					.unlockedBy("has_wheellock_mechanism", has(ModTags.Items.WHEELLOCK_MECHANISM))
					.unlockedBy("has_large_handle", has(ModTags.Items.LARGE_HANDLE))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_wheellock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wheellock_arquebus"));
		}
		
		// Create wheellock caliver
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.WHEELLOCK_CALIVER.get())
					.pattern("BMH")
					.define('B', ModTags.Items.MEDIUM_METAL_BARREL)
					.define('M', ModTags.Items.WHEELLOCK_MECHANISM)
					.define('H', ModTags.Items.SMALL_STOCK)
					.unlockedBy("has_medium_metal_barrel", has(ModTags.Items.MEDIUM_METAL_BARREL))
					.unlockedBy("has_wheellock_mechanism", has(ModTags.Items.WHEELLOCK_MECHANISM))
					.unlockedBy("has_small_stock", has(ModTags.Items.SMALL_STOCK))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_wheellock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wheellock_caliver"));
		}
		
		// Create wheellock musketoon
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.WHEELLOCK_MUSKETOON.get())
					.pattern("BMH")
					.define('B', ModTags.Items.MEDIUM_METAL_FLARED_BARREL)
					.define('M', ModTags.Items.WHEELLOCK_MECHANISM)
					.define('H', ModTags.Items.MEDIUM_STOCK)
					.unlockedBy("has_medium_metal_flared_barrel", has(ModTags.Items.MEDIUM_METAL_FLARED_BARREL))
					.unlockedBy("has_wheellock_mechanism", has(ModTags.Items.WHEELLOCK_MECHANISM))
					.unlockedBy("has_medium_stock", has(ModTags.Items.MEDIUM_STOCK))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_wheellock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wheellock_musketoon"));
		}
		
		// Create wheellock musket
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.WHEELLOCK_MUSKET.get())
					.pattern("BMH")
					.define('B', ModTags.Items.LARGE_METAL_BARREL)
					.define('M', ModTags.Items.WHEELLOCK_MECHANISM)
					.define('H', ModTags.Items.MEDIUM_STOCK)
					.unlockedBy("has_large_metal_barrel", has(ModTags.Items.LARGE_METAL_BARREL))
					.unlockedBy("has_wheellock_mechanism", has(ModTags.Items.WHEELLOCK_MECHANISM))
					.unlockedBy("has_medium_stock", has(ModTags.Items.MEDIUM_STOCK))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_wheellock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wheellock_musket"));
		}
		
		// Create wheellock long musket
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.WHEELLOCK_LONG_MUSKET.get())
					.pattern("BMH")
					.define('B', ModTags.Items.LARGE_METAL_BARREL)
					.define('M', ModTags.Items.WHEELLOCK_MECHANISM)
					.define('H', ModTags.Items.LARGE_STOCK)
					.unlockedBy("has_large_metal_barrel", has(ModTags.Items.LARGE_METAL_BARREL))
					.unlockedBy("has_wheellock_mechanism", has(ModTags.Items.WHEELLOCK_MECHANISM))
					.unlockedBy("has_large_stock", has(ModTags.Items.LARGE_STOCK))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_wheellock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wheellock_long_musket"));
		}
		
		// Create wheellock blunderbuss pistol
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.WHEELLOCK_BLUNDERBUSS_PISTOL.get())
					.pattern("BMH")
					.define('B', ModTags.Items.SMALL_METAL_FLARED_BARREL)
					.define('M', ModTags.Items.MATCHLOCK_MECHANISM)
					.define('H', ModTags.Items.MEDIUM_HANDLE)
					.unlockedBy("has_small_metal_flared_barrel", has(ModTags.Items.SMALL_METAL_FLARED_BARREL))
					.unlockedBy("has_wheellock_mechanism", has(ModTags.Items.WHEELLOCK_MECHANISM))
					.unlockedBy("has_medium_handle", has(ModTags.Items.MEDIUM_HANDLE))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_wheellock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wheellock_blunderbuss_pistol"));
		}
		
		// Create wheelock blunderbuss
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.WHEELLOCK_BLUNDERBUSS.get())
					.pattern("BMH")
					.define('B', ModTags.Items.LARGE_METAL_FLARED_BARREL)
					.define('M', ModTags.Items.WHEELLOCK_MECHANISM)
					.define('H', ModTags.Items.LARGE_STOCK)
					.unlockedBy("has_large_metal_flared_barrel", has(ModTags.Items.LARGE_METAL_FLARED_BARREL))
					.unlockedBy("has_wheellock_mechanism", has(ModTags.Items.WHEELLOCK_MECHANISM))
					.unlockedBy("has_large_stock", has(ModTags.Items.LARGE_STOCK))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_wheellock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wheellock_blunderbuss"));
		}
		
		// Flintlock		
		// Create flintlock derringer
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.FLINTLOCK_DERRINGER.get())
					.pattern("BMH")
					.define('B', ModTags.Items.TINY_METAL_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.SMALL_HANDLE)
					.unlockedBy("has_tiny_barrel", has(ModTags.Items.TINY_METAL_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_small_handle", has(ModTags.Items.SMALL_HANDLE))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_flintlock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_derringer"));
		}
		
		// Create flintlock duckfoot derringer
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.FLINTLOCK_DUCKFOOT_DERRINGER.get())
					.pattern("B  ")
					.pattern("BMH")
					.pattern("B  ")
					.define('B', ModTags.Items.TINY_METAL_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.SMALL_HANDLE)
					.unlockedBy("has_tiny_barrel", has(ModTags.Items.TINY_METAL_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_small_handle", has(ModTags.Items.SMALL_HANDLE))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_flintlock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_duckfoot_derringer"));
		}
				
		// Create flintlock pistol
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.FLINTLOCK_PISTOL.get())
					.pattern("BMH")
					.define('B', ModTags.Items.SMALL_METAL_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.MEDIUM_HANDLE)
					.unlockedBy("has_small_barrel", has(ModTags.Items.SMALL_METAL_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_medium_handle", has(ModTags.Items.MEDIUM_HANDLE))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_flintlock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_pistol"));
		}
		
		// Create flintlock pepperbox pistol
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.FLINTLOCK_PEPPERBOX_PISTOL.get())
					.pattern("BB ")
					.pattern("BMH")
					.pattern("B  ")
					.define('B', ModTags.Items.SMALL_METAL_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.MEDIUM_HANDLE)
					.unlockedBy("has_small_barrel", has(ModTags.Items.SMALL_METAL_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_medium_handle", has(ModTags.Items.MEDIUM_HANDLE))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_flintlock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_pepperbox_pistol"));
		}
		
		// Create flintlock arquebus
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.FLINTLOCK_ARQUEBUS.get())
					.pattern("BMH")
					.define('B', ModTags.Items.SMALL_METAL_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.LARGE_HANDLE)
					.unlockedBy("has_small_barrel", has(ModTags.Items.SMALL_METAL_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_large_handle", has(ModTags.Items.LARGE_HANDLE))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_flintlock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_arquebus"));
		}
		
		// Create flintlock caliver
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.FLINTLOCK_CALIVER.get())
					.pattern("BMH")
					.define('B', ModTags.Items.MEDIUM_METAL_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.SMALL_STOCK)
					.unlockedBy("has_medium_metal_barrel", has(ModTags.Items.MEDIUM_METAL_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_small_stock", has(ModTags.Items.SMALL_STOCK))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_flintlock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_caliver"));
		}
		
		// Create flintlock musketoon
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.FLINTLOCK_MUSKETOON.get())
					.pattern("BMH")
					.define('B', ModTags.Items.MEDIUM_METAL_FLARED_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.MEDIUM_STOCK)
					.unlockedBy("has_medium_metal_flared_barrel", has(ModTags.Items.MEDIUM_METAL_FLARED_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_medium_stock", has(ModTags.Items.MEDIUM_STOCK))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_flintlock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_musketoon"));
		}
		
		// Create flintlock musket
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.FLINTLOCK_MUSKET.get())
					.pattern("BMH")
					.define('B', ModTags.Items.LARGE_METAL_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.MEDIUM_STOCK)
					.unlockedBy("has_large_metal_barrel", has(ModTags.Items.LARGE_METAL_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_medium_stock", has(ModTags.Items.MEDIUM_STOCK))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_flintlock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_musket"));
		}
		
		// Create flintlock nock gun
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.FLINTLOCK_NOCK_GUN.get())
					.pattern("BB ")
					.pattern("BMH")
					.pattern("BB ")
					.define('B', ModTags.Items.LARGE_METAL_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.LARGE_STOCK)
					.unlockedBy("has_large_metal_barrel", has(ModTags.Items.LARGE_METAL_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_large_stock", has(ModTags.Items.LARGE_STOCK))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_flintlock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_nock_gun"));
		}
		
		// Create flintlock long musket
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.FLINTLOCK_LONG_MUSKET.get())
					.pattern("BMH")
					.define('B', ModTags.Items.LARGE_METAL_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.LARGE_STOCK)
					.unlockedBy("has_large_metal_barrel", has(ModTags.Items.LARGE_METAL_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_large_stock", has(ModTags.Items.LARGE_STOCK))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_flintlock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_long_musket"));
		}
		
		// Create flintlock blunderbuss pistol
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.FLINTLOCK_BLUNDERBUSS_PISTOL.get())
					.pattern("BMH")
					.define('B', ModTags.Items.SMALL_METAL_FLARED_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.MEDIUM_HANDLE)
					.unlockedBy("has_small_metal_flared_barrel", has(ModTags.Items.SMALL_METAL_FLARED_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_medium_handle", has(ModTags.Items.MEDIUM_HANDLE))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_flintlock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_blunderbuss_pistol"));
		}
		
		// Create flintlock blunderbuss
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.FLINTLOCK_BLUNDERBUSS.get())
					.pattern("BMH")
					.define('B', ModTags.Items.LARGE_METAL_FLARED_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.LARGE_STOCK)
					.unlockedBy("has_large_metal_flared_barrel", has(ModTags.Items.LARGE_METAL_FLARED_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_large_stock", has(ModTags.Items.LARGE_STOCK))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_flintlock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_blunderbuss"));
		}
		
		// Create flintlock doublebarrel blunderbuss
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS.get())
					.pattern("B  ")
					.pattern("BMH")
					.define('B', ModTags.Items.LARGE_METAL_FLARED_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.LARGE_STOCK)
					.unlockedBy("has_large_metal_flared_barrel", has(ModTags.Items.LARGE_METAL_FLARED_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_large_stock", has(ModTags.Items.LARGE_STOCK))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_flintlock_weapons"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_doublebarrel_blunderbuss"));
		}
		
		// Ammo
		// Projectiles
		// Create small stone musket ball
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.SMALL_STONE_MUSKET_BALL.get(), 2)
					.requires(Items.STONE)
					.requires(Items.FLINT)					
					.unlockedBy("has_stone", has(Items.STONE))
					.unlockedBy("has_flint", has(Items.FLINT))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_stone_firearm_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_stone_musket_ball"));
		}
		
		// Create medium stone musket ball
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.MEDIUM_STONE_MUSKET_BALL.get(), 2)
					.requires(Items.STONE)
					.requires(Items.FLINT)
					.requires(Items.FLINT)
					.unlockedBy("has_stone", has(Items.STONE))
					.unlockedBy("has_flint", has(Items.FLINT))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_stone_firearm_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_stone_musket_ball"));
		}
		
		// Create large stone musket ball
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.LARGE_STONE_MUSKET_BALL.get(), 2)
					.requires(Items.STONE)
					.requires(Items.FLINT)
					.requires(Items.FLINT)
					.requires(Items.FLINT)
					.unlockedBy("has_stone", has(Items.STONE))
					.unlockedBy("has_flint", has(Items.FLINT))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_stone_firearm_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_stone_musket_ball"));
		}
				
		// Create small stone birdshot
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.SMALL_STONE_BIRDSHOT.get(), 1)
					.requires(Tags.Items.GRAVEL)
					.requires(Items.PAPER)					
					.unlockedBy("has_gravel", has(Tags.Items.GRAVEL))
					.unlockedBy("has_paper", has(Items.PAPER))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_stone_firearm_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_stone_birdshot"));
		}
		
		// Create medium stone birdshot
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.MEDIUM_STONE_BIRDSHOT.get(), 1)
					.requires(Tags.Items.GRAVEL)
					.requires(Tags.Items.GRAVEL)
					.requires(Items.PAPER)
					.requires(Items.PAPER)
					.unlockedBy("has_gravel", has(Tags.Items.GRAVEL))
					.unlockedBy("has_paper", has(Items.PAPER))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_stone_firearm_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_stone_birdshot"));
		}
		
		// Create large stone birdshot
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.LARGE_STONE_BIRDSHOT.get(), 1)
					.requires(Tags.Items.GRAVEL)
					.requires(Tags.Items.GRAVEL)
					.requires(Tags.Items.GRAVEL)
					.requires(Items.PAPER)
					.requires(Items.PAPER)
					.unlockedBy("has_gravel", has(Tags.Items.GRAVEL))
					.unlockedBy("has_paper", has(Items.PAPER))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_stone_firearm_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_stone_birdshot"));
		}
		
		// Create small iron musket ball
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.SMALL_IRON_MUSKET_BALL.get(), 2)
					.pattern("ii")
					.pattern("ii")
					.define('i', Tags.Items.NUGGETS_IRON)
					.unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_iron_firearm_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_iron_musket_ball"));
		}
		
		// Create medium iron musket ball
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MEDIUM_IRON_MUSKET_BALL.get(), 2)
					.pattern("Ii")
					.pattern("iI")
					.define('i', Tags.Items.NUGGETS_IRON)
					.define('I', Tags.Items.INGOTS_IRON)
					.unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
					.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_iron_firearm_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_iron_musket_ball"));
		}
		
		// Create large iron musket ball
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.LARGE_IRON_MUSKET_BALL.get(), 2)
					.pattern("II")
					.pattern("II")
					.define('I', Tags.Items.INGOTS_IRON)
					.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_iron_firearm_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_iron_musket_ball"));
		}
		
		// Create small iron buckshot
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.SMALL_IRON_BUCKSHOT.get(), 1)
					.requires(ModItems.SMALL_IRON_MUSKET_BALL.get())
					.requires(ModItems.SMALL_IRON_MUSKET_BALL.get())
					.requires(ModItems.SMALL_IRON_MUSKET_BALL.get())
					.requires(Items.PAPER)
					.unlockedBy("has_small_iron_musket_ball", has(ModItems.SMALL_IRON_MUSKET_BALL.get()))
					.unlockedBy("has_paper", has(Items.PAPER))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_iron_firearm_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_iron_buckshot"));
		}
		
		// Create medium iron buckshot
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.MEDIUM_IRON_BUCKSHOT.get(), 1)
					.requires(ModItems.SMALL_IRON_MUSKET_BALL.get())
					.requires(ModItems.SMALL_IRON_MUSKET_BALL.get())
					.requires(ModItems.SMALL_IRON_MUSKET_BALL.get())
					.requires(ModItems.SMALL_IRON_MUSKET_BALL.get())
					.requires(Items.PAPER)
					.requires(Items.PAPER)
					.unlockedBy("has_small_iron_musket_ball", has(ModItems.SMALL_IRON_MUSKET_BALL.get()))
					.unlockedBy("has_paper", has(Items.PAPER))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_iron_firearm_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_iron_buckshot"));
		}
		
		// Create large iron buckshot
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.LARGE_IRON_BUCKSHOT.get(), 1)
					.requires(ModItems.SMALL_IRON_MUSKET_BALL.get())
					.requires(ModItems.SMALL_IRON_MUSKET_BALL.get())
					.requires(ModItems.SMALL_IRON_MUSKET_BALL.get())
					.requires(ModItems.SMALL_IRON_MUSKET_BALL.get())
					.requires(ModItems.SMALL_IRON_MUSKET_BALL.get())					
					.requires(Items.PAPER)
					.requires(Items.PAPER)
					.unlockedBy("has_small_iron_musket_ball", has(ModItems.SMALL_IRON_MUSKET_BALL.get()))
					.unlockedBy("has_paper", has(Items.PAPER))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_iron_firearm_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_iron_buckshot"));
		}
		
		// Create small iron birdshot
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.SMALL_IRON_BIRDSHOT.get(), 1)
					.requires(Tags.Items.NUGGETS_IRON)
					.requires(Tags.Items.NUGGETS_IRON)
					.requires(Tags.Items.NUGGETS_IRON)
					.requires(Items.PAPER)
					.unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
					.unlockedBy("has_paper", has(Items.PAPER))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_iron_firearm_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_iron_birdshot"));
		}
		
		// Create medium iron birdshot
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.MEDIUM_IRON_BIRDSHOT.get(), 1)
					.requires(Tags.Items.NUGGETS_IRON)
					.requires(Tags.Items.NUGGETS_IRON)
					.requires(Tags.Items.NUGGETS_IRON)
					.requires(Tags.Items.NUGGETS_IRON)
					.requires(Items.PAPER)
					.requires(Items.PAPER)
					.unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
					.unlockedBy("has_paper", has(Items.PAPER))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_iron_firearm_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_iron_birdshot"));
		}
		
		// Create large iron birdshot
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.LARGE_IRON_BIRDSHOT.get(), 1)
					.requires(Tags.Items.NUGGETS_IRON)
					.requires(Tags.Items.NUGGETS_IRON)
					.requires(Tags.Items.NUGGETS_IRON)
					.requires(Tags.Items.NUGGETS_IRON)
					.requires(Tags.Items.NUGGETS_IRON)
					.requires(Items.PAPER)
					.requires(Items.PAPER)
					.unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
					.unlockedBy("has_paper", has(Items.PAPER))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_iron_firearm_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_iron_birdshot"));
		}
		
		// Create small lead musket ball
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.SMALL_LEAD_MUSKET_BALL.get(), 2)
					.pattern("ll")
					.pattern("ll")
					.define('l', ModTags.Items.NUGGETS_LEAD)
					.unlockedBy("has_lead_nugget", has(ModTags.Items.NUGGETS_LEAD))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_lead_firearm_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_lead_musket_ball"));
		}
		
		// Create medium lead musket ball
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MEDIUM_LEAD_MUSKET_BALL.get(), 2)
					.pattern("Ll")
					.pattern("lL")
					.define('l', ModTags.Items.NUGGETS_LEAD)
					.define('L', ModTags.Items.INGOTS_LEAD)						
					.unlockedBy("has_lead_nugget", has(ModTags.Items.NUGGETS_LEAD))
					.unlockedBy("has_lead_ingot", has(ModTags.Items.INGOTS_LEAD))		
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_lead_firearm_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_lead_musket_ball"));
		}
		
		// Create large lead musket ball
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.LARGE_LEAD_MUSKET_BALL.get(), 2)
					.pattern("LL")
					.pattern("LL")
					.define('L', ModTags.Items.INGOTS_LEAD)	
					.unlockedBy("has_lead_ingot", has(ModTags.Items.INGOTS_LEAD))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_lead_firearm_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_lead_musket_ball"));
		}
		
		// Create small lead buckshot
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.SMALL_LEAD_BUCKSHOT.get(), 1)
					.requires(ModItems.SMALL_LEAD_MUSKET_BALL.get())
					.requires(ModItems.SMALL_LEAD_MUSKET_BALL.get())
					.requires(ModItems.SMALL_LEAD_MUSKET_BALL.get())
					.requires(Items.PAPER)
					.unlockedBy("has_small_lead_musket_ball", has(ModItems.SMALL_LEAD_MUSKET_BALL.get()))
					.unlockedBy("has_paper", has(Items.PAPER))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_lead_firearm_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_lead_buckshot"));
		}
		
		// Create medium lead buckshot
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.MEDIUM_LEAD_BUCKSHOT.get(), 1)
					.requires(ModItems.SMALL_LEAD_MUSKET_BALL.get())
					.requires(ModItems.SMALL_LEAD_MUSKET_BALL.get())
					.requires(ModItems.SMALL_LEAD_MUSKET_BALL.get())
					.requires(ModItems.SMALL_LEAD_MUSKET_BALL.get())					
					.requires(Items.PAPER)
					.requires(Items.PAPER)
					.unlockedBy("has_small_lead_musket_ball", has(ModItems.SMALL_LEAD_MUSKET_BALL.get()))
					.unlockedBy("has_paper", has(Items.PAPER))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_lead_firearm_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_lead_buckshot"));
		}
		
		// Create large lead buckshot
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.LARGE_LEAD_BUCKSHOT.get(), 1)
					.requires(ModItems.SMALL_LEAD_MUSKET_BALL.get())
					.requires(ModItems.SMALL_LEAD_MUSKET_BALL.get())
					.requires(ModItems.SMALL_LEAD_MUSKET_BALL.get())
					.requires(ModItems.SMALL_LEAD_MUSKET_BALL.get())
					.requires(ModItems.SMALL_LEAD_MUSKET_BALL.get())
					.requires(Items.PAPER)
					.requires(Items.PAPER)
					.unlockedBy("has_small_lead_musket_ball", has(ModItems.SMALL_LEAD_MUSKET_BALL.get()))
					.unlockedBy("has_paper", has(Items.PAPER))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_lead_firearm_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_lead_buckshot"));
		}
		
		// Create small lead birdshot
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.SMALL_LEAD_BIRDSHOT.get(), 1)
					.requires(ModTags.Items.NUGGETS_LEAD)
					.requires(ModTags.Items.NUGGETS_LEAD)
					.requires(ModTags.Items.NUGGETS_LEAD)					
					.requires(Items.PAPER)
					.unlockedBy("has_lead_nugget", has(ModTags.Items.NUGGETS_LEAD))
					.unlockedBy("has_paper", has(Items.PAPER))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_lead_firearm_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_lead_birdshot"));
		}
		
		// Create medium lead birdshot
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.MEDIUM_LEAD_BIRDSHOT.get(), 1)
					.requires(ModTags.Items.NUGGETS_LEAD)
					.requires(ModTags.Items.NUGGETS_LEAD)
					.requires(ModTags.Items.NUGGETS_LEAD)
					.requires(ModTags.Items.NUGGETS_LEAD)
					.requires(Items.PAPER)
					.requires(Items.PAPER)
					.unlockedBy("has_lead_nugget", has(ModTags.Items.NUGGETS_LEAD))					
					.unlockedBy("has_paper", has(Items.PAPER))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_lead_firearm_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_lead_birdshot"));
		}
		
		// Create large lead birdshot
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.LARGE_LEAD_BIRDSHOT.get(), 1)
					.requires(ModTags.Items.NUGGETS_LEAD)
					.requires(ModTags.Items.NUGGETS_LEAD)
					.requires(ModTags.Items.NUGGETS_LEAD)
					.requires(ModTags.Items.NUGGETS_LEAD)
					.requires(ModTags.Items.NUGGETS_LEAD)
					.requires(Items.PAPER)
					.requires(Items.PAPER)
					.unlockedBy("has_lead_nugget", has(ModTags.Items.NUGGETS_LEAD))
					.unlockedBy("has_paper", has(Items.PAPER))
					.condition(new ResourceLocation(OldGuns.MODID, "can_craft_lead_firearm_ammo"))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_lead_birdshot"));
		}
		
		// Cartridges
		// Create small stone musket ball paper cartridges
		shapelessPaperCartridgeRecipe(recipeConsumer, ModItems.SMALL_STONE_MUSKET_BALL_LOW_GRADE_PAPER_CARTRIDGE.get(), ModItems.SMALL_STONE_MUSKET_BALL.get(), ModTags.Items.LOW_GRADE_BLACK_POWDER, 1, new ResourceLocation(OldGuns.MODID, "can_craft_stone_firearm_ammo"));
		shapelessPaperCartridgeRecipe(recipeConsumer, ModItems.SMALL_STONE_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get(), ModItems.SMALL_STONE_MUSKET_BALL.get(), ModTags.Items.MEDIUM_GRADE_BLACK_POWDER, 1, new ResourceLocation(OldGuns.MODID, "can_craft_stone_firearm_ammo"));
		// Create medium stone musket ball paper cartridge
		shapelessPaperCartridgeRecipe(recipeConsumer, ModItems.MEDIUM_STONE_MUSKET_BALL_LOW_GRADE_PAPER_CARTRIDGE.get(), ModItems.MEDIUM_STONE_MUSKET_BALL.get(), ModTags.Items.LOW_GRADE_BLACK_POWDER, 2, new ResourceLocation(OldGuns.MODID, "can_craft_stone_firearm_ammo"));
		shapelessPaperCartridgeRecipe(recipeConsumer, ModItems.MEDIUM_STONE_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get(), ModItems.MEDIUM_STONE_MUSKET_BALL.get(), ModTags.Items.MEDIUM_GRADE_BLACK_POWDER, 2, new ResourceLocation(OldGuns.MODID, "can_craft_stone_firearm_ammo"));
		// Create large stone musket ball paper cartridge
		shapelessPaperCartridgeRecipe(recipeConsumer, ModItems.LARGE_STONE_MUSKET_BALL_LOW_GRADE_PAPER_CARTRIDGE.get(), ModItems.LARGE_STONE_MUSKET_BALL.get(), ModTags.Items.LOW_GRADE_BLACK_POWDER, 2, new ResourceLocation(OldGuns.MODID, "can_craft_stone_firearm_ammo"));
		shapelessPaperCartridgeRecipe(recipeConsumer, ModItems.LARGE_STONE_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get(), ModItems.LARGE_STONE_MUSKET_BALL.get(), ModTags.Items.MEDIUM_GRADE_BLACK_POWDER, 2, new ResourceLocation(OldGuns.MODID, "can_craft_stone_firearm_ammo"));
		
		// Create small iron musket ball paper cartridges
		shapelessPaperCartridgeRecipe(recipeConsumer, ModItems.SMALL_IRON_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get(), ModItems.SMALL_IRON_MUSKET_BALL.get(), ModTags.Items.MEDIUM_GRADE_BLACK_POWDER, 1, new ResourceLocation(OldGuns.MODID, "can_craft_iron_firearm_ammo"));
		shapelessPaperCartridgeRecipe(recipeConsumer, ModItems.SMALL_IRON_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get(), ModItems.SMALL_IRON_MUSKET_BALL.get(), ModTags.Items.HIGH_GRADE_BLACK_POWDER, 1, new ResourceLocation(OldGuns.MODID, "can_craft_iron_firearm_ammo"));
		// Create medium iron musket ball paper cartridges
		shapelessPaperCartridgeRecipe(recipeConsumer, ModItems.MEDIUM_IRON_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get(), ModItems.MEDIUM_IRON_MUSKET_BALL.get(), ModTags.Items.MEDIUM_GRADE_BLACK_POWDER, 2, new ResourceLocation(OldGuns.MODID, "can_craft_iron_firearm_ammo"));
		shapelessPaperCartridgeRecipe(recipeConsumer, ModItems.MEDIUM_IRON_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get(), ModItems.MEDIUM_IRON_MUSKET_BALL.get(), ModTags.Items.HIGH_GRADE_BLACK_POWDER, 2, new ResourceLocation(OldGuns.MODID, "can_craft_iron_firearm_ammo"));
		// Create large iron musket ball paper cartridges
		shapelessPaperCartridgeRecipe(recipeConsumer, ModItems.LARGE_IRON_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get(), ModItems.LARGE_IRON_MUSKET_BALL.get(), ModTags.Items.MEDIUM_GRADE_BLACK_POWDER, 2, new ResourceLocation(OldGuns.MODID, "can_craft_iron_firearm_ammo"));
		shapelessPaperCartridgeRecipe(recipeConsumer, ModItems.LARGE_IRON_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get(), ModItems.LARGE_IRON_MUSKET_BALL.get(), ModTags.Items.HIGH_GRADE_BLACK_POWDER, 2, new ResourceLocation(OldGuns.MODID, "can_craft_iron_firearm_ammo"));
		
		// Create small lead musket ball paper cartridges
		shapelessPaperCartridgeRecipe(recipeConsumer, ModItems.SMALL_LEAD_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get(), ModItems.SMALL_LEAD_MUSKET_BALL.get(), ModTags.Items.MEDIUM_GRADE_BLACK_POWDER, 1, new ResourceLocation(OldGuns.MODID, "can_craft_lead_firearm_ammo"));
		shapelessPaperCartridgeRecipe(recipeConsumer, ModItems.SMALL_LEAD_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get(), ModItems.SMALL_LEAD_MUSKET_BALL.get(), ModTags.Items.HIGH_GRADE_BLACK_POWDER, 1, new ResourceLocation(OldGuns.MODID, "can_craft_lead_firearm_ammo"));
		// Create medium lead musket ball paper cartridges
		shapelessPaperCartridgeRecipe(recipeConsumer, ModItems.MEDIUM_LEAD_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get(), ModItems.MEDIUM_LEAD_MUSKET_BALL.get(), ModTags.Items.MEDIUM_GRADE_BLACK_POWDER, 2, new ResourceLocation(OldGuns.MODID, "can_craft_lead_firearm_ammo"));
		shapelessPaperCartridgeRecipe(recipeConsumer, ModItems.MEDIUM_LEAD_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get(), ModItems.MEDIUM_LEAD_MUSKET_BALL.get(), ModTags.Items.HIGH_GRADE_BLACK_POWDER, 2, new ResourceLocation(OldGuns.MODID, "can_craft_lead_firearm_ammo"));
		// Create large lead musket ball paper cartridges
		shapelessPaperCartridgeRecipe(recipeConsumer, ModItems.LARGE_LEAD_MUSKET_BALL_MEDIUM_GRADE_PAPER_CARTRIDGE.get(), ModItems.LARGE_LEAD_MUSKET_BALL.get(), ModTags.Items.MEDIUM_GRADE_BLACK_POWDER, 2, new ResourceLocation(OldGuns.MODID, "can_craft_lead_firearm_ammo"));
		shapelessPaperCartridgeRecipe(recipeConsumer, ModItems.LARGE_LEAD_MUSKET_BALL_HIGH_GRADE_PAPER_CARTRIDGE.get(), ModItems.LARGE_LEAD_MUSKET_BALL.get(), ModTags.Items.HIGH_GRADE_BLACK_POWDER, 2, new ResourceLocation(OldGuns.MODID, "can_craft_lead_firearm_ammo"));		
		
		// Parts
		// Create tiny stone barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.TINY_STONE_BARREL.get())
					.pattern("sss")
					.pattern(" ss")
					.define('s', Items.STONE_SLAB)
					.unlockedBy("has_stone_slab", has(Items.STONE_SLAB))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "tiny_stone_barrel"));
		}
				
		// Create small stone barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.SMALL_STONE_BARREL.get())
					.pattern("SSs")
					.define('S', Items.STONE)
					.define('s', Items.STONE_SLAB)
					.unlockedBy("has_stone_slab", has(Items.STONE_SLAB))
					.unlockedBy("has_stone", has(Items.STONE))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_stone_barrel"));
		}
		
		// Create medium stone barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MEDIUM_STONE_BARREL.get())
					.pattern("SSs")
					.pattern(" sS")
					.define('S', Items.STONE)
					.define('s', Items.STONE_SLAB)
					.unlockedBy("has_stone_slab", has(Items.STONE_SLAB))
					.unlockedBy("has_stone", has(Items.STONE))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_stone_barrel"));
		}

		// Create large stone barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.LARGE_STONE_BARREL.get())
					.pattern("SSS")
					.pattern(" sS")
					.define('S', Items.STONE)
					.define('s', Items.STONE_SLAB)
					.unlockedBy("has_stone_slab", has(Items.STONE_SLAB))
					.unlockedBy("has_stone", has(Items.STONE))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_stone_barrel"));
		}
				
		// Create tiny iron barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.TINY_IRON_BARREL.get())
					.pattern("iii")
					.pattern(" ii")
					.define('i', Tags.Items.NUGGETS_IRON)
					.unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "tiny_iron_barrel"));
		}
				
		// Create small iron barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.SMALL_IRON_BARREL.get())
					.pattern("IIi")
					.define('I', Tags.Items.INGOTS_IRON)
					.define('i', Tags.Items.NUGGETS_IRON)
					.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
					.unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_iron_barrel"));
		}
		
		// Create medium iron barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MEDIUM_IRON_BARREL.get())
					.pattern("IIi")
					.pattern(" iI")
					.define('I', Tags.Items.INGOTS_IRON)
					.define('i', Tags.Items.NUGGETS_IRON)
					.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
					.unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_iron_barrel"));
		}

		// Create large iron barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.LARGE_IRON_BARREL.get())
					.pattern("III")
					.pattern(" iI")
					.define('I', Tags.Items.INGOTS_IRON)
					.define('i', Tags.Items.NUGGETS_IRON)
					.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
					.unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_iron_barrel"));
		}
		
		// Create tiny brass barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.TINY_BRASS_BARREL.get())
					.pattern("bbb")
					.pattern(" bb")
					.define('b', ModTags.Items.NUGGETS_BRASS)
					.unlockedBy("has_brass_nugget", has(ModTags.Items.NUGGETS_BRASS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "tiny_brass_barrel"));
		}
		
		// Create small brass barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.SMALL_BRASS_BARREL.get())
					.pattern("BBb")
					.define('B', ModTags.Items.INGOTS_BRASS)
					.define('b', ModTags.Items.NUGGETS_BRASS)
					.unlockedBy("has_brass_ingot", has(ModTags.Items.INGOTS_BRASS))
					.unlockedBy("has_brass_nugget", has(ModTags.Items.NUGGETS_BRASS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_brass_barrel"));
		}
		
		// Create medium brass barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MEDIUM_BRASS_BARREL.get())
					.pattern("BBb")
					.pattern(" bB")
					.define('B', ModTags.Items.INGOTS_BRASS)
					.define('b', ModTags.Items.NUGGETS_BRASS)
					.unlockedBy("has_brass_ingot", has(ModTags.Items.INGOTS_BRASS))
					.unlockedBy("has_brass_nugget", has(ModTags.Items.NUGGETS_BRASS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_brass_barrel"));
		}

		// Create large brass barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.LARGE_BRASS_BARREL.get())
					.pattern("BBB")
					.pattern(" bB")
					.define('B', ModTags.Items.INGOTS_BRASS)
					.define('b', ModTags.Items.NUGGETS_BRASS)
					.unlockedBy("has_brass_ingot", has(ModTags.Items.INGOTS_BRASS))
					.unlockedBy("has_brass_nugget", has(ModTags.Items.NUGGETS_BRASS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_brass_barrel"));
		}
		
		// Create small stone flared barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.SMALL_STONE_FLARED_BARREL.get())
					.pattern("s ")
					.pattern(" X")
					.pattern("s ")
					.define('X', ModItems.SMALL_STONE_BARREL.get())
					.define('s', Items.STONE_SLAB)
					.unlockedBy("has_small_stone_barrel", has(ModItems.SMALL_STONE_BARREL.get()))
					.unlockedBy("has_stone_slab", has(Items.STONE_SLAB))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_stone_flared_barrel"));
		}
		
		// Create medium stone flared barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MEDIUM_STONE_FLARED_BARREL.get())
					.pattern("S ")
					.pattern(" X")
					.pattern("S ")
					.define('X', ModItems.MEDIUM_STONE_BARREL.get())
					.define('S', Items.STONE)
					.unlockedBy("has_medium_stone_barrel", has(ModItems.MEDIUM_STONE_BARREL.get()))
					.unlockedBy("has_stone", has(Items.STONE))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_stone_flared_barrel"));
		}
		
		// Create large stone flared barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.LARGE_STONE_FLARED_BARREL.get())
					.pattern("Ss ")
					.pattern("  X")
					.pattern("Ss ")
					.define('X', ModItems.LARGE_STONE_BARREL.get())
					.define('S', Items.STONE)
					.define('s', Items.STONE_SLAB)
					.unlockedBy("has_large_stone_barrel", has(ModItems.LARGE_STONE_BARREL.get()))
					.unlockedBy("has_stone_slab", has(Items.STONE_SLAB))
					.unlockedBy("has_stone", has(Items.STONE))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_stone_flared_barrel"));
		}
		
		// Create small iron flared barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.SMALL_IRON_FLARED_BARREL.get())
					.pattern("i ")
					.pattern(" X")
					.pattern("i ")
					.define('X', ModItems.SMALL_IRON_BARREL.get())
					.define('i', Tags.Items.NUGGETS_IRON)
					.unlockedBy("has_small_iron_barrel", has(ModItems.SMALL_IRON_BARREL.get()))
					.unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_iron_flared_barrel"));
		}
		
		// Create medium iron flared barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MEDIUM_IRON_FLARED_BARREL.get())
					.pattern("I ")
					.pattern(" X")
					.pattern("I ")
					.define('X', ModItems.MEDIUM_IRON_BARREL.get())
					.define('I', Tags.Items.INGOTS_IRON)
					.unlockedBy("has_medium_iron_barrel", has(ModItems.MEDIUM_IRON_BARREL.get()))
					.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_iron_flared_barrel"));
		}
		
		// Create large iron flared barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.LARGE_IRON_FLARED_BARREL.get())
					.pattern("Ii ")
					.pattern("  X")
					.pattern("Ii ")
					.define('X', ModItems.LARGE_IRON_BARREL.get())
					.define('I', Tags.Items.INGOTS_IRON)
					.define('i', Tags.Items.NUGGETS_IRON)
					.unlockedBy("has_large_iron_barrel", has(ModItems.LARGE_IRON_BARREL.get()))
					.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
					.unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_iron_flared_barrel"));
		}
		
		// Create small brass flared barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.SMALL_BRASS_FLARED_BARREL.get())
					.pattern("b ")
					.pattern(" X")
					.pattern("b ")
					.define('X', ModItems.SMALL_BRASS_BARREL.get())
					.define('b', ModTags.Items.NUGGETS_BRASS)
					.unlockedBy("has_small_brass_barrel", has(ModItems.SMALL_BRASS_BARREL.get()))
					.unlockedBy("has_brass_nugget", has(ModTags.Items.NUGGETS_BRASS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_brass_flared_barrel"));
		}
		
		// Create medium brass flared barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MEDIUM_BRASS_FLARED_BARREL.get())
					.pattern("B ")
					.pattern(" X")
					.pattern("B ")
					.define('X', ModItems.MEDIUM_BRASS_BARREL.get())
					.define('B', ModTags.Items.INGOTS_BRASS)
					.unlockedBy("has_medium_brass_barrel", has(ModItems.MEDIUM_IRON_BARREL.get()))
					.unlockedBy("has_brass_ingot", has(ModTags.Items.INGOTS_BRASS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_brass_flared_barrel"));
		}
		
		// Create large brass flared barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.LARGE_BRASS_FLARED_BARREL.get())
					.pattern("Bb ")
					.pattern("  X")
					.pattern("Bb ")
					.define('X', ModItems.LARGE_BRASS_BARREL.get())
					.define('B', ModTags.Items.INGOTS_BRASS)
					.define('b', ModTags.Items.NUGGETS_BRASS)
					.unlockedBy("has_large_brass_barrel", has(ModItems.LARGE_BRASS_BARREL.get()))
					.unlockedBy("has_brass_ingot", has(ModTags.Items.INGOTS_BRASS))
					.unlockedBy("has_brass_nugget", has(ModTags.Items.NUGGETS_BRASS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_iron_brass_barrel"));
		}
		
		
		// Create small wooden handle
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.SMALL_WOODEN_HANDLE.get())
					.pattern("SR")
					.define('S', ItemTags.WOODEN_SLABS)
					.define('R', Tags.Items.RODS_WOODEN)
					.unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
					.unlockedBy("has_wooden_slab", has(ItemTags.WOODEN_SLABS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_wooden_handle"));
		}
		
		// Create medium wooden handle
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MEDIUM_WOODEN_HANDLE.get())
					.pattern("SSR")
					.define('S', ItemTags.WOODEN_SLABS)
					.define('R', Tags.Items.RODS_WOODEN)
					.unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
					.unlockedBy("has_wooden_slab", has(ItemTags.WOODEN_SLABS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_wooden_handle"));
		}
		
		// Create large wooden handle
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.LARGE_WOODEN_HANDLE.get())
					.pattern("SS ")
					.pattern(" RR")
					.define('S', ItemTags.WOODEN_SLABS)
					.define('R', Tags.Items.RODS_WOODEN)
					.unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
					.unlockedBy("has_wooden_slab", has(ItemTags.WOODEN_SLABS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_wooden_handle"));
		}
		
		// Create small wooden stock
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.SMALL_WOODEN_STOCK.get())
					.pattern("SL")
					.define('S', ItemTags.WOODEN_SLABS)
					.define('L', ItemTags.LOGS)
					.unlockedBy("has_wooden_slab", has(ItemTags.WOODEN_SLABS))
					.unlockedBy("has_wooden_log", has(ItemTags.LOGS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_wooden_stock"));
		}
		
		// Create medium wooden stock
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MEDIUM_WOODEN_STOCK.get())
					.pattern("SLL")
					.define('S', ItemTags.WOODEN_SLABS)
					.define('L', ItemTags.LOGS)
					.unlockedBy("has_wooden_slab", has(ItemTags.WOODEN_SLABS))
					.unlockedBy("has_wooden_log", has(ItemTags.LOGS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_wooden_stock"));
		}
		
		// Create large wooden stock
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.LARGE_WOODEN_STOCK.get())
					.pattern("LLL")
					.pattern(" SS")
					.define('S', ItemTags.WOODEN_SLABS)
					.define('L', ItemTags.LOGS)
					.unlockedBy("has_wooden_slab", has(ItemTags.WOODEN_SLABS))
					.unlockedBy("has_wooden_log", has(ItemTags.LOGS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_wooden_stock"));
		}
		
		// Create wood gear set
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.WOOD_GEAR_SET.get(), 2)
					.pattern(" S ")
					.pattern("SPS")
					.pattern(" S ")
					.define('S', ItemTags.WOODEN_SLABS)
					.define('P', ItemTags.PLANKS)
					.unlockedBy("has_wooden_slab", has(ItemTags.WOODEN_SLABS))
					.unlockedBy("has_plank", has(ItemTags.PLANKS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wood_gear_set"));
		}
		
		// Create iron gear set
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.IRON_GEAR_SET.get(), 2)
					.pattern(" i ")
					.pattern("iIi")
					.pattern(" i ")
					.define('i', Tags.Items.NUGGETS_IRON)
					.define('I', Tags.Items.INGOTS_IRON)
					.unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
					.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "iron_gear_set"));
		}
		
		// Create gold gear set
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.GOLD_GEAR_SET.get(), 2)
					.pattern(" g ")
					.pattern("gGg")
					.pattern(" g ")
					.define('g', Tags.Items.NUGGETS_GOLD)
					.define('G', Tags.Items.INGOTS_GOLD)
					.unlockedBy("has_gold_nugget", has(Tags.Items.NUGGETS_GOLD))
					.unlockedBy("has_gold_ingot", has(Tags.Items.INGOTS_GOLD))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "gold_gear_set"));
		}
		
		// Create wood trigger assembly
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.WOOD_TRIGGER_ASSEMBLY.get())
					.pattern(" S ")
					.pattern("PEP")
					.pattern("  L")
					.define('E', ModTags.Items.WOOD_GEAR_SET)
					.define('P', ItemTags.PLANKS)
					.define('S', Tags.Items.RODS_WOODEN)
					.define('L', Items.LEVER)
					.unlockedBy("has_wood_gear_set", has(ModTags.Items.WOOD_GEAR_SET))
					.unlockedBy("has_plank", has(ItemTags.PLANKS))
					.unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
					.unlockedBy("has_lever", has(Items.LEVER))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wood_trigger_assembly"));
		}
		
		// Create iron trigger assembly
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.IRON_TRIGGER_ASSEMBLY.get())
					.pattern(" T ")
					.pattern("IEI")
					.pattern("  L")
					.define('E', ModTags.Items.IRON_GEAR_SET)
					.define('I', Tags.Items.INGOTS_IRON)
					.define('L', Items.LEVER)
					.define('T', Items.TRIPWIRE_HOOK)
					.unlockedBy("has_iron_gear_set", has(ModTags.Items.IRON_GEAR_SET))
					.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
					.unlockedBy("has_lever", has(Items.LEVER))
					.unlockedBy("has_tripwire_hook", has(Items.TRIPWIRE_HOOK))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "iron_trigger_assembly"));
		}
		
		// Create gold trigger assembly
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.GOLD_TRIGGER_ASSEMBLY.get())
					.pattern(" T ")
					.pattern("GEG")
					.pattern("  L")
					.define('E', ModTags.Items.GOLD_GEAR_SET)
					.define('G', Tags.Items.INGOTS_GOLD)
					.define('L', Items.LEVER)
					.define('T', Items.TRIPWIRE_HOOK)
					.unlockedBy("has_gold_gear_set", has(ModTags.Items.GOLD_GEAR_SET))
					.unlockedBy("has_gold_ingot", has(Tags.Items.INGOTS_GOLD))
					.unlockedBy("has_lever", has(Items.LEVER))
					.unlockedBy("has_tripwire_hook", has(Items.TRIPWIRE_HOOK))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "gold_trigger_assembly"));
		}
		
		// Create match cord
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.MATCH_CORD.get())
					.requires(Tags.Items.STRING)
					.requires(Items.TORCH)			
					.unlockedBy("has_string", has(Tags.Items.STRING))
					.unlockedBy("has_torch", has(Items.TORCH))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "match_cord"));
		}
		
		// Create matchlock mechanism
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MATCHLOCK_MECHANISM.get())
					.pattern(" M ")
					.pattern("STS")
					.pattern(" E ")
					.define('M', ModTags.Items.MATCH_CORD)
					.define('S', Items.STONE)
					.define('T', ModTags.Items.WOOD_TRIGGER_ASSEMBLY)
					.define('E', ModTags.Items.WOOD_GEAR_SET)
					.unlockedBy("has_match_cord", has(ModTags.Items.MATCH_CORD))
					.unlockedBy("has_stone", has(Items.STONE))
					.unlockedBy("has_wood_trigger_assembly", has(ModTags.Items.WOOD_TRIGGER_ASSEMBLY))
					.unlockedBy("has_wood_gear_set", has(ModTags.Items.WOOD_GEAR_SET))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "matchlock_mechansim"));
		}
		
		// Create wheellock mechanism
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.WHEELLOCK_MECHANISM.get())
					.pattern("EFE")
					.pattern("ITI")
					.pattern(" E ")
					.define('F', Items.FLINT)
					.define('I', Tags.Items.INGOTS_IRON)
					.define('T', ModTags.Items.IRON_TRIGGER_ASSEMBLY)
					.define('E', ModTags.Items.IRON_GEAR_SET)
					.unlockedBy("has_flint", has(Items.FLINT))
					.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
					.unlockedBy("has_iron_trigger_assembly", has(ModTags.Items.IRON_TRIGGER_ASSEMBLY))
					.unlockedBy("has_iron_gear_set", has(ModTags.Items.IRON_GEAR_SET))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wheellock_mechansim"));
		}
		
		// Create flintlock mechanism
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.FLINTLOCK_MECHANISM.get())
					.pattern(" FL")
					.pattern("GTG")
					.pattern(" E ")
					.define('F', Items.FLINT)
					.define('L', Items.LEVER)
					.define('G', Tags.Items.INGOTS_GOLD)
					.define('T', ModTags.Items.GOLD_TRIGGER_ASSEMBLY)
					.define('E', ModTags.Items.GOLD_GEAR_SET)
					.unlockedBy("has_flint", has(Items.FLINT))
					.unlockedBy("has_lever", has(Items.LEVER))
					.unlockedBy("has_gold_ingot", has(Tags.Items.INGOTS_GOLD))
					.unlockedBy("has_gold_trigger_assembly", has(ModTags.Items.GOLD_TRIGGER_ASSEMBLY))
					.unlockedBy("has_gold_gear_set", has(ModTags.Items.GOLD_GEAR_SET))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_mechansim"));
		}
		
		// Tools
		// Create repair kit
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.REPAIR_KIT.get())
					.requires(Tags.Items.SLIMEBALLS)
					.requires(ItemTags.WOOL)
					.requires(Tags.Items.RODS_WOODEN)
					.requires(Tags.Items.LEATHER)
					.requires(ItemTags.LOGS)
					.requires(Items.LEVER)
					.unlockedBy("has_firearm", has(ModTags.Items.FIREARM))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "repair_kit"));
		}
		
		// Create mortar and pestle
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MORTAR_AND_PESTLE.get())
					.pattern(" L ")
					.pattern("G G")
					.pattern(" G ")
					.define('L', Items.LEVER)
					.define('G', Items.POLISHED_GRANITE)
					.unlockedBy("has_lever", has(Items.LEVER))
					.unlockedBy("has_polished_granite", has(Items.POLISHED_GRANITE))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "mortar_and_pestle"));
		}
		
	}
	
	private static void shapelessMuzzleloaderSingleReloadRecipe(Consumer<FinishedRecipe> recipeConsumer, Item firearm, Tag<Item> inputAmmo, Tag<Item> inputPowder, int powderAmount) {
		ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(firearm)
			.requires(firearm)
			.requires(inputAmmo)
			.requires(inputPowder, powderAmount)
			.unlockedBy("has_firearm", has(firearm))
			.unlockedBy("has_ammo", has(inputAmmo))
			.unlockedBy("has_powder", has(inputPowder))
			.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, firearm.getRegistryName().getPath() + "_" + inputAmmo.toString().toLowerCase().replace("[", "").replace("]", "").replace("namedtag", "").replace(OldGuns.MODID + ":", "") + "_reload"));
	}
	
	private static void shapelessMuzzleloaderSingleCartridgeReloadRecipe(Consumer<FinishedRecipe> recipeConsumer, Item firearm, Tag<Item> inputCartridge) {
		ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(firearm)
			.requires(firearm)
			.requires(inputCartridge)
			.unlockedBy("has_firearm", has(firearm))
			.unlockedBy("has_cartridge", has(inputCartridge))
			.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, firearm.getRegistryName().getPath() + "_" + inputCartridge.toString().toLowerCase().replace("[", "").replace("]", "").replace("namedtag", "").replace(OldGuns.MODID + ":", "") + "_reload"));
	}
	
	
	private static void shapelessPaperCartridgeRecipe(Consumer<FinishedRecipe> recipeConsumer, Item outputCartridge, Item inputAmmo, Tag<Item> inputPowder, int powderAmount, ResourceLocation ammoCraftCondition) {
		ShapelessGunsmithsBenchRecipeBuilder.shapeless(outputCartridge)
			.requires(inputAmmo)		
			.requires(Tags.Items.STRING)
			.requires(ModItems.WAXED_PAPER.get())
			.requires(inputPowder, powderAmount)					
			.unlockedBy("has_ammo", has(inputAmmo))
			.unlockedBy("has_string", has(Tags.Items.STRING))
			.unlockedBy("has_waxed_paper", has(ModItems.WAXED_PAPER.get()))
			.unlockedBy("has_black_powder", has(inputPowder))
			.condition(ammoCraftCondition)
			.condition(new ResourceLocation(OldGuns.MODID, "can_craft_paper_cartridges"))
			.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, outputCartridge.getRegistryName().getPath()));
	}
	
	@Override
	public String getName() {
		return "OldGunsRecipes";
	}
}
