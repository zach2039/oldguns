package com.zach2039.oldguns.world.item.crafting.recipe;

import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.zach2039.oldguns.api.ammo.Ammo;
import com.zach2039.oldguns.api.ammo.FirearmAmmo;
import com.zach2039.oldguns.api.firearm.Firearm;
import com.zach2039.oldguns.api.firearm.FirearmCondition;
import com.zach2039.oldguns.api.firearm.util.FirearmNBTHelper;
import com.zach2039.oldguns.capability.firearmempty.FirearmEmptyCapability;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.item.crafting.util.ModRecipeUtil;
import com.zach2039.oldguns.world.item.firearm.FirearmItem;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.crafting.CraftingHelper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ShapelessVanillaMuzzleloaderReloadRecipe extends ShapelessRecipe
{
	private final ItemStack result;
	private final boolean isSimple;
	
	public ShapelessVanillaMuzzleloaderReloadRecipe(final ResourceLocation id, final String group, final ItemStack result, final NonNullList<Ingredient> ingredients) {
		super(group, CraftingBookCategory.MISC, result, ingredients);
		this.result = result;
		this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
	}
	
	@Override
	public boolean matches(CraftingContainer craftingContainer, Level level) {
		StackedContents stackedcontents = new StackedContents();
		java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
		int i = 0;

		for(int j = 0; j < craftingContainer.getContainerSize(); ++j) {
			ItemStack itemstack = craftingContainer.getItem(j);
			// Check if items are valid, but check status of firearms as well, since we don't want to try and reload a broken firearm
			if (!itemstack.isEmpty()) {
				if (!(itemstack.getItem() instanceof Firearm)) {
					++i;
		            if (isSimple)
		            stackedcontents.accountStack(itemstack, 1);
		            else inputs.add(itemstack);
				} else {
					if (
							FirearmNBTHelper.getNBTTagCondition(itemstack) != FirearmCondition.BROKEN && 
							FirearmNBTHelper.peekNBTTagAmmoCount(itemstack) < ((Firearm)itemstack.getItem()).getAmmoCapacity()
						)
					{
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
	public ItemStack assemble(final CraftingContainer inv, RegistryAccess registryAccess)
	{
		/* Required itemstacks for proper nbt results. */
		ItemStack firearmStack = ItemStack.EMPTY;
		ItemStack ammoStack = ItemStack.EMPTY;
		
		/* Find input firearm and copy to output. */
		for (int i = 0; i < inv.getContainerSize(); i++)
		{
			ItemStack stack = inv.getItem(i);
			
			/* If item is a firearm instance and can reload, set output stack and break. */
			if (stack.getItem() instanceof Firearm)
			{
				Firearm firearmItem = (Firearm) stack.getItem();
				
				if (firearmItem.canReload(stack) && FirearmNBTHelper.getNBTTagCondition(stack) != FirearmCondition.BROKEN)
				{
					firearmStack = stack.copy();
					break;
				}
			}
		}
		
		/* Find input ammo and copy to output. */
		for (int i = 0; i < inv.getContainerSize(); i++)
		{
			ItemStack stack = inv.getItem(i);
			
			/* If item is a ammo instance, set input ammo stack and break. */
			if (stack.getItem() instanceof FirearmAmmo)
			{
				ammoStack = stack.copy();
				break;
			}
		}
		
		/* Increase ammo count by one on stack, if all itemstacks were found. */
		if (!firearmStack.isEmpty() && !ammoStack.isEmpty())
		{
			FirearmNBTHelper.pushNBTTagAmmo(firearmStack, ammoStack);
			
			FirearmEmptyCapability.updateFirearmEmpty(firearmStack);
			
			return firearmStack;
		}
		
		return ItemStack.EMPTY;
	}

	@Override
	@Nonnull
	public ItemStack getResultItem(RegistryAccess registryAccess)
	{
		ItemStack outputStack = result.copy();
		
		if (outputStack.getItem() instanceof FirearmItem) {
			FirearmItem firearmItem = (FirearmItem)outputStack.getItem();
			
			List<ItemStack> dummyAmmoStackList = new ArrayList<ItemStack>();
			
			this.getIngredients().forEach((ing) -> {
				if (ing.getItems()[0].getItem() instanceof Ammo) {
					dummyAmmoStackList.add(ing.getItems()[0]);
					return;
				}
			});
			
			if (dummyAmmoStackList.isEmpty()) {
				dummyAmmoStackList.add(firearmItem.getDefaultProjectileForFirearm());
			}
	
			FirearmNBTHelper.setNBTTagMagazineStack(outputStack, dummyAmmoStackList);
	
			((FirearmItem)outputStack.getItem()).initNBTTags(outputStack);
	
			FirearmEmptyCapability.updateFirearmEmpty(outputStack);
		}

		return outputStack;
	}
	
	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModCrafting.Recipes.FIREARM_MUZZLELOADER_RELOAD_SHAPELESS.get();
	}
	
	public static class Serializer implements RecipeSerializer<ShapelessVanillaMuzzleloaderReloadRecipe> {
		@Override
		public ShapelessVanillaMuzzleloaderReloadRecipe fromJson(final ResourceLocation recipeID, final JsonObject json) {
			final String group = GsonHelper.getAsString(json, "group", "");
			final NonNullList<Ingredient> ingredients = ModRecipeUtil.parseShapeless(json);
			final ItemStack result = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);

			return new ShapelessVanillaMuzzleloaderReloadRecipe(recipeID, group, result, ingredients);
		}

		@Override
		public ShapelessVanillaMuzzleloaderReloadRecipe fromNetwork(final FriendlyByteBuf buffer) {
			final String group = buffer.readUtf(Short.MAX_VALUE);
			final int numIngredients = buffer.readVarInt();
			final NonNullList<Ingredient> ingredients = NonNullList.withSize(numIngredients, Ingredient.EMPTY);

			for (int j = 0; j < ingredients.size(); ++j) {
				ingredients.set(j, Ingredient.fromNetwork(buffer));
			}

			final ItemStack result = buffer.readItem();

			return new ShapelessVanillaMuzzleloaderReloadRecipe(recipeID, group, result, ingredients);
		}

		@Override
		public Codec<ShapelessVanillaMuzzleloaderReloadRecipe> codec() {
			return null;
		}

		@Override
		public void toNetwork(final FriendlyByteBuf buffer, final ShapelessVanillaMuzzleloaderReloadRecipe recipe) {
			buffer.writeUtf(recipe.getGroup());
			buffer.writeVarInt(recipe.getIngredients().size());

			for (final Ingredient ingredient : recipe.getIngredients()) {
				ingredient.toNetwork(buffer);
			}

			buffer.writeItem(recipe.result);
		}
	}
}

