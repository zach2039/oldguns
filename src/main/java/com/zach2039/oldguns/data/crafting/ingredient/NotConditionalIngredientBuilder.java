package com.zach2039.oldguns.data.crafting.ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.zach2039.oldguns.OldGuns;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag.Named;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.ItemExistsCondition;
import net.minecraftforge.common.crafting.conditions.NotCondition;

/**
 * Builds an {@link Ingredient} that can be deserialised by {@link ConditionalIngredientSerializer}.
 *
 * @author Choonster
 */
public class NotConditionalIngredientBuilder {
	private final Ingredient ingredient;
	private final List<ItemExistsCondition> conditions;

	private NotConditionalIngredientBuilder(final Ingredient ingredient) {
		this.ingredient = ingredient;
		conditions = new ArrayList<>();
	}

	/**
	 * Creates a new {@link NotConditionalIngredientBuilder}.
	 *
	 * @param items The Ingredient Items to be used when the conditions are met
	 * @return The builder
	 */
	public static NotConditionalIngredientBuilder conditionalIngredient(final Named<Item> tag) {
		return conditionalIngredient(Ingredient.of(tag));
	}
	
	/**
	 * Creates a new {@link NotConditionalIngredientBuilder}.
	 *
	 * @param items The Ingredient Items to be used when the conditions are met
	 * @return The builder
	 */
	public static NotConditionalIngredientBuilder conditionalIngredient(final ItemLike... items) {
		return conditionalIngredient(Ingredient.of(items));
	}
	
	/**
	 * Creates a new {@link NotConditionalIngredientBuilder}.
	 *
	 * @param ingredient The Ingredient to be used when the conditions are met
	 * @return The builder
	 */
	public static NotConditionalIngredientBuilder conditionalIngredient(final Ingredient ingredient) {
		return new NotConditionalIngredientBuilder(ingredient);
	}

	/**
	 * Adds a condition with extra data.
	 *
	 * @param type The condition type
	 * @param data The data
	 * @return This builder
	 */
	public NotConditionalIngredientBuilder addCondition(ItemExistsCondition condition) {
		conditions.add(condition);
		return this;
	}

	/**
	 * Validates that the ingredient has at least one condition.
	 */
	private void validate() {
		if (conditions.isEmpty()) {
			final String stacks = Arrays.stream(ingredient.getItems())
					.map(ItemStack::toString)
					.collect(Collectors.joining(","));

			throw new IllegalStateException("Conditional ingredient producing [" + stacks + "] has no conditions");
		}
	}

	/**
	 * Builds the final {@link Ingredient}.
	 *
	 * @return The Ingredient
	 */
	public Result build() {
		validate();
		return new Result(ingredient, conditions);
	}

	/**
	 * An {@link Ingredient} that serialises into JSON that can be deserialised by {@link ConditionalIngredientSerializer}.
	 * <p>
	 * Note: This is only intended for use during recipe generation, it won't match any items if used in a recipe during gameplay.
	 */
	public static class Result extends Ingredient {
		private final Ingredient ingredient;
		private final List<ItemExistsCondition> conditions;

		private Result(final Ingredient ingredient, final List<ItemExistsCondition> conditions) {
			super(Stream.empty());
			this.ingredient = ingredient;
			this.conditions = conditions;
		}

		@Override
		public JsonElement toJson() {
			final JsonObject rootObject = new JsonObject();
			rootObject.addProperty("type", new ResourceLocation(OldGuns.MODID, "conditional").toString());

			final JsonArray conditionsArray = new JsonArray();
			conditions.forEach(condition -> conditionsArray.add(ItemExistsCondition.Serializer.INSTANCE.getJson(condition)));
			rootObject.add("conditions", conditionsArray);

			final JsonElement ingredientObject = ingredient.toJson();
			rootObject.add("ingredient", ingredientObject);

			return rootObject;
		}
	}
}
