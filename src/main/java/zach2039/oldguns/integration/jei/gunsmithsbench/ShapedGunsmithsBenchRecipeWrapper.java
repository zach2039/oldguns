package zach2039.oldguns.integration.jei.gunsmithsbench;

import java.util.List;

import javax.annotation.Nullable;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IStackHelper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.item.crafting.ShapedGunsmithsBenchRecipe;

public class ShapedGunsmithsBenchRecipeWrapper implements IShapedCraftingRecipeWrapper {

	private final IJeiHelpers jeiHelpers;
	protected final ShapedGunsmithsBenchRecipe recipe;
	
	public ShapedGunsmithsBenchRecipeWrapper(IJeiHelpers jeiHelpers, ShapedGunsmithsBenchRecipe recipe) 
	{
		this.jeiHelpers = jeiHelpers;
		this.recipe = recipe;
	}

	@Override
	public int getWidth() {
		return recipe.getRecipeWidth();
	}

	@Override
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
			OldGuns.logger.catching(e);
		}
	}

	@Nullable
	@Override
	public ResourceLocation getRegistryName() {
		return recipe.getRegistryName();
	}
}
