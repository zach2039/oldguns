package zach2039.oldguns.compat.patchouli.processor;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import vazkii.patchouli.api.IVariableProvider;
import vazkii.patchouli.common.util.ItemStackUtil;
import zach2039.oldguns.common.item.crafting.ShapedGunsmithsBenchRecipe;
import zach2039.oldguns.common.item.crafting.base.GunsmithsBenchRecipe;

import vazkii.patchouli.api.IComponentProcessor;

import java.util.ArrayList;
import java.util.List;

public class GunsmithsBenchRecipeProcessor implements IComponentProcessor {
	
	private GunsmithsBenchRecipe recipe;
	private GunsmithsBenchRecipe recipe2;
	private boolean shapeless = true;
	private boolean shapeless2 = true;
	private int longestIngredientSize = 0;
	private boolean hasCustomHeading;

	@Override
	public void setup(IVariableProvider<String> variables) {

		if (variables.has("recipe")) {
			String recipeId = variables.get("recipe");
			
			recipe = (GunsmithsBenchRecipe) CraftingManager.getRecipe(new ResourceLocation(recipeId));
			if (shapeless) {
				shapeless = !(recipe instanceof ShapedGunsmithsBenchRecipe);
			}
		}
		
		if (variables.has("recipe2")) {
			String recipeId2 = variables.get("recipe2");
			recipe2 = (GunsmithsBenchRecipe) CraftingManager.getRecipe(new ResourceLocation(recipeId2));
			if (shapeless2) {
				shapeless2 = !(recipe2 instanceof ShapedGunsmithsBenchRecipe);
			}
		}
		
		this.hasCustomHeading = variables.has("heading");
	}

	@Override
	public String process(String key) {
		if (recipe == null) {
			return null;
		}
		if (key.equals("heading")) {
			if (!hasCustomHeading) {
				//return IVariable.from(recipe.getResultItem().getHoverName());
				return recipe.recipeOutput.getDisplayName();
			}
			return null;
		}
		if (key.equals("heading2") && this.recipe2 != null) {
			if (!hasCustomHeading) {
				//return IVariable.from(recipe2.getResultItem().getHoverName());
				return recipe2.recipeOutput.getDisplayName();
			}
			return null;
		}
		if (key.startsWith("input")) {
			int index = Integer.parseInt(key.substring(5)) - 1;
			int shapedX = index % 3;
			int shapedY = index / 3;
			List<Ingredient> ingredients = new ArrayList();
			
			if (index <= 8) {
				if (recipe instanceof ShapedGunsmithsBenchRecipe) {
					ShapedGunsmithsBenchRecipe shaped = (ShapedGunsmithsBenchRecipe) recipe;
					if (shaped.getWidth() < shapedX + 1) {
						ingredients.add(Ingredient.EMPTY);
					} else {
						int realIndex = index - (shapedY * (3 - shaped.getWidth()));
						NonNullList<Ingredient> list = recipe.getIngredients();
						ingredients.add(list.size() > realIndex ? list.get(realIndex) : Ingredient.EMPTY);
					}
	
				} else {
					NonNullList<Ingredient> list = recipe.getIngredients();
					ingredients.add(list.size() > index ? list.get(index) : Ingredient.EMPTY);
				}
			}
			
			if (recipe2 != null && index > 8) {
				if (recipe2 instanceof ShapedGunsmithsBenchRecipe) {
					ShapedGunsmithsBenchRecipe shaped2 = (ShapedGunsmithsBenchRecipe) recipe2;
					if (shaped2.getWidth() < shapedX + 1) {
						ingredients.add(Ingredient.EMPTY);
					} else {
						int realIndex = (index - 9) - (shapedY * (3 - shaped2.getWidth()));
						NonNullList<Ingredient> list = recipe2.getIngredients();
						ingredients.add(list.size() > realIndex ? list.get(realIndex) : Ingredient.EMPTY);
					}
	
				} else {
					NonNullList<Ingredient> list = recipe2.getIngredients();
					ingredients.add(list.size() > (index - 9) ? list.get((index - 9)) : Ingredient.EMPTY);
				}
			}

			String output = "";
			for (Ingredient ing : ingredients) {
				if (ing.getMatchingStacks().length > 0) {
					ItemStack firstMatch = ing.getMatchingStacks()[0];
					output = output + ItemStackUtil.serializeStack(firstMatch);
				}
			}

			//return PatchouliUtils.interweaveIngredients(ingredients, longestIngredientSize);
			return output;
		}
		if (key.equals("output")) {
			//return IVariable.wrapList(Arrays.stream(List.of(recipe.getResultItem(), recipe2.getResultItem()).toArray()).map(IVariable::from).collect(Collectors.toList()));
			//return IVariable.from(recipe.getResultItem());
			return ItemStackUtil.serializeStack(recipe.recipeOutput);
		}
		if (key.equals("output2") && this.recipe2 != null) {
			//return IVariable.wrapList(Arrays.stream(List.of(recipe.getResultItem(), recipe2.getResultItem()).toArray()).map(IVariable::from).collect(Collectors.toList()));
			//return IVariable.from(recipe2.getResultItem());
			return ItemStackUtil.serializeStack(recipe2.recipeOutput);
		}
		if (key.equals("shapeless")) {
			//return IVariable.wrap(shapeless);
			return (shapeless) ? "true" : "false";
		}
		if (key.equals("shapeless2") && this.recipe2 != null) {
			//return IVariable.wrap(shapeless2);
			return (shapeless2) ? "true" : "false";
		}
		return null;
	}
	
}
