package com.zach2039.oldguns.data.crafting.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.zach2039.oldguns.serialization.VanillaCodecs;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapedGunsmithsBenchRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.neoforged.fml.util.ObfuscationReflectionHelper;

import javax.annotation.Nullable;
import java.lang.reflect.Field;

/**
 * Base class for {@link ShapedRecipe} serializers.
 * <p>
 * Adapted from {@link ShapedRecipe.Serializer}.
 *
 * @author Choonster
 */
public class ShapedGunsmithsBenchRecipeSerializer<T extends ShapedGunsmithsBenchRecipe> implements RecipeSerializer<T> {

	private final ShapedGunsmithsBenchRecipeFactory<T> factory;
	private final Codec<T> codec;

	public ShapedGunsmithsBenchRecipeSerializer(final ShapedGunsmithsBenchRecipeFactory<T> factory) {
		this.factory = factory;

		codec = RecordCodecBuilder.create(instance -> instance.group(

				ExtraCodecs.strictOptionalField(Codec.STRING, "group", "")
						.forGetter(ShapedGunsmithsBenchRecipe::getGroup),

				CraftingBookCategory.CODEC
						.fieldOf("category")
						.orElse(CraftingBookCategory.MISC)
						.forGetter(ShapedGunsmithsBenchRecipe::category),

				ShapedRecipePattern.MAP_CODEC
						.forGetter(ShapedGunsmithsBenchRecipeSerializer::getPattern),

				VanillaCodecs.RECIPE_RESULT
						.fieldOf("result")
						.forGetter(ShapedGunsmithsBenchRecipeSerializer::getResult),

				ExtraCodecs.strictOptionalField(Codec.BOOL, "show_notification", true)
						.forGetter(ShapedGunsmithsBenchRecipe::showNotification)

		).apply(instance, factory::createRecipe));
	}

	public ShapedGunsmithsBenchRecipeFactory<T> factory() {
		return factory;
	}

	@Override
	public Codec<T> codec() {
		return codec;
	}

	@Nullable
	@Override
	public T fromNetwork(final FriendlyByteBuf buf) {
		final var group = buf.readUtf();
		final var category = buf.readEnum(CraftingBookCategory.class);
		final var pattern = ShapedRecipePattern.fromNetwork(buf);
		final var result = buf.readItem();
		final var showNotification = buf.readBoolean();

		return factory.createRecipe(group, category, pattern, result, showNotification);
	}

	@Override
	public void toNetwork(final FriendlyByteBuf buf, final T t) {
		buf.writeUtf(t.getGroup());
		buf.writeEnum(t.category());
		getPattern(t).toNetwork(buf);
		buf.writeItem(getResult(t));
		buf.writeBoolean(t.showNotification());
	}

	private static ShapedRecipePattern getPattern(final ShapedGunsmithsBenchRecipe recipe) {
		return recipe.getPattern();
	}

	private static ItemStack getResult(final ShapedGunsmithsBenchRecipe recipe) {
		return recipe.getResult();
	}
}