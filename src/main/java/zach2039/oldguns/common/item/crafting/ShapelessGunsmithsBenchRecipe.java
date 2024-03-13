package zach2039.oldguns.common.item.crafting;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.google.gson.JsonParseException;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.common.util.RecipeMatcher;
import zach2039.oldguns.api.crafting.impl.IGunsmithsBench;
import zach2039.oldguns.common.init.ModConfigs.ConfigCategoryRecipes;
import zach2039.oldguns.common.item.crafting.base.GunsmithsBenchRecipe;
import zach2039.oldguns.common.item.crafting.util.RecipeUtil;

import java.util.List;

public class ShapelessGunsmithsBenchRecipe extends GunsmithsBenchRecipe implements IRecipe
{
	protected boolean isSimple = true;

	public ShapelessGunsmithsBenchRecipe(ResourceLocation group, NonNullList<Ingredient> inputs, @Nonnull ItemStack result)
	{
		super(group, result.copy(), inputs);
		for (Ingredient i : recipeItems)
			this.isSimple &= i.isSimple();
	}
	public ShapelessGunsmithsBenchRecipe(ResourceLocation group, @Nonnull ItemStack result, Object... recipe)
	{
		super(group, result.copy(), NonNullList.create());
		for (Object in : recipe)
		{
			Ingredient ing = CraftingHelper.getIngredient(in);
			if (ing != null)
			{
				recipeItems.add(ing);
				this.isSimple &= ing.isSimple();
			}
			else
			{
				String ret = "Invalid shapeless ore recipe: ";
				for (Object tmp :  recipe)
				{
					ret += tmp + ", ";
				}
				ret += result;
				throw new RuntimeException(ret);
			}
		}
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv)
	{
		/* Get default output. */
		ItemStack output = super.getCraftingResult(inv); 
		
		/* Return if crafting block isn't a gunsmith's workbench. */
		if (!(inv instanceof IGunsmithsBench))
		{
			return ItemStack.EMPTY;
		}
		
		return ConfigCategoryRecipes.isRecipeEnabled(output) ? output : ItemStack.EMPTY;
		//return output;
	}

	@Override
	public String getGroup()
	{
		return group == null ? "" : group.toString();
	}
	
	public static class Factory implements IRecipeFactory
	{
		@Override
		public IRecipe parse(JsonContext context, JsonObject json)
		{
			/* Create a new firearm build recipe using parsed json info. */
			final String group = JsonUtils.getString(json, "group", "");
			final NonNullList<Ingredient> ingredients = RecipeUtil.parseShapeless(context, json);
			final ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);

			return new ShapelessGunsmithsBenchRecipe(group.isEmpty() ? null : new ResourceLocation(group), ingredients, result);
		}
	}

	@Override
	@Nonnull
	public ItemStack getRecipeOutput(){ return recipeOutput; }

	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	@Override
	public boolean matches(@Nonnull InventoryCrafting inv, @Nonnull World world)
	{
		int ingredientCount = 0;
		RecipeItemHelper recipeItemHelper = new RecipeItemHelper();
		List<ItemStack> items = Lists.newArrayList();

		for (int i = 0; i < inv.getSizeInventory(); ++i)
		{
			ItemStack itemstack = inv.getStackInSlot(i);
			if (!itemstack.isEmpty())
			{
				++ingredientCount;
				if (this.isSimple)
					recipeItemHelper.accountStack(itemstack, 1);
				else
					items.add(itemstack);
			}
		}

		if (ingredientCount != this.recipeItems.size())
			return false;

		if (this.isSimple)
			return recipeItemHelper.canCraft(this, null);

		return RecipeMatcher.findMatches(items, this.recipeItems) != null;
	}

	@Override
	@Nonnull
	public NonNullList<Ingredient> getIngredients()
	{
		return this.recipeItems;
	}

	/**
	 * Used to determine if this recipe can fit in a grid of the given width/height
	 */
	@Override
	public boolean canFit(int width, int height)
	{
		return width * height >= this.recipeItems.size();
	}

	public static ShapelessGunsmithsBenchRecipe factory(JsonContext context, JsonObject json)
	{
		String group = JsonUtils.getString(json, "group", "");

		NonNullList<Ingredient> ings = NonNullList.create();
		for (JsonElement ele : JsonUtils.getJsonArray(json, "ingredients"))
			ings.add(CraftingHelper.getIngredient(ele, context));

		if (ings.isEmpty())
			throw new JsonParseException("No ingredients for shapeless recipe");

		ItemStack itemstack = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
		return new ShapelessGunsmithsBenchRecipe(group.isEmpty() ? null : new ResourceLocation(group), ings, itemstack);
	}
}
