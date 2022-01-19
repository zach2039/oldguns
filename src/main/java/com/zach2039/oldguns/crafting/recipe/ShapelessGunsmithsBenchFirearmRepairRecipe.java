package com.zach2039.oldguns.crafting.recipe;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmCondition;
import com.zach2039.oldguns.api.firearm.IFirearm;
import com.zach2039.oldguns.api.firearm.util.FirearmNBTHelper;
import com.zach2039.oldguns.crafting.util.ModRecipeUtil;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.inventory.GunsmithsBenchCraftingContainer;
import com.zach2039.oldguns.item.tools.RepairKitItem;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ShapelessGunsmithsBenchFirearmRepairRecipe extends ShapelessGunsmithsBenchRecipe implements GunsmithsBenchRecipe {

	public ShapelessGunsmithsBenchFirearmRepairRecipe(ResourceLocation id, String group, ItemStack result, NonNullList<Ingredient> ingredients) {
		super(id, group, result, ingredients);
	}

	@Override
	public ItemStack assemble(final GunsmithsBenchCraftingContainer inv) {
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
	public NonNullList<ItemStack> getRemainingItems(final GunsmithsBenchCraftingContainer inv) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);

		for (int i = 0; i < remainingItems.size(); ++i) {
			final ItemStack itemstack = inv.getItem(i);

			if (!itemstack.isEmpty() && (itemstack.getItem() instanceof RepairKitItem)) {
				remainingItems.set(i, damageItem(itemstack.copy()));
			} else {
				remainingItems.set(i, ForgeHooks.getContainerItem(itemstack));
			}
		}

		return remainingItems;
	}
	
	private ItemStack damageItem(final ItemStack stack) {
		final PlayerEntity craftingPlayer = ForgeHooks.getCraftingPlayer();
		
		World level = craftingPlayer.getCommandSenderWorld();
		if (stack.hurt(1, level.random, craftingPlayer instanceof ServerPlayerEntity ? (ServerPlayerEntity) craftingPlayer : null)) {
			ForgeEventFactory.onPlayerDestroyItem(craftingPlayer, stack, null);
			return ItemStack.EMPTY;
		}
		
		return stack;
	}
	
	public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<ShapelessGunsmithsBenchFirearmRepairRecipe> {
		
		@Override
		public ShapelessGunsmithsBenchFirearmRepairRecipe fromJson(final ResourceLocation recipeID, final JsonObject json) {
			final String group = JSONUtils.getAsString(json, "group", "");
			final NonNullList<Ingredient> ingredients = ModRecipeUtil.parseShapeless(json);
			final ItemStack result = CraftingHelper.getItemStack(JSONUtils.getAsJsonObject(json, "result"), true);

			ShapelessGunsmithsBenchFirearmRepairRecipe recipeFromJson = new ShapelessGunsmithsBenchFirearmRepairRecipe(recipeID, group, result, ingredients);
			
			return recipeFromJson;
		}

		@Override
		public ShapelessGunsmithsBenchFirearmRepairRecipe fromNetwork(final ResourceLocation recipeID, final PacketBuffer buffer) {
			final String group = buffer.readUtf(Short.MAX_VALUE);
			final int numIngredients = buffer.readVarInt();
			final NonNullList<Ingredient> ingredients = NonNullList.withSize(numIngredients, Ingredient.EMPTY);

			for (int j = 0; j < ingredients.size(); ++j) {
				ingredients.set(j, Ingredient.fromNetwork(buffer));
			}

			final ItemStack result = buffer.readItem();

			return new ShapelessGunsmithsBenchFirearmRepairRecipe(recipeID, group, result, ingredients);
		}

		@Override
		public void toNetwork(final PacketBuffer buffer, final ShapelessGunsmithsBenchFirearmRepairRecipe recipe) {
			buffer.writeUtf(recipe.getGroup());
			buffer.writeVarInt(recipe.getIngredients().size());

			for (final Ingredient ingredient : recipe.getIngredients()) {
				ingredient.toNetwork(buffer);
			}

			buffer.writeItem(recipe.getResultItem());
		}
	}
	
	@Override
	public IRecipeSerializer<?> getSerializer() {
		return ModCrafting.Recipes.GUNSMITHS_BENCH_FIREARM_REPAIR_SHAPELESS.get();
	}
}
