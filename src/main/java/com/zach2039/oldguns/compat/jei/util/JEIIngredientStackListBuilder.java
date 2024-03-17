/* Taken from IE with the following license.
 * 
 * ---
 * 
 * BluSunrize
 * Copyright (c) 2020
 *
 * This code is licensed under "Blu's License of Common Sense"
 * Details can be found in the license file in the root folder of this project
 */

package com.zach2039.oldguns.compat.jei.util;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author BluSunrize - 19.01.2020
 */
public class JEIIngredientStackListBuilder
{
	private final List<List<ItemStack>> list;

	private JEIIngredientStackListBuilder()
	{
		this.list = new ArrayList<>();
	}

	public static JEIIngredientStackListBuilder make(Ingredient... ingredientStacks)
	{
		JEIIngredientStackListBuilder builder = new JEIIngredientStackListBuilder();
		builder.add(ingredientStacks);
		return builder;
	}
	
	public static JEIIngredientStackListBuilder make(NonNullList<Ingredient> ingredientStacks)
	{
		JEIIngredientStackListBuilder builder = new JEIIngredientStackListBuilder();
		builder.add(ingredientStacks);
		return builder;
	}

	public JEIIngredientStackListBuilder add(Ingredient... ingredientStacks)
	{
		for(Ingredient ingr : ingredientStacks)
			this.list.add(Arrays.asList(ingr.getItems()));
		return this;
	}
	
	public JEIIngredientStackListBuilder add(NonNullList<Ingredient> ingredientStacks)
	{
		for(Ingredient ingr : ingredientStacks)
			this.list.add(Arrays.asList(ingr.getItems()));
		return this;
	}

	public List<List<ItemStack>> build()
	{
		return this.list;
	}
}