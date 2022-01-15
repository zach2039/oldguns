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
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author grilled-salmon
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
			ShapelessRecipeBuilder.shapeless(ModItems.LEAD_INGOT.get(), 9)
					.requires(ModTags.Items.INGOTS_LEAD)
					.unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE))
					.unlockedBy("has_lead_ingot", has(ModTags.Items.INGOTS_LEAD))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "lead_nugget_vanilla"));
			
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.LEAD_INGOT.get(), 9)
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
		
		// Medium grade black powder from niter, sulfur, and charcoal
		{
			ShapelessVanillaMortarAndPestleRecipeBuilder.shapeless(ModItems.MEDIUM_GRADE_BLACK_POWDER.get(), 3)
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
		{
			ShapelessVanillaMortarAndPestleRecipeBuilder.shapeless(ModItems.MEDIUM_GRADE_BLACK_POWDER.get(), 9)
					.requires(ModBlocks.MEDIUM_GRADE_BLACK_POWDER_BLOCK.get())
					.unlockedBy("has_medium_grade_black_powder_cake", has(ModBlocks.MEDIUM_GRADE_BLACK_POWDER_BLOCK.get()))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_grade_black_powder_from_block_vanilla"));

			ShapelessGunsmithsBenchMortarAndPestleRecipeBuilder.shapeless(ModItems.MEDIUM_GRADE_BLACK_POWDER.get(), 9)
					.requires(ModBlocks.MEDIUM_GRADE_BLACK_POWDER_BLOCK.get())
					.unlockedBy("has_medium_grade_black_powder_cake", has(ModBlocks.MEDIUM_GRADE_BLACK_POWDER_BLOCK.get()))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_grade_black_powder_from_block"));
		}
		
		// High grade black powder block from high grade black powder
		{
			ShapelessVanillaMortarAndPestleRecipeBuilder.shapeless(ModBlocks.HIGH_GRADE_BLACK_POWDER_BLOCK.get())
					.requires(ModItems.HIGH_GRADE_BLACK_POWDER.get(), 9)
					.unlockedBy("has_high_grade_black_powder", has(ModItems.HIGH_GRADE_BLACK_POWDER.get()))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "high_grade_black_powder_block_vanilla"));

			ShapelessGunsmithsBenchMortarAndPestleRecipeBuilder.shapeless(ModBlocks.HIGH_GRADE_BLACK_POWDER_BLOCK.get())
					.requires(ModItems.HIGH_GRADE_BLACK_POWDER.get(), 9)
					.unlockedBy("has_high_grade_black_powder", has(ModItems.HIGH_GRADE_BLACK_POWDER.get()))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "high_grade_black_powder_block"));
		}
		
		// Medium grade black powder block from high grade black powder
		{
			ShapelessVanillaMortarAndPestleRecipeBuilder.shapeless(ModBlocks.MEDIUM_GRADE_BLACK_POWDER_BLOCK.get())
					.requires(ModItems.MEDIUM_GRADE_BLACK_POWDER.get(), 9)
					.unlockedBy("has_medium_grade_black_powder", has(ModItems.MEDIUM_GRADE_BLACK_POWDER.get()))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_grade_black_powder_block_vanilla"));

			ShapelessGunsmithsBenchMortarAndPestleRecipeBuilder.shapeless(ModBlocks.MEDIUM_GRADE_BLACK_POWDER_BLOCK.get())
					.requires(ModItems.MEDIUM_GRADE_BLACK_POWDER.get(), 9)
					.unlockedBy("has_medium_grade_black_powder", has(ModItems.MEDIUM_GRADE_BLACK_POWDER.get()))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_grade_black_powder_block"));
		}
		
		// Workshops 
		// Create gunsmiths bench
		{
			ShapedRecipeBuilder.shaped(ModBlocks.GUNSMITHS_BENCH.get())
					.pattern("LLC")
					.pattern("PGP")
					.define('L', ItemTags.LOGS)
					.define('C', Tags.Items.COBBLESTONE)
					.define('P', ItemTags.PLANKS)
					.define('G', ModTags.Items.ANY_GUNPOWDER)	
					.unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "gunsmiths_bench"));
		}
			
		// Firearm Repair
		{
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
		}
		
		// Firearm Reloading
		// Reload matchlock derringer
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.MATCHLOCK_DERRINGER.get())
					.requires(ModItems.MATCHLOCK_DERRINGER.get())
					.requires(ModTags.Items.SMALL_ROCK_MUSKET_BALL)
					.requires(ModTags.Items.MATCHLOCK_SUITABLE_POWDER)
					.unlockedBy("has_matchlock_derringer", has(ModItems.MATCHLOCK_DERRINGER.get()))
					.unlockedBy("has_small_rock_musket_ball", has(ModTags.Items.SMALL_ROCK_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "matchlock_derringer_reload"));
		}
				
		// Reload matchlock pistol
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.MATCHLOCK_PISTOL.get())
					.requires(ModItems.MATCHLOCK_PISTOL.get())
					.requires(ModTags.Items.SMALL_ROCK_MUSKET_BALL)
					.requires(ModTags.Items.MATCHLOCK_SUITABLE_POWDER)
					.unlockedBy("has_matchlock_pistol", has(ModItems.MATCHLOCK_PISTOL.get()))
					.unlockedBy("has_small_rock_musket_ball", has(ModTags.Items.SMALL_ROCK_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "matchlock_pistol_reload"));
		}
		
		// Reload matchlock arquebus
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.MATCHLOCK_ARQUEBUS.get())
					.requires(ModItems.MATCHLOCK_ARQUEBUS.get())
					.requires(ModTags.Items.SMALL_ROCK_MUSKET_BALL)
					.requires(ModTags.Items.MATCHLOCK_SUITABLE_POWDER)
					.unlockedBy("has_matchlock_arquebus", has(ModItems.MATCHLOCK_ARQUEBUS.get()))
					.unlockedBy("has_small_rock_musket_ball", has(ModTags.Items.SMALL_ROCK_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "matchlock_arquebus_reload"));
		}
		
		// Reload matchlock caliver
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.MATCHLOCK_CALIVER.get())
					.requires(ModItems.MATCHLOCK_CALIVER.get())
					.requires(ModTags.Items.MEDIUM_ROCK_MUSKET_BALL)
					.requires(ModTags.Items.MATCHLOCK_SUITABLE_POWDER)
					.requires(ModTags.Items.MATCHLOCK_SUITABLE_POWDER)
					.unlockedBy("has_matchlock_caliver", has(ModItems.MATCHLOCK_CALIVER.get()))
					.unlockedBy("has_medium_rock_musket_ball", has(ModTags.Items.MEDIUM_ROCK_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "matchlock_caliver_reload"));
		}
		
		// Reload matchlock musketoon ball and birdshot
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.MATCHLOCK_MUSKETOON.get())
					.requires(ModItems.MATCHLOCK_MUSKETOON.get())
					.requires(ModTags.Items.MEDIUM_ROCK_MUSKET_BALL)
					.requires(ModTags.Items.MATCHLOCK_SUITABLE_POWDER)
					.requires(ModTags.Items.MATCHLOCK_SUITABLE_POWDER)
					.unlockedBy("has_matchlock_musketoon", has(ModItems.MATCHLOCK_MUSKETOON.get()))
					.unlockedBy("has_medium_rock_musket_ball", has(ModTags.Items.MEDIUM_ROCK_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "matchlock_musketoon_ball_reload"));
		}
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.MATCHLOCK_MUSKETOON.get())
					.requires(ModItems.MATCHLOCK_MUSKETOON.get())
					.requires(ModTags.Items.MEDIUM_ROCK_BIRDSHOT)
					.requires(ModTags.Items.MATCHLOCK_SUITABLE_POWDER)
					.requires(ModTags.Items.MATCHLOCK_SUITABLE_POWDER)
					.unlockedBy("has_matchlock_musketoon", has(ModItems.FLINTLOCK_MUSKETOON.get()))
					.unlockedBy("has_medium_rock_birdshot", has(ModTags.Items.MEDIUM_ROCK_BIRDSHOT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "matchlock_musketoon_birdshot_reload"));
		}
		
		// Reload matchlock musket
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.MATCHLOCK_MUSKET.get())
					.requires(ModItems.MATCHLOCK_MUSKET.get())
					.requires(ModTags.Items.LARGE_ROCK_MUSKET_BALL)
					.requires(ModTags.Items.MATCHLOCK_SUITABLE_POWDER)
					.requires(ModTags.Items.MATCHLOCK_SUITABLE_POWDER)
					.unlockedBy("has_matchlock_musket", has(ModItems.MATCHLOCK_MUSKET.get()))
					.unlockedBy("has_large_rock_musket_ball", has(ModTags.Items.LARGE_ROCK_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "matchlock_musket_reload"));
		}
		
		// Reload matchlock long musket
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.MATCHLOCK_LONG_MUSKET.get())
					.requires(ModItems.MATCHLOCK_LONG_MUSKET.get())
					.requires(ModTags.Items.LARGE_ROCK_MUSKET_BALL)
					.requires(ModTags.Items.MATCHLOCK_SUITABLE_POWDER)
					.requires(ModTags.Items.MATCHLOCK_SUITABLE_POWDER)
					.unlockedBy("has_matchlock_long_musket", has(ModItems.MATCHLOCK_LONG_MUSKET.get()))
					.unlockedBy("has_large_rock_musket_ball", has(ModTags.Items.LARGE_ROCK_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "matchlock_long_musket_reload"));
		}
		
		// Reload matchlock blunderbuss pistol birdshot
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.MATCHLOCK_BLUNDERBUSS_PISTOL.get())
					.requires(ModItems.MATCHLOCK_BLUNDERBUSS_PISTOL.get())
					.requires(ModTags.Items.SMALL_ROCK_BIRDSHOT)
					.requires(ModTags.Items.MATCHLOCK_SUITABLE_POWDER)
					.unlockedBy("has_matchlock_blunderbuss_pistol", has(ModItems.MATCHLOCK_BLUNDERBUSS_PISTOL.get()))
					.unlockedBy("has_small_rock_birdshot", has(ModTags.Items.SMALL_ROCK_BIRDSHOT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "matchlock_blunderbuss_pistol_birdshot_reload"));
		}
		
		// Reload matchlock blunderbuss birdshot
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.MATCHLOCK_BLUNDERBUSS.get())
					.requires(ModItems.MATCHLOCK_BLUNDERBUSS.get())
					.requires(ModTags.Items.LARGE_ROCK_BIRDSHOT)
					.requires(ModTags.Items.MATCHLOCK_SUITABLE_POWDER)
					.requires(ModTags.Items.MATCHLOCK_SUITABLE_POWDER)
					.unlockedBy("has_matchlock_blunderbuss", has(ModItems.MATCHLOCK_BLUNDERBUSS.get()))
					.unlockedBy("has_large_rock_birdshot", has(ModTags.Items.LARGE_ROCK_BIRDSHOT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "matchlock_blunderbuss_birdshot_reload"));
		}

		// Wheellock
		// Reload wheellock derringer
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.WHEELLOCK_DERRINGER.get())
					.requires(ModItems.WHEELLOCK_DERRINGER.get())
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(ModTags.Items.WHEELLOCK_SUITABLE_POWDER)
					.unlockedBy("has_wheellock_derringer", has(ModItems.WHEELLOCK_DERRINGER.get()))
					.unlockedBy("has_small_metal_musket_ball", has(ModTags.Items.SMALL_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wheellock_derringer_reload"));
		}
				
		// Reload wheellock pistol
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.WHEELLOCK_PISTOL.get())
					.requires(ModItems.WHEELLOCK_PISTOL.get())
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(ModTags.Items.WHEELLOCK_SUITABLE_POWDER)
					.unlockedBy("has_wheellock_pistol", has(ModItems.WHEELLOCK_PISTOL.get()))
					.unlockedBy("has_small_metal_musket_ball", has(ModTags.Items.SMALL_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wheellock_pistol_reload"));
		}
		
		// Reload wheellock doublebarrel pistol
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.WHEELLOCK_DOUBLEBARREL_PISTOL.get())
					.requires(ModItems.WHEELLOCK_DOUBLEBARREL_PISTOL.get())
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(ModTags.Items.WHEELLOCK_SUITABLE_POWDER)
					.unlockedBy("has_wheellock_doublebarrel_pistol", has(ModItems.WHEELLOCK_DOUBLEBARREL_PISTOL.get()))
					.unlockedBy("has_small_metal_musket_ball", has(ModTags.Items.SMALL_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wheellock_doublebarrel_pistol_reload"));
		}
		
		// Reload wheellock arquebus
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.WHEELLOCK_ARQUEBUS.get())
					.requires(ModItems.WHEELLOCK_ARQUEBUS.get())
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(ModTags.Items.WHEELLOCK_SUITABLE_POWDER)
					.unlockedBy("has_wheellock_arquebus", has(ModItems.WHEELLOCK_ARQUEBUS.get()))
					.unlockedBy("has_small_metal_musket_ball", has(ModTags.Items.SMALL_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wheellock_arquebus_reload"));
		}
		
		// Reload wheellock caliver
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.WHEELLOCK_CALIVER.get())
					.requires(ModItems.WHEELLOCK_CALIVER.get())
					.requires(ModTags.Items.MEDIUM_METAL_MUSKET_BALL)
					.requires(ModTags.Items.WHEELLOCK_SUITABLE_POWDER)
					.requires(ModTags.Items.WHEELLOCK_SUITABLE_POWDER)
					.unlockedBy("has_wheellock_caliver", has(ModItems.WHEELLOCK_CALIVER.get()))
					.unlockedBy("has_medium_metal_musket_ball", has(ModTags.Items.MEDIUM_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wheellock_caliver_reload"));
		}
		
		// Reload wheellock musketoon ball and birdshot
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.WHEELLOCK_MUSKETOON.get())
					.requires(ModItems.WHEELLOCK_MUSKETOON.get())
					.requires(ModTags.Items.MEDIUM_METAL_MUSKET_BALL)
					.requires(ModTags.Items.WHEELLOCK_SUITABLE_POWDER)
					.requires(ModTags.Items.WHEELLOCK_SUITABLE_POWDER)
					.unlockedBy("has_wheellock_musketoon", has(ModItems.WHEELLOCK_MUSKETOON.get()))
					.unlockedBy("has_medium_metal_musket_ball", has(ModTags.Items.MEDIUM_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wheellock_musketoon_ball_reload"));
		}
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.WHEELLOCK_MUSKETOON.get())
					.requires(ModItems.WHEELLOCK_MUSKETOON.get())
					.requires(ModTags.Items.MEDIUM_METAL_BIRDSHOT)
					.requires(ModTags.Items.WHEELLOCK_SUITABLE_POWDER)
					.requires(ModTags.Items.WHEELLOCK_SUITABLE_POWDER)
					.unlockedBy("has_wheellock_musketoon", has(ModItems.WHEELLOCK_MUSKETOON.get()))
					.unlockedBy("has_medium_metal_birdshot", has(ModTags.Items.MEDIUM_METAL_BIRDSHOT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wheellock_musketoon_birdshot_reload"));
		}
		
		// Reload wheellock musket
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.WHEELLOCK_MUSKET.get())
					.requires(ModItems.WHEELLOCK_MUSKET.get())
					.requires(ModTags.Items.LARGE_METAL_MUSKET_BALL)
					.requires(ModTags.Items.WHEELLOCK_SUITABLE_POWDER)
					.requires(ModTags.Items.WHEELLOCK_SUITABLE_POWDER)
					.unlockedBy("has_wheellock_musket", has(ModItems.WHEELLOCK_MUSKET.get()))
					.unlockedBy("has_large_metal_musket_ball", has(ModTags.Items.LARGE_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wheellock_musket_reload"));
		}
		
		// Reload wheellock long musket
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.WHEELLOCK_LONG_MUSKET.get())
					.requires(ModItems.WHEELLOCK_LONG_MUSKET.get())
					.requires(ModTags.Items.LARGE_METAL_MUSKET_BALL)
					.requires(ModTags.Items.WHEELLOCK_SUITABLE_POWDER)
					.requires(ModTags.Items.WHEELLOCK_SUITABLE_POWDER)
					.unlockedBy("has_wheellock_long_musket", has(ModItems.WHEELLOCK_LONG_MUSKET.get()))
					.unlockedBy("has_large_metal_musket_ball", has(ModTags.Items.LARGE_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wheellock_long_musket_reload"));
		}
		
		// Reload wheellock blunderbuss pistol buckshot and birdshot
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.WHEELLOCK_BLUNDERBUSS_PISTOL.get())
					.requires(ModItems.WHEELLOCK_BLUNDERBUSS_PISTOL.get())
					.requires(ModTags.Items.SMALL_METAL_BUCKSHOT)
					.requires(ModTags.Items.WHEELLOCK_SUITABLE_POWDER)
					.unlockedBy("has_wheellock_blunderbuss_pistol", has(ModItems.WHEELLOCK_BLUNDERBUSS_PISTOL.get()))
					.unlockedBy("has_small_metal_buckshot", has(ModTags.Items.SMALL_METAL_BUCKSHOT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wheellock_blunderbuss_pistol_buckshot_reload"));
		}
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.WHEELLOCK_BLUNDERBUSS_PISTOL.get())
					.requires(ModItems.WHEELLOCK_BLUNDERBUSS_PISTOL.get())
					.requires(ModTags.Items.SMALL_METAL_BIRDSHOT)
					.requires(ModTags.Items.WHEELLOCK_SUITABLE_POWDER)
					.unlockedBy("has_wheellock_blunderbuss_pistol", has(ModItems.WHEELLOCK_BLUNDERBUSS_PISTOL.get()))
					.unlockedBy("has_small_metal_birdshot", has(ModTags.Items.SMALL_METAL_BIRDSHOT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wheellock_blunderbuss_pistol_birdshot_reload"));
		}
		
		// Reload wheellock blunderbuss buckshot and birdshot
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.WHEELLOCK_BLUNDERBUSS.get())
					.requires(ModItems.WHEELLOCK_BLUNDERBUSS.get())
					.requires(ModTags.Items.LARGE_METAL_BUCKSHOT)
					.requires(ModTags.Items.WHEELLOCK_SUITABLE_POWDER)
					.requires(ModTags.Items.WHEELLOCK_SUITABLE_POWDER)
					.unlockedBy("has_wheellock_blunderbuss", has(ModItems.WHEELLOCK_BLUNDERBUSS.get()))
					.unlockedBy("has_large_metal_buckshot", has(ModTags.Items.LARGE_METAL_BUCKSHOT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wheellock_blunderbuss_buckshot_reload"));
		}
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.WHEELLOCK_BLUNDERBUSS.get())
					.requires(ModItems.WHEELLOCK_BLUNDERBUSS.get())
					.requires(ModTags.Items.LARGE_METAL_BIRDSHOT)
					.requires(ModTags.Items.WHEELLOCK_SUITABLE_POWDER)
					.requires(ModTags.Items.WHEELLOCK_SUITABLE_POWDER)
					.unlockedBy("has_wheellock_blunderbuss", has(ModItems.WHEELLOCK_BLUNDERBUSS.get()))
					.unlockedBy("has_large_metal_birdshot", has(ModTags.Items.LARGE_METAL_BIRDSHOT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "wheellock_blunderbuss_birdshot_reload"));
		}		
		
		// Flintlock
		// Reload flintlock derringer
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.FLINTLOCK_DERRINGER.get())
					.requires(ModItems.FLINTLOCK_DERRINGER.get())
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.unlockedBy("has_flintlock_derringer", has(ModItems.FLINTLOCK_DERRINGER.get()))
					.unlockedBy("has_small_metal_musket_ball", has(ModTags.Items.SMALL_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_derringer_reload"));
		}
		
		// Reload flintlock duckfoot derringer
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.FLINTLOCK_DUCKFOOT_DERRINGER.get())
					.requires(ModItems.FLINTLOCK_DUCKFOOT_DERRINGER.get())
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.unlockedBy("has_flintlock_duckfoot_derringer", has(ModItems.FLINTLOCK_DUCKFOOT_DERRINGER.get()))
					.unlockedBy("has_small_metal_musket_ball", has(ModTags.Items.SMALL_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_duckfoot_derringer_reload"));
		}
				
		// Reload flintlock pistol
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.FLINTLOCK_PISTOL.get())
					.requires(ModItems.FLINTLOCK_PISTOL.get())
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.unlockedBy("has_flintlock_pistol", has(ModItems.FLINTLOCK_PISTOL.get()))
					.unlockedBy("has_small_metal_musket_ball", has(ModTags.Items.SMALL_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_pistol_reload"));
		}
		
		// Reload flintlock pepperbox pistol
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.FLINTLOCK_PEPPERBOX_PISTOL.get())
					.requires(ModItems.FLINTLOCK_PEPPERBOX_PISTOL.get())
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.unlockedBy("has_flintlock_pepperbox_pistol", has(ModItems.FLINTLOCK_PEPPERBOX_PISTOL.get()))
					.unlockedBy("has_small_metal_musket_ball", has(ModTags.Items.SMALL_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_pepperbox_pistol_reload"));
		}
		
		// Reload flintlock arquebus
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.FLINTLOCK_ARQUEBUS.get())
					.requires(ModItems.FLINTLOCK_ARQUEBUS.get())
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.unlockedBy("has_flintlock_arquebus", has(ModItems.FLINTLOCK_ARQUEBUS.get()))
					.unlockedBy("has_small_metal_musket_ball", has(ModTags.Items.SMALL_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_arquebus_reload"));
		}
		
		// Reload flintlock caliver
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.FLINTLOCK_CALIVER.get())
					.requires(ModItems.FLINTLOCK_CALIVER.get())
					.requires(ModTags.Items.MEDIUM_METAL_MUSKET_BALL)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.unlockedBy("has_flintlock_caliver", has(ModItems.FLINTLOCK_CALIVER.get()))
					.unlockedBy("has_medium_metal_musket_ball", has(ModTags.Items.MEDIUM_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_caliver_reload"));
		}
		
		// Reload flintlock musketoon ball and birdshot
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.FLINTLOCK_MUSKETOON.get())
					.requires(ModItems.FLINTLOCK_MUSKETOON.get())
					.requires(ModTags.Items.MEDIUM_METAL_MUSKET_BALL)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.unlockedBy("has_flintlock_musketoon", has(ModItems.FLINTLOCK_MUSKETOON.get()))
					.unlockedBy("has_medium_metal_musket_ball", has(ModTags.Items.MEDIUM_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_musketoon_ball_reload"));
		}
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.FLINTLOCK_MUSKETOON.get())
					.requires(ModItems.FLINTLOCK_MUSKETOON.get())
					.requires(ModTags.Items.MEDIUM_METAL_BIRDSHOT)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.unlockedBy("has_flintlock_musketoon", has(ModItems.FLINTLOCK_MUSKETOON.get()))
					.unlockedBy("has_medium_metal_birdshot", has(ModTags.Items.MEDIUM_METAL_BIRDSHOT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_musketoon_birdshot_reload"));
		}
		
		// Reload flintlock musket
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.FLINTLOCK_MUSKET.get())
					.requires(ModItems.FLINTLOCK_MUSKET.get())
					.requires(ModTags.Items.LARGE_METAL_MUSKET_BALL)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.unlockedBy("has_flintlock_musket", has(ModItems.FLINTLOCK_MUSKET.get()))
					.unlockedBy("has_large_metal_musket_ball", has(ModTags.Items.LARGE_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_musket_reload"));
		}
		
		// Reload flintlock nock gun
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.FLINTLOCK_NOCK_GUN.get())
					.requires(ModItems.FLINTLOCK_NOCK_GUN.get())
					.requires(ModTags.Items.LARGE_METAL_MUSKET_BALL)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.unlockedBy("has_flintlock_nock_gun", has(ModItems.FLINTLOCK_MUSKET.get()))
					.unlockedBy("has_large_metal_musket_ball", has(ModTags.Items.LARGE_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_nock_gun_reload"));
		}
		
		// Reload flintlock long musket
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.FLINTLOCK_LONG_MUSKET.get())
					.requires(ModItems.FLINTLOCK_LONG_MUSKET.get())
					.requires(ModTags.Items.LARGE_METAL_MUSKET_BALL)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.unlockedBy("has_flintlock_long_musket", has(ModItems.FLINTLOCK_LONG_MUSKET.get()))
					.unlockedBy("has_large_metal_musket_ball", has(ModTags.Items.LARGE_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_long_musket_reload"));
		}
		
		// Reload flintlock blunderbuss pistol buckshot and birdshot
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.FLINTLOCK_BLUNDERBUSS_PISTOL.get())
					.requires(ModItems.FLINTLOCK_BLUNDERBUSS_PISTOL.get())
					.requires(ModTags.Items.SMALL_METAL_BUCKSHOT)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.unlockedBy("has_flintlock_blunderbuss_pistol", has(ModItems.FLINTLOCK_BLUNDERBUSS_PISTOL.get()))
					.unlockedBy("has_small_metal_buckshot", has(ModTags.Items.SMALL_METAL_BUCKSHOT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_blunderbuss_pistol_buckshot_reload"));
		}
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.FLINTLOCK_BLUNDERBUSS_PISTOL.get())
					.requires(ModItems.FLINTLOCK_BLUNDERBUSS_PISTOL.get())
					.requires(ModTags.Items.SMALL_METAL_BIRDSHOT)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.unlockedBy("has_flintlock_blunderbuss_pistol", has(ModItems.FLINTLOCK_BLUNDERBUSS_PISTOL.get()))
					.unlockedBy("has_small_metal_birdshot", has(ModTags.Items.SMALL_METAL_BIRDSHOT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_blunderbuss_pistol_birdshot_reload"));
		}
		
		// Reload flintlock blunderbuss buckshot and birdshot
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.FLINTLOCK_BLUNDERBUSS.get())
					.requires(ModItems.FLINTLOCK_BLUNDERBUSS.get())
					.requires(ModTags.Items.LARGE_METAL_BUCKSHOT)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.unlockedBy("has_flintlock_blunderbuss", has(ModItems.FLINTLOCK_BLUNDERBUSS.get()))
					.unlockedBy("has_large_metal_buckshot", has(ModTags.Items.LARGE_METAL_BUCKSHOT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_blunderbuss_buckshot_reload"));
		}
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.FLINTLOCK_BLUNDERBUSS.get())
					.requires(ModItems.FLINTLOCK_BLUNDERBUSS.get())
					.requires(ModTags.Items.LARGE_METAL_BIRDSHOT)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.unlockedBy("has_flintlock_blunderbuss", has(ModItems.FLINTLOCK_BLUNDERBUSS.get()))
					.unlockedBy("has_large_metal_birdshot", has(ModTags.Items.LARGE_METAL_BIRDSHOT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_blunderbuss_birdshot_reload"));
		}
		
		// Reload flintlock doublebarrel blunderbuss buckshot and birdshot
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS.get())
					.requires(ModItems.FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS.get())
					.requires(ModTags.Items.LARGE_METAL_BUCKSHOT)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.unlockedBy("has_flintlock_doublebarrel_blunderbuss", has(ModItems.FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS.get()))
					.unlockedBy("has_large_metal_buckshot", has(ModTags.Items.LARGE_METAL_BUCKSHOT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_doublebarrel_blunderbuss_buckshot_reload"));
		}
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapeless(ModItems.FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS.get())
					.requires(ModItems.FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS.get())
					.requires(ModTags.Items.LARGE_METAL_BIRDSHOT)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.requires(ModTags.Items.FLINTLOCK_SUITABLE_POWDER)
					.unlockedBy("has_flintlock_doublebarrel_blunderbuss", has(ModItems.FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS.get()))
					.unlockedBy("has_large_metal_birdshot", has(ModTags.Items.LARGE_METAL_BIRDSHOT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_doublebarrel_blunderbuss_birdshot_reload"));
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
		// Create small stone musket ball
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.SMALL_STONE_MUSKET_BALL.get(), 2)
					.requires(Tags.Items.STONE)
					.requires(Items.FLINT)					
					.unlockedBy("has_stone", has(Tags.Items.STONE))
					.unlockedBy("has_flint", has(Items.FLINT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_stone_musket_ball"));
		}
		
		// Create medium stone musket ball
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.MEDIUM_STONE_MUSKET_BALL.get(), 2)
					.requires(Tags.Items.STONE)
					.requires(Items.FLINT)
					.requires(Items.FLINT)
					.unlockedBy("has_stone", has(Tags.Items.STONE))
					.unlockedBy("has_flint", has(Items.FLINT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_stone_musket_ball"));
		}
		
		// Create large stone musket ball
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.LARGE_STONE_MUSKET_BALL.get(), 2)
					.requires(Tags.Items.STONE)
					.requires(Items.FLINT)
					.requires(Items.FLINT)
					.requires(Items.FLINT)
					.unlockedBy("has_stone", has(Tags.Items.STONE))
					.unlockedBy("has_flint", has(Items.FLINT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_stone_musket_ball"));
		}
				
		// Create small stone birdshot
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapeless(ModItems.SMALL_STONE_BIRDSHOT.get(), 1)
					.requires(Tags.Items.GRAVEL)
					.requires(Items.PAPER)					
					.unlockedBy("has_gravel", has(Tags.Items.GRAVEL))
					.unlockedBy("has_paper", has(Items.PAPER))
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
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_stone_birdshot"));
		}
		
		// Create small iron musket ball
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.SMALL_IRON_MUSKET_BALL.get(), 2)
					.pattern(" i ")
					.pattern("iii")
					.pattern(" i ")
					.define('i', Tags.Items.NUGGETS_IRON)
					.unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_iron_musket_ball"));
		}
		
		// Create medium iron musket ball
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MEDIUM_IRON_MUSKET_BALL.get(), 2)
					.pattern(" i ")
					.pattern("iIi")
					.pattern(" i ")
					.define('i', Tags.Items.NUGGETS_IRON)
					.define('I', Tags.Items.INGOTS_IRON)
					.unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
					.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_iron_musket_ball"));
		}
		
		// Create large iron musket ball
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.LARGE_IRON_MUSKET_BALL.get(), 2)
					.pattern(" I ")
					.pattern("III")
					.pattern(" I ")
					.define('I', Tags.Items.INGOTS_IRON)
					.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
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
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_iron_birdshot"));
		}
		
		// Create small lead musket ball
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.SMALL_LEAD_MUSKET_BALL.get(), 2)
					.pattern(" l ")
					.pattern("lll")
					.pattern(" l ")
					.define('l', ModTags.Items.NUGGETS_LEAD)
					.unlockedBy("has_lead_nugget", has(ModTags.Items.NUGGETS_LEAD))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_lead_musket_ball"));
		}
		
		// Create medium lead musket ball
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.MEDIUM_LEAD_MUSKET_BALL.get(), 2)
					.pattern(" l ")
					.pattern("lLl")
					.pattern(" l ")
					.define('l', ModTags.Items.NUGGETS_LEAD)
					.define('L', ModTags.Items.INGOTS_LEAD)						
					.unlockedBy("has_lead_nugget", has(ModTags.Items.NUGGETS_LEAD))
					.unlockedBy("has_lead_ingot", has(ModTags.Items.INGOTS_LEAD))					
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_lead_musket_ball"));
		}
		
		// Create large lead musket ball
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.LARGE_LEAD_MUSKET_BALL.get(), 2)
					.pattern(" L ")
					.pattern("LLL")
					.pattern(" L ")
					.define('L', ModTags.Items.INGOTS_LEAD)	
					.unlockedBy("has_lead_ingot", has(ModTags.Items.INGOTS_LEAD))
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
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_lead_birdshot"));
		}
		
		// Parts
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
		
		// Create flintlock mechanism
		{
			ShapedGunsmithsBenchRecipeBuilder.shaped(ModItems.FLINTLOCK_MECHANISM.get())
					.pattern("  F")
					.pattern("IGL")
					.pattern(" R ")
					.define('F', Items.FLINT)
					.define('I', Tags.Items.INGOTS_IRON)
					.define('G', Tags.Items.INGOTS_GOLD)
					.define('L', Items.LEVER)
					.define('R', Tags.Items.RODS_WOODEN)
					.unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
					.unlockedBy("has_flint", has(Items.FLINT))
					.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
					.unlockedBy("has_gold_ingot", has(Tags.Items.INGOTS_GOLD))
					.unlockedBy("has_lever", has(Items.LEVER))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_mechanism"));
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
					.unlockedBy("has_stone", has(Tags.Items.STONE))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "mortar_and_pestle"));
		}
	}
	
	@Override
	public String getName() {
		return "OldGunsRecipes";
	}
}
