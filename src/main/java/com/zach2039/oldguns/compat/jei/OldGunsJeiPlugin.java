package com.zach2039.oldguns.compat.jei;

import java.util.stream.Collectors;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.client.gui.inventory.GunsmithsBenchScreen;
import com.zach2039.oldguns.compat.jei.gunsmithsbench.GunsmithsBenchRecipeCategory;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapedGunsmithsBenchRecipe;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessGunsmithsBenchRecipe;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
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
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeSerializer;

@JeiPlugin
public class OldGunsJeiPlugin implements IModPlugin {

	private static final ResourceLocation UID = new ResourceLocation(OldGuns.MODID, "plugin/main");
	
	@Override
	public void registerItemSubtypes(ISubtypeRegistration registration) {

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

	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {

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
		return (serializer instanceof ShapedGunsmithsBenchRecipe.Serializer) || (serializer instanceof ShapelessGunsmithsBenchRecipe.Serializer);
	}
}
