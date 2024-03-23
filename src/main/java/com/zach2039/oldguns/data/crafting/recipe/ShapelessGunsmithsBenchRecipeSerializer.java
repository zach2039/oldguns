package com.zach2039.oldguns.data.crafting.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.zach2039.oldguns.serialization.VanillaCodecs;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapedGunsmithsBenchRecipe;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessGunsmithsBenchRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.neoforged.fml.util.ObfuscationReflectionHelper;

import javax.annotation.Nullable;
import java.lang.reflect.Field;

import static net.minecraft.world.item.ItemStack.ITEM_WITH_COUNT_CODEC;

/**
 * Base class for {@link ShapelessRecipe} serializers.
 * <p>
 * Adapted from {@link ShapelessRecipe.Serializer}.
 *
 * @author Choonster
 */
public class ShapelessGunsmithsBenchRecipeSerializer<T extends ShapelessGunsmithsBenchRecipe> implements RecipeSerializer<T> {

	private final ShapelessGunsmithsBenchRecipeFactory<T> factory;
	private final Codec<T> codec;

	public ShapelessGunsmithsBenchRecipeSerializer(final ShapelessGunsmithsBenchRecipeFactory<T> factory) {
		this.factory = factory;

		codec = RecordCodecBuilder.create(instance -> instance.group(

				ExtraCodecs.strictOptionalField(Codec.STRING, "group", "")
						.forGetter(ShapelessGunsmithsBenchRecipe::getGroup),

				CraftingBookCategory.CODEC
						.fieldOf("category")
						.orElse(CraftingBookCategory.MISC)
						.forGetter(ShapelessGunsmithsBenchRecipe::category),

				VanillaCodecs.RECIPE_RESULT
						.fieldOf("result")
						.forGetter(ShapelessGunsmithsBenchRecipe::getResult),

				Ingredient.CODEC_NONEMPTY
						.listOf()
						.fieldOf("ingredients")
						.flatXmap(ingredients -> {
							final var nonEmptyIngredients = ingredients
									.stream()
									.filter(ingredient -> !ingredient.isEmpty())
									.toArray(Ingredient[]::new);

							if (nonEmptyIngredients.length == 0) {
								return DataResult.error(() -> "No ingredients for shapeless recipe");
							}

							return nonEmptyIngredients.length > ShapedGunsmithsBenchRecipe.MAX_WIDTH * ShapedGunsmithsBenchRecipe.MAX_HEIGHT ?
									DataResult.error(() -> "Too many ingredients for shapeless recipe") :
									DataResult.success(NonNullList.of(Ingredient.EMPTY, nonEmptyIngredients));
						}, DataResult::success)
						.forGetter(ShapelessGunsmithsBenchRecipe::getIngredients)

		).apply(instance, factory::createRecipe));
	}

	public ShapelessGunsmithsBenchRecipeFactory<T> factory() {
		return factory;
	}

	@Override
	public Codec<T> codec() {
		return codec;
	}

	@Nullable
	@Override
	public T fromNetwork(final FriendlyByteBuf buffer) {
		final var group = buffer.readUtf();
		final var category = buffer.readEnum(CraftingBookCategory.class);
		final var numIngredients = buffer.readVarInt();
		final var ingredients = NonNullList.withSize(numIngredients, Ingredient.EMPTY);

		ingredients.replaceAll(ignored -> Ingredient.fromNetwork(buffer));

		final var result = buffer.readItem();

		return factory.createRecipe(group, category, result, ingredients);
	}

	@Override
	public void toNetwork(final FriendlyByteBuf buffer, final T recipe) {
		buffer.writeUtf(recipe.getGroup());
		buffer.writeEnum(recipe.category());
		buffer.writeVarInt(recipe.getIngredients().size());

		recipe.getIngredients()
				.forEach(ingredient -> ingredient.toNetwork(buffer));

		buffer.writeItem(getResult(recipe));
	}

	private static ItemStack getResult(final ShapelessGunsmithsBenchRecipe recipe) {
		return recipe.getResult();
	}
}
