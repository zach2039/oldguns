package com.zach2039.oldguns.world.item.crafting.recipe;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.item.crafting.util.ModRecipeUtil;

import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraftforge.common.crafting.CraftingHelper;

public class ShapelessVanillaScrapDesignNotesRecipe extends ShapelessRecipe {
	
	public ShapelessVanillaScrapDesignNotesRecipe(ResourceLocation id, String group, ItemStack result, NonNullList<Ingredient> ingredients) {
		super(id, group, result, ingredients);
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(final CraftingContainer inv) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);

		// Dont allow remaining items, since we want to refil the powder horn and not duplicate it
		
		return remainingItems;
	}
	
	public static class Serializer implements RecipeSerializer<ShapelessVanillaScrapDesignNotesRecipe> {
		
		@Override
		public ShapelessVanillaScrapDesignNotesRecipe fromJson(final ResourceLocation recipeID, final JsonObject json) {
			final String group = GsonHelper.getAsString(json, "group", "");
			final NonNullList<Ingredient> ingredients = ModRecipeUtil.parseShapeless(json);
			final ItemStack result = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);

			ShapelessVanillaScrapDesignNotesRecipe recipeFromJson = new ShapelessVanillaScrapDesignNotesRecipe(recipeID, group, result, ingredients);
			
			return recipeFromJson;
		}

		@Override
		public ShapelessVanillaScrapDesignNotesRecipe fromNetwork(final ResourceLocation recipeID, final FriendlyByteBuf buffer) {
			final String group = buffer.readUtf(Short.MAX_VALUE);
			final int numIngredients = buffer.readVarInt();
			final NonNullList<Ingredient> ingredients = NonNullList.withSize(numIngredients, Ingredient.EMPTY);

			for (int j = 0; j < ingredients.size(); ++j) {
				ingredients.set(j, Ingredient.fromNetwork(buffer));
			}

			final ItemStack result = buffer.readItem();

			return new ShapelessVanillaScrapDesignNotesRecipe(recipeID, group, result, ingredients);
		}

		@Override
		public void toNetwork(final FriendlyByteBuf buffer, final ShapelessVanillaScrapDesignNotesRecipe recipe) {
			buffer.writeUtf(recipe.getGroup());
			buffer.writeVarInt(recipe.getIngredients().size());

			for (final Ingredient ingredient : recipe.getIngredients()) {
				ingredient.toNetwork(buffer);
			}

			buffer.writeItem(recipe.getResultItem());
		}
	}
	
	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModCrafting.Recipes.SCRAP_DESIGN_NOTES_SHAPELESS.get();
	}
}
