package com.zach2039.oldguns.init;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.item.crafting.cauldron.CauldronRecipe;
import com.zach2039.oldguns.world.item.crafting.ingredient.ConditionalIngredientSerializer;
import com.zach2039.oldguns.world.item.crafting.ingredient.IngredientAnyDesignNotes;
import com.zach2039.oldguns.world.item.crafting.ingredient.IngredientNever;
import com.zach2039.oldguns.world.item.crafting.ingredient.IngredientPowderHorn;
import com.zach2039.oldguns.world.item.crafting.recipe.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.brewing.BrewingRecipeRegistry;
import net.neoforged.neoforge.common.crafting.CraftingHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Taken from <a href="https://github.com/Choonster-Minecraft-Mods/TestMod3">TestMod3</a> on Github
 * 
 * @author Choonster
 *
 * With additions by:
 * @author zach2039
 */
public class ModCrafting {
	
	public static class Brewing {

		public static void register() {
			BrewingRecipeRegistry.addRecipe(
					Ingredient.of(Items.POTION.getDefaultInstance()),
					Ingredient.of(ModItems.NITRATE_SOIL.get().getDefaultInstance()),
					ModItems.LIQUID_NITER_BOTTLE.get().getDefaultInstance()
					);	
		}
	}
	
	public static class Ingredients {
		public static final IIngredientSerializer<IngredientNever> NEVER = CraftingHelper.register(new ResourceLocation(OldGuns.MODID, "never"), new IngredientNever.Serializer());
		public static final IIngredientSerializer<Ingredient> CONDITIONAL = CraftingHelper.register(new ResourceLocation(OldGuns.MODID, "conditional"), new ConditionalIngredientSerializer());
		public static final IIngredientSerializer<IngredientPowderHorn> POWDER_HORN = CraftingHelper.register(new ResourceLocation(OldGuns.MODID, "powder_horn"), new IngredientPowderHorn.Serializer());
		public static final IIngredientSerializer<IngredientAnyDesignNotes> ANY_DESIGN_NOTES = CraftingHelper.register(new ResourceLocation(OldGuns.MODID, "any_design_notes"), new IngredientAnyDesignNotes.Serializer());

		public static void register() {}
	}

	public static class Recipes {
		private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, OldGuns.MODID);

		private static boolean isInitialized;

		public static final DeferredHolder<RecipeSerializer<?>, ? extends RecipeSerializer<ShapelessVanillaMuzzleloaderReloadRecipe.Serializer>> FIREARM_MUZZLELOADER_RELOAD_SHAPELESS = RECIPE_SERIALIZERS.register("firearm_muzzleloader_reload_shapeless", ShapelessVanillaMuzzleloaderReloadRecipe.Serializer::new);
		
		public static final DeferredHolder<RecipeSerializer, ? extends ShapelessVanillaMuzzleloaderPowderHornReloadRecipe.Serializer> FIREARM_MUZZLELOADER_POWDER_HORN_RELOAD_SHAPELESS = RECIPE_SERIALIZERS.register("firearm_muzzleloader_powder_horn_reload_shapeless", ShapelessVanillaMuzzleloaderPowderHornReloadRecipe.Serializer::new);
		
		public static final DeferredHolder<RecipeSerializer, ? extends ShapelessVanillaMortarAndPestleRecipe.Serializer> MORTAR_AND_PESTLE_SHAPELESS = RECIPE_SERIALIZERS.register("mortar_and_pestle_shapeless", ShapelessVanillaMortarAndPestleRecipe.Serializer::new);
		
		public static final DeferredHolder<RecipeSerializer, ? extends ShapelessGunsmithsBenchRecipe.Serializer> GUNSMITHS_BENCH_SHAPELESS = RECIPE_SERIALIZERS.register("gunsmiths_bench_shapeless", ShapelessGunsmithsBenchRecipe.Serializer::new);
		
		public static final DeferredHolder<RecipeSerializer, ? extends ShapelessGunsmithsBenchMortarAndPestleRecipe.Serializer> GUNSMITHS_BENCH_MORTAR_AND_PESTLE_SHAPELESS = RECIPE_SERIALIZERS.register("gunsmiths_bench_mortar_and_pestle_shapeless", ShapelessGunsmithsBenchMortarAndPestleRecipe.Serializer::new);
		
		public static final DeferredHolder<RecipeSerializer, ? extends ShapelessGunsmithsBenchHacksawRecipe.Serializer> GUNSMITHS_BENCH_HACKSAW_SHAPELESS = RECIPE_SERIALIZERS.register("gunsmiths_bench_hacksaw_shapeless", ShapelessGunsmithsBenchHacksawRecipe.Serializer::new);
		
		public static final DeferredHolder<RecipeSerializer, ? extends ShapelessGunsmithsBenchFirearmRepairWithKitRecipe.Serializer> GUNSMITHS_BENCH_FIREARM_REPAIR_WITH_KIT_SHAPELESS = RECIPE_SERIALIZERS.register("gunsmiths_bench_firearm_repair_with_kit_shapeless", ShapelessGunsmithsBenchFirearmRepairWithKitRecipe.Serializer::new);
		
		public static final DeferredHolder<RecipeSerializer, ? extends ShapelessGunsmithsBenchFirearmRepairWithPartsRecipe.Serializer> GUNSMITHS_BENCH_FIREARM_REPAIR_WITH_PARTS_SHAPELESS = RECIPE_SERIALIZERS.register("gunsmiths_bench_firearm_repair_with_parts_shapeless", ShapelessGunsmithsBenchFirearmRepairWithPartsRecipe.Serializer::new);
		
		public static final DeferredHolder<RecipeSerializer, ? extends ShapelessVanillaRefillPowderHornRecipe.Serializer> POWDER_HORN_REFILL_SHAPELESS = RECIPE_SERIALIZERS.register("powder_horn_refill_shapeless", ShapelessVanillaRefillPowderHornRecipe.Serializer::new);
		
		public static final DeferredHolder<RecipeSerializer, ? extends ShapelessVanillaScrapDesignNotesRecipe.Serializer> SCRAP_DESIGN_NOTES_SHAPELESS = RECIPE_SERIALIZERS.register("scrap_design_notes_shapeless", ShapelessVanillaScrapDesignNotesRecipe.Serializer::new);
		
		public static final DeferredHolder<RecipeSerializer, ? extends ShapedGunsmithsBenchRecipe.Serializer> GUNSMITHS_BENCH_SHAPED = RECIPE_SERIALIZERS.register("gunsmiths_bench_shaped", ShapedGunsmithsBenchRecipe.Serializer::new);
		
		public static final DeferredHolder<RecipeSerializer, ? extends GunsmithsBenchDualArmorDyeRecipe.Serializer> GUNSMITHS_BENCH_DUAL_ARMOR_DYE = RECIPE_SERIALIZERS.register("gunsmiths_bench_dual_armor_dye", GunsmithsBenchDualArmorDyeRecipe.Serializer::new);
		
		public static final DeferredHolder<RecipeSerializer, ? extends CauldronRecipe.Serializer> CAULDRON = RECIPE_SERIALIZERS.register("crafting_special_cauldron", CauldronRecipe.Serializer::new);
		
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
	public static <C extends Container, T extends Recipe<C>> Map<ResourceLocation, Recipe<C>> getRecipes(Level world, RecipeType<T> type) {
		return  (Map<ResourceLocation, Recipe<C>>) world.getRecipeManager().getRecipeIds()
				.collect(Collectors.toMap(v -> v, v -> world.getRecipeManager().byKey(v).orElseThrow(IllegalArgumentException::new)));
	}
}



