package com.zach2039.oldguns.init;

import java.util.Optional;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.item.crafting.GunsmithsBenchRecipe;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public interface ModRecipeTypes<T extends Recipe<?>> {
	
	RecipeType<GunsmithsBenchRecipe> GUNSMITHS_BENCH = register("gunsmiths_bench");
	RecipeType<GunsmithsBenchRecipe> DAMAGEABLE_TOOL_CRAFT = register("damagable_tool_craft");

	static <T extends Recipe<?>> RecipeType<T> register(final String name) {
		return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(OldGuns.MODID, name), new RecipeType<T>() {
			public String toString() {
				return name;
			}
		});
	}

	default <C extends Container> Optional<T> tryMatch(Recipe<C> p_44116_, Level p_44117_, C p_44118_) {
		return p_44116_.matches(p_44118_, p_44117_) ? Optional.of((T)p_44116_) : Optional.empty();
	}
}
