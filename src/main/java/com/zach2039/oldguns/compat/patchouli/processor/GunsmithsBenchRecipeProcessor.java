package com.zach2039.oldguns.compat.patchouli.processor;

import com.zach2039.oldguns.compat.patchouli.PatchouliUtils;
import com.zach2039.oldguns.world.item.crafting.GunsmithsBenchRecipe;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapedGunsmithsBenchRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

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
	public void setup(Level level, IVariableProvider variables) {
		RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();

		if (variables.has("recipe")) {
			String recipeId = variables.get("recipe").asString();
			
			recipe = (GunsmithsBenchRecipe) manager.byKey(new ResourceLocation(recipeId)).orElseThrow(IllegalArgumentException::new);
			if (shapeless) {
				shapeless = !(recipe instanceof ShapedGunsmithsBenchRecipe);
			}
		}
		
		if (variables.has("recipe2")) {
			String recipeId2 = variables.get("recipe2").asString();
			recipe2 = (GunsmithsBenchRecipe) manager.byKey(new ResourceLocation(recipeId2)).orElseThrow(IllegalArgumentException::new);
			if (shapeless2) {
				shapeless2 = !(recipe2 instanceof ShapedGunsmithsBenchRecipe);
			}
		}
		
		this.hasCustomHeading = variables.has("heading");
	}

	@Override
	public IVariable process(Level level, String key) {
		if (recipe == null) {
			return null;
		}
		if (key.equals("heading")) {
			if (!hasCustomHeading) {
				return IVariable.from(recipe.getResultItem(RegistryAccess.EMPTY).getHoverName());
			}
			return null;
		}
		if (key.equals("heading2") && this.recipe2 != null) {
			if (!hasCustomHeading) {
				return IVariable.from(recipe2.getResultItem(RegistryAccess.EMPTY).getHoverName());
			}
			return null;
		}
		if (key.startsWith("input")) {
			int index = Integer.parseInt(key.substring(5)) - 1;
			int shapedX = index % 3;
			int shapedY = index / 3;
			List<Ingredient> ingredients = new ArrayList<>();
			
			if (index <= 8) {
				if (recipe instanceof ShapedGunsmithsBenchRecipe shaped) {
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
				if (recipe2 instanceof ShapedGunsmithsBenchRecipe shaped) {
					if (shaped.getWidth() < shapedX + 1) {
						ingredients.add(Ingredient.EMPTY);
					} else {
						int realIndex = (index - 9) - (shapedY * (3 - shaped.getWidth()));
						NonNullList<Ingredient> list = recipe2.getIngredients();
						ingredients.add(list.size() > realIndex ? list.get(realIndex) : Ingredient.EMPTY);
					}
	
				} else {
					NonNullList<Ingredient> list = recipe2.getIngredients();
					ingredients.add(list.size() > (index - 9) ? list.get((index - 9)) : Ingredient.EMPTY);
				}
			}
			
			return PatchouliUtils.interweaveIngredients(ingredients, longestIngredientSize);
		}
		if (key.equals("output")) {
			//return IVariable.wrapList(Arrays.stream(List.of(recipe.getResultItem(), recipe2.getResultItem()).toArray()).map(IVariable::from).collect(Collectors.toList()));
			return IVariable.from(recipe.getResultItem(RegistryAccess.EMPTY));
		}
		if (key.equals("output2") && this.recipe2 != null) {
			//return IVariable.wrapList(Arrays.stream(List.of(recipe.getResultItem(), recipe2.getResultItem()).toArray()).map(IVariable::from).collect(Collectors.toList()));
			return IVariable.from(recipe2.getResultItem(RegistryAccess.EMPTY));
		}
		if (key.equals("shapeless")) {
			return IVariable.wrap(shapeless);
		}
		if (key.equals("shapeless2") && this.recipe2 != null) {
			return IVariable.wrap(shapeless2);
		}
		return null;
	}
	
}
