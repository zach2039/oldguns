package com.zach2039.oldguns.world.item.crafting.recipe;

import com.zach2039.oldguns.api.firearm.FirearmCondition;
import com.zach2039.oldguns.api.firearm.IFirearm;
import com.zach2039.oldguns.api.firearm.util.FirearmNBTHelper;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.item.crafting.DamageableToolRecipe;
import com.zach2039.oldguns.world.item.tools.RepairKitItem;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;

public class ShapelessVanillaFirearmRepairRecipe extends ShapelessRecipe implements DamageableToolRecipe
{
	public ShapelessVanillaFirearmRepairRecipe(final ResourceLocation id, final String group, final ItemStack recipeOutput, final NonNullList<Ingredient> ingredients) {
		super(id, group, recipeOutput, ingredients);
	}

	@Override
	public ItemStack assemble(final CraftingContainer inv) {
		ItemStack firearmStack = ItemStack.EMPTY;
		ItemStack repairKitStack = ItemStack.EMPTY;
		
		for (int i = 0; i < inv.getContainerSize(); i++) {
			ItemStack stack = inv.getItem(i);
			
			if (stack.getItem() instanceof IFirearm) {				
				if (stack.isDamaged()) {
					firearmStack = stack.copy();
				}
			}
			
			if (stack.getItem() instanceof RepairKitItem) {
				repairKitStack = stack.copy();
			}
			
			if (!firearmStack.isEmpty() && !repairKitStack.isEmpty())
				break;
		}
		
		// Repair firearm by 25%
		if (!firearmStack.isEmpty() && !repairKitStack.isEmpty()) {
			int firearmDamage = firearmStack.getDamageValue();
			int repairAmount = Math.round((float)firearmStack.getMaxDamage() / (float)4);
			firearmStack.setDamageValue(Math.max(0, firearmDamage - repairAmount));
			
			if (FirearmNBTHelper.getNBTTagCondition(firearmStack) == FirearmCondition.BROKEN) {
				FirearmNBTHelper.setNBTTagCondition(firearmStack, FirearmCondition.VERY_POOR);
			}
			((IFirearm)firearmStack.getItem()).initNBTTags(firearmStack);
			
			return firearmStack;
		}
		
		return ItemStack.EMPTY;
	}
	
	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModCrafting.Recipes.FIREARM_REPAIR_SHAPELESS.get();
	}
}

