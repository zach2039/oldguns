package com.zach2039.oldguns.crafting.recipe;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Nonnull;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.zach2039.oldguns.api.crafting.IDesignNotes;
import com.zach2039.oldguns.api.firearm.IFirearm;
import com.zach2039.oldguns.crafting.util.ModRecipeUtil;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.init.ModRecipeTypes;
import com.zach2039.oldguns.inventory.GunsmithsBenchCraftingContainer;
import com.zach2039.oldguns.inventory.menu.GunsmithsBenchMenu;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ShapedGunsmithsBenchRecipe implements IRecipe<GunsmithsBenchCraftingContainer>, GunsmithsBenchRecipe {
	
	static int MAX_WIDTH = 3;
	static int MAX_HEIGHT = 3;

	public static void setCraftingSize(int width, int height) {
		if (MAX_WIDTH < width) MAX_WIDTH = width;
		if (MAX_HEIGHT < height) MAX_HEIGHT = height;
	}

	final int width;
	final int height;
	final NonNullList<Ingredient> recipeItems;
	final ItemStack result;
	private final ResourceLocation id;
	final String group;

	protected ShapedGunsmithsBenchRecipe(final ResourceLocation id, final String group, final int recipeWidth, final int recipeHeight, final NonNullList<Ingredient> recipeItems, final ItemStack recipeOutput) {
		this.id = id;
		this.group = group;
		this.width = recipeWidth;
		this.height = recipeHeight;
		this.result = recipeOutput;
		this.recipeItems = recipeItems;
	}

	public ResourceLocation getId() {
		return this.id;
	}

	public String getGroup() {
		return this.group;
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

	public NonNullList<Ingredient> getIngredients() {
		return this.recipeItems;
	}

	public boolean canCraftInDimensions(int p_44161_, int p_44162_) {
		return p_44161_ >= this.width && p_44162_ >= this.height;
	}

	@Override
	public boolean matches(GunsmithsBenchCraftingContainer p_44176_, World p_44177_) {
		for(int i = 0; i <= p_44176_.getWidth() - this.width; ++i) {
			for(int j = 0; j <= p_44176_.getHeight() - this.height; ++j) {
				if (this.matches(p_44176_, i, j, true)) {
					return true;
				}

				if (this.matches(p_44176_, i, j, false)) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean matches(GunsmithsBenchCraftingContainer containerCrafting, int width, int height, boolean p_44174_) {
		for(int i = 0; i < containerCrafting.getWidth(); ++i) {
			for(int j = 0; j < containerCrafting.getHeight(); ++j) {
				int k = i - width;
				int l = j - height;
				Ingredient ingredient = Ingredient.EMPTY;
				if (k >= 0 && l >= 0 && k < this.width && l < this.height) {
					if (p_44174_) {
						ingredient = this.recipeItems.get(this.width - k - 1 + l * this.width);
					} else {
						ingredient = this.recipeItems.get(k + l * this.width);
					}
				}

				if (!ingredient.test(containerCrafting.getItem(i + j * containerCrafting.getWidth()))) {
					return false;
				}
			}
		}

		return true;
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

	public int getWidth() {
		return this.width;
	}

	public int getRecipeWidth() {
		return getWidth();
	}

	public int getHeight() {
		return this.height;
	}

	public int getRecipeHeight() {
		return getHeight();
	}

	static NonNullList<Ingredient> dissolvePattern(String[] p_44203_, Map<String, Ingredient> p_44204_, int p_44205_, int p_44206_) {
		NonNullList<Ingredient> nonnulllist = NonNullList.withSize(p_44205_ * p_44206_, Ingredient.EMPTY);
		Set<String> set = Sets.newHashSet(p_44204_.keySet());
		set.remove(" ");

		for(int i = 0; i < p_44203_.length; ++i) {
			for(int j = 0; j < p_44203_[i].length(); ++j) {
				String s = p_44203_[i].substring(j, j + 1);
				Ingredient ingredient = p_44204_.get(s);
				if (ingredient == null) {
					throw new JsonSyntaxException("Pattern references symbol '" + s + "' but it's not defined in the key");
				}

				set.remove(s);
				nonnulllist.set(j + p_44205_ * i, ingredient);
			}
		}

		if (!set.isEmpty()) {
			throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
		} else {
			return nonnulllist;
		}
	}

	@VisibleForTesting
	static String[] shrink(String... p_44187_) {
		int i = Integer.MAX_VALUE;
		int j = 0;
		int k = 0;
		int l = 0;

		for(int i1 = 0; i1 < p_44187_.length; ++i1) {
			String s = p_44187_[i1];
			i = Math.min(i, firstNonSpace(s));
			int j1 = lastNonSpace(s);
			j = Math.max(j, j1);
			if (j1 < 0) {
				if (k == i1) {
					++k;
				}

				++l;
			} else {
				l = 0;
			}
		}

		if (p_44187_.length == l) {
			return new String[0];
		} else {
			String[] astring = new String[p_44187_.length - l - k];

			for(int k1 = 0; k1 < astring.length; ++k1) {
				astring[k1] = p_44187_[k1 + k].substring(i, j + 1);
			}

			return astring;
		}
	}

	public boolean isIncomplete() {
		NonNullList<Ingredient> nonnulllist = this.getIngredients();
		return nonnulllist.isEmpty() || nonnulllist.stream().filter((p_151277_) -> {
			return !p_151277_.isEmpty();
		}).anyMatch((p_151273_) -> {
			return p_151273_.getItems().length == 0;
		});
	}

	private static int firstNonSpace(String p_44185_) {
		int i;
		for(i = 0; i < p_44185_.length() && p_44185_.charAt(i) == ' '; ++i) {
		}

		return i;
	}

	private static int lastNonSpace(String p_44201_) {
		int i;
		for(i = p_44201_.length() - 1; i >= 0 && p_44201_.charAt(i) == ' '; --i) {
		}

		return i;
	}

	static String[] patternFromJson(JsonArray p_44197_) {
		String[] astring = new String[p_44197_.size()];
		if (astring.length > MAX_HEIGHT) {
			throw new JsonSyntaxException("Invalid pattern: too many rows, " + MAX_HEIGHT + " is maximum");
		} else if (astring.length == 0) {
			throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
		} else {
			for(int i = 0; i < astring.length; ++i) {
				String s = GsonBuilder.convertToString(p_44197_.get(i), "pattern[" + i + "]");
				if (s.length() > MAX_WIDTH) {
					throw new JsonSyntaxException("Invalid pattern: too many columns, " + MAX_WIDTH + " is maximum");
				}

				if (i > 0 && astring[0].length() != s.length()) {
					throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
				}

				astring[i] = s;
			}

			return astring;
		}
	}

	static Map<String, Ingredient> keyFromJson(JsonObject p_44211_) {
		Map<String, Ingredient> map = Maps.newHashMap();

		for(Entry<String, JsonElement> entry : p_44211_.entrySet()) {
			if (entry.getKey().length() != 1) {
				throw new JsonSyntaxException("Invalid key entry: '" + (String)entry.getKey() + "' is an invalid symbol (must be 1 character only).");
			}

			if (" ".equals(entry.getKey())) {
				throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
			}

			map.put(entry.getKey(), Ingredient.fromJson(entry.getValue()));
		}

		map.put(" ", Ingredient.EMPTY);
		return map;
	}

	public static ItemStack itemStackFromJson(JsonObject p_151275_) {
		return net.minecraftforge.common.crafting.CraftingHelper.getItemStack(p_151275_, true, true);
	}

	public static Item itemFromJson(JsonObject p_151279_) {
		String s = GsonHelper.getAsString(p_151279_, "item");
		Item item = Registry.ITEM.getOptional(new ResourceLocation(s)).orElseThrow(() -> {
			return new JsonSyntaxException("Unknown item '" + s + "'");
		});
		if (item == Items.AIR) {
			throw new JsonSyntaxException("Invalid item: " + s);
		} else {
			return item;
		}
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModCrafting.Recipes.GUNSMITHS_BENCH_SHAPED.get();
	}

	public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<ShapedGunsmithsBenchRecipe> {
		
		@Override
		public ShapedGunsmithsBenchRecipe fromJson(final ResourceLocation recipeID, final JsonObject json) {
			final String group = GsonHelper.getAsString(json, "group", "");
			final ModRecipeUtil.ShapedPrimer primer = ModRecipeUtil.parseShaped(json);
			final ItemStack result = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);

			ShapedGunsmithsBenchRecipe recipeFromJson = new ShapedGunsmithsBenchRecipe(recipeID, group, 
					primer.recipeWidth(), primer.recipeHeight(), primer.ingredients(), result);
			
			return recipeFromJson;
		}

		@Override
		public ShapedGunsmithsBenchRecipe fromNetwork(final ResourceLocation recipeID, final FriendlyByteBuf buffer) {
			final int width = buffer.readVarInt();
			final int height = buffer.readVarInt();
			final String group = buffer.readUtf(Short.MAX_VALUE);
			final NonNullList<Ingredient> ingredients = NonNullList.withSize(width * height, Ingredient.EMPTY);

			for (int i = 0; i < ingredients.size(); ++i) {
				ingredients.set(i, Ingredient.fromNetwork(buffer));
			}

			final ItemStack result = buffer.readItem();

			return new ShapedGunsmithsBenchRecipe(recipeID, group, width, height, ingredients, result);
		}

		@Override
		public void toNetwork(final FriendlyByteBuf buffer, final ShapedGunsmithsBenchRecipe recipe) {
			buffer.writeVarInt(recipe.getRecipeWidth());
			buffer.writeVarInt(recipe.getRecipeHeight());
			buffer.writeUtf(recipe.getGroup());

			for (final Ingredient ingredient : recipe.getIngredients()) {
				ingredient.toNetwork(buffer);
			}

			buffer.writeItem(recipe.getResultItem());
		}
	}

	@Override
	public RecipeType<?> getType() {
		return ModRecipeTypes.GUNSMITHS_BENCH;
	}
}
