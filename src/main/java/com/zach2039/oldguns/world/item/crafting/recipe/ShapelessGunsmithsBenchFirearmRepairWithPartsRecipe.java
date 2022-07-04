package com.zach2039.oldguns.world.item.crafting.recipe;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.api.firearm.Firearm;
import com.zach2039.oldguns.api.firearm.util.FirearmNBTHelper;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.inventory.GunsmithsBenchCraftingContainer;
import com.zach2039.oldguns.world.item.crafting.GunsmithsBenchRecipe;
import com.zach2039.oldguns.world.item.crafting.util.ModRecipeUtil;
import com.zach2039.oldguns.world.item.material.RepairPartItem;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;

public class ShapelessGunsmithsBenchFirearmRepairWithPartsRecipe extends ShapelessGunsmithsBenchRecipe implements GunsmithsBenchRecipe {

	public ShapelessGunsmithsBenchFirearmRepairWithPartsRecipe(ResourceLocation id, String group, ItemStack result, NonNullList<Ingredient> ingredients) {
		super(id, group, result, ingredients);
	}

	@Override
	public ItemStack assemble(final GunsmithsBenchCraftingContainer inv) {
		ItemStack firearmStack = ItemStack.EMPTY;
		List<ItemStack> repairPartStacks = new ArrayList<ItemStack>();
		
		for (int i = 0; i < inv.getContainerSize(); i++) {
			ItemStack stack = inv.getItem(i);
			
			if (stack.getItem() instanceof Firearm) {				
				if (stack.isDamaged()) {
					firearmStack = stack.copy();
				}
			}
			
			if (stack.getItem() instanceof RepairPartItem) {
				repairPartStacks.add(stack.copy());
			}
		}
		
		// Repair firearm by 10% for every part
		if (!firearmStack.isEmpty() && !repairPartStacks.isEmpty()) {
			int firearmDamage = firearmStack.getDamageValue();
			int repairAmount = Math.round((float)firearmStack.getMaxDamage() * 0.1F);
			firearmStack.setDamageValue(Math.max(0, firearmDamage - (repairAmount * repairPartStacks.size())));
			
			FirearmNBTHelper.refreshFirearmCondition(firearmStack);
			
			((Firearm)firearmStack.getItem()).initNBTTags(firearmStack);
			
			return firearmStack;
		}
		
		return ItemStack.EMPTY;
	}
	
	@Override
	public boolean matches(GunsmithsBenchCraftingContainer craftinv, Level level) {
		StackedContents stackedcontents = new StackedContents();
		java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
		boolean partFound = false;
		int i = 0;

		for(int j = 0; j < craftinv.getContainerSize(); ++j) {
			ItemStack itemstack = craftinv.getItem(j);
			if (!itemstack.isEmpty()) {
				
				if (isSimple) {
					++i;
					stackedcontents.accountStack(itemstack, 1);
				} else {
					// Only add a single repair itemstack to inputs, so we match even with multiple parts used
					if (itemstack.getItem() instanceof RepairPartItem) {
						if (!partFound) {
							++i;
							inputs.add(itemstack);
							partFound = true;
						}
					} else {
						++i;
						inputs.add(itemstack);
					}
				}
			}
		}

		return i == this.ingredients.size() && (isSimple ? stackedcontents.canCraft(this, (IntList)null) : net.minecraftforge.common.util.RecipeMatcher.findMatches(inputs,  this.ingredients) != null);
	}
	
	public static class Serializer implements RecipeSerializer<ShapelessGunsmithsBenchFirearmRepairWithPartsRecipe> {
		
		@Override
		public ShapelessGunsmithsBenchFirearmRepairWithPartsRecipe fromJson(final ResourceLocation recipeID, final JsonObject json) {
			final String group = GsonHelper.getAsString(json, "group", "");
			final NonNullList<Ingredient> ingredients = ModRecipeUtil.parseShapeless(json);
			final ItemStack result = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);

			ShapelessGunsmithsBenchFirearmRepairWithPartsRecipe recipeFromJson = new ShapelessGunsmithsBenchFirearmRepairWithPartsRecipe(recipeID, group, result, ingredients);
			
			return recipeFromJson;
		}

		@Override
		public ShapelessGunsmithsBenchFirearmRepairWithPartsRecipe fromNetwork(final ResourceLocation recipeID, final FriendlyByteBuf buffer) {
			final String group = buffer.readUtf(Short.MAX_VALUE);
			final int numIngredients = buffer.readVarInt();
			final NonNullList<Ingredient> ingredients = NonNullList.withSize(numIngredients, Ingredient.EMPTY);

			for (int j = 0; j < ingredients.size(); ++j) {
				ingredients.set(j, Ingredient.fromNetwork(buffer));
			}

			final ItemStack result = buffer.readItem();

			return new ShapelessGunsmithsBenchFirearmRepairWithPartsRecipe(recipeID, group, result, ingredients);
		}

		@Override
		public void toNetwork(final FriendlyByteBuf buffer, final ShapelessGunsmithsBenchFirearmRepairWithPartsRecipe recipe) {
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
		return ModCrafting.Recipes.GUNSMITHS_BENCH_FIREARM_REPAIR_WITH_PARTS_SHAPELESS.get();
	}
}
