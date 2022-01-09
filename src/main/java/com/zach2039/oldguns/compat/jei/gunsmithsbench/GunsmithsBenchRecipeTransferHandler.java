package com.zach2039.oldguns.compat.jei.gunsmithsbench;

import com.zach2039.oldguns.world.inventory.menu.GunsmithsBenchMenu;
import com.zach2039.oldguns.world.item.crafting.GunsmithsBenchRecipe;

import mezz.jei.api.recipe.transfer.IRecipeTransferHandler;
import mezz.jei.api.recipe.transfer.IRecipeTransferHandlerHelper;

public class GunsmithsBenchRecipeTransferHandler implements IRecipeTransferHandler<GunsmithsBenchMenu, GunsmithsBenchRecipe> {

	IRecipeTransferHandlerHelper transferHandlerHelper;
	
	public GunsmithsBenchRecipeTransferHandler(IRecipeTransferHandlerHelper transferHandlerHelper)
	{
		this.transferHandlerHelper = transferHandlerHelper;
	}
	
	@Override
	public Class<GunsmithsBenchMenu> getContainerClass() {
		return GunsmithsBenchMenu.class;
	}

	@Override
	public Class<GunsmithsBenchRecipe> getRecipeClass() {
		return GunsmithsBenchRecipe.class;
	}
}
