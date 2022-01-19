package com.zach2039.oldguns.init;

import java.util.Map;
import java.util.stream.Collectors;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.crafting.ingredient.ConditionalIngredientSerializer;
import com.zach2039.oldguns.crafting.ingredient.IngredientNever;
import com.zach2039.oldguns.crafting.recipe.ShapedGunsmithsBenchRecipe;
import com.zach2039.oldguns.crafting.recipe.ShapelessGunsmithsBenchFirearmRepairRecipe;
import com.zach2039.oldguns.crafting.recipe.ShapelessGunsmithsBenchMortarAndPestleRecipe;
import com.zach2039.oldguns.crafting.recipe.ShapelessGunsmithsBenchRecipe;
import com.zach2039.oldguns.crafting.recipe.ShapelessVanillaFirearmRepairRecipe;
import com.zach2039.oldguns.crafting.recipe.ShapelessVanillaMortarAndPestleRecipe;
import com.zach2039.oldguns.crafting.recipe.ShapelessVanillaMuzzleloaderReloadRecipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author grilled-salmon
 */
public class ModCrafting {
		
	public static class Brewing {

		public static void register() {
			BrewingRecipeRegistry.addRecipe(
					Ingredient.of(Items.POTION.getDefaultInstance()),
					Ingredient.of(ModItems.NITRATE_SOIL.get().getDefaultInstance()),
					ModItems.LIQUID_NITER.get().getDefaultInstance()
					);	
		}
	}
	
	public static class Ingredients {
		public static final IIngredientSerializer<IngredientNever> NEVER = CraftingHelper.register(new ResourceLocation(OldGuns.MODID, "never"), new IngredientNever.Serializer());
		public static final IIngredientSerializer<Ingredient> CONDITIONAL = CraftingHelper.register(new ResourceLocation(OldGuns.MODID, "conditional"), new ConditionalIngredientSerializer());

		public static void register() {}
	}

	public static class Recipes {
		private static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, OldGuns.MODID);

		private static boolean isInitialized;

		public static final RegistryObject<ShapelessVanillaMuzzleloaderReloadRecipe.Serializer> FIREARM_MUZZLELOADER_RELOAD_SHAPELESS = RECIPE_SERIALIZERS.register("firearm_muzzleloader_reload_shapeless",
				ShapelessVanillaMuzzleloaderReloadRecipe.Serializer::new
		);
		
		public static final RegistryObject<ShapelessVanillaFirearmRepairRecipe.Serializer> FIREARM_REPAIR_SHAPELESS = RECIPE_SERIALIZERS.register("firearm_repair_shapeless",
				ShapelessVanillaFirearmRepairRecipe.Serializer::new
		);
		
		public static final RegistryObject<ShapelessVanillaMortarAndPestleRecipe.Serializer> MORTAR_AND_PESTLE_SHAPELESS = RECIPE_SERIALIZERS.register("mortar_and_pestle_shapeless",
				ShapelessVanillaMortarAndPestleRecipe.Serializer::new
		);
		
		public static final RegistryObject<ShapelessGunsmithsBenchRecipe.Serializer> GUNSMITHS_BENCH_SHAPELESS = RECIPE_SERIALIZERS.register("gunsmiths_bench_shapeless",
				ShapelessGunsmithsBenchRecipe.Serializer::new
		);
		public static final RegistryObject<ShapelessGunsmithsBenchMortarAndPestleRecipe.Serializer> GUNSMITHS_BENCH_MORTAR_AND_PESTLE_SHAPELESS = RECIPE_SERIALIZERS.register("gunsmiths_bench_mortar_and_pestle_shapeless",
				ShapelessGunsmithsBenchMortarAndPestleRecipe.Serializer::new
		);
		public static final RegistryObject<ShapelessGunsmithsBenchFirearmRepairRecipe.Serializer> GUNSMITHS_BENCH_FIREARM_REPAIR_SHAPELESS = RECIPE_SERIALIZERS.register("gunsmiths_bench_firearm_repair_shapeless",
				ShapelessGunsmithsBenchFirearmRepairRecipe.Serializer::new
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
	
	@SuppressWarnings("unchecked")
	public static <C extends IInventory, T extends IRecipe<?>> Map<ResourceLocation, IRecipe<?>> getRecipes(World world, IRecipeType<T> type) {
		return  (Map<ResourceLocation, IRecipe<?>>) world.getRecipeManager().getRecipeIds()
				.collect(Collectors.toMap(v -> v, v -> world.getRecipeManager().byKey(v).orElseThrow(IllegalArgumentException::new)));
	}
}



