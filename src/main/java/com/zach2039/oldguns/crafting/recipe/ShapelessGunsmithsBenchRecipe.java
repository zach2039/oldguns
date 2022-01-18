package com.zach2039.oldguns.crafting.recipe;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.api.crafting.IDesignNotes;
import com.zach2039.oldguns.api.firearm.IFirearm;
import com.zach2039.oldguns.crafting.util.ModRecipeUtil;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.inventory.GunsmithsBenchCraftingContainer;
import com.zach2039.oldguns.world.inventory.menu.GunsmithsBenchMenu;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ShapelessGunsmithsBenchRecipe implements GunsmithsBenchRecipe {
	
	private final ResourceLocation id;
	final String group;
	final ItemStack result;
	final NonNullList<Ingredient> ingredients;
	private final boolean isSimple;

	public ShapelessGunsmithsBenchRecipe(ResourceLocation id, String group, ItemStack result, NonNullList<Ingredient> ingredients) {
		this.id = id;
		this.group = group;
		this.result = result;
		this.ingredients = ingredients;
		this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
	}

	@Override
	public boolean matches(GunsmithsBenchCraftingContainer craftinv, World level) {
		StackedContents stackedcontents = new StackedContents();
		java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
		int i = 0;

		for(int j = 0; j < craftinv.getContainerSize(); ++j) {
			ItemStack itemstack = craftinv.getItem(j);
			if (!itemstack.isEmpty()) {
				++i;
				if (isSimple)
					stackedcontents.accountStack(itemstack, 1);
				else inputs.add(itemstack);
			}
		}

		return i == this.ingredients.size() && (isSimple ? stackedcontents.canCraft(this, (IntList)null) : net.minecraftforge.common.util.RecipeMatcher.findMatches(inputs,  this.ingredients) != null);
	}
	
	@Override
	public ItemStack assemble(GunsmithsBenchCraftingContainer craftinv) {
		ItemStack resultStack = this.result.copy();
		
		if (requiresDesignNotes(resultStack.getItem())) {
			ItemStack item = craftinv.getItem(GunsmithsBenchMenu.NOTES_SLOT);
			if (!(item.getItem() instanceof IDesignNotes))
				return ItemStack.EMPTY;
			
			String designName = IDesignNotes.getDesign(item);
			String resultName = resultStack.getItem().getRegistryName().toString();
			if (!designName.equals(resultName))
				return ItemStack.EMPTY;
		}
		
		if (resultStack.getItem() instanceof IFirearm)
			((IFirearm)this.result.getItem()).initNBTTags(resultStack);
		
		return resultStack;
	}

	@Override
	public boolean canCraftInDimensions(int x, int y) {
		return x * y >= this.ingredients.size();
	}
	
	@Override	
	public String getGroup() {
		return this.group;
	}
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		return this.ingredients;
	}
	
	@Override
    @Nonnull
    public ItemStack getResultItem()
	{
		ItemStack outputStack = this.result;
		
		if (outputStack.getItem() instanceof IFirearm)
			((IFirearm)outputStack.getItem()).initNBTTags(outputStack);
		
		return outputStack;
	}

	@Override
	public ResourceLocation getId() {
		return this.id;
	}
	
	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModCrafting.Recipes.GUNSMITHS_BENCH_SHAPELESS.get();
	}

	public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<ShapelessGunsmithsBenchRecipe> {
		
		@Override
		public ShapelessGunsmithsBenchRecipe fromJson(final ResourceLocation recipeID, final JsonObject json) {
			final String group = GsonHelper.getAsString(json, "group", "");
			final NonNullList<Ingredient> ingredients = ModRecipeUtil.parseShapeless(json);
			final ItemStack result = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);

			ShapelessGunsmithsBenchRecipe recipeFromJson = new ShapelessGunsmithsBenchRecipe(recipeID, group, result, ingredients);
			
			return recipeFromJson;
		}

		@Override
		public ShapelessGunsmithsBenchRecipe fromNetwork(final ResourceLocation recipeID, final FriendlyByteBuf buffer) {
			final String group = buffer.readUtf(Short.MAX_VALUE);
			final int numIngredients = buffer.readVarInt();
			final NonNullList<Ingredient> ingredients = NonNullList.withSize(numIngredients, Ingredient.EMPTY);

			for (int j = 0; j < ingredients.size(); ++j) {
				ingredients.set(j, Ingredient.fromNetwork(buffer));
			}

			final ItemStack result = buffer.readItem();

			return new ShapelessGunsmithsBenchRecipe(recipeID, group, result, ingredients);
		}

		@Override
		public void toNetwork(final FriendlyByteBuf buffer, final ShapelessGunsmithsBenchRecipe recipe) {
			buffer.writeUtf(recipe.getGroup());
			buffer.writeVarInt(recipe.getIngredients().size());

			for (final Ingredient ingredient : recipe.getIngredients()) {
				ingredient.toNetwork(buffer);
			}

			buffer.writeItem(recipe.getResultItem());
		}
	}


}