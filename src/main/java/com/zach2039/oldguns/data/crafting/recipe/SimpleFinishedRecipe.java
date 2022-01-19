package com.zach2039.oldguns.data.crafting.recipe;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.util.ModJsonUtil;

import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.nbt.CompoundNBT;

/**
 * A {@link FinishedRecipe} that allows the recipe result to have NBT.
 * Delegates all other logic to another {@link FinishedRecipe} instance.
 *
 * @author Choonster
 */
public class SimpleFinishedRecipe extends DelegateFinishedRecipe implements IFinishedRecipe {
	private final IRecipeSerializer<?> serializer;
	private final CompoundNBT resultNBT;

	public SimpleFinishedRecipe(final IFinishedRecipe baseRecipe, final ItemStack result, final IRecipeSerializer<?> serializer) {
		super(baseRecipe);
		this.serializer = serializer;
		resultNBT = result.getTag();
	}

	@Override
	public void serializeRecipeData(final JsonObject json) {
		super.serializeRecipeData(json);

		if (resultNBT != null) {
			ModJsonUtil.setCompoundTag(json.getAsJsonObject("result"), "nbt", resultNBT);
		}
	}

	@Override
	public IRecipeSerializer<?> getType() {
		return serializer;
	}
}
