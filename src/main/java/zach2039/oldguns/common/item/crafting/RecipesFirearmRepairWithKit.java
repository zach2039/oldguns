package zach2039.oldguns.common.item.crafting;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.item.crafting.util.RecipeUtil;
import zach2039.oldguns.common.item.firearm.ItemFirearm;
import zach2039.oldguns.common.item.tools.ItemRepairKit;
import zach2039.oldguns.common.item.util.FirearmNBTHelper;
import zach2039.oldguns.common.item.util.FirearmType.FirearmCondition;

public class RecipesFirearmRepairWithKit extends ShapelessOreRecipe
{
	/**
	 * Default constructor.
	 */
	public RecipesFirearmRepairWithKit(@Nullable final ResourceLocation group, final NonNullList<Ingredient> input, final ItemStack result) {
		super(group, input, result);
	}
	
	@Override
	public boolean isDynamic()
	{
		return true;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv)
	{
		/* Required itemstacks for proper nbt results. */
		ItemStack firearmStack = ItemStack.EMPTY;
		ItemStack repairKitStack = ItemStack.EMPTY;
		
		/* Find input firearm and copy to output. */
		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			ItemStack stack = inv.getStackInSlot(i);
			
			/* If item is a firearm instance and can reload, set output stack and break. */
			if (stack.getItem() instanceof ItemFirearm)
			{
				ItemFirearm firearmItem = (ItemFirearm) stack.getItem();
				
				if (firearmItem.getDamage(stack) != 0)
				{
					firearmStack = stack.copy();
					break;
				}
			}
		}
		
		/* Find input repair and copy to output. */
		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			ItemStack stack = inv.getStackInSlot(i);
			
			/* If item is a ammo instance, set input ammo stack and break. */
			if (stack.getItem() instanceof ItemRepairKit)
			{
				repairKitStack = stack.copy();
				break;
			}
		}
		
		/* Repair firearm by 25% of max durability. */
		if (!firearmStack.isEmpty() && !repairKitStack.isEmpty())
		{
			int damage = firearmStack.getItemDamage();
			int repairAmount = Math.round((float)firearmStack.getMaxDamage() / (float)4);
			firearmStack.setItemDamage(Math.max(0, damage - repairAmount));
			/* Restore condition and update for durability, if repaired. */
			if (FirearmNBTHelper.getNBTTagCondition(firearmStack) == FirearmCondition.BROKEN)
			{
				FirearmNBTHelper.setNBTTagCondition(firearmStack, FirearmCondition.VERY_POOR);
				FirearmNBTHelper.refreshFirearmCondition(firearmStack);
			}
		}
		
		return firearmStack;
	}

	private ItemStack damageRepairKit(final ItemStack stack)
	{
		final EntityPlayer craftingPlayer = ForgeHooks.getCraftingPlayer();
		if (stack.attemptDamageItem(1, craftingPlayer.getRNG(), craftingPlayer instanceof EntityPlayerMP ? (EntityPlayerMP) craftingPlayer : null)) {
			ForgeEventFactory.onPlayerDestroyItem(craftingPlayer, stack, null);
			return ItemStack.EMPTY;
		}

		return stack;
	}
	
	@Override
	public NonNullList<ItemStack> getRemainingItems(final InventoryCrafting inventoryCrafting) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inventoryCrafting.getSizeInventory(), ItemStack.EMPTY);

		/* Find and damage/destroy repair kit on use. */
		for (int i = 0; i < remainingItems.size(); ++i) 
		{
			final ItemStack itemstack = inventoryCrafting.getStackInSlot(i);

			if (!itemstack.isEmpty() && itemstack.getItem() instanceof ItemRepairKit)
			{
				remainingItems.set(i, damageRepairKit(itemstack.copy()));
			}
			else
			{
				remainingItems.set(i, ForgeHooks.getContainerItem(itemstack));
			}
		}

		return remainingItems;
	}
	
	@Override
	public String getGroup()
	{
		return group == null ? "" : group.toString();
	}
	
	public static class Factory implements IRecipeFactory
	{
		@Override
		public IRecipe parse(JsonContext context, JsonObject json)
		{
			/* Create a new single reload recipe using parsed json info. */
			final String group = JsonUtils.getString(json, "group", "");
			final NonNullList<Ingredient> ingredients = RecipeUtil.parseShapeless(context, json);
			
			return new RecipesFirearmRepairWithKit(group.isEmpty() ? null : new ResourceLocation(group), ingredients, ItemStack.EMPTY);
		}
	}
}
