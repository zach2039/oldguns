package zach2039.oldguns.common.item.crafting.base;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import zach2039.oldguns.api.crafting.impl.IGunsmithsBench;
import zach2039.oldguns.common.init.ModConfigs;

import javax.annotation.Nonnull;

public class GunsmithsBenchRecipe extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> {

	protected final NonNullList<Ingredient> recipeItems;
	@Nonnull
	public final ItemStack recipeOutput;
	protected final ResourceLocation group;

	public GunsmithsBenchRecipe(ResourceLocation group, @Nonnull ItemStack recipeOutput, NonNullList<Ingredient> recipeItems)
	{
		this.group = group;
		this.recipeOutput = recipeOutput;
		this.recipeItems = recipeItems;
	}

	public ItemStack getCraftingResult(InventoryCrafting inv)
	{
		/* Return if crafting block isn't a gunsmith's workbench. */
		if (!(inv instanceof IGunsmithsBench))
		{
		return ItemStack.EMPTY;
		}

		return ModConfigs.ConfigCategoryRecipes.isRecipeEnabled(recipeOutput) ? recipeOutput.copy() : ItemStack.EMPTY;
	}

	public NonNullList<Ingredient> getIngredients()
	{
		return recipeItems;
	}
}
