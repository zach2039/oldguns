package zach2039.oldguns.integration.jei.gunsmithsbench;

import java.util.List;

import javax.annotation.Nullable;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IStackHelper;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import mezz.jei.recipes.BrokenCraftingRecipeException;
import mezz.jei.util.ErrorUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import zach2039.oldguns.common.item.crafting.ShapedGunsmithsBenchRecipe;

public class ShapedGunsmithsBenchRecipeWrapper implements ICraftingRecipeWrapper {

	private final IJeiHelpers jeiHelpers;
	protected final ShapedGunsmithsBenchRecipe recipe;
	
	public ShapedGunsmithsBenchRecipeWrapper(IJeiHelpers jeiHelpers, ShapedGunsmithsBenchRecipe recipe) 
	{
		this.jeiHelpers = jeiHelpers;
		this.recipe = recipe;
	}

	public int getWidth() {
		return recipe.getRecipeWidth();
	}

	public int getHeight() {
		return recipe.getRecipeHeight();
	}

	@Override
	public void getIngredients(IIngredients ingredients) 
	{
		ItemStack recipeOutput = recipe.getRecipeOutput();
		IStackHelper stackHelper = jeiHelpers.getStackHelper();

		try {
			List<List<ItemStack>> inputLists = stackHelper.expandRecipeItemStackInputs(recipe.getIngredients());
			ingredients.setInputLists(VanillaTypes.ITEM, inputLists);
			ingredients.setOutput(VanillaTypes.ITEM, recipeOutput);
		} catch (RuntimeException e) {
			String info = ErrorUtil.getInfoFromBrokenCraftingRecipe(recipe, recipe.getIngredients(), recipeOutput);
			throw new BrokenCraftingRecipeException(info, e);
		}
	}

	@Nullable
	@Override
	public ResourceLocation getRegistryName() {
		return recipe.getRegistryName();
	}
}
