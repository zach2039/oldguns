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

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.crafting.IDesignNotes;
import com.zach2039.oldguns.compat.jei.JEIRecipeTypes;
import com.zach2039.oldguns.compat.jei.OldGunsRecipeCategory;
import com.zach2039.oldguns.init.ModBlocks;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.world.item.crafting.GunsmithsBenchRecipe;
import com.zach2039.oldguns.world.item.crafting.recipe.ShapelessGunsmithsBenchRecipe;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class GunsmithsBenchRecipeCategory extends OldGunsRecipeCategory<GunsmithsBenchRecipe> {

	public static final ResourceLocation UID = new ResourceLocation(OldGuns.MODID, "gunsmiths_bench");
	
	public GunsmithsBenchRecipeCategory(IGuiHelper guiHelper) {
		super(GunsmithsBenchRecipe.class, guiHelper, UID, "block.oldguns.gunsmiths_bench");
		ResourceLocation background = new ResourceLocation(OldGuns.MODID, "textures/gui/container/gunsmiths_bench.png");
		setBackground(guiHelper.createDrawable(background, 5, 13, 142, 60));
		setIcon(new ItemStack(ModBlocks.GUNSMITHS_BENCH.get()));
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, GunsmithsBenchRecipe recipe, IFocusGroup focuses) {		
		ItemStack designNotes = getNotesForRecipe(recipe.getResultItem());
			
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 3; ++x) {
				int index = x + (y * 3);
				if (index < recipe.getIngredients().size()) {
					builder.addSlot(RecipeIngredientRole.INPUT, 25 + (x * 18), 4 + (y * 18))
						.addItemStacks(Arrays.asList(recipe.getIngredients().get(index).getItems()));
				}
			}
		}
		
		builder.addSlot(RecipeIngredientRole.CATALYST, 3, 22)
			.addItemStack(designNotes);
		
		if (recipe instanceof ShapelessGunsmithsBenchRecipe) {
			builder.setShapeless();
		}
	
		builder.addSlot(RecipeIngredientRole.OUTPUT, 119, 22)
			.addIngredient(VanillaTypes.ITEM_STACK, recipe.getResultItem());
	}
	
	private ItemStack getNotesForRecipe(ItemStack output) {
		ItemStack designNotesStack = ItemStack.EMPTY;
		
		boolean hasDesign = IDesignNotes.hasDesignNotes(output.getItem());
		if (hasDesign) {
			ItemStack designItem = IDesignNotes.setDesignTagOnItem(new ItemStack(ModItems.DESIGN_NOTES.get()), output.getItem());
			designNotesStack = designItem;
		}
		
		return designNotesStack;
	}

	@Override
	public RecipeType<GunsmithsBenchRecipe> getRecipeType() {
		return JEIRecipeTypes.GUNSMITHS_BENCH;
	}
}
