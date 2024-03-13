package zach2039.oldguns.common.item.crafting;

import javax.annotation.Nonnull;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.google.gson.JsonSyntaxException;
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
import net.minecraftforge.common.crafting.IShapedRecipe;
import net.minecraftforge.common.crafting.JsonContext;
import zach2039.oldguns.common.item.crafting.base.GunsmithsBenchRecipe;
import zach2039.oldguns.common.item.crafting.util.RecipeUtil;

import java.util.Map;
import java.util.Set;

public class ShapedGunsmithsBenchRecipe extends GunsmithsBenchRecipe implements IShapedRecipe
{
	protected int width = 0;
	protected int height = 0;
	protected boolean mirrored = true;

	public ShapedGunsmithsBenchRecipe(ResourceLocation group, @Nonnull ItemStack result, Object... recipe) {
		this(group, result, CraftingHelper.parseShaped(recipe));
	}
	public ShapedGunsmithsBenchRecipe(ResourceLocation group, @Nonnull ItemStack result, CraftingHelper.ShapedPrimer primer)
	{
		super(group, result.copy(), primer.input);
		this.width = primer.width;
		this.height = primer.height;
		this.mirrored = primer.mirrored;
	}



	public static class Factory implements IRecipeFactory
	{
		@Override
		public IRecipe parse(JsonContext context, JsonObject json)
		{
			/* Create a new firearm build recipe using parsed json info. */
			final String group = JsonUtils.getString(json, "group", "");
			final CraftingHelper.ShapedPrimer primer = RecipeUtil.parseShaped(context, json);
			final ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);

			return new ShapedGunsmithsBenchRecipe(group.isEmpty() ? null : new ResourceLocation(group), result, primer);
		}
	}

	/**
	 * Returns an Item that is the result of this recipe
	 */
	@Override
	@Nonnull
	public ItemStack getCraftingResult(@Nonnull InventoryCrafting var1){ return super.getCraftingResult(var1); }

	@Override
	@Nonnull
	public ItemStack getRecipeOutput(){ return recipeOutput; }

	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	@Override
	public boolean matches(@Nonnull InventoryCrafting inv, @Nonnull World world)
	{
		for (int x = 0; x <= inv.getWidth() - width; x++)
		{
			for (int y = 0; y <= inv.getHeight() - height; ++y)
			{
				if (checkMatch(inv, x, y, false))
				{
					return true;
				}

				if (mirrored && checkMatch(inv, x, y, true))
				{
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Based on net.minecraft.item.crafting.ShapedRecipes#checkMatch(InventoryCrafting, int, int, boolean)
	 */
	protected boolean checkMatch(InventoryCrafting inv, int startX, int startY, boolean mirror)
	{
		for (int x = 0; x < inv.getWidth(); x++)
		{
			for (int y = 0; y < inv.getHeight(); y++)
			{
				int subX = x - startX;
				int subY = y - startY;
				Ingredient target = Ingredient.EMPTY;

				if (subX >= 0 && subY >= 0 && subX < width && subY < height)
				{
					if (mirror)
					{
						target = recipeItems.get(width - subX - 1 + subY * width);
					}
					else
					{
						target = recipeItems.get(subX + subY * width);
					}
				}

				if (!target.apply(inv.getStackInRowAndColumn(x, y)))
				{
					return false;
				}
			}
		}

		return true;
	}

	public ShapedGunsmithsBenchRecipe setMirrored(boolean mirror)
	{
		mirrored = mirror;
		return this;
	}

	@Override
	@Nonnull
	public NonNullList<Ingredient> getIngredients()
	{
		return super.getIngredients();
	}

	@Deprecated //Use IShapedRecipe.getRecipeWidth
	public int getWidth()
	{
		return width;
	}

	@Override
	public int getRecipeWidth()
	{
		return this.getWidth();
	}

	@Deprecated //Use IShapedRecipe.getRecipeHeight
	public int getHeight()
	{
		return height;
	}

	@Override
	public int getRecipeHeight()
	{
		return this.getHeight();
	}

	@Override
	@Nonnull
	public String getGroup()
	{
		return this.group == null ? "" : this.group.toString();
	}

	/**
	 * Used to determine if this recipe can fit in a grid of the given width/height
	 */
	@Override
	public boolean canFit(int width, int height)
	{
		return width >= this.width && height >= this.height;
	}

	public static ShapedGunsmithsBenchRecipe factory(JsonContext context, JsonObject json)
	{
		String group = JsonUtils.getString(json, "group", "");
		//if (!group.isEmpty() && group.indexOf(':') == -1)
		//    group = context.getModId() + ":" + group;

		Map<Character, Ingredient> ingMap = Maps.newHashMap();
		for (Map.Entry<String, JsonElement> entry : JsonUtils.getJsonObject(json, "key").entrySet())
		{
			if (entry.getKey().length() != 1)
				throw new JsonSyntaxException("Invalid key entry: '" + entry.getKey() + "' is an invalid symbol (must be 1 character only).");
			if (" ".equals(entry.getKey()))
				throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");

			ingMap.put(entry.getKey().toCharArray()[0], CraftingHelper.getIngredient(entry.getValue(), context));
		}

		ingMap.put(' ', Ingredient.EMPTY);

		JsonArray patternJ = JsonUtils.getJsonArray(json, "pattern");

		if (patternJ.size() == 0)
			throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");

		String[] pattern = new String[patternJ.size()];
		for (int x = 0; x < pattern.length; ++x)
		{
			String line = JsonUtils.getString(patternJ.get(x), "pattern[" + x + "]");
			if (x > 0 && pattern[0].length() != line.length())
				throw new JsonSyntaxException("Invalid pattern: each row must  be the same width");
			pattern[x] = line;
		}

		CraftingHelper.ShapedPrimer primer = new CraftingHelper.ShapedPrimer();
		primer.width = pattern[0].length();
		primer.height = pattern.length;
		primer.mirrored = JsonUtils.getBoolean(json, "mirrored", true);
		primer.input = NonNullList.withSize(primer.width * primer.height, Ingredient.EMPTY);

		Set<Character> keys = Sets.newHashSet(ingMap.keySet());
		keys.remove(' ');

		int x = 0;
		for (String line : pattern)
		{
			for (char chr : line.toCharArray())
			{
				Ingredient ing = ingMap.get(chr);
				if (ing == null)
					throw new JsonSyntaxException("Pattern references symbol '" + chr + "' but it's not defined in the key");
				primer.input.set(x++, ing);
				keys.remove(chr);
			}
		}

		if (!keys.isEmpty())
			throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + keys);

		ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
		return new ShapedGunsmithsBenchRecipe(group.isEmpty() ? null : new ResourceLocation(group), result, primer);
	}
}
