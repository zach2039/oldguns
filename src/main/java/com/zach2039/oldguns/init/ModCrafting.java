package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.item.crafting.ingredient.FirearmIngredient;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapedGunsmithsBenchRecipe;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessFirearmMuzzleloaderReloadRecipe;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessGunsmithsBenchRecipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModCrafting {

	public static class Ingredients {
		public static final IIngredientSerializer<FirearmIngredient> FIREARM = CraftingHelper.register(new ResourceLocation(OldGuns.MODID, "firearm"), new FirearmIngredient.Serializer());

		public static void register() {}
	}

	public static class Recipes {
		private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, OldGuns.MODID);

		private static boolean isInitialized;

		public static final RegistryObject<ShapelessFirearmMuzzleloaderReloadRecipe.Serializer> FIREARM_MUZZLELOADER_RELOAD_SHAPELESS = RECIPE_SERIALIZERS.register("firearm_muzzleloader_reload_shapeless",
				ShapelessFirearmMuzzleloaderReloadRecipe.Serializer::new
		);
		
		public static final RegistryObject<ShapelessGunsmithsBenchRecipe.Serializer> GUNSMITHS_BENCH_SHAPELESS = RECIPE_SERIALIZERS.register("gunsmiths_bench_shapeless",
				ShapelessGunsmithsBenchRecipe.Serializer::new
		);
		
		public static final RegistryObject<ShapedGunsmithsBenchRecipe.Serializer> GUNSMITHS_BENCH_SHAPED = RECIPE_SERIALIZERS.register("gunsmiths_bench_shaped",
				ShapedGunsmithsBenchRecipe.Serializer::new
		);
		
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

			RECIPE_SERIALIZERS.register(modEventBus);

			isInitialized = true;
		}
	}
}
