package zach2039.oldguns.common.item.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import zach2039.oldguns.common.init.ModRecipes;
import zach2039.oldguns.common.item.crafting.RecipesFirearmBreechloaderReload;

public class FirearmRecipeHelper {

	/**
	 * Sets the ammo count of the firearm itemstack instance.
	 * @param stackIn
	 */
	public static List<RecipesFirearmBreechloaderReload> getBreechloadingReloadRecipes(ItemStack stackIn)
	{
		List<RecipesFirearmBreechloaderReload> recipes = new ArrayList<RecipesFirearmBreechloaderReload>();
		
		ModRecipes.breechloaderReloadRecipes.forEach((RecipesFirearmBreechloaderReload recipe) -> {
			if (recipe.getRecipeOutput().getItem() == stackIn.getItem()) 
			{
				recipes.add(recipe);
			}
		});
		
		return recipes;
	}
}
