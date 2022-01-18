package com.zach2039.oldguns.crafting.ingredient;

import java.util.stream.Stream;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.init.ModCrafting;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.common.crafting.IIngredientSerializer;

/**
 * An {@link Ingredient} that never matches any {@link ItemStack}.
 * <p>
 * Test for this thread:
 * https://www.minecraftforge.net/forum/topic/59744-112-how-to-disable-some-mod-recipe-files-via-config-file/
 *
 * @author Choonster
 */
@SuppressWarnings("unused")
public class IngredientNever extends Ingredient {
	public static final IngredientNever INSTANCE = new IngredientNever();

	private IngredientNever() {
		super(Stream.empty());
	}

	@Override
	public boolean test(@Nullable final ItemStack p_test_1_) {
		return false;
	}

	@Override
	public IIngredientSerializer<? extends Ingredient> getSerializer() {
		return ModCrafting.Ingredients.NEVER;
	}

	public static class Serializer implements IIngredientSerializer<IngredientNever> {

		@Override
		public IngredientNever parse(final JsonObject json) {
			return IngredientNever.INSTANCE;
		}

		@Override
		public IngredientNever parse(final FriendlyByteBuf buffer) {
			return IngredientNever.INSTANCE;
		}

		@Override
		public void write(final FriendlyByteBuf buffer, final IngredientNever ingredient) {
			// No-op
		}
	}
}