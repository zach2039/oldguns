package com.zach2039.oldguns.world.item.crafting.recipe;

import com.zach2039.oldguns.api.crafting.IDesignNotes;
import com.zach2039.oldguns.api.firearm.Firearm;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.init.ModRecipeTypes;
import com.zach2039.oldguns.util.ModRegistryUtil;
import com.zach2039.oldguns.world.inventory.GunsmithsBenchCraftingContainer;
import com.zach2039.oldguns.world.inventory.menu.GunsmithsBenchMenu;
import com.zach2039.oldguns.world.item.crafting.GunsmithsBenchRecipe;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;

public class ShapedGunsmithsBenchRecipe implements Recipe<GunsmithsBenchCraftingContainer>, GunsmithsBenchRecipe {

	public static final int MAX_WIDTH = 3;
	public static final int MAX_HEIGHT = 3;

	final ShapedRecipePattern pattern;

	final ItemStack result;

	final String group;

	protected ShapedGunsmithsBenchRecipe(final String group, final ShapedRecipePattern pattern, final ItemStack recipeOutput) {
		this.group = group;
		this.pattern = pattern;
		this.result = recipeOutput;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModCrafting.Recipes.GUNSMITHS_BENCH_SHAPED.get();
	}

	@Override
	public String getGroup() {
		return this.group;
	}

	@Override
    @Nonnull
    public ItemStack getResultItem(RegistryAccess registryAccess)
	{
		ItemStack outputStack = this.result;
		
		if (outputStack.getItem() instanceof Firearm)
			((Firearm)outputStack.getItem()).initNBTTags(outputStack);
		
		return outputStack;
	}

	public ItemStack getResult()
	{
		return this.result;
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return this.pattern.ingredients();
	}

	public ShapedRecipePattern getPattern()
	{
		return this.pattern;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width >= this.getWidth() && height >= this.getHeight();
	}

	@Override
	public boolean matches(GunsmithsBenchCraftingContainer craftingContainer, Level levelIn) {
		for(int i = 0; i <= craftingContainer.getWidth() - this.getWidth(); ++i) {
			for(int j = 0; j <= craftingContainer.getHeight() - this.getHeight(); ++j) {
				if (this.matches(craftingContainer, i, j, true)) {
					return true;
				}

				if (this.matches(craftingContainer, i, j, false)) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean matches(GunsmithsBenchCraftingContainer containerCrafting, int width, int height, boolean p_44174_) {
		for(int i = 0; i < containerCrafting.getWidth(); ++i) {
			for(int j = 0; j < containerCrafting.getHeight(); ++j) {
				int k = i - width;
				int l = j - height;
				Ingredient ingredient = Ingredient.EMPTY;
				if (k >= 0 && l >= 0 && k < this.getWidth() && l < this.getHeight()) {
					if (p_44174_) {
						ingredient = this.pattern.ingredients().get(this.getWidth() - k - 1 + l * this.getWidth());
					} else {
						ingredient = this.pattern.ingredients().get(k + l * this.getWidth());
					}
				}

				if (!ingredient.test(containerCrafting.getItem(i + j * containerCrafting.getWidth()))) {
					return false;
				}
			}
		}

		return true;
	}
	
	@Override
	public ItemStack assemble(final GunsmithsBenchCraftingContainer craftinv, RegistryAccess registryAccess) {
		ItemStack resultStack = this.result.copy();
		
		if (requiresDesignNotes(resultStack.getItem())) {
			ItemStack item = craftinv.getItem(GunsmithsBenchMenu.NOTES_SLOT);
			if (!(item.getItem() instanceof IDesignNotes))
				return ItemStack.EMPTY;
			
			String designName = IDesignNotes.getDesign(item);
			String resultName = ModRegistryUtil.getKey(resultStack.getItem()).toString();
			if (!designName.equals(resultName))
				return ItemStack.EMPTY;
		}
		
		if (resultStack.getItem() instanceof Firearm)
			((Firearm)this.result.getItem()).initNBTTags(resultStack);
		
		return resultStack;
	}

	public int getWidth() {
		return this.pattern.width();
	}

	public int getHeight() {
		return this.pattern.height();
	}


	@Override
	public RecipeType<?> getType() {
		return ModRecipeTypes.GUNSMITHS_BENCH.get();
	}
}
