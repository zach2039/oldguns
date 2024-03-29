package com.zach2039.oldguns.world.item.crafting.recipe;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.item.crafting.util.ModRecipeUtil;
import com.zach2039.oldguns.world.item.tools.MortarAndPestleItem;

import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.ForgeEventFactory;

public class ShapelessVanillaMortarAndPestleRecipe extends ShapelessRecipe {

	private final ItemStack result;

	public ShapelessVanillaMortarAndPestleRecipe(ResourceLocation id, String group, ItemStack result,
			NonNullList<Ingredient> ingredients) {
		super(id, group, CraftingBookCategory.MISC, result, ingredients);
		this.result = result;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(final CraftingContainer inv) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);

		for (int i = 0; i < remainingItems.size(); ++i) {
			final ItemStack itemstack = inv.getItem(i);

			if (!itemstack.isEmpty() && (itemstack.getItem() instanceof MortarAndPestleItem)) {
				remainingItems.set(i, damageItem(itemstack.copy()));
			} else {
				remainingItems.set(i, ForgeHooks.getCraftingRemainingItem(itemstack));
			}
		}

		return remainingItems;
	}

	private ItemStack damageItem(final ItemStack stack) {
		final Player craftingPlayer = ForgeHooks.getCraftingPlayer();

		Level level = (craftingPlayer != null) ? craftingPlayer.getCommandSenderWorld() : null;
		RandomSource rand = (level != null) ? level.random : RandomSource.create();
		if (stack.hurt(1, rand, craftingPlayer instanceof ServerPlayer ? (ServerPlayer) craftingPlayer : null)) {
			ForgeEventFactory.onPlayerDestroyItem(craftingPlayer, stack, null);
			return ItemStack.EMPTY;
		}

		return stack;
	}
	
	public static class Serializer implements RecipeSerializer<ShapelessVanillaMortarAndPestleRecipe> {

		@Override
		public ShapelessVanillaMortarAndPestleRecipe fromJson(final ResourceLocation recipeID, final JsonObject json) {
			final String group = GsonHelper.getAsString(json, "group", "");
			final NonNullList<Ingredient> ingredients = ModRecipeUtil.parseShapeless(json);
			final ItemStack result = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);

			ShapelessVanillaMortarAndPestleRecipe recipeFromJson = new ShapelessVanillaMortarAndPestleRecipe(recipeID, group, result, ingredients);

			return recipeFromJson;
		}

		@Override
		public ShapelessVanillaMortarAndPestleRecipe fromNetwork(final ResourceLocation recipeID, final FriendlyByteBuf buffer) {
			final String group = buffer.readUtf(Short.MAX_VALUE);
			final int numIngredients = buffer.readVarInt();
			final NonNullList<Ingredient> ingredients = NonNullList.withSize(numIngredients, Ingredient.EMPTY);

			for (int j = 0; j < ingredients.size(); ++j) {
				ingredients.set(j, Ingredient.fromNetwork(buffer));
			}

			final ItemStack result = buffer.readItem();

			return new ShapelessVanillaMortarAndPestleRecipe(recipeID, group, result, ingredients);
		}

		@Override
		public void toNetwork(final FriendlyByteBuf buffer, final ShapelessVanillaMortarAndPestleRecipe recipe) {
			buffer.writeUtf(recipe.getGroup());
			buffer.writeVarInt(recipe.getIngredients().size());

			for (final Ingredient ingredient : recipe.getIngredients()) {
				ingredient.toNetwork(buffer);
			}

			buffer.writeItem(recipe.result);
		}
	}
	
	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModCrafting.Recipes.MORTAR_AND_PESTLE_SHAPELESS.get();
	}
}

