package com.zach2039.oldguns.data;

import java.util.function.Consumer;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.data.crafting.recipe.ShapedGunsmithsBenchRecipeBuilder;
import com.zach2039.oldguns.data.crafting.recipe.ShapelessFirearmMuzzleloaderReloadRecipeBuilder;
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
			
		// Reload flintlock pistol
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapelessFirearmMuzzleloaderReloadRecipe(ModItems.FLINTLOCK_PISTOL.get())
					.requires(ModItems.FLINTLOCK_PISTOL.get())
					.requires(ModItems.SMALL_IRON_MUSKET_BALL.get())
					.requires(Tags.Items.GUNPOWDER)
					.unlockedBy("has_flintlock_pistol", has(ModItems.FLINTLOCK_PISTOL.get()))
					.unlockedBy("has_small_iron_musket_ball", has(ModItems.SMALL_IRON_MUSKET_BALL.get()))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_pistol_reload"));
		}

		// Create flintlock pistol
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.FLINTLOCK_PISTOL.get())
					.pattern("BM")
					.pattern(" H")
					.define('B', ModTags.Items.SMALL_BARREL)
					.define('M', ModTags.Items.FLINTLOCK_MECHANISM)
					.define('H', ModTags.Items.MEDIUM_HANDLE)
					.unlockedBy("has_small_barrel", has(ModTags.Items.SMALL_BARREL))
					.unlockedBy("has_flintlock_mechanism", has(ModTags.Items.FLINTLOCK_MECHANISM))
					.unlockedBy("has_medium_handle", has(ModTags.Items.MEDIUM_HANDLE))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_pistol"));
		}
		
		// Create small iron musket ball
		{
			ShapedGunsmithsBenchRecipeBuilder.shapedGunsmithsBenchRecipe(ModItems.SMALL_IRON_MUSKET_BALL.get(), 2)
					.pattern(" N ")
					.pattern("NNN")
					.pattern(" N ")
					.define('N', Tags.Items.NUGGETS_IRON)
					.unlockedBy("has_iron_nugget", has(Tags.Items.NUGGETS_IRON))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "small_iron_musket_ball"));
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
