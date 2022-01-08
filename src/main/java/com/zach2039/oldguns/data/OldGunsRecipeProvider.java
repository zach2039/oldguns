package com.zach2039.oldguns.data;

import java.util.function.Consumer;

import com.zach2039.oldguns.OldGuns;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;

public class OldGunsRecipeProvider extends RecipeProvider {
	
	public OldGunsRecipeProvider(final DataGenerator dataGenerator) {
		super(dataGenerator);
	}

	@Override
	protected void buildCraftingRecipes(final Consumer<FinishedRecipe> recipeConsumer) {

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

		// Firearms
		// Create flintlock pistol
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.FLINTLOCK_PISTOL.get())
					.pattern("BM")
					.pattern(" H")
					.define('B', ModTags.Items.SMALL_METAL_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.MEDIUM_HANDLE)
					.unlockedBy("has_small_barrel", has(ModTags.Items.SMALL_METAL_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_medium_handle", has(ModTags.Items.MEDIUM_HANDLE))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_pistol"));
		}
		
		// Create flintlock caliver
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.FLINTLOCK_CALIVER.get())
					.pattern("BM")
					.pattern(" H")
					.define('B', ModTags.Items.MEDIUM_METAL_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.MEDIUM_STOCK)
					.unlockedBy("has_medium_metal_barrel", has(ModTags.Items.MEDIUM_METAL_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_small_stock", has(ModTags.Items.SMALL_STOCK))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_caliver"));
		}
		
		// Create flintlock long musket
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.FLINTLOCK_LONG_MUSKET.get())
					.pattern("BM")
					.pattern(" H")
					.define('B', ModTags.Items.LARGE_METAL_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.LARGE_STOCK)
					.unlockedBy("has_large_metal_barrel", has(ModTags.Items.LARGE_METAL_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_large_stock", has(ModTags.Items.LARGE_STOCK))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_long_musket"));
		}
		
		// Create flintlock blunderbuss
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.FLINTLOCK_BLUNDERBUSS.get())
					.pattern("BM")
					.pattern(" H")
					.define('B', ModTags.Items.HUGE_METAL_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.LARGE_STOCK)
					.unlockedBy("has_huge_metal_barrel", has(ModTags.Items.HUGE_METAL_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_large_stock", has(ModTags.Items.LARGE_STOCK))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_blunderbuss"));
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
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(Items.PAPER)
					.unlockedBy("has_small_metal_musket_ball", has(ModTags.Items.SMALL_METAL_MUSKET_BALL))
					.unlockedBy("has_paper", has(Items.PAPER))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_iron_buckshot"));
		}
		
		// Create medium iron buckshot
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapelessGunsmithsBenchRecipe(ModItems.MEDIUM_IRON_BUCKSHOT.get(), 1)
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(Items.PAPER)
					.requires(Items.PAPER)
					.unlockedBy("has_small_metal_musket_ball", has(ModTags.Items.SMALL_METAL_MUSKET_BALL))
					.unlockedBy("has_paper", has(Items.PAPER))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "medium_iron_buckshot"));
		}
		
		// Create large iron buckshot
		{
			ShapelessGunsmithsBenchRecipeBuilder.shapelessGunsmithsBenchRecipe(ModItems.LARGE_IRON_BUCKSHOT.get(), 1)
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(ModTags.Items.SMALL_METAL_MUSKET_BALL)
					.requires(Items.PAPER)
					.requires(Items.PAPER)
					.unlockedBy("has_small_metal_musket_ball", has(ModTags.Items.SMALL_METAL_MUSKET_BALL))
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
		
		// Parts
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
		
		// Create huge iron barrel
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.HUGE_IRON_BARREL.get())
					.pattern("III")
					.pattern("III")
					.pattern("iiI")
					.define('I', Tags.Items.INGOTS_IRON)
					.define('i', Tags.Items.NUGGETS_IRON)
					.unlockedBy("has_iron_ingot", has(Tags.Items.INGOTS_IRON))
					.unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "huge_iron_barrel"));
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
