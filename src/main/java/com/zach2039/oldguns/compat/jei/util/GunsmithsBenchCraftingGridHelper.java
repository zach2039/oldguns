package com.zach2039.oldguns.compat.jei.util;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.recipe.RecipeIngredientRole;

import java.util.ArrayList;
import java.util.List;

/**
 * Copy of JEIs CraftingGridHelper with edits
 * @author mezz
 *
 */
public class GunsmithsBenchCraftingGridHelper implements ICraftingGridHelper {
//	@Override
//	public <T> void setInputs(IRecipeLayoutBuilder builder, IIngredientType<T> ingredientType, List<List<T>> inputs, int width, int height) {
//		if (width <= 0 || height <= 0) {
//			builder.setShapeless();
//		}
//
//		List<IRecipeSlotBuilder> inputSlots = new ArrayList<>();
//		for (int y = 0; y < 3; ++y) {
//			for (int x = 0; x < 3; ++x) {
//				IRecipeSlotBuilder slot = builder.addSlot(RecipeIngredientRole.INPUT, x * 18 + 25, y * 18 + 4);
//				inputSlots.add(slot);
//			}
//		}
//
//		setInputs(inputSlots, ingredientType, inputs, width, height);
//	}

	@Override
	public <T> void setInputs(List<IRecipeSlotBuilder> slotBuilders, IIngredientType<T> ingredientType, List<List<T>> inputs, int width, int height) {
		if (width <= 0 || height <= 0) {
			width = height = getShapelessSize(inputs.size());
		}
		if (slotBuilders.size() < width * height) {
			throw new IllegalArgumentException(String.format("There are not enough slots (%s) to hold a recipe of this size. (%sx%s)", slotBuilders.size(), width, height));
		}

		for (int i = 0; i < inputs.size(); i++) {
			int index = getCraftingIndex(i, width, height);
			IRecipeSlotBuilder slot = slotBuilders.get(index);

			List<T> ingredients = inputs.get(i);
			if (ingredients != null) {
				slot.addIngredients(ingredientType, ingredients);
			}
		}
	}

//	@Override
//	public <T> void setOutputs(IRecipeLayoutBuilder builder, IIngredientType<T> ingredientType, List<T> outputs) {
//		IRecipeSlotBuilder outputSlot = builder.addSlot(RecipeIngredientRole.OUTPUT, 119, 22);
//		if (outputs != null) {
//			outputSlot.addIngredients(ingredientType, outputs);
//		}
//	}

	private static int getShapelessSize(int total) {
		if (total > 4) {
			return 3;
		} else if (total > 1) {
			return 2;
		} else {
			return 1;
		}
	}

	private static int getCraftingIndex(int i, int width, int height) {
		int index;
		if (width == 1) {
			if (height == 3) {
				index = (i * 3) + 1;
			} else if (height == 2) {
				index = (i * 3) + 1;
			} else {
				index = 4;
			}
		} else if (height == 1) {
			index = i + 3;
		} else if (width == 2) {
			index = i;
			if (i > 1) {
				index++;
				if (i > 3) {
					index++;
				}
			}
		} else if (height == 2) {
			index = i + 3;
		} else {
			index = i;
		}
		return index;
	}

	@Override
	public <T> List<IRecipeSlotBuilder> createAndSetInputs(IRecipeLayoutBuilder builder,
			IIngredientType<T> ingredientType,
			List<@org.jetbrains.annotations.Nullable List<@org.jetbrains.annotations.Nullable T>> inputs, int width,
			int height) {
		if (width <= 0 || height <= 0) {
			builder.setShapeless();
		}

		List<IRecipeSlotBuilder> inputSlots = new ArrayList<>();
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 3; ++x) {
				IRecipeSlotBuilder slot = builder.addSlot(RecipeIngredientRole.INPUT, x * 18 + 25, y * 18 + 4);
				inputSlots.add(slot);
			}
		}

		setInputs(inputSlots, ingredientType, inputs, width, height);
		return inputSlots;
	}

	@Override
	public <T> IRecipeSlotBuilder createAndSetOutputs(IRecipeLayoutBuilder builder, IIngredientType<T> ingredientType,
			@org.jetbrains.annotations.Nullable List<@org.jetbrains.annotations.Nullable T> outputs) {

		IRecipeSlotBuilder outputSlot = builder.addSlot(RecipeIngredientRole.OUTPUT, 119, 22);
		if (outputs != null) {
			outputSlot.addIngredients(ingredientType, outputs);
		}
		return outputSlot;
	}

}