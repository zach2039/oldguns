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
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fluids.FluidStack;

public class CauldronInteractionRecipeCategory implements IRecipeCategory<CauldronRecipe> {
	public static final ResourceLocation UID = new ResourceLocation(OldGuns.MODID, "cauldron_interaction");
	
	public static final int width = 82;
	public static final int height = 34;

	private final IDrawable background;
	private final IDrawable icon;
	private final Component localizedName;
	
	public CauldronInteractionRecipeCategory(IGuiHelper guiHelper) {
		background = guiHelper.createBlankDrawable(82, 34);
		icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Blocks.CAULDRON));
		localizedName = Component.translatable("block.minecraft.cauldron");
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
	
	private void drawSlot(GuiGraphics guiGraphics, int x, int y, int w, int h, int dark, int main, int light)
	{
		final int minX = x + 8 - w / 2;
		final int minY = y + 8 - h / 2;
		final int maxX = minX + w;
		final int maxY = minY + h;

		guiGraphics.fill(minX, minY - 1, maxX, minY, dark);
		guiGraphics.fill(minX - 1, minY - 1, minX, maxY, dark);
		guiGraphics.fill(minX, minY, maxX, maxY, main);
		guiGraphics.fill(minX, maxY, maxX + 1, maxY + 1, light);
		guiGraphics.fill(maxX, minY, maxX + 1, maxY, light);
	}
	
	@Override
	public void draw(CauldronRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
		drawSlot(guiGraphics, 31, 9, 16, 16, (0xff<<24)|0x373737, (0xff<<24)|0x8b8b8b, (0xff<<24)|0xffffff);
	}
	
	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, CauldronRecipe recipe, IFocusGroup focuses) {
		ItemStack resultItem = recipe.getOutput();
		ItemStack inputItem = recipe.getInput();

		builder.addSlot(RecipeIngredientRole.INPUT, 1, 9)
			.addItemStack(inputItem)
			;
		
		if (recipe.getFluid() == Fluids.EMPTY) {
			builder.addSlot(RecipeIngredientRole.INPUT, 31, 9)
				.addItemStack(new ItemStack(Blocks.CAULDRON))
				;
		} else {
			builder.addSlot(RecipeIngredientRole.INPUT, 31, 9)
				.setFluidRenderer(1000, true, 16, 16)
				.addIngredient(ForgeTypes.FLUID_STACK, new FluidStack(recipe.getFluid(), 333))
				.setOverlay(background, width, height)
				;	
		}
		
		
		builder.addSlot(RecipeIngredientRole.OUTPUT, 61, 9)
			.addItemStack(resultItem)
			;
	}

	@Override
	public RecipeType<CauldronRecipe> getRecipeType() {
		return JEIRecipeTypes.CAULDRON;
	}
}
