package com.zach2039.oldguns.data;

import java.util.function.Consumer;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.data.crafting.ConditionBuilder;
import com.zach2039.oldguns.data.crafting.ingredient.NotConditionalIngredientBuilder;
import com.zach2039.oldguns.data.crafting.recipe.ShapedGunsmithsBenchRecipeBuilder;
import com.zach2039.oldguns.data.crafting.recipe.ShapelessFirearmMuzzleloaderReloadRecipeBuilder;
import com.zach2039.oldguns.data.crafting.recipe.ShapelessGunsmithsBenchRecipeBuilder;
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
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.ItemExistsCondition;

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
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "lead_ingot"));
		}
		
		// Brass ingots from nuggets
		{
			ShapelessRecipeBuilder.shapeless(ModItems.BRASS_INGOT.get())
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
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "lead_nugget"));
		}
		
		// Brass nuggets from ingots
		{
			ShapelessRecipeBuilder.shapeless(ModItems.BRASS_NUGGET.get(), 9)
					.requires(ModTags.Items.INGOTS_BRASS)
					.unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE))
					.unlockedBy("has_brass_ingot", has(ModTags.Items.INGOTS_BRASS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "brass_nugget"));
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
					.define('G', Tags.Items.GUNPOWDER)	
					.unlockedBy("has_crafting_table", has(Blocks.CRAFTING_TABLE))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "gunsmiths_bench"));
		}
			
		// Firearm Reloading
		// Reload flintlock derringer
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapelessFirearmMuzzleloaderReloadRecipe(ModItems.FLINTLOCK_DERRINGER.get())
					.requires(ModItems.FLINTLOCK_DERRINGER.get())
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(Tags.Items.GUNPOWDER)
					.unlockedBy("has_flintlock_derringer", has(ModItems.FLINTLOCK_DERRINGER.get()))
					.unlockedBy("has_small_metal_musket_ball", has(ModTags.Items.SMALL_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_derringer_reload"));
		}
		
		// Reload flintlock duckfoot derringer
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapelessFirearmMuzzleloaderReloadRecipe(ModItems.FLINTLOCK_DUCKFOOT_DERRINGER.get())
					.requires(ModItems.FLINTLOCK_DUCKFOOT_DERRINGER.get())
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(Tags.Items.GUNPOWDER)
					.unlockedBy("has_flintlock_duckfoot_derringer", has(ModItems.FLINTLOCK_DUCKFOOT_DERRINGER.get()))
					.unlockedBy("has_small_metal_musket_ball", has(ModTags.Items.SMALL_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_duckfoot_derringer_reload"));
		}
				
		// Reload flintlock pistol
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapelessFirearmMuzzleloaderReloadRecipe(ModItems.FLINTLOCK_PISTOL.get())
					.requires(ModItems.FLINTLOCK_PISTOL.get())
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(Tags.Items.GUNPOWDER)
					.unlockedBy("has_flintlock_pistol", has(ModItems.FLINTLOCK_PISTOL.get()))
					.unlockedBy("has_small_metal_musket_ball", has(ModTags.Items.SMALL_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_pistol_reload"));
		}
		
		// Reload flintlock pepperbox pistol
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapelessFirearmMuzzleloaderReloadRecipe(ModItems.FLINTLOCK_PEPPERBOX_PISTOL.get())
					.requires(ModItems.FLINTLOCK_PEPPERBOX_PISTOL.get())
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(Tags.Items.GUNPOWDER)
					.unlockedBy("has_flintlock_pepperbox_pistol", has(ModItems.FLINTLOCK_PEPPERBOX_PISTOL.get()))
					.unlockedBy("has_small_metal_musket_ball", has(ModTags.Items.SMALL_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_pepperbox_pistol_reload"));
		}
		
		// Reload flintlock arquebus
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapelessFirearmMuzzleloaderReloadRecipe(ModItems.FLINTLOCK_ARQUEBUS.get())
					.requires(ModItems.FLINTLOCK_ARQUEBUS.get())
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(Tags.Items.GUNPOWDER)
					.unlockedBy("has_flintlock_arquebus", has(ModItems.FLINTLOCK_ARQUEBUS.get()))
					.unlockedBy("has_small_metal_musket_ball", has(ModTags.Items.SMALL_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_arquebus_reload"));
		}
		
		// Reload flintlock caliver
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapelessFirearmMuzzleloaderReloadRecipe(ModItems.FLINTLOCK_CALIVER.get())
					.requires(ModItems.FLINTLOCK_CALIVER.get())
					.requires(ModTags.Items.MEDIUM_METAL_MUSKET_BALL)
					.requires(Tags.Items.GUNPOWDER)
					.requires(Tags.Items.GUNPOWDER)
					.unlockedBy("has_flintlock_caliver", has(ModItems.FLINTLOCK_CALIVER.get()))
					.unlockedBy("has_medium_metal_musket_ball", has(ModTags.Items.MEDIUM_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_caliver_reload"));
		}
		
		// Reload flintlock musketoon ball and birdshot
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapelessFirearmMuzzleloaderReloadRecipe(ModItems.FLINTLOCK_MUSKETOON.get())
					.requires(ModItems.FLINTLOCK_MUSKETOON.get())
					.requires(ModTags.Items.MEDIUM_METAL_MUSKET_BALL)
					.requires(Tags.Items.GUNPOWDER)
					.requires(Tags.Items.GUNPOWDER)
					.unlockedBy("has_flintlock_musketoon", has(ModItems.FLINTLOCK_MUSKETOON.get()))
					.unlockedBy("has_medium_metal_musket_ball", has(ModTags.Items.MEDIUM_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_musketoon_ball_reload"));
		}
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapelessFirearmMuzzleloaderReloadRecipe(ModItems.FLINTLOCK_MUSKETOON.get())
					.requires(ModItems.FLINTLOCK_MUSKETOON.get())
					.requires(ModTags.Items.MEDIUM_METAL_BIRDSHOT)
					.requires(Tags.Items.GUNPOWDER)
					.requires(Tags.Items.GUNPOWDER)
					.unlockedBy("has_flintlock_musketoon", has(ModItems.FLINTLOCK_MUSKETOON.get()))
					.unlockedBy("has_medium_metal_birdshot", has(ModTags.Items.MEDIUM_METAL_BIRDSHOT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_musketoon_birdshot_reload"));
		}
		
		// Reload flintlock musket
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapelessFirearmMuzzleloaderReloadRecipe(ModItems.FLINTLOCK_MUSKET.get())
					.requires(ModItems.FLINTLOCK_MUSKET.get())
					.requires(ModTags.Items.LARGE_METAL_MUSKET_BALL)
					.requires(Tags.Items.GUNPOWDER)
					.requires(Tags.Items.GUNPOWDER)
					.unlockedBy("has_flintlock_musket", has(ModItems.FLINTLOCK_MUSKET.get()))
					.unlockedBy("has_large_metal_musket_ball", has(ModTags.Items.LARGE_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_musket_reload"));
		}
		
		// Reload flintlock nock gun
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapelessFirearmMuzzleloaderReloadRecipe(ModItems.FLINTLOCK_NOCK_GUN.get())
					.requires(ModItems.FLINTLOCK_NOCK_GUN.get())
					.requires(ModTags.Items.LARGE_METAL_MUSKET_BALL)
					.requires(Tags.Items.GUNPOWDER)
					.requires(Tags.Items.GUNPOWDER)
					.unlockedBy("has_flintlock_nock_gun", has(ModItems.FLINTLOCK_MUSKET.get()))
					.unlockedBy("has_large_metal_musket_ball", has(ModTags.Items.LARGE_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_nock_gun_reload"));
		}
		
		// Reload flintlock long musket
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapelessFirearmMuzzleloaderReloadRecipe(ModItems.FLINTLOCK_LONG_MUSKET.get())
					.requires(ModItems.FLINTLOCK_LONG_MUSKET.get())
					.requires(ModTags.Items.LARGE_METAL_MUSKET_BALL)
					.requires(Tags.Items.GUNPOWDER)
					.requires(Tags.Items.GUNPOWDER)
					.unlockedBy("has_flintlock_long_musket", has(ModItems.FLINTLOCK_LONG_MUSKET.get()))
					.unlockedBy("has_large_metal_musket_ball", has(ModTags.Items.LARGE_METAL_MUSKET_BALL))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_long_musket_reload"));
		}
		
		// Reload flintlock blunderbuss pistol buckshot and birdshot
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapelessFirearmMuzzleloaderReloadRecipe(ModItems.FLINTLOCK_BLUNDERBUSS_PISTOL.get())
					.requires(ModItems.FLINTLOCK_BLUNDERBUSS_PISTOL.get())
					.requires(ModTags.Items.SMALL_METAL_BUCKSHOT)
					.requires(Tags.Items.GUNPOWDER)
					.unlockedBy("has_flintlock_blunderbuss_pistol", has(ModItems.FLINTLOCK_BLUNDERBUSS_PISTOL.get()))
					.unlockedBy("has_small_metal_buckshot", has(ModTags.Items.SMALL_METAL_BUCKSHOT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_blunderbuss_pistol_buckshot_reload"));
		}
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapelessFirearmMuzzleloaderReloadRecipe(ModItems.FLINTLOCK_BLUNDERBUSS_PISTOL.get())
					.requires(ModItems.FLINTLOCK_BLUNDERBUSS_PISTOL.get())
					.requires(ModTags.Items.SMALL_METAL_BIRDSHOT)
					.requires(Tags.Items.GUNPOWDER)
					.unlockedBy("has_flintlock_blunderbuss_pistol", has(ModItems.FLINTLOCK_BLUNDERBUSS_PISTOL.get()))
					.unlockedBy("has_small_metal_birdshot", has(ModTags.Items.SMALL_METAL_BIRDSHOT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_blunderbuss_pistol_birdshot_reload"));
		}
		
		// Reload flintlock blunderbuss buckshot and birdshot
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapelessFirearmMuzzleloaderReloadRecipe(ModItems.FLINTLOCK_BLUNDERBUSS.get())
					.requires(ModItems.FLINTLOCK_BLUNDERBUSS.get())
					.requires(ModTags.Items.LARGE_METAL_BUCKSHOT)
					.requires(Tags.Items.GUNPOWDER)
					.requires(Tags.Items.GUNPOWDER)
					.unlockedBy("has_flintlock_blunderbuss", has(ModItems.FLINTLOCK_BLUNDERBUSS.get()))
					.unlockedBy("has_large_metal_buckshot", has(ModTags.Items.LARGE_METAL_BUCKSHOT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_blunderbuss_buckshot_reload"));
		}
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapelessFirearmMuzzleloaderReloadRecipe(ModItems.FLINTLOCK_BLUNDERBUSS.get())
					.requires(ModItems.FLINTLOCK_BLUNDERBUSS.get())
					.requires(ModTags.Items.LARGE_METAL_BIRDSHOT)
					.requires(Tags.Items.GUNPOWDER)
					.requires(Tags.Items.GUNPOWDER)
					.unlockedBy("has_flintlock_blunderbuss", has(ModItems.FLINTLOCK_BLUNDERBUSS.get()))
					.unlockedBy("has_large_metal_birdshot", has(ModTags.Items.LARGE_METAL_BIRDSHOT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_blunderbuss_birdshot_reload"));
		}
		
		// Reload flintlock doublebarrel blunderbuss buckshot and birdshot
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapelessFirearmMuzzleloaderReloadRecipe(ModItems.FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS.get())
					.requires(ModItems.FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS.get())
					.requires(ModTags.Items.LARGE_METAL_BUCKSHOT)
					.requires(Tags.Items.GUNPOWDER)
					.requires(Tags.Items.GUNPOWDER)
					.unlockedBy("has_flintlock_doublebarrel_blunderbuss", has(ModItems.FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS.get()))
					.unlockedBy("has_large_metal_buckshot", has(ModTags.Items.LARGE_METAL_BUCKSHOT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_doublebarrel_blunderbuss_buckshot_reload"));
		}
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapelessFirearmMuzzleloaderReloadRecipe(ModItems.FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS.get())
					.requires(ModItems.FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS.get())
					.requires(ModTags.Items.LARGE_METAL_BIRDSHOT)
					.requires(Tags.Items.GUNPOWDER)
					.requires(Tags.Items.GUNPOWDER)
					.unlockedBy("has_flintlock_doublebarrel_blunderbuss", has(ModItems.FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS.get()))
					.unlockedBy("has_large_metal_birdshot", has(ModTags.Items.LARGE_METAL_BIRDSHOT))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_doublebarrel_blunderbuss_birdshot_reload"));
		}

		// Firearms
		// Create flintlock derringer
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.FLINTLOCK_DERRINGER.get())
					.pattern("BMH")
					.define('B', ModTags.Items.TINY_METAL_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.SMALL_HANDLE)
					.unlockedBy("has_tiny_barrel", has(ModTags.Items.TINY_METAL_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_small_handle", has(ModTags.Items.SMALL_HANDLE))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_derringer"));
		}
		
		// Create flintlock duckfoot derringer
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.FLINTLOCK_DUCKFOOT_DERRINGER.get())
					.pattern("B  ")
					.pattern("BMH")
					.pattern("B  ")
					.define('B', ModTags.Items.TINY_METAL_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.SMALL_HANDLE)
					.unlockedBy("has_tiny_barrel", has(ModTags.Items.TINY_METAL_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_small_handle", has(ModTags.Items.SMALL_HANDLE))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_duckfoot_derringer"));
		}
				
		// Create flintlock pistol
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.FLINTLOCK_PISTOL.get())
					.pattern("BMH")
					.define('B', ModTags.Items.SMALL_METAL_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.MEDIUM_HANDLE)
					.unlockedBy("has_small_barrel", has(ModTags.Items.SMALL_METAL_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_medium_handle", has(ModTags.Items.MEDIUM_HANDLE))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_pistol"));
		}
		
		// Create flintlock pepperbox pistol
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.FLINTLOCK_PEPPERBOX_PISTOL.get())
					.pattern("BB ")
					.pattern("BMH")
					.pattern("B  ")
					.define('B', ModTags.Items.SMALL_METAL_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.MEDIUM_HANDLE)
					.unlockedBy("has_small_barrel", has(ModTags.Items.SMALL_METAL_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_medium_handle", has(ModTags.Items.MEDIUM_HANDLE))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_pepperbox_pistol"));
		}
		
		// Create flintlock arquebus
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.FLINTLOCK_ARQUEBUS.get())
					.pattern("BMH")
					.define('B', ModTags.Items.SMALL_METAL_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.LARGE_HANDLE)
					.unlockedBy("has_small_barrel", has(ModTags.Items.SMALL_METAL_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_large_handle", has(ModTags.Items.LARGE_HANDLE))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_arquebus"));
		}
		
		// Create flintlock caliver
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.FLINTLOCK_CALIVER.get())
					.pattern("BMH")
					.define('B', ModTags.Items.MEDIUM_METAL_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.SMALL_STOCK)
					.unlockedBy("has_medium_metal_barrel", has(ModTags.Items.MEDIUM_METAL_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_small_stock", has(ModTags.Items.SMALL_STOCK))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_caliver"));
		}
		
		// Create flintlock musketoon
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.FLINTLOCK_MUSKETOON.get())
					.pattern("BMH")
					.define('B', ModTags.Items.MEDIUM_METAL_FLARED_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.MEDIUM_STOCK)
					.unlockedBy("has_medium_metal_flared_barrel", has(ModTags.Items.MEDIUM_METAL_FLARED_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_medium_stock", has(ModTags.Items.MEDIUM_STOCK))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_musketoon"));
		}
		
		// Create flintlock musket
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.FLINTLOCK_MUSKET.get())
					.pattern("BMH")
					.define('B', ModTags.Items.LARGE_METAL_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.MEDIUM_STOCK)
					.unlockedBy("has_large_metal_barrel", has(ModTags.Items.LARGE_METAL_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_medium_stock", has(ModTags.Items.MEDIUM_STOCK))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_musket"));
		}
		
		// Create flintlock nock gun
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.FLINTLOCK_NOCK_GUN.get())
					.pattern("BB ")
					.pattern("BMH")
					.pattern("BB ")
					.define('B', ModTags.Items.LARGE_METAL_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.LARGE_STOCK)
					.unlockedBy("has_large_metal_barrel", has(ModTags.Items.LARGE_METAL_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_large_stock", has(ModTags.Items.LARGE_STOCK))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_nock_gun"));
		}
		
		// Create flintlock long musket
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.FLINTLOCK_LONG_MUSKET.get())
					.pattern("BMH")
					.define('B', ModTags.Items.LARGE_METAL_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.LARGE_STOCK)
					.unlockedBy("has_large_metal_barrel", has(ModTags.Items.LARGE_METAL_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_large_stock", has(ModTags.Items.LARGE_STOCK))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_long_musket"));
		}
		
		// Create flintlock blunderbuss pistol
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.FLINTLOCK_BLUNDERBUSS_PISTOL.get())
					.pattern("BMH")
					.define('B', ModTags.Items.SMALL_METAL_FLARED_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.MEDIUM_HANDLE)
					.unlockedBy("has_small_metal_flared_barrel", has(ModTags.Items.SMALL_METAL_FLARED_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_medium_handle", has(ModTags.Items.MEDIUM_HANDLE))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_blunderbuss_pistol"));
		}
		
		// Create flintlock blunderbuss
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.FLINTLOCK_BLUNDERBUSS.get())
					.pattern("BMH")
					.define('B', ModTags.Items.LARGE_METAL_FLARED_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.LARGE_STOCK)
					.unlockedBy("has_large_metal_flared_barrel", has(ModTags.Items.LARGE_METAL_FLARED_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_large_stock", has(ModTags.Items.LARGE_STOCK))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_blunderbuss"));
		}
		
		// Create flintlock doublebarrel blunderbuss
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS.get())
					.pattern("B  ")
					.pattern("BMH")
					.define('B', ModTags.Items.LARGE_METAL_FLARED_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.LARGE_STOCK)
					.unlockedBy("has_large_metal_flared_barrel", has(ModTags.Items.LARGE_METAL_FLARED_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_large_stock", has(ModTags.Items.LARGE_STOCK))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_doublebarrel_blunderbuss"));
		}
		
		// Ammo
		// Create small iron musket ball
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.SMALL_IRON_MUSKET_BALL.get(), 2)
					.pattern(" i ")
					.pattern("iii")
					.pattern(" i ")
					.define('i', Tags.Items.NUGGETS_IRON)
					.unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_iron_musket_ball"));
		}
		
		// Create medium iron musket ball
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.MEDIUM_IRON_MUSKET_BALL.get(), 2)
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
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.LARGE_IRON_MUSKET_BALL.get(), 2)
					.pattern(" I ")
					.pattern("III")
					.pattern(" I ")
					.define('I', Tags.Items.INGOTS_IRON)
					.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_iron_musket_ball"));
		}
		
		// Create small iron buckshot
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapelessGunsmithsBenchRecipe(ModItems.SMALL_IRON_BUCKSHOT.get(), 1)
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
			ShapelessGunsmithsBenchRecipeBuilder.shapelessGunsmithsBenchRecipe(ModItems.MEDIUM_IRON_BUCKSHOT.get(), 1)
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
			ShapelessGunsmithsBenchRecipeBuilder.shapelessGunsmithsBenchRecipe(ModItems.LARGE_IRON_BUCKSHOT.get(), 1)
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
			ShapelessGunsmithsBenchRecipeBuilder.shapelessGunsmithsBenchRecipe(ModItems.SMALL_IRON_BIRDSHOT.get(), 1)
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
			ShapelessGunsmithsBenchRecipeBuilder.shapelessGunsmithsBenchRecipe(ModItems.MEDIUM_IRON_BIRDSHOT.get(), 1)
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
			ShapelessGunsmithsBenchRecipeBuilder.shapelessGunsmithsBenchRecipe(ModItems.LARGE_IRON_BIRDSHOT.get(), 1)
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
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.SMALL_LEAD_MUSKET_BALL.get(), 2)
					.pattern(" l ")
					.pattern("lll")
					.pattern(" l ")
					.define('l', ModTags.Items.NUGGETS_LEAD)
					.unlockedBy("has_lead_nugget", has(ModTags.Items.NUGGETS_LEAD))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_lead_musket_ball"));
		}
		
		// Create medium lead musket ball
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.MEDIUM_LEAD_MUSKET_BALL.get(), 2)
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
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.LARGE_LEAD_MUSKET_BALL.get(), 2)
					.pattern(" L ")
					.pattern("LLL")
					.pattern(" L ")
					.define('L', ModTags.Items.INGOTS_LEAD)	
					.unlockedBy("has_lead_ingot", has(ModTags.Items.INGOTS_LEAD))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "large_lead_musket_ball"));
		}
		
		// Create small lead buckshot
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapelessGunsmithsBenchRecipe(ModItems.SMALL_LEAD_BUCKSHOT.get(), 1)
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
			ShapelessGunsmithsBenchRecipeBuilder.shapelessGunsmithsBenchRecipe(ModItems.MEDIUM_LEAD_BUCKSHOT.get(), 1)
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
			ShapelessGunsmithsBenchRecipeBuilder.shapelessGunsmithsBenchRecipe(ModItems.LARGE_LEAD_BUCKSHOT.get(), 1)
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
			ShapelessGunsmithsBenchRecipeBuilder.shapelessGunsmithsBenchRecipe(ModItems.SMALL_LEAD_BIRDSHOT.get(), 1)
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
			ShapelessGunsmithsBenchRecipeBuilder.shapelessGunsmithsBenchRecipe(ModItems.MEDIUM_LEAD_BIRDSHOT.get(), 1)
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
			ShapelessGunsmithsBenchRecipeBuilder.shapelessGunsmithsBenchRecipe(ModItems.LARGE_LEAD_BIRDSHOT.get(), 1)
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
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.TINY_IRON_BARREL.get())
					.pattern("iii")
					.pattern(" ii")
					.define('i', Tags.Items.NUGGETS_IRON)
					.unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "tiny_iron_barrel"));
		}
				
		// Create small iron barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.SMALL_IRON_BARREL.get())
					.pattern("IIi")
					.define('I', Tags.Items.INGOTS_IRON)
					.define('i', Tags.Items.NUGGETS_IRON)
					.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
					.unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_iron_barrel"));
		}
		
		// Create medium iron barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.MEDIUM_IRON_BARREL.get())
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
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.LARGE_IRON_BARREL.get())
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
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.TINY_BRASS_BARREL.get())
					.pattern("bbb")
					.pattern(" bb")
					.define('b', ModTags.Items.NUGGETS_BRASS)
					.unlockedBy("has_brass_nugget", has(ModTags.Items.NUGGETS_BRASS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "tiny_brass_barrel"));
		}
		
		// Create small brass barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.SMALL_BRASS_BARREL.get())
					.pattern("BBb")
					.define('B', ModTags.Items.INGOTS_BRASS)
					.define('b', ModTags.Items.NUGGETS_BRASS)
					.unlockedBy("has_brass_ingot", has(ModTags.Items.INGOTS_BRASS))
					.unlockedBy("has_brass_nugget", has(ModTags.Items.NUGGETS_BRASS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_brass_barrel"));
		}
		
		// Create medium brass barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.MEDIUM_BRASS_BARREL.get())
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
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.LARGE_BRASS_BARREL.get())
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
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.SMALL_IRON_FLARED_BARREL.get())
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
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.MEDIUM_IRON_FLARED_BARREL.get())
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
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.LARGE_IRON_FLARED_BARREL.get())
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
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.SMALL_BRASS_FLARED_BARREL.get())
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
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.MEDIUM_BRASS_FLARED_BARREL.get())
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
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.LARGE_BRASS_FLARED_BARREL.get())
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
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.SMALL_WOODEN_HANDLE.get())
					.pattern("SR")
					.define('S', ItemTags.WOODEN_SLABS)
					.define('R', Tags.Items.RODS_WOODEN)
					.unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
					.unlockedBy("has_wooden_slab", has(ItemTags.WOODEN_SLABS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_wooden_handle"));
		}
		
		// Create medium wooden handle
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.MEDIUM_WOODEN_HANDLE.get())
					.pattern("SSR")
					.define('S', ItemTags.WOODEN_SLABS)
					.define('R', Tags.Items.RODS_WOODEN)
					.unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
					.unlockedBy("has_wooden_slab", has(ItemTags.WOODEN_SLABS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_wooden_handle"));
		}
		
		// Create large wooden handle
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.LARGE_WOODEN_HANDLE.get())
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
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.SMALL_WOODEN_STOCK.get())
					.pattern("SL")
					.define('S', ItemTags.WOODEN_SLABS)
					.define('L', ItemTags.LOGS)
					.unlockedBy("has_wooden_slab", has(ItemTags.WOODEN_SLABS))
					.unlockedBy("has_wooden_log", has(ItemTags.LOGS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_wooden_stock"));
		}
		
		// Create medium wooden stock
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.MEDIUM_WOODEN_STOCK.get())
					.pattern("SLL")
					.define('S', ItemTags.WOODEN_SLABS)
					.define('L', ItemTags.LOGS)
					.unlockedBy("has_wooden_slab", has(ItemTags.WOODEN_SLABS))
					.unlockedBy("has_wooden_log", has(ItemTags.LOGS))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_wooden_stock"));
		}
		
		// Create large wooden stock
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.LARGE_WOODEN_STOCK.get())
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
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.FLINTLOCK_MECHANISM.get())
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
	}
	
	@Override
	public String getName() {
		return "OldGunsRecipes";
	}
}
