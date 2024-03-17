package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.item.crafting.GunsmithsBenchRecipe;
import com.zach2039.oldguns.world.item.crafting.cauldron.CauldronRecipe;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessVanillaMuzzleloaderPowderHornReloadRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Optional;
import java.util.function.Supplier;

public class ModRecipeTypes {
	public static final DeferredRegister<RecipeType<?>> RECIPES = DeferredRegister.create(Registries.RECIPE_TYPE, OldGuns.MODID);
	
	public static final TypeWithClass<GunsmithsBenchRecipe> GUNSMITHS_BENCH = register("gunsmiths_bench", GunsmithsBenchRecipe.class);
	
	public static final TypeWithClass<GunsmithsBenchRecipe> DAMAGEABLE_TOOL_CRAFT = register("damagable_tool_craft", GunsmithsBenchRecipe.class);
	
	public static final TypeWithClass<CauldronRecipe> CAULDRON = register("cauldron", CauldronRecipe.class);
	
	public static final TypeWithClass<ShapelessVanillaMuzzleloaderPowderHornReloadRecipe> POWDER_HORN_RELOAD = register("powder_horn_reload", ShapelessVanillaMuzzleloaderPowderHornReloadRecipe.class);

	private static boolean isInitialized;
	
	/**
	 * Registers the {@link DeferredRegister} instance with the mod event bus.
	 * <p>
	 * This should be called during mod construction.
	 *
	 * @param modEventBus The mod event bus
	 */
	public static void initialize(final IEventBus modEventBus) {
		if (isInitialized) {
			throw new IllegalStateException("Already initialized");
		}

		RECIPES.register(modEventBus);

		isInitialized = true;
	}
	
	static <T extends Recipe<?>> TypeWithClass<T> register(String name, Class<T> type) {
		DeferredHolder<RecipeType<?>, ? extends RecipeType<T>> regObj = RECIPES.register(name, () -> new RecipeType<T>() {});
		return new TypeWithClass<>(regObj, type);
	}

	public <C extends Container, T extends Recipe<?>> Optional<T> tryMatch(Recipe<C> recipe, Level level, C c) {
		return recipe.matches(c, level) ? Optional.of((T)recipe) : Optional.empty();
	}
	
	public record TypeWithClass<T extends Recipe<?>>(DeferredHolder<RecipeType<?>, ? extends RecipeType<T>> type, Class<T> recipeClass) implements Supplier<RecipeType<T>> {
		public RecipeType<T> get() {
			return type.get();
		}
	}
}
