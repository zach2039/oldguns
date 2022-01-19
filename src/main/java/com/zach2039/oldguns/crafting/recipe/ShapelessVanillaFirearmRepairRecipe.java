package com.zach2039.oldguns.crafting.recipe;

import com.zach2039.oldguns.api.firearm.FirearmType.FirearmCondition;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.zach2039.oldguns.api.firearm.IFirearm;
import com.zach2039.oldguns.api.firearm.util.FirearmNBTHelper;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.item.tools.RepairKitItem;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.item.crafting.ShapelessRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;


public class ShapelessVanillaFirearmRepairRecipe extends ShapelessRecipe implements DamageableToolRecipe
{
	static int MAX_WIDTH = 3;
	static int MAX_HEIGHT = 3;
	
	public ShapelessVanillaFirearmRepairRecipe(final ResourceLocation id, final String group, final ItemStack recipeOutput, final NonNullList<Ingredient> ingredients) {
		super(id, group, recipeOutput, ingredients);
	}

	@Override
	public ItemStack assemble(final CraftingInventory inv) {
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
	public IRecipeSerializer<?> getSerializer() {
		return ModCrafting.Recipes.FIREARM_REPAIR_SHAPELESS.get();
	}
	
	public static class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ShapelessVanillaFirearmRepairRecipe> {

	      public ShapelessVanillaFirearmRepairRecipe fromJson(ResourceLocation p_199425_1_, JsonObject p_199425_2_) {
	         String s = JSONUtils.getAsString(p_199425_2_, "group", "");
	         NonNullList<Ingredient> nonnulllist = itemsFromJson(JSONUtils.getAsJsonArray(p_199425_2_, "ingredients"));
	         if (nonnulllist.isEmpty()) {
	            throw new JsonParseException("No ingredients for shapeless recipe");
	         } else if (nonnulllist.size() > MAX_WIDTH * MAX_HEIGHT) {
	            throw new JsonParseException("Too many ingredients for shapeless recipe the max is " + (MAX_WIDTH * MAX_HEIGHT));
	         } else {
	            ItemStack itemstack = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(p_199425_2_, "result"));
	            return new ShapelessVanillaFirearmRepairRecipe(p_199425_1_, s, itemstack, nonnulllist);
	         }
	      }

	      private static NonNullList<Ingredient> itemsFromJson(JsonArray p_199568_0_) {
	         NonNullList<Ingredient> nonnulllist = NonNullList.create();

	         for(int i = 0; i < p_199568_0_.size(); ++i) {
	            Ingredient ingredient = Ingredient.fromJson(p_199568_0_.get(i));
	            if (!ingredient.isEmpty()) {
	               nonnulllist.add(ingredient);
	            }
	         }

	         return nonnulllist;
	      }

	      public ShapelessVanillaFirearmRepairRecipe fromNetwork(ResourceLocation p_199426_1_, PacketBuffer p_199426_2_) {
	         String s = p_199426_2_.readUtf(32767);
	         int i = p_199426_2_.readVarInt();
	         NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);

	         for(int j = 0; j < nonnulllist.size(); ++j) {
	            nonnulllist.set(j, Ingredient.fromNetwork(p_199426_2_));
	         }

	         ItemStack itemstack = p_199426_2_.readItem();
	         return new ShapelessVanillaFirearmRepairRecipe(p_199426_1_, s, itemstack, nonnulllist);
	      }

	      public void toNetwork(PacketBuffer p_199427_1_, ShapelessVanillaFirearmRepairRecipe p_199427_2_) {
	         p_199427_1_.writeUtf(p_199427_2_.getGroup());
	         p_199427_1_.writeVarInt(p_199427_2_.getIngredients().size());

	         for(Ingredient ingredient : p_199427_2_.getIngredients()) {
	            ingredient.toNetwork(p_199427_1_);
	         }

	         p_199427_1_.writeItem(p_199427_2_.getResultItem());
	      }
	   }
}

