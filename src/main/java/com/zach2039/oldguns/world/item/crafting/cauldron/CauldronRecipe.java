package com.zach2039.oldguns.world.item.crafting.cauldron;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.NotNull;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.init.ModCrafting;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class CauldronRecipe extends CustomRecipe implements ICauldronRecipe {
	
	@NotNull private final ItemStack input;
    @NotNull private final ItemStack output;

    public CauldronRecipe(ResourceLocation id, @NotNull ItemStack input, ItemStack output)
    {
    	super(id);
        this.input = input;
        this.output = output;
    }

	@Override
    public boolean isInput(@NotNull ItemStack stack)
    {
        return this.input.equals(stack, false);
    }
    
    @Override
    public ItemStack getOutput(ItemStack input)
    {
        return isInput(input) ? getOutput().copy() : ItemStack.EMPTY;
    }
    
    public ItemStack getInput()
    {
        return input;
    }

    public ItemStack getOutput()
    {
        return output;
    }

	@Override
	public boolean matches(CraftingContainer pContainer, Level pLevel) {
		return false;
	}

	@Override
	public ItemStack assemble(CraftingContainer pContainer) {
		return getOutput();
	}

	@Override
	public boolean canCraftInDimensions(int pWidth, int pHeight) {
		return true;
	}

	@Nonnull
	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModCrafting.Recipes.CAULDRON.get();
	}
	
	public static class Serializer implements RecipeSerializer<CauldronRecipe> {
		public CauldronRecipe fromJson(ResourceLocation id, JsonObject jsonObj) {
			return null;
		}

		public CauldronRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
			return null;
		}

		public void toNetwork(FriendlyByteBuf buf, CauldronRecipe recipe) {}
	}
}
