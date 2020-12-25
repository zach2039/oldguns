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
import net.minecraftforge.oredict.ShapelessOreRecipe;
import zach2039.oldguns.api.firearm.FirearmType.FirearmCondition;
import zach2039.oldguns.common.init.ModConfigs.ConfigCategoryRecipes;
import zach2039.oldguns.common.item.ammo.ItemFirearmAmmo;
import zach2039.oldguns.common.item.crafting.util.RecipeUtil;
import zach2039.oldguns.common.item.firearm.ItemFirearm;
import zach2039.oldguns.common.item.util.FirearmNBTHelper;

public class RecipesFirearmReload extends ShapelessOreRecipe
{
	/**
	 * Default constructor.
	 */
	public RecipesFirearmReload(@Nullable final ResourceLocation group, final NonNullList<Ingredient> input, final ItemStack result) {
		super(group, input, result);
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv)
	{
		/* Required itemstacks for proper nbt results. */
		ItemStack firearmStack = ItemStack.EMPTY;
		ItemStack ammoStack = ItemStack.EMPTY;
		
		/* Find input firearm and copy to output. */
		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			ItemStack stack = inv.getStackInSlot(i);
			
			/* If item is a firearm instance and can reload, set output stack and break. */
			if (stack.getItem() instanceof ItemFirearm)
			{
				ItemFirearm firearmItem = (ItemFirearm) stack.getItem();
				
				if (firearmItem.canReload(stack) && FirearmNBTHelper.getNBTTagCondition(stack) != FirearmCondition.BROKEN)
				{
					firearmStack = stack.copy();
					break;
				}
			}
		}
		
		/* Find input ammo and copy to output. */
		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			ItemStack stack = inv.getStackInSlot(i);
			
			/* If item is a ammo instance, set input ammo stack and break. */
			if (stack.getItem() instanceof ItemFirearmAmmo)
			{
				ammoStack = stack.copy();
				break;
			}
		}
		
		/* Increase ammo count by one on stack, if all itemstacks were found. */
		if (!firearmStack.isEmpty() && !ammoStack.isEmpty())
		{
			FirearmNBTHelper.pushNBTTagAmmo(firearmStack, ammoStack);
		}
		
		return ConfigCategoryRecipes.isRecipeEnabled(firearmStack) ? firearmStack : ItemStack.EMPTY;
		//return firearmStack;
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
			/* Create a new single reload recipe using parsed json info. */
			final String group = JsonUtils.getString(json, "group", "");
			final NonNullList<Ingredient> ingredients = RecipeUtil.parseShapeless(context, json);
			final ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
			
			return new RecipesFirearmReload(group.isEmpty() ? null : new ResourceLocation(group), ingredients, result);
		}
	}
}
