package com.zach2039.oldguns.data;

import java.util.function.Consumer;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.data.crafting.ingredient.FirearmIngredientBuilder;
import com.zach2039.oldguns.data.crafting.recipe.ShapelessFirearmMuzzleloaderReloadRecipeBuilder;
import com.zach2039.oldguns.init.ModItems;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.Tags.Items;

public class OldGunsRecipeProvider extends RecipeProvider {
	
	public OldGunsRecipeProvider(final DataGenerator dataGenerator) {
		super(dataGenerator);
	}

	@Override
	protected void buildCraftingRecipes(final Consumer<FinishedRecipe> recipeConsumer) {

		// Cut an Oak Log into two Oak Planks with a Wooden Axe, damaging the axe
		{
			ShapelessFirearmMuzzleloaderReloadRecipeBuilder.shapelessFirearmMuzzleloaderReloadRecipe(ModItems.FLINTLOCK_PISTOL.get())
					.requires(ModItems.FLINTLOCK_PISTOL.get())
					.requires(ModItems.SMALL_IRON_MUSKET_BALL.get())
					.requires(Items.GUNPOWDER)
					.unlockedBy("has_flintlock_pistol", has(ModItems.FLINTLOCK_PISTOL.get()))
					.unlockedBy("has_small_iron_musket_ball", has(ModItems.SMALL_IRON_MUSKET_BALL.get()))
					.save(recipeConsumer, new ResourceLocation(OldGuns.MODID, "flintlock_pistol_reload"));
		}

	}

	@Override
	public String getName() {
		return "OldGunsRecipes";
	}
}
