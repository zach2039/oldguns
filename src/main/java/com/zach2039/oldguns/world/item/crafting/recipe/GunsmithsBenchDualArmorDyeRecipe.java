package com.zach2039.oldguns.world.item.crafting.recipe;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.zach2039.oldguns.api.equipment.DualColorDyeableItem;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.inventory.GunsmithsBenchCraftingContainer;
import com.zach2039.oldguns.world.item.crafting.GunsmithsBenchRecipe;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

public class GunsmithsBenchDualArmorDyeRecipe implements GunsmithsBenchRecipe {

	private final ResourceLocation id;

	public GunsmithsBenchDualArmorDyeRecipe(ResourceLocation id) {
		this.id = id;
	}

	@Override
	public boolean matches(GunsmithsBenchCraftingContainer container, Level level) {
		ItemStack itemstack = ItemStack.EMPTY;
		List<ItemStack> dyeStacksA = Lists.newArrayList();
		List<ItemStack> dyeStacksB = Lists.newArrayList();

		for(int i = 0; i < container.getContainerSize(); ++i) {
			ItemStack itemstack1 = container.getItem(i);
			if (!itemstack1.isEmpty()) {
				if (itemstack1.getItem() instanceof DyeableLeatherItem) {
					if (!itemstack.isEmpty()) {
						return false;
					}

					itemstack = itemstack1;
				} else {
					if (!(itemstack1.getItem() instanceof DyeItem)) {
						return false;
					}

					if (i < 3) {
						dyeStacksA.add(itemstack1);
					} else if (i > 5 && i < 9) {
						dyeStacksB.add(itemstack1);
					}
				}
			}
		}

		return !itemstack.isEmpty() && (!dyeStacksA.isEmpty() || !dyeStacksB.isEmpty());
	}

	@Override
	public ItemStack assemble(GunsmithsBenchCraftingContainer container) {
		List<DyeItem> dyeStacksA = Lists.newArrayList();
		List<DyeItem> dyeStacksB = Lists.newArrayList();
		ItemStack itemstack = ItemStack.EMPTY;

		for(int i = 0; i < container.getContainerSize(); ++i) {
			ItemStack itemstack1 = container.getItem(i);
			if (!itemstack1.isEmpty()) {
				Item item = itemstack1.getItem();
				if (item instanceof DualColorDyeableItem) {
					if (!itemstack.isEmpty()) {
						return ItemStack.EMPTY;
					}

					itemstack = itemstack1.copy();
				} else {
					if (!(item instanceof DyeItem)) {
						return ItemStack.EMPTY;
					}

					if (i < 3) {
						dyeStacksA.add((DyeItem) item);
					} else if (i > 5 && i < 9) {
						dyeStacksB.add((DyeItem) item);
					}
				}
			}
		}

		if (itemstack.isEmpty() || (dyeStacksA.isEmpty() && dyeStacksB.isEmpty()))
			return ItemStack.EMPTY;
		
		itemstack = DualColorDyeableItem.dyeArmorColorA(itemstack, dyeStacksA);
		itemstack = DualColorDyeableItem.dyeArmorColorB(itemstack, dyeStacksB);
		
		return itemstack;
	}

	@Override
	public ItemStack getResultItem() {
		return ItemStack.EMPTY;
	}
	
	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= 2;
	}
	
	@Override
	public ResourceLocation getId() {
		return this.id;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModCrafting.Recipes.GUNSMITHS_BENCH_SHAPELESS.get();
	}

	public static class Serializer implements RecipeSerializer<GunsmithsBenchDualArmorDyeRecipe> {

		public GunsmithsBenchDualArmorDyeRecipe fromJson(ResourceLocation id, JsonObject jsonObj) {
			return new GunsmithsBenchDualArmorDyeRecipe(id);
		}

		public GunsmithsBenchDualArmorDyeRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
			return new GunsmithsBenchDualArmorDyeRecipe(id);
		}

		public void toNetwork(FriendlyByteBuf p_44401_, GunsmithsBenchDualArmorDyeRecipe recipe) {}
	}

}
