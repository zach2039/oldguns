package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.item.crafting.conditions.CanCraftExoticItemsCondition;
import com.zach2039.oldguns.world.item.crafting.ingredient.ConditionalIngredientSerializer;
import com.zach2039.oldguns.world.item.crafting.ingredient.IngredientNever;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapedGunsmithsBenchRecipe;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessFirearmMuzzleloaderReloadRecipe;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessFirearmRepairRecipe;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessGunsmithsBenchRecipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author grilled-salmon
 */
public class ModCrafting {
		
	public static class Ingredients {
		public static final IIngredientSerializer<IngredientNever> NEVER = CraftingHelper.register(new ResourceLocation(OldGuns.MODID, "never"), new IngredientNever.Serializer());
		public static final IIngredientSerializer<Ingredient> CONDITIONAL = CraftingHelper.register(new ResourceLocation(OldGuns.MODID, "conditional"), new ConditionalIngredientSerializer());

		public static void register() {}
	}

	public static class Recipes {
		private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, OldGuns.MODID);

		private static boolean isInitialized;

		public static final RegistryObject<ShapelessFirearmMuzzleloaderReloadRecipe.Serializer> FIREARM_MUZZLELOADER_RELOAD_SHAPELESS = RECIPE_SERIALIZERS.register("firearm_muzzleloader_reload_shapeless",
				ShapelessFirearmMuzzleloaderReloadRecipe.Serializer::new
		);
		
		public static final RegistryObject<ShapelessFirearmRepairRecipe.Serializer> FIREARM_REPAIR_SHAPELESS = RECIPE_SERIALIZERS.register("firearm_repair_shapeless",
				ShapelessFirearmRepairRecipe.Serializer::new
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
