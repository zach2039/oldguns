package zach2039.oldguns.integration.jei.gunsmithsbench;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mezz.jei.Internal;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import mezz.jei.startup.StackHelper;
import mezz.jei.util.ErrorUtil;
import mezz.jei.util.Log;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.oredict.OreIngredient;
import scala.actors.threadpool.Arrays;
import zach2039.oldguns.common.item.crafting.ShapedGunsmithsBenchRecipe;
import zach2039.oldguns.common.item.crafting.ShapelessGunsmithsBenchRecipe;

public final class GunsmithsBenchRecipeChecker {

	private GunsmithsBenchRecipeChecker() {}
	
	public static List<IRecipe> getValidRecipes(final IJeiHelpers jeiHelpers) {
		CraftingRecipeValidator<ShapelessGunsmithsBenchRecipe> shapelessGunsmithsBenchRecipeValidator = new CraftingRecipeValidator<>(recipe -> new ShapelessGunsmithsBenchRecipeWrapper(jeiHelpers, recipe));
		CraftingRecipeValidator<ShapedGunsmithsBenchRecipe> shapedGunsmithsBenchRecipeValidator = new CraftingRecipeValidator<>(recipe -> new ShapedGunsmithsBenchRecipeWrapper(jeiHelpers, recipe));
		
		StackHelper stackHelper = Internal.getStackHelper();
		Iterator<IRecipe> recipeIterator = CraftingManager.REGISTRY.iterator();
		List<IRecipe> validRecipes = new ArrayList<>();
		while (recipeIterator.hasNext()) 
		{
			IRecipe recipe = recipeIterator.next();
			if (recipe instanceof ShapelessGunsmithsBenchRecipe) 
			{
				if (shapelessGunsmithsBenchRecipeValidator.isRecipeValid((ShapelessGunsmithsBenchRecipe) recipe, stackHelper)) 
				{
					validRecipes.add(recipe);
				}
			} 
			else if (recipe instanceof ShapedGunsmithsBenchRecipe) 
			{
				if (shapedGunsmithsBenchRecipeValidator.isRecipeValid((ShapedGunsmithsBenchRecipe) recipe, stackHelper)) 
				{
					validRecipes.add(recipe);
				}
			}
		}
		return validRecipes;
	}
	
	private static final class CraftingRecipeValidator<T extends IRecipe> {
		private static final int INVALID_COUNT = -1;
		private static final int CANT_DISPLAY = -2;
		private final IRecipeWrapperFactory<T> recipeWrapperFactory;
		
		public CraftingRecipeValidator(IRecipeWrapperFactory<T> recipeWrapperFactory) {
			this.recipeWrapperFactory = recipeWrapperFactory;
		}
		
		public boolean isRecipeValid(T recipe, StackHelper stackHelper) 
		{
			ItemStack recipeOutput = recipe.getRecipeOutput();
			//noinspection ConstantConditions
			if (recipeOutput == null || recipeOutput.isEmpty()) 
			{
				if (!recipe.isDynamic()) 
				{
					String recipeInfo = getInfo(recipe);
					Log.get().error("Recipe has no output. {}", recipeInfo);
				}
				return false;
			}
			
			List<Ingredient> ingredients = recipe.getIngredients();
			//noinspection ConstantConditions
			if (ingredients == null) 
			{
				String recipeInfo = getInfo(recipe);
				Log.get().error("Recipe has no input Ingredients. {}", recipeInfo);
				return false;
			}
			int inputCount = getInputCount(ingredients, stackHelper);
			if (inputCount == CANT_DISPLAY) 
			{
				String recipeInfo = getInfo(recipe);
				Log.get().warn("Recipe contains ingredients that can't be understood or displayed by JEI: {}", recipeInfo);
				return false;
			} 
			else if (inputCount == INVALID_COUNT) 
			{
				return false;
			} 
			else if (inputCount > 9) 
			{
				String recipeInfo = getInfo(recipe);
				Log.get().error("Recipe has too many inputs. {}", recipeInfo);
				return false;
			}
			else if (inputCount == 0) 
			{
				String recipeInfo = getInfo(recipe);
				Log.get().error("Recipe has no inputs. {}", recipeInfo);
				return false;
			}
			return true;
		}

		private String getInfo(T recipe) 
		{
			IRecipeWrapper recipeWrapper = recipeWrapperFactory.getRecipeWrapper(recipe);
			return ErrorUtil.getInfoFromRecipe(recipe, recipeWrapper);
		}
		
		protected static int getInputCount(List<Ingredient> ingredientList, StackHelper stackHelper) 
		{
			int inputCount = 0;
			for (Ingredient ingredient : ingredientList) 
			{
				List<ItemStack> input = Arrays.asList(ingredient.getMatchingStacks());
				//noinspection ConstantConditions
				if (input == null) 
				{
					return INVALID_COUNT;
				} 
				else if (ingredient instanceof OreIngredient && input.isEmpty()) 
				{
					return INVALID_COUNT;
				}
				else if (!ingredient.isSimple() && input.isEmpty()) 
				{
					return CANT_DISPLAY;
				}
				else
				{
					inputCount++;
				}
			}
			return inputCount;
		}
	}
}
