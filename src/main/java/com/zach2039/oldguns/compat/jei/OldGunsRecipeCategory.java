/*
 * Taken from IE with the following license.
 * 
 * ---
 * 
 * BluSunrize
 * Copyright (c) 2017
 *
 * This code is licensed under "Blu's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */

package com.zach2039.oldguns.compat.jei;

import java.awt.Component;

import javax.annotation.Nullable;

import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public abstract class OldGunsRecipeCategory<T> implements IRecipeCategory<T>
{
	public final ResourceLocation uid;
	protected final IGuiHelper guiHelper;
	private final Class<? extends T> recipeClass;
	public ITextComponent title;
	private IDrawableStatic background;
	private IDrawable icon;

	public OldGunsRecipeCategory(Class<? extends T> recipeClass, IGuiHelper guiHelper, ResourceLocation uid, String localKey)
	{
		this.recipeClass = recipeClass;
		this.guiHelper = guiHelper;
		this.uid = uid;
		this.title = new TranslationTextComponent(localKey);
	}

	@Override
	public IDrawable getBackground()
	{
		return this.background;
	}

	protected void setBackground(IDrawableStatic background)
	{
		this.background = background;
	}

	@Nullable
	@Override
	public IDrawable getIcon()
	{
		return this.icon;
	}

	protected void setIcon(ItemStack stack)
	{
		this.setIcon(this.guiHelper.createDrawableIngredient(stack));
	}

	protected void setIcon(IDrawable icon)
	{
		this.icon = icon;
	}

	@Override
	public ResourceLocation getUid()
	{
		return this.uid;
	}

	@Override
	public String getTitle()
	{
		return this.title.getString();
	}
	
	@Override
	public ITextComponent getTitleAsTextComponent()
	{
		return this.title;
	}

	@Override
	public Class<? extends T> getRecipeClass()
	{
		return this.recipeClass;
	}
}