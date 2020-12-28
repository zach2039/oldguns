package zach2039.oldguns.common.item.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.registries.IForgeRegistryEntry;
import zach2039.oldguns.api.firearm.impl.IFirearm;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.init.ModItems;
import zach2039.oldguns.common.init.ModConfigs.ConfigCategoryRecipes;
import zach2039.oldguns.common.item.ammo.ItemFirearmAmmo;
import zach2039.oldguns.common.item.crafting.util.RecipeUtil;
import zach2039.oldguns.common.item.util.FirearmNBTHelper;

/**
 * Took a lot from Darkhax's tutorial on custom recipes.
 * @author sumyunguy
 * @author Darkhax
 */
public class BreechloadingReloadRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe
{
	
	@Nonnull
    protected ItemStack output = ItemStack.EMPTY;
	protected NonNullList<Ingredient> input = null;
	protected final ResourceLocation group;
	
	public BreechloadingReloadRecipe(@Nullable final ResourceLocation group, final NonNullList<Ingredient> input, final ItemStack result) {
		this.output = result;
		this.input = input;
		this.group = group;
	}
	
	@Override
	public boolean matches(InventoryCrafting inv, World worldIn)
	{
		for (Ingredient ingredient : input) {
			if (!ingredient.test(inv.getStackInSlot(0))) {
				return false;
			}
		}
		return true;
	}

	@Override
    @Nonnull
    public NonNullList<Ingredient> getIngredients()
    {
        return this.input;
    }
	
	@Override
	public ItemStack getCraftingResult(InventoryCrafting inv)
	{
		ItemStack outputCopy = this.output.copy();
		
		return ConfigCategoryRecipes.isRecipeEnabled(outputCopy) ? outputCopy : ItemStack.EMPTY;
		//return firearmStack;
	}
	
    @Override
    @Nonnull
    public ItemStack getRecipeOutput()
	{
		ItemStack outputStack = this.output;
		List<ItemStack> dummyAmmoStackList = new ArrayList<ItemStack>();
		dummyAmmoStackList.add(new ItemStack(ModItems.MEDIUM_IRON_MUSKET_BALL));
		
		FirearmNBTHelper.setNBTTagMagazineStack(outputStack, dummyAmmoStackList);
		return ConfigCategoryRecipes.isRecipeEnabled(this.output) ? outputStack : ItemStack.EMPTY;
	}

    public Map<Ingredient, Integer> getIngredientsWithCounts()
    {
    	Map<Ingredient, Integer> countMap = new HashMap<Ingredient, Integer>();
    
    	for (Ingredient ingredient : input) {
    		boolean merged = false;
    		for (Entry<Ingredient, Integer> entry : countMap.entrySet()) {
    			if (entry.getKey().getValidItemStacksPacked().compareTo(ingredient.getValidItemStacksPacked()) == 0)
    			{
    				int oldCount = countMap.get(entry.getKey());
        			countMap.replace(entry.getKey(), oldCount, oldCount + 1);
        			merged = true;
        			break;
    			}
    		}
    		if (!merged)
    			countMap.put(ingredient, 1);
    	}
    	
    	return countMap;
    }
    
    public boolean isValid (IInventory inv) 
    {   
    	if (getRecipeOutput() == ItemStack.EMPTY)
    		return false;
    	
    	for (Entry<Ingredient, Integer> entry : getIngredientsWithCounts().entrySet()) {
    		Ingredient ingredient = entry.getKey();
    		int countRequired = entry.getValue();
    		int numItems = 0;
    		List<ItemStack> satisfactoryStacks = new ArrayList<ItemStack>();
    		//OldGuns.logger.info("ingredient : " + entry.getKey());
    		//OldGuns.logger.info("count : " + entry.getValue());
    		boolean hasValidStackForIngredient = false;
    		boolean hasCorrectCountForIngredients = false;
    		/* Check if player has an itemstack that satisfies the ingredient. */
    		for (int i = 0; i < inv.getSizeInventory(); i++)
    		{
    			ItemStack itemstack = inv.getStackInSlot(i);
    			if (ingredient.test(itemstack)) {
    				satisfactoryStacks.add(itemstack);
    				hasValidStackForIngredient = true;
    			}
    		}
    		/* Also check if total stacksize can satisfy count required. */
    		for (ItemStack stack : satisfactoryStacks)
    		{
    			numItems += stack.getCount();
    		}
    		if (numItems >= countRequired)
    			hasCorrectCountForIngredients = true;
    		
    		if (!hasCorrectCountForIngredients || !hasValidStackForIngredient)
    			return false;
    	}
    		
		return true;
    }
    
    public ItemStack getAmmoItemStack()
    {
    	for (Ingredient ingredient : input) {
    		for (ItemStack stack :ingredient.getMatchingStacks()) {
    			if (stack.getItem() instanceof ItemFirearmAmmo)
    			{
    				return stack;
    			}
    		}
		}
		return ItemStack.EMPTY;
    }
    
    public boolean consumeIngredients(IInventory inv, boolean isCreative)
    {
    	for (Ingredient ingredient : input) {
    		ItemStack stackToConsume = findMatchingItemStackForIngredient(ingredient, inv);
    		
    		if (isCreative)
    			continue;
    		if (stackToConsume.getItem() instanceof IFirearm)
    			continue;
    		
    		if (stackToConsume != ItemStack.EMPTY) 
    		{
    			stackToConsume.shrink(1);
    		}
    		else
    		{
    			return false;
    		}
		}
    	
		return true;
    }
    
    public static ItemStack findMatchingItemStackForIngredient(Ingredient ingredient, IInventory inv)
    {
    	for (ItemStack stackIng : ingredient.getMatchingStacks()) {
			for (int i = 0; i < inv.getSizeInventory(); i++) {
				ItemStack stackInv = inv.getStackInSlot(i);
    			if (stackIng.isItemEqualIgnoreDurability(stackInv))
    			{
    				return stackInv;
    			}
			}
		}
    	return ItemStack.EMPTY;
    }
    
    @Override
	public boolean canFit(int width, int height)
    {
    	return true;
    }
    
	@Override
	public NonNullList<ItemStack> getRemainingItems(final InventoryCrafting inventoryCrafting) {
		final NonNullList<ItemStack> remainingItems = NonNullList.withSize(inventoryCrafting.getSizeInventory(), ItemStack.EMPTY);
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
			final ItemStack result = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
			
			/* Store parsed recipe, as well as register. */
			BreechloadingReloadRecipe recipe = new BreechloadingReloadRecipe(group.isEmpty() ? null : new ResourceLocation(group), ingredients, result);
			return recipe;
		}
	}
}
