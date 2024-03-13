package zach2039.oldguns.compat.jei.gunsmithsbench;

import java.util.List;

import javax.annotation.Nullable;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IStackHelper;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.item.crafting.ShapelessGunsmithsBenchRecipe;

public class ShapelessGunsmithsBenchRecipeWrapper implements ICraftingRecipeWrapper {
	
	private final IJeiHelpers jeiHelpers;
	protected final ShapelessGunsmithsBenchRecipe recipe;

	public ShapelessGunsmithsBenchRecipeWrapper(IJeiHelpers jeiHelpers, ShapelessGunsmithsBenchRecipe recipe) 
	{
		this.jeiHelpers = jeiHelpers;
		this.recipe = recipe;
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
