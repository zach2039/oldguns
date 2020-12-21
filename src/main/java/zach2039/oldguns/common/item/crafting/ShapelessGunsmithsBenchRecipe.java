package zach2039.oldguns.common.item.crafting;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import zach2039.oldguns.api.crafting.impl.IGunsmithsBench;
import zach2039.oldguns.common.item.crafting.base.ShapelessOldGunsRecipe;
import zach2039.oldguns.common.item.crafting.util.RecipeUtil;

public class ShapelessGunsmithsBenchRecipe extends ShapelessOldGunsRecipe
{
	/**
	 * Default constructor.
	 */
	public ShapelessGunsmithsBenchRecipe(@Nullable final ResourceLocation group, final NonNullList<Ingredient> inputs, final ItemStack result) 
	{
		super(group, inputs, result);
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
		
		return output;
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
}
