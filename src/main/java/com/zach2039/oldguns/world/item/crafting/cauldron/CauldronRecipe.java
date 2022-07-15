package com.zach2039.oldguns.world.item.crafting.cauldron;

import javax.annotation.Nonnull;

import org.jetbrains.annotations.NotNull;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.init.ModCrafting;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class CauldronRecipe extends CustomRecipe implements ICauldronRecipe {
	
	@NotNull private final ItemStack input;
    @NotNull private final ItemStack output;
    @NotNull private final Fluid fluid;

    public CauldronRecipe(ResourceLocation id, @NotNull ItemStack input, ItemStack output, Fluid fluid)
    {
    	super(id);
        this.input = input;
        this.output = output;
        this.fluid = fluid;
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
		return true;
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

	@Override
	public Fluid getFluid() {
		return fluid;
	}
}
