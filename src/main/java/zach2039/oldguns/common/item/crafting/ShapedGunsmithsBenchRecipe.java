package zach2039.oldguns.common.item.crafting;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import zach2039.oldguns.api.crafting.impl.IGunsmithsBench;
import zach2039.oldguns.common.item.crafting.base.ShapedOldGunsRecipe;
import zach2039.oldguns.common.item.crafting.util.RecipeUtil;

public class ShapedGunsmithsBenchRecipe extends ShapedOldGunsRecipe
{
	/**
	 * Default constructor.
	 */
	public ShapedGunsmithsBenchRecipe(@Nullable final ResourceLocation group, final ItemStack result, final CraftingHelper.ShapedPrimer primer) 
	{
		super(group, result, primer);
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
			final CraftingHelper.ShapedPrimer primer = RecipeUtil.parseShaped(context, json);
			final ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);

			return new ShapedGunsmithsBenchRecipe(group.isEmpty() ? null : new ResourceLocation(group), result, primer);
		}
	}
}
