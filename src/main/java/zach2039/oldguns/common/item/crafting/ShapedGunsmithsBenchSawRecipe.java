package zach2039.oldguns.common.item.crafting;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.event.ForgeEventFactory;
import zach2039.oldguns.common.item.crafting.util.RecipeUtil;
import zach2039.oldguns.common.item.tools.ItemHackSaw;

public class ShapedGunsmithsBenchSawRecipe extends ShapedGunsmithsBenchRecipe
{
	/**
	 * Default constructor.
	 */
	public ShapedGunsmithsBenchSawRecipe(@Nullable final ResourceLocation group, final ItemStack result, final CraftingHelper.ShapedPrimer primer) 
	{
		super(group, result, primer);
	}

	private ItemStack damageHackSaw(final ItemStack stack)
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

		/* Find and damage/destroy saw on use. */
		for (int i = 0; i < remainingItems.size(); ++i) 
		{
			final ItemStack itemstack = inventoryCrafting.getStackInSlot(i);

			if (!itemstack.isEmpty() && itemstack.getItem() instanceof ItemHackSaw)
			{
				remainingItems.set(i, damageHackSaw(itemstack.copy()));
			}
			else
			{
				remainingItems.set(i, ForgeHooks.getContainerItem(itemstack));
			}
		}

		return remainingItems;
	}
	
	public static class Factory implements IRecipeFactory
	{
		@Override
		public IRecipe parse(JsonContext context, JsonObject json)
		{
			/* Create a new firearm build recipe using parsed json info. */
			final String group = JsonUtils.getString(json, "group", "");
			final CraftingHelper.ShapedPrimer primer = RecipeUtil.parseShaped(context, json);
			final ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);

			return new ShapedGunsmithsBenchSawRecipe(group.isEmpty() ? null : new ResourceLocation(group), result, primer);
		}
	}
}
