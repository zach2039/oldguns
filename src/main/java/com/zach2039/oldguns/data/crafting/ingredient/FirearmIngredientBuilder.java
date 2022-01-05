package com.zach2039.oldguns.data.crafting.ingredient;

import java.util.stream.Stream;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.zach2039.oldguns.OldGuns;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class FirearmIngredientBuilder {
	private final ItemStack firearm;

	private FirearmIngredientBuilder(final ItemStack firearm) {
		this.firearm = firearm;
	}

	public static FirearmIngredientBuilder firearmIngredient(final ItemLike firearm) {
		return firearmIngredient(new ItemStack(firearm));
	}

	public static FirearmIngredientBuilder firearmIngredient(final ItemStack firearm) {
		return new FirearmIngredientBuilder(firearm);
	}

	/**
	 * Validates that the spawner {@link ItemStack} is non-empty and that the {@link EntityType} has been set and has a registry name.
	 */
	private void validate() {
		if (firearm.isEmpty()) {
			throw new IllegalStateException("Mob Spawner ingredient has empty spawner ItemStack");
		}
	}

	/**
	 * Builds the final {@link Ingredient}.
	 *
	 * @return The Ingredient
	 */
	public Result build() {
		validate();
		return new Result(firearm);
	}

	/**
	 * An {@link Ingredient} that serialises into JSON that can be deserialised by {@link MobSpawnerIngredientSerializer}.
	 * <p>
	 * Note: This is only intended for use during recipe generation, it won't match any items if used in a recipe during gameplay.
	 */
	public static class Result extends Ingredient {

		private Result(final ItemStack firearm) {
			super(Stream.of(new ItemValue(firearm)));
		}

		@Override
		public JsonElement toJson() {
			final JsonObject rootObject = super.toJson().getAsJsonObject();

			rootObject.addProperty("type", new ResourceLocation(OldGuns.MODID, "firearm").toString());

				return rootObject;
			}
		}
}
