package com.zach2039.oldguns.world.item.crafting.recipe;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.api.firearm.FirearmCondition;
import com.zach2039.oldguns.api.firearm.Firearm;
import com.zach2039.oldguns.api.firearm.util.FirearmNBTHelper;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.inventory.GunsmithsBenchCraftingContainer;
import com.zach2039.oldguns.world.item.crafting.GunsmithsBenchRecipe;
import com.zach2039.oldguns.world.item.crafting.util.ModRecipeUtil;
import com.zach2039.oldguns.world.item.tools.RepairKitItem;

import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ShapelessGunsmithsBenchFirearmRepairWithKitRecipe extends ShapelessGunsmithsBenchRecipe implements GunsmithsBenchRecipe {

	public ShapelessGunsmithsBenchFirearmRepairWithKitRecipe(ResourceLocation id, String group, ItemStack result, NonNullList<Ingredient> ingredients) {
		super(id, group, result, ingredients);
	}

	@Override
	public ItemStack assemble(final GunsmithsBenchCraftingContainer inv) {
		ItemStack firearmStack = ItemStack.EMPTY;
		ItemStack repairKitStack = ItemStack.EMPTY;
		
		for (int i = 0; i < inv.getContainerSize(); i++) {
			ItemStack stack = inv.getItem(i);
			
			if (stack.getItem() instanceof Firearm) {				
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
			((Firearm)firearmStack.getItem()).initNBTTags(firearmStack);
			
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
		final Player craftingPlayer = ForgeHooks.getCraftingPlayer();

		/*
		 *  At certain times, craftingPlayer might be null, like during PrimalMagicks 
		 *  generateItemAffinityValuesFromIngredients method call. Prevent bad here
		 *  with a null check.
		 */
		if (craftingPlayer != null) {
			Level level = craftingPlayer.getCommandSenderWorld();
			if (stack.hurt(1, level.random, craftingPlayer instanceof ServerPlayer ? (ServerPlayer) craftingPlayer : null)) {
				ForgeEventFactory.onPlayerDestroyItem(craftingPlayer, stack, null);
				return ItemStack.EMPTY;
			}
		}
		
		return stack;
	}
	
	public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<ShapelessGunsmithsBenchFirearmRepairWithKitRecipe> {
		
		@Override
		public ShapelessGunsmithsBenchFirearmRepairWithKitRecipe fromJson(final ResourceLocation recipeID, final JsonObject json) {
			final String group = GsonHelper.getAsString(json, "group", "");
			final NonNullList<Ingredient> ingredients = ModRecipeUtil.parseShapeless(json);
			final ItemStack result = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);

			ShapelessGunsmithsBenchFirearmRepairWithKitRecipe recipeFromJson = new ShapelessGunsmithsBenchFirearmRepairWithKitRecipe(recipeID, group, result, ingredients);
			
			return recipeFromJson;
		}

		@Override
		public ShapelessGunsmithsBenchFirearmRepairWithKitRecipe fromNetwork(final ResourceLocation recipeID, final FriendlyByteBuf buffer) {
			final String group = buffer.readUtf(Short.MAX_VALUE);
			final int numIngredients = buffer.readVarInt();
			final NonNullList<Ingredient> ingredients = NonNullList.withSize(numIngredients, Ingredient.EMPTY);

			for (int j = 0; j < ingredients.size(); ++j) {
				ingredients.set(j, Ingredient.fromNetwork(buffer));
			}

			final ItemStack result = buffer.readItem();

			return new ShapelessGunsmithsBenchFirearmRepairWithKitRecipe(recipeID, group, result, ingredients);
		}

		@Override
		public void toNetwork(final FriendlyByteBuf buffer, final ShapelessGunsmithsBenchFirearmRepairWithKitRecipe recipe) {
			buffer.writeUtf(recipe.getGroup());
			buffer.writeVarInt(recipe.getIngredients().size());

			for (final Ingredient ingredient : recipe.getIngredients()) {
				ingredient.toNetwork(buffer);
			}

			buffer.writeItem(recipe.getResultItem());
		}
	}
	
	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModCrafting.Recipes.GUNSMITHS_BENCH_FIREARM_REPAIR_WITH_KIT_SHAPELESS.get();
	}
}
