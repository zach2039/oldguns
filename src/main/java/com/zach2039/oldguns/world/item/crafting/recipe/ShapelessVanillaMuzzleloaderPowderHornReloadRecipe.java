package com.zach2039.oldguns.world.item.crafting.recipe;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.ammo.Ammo;
import com.zach2039.oldguns.api.ammo.FirearmAmmo;
import com.zach2039.oldguns.api.firearm.Firearm;
import com.zach2039.oldguns.api.firearm.FirearmCondition;
import com.zach2039.oldguns.api.firearm.util.FirearmNBTHelper;
import com.zach2039.oldguns.api.firearm.util.PowderHornNBTHelper;
import com.zach2039.oldguns.capability.firearmempty.FirearmEmptyCapability;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.item.crafting.util.ModRecipeUtil;
import com.zach2039.oldguns.world.item.firearm.FirearmItem;
import com.zach2039.oldguns.world.item.tools.PowderHornItem;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.CraftingHelper;

public class ShapelessVanillaMuzzleloaderPowderHornReloadRecipe extends ShapelessRecipe
{
	private final boolean isSimple;
	private final TagKey<Item> powderTag;
	private final int powderAmount;
	
	public ShapelessVanillaMuzzleloaderPowderHornReloadRecipe(final ResourceLocation id, final String group, final ItemStack recipeOutput, final TagKey<Item> powderTag, final int powderAmount, final NonNullList<Ingredient> ingredients) {
		super(id, group, recipeOutput, ingredients);
		this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
		this.powderTag = powderTag;
		this.powderAmount = powderAmount;
	}
	
	public int getPowderAmount() {
		return powderAmount;
	}
	
	public TagKey<Item> getPowderTag() {
		return powderTag;
	}
	
	@Override
	public NonNullList<ItemStack> getRemainingItems(final CraftingContainer inv) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);

		for (int i = 0; i < remainingItems.size(); ++i) {
			final ItemStack itemstack = inv.getItem(i);

			if (!itemstack.isEmpty() && (itemstack.getItem() instanceof PowderHornItem)) {
				ItemStack copy = itemstack.copy();
				remainingItems.set(i, damageItem(copy, powderAmount));
				if (copy.getDamageValue() == copy.getMaxDamage()) {
					PowderHornNBTHelper.setPowderStack(copy, ItemStack.EMPTY);
				}
			} else {
				remainingItems.set(i, ForgeHooks.getCraftingRemainingItem(itemstack));
			}
		}

		return remainingItems;
	}
	
	private ItemStack damageItem(final ItemStack stack, final int amount) {
		final Player craftingPlayer = ForgeHooks.getCraftingPlayer();

		Level level = craftingPlayer.getCommandSenderWorld();
		if (stack.hurt(amount, level.random, craftingPlayer instanceof ServerPlayer ? (ServerPlayer) craftingPlayer : null)) {
			stack.setDamageValue(stack.getMaxDamage());
			return stack;
		}

		return stack;
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
				if (!(itemstack.getItem() instanceof Firearm) && !(itemstack.getItem() instanceof PowderHornItem)) {
					++i;
		            if (isSimple)
		            stackedcontents.accountStack(itemstack, 1);
		            else inputs.add(itemstack);
				} else if (itemstack.getItem() instanceof PowderHornItem) {					
					if (
							PowderHornNBTHelper.peekPowderCount(itemstack) >= powderAmount &&
							PowderHornNBTHelper.hasPowderOfTag(itemstack, powderTag)
						) {
						++i;
			            if (isSimple)
			            stackedcontents.accountStack(itemstack, 1);
			            else inputs.add(itemstack);
					}
				} else {
					if (
							FirearmNBTHelper.getNBTTagCondition(itemstack) != FirearmCondition.BROKEN && 
							FirearmNBTHelper.peekNBTTagAmmoCount(itemstack) < ((Firearm)itemstack.getItem()).getAmmoCapacity()
						) {
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
		ItemStack powderHornStack = ItemStack.EMPTY;
		
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
		
		/* Find powder horn and copy to output. */
		for (int i = 0; i < inv.getContainerSize(); i++)
		{
			ItemStack stack = inv.getItem(i);
			
			/* If item is a ammo instance, set input ammo stack and break. */
			if (stack.getItem() instanceof PowderHornItem)
			{
				powderHornStack = stack.copy();
				break;
			}
		}
		
		/* Increase ammo count by one on stack, if all itemstacks were found and powder horn is of proper powder type. */
		if (!firearmStack.isEmpty() && !ammoStack.isEmpty() && !powderHornStack.isEmpty())
		{
			FirearmNBTHelper.pushNBTTagAmmo(firearmStack, ammoStack);
			
			FirearmEmptyCapability.updateFirearmEmpty(firearmStack);
			
			return firearmStack;
		}
		
		return ItemStack.EMPTY;
	}

	@Override
	@Nonnull
	public ItemStack getResultItem()
	{
		ItemStack outputStack = super.getResultItem().copy();
		
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
	
	public static class Serializer implements RecipeSerializer<ShapelessVanillaMuzzleloaderPowderHornReloadRecipe> {
		@Override
		public ShapelessVanillaMuzzleloaderPowderHornReloadRecipe fromJson(final ResourceLocation recipeID, final JsonObject json) {
			final String group = GsonHelper.getAsString(json, "group", "");
			final NonNullList<Ingredient> ingredients = ModRecipeUtil.parseShapeless(json);
			final ItemStack result = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true);
			final TagKey<Item> powderTag = ItemTags.create(new ResourceLocation(OldGuns.MODID, GsonHelper.getAsJsonObject(json, "powderTag").getAsString()));
			final int powderAmount = GsonHelper.getAsJsonObject(json, "powderAmount").getAsInt();

			return new ShapelessVanillaMuzzleloaderPowderHornReloadRecipe(recipeID, group, result, powderTag, powderAmount, ingredients);
		}

		@Override
		public ShapelessVanillaMuzzleloaderPowderHornReloadRecipe fromNetwork(final ResourceLocation recipeID, final FriendlyByteBuf buffer) {
			final String group = buffer.readUtf(Short.MAX_VALUE);
			final int numIngredients = buffer.readVarInt();
			final NonNullList<Ingredient> ingredients = NonNullList.withSize(numIngredients, Ingredient.EMPTY);

			for (int j = 0; j < ingredients.size(); ++j) {
				ingredients.set(j, Ingredient.fromNetwork(buffer));
			}

			final ItemStack result = buffer.readItem();
			final TagKey<Item> powderTag = ItemTags.create(new ResourceLocation(OldGuns.MODID, buffer.readUtf()));
			final int powderAmount = buffer.readInt();
			
			return new ShapelessVanillaMuzzleloaderPowderHornReloadRecipe(recipeID, group, result, powderTag, powderAmount, ingredients);
		}

		@Override
		public void toNetwork(final FriendlyByteBuf buffer, final ShapelessVanillaMuzzleloaderPowderHornReloadRecipe recipe) {
			buffer.writeUtf(recipe.getGroup());
			buffer.writeVarInt(recipe.getIngredients().size());

			for (final Ingredient ingredient : recipe.getIngredients()) {
				ingredient.toNetwork(buffer);
			}

			buffer.writeItem(recipe.getResultItem());
			buffer.writeUtf(recipe.getPowderTag().location().getPath());
			buffer.writeInt(recipe.getPowderAmount());
		}
	}
}

