package com.zach2039.oldguns.world.item.crafting.ingredient;

import com.mojang.serialization.Codec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.crafting.IngredientType;

import javax.annotation.Nullable;
import java.util.stream.Stream;

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

	public static final Codec<IngredientNever> CODEC = Codec.unit(INSTANCE);

	public static final IngredientType<IngredientNever> TYPE = new IngredientType<>(CODEC);

	private IngredientNever() {
		super(Stream.empty());
	}

	@Override
	public boolean test(@Nullable ItemStack itemStack) {
		return false;
	}
}