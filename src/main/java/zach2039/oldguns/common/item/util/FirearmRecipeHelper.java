package zach2039.oldguns.common.item.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import zach2039.oldguns.common.init.ModRecipes;
import zach2039.oldguns.common.item.crafting.BreechloadingReloadRecipe;

public class FirearmRecipeHelper {

	/**
	 * Sets the ammo count of the firearm itemstack instance.
	 * @param stackIn
	 */
	public static List<BreechloadingReloadRecipe> getBreechloadingReloadRecipes(ItemStack stackIn)
	{
		List<BreechloadingReloadRecipe> recipes = new ArrayList<BreechloadingReloadRecipe>();
		
		ModRecipes.breechloaderReloadRecipes.forEach((BreechloadingReloadRecipe recipe) -> {
			if (recipe.getRecipeOutput().getItem() == stackIn.getItem()) 
			{
				recipes.add(recipe);
			}
		});
		
		return recipes;
	}
}
