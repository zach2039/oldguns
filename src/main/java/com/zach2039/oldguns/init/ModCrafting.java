package com.zach2039.oldguns.init;

import java.util.Map;
import java.util.stream.Collectors;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.world.item.crafting.cauldron.CauldronRecipe;
import com.zach2039.oldguns.world.item.crafting.ingredient.ConditionalIngredientSerializer;
import com.zach2039.oldguns.world.item.crafting.ingredient.IngredientAnyDesignNotes;
import com.zach2039.oldguns.world.item.crafting.ingredient.IngredientNever;
import com.zach2039.oldguns.world.item.crafting.ingredient.IngredientPowderHorn;
import com.zach2039.oldguns.world.item.crafting.recipe.GunsmithsBenchDualArmorDyeRecipe;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapedGunsmithsBenchRecipe;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessGunsmithsBenchFirearmRepairWithKitRecipe;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessGunsmithsBenchFirearmRepairWithPartsRecipe;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessGunsmithsBenchHacksawRecipe;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessGunsmithsBenchMortarAndPestleRecipe;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessGunsmithsBenchRecipe;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessVanillaMortarAndPestleRecipe;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessVanillaMuzzleloaderPowderHornReloadRecipe;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessVanillaMuzzleloaderReloadRecipe;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessVanillaRefillPowderHornRecipe;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessVanillaScrapDesignNotesRecipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientSerializer;
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
		private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, OldGuns.MODID);

		private static boolean isInitialized;

		public static final RegistryObject<ShapelessVanillaMuzzleloaderReloadRecipe.Serializer> FIREARM_MUZZLELOADER_RELOAD_SHAPELESS = RECIPE_SERIALIZERS.register("firearm_muzzleloader_reload_shapeless", ShapelessVanillaMuzzleloaderReloadRecipe.Serializer::new);
		
		public static final RegistryObject<ShapelessVanillaMuzzleloaderPowderHornReloadRecipe.Serializer> FIREARM_MUZZLELOADER_POWDER_HORN_RELOAD_SHAPELESS = RECIPE_SERIALIZERS.register("firearm_muzzleloader_powder_horn_reload_shapeless", ShapelessVanillaMuzzleloaderPowderHornReloadRecipe.Serializer::new);
		
		public static final RegistryObject<ShapelessVanillaMortarAndPestleRecipe.Serializer> MORTAR_AND_PESTLE_SHAPELESS = RECIPE_SERIALIZERS.register("mortar_and_pestle_shapeless", ShapelessVanillaMortarAndPestleRecipe.Serializer::new);
		
		public static final RegistryObject<ShapelessGunsmithsBenchRecipe.Serializer> GUNSMITHS_BENCH_SHAPELESS = RECIPE_SERIALIZERS.register("gunsmiths_bench_shapeless", ShapelessGunsmithsBenchRecipe.Serializer::new);
		
		public static final RegistryObject<ShapelessGunsmithsBenchMortarAndPestleRecipe.Serializer> GUNSMITHS_BENCH_MORTAR_AND_PESTLE_SHAPELESS = RECIPE_SERIALIZERS.register("gunsmiths_bench_mortar_and_pestle_shapeless", ShapelessGunsmithsBenchMortarAndPestleRecipe.Serializer::new);
		
		public static final RegistryObject<ShapelessGunsmithsBenchHacksawRecipe.Serializer> GUNSMITHS_BENCH_HACKSAW_SHAPELESS = RECIPE_SERIALIZERS.register("gunsmiths_bench_hacksaw_shapeless", ShapelessGunsmithsBenchHacksawRecipe.Serializer::new);
		
		public static final RegistryObject<ShapelessGunsmithsBenchFirearmRepairWithKitRecipe.Serializer> GUNSMITHS_BENCH_FIREARM_REPAIR_WITH_KIT_SHAPELESS = RECIPE_SERIALIZERS.register("gunsmiths_bench_firearm_repair_with_kit_shapeless", ShapelessGunsmithsBenchFirearmRepairWithKitRecipe.Serializer::new);
		
		public static final RegistryObject<ShapelessGunsmithsBenchFirearmRepairWithPartsRecipe.Serializer> GUNSMITHS_BENCH_FIREARM_REPAIR_WITH_PARTS_SHAPELESS = RECIPE_SERIALIZERS.register("gunsmiths_bench_firearm_repair_with_parts_shapeless", ShapelessGunsmithsBenchFirearmRepairWithPartsRecipe.Serializer::new);
		
		public static final RegistryObject<ShapelessVanillaRefillPowderHornRecipe.Serializer> POWDER_HORN_REFILL_SHAPELESS = RECIPE_SERIALIZERS.register("powder_horn_refill_shapeless", ShapelessVanillaRefillPowderHornRecipe.Serializer::new);
		
		public static final RegistryObject<ShapelessVanillaScrapDesignNotesRecipe.Serializer> SCRAP_DESIGN_NOTES_SHAPELESS = RECIPE_SERIALIZERS.register("scrap_design_notes_shapeless", ShapelessVanillaScrapDesignNotesRecipe.Serializer::new);
		
		public static final RegistryObject<ShapedGunsmithsBenchRecipe.Serializer> GUNSMITHS_BENCH_SHAPED = RECIPE_SERIALIZERS.register("gunsmiths_bench_shaped", ShapedGunsmithsBenchRecipe.Serializer::new);
		
		public static final RegistryObject<GunsmithsBenchDualArmorDyeRecipe.Serializer> GUNSMITHS_BENCH_DUAL_ARMOR_DYE = RECIPE_SERIALIZERS.register("gunsmiths_bench_dual_armor_dye", GunsmithsBenchDualArmorDyeRecipe.Serializer::new);
		
		public static final RegistryObject<CauldronRecipe.Serializer> CAULDRON = RECIPE_SERIALIZERS.register("crafting_special_cauldron", CauldronRecipe.Serializer::new);
		
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



