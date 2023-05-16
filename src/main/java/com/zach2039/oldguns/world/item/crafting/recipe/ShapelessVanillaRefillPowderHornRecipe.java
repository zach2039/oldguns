package com.zach2039.oldguns.world.item.crafting.recipe;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.firearm.util.PowderHornNBTHelper;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.init.ModTags;
import com.zach2039.oldguns.world.inventory.GunsmithsBenchCraftingContainer;
import com.zach2039.oldguns.world.item.crafting.util.ModRecipeUtil;
import com.zach2039.oldguns.world.item.tools.HacksawItem;
import com.zach2039.oldguns.world.item.tools.PowderHornItem;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.CraftingHelper;

public class ShapelessVanillaRefillPowderHornRecipe extends ShapelessRecipe {

	private final ItemStack result;
	private final boolean isSimple;
	
	public ShapelessVanillaRefillPowderHornRecipe(ResourceLocation id, String group, ItemStack result, NonNullList<Ingredient> ingredients) {
		super(id, group, CraftingBookCategory.MISC, result, ingredients);
		this.result = result;
		this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
	}

	@Override
	public ItemStack getResultItem(RegistryAccess registryAccess) {
		// Recipe is dynamic, so return empty itemstack
		return ItemStack.EMPTY;
	}
	
	@Override
	public boolean isSpecial() {
		// Recipe is dynamic, so return true
		return true;
	}
	
	@Override
	public NonNullList<ItemStack> getRemainingItems(final CraftingContainer inv) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);

		// Dont allow remaining items, since we want to refil the powder horn and not duplicate it
		
		return remainingItems;
	}
	
	@Override
	public ItemStack assemble(final CraftingContainer inv, RegistryAccess registryAccess) {
		ItemStack powderHornStack = ItemStack.EMPTY;
		List<ItemStack> powderStacks = new ArrayList<ItemStack>();
		
		for (int i = 0; i < inv.getContainerSize(); i++) {
			ItemStack stack = inv.getItem(i);
			
			if (stack.getItem() instanceof PowderHornItem) {				
				powderHornStack = stack.copy();
			}
			
			if (stack.is(ModTags.Items.ANY_GUNPOWDER)) {
				powderStacks.add(stack.copy());
			}
		}

		if (!powderHornStack.isEmpty() && !powderStacks.isEmpty()) {
			ItemStack firstPowderStack = powderStacks.get(0);
			int multiplier = 1;
			int powderAmount = powderStacks.size() * multiplier;
			
			// Dont allow overfill
			if (PowderHornNBTHelper.peekPowderCount(powderHornStack) + powderAmount > PowderHornItem.MAX_CAPACITY)
				return ItemStack.EMPTY;
			
			// Dont allow powder mixing
			if (!powderStacks.stream().allMatch(e -> firstPowderStack.getItem() == e.getItem()))
				return ItemStack.EMPTY;
				
			// Dont allow overwriting stored powder
			final ItemStack powderHornFinal = powderHornStack;
			final ItemStack powderStackFinal = firstPowderStack.copy();
			powderStackFinal.setCount(PowderHornNBTHelper.peekPowderCount(powderHornFinal) + powderAmount);
			if (!PowderHornNBTHelper.peekPowderStack(powderHornFinal).isEmpty()) {
				if (!powderStacks.stream().allMatch(e -> PowderHornNBTHelper.peekPowderStack(powderHornFinal).getItem() == powderStackFinal.getItem()))
					return ItemStack.EMPTY;
			}
			
			PowderHornNBTHelper.setPowderStack(powderHornFinal, powderStackFinal);
			
			return powderHornStack;
		}
		
		return ItemStack.EMPTY;
	}
	
	@Override
	public boolean matches(CraftingContainer craftinv, Level level) {
		StackedContents stackedcontents = new StackedContents();
		java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
		boolean powderFound = false;
		int i = 0;

		for(int j = 0; j < craftinv.getContainerSize(); ++j) {
			ItemStack itemstack = craftinv.getItem(j);
			if (!itemstack.isEmpty()) {
				// Only add a single powder itemstack to inputs, so we match even with multiple parts used
				if (itemstack.is(ModTags.Items.ANY_GUNPOWDER)) {
					if (!powderFound) {
						if (isSimple) {
							++i;
							stackedcontents.accountStack(itemstack, 1);
						} else {
							++i;
							inputs.add(itemstack);
						}
						powderFound = true;
					}
				} else {
					if (isSimple) {
						++i;
						stackedcontents.accountStack(itemstack, 1);
					} else {
						++i;
						inputs.add(itemstack);
					}
				}
			}
		}

		return i == this.getIngredients().size() && (isSimple ? stackedcontents.canCraft(this, (IntList)null) : net.minecraftforge.common.util.RecipeMatcher.findMatches(inputs,  this.getIngredients()) != null);
	}
	
	public static class Serializer implements RecipeSerializer<ShapelessVanillaRefillPowderHornRecipe> {
		
		@Override
		public ShapelessVanillaRefillPowderHornRecipe fromJson(final ResourceLocation recipeID, final JsonObject json) {
			final String group = GsonHelper.getAsString(json, "group", "");
			final NonNullList<Ingredient> ingredients = ModRecipeUtil.parseShapeless(json);
			final ItemStack result = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);

			ShapelessVanillaRefillPowderHornRecipe recipeFromJson = new ShapelessVanillaRefillPowderHornRecipe(recipeID, group, result, ingredients);
			
			return recipeFromJson;
		}

		@Override
		public ShapelessVanillaRefillPowderHornRecipe fromNetwork(final ResourceLocation recipeID, final FriendlyByteBuf buffer) {
			final String group = buffer.readUtf(Short.MAX_VALUE);
			final int numIngredients = buffer.readVarInt();
			final NonNullList<Ingredient> ingredients = NonNullList.withSize(numIngredients, Ingredient.EMPTY);

			for (int j = 0; j < ingredients.size(); ++j) {
				ingredients.set(j, Ingredient.fromNetwork(buffer));
			}

			final ItemStack result = buffer.readItem();

			return new ShapelessVanillaRefillPowderHornRecipe(recipeID, group, result, ingredients);
		}

		@Override
		public void toNetwork(final FriendlyByteBuf buffer, final ShapelessVanillaRefillPowderHornRecipe recipe) {
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
		return ModCrafting.Recipes.POWDER_HORN_REFILL_SHAPELESS.get();
	}
}
