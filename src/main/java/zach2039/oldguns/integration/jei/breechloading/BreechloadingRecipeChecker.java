package zach2039.oldguns.integration.jei.breechloading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IRecipeWrapperFactory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.oredict.OreIngredient;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.item.crafting.BreechloadingReloadRecipe;
import zach2039.oldguns.common.item.crafting.ShapedGunsmithsBenchRecipe;
import zach2039.oldguns.common.item.crafting.ShapelessGunsmithsBenchRecipe;

public final class BreechloadingRecipeChecker {

	private BreechloadingRecipeChecker() {}
	
	public static List<IRecipe> getValidRecipes(final IJeiHelpers jeiHelpers) {
		CraftingRecipeValidator<BreechloadingReloadRecipe> breechloadingRecipeValidator = new CraftingRecipeValidator<>(recipe -> new BreechloadingRecipeWrapper(jeiHelpers, recipe));
		
		Iterator<IRecipe> recipeIterator = CraftingManager.REGISTRY.iterator();
		List<IRecipe> validRecipes = new ArrayList<>();
		while (recipeIterator.hasNext()) 
		{
			IRecipe recipe = recipeIterator.next();
			if (recipe instanceof BreechloadingReloadRecipe) 
			{
				if (breechloadingRecipeValidator.isRecipeValid((BreechloadingReloadRecipe) recipe)) 
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
		
		public boolean isRecipeValid(T recipe) 
		{		
			List<Ingredient> ingredients = recipe.getIngredients();
			//noinspection ConstantConditions
			if (ingredients == null) 
			{
				return false;
			}
			int inputCount = getInputCount(ingredients);
			if (inputCount == CANT_DISPLAY) 
			{
				return false;
			} 
			else if (inputCount == INVALID_COUNT) 
			{
				return false;
			} 
			else if (inputCount > 9) 
			{
				return false;
			}
			else if (inputCount == 0) 
			{
				return false;
			}
			return true;
		}
		
		protected static int getInputCount(List<Ingredient> ingredientList) 
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
