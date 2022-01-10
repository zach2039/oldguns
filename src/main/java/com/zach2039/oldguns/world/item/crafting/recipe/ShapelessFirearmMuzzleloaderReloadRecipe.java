package com.zach2039.oldguns.world.item.crafting.recipe;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.api.ammo.IFirearmAmmo;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmCondition;
import com.zach2039.oldguns.api.firearm.IFirearm;
import com.zach2039.oldguns.api.firearm.util.FirearmNBTHelper;
import com.zach2039.oldguns.capability.firearmempty.FirearmEmptyCapability;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.init.ModItems;
import com.zach2039.oldguns.world.item.crafting.util.ModRecipeUtil;
import com.zach2039.oldguns.world.item.firearm.FirearmItem;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ShapelessFirearmMuzzleloaderReloadRecipe extends ShapelessRecipe
{
	private final boolean isSimple;
	
	private ShapelessFirearmMuzzleloaderReloadRecipe(final ResourceLocation id, final String group, final ItemStack recipeOutput, final NonNullList<Ingredient> ingredients) {
		super(id, group, recipeOutput, ingredients);
		this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
	}
	
	@Override
	public boolean matches(CraftingContainer p_44262_, Level p_44263_) {
		StackedContents stackedcontents = new StackedContents();
		java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
		int i = 0;

		for(int j = 0; j < p_44262_.getContainerSize(); ++j) {
			ItemStack itemstack = p_44262_.getItem(j);
			// Check if items are valid, but check status of firearms as well, since we don't want to try and reload a broken firearm
			if (!itemstack.isEmpty()) {
				if (!(itemstack.getItem() instanceof IFirearm)) {
					++i;
		            if (isSimple)
		            stackedcontents.accountStack(itemstack, 1);
		            else inputs.add(itemstack);
				} else {
					if (
							FirearmNBTHelper.getNBTTagCondition(itemstack) != FirearmCondition.BROKEN && 
							FirearmNBTHelper.peekNBTTagAmmoCount(itemstack) < ((IFirearm)itemstack.getItem()).getAmmoCapacity()
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
	public ItemStack assemble(final CraftingContainer inv)
	{
		/* Required itemstacks for proper nbt results. */
		ItemStack firearmStack = ItemStack.EMPTY;
		ItemStack ammoStack = ItemStack.EMPTY;
		
		/* Find input firearm and copy to output. */
		for (int i = 0; i < inv.getContainerSize(); i++)
		{
			ItemStack stack = inv.getItem(i);
			
			/* If item is a firearm instance and can reload, set output stack and break. */
			if (stack.getItem() instanceof IFirearm)
			{
				IFirearm firearmItem = (IFirearm) stack.getItem();
				
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
			if (stack.getItem() instanceof IFirearmAmmo)
			{
				ammoStack = stack.copy();
				break;
			}
		}
		
		/* Increase ammo count by one on stack, if all itemstacks were found. */
		if (!firearmStack.isEmpty() && !ammoStack.isEmpty())
		{
			FirearmNBTHelper.pushNBTTagAmmo(firearmStack, ammoStack);
			
			FirearmEmptyCapability.update(null, firearmStack);
			
			return firearmStack;
		}
		
		return ItemStack.EMPTY;
	}

	@Override
    @Nonnull
    public ItemStack getResultItem()
	{
		ItemStack outputStack = super.getResultItem();
		List<ItemStack> dummyAmmoStackList = new ArrayList<ItemStack>();
		dummyAmmoStackList.add(new ItemStack(ModItems.SMALL_IRON_MUSKET_BALL.get()));
		
		((FirearmItem)outputStack.getItem()).initNBTTags(outputStack);
    	
		FirearmNBTHelper.setNBTTagMagazineStack(outputStack, dummyAmmoStackList);
		FirearmNBTHelper.refreshFirearmCondition(outputStack);
		FirearmEmptyCapability.update(null, outputStack);
		
		return outputStack;
	}
	
	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModCrafting.Recipes.FIREARM_MUZZLELOADER_RELOAD_SHAPELESS.get();
	}
	
	public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<ShapelessFirearmMuzzleloaderReloadRecipe> {
		@Override
		public ShapelessFirearmMuzzleloaderReloadRecipe fromJson(final ResourceLocation recipeID, final JsonObject json) {
			final String group = GsonHelper.getAsString(json, "group", "");
			final NonNullList<Ingredient> ingredients = ModRecipeUtil.parseShapeless(json);
			final ItemStack result = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);

			return new ShapelessFirearmMuzzleloaderReloadRecipe(recipeID, group, result, ingredients);
		}

		@Override
		public ShapelessFirearmMuzzleloaderReloadRecipe fromNetwork(final ResourceLocation recipeID, final FriendlyByteBuf buffer) {
			final String group = buffer.readUtf(Short.MAX_VALUE);
			final int numIngredients = buffer.readVarInt();
			final NonNullList<Ingredient> ingredients = NonNullList.withSize(numIngredients, Ingredient.EMPTY);

			for (int j = 0; j < ingredients.size(); ++j) {
				ingredients.set(j, Ingredient.fromNetwork(buffer));
			}

			final ItemStack result = buffer.readItem();

			return new ShapelessFirearmMuzzleloaderReloadRecipe(recipeID, group, result, ingredients);
		}

		@Override
		public void toNetwork(final FriendlyByteBuf buffer, final ShapelessFirearmMuzzleloaderReloadRecipe recipe) {
			buffer.writeUtf(recipe.getGroup());
			buffer.writeVarInt(recipe.getIngredients().size());

			for (final Ingredient ingredient : recipe.getIngredients()) {
				ingredient.toNetwork(buffer);
			}

			buffer.writeItem(recipe.getResultItem());
		}
	}
}

