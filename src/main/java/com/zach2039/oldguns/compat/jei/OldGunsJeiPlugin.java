package com.zach2039.oldguns.compat.jei;

import java.util.stream.Collectors;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.crafting.IDesignNotes;
import com.zach2039.oldguns.client.gui.inventory.GunsmithsBenchScreen;
import com.zach2039.oldguns.compat.jei.gunsmithsbench.GunsmithsBenchRecipeCategory;
import com.zach2039.oldguns.init.ModBlocks;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.world.inventory.menu.GunsmithsBenchMenu;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.registration.IAdvancedRegistration;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IModIngredientRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import mezz.jei.api.registration.ISubtypeRegistration;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeSerializer;

@JeiPlugin
public class OldGunsJeiPlugin implements IModPlugin {

	private static final ResourceLocation UID = new ResourceLocation(OldGuns.MODID, "plugin/main");
	
	public static final ItemStack POTION = new ItemStack(Items.POTION);
	
	@Override
	public void registerItemSubtypes(ISubtypeRegistration registration) {
		registration.registerSubtypeInterpreter(ModItems.DESIGN_NOTES.get(), (stack, ctx) -> {
			return (IDesignNotes.getDesign(stack) != "") ? IDesignNotes.getDesign(stack) : IIngredientSubtypeInterpreter.NONE;
		});
	}

	@Override
	public void registerFluidSubtypes(ISubtypeRegistration registration) {

	}

	@Override
	public void registerIngredients(IModIngredientRegistration registration) {

	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
		registration.addRecipeCategories(
					new GunsmithsBenchRecipeCategory(guiHelper)
				);
	}

	@Override
	public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {

	}

	@SuppressWarnings("resource")
	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		
		if (Minecraft.getInstance().level != null) {
			RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
			
			registration.addRecipes(recipeManager.getRecipes().stream()
					.filter(OldGunsJeiPlugin::isGunsmithsBenchRecipe)
					.collect(Collectors.toList()), GunsmithsBenchRecipeCategory.UID);
		}	
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
		registration.addRecipeTransferHandler(GunsmithsBenchMenu.class, GunsmithsBenchRecipeCategory.UID, 1, 10, 11, 36);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(ModBlocks.GUNSMITHS_BENCH.get()), GunsmithsBenchRecipeCategory.UID);
	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration) {
		registration.addRecipeClickArea(GunsmithsBenchScreen.class, 88, 31, 28, 23, GunsmithsBenchRecipeCategory.UID);
	}

	@Override
	public void registerAdvanced(IAdvancedRegistration registration) {
		
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
		
	}

	@Override
	public ResourceLocation getPluginUid() {
		return UID;
	}
	
	private static boolean isGunsmithsBenchRecipe(Recipe<?> recipe) {
		RecipeSerializer<?> serializer = recipe.getSerializer();
		return (
				(serializer == ModCrafting.Recipes.GUNSMITHS_BENCH_MORTAR_AND_PESTLE_SHAPELESS.get()) ||
				(serializer == ModCrafting.Recipes.GUNSMITHS_BENCH_SHAPED.get()) || 
				(serializer == ModCrafting.Recipes.GUNSMITHS_BENCH_SHAPELESS.get())
				);
	}
}
