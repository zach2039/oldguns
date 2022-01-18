package com.zach2039.oldguns.init;

import java.util.Optional;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.crafting.recipe.GunsmithsBenchRecipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public interface ModRecipeTypes<T extends IRecipe<?>> {
	
	IRecipeType<GunsmithsBenchRecipe> GUNSMITHS_BENCH = register("gunsmiths_bench");
	IRecipeType<GunsmithsBenchRecipe> DAMAGEABLE_TOOL_CRAFT = register("damagable_tool_craft");

	static <T extends IRecipe<?>> IRecipeType<T> register(final String name) {
		return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(OldGuns.MODID, name), new IRecipeType<T>() {
			public String toString() {
				return name;
			}
		});
	}

	default <C extends IInventory> Optional<T> tryMatch(IRecipe<C> p_44116_, World p_44117_, C p_44118_) {
		return p_44116_.matches(p_44118_, p_44117_) ? Optional.of((T)p_44116_) : Optional.empty();
	}
}
