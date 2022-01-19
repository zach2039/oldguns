package com.zach2039.oldguns.data.crafting.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.zach2039.oldguns.crafting.recipe.ShapelessVanillaMuzzleloaderReloadRecipe;
import com.zach2039.oldguns.init.ModCrafting;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.item.crafting.ShapelessRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class ShapelessFirearmMuzzleloaderReloadRecipeBuilder extends EnhancedShapelessRecipeBuilder<ShapelessVanillaMuzzleloaderReloadRecipe, ShapelessFirearmMuzzleloaderReloadRecipeBuilder> {
	static int MAX_WIDTH = 3;
	static int MAX_HEIGHT = 3;
	   
	protected ShapelessFirearmMuzzleloaderReloadRecipeBuilder(final ItemStack result) {
		super(result, ModCrafting.Recipes.FIREARM_MUZZLELOADER_RELOAD_SHAPELESS.get());
	}

	public static ShapelessFirearmMuzzleloaderReloadRecipeBuilder shapeless(final IItemProvider result) {
		return shapeless(new ItemStack(result));
	}

	public static ShapelessFirearmMuzzleloaderReloadRecipeBuilder shapeless(final ItemStack result) {
		return new ShapelessFirearmMuzzleloaderReloadRecipeBuilder(result);
	}
	
	public static class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ShapelessVanillaMuzzleloaderReloadRecipe> {

	      public ShapelessVanillaMuzzleloaderReloadRecipe fromJson(ResourceLocation p_199425_1_, JsonObject p_199425_2_) {
	         String s = JSONUtils.getAsString(p_199425_2_, "group", "");
	         NonNullList<Ingredient> nonnulllist = itemsFromJson(JSONUtils.getAsJsonArray(p_199425_2_, "ingredients"));
	         if (nonnulllist.isEmpty()) {
	            throw new JsonParseException("No ingredients for shapeless recipe");
	         } else if (nonnulllist.size() > MAX_WIDTH * MAX_HEIGHT) {
	            throw new JsonParseException("Too many ingredients for shapeless recipe the max is " + (MAX_WIDTH * MAX_HEIGHT));
	         } else {
	            ItemStack itemstack = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(p_199425_2_, "result"));
	            return new ShapelessVanillaMuzzleloaderReloadRecipe(p_199425_1_, s, itemstack, nonnulllist);
	         }
	      }

	      private static NonNullList<Ingredient> itemsFromJson(JsonArray p_199568_0_) {
	         NonNullList<Ingredient> nonnulllist = NonNullList.create();

	         for(int i = 0; i < p_199568_0_.size(); ++i) {
	            Ingredient ingredient = Ingredient.fromJson(p_199568_0_.get(i));
	            if (!ingredient.isEmpty()) {
	               nonnulllist.add(ingredient);
	            }
	         }

	         return nonnulllist;
	      }

	      public ShapelessVanillaMuzzleloaderReloadRecipe fromNetwork(ResourceLocation p_199426_1_, PacketBuffer p_199426_2_) {
	         String s = p_199426_2_.readUtf(32767);
	         int i = p_199426_2_.readVarInt();
	         NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);

	         for(int j = 0; j < nonnulllist.size(); ++j) {
	            nonnulllist.set(j, Ingredient.fromNetwork(p_199426_2_));
	         }

	         ItemStack itemstack = p_199426_2_.readItem();
	         return new ShapelessVanillaMuzzleloaderReloadRecipe(p_199426_1_, s, itemstack, nonnulllist);
	      }

	      public void toNetwork(PacketBuffer p_199427_1_, ShapelessVanillaMuzzleloaderReloadRecipe p_199427_2_) {
	         p_199427_1_.writeUtf(p_199427_2_.getGroup());
	         p_199427_1_.writeVarInt(p_199427_2_.getIngredients().size());

	         for(Ingredient ingredient : p_199427_2_.getIngredients()) {
	            ingredient.toNetwork(p_199427_1_);
	         }

	         p_199427_1_.writeItem(p_199427_2_.getResultItem());
	      }
	   }
}
