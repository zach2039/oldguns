package com.zach2039.oldguns.compat.patchouli.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.compat.patchouli.PatchouliUtils;
import com.zach2039.oldguns.init.ModRecipeTypes;
import com.zach2039.oldguns.world.item.crafting.GunsmithsBenchRecipe;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapedGunsmithsBenchRecipe;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;


public class MultiGunsmithsBenchRecipeProcessor implements IComponentProcessor {
	
	private List<GunsmithsBenchRecipe> recipes;
	private List<GunsmithsBenchRecipe> recipes2;
	private boolean shapeless = true;
	private boolean shapeless2 = true;
	private int longestIngredientSize = 0;
	private boolean hasCustomHeading;

	@Override
	public void setup(IVariableProvider variables) {

		List<String> names = variables.get("recipes").asStream().map(IVariable::asString).collect(Collectors.toList());
		this.recipes = new ArrayList<>();
		for (String name : names) {
			GunsmithsBenchRecipe recipe = PatchouliUtils.getRecipe(ModRecipeTypes.GUNSMITHS_BENCH, new ResourceLocation(name));
			if (recipe != null) {
				recipes.add(recipe);
				if (shapeless) {
					shapeless = !(recipe instanceof ShapedGunsmithsBenchRecipe);
				}
				for (Ingredient ingredient : recipe.getIngredients()) {
					int size = ingredient.getItems().length;
					if (longestIngredientSize < size) {
						longestIngredientSize = size;
					}
				}
			} else {
				OldGuns.LOGGER.warn("Missing crafting recipe " + name);
			}
		}
		
		if (variables.has("recipes2")) {
			List<String> names2 = variables.get("recipes2").asStream().map(IVariable::asString).collect(Collectors.toList());
			this.recipes2 = new ArrayList<>();
			for (String name : names2) {
				GunsmithsBenchRecipe recipe = PatchouliUtils.getRecipe(ModRecipeTypes.GUNSMITHS_BENCH, new ResourceLocation(name));
				if (recipe != null) {
					recipes2.add(recipe);
					if (shapeless2) {
						shapeless2 = !(recipe instanceof ShapedGunsmithsBenchRecipe);
					}
					for (Ingredient ingredient : recipe.getIngredients()) {
						int size = ingredient.getItems().length;
						if (longestIngredientSize < size) {
							longestIngredientSize = size;
						}
					}
				} else {
					OldGuns.LOGGER.warn("Missing crafting recipe " + name);
				}
			}
		}
		
		this.hasCustomHeading = variables.has("heading") || variables.has("heading2");
	}

	@Override
	public IVariable process(String key) {
		if (recipes.isEmpty()) {
			return null;
		}
		if (key.equals("heading")) {
			if (!hasCustomHeading) {
				return IVariable.from(recipes.get(0).getResultItem().getHoverName());
			}
			return null;
		}
		if (key.equals("heading2") && this.recipes2 != null) {
			if (!hasCustomHeading) {
				return IVariable.from(recipes2.get(0).getResultItem().getHoverName());
			}
			return null;
		}
		if (key.startsWith("input")) {
			int index = Integer.parseInt(key.substring(5)) - 1;
			int shapedX = index % 3;
			int shapedY = index / 3;
			List<Ingredient> ingredients = new ArrayList<>();
			
			if (index <= 8) {
				for (GunsmithsBenchRecipe recipe : recipes) {
					if (recipes instanceof ShapedGunsmithsBenchRecipe shaped) {
						if (shaped.getWidth() < shapedX + 1) {
							ingredients.add(Ingredient.EMPTY);
						} else if (shaped.getHeight() < shapedY + 1) {
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
			}
			
			if (recipes2 != null && index > 8) {
				for (GunsmithsBenchRecipe recipe2 : recipes2) {
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
			}
			
			return PatchouliUtils.interweaveIngredients(ingredients, longestIngredientSize);
		}
		if (key.equals("output")) {
			//return IVariable.wrapList(Arrays.stream(List.of(recipe.getResultItem(), recipe2.getResultItem()).toArray()).map(IVariable::from).collect(Collectors.toList()));
			return IVariable.wrapList(recipes.stream().map(GunsmithsBenchRecipe::getResultItem).map(IVariable::from).collect(Collectors.toList()));
		}
		if (key.equals("output2") && this.recipes2 != null) {
			//return IVariable.wrapList(Arrays.stream(List.of(recipe.getResultItem(), recipe2.getResultItem()).toArray()).map(IVariable::from).collect(Collectors.toList()));
			return IVariable.wrapList(recipes2.stream().map(GunsmithsBenchRecipe::getResultItem).map(IVariable::from).collect(Collectors.toList()));
		}
		if (key.equals("shapeless")) {
			return IVariable.wrap(shapeless);
		}
		if (key.equals("shapeless2") && this.recipes2 != null) {
			return IVariable.wrap(shapeless2);
		}
		return null;
	}
	
}
