/* Some taken from IE with the following license.
 * 
 * ---
 * 
 * BluSunrize
 * Copyright (c) 2017
 *
 * This code is licensed under "Blu's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */
package com.zach2039.oldguns.compat.jei.category;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.compat.jei.JEIRecipeTypes;
import com.zach2039.oldguns.world.item.crafting.cauldron.CauldronRecipe;

import mezz.jei.api.constants.ModIds;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.ICraftingGridHelper;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

public class CauldronInteractionRecipeCategory implements IRecipeCategory<CauldronRecipe> {
	public static final ResourceLocation UID = new ResourceLocation(OldGuns.MODID, "cauldron_interaction");
	
	public static final int width = 82;
	public static final int height = 34;

	private final IDrawable background;
	private final IDrawable icon;
	private final Component localizedName;
	private final ICraftingGridHelper craftingGridHelper;
	
	public CauldronInteractionRecipeCategory(IGuiHelper guiHelper) {
		ResourceLocation location = new ResourceLocation(ModIds.JEI_ID, "textures/gui/gui_vanilla.png");
		background = guiHelper.createDrawable(location, 0, 220, width, height);
		icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Blocks.CAULDRON));
		localizedName = Component.translatable("block.factorymade.industrial_shaper");
		craftingGridHelper = guiHelper.createCraftingGridHelper();
	}

	@Override
	public Component getTitle() {
		return localizedName;
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public void draw(CauldronRecipe recipe, @Nonnull IRecipeSlotsView slotsView, @Nonnull PoseStack ms, double mouseX, double mouseY) {
		RenderSystem.enableBlend();
		background.draw(ms, 0, 4);	
		RenderSystem.disableBlend();
	}

	
	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, CauldronRecipe recipe, IFocusGroup focuses) {
		ItemStack resultItem = recipe.getResultItem();
		
		List<ItemStack> inputs = Arrays.asList(recipe.getInput());
		
		craftingGridHelper.setOutputs(builder, VanillaTypes.ITEM_STACK, List.of(resultItem));
		craftingGridHelper.setInputs(builder, VanillaTypes.ITEM_STACK, Arrays.asList(inputs), 0, 0);
	}

	@Override
	public RecipeType<CauldronRecipe> getRecipeType() {
		return JEIRecipeTypes.CAULDRON;
	}
}
