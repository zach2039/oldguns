package com.zach2039.oldguns.world.item.crafting.recipe;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmCondition;
import com.zach2039.oldguns.api.firearm.IFirearm;
import com.zach2039.oldguns.api.firearm.util.FirearmNBTHelper;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.item.crafting.util.ModRecipeUtil;
import com.zach2039.oldguns.world.item.tools.RepairKitItem;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ShapelessFirearmRepairRecipe extends ShapelessRecipe
{
	private final boolean isSimple;
	
	private ShapelessFirearmRepairRecipe(final ResourceLocation id, final String group, final ItemStack recipeOutput, final NonNullList<Ingredient> ingredients) {
		super(id, group, recipeOutput, ingredients);
		this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
	}
	
	private ItemStack damageRepairKit(final ItemStack stack) {
		final Player craftingPlayer = ForgeHooks.getCraftingPlayer();
		
		Level level = craftingPlayer.getCommandSenderWorld();
		if (stack.hurt(1, level.random, craftingPlayer instanceof ServerPlayer ? (ServerPlayer) craftingPlayer : null)) {
			ForgeEventFactory.onPlayerDestroyItem(craftingPlayer, stack, null);
			return ItemStack.EMPTY;
		}
		
		return stack;
	}
	
	@Override
	public NonNullList<ItemStack> getRemainingItems(final CraftingContainer inv) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);

		for (int i = 0; i < remainingItems.size(); ++i) {
			final ItemStack itemstack = inv.getItem(i);

			if (!itemstack.isEmpty() && itemstack.getItem() instanceof RepairKitItem) {
				remainingItems.set(i, damageRepairKit(itemstack.copy()));
			} else {
				remainingItems.set(i, ForgeHooks.getContainerItem(itemstack));
			}
		}

		return remainingItems;
	}
	
	@Override
	public boolean matches(CraftingContainer p_44262_, Level p_44263_) {
		StackedContents stackedcontents = new StackedContents();
		java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
		int i = 0;

		for(int j = 0; j < p_44262_.getContainerSize(); ++j) {
			ItemStack itemstack = p_44262_.getItem(j);			
			if (!itemstack.isEmpty()) {
				if (!(itemstack.getItem() instanceof IFirearm)) {
					++i;
		            if (isSimple)
		            stackedcontents.accountStack(itemstack, 1);
		            else inputs.add(itemstack);
				} else {
					if (itemstack.isDamaged()) {
						++i;
			            if (isSimple)
			            stackedcontents.accountStack(itemstack, 1);
			            else inputs.add(itemstack);
					}
				}
			}
		}

		return i == this.getIngredients().size() && (isSimple ? stackedcontents.canCraft(this, (IntList)null) : net.minecraftforge.common.util.RecipeMatcher.findMatches(inputs,  this.getIngredients()) != null);
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
	
	public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<ShapelessFirearmRepairRecipe> {
		@Override
		public ShapelessFirearmRepairRecipe fromJson(final ResourceLocation recipeID, final JsonObject json) {
			final String group = GsonHelper.getAsString(json, "group", "");
			final NonNullList<Ingredient> ingredients = ModRecipeUtil.parseShapeless(json);
			final ItemStack result = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);

			return new ShapelessFirearmRepairRecipe(recipeID, group, result, ingredients);
		}

		@Override
		public ShapelessFirearmRepairRecipe fromNetwork(final ResourceLocation recipeID, final FriendlyByteBuf buffer) {
			final String group = buffer.readUtf(Short.MAX_VALUE);
			final int numIngredients = buffer.readVarInt();
			final NonNullList<Ingredient> ingredients = NonNullList.withSize(numIngredients, Ingredient.EMPTY);

			for (int j = 0; j < ingredients.size(); ++j) {
				ingredients.set(j, Ingredient.fromNetwork(buffer));
			}

			final ItemStack result = buffer.readItem();

			return new ShapelessFirearmRepairRecipe(recipeID, group, result, ingredients);
		}

		@Override
		public void toNetwork(final FriendlyByteBuf buffer, final ShapelessFirearmRepairRecipe recipe) {
			buffer.writeUtf(recipe.getGroup());
			buffer.writeVarInt(recipe.getIngredients().size());

			for (final Ingredient ingredient : recipe.getIngredients()) {
				ingredient.toNetwork(buffer);
			}

			buffer.writeItem(recipe.getResultItem());
		}
	}
}

