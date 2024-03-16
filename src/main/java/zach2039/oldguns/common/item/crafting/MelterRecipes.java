package zach2039.oldguns.common.item.crafting;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.init.ModConfigs.ConfigCategoryRecipes;
import zach2039.oldguns.common.init.ModItems;

public class MelterRecipes
{
	private static final MelterRecipes MELTING_BASE = new MelterRecipes();
	
    /** The multimap of melting results, queried through <input ,<cast, output>>. */
    private final Map<ItemStack, Map<ItemStack, ItemStack>> meltingMultimap = Maps.<ItemStack, Map<ItemStack, ItemStack>>newHashMap();
    
    /** A list which contains how many experience points each recipe output will give. */
    private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();

    /**
     * Returns an instance of FurnaceRecipes.
     */
    public static MelterRecipes instance()
    {
        return MELTING_BASE;
    }
    
    private MelterRecipes()
    {
        //this.addMeltingRecipeForBlock(Blocks.IRON_ORE, ModItems.SMALL_MUSKET_BALL_MOLD, new ItemStack(ModItems.SMALL_IRON_MUSKET_BALL, 8), 0.7F);
    	this.addMelting(ModItems.IRON_BITS, 1, ModItems.SMALL_MUSKET_BALL_MOLD, new ItemStack(ModItems.SMALL_IRON_MUSKET_BALL, 1), 0.7F);
    	this.addMelting(ModItems.IRON_BITS, 2, ModItems.MEDIUM_MUSKET_BALL_MOLD, new ItemStack(ModItems.MEDIUM_IRON_MUSKET_BALL, 1), 0.7F);
    	this.addMelting(ModItems.IRON_BITS, 3, ModItems.LARGE_MUSKET_BALL_MOLD, new ItemStack(ModItems.LARGE_IRON_MUSKET_BALL, 1), 0.7F);
    }
 
    /**
     * Adds a melting recipe, where the input item is an instance of Block.
     */
    public void addMeltingRecipeForBlock(Block input, Item cast, ItemStack stack, float experience)
    {
        this.addMelting(Item.getItemFromBlock(input), cast, stack, experience);
    }

    /**
     * Adds a melting recipe using an Item as the input item.
     */
    public void addMelting(Item input, int amount, Item cast, ItemStack stack, float experience)
    {
        this.addMeltingRecipe(new ItemStack(input, amount, 32767), new ItemStack(cast, 1, 32767), stack, experience);
    }
    
    /**
     * Adds a melting recipe using an Item as the input item.
     */
    public void addMelting(Item input, Item cast, ItemStack stack, float experience)
    {
        this.addMeltingRecipe(new ItemStack(input, 1, 32767), new ItemStack(cast, 1, 32767), stack, experience);
    }

    /**
     * Adds a smelting recipe using an ItemStack as the input for the recipe.
     */
    public void addMeltingRecipe(ItemStack input, ItemStack cast, ItemStack stack, float experience)
    {
    	/* Prevent adding of recipe if disabled. */
    	if (!ConfigCategoryRecipes.isRecipeEnabled(stack)) {
    		return;
    	}
		
        if (getMeltingResult(input, cast) != ItemStack.EMPTY) 
        {
        	OldGuns.LOGGER.debug("Ignored melting recipe with conflicting input: {} = {}", input, stack);
        	return; 
        }
        // Create cast to output map, and populate.
        Map<ItemStack, ItemStack> castOutputMap = Maps.<ItemStack, ItemStack>newHashMap();
        castOutputMap.put(cast, stack);
        
        // Put cast to output map in multimap, with input item as key.
        this.meltingMultimap.put(input, castOutputMap);
        
        this.experienceList.put(stack, Float.valueOf(experience));
    }
    
    /**
     * Returns the melting result of an item.
     * <input, <cast, output>>
     */
    public ItemStack getMeltingResult(ItemStack inputStack, ItemStack castStack)
    {
        for (Entry<ItemStack, Map<ItemStack, ItemStack>> multimapEntry : this.meltingMultimap.entrySet())
        {
        	Map<ItemStack, ItemStack> map = multimapEntry.getValue();
        	
        	for (Entry<ItemStack, ItemStack> mapEntry : map.entrySet())
            {
	            if (this.compareItemStacks(inputStack, multimapEntry.getKey()))
	            {
	            	if (this.compareItemStacks(castStack, mapEntry.getKey()))
	            		return mapEntry.getValue();
	            }
            }
        }

        return ItemStack.EMPTY;
    }

    /**
     * Compares two itemstacks to ensure that they are the same. This checks both the item and the metadata of the item.
     */
    private boolean compareItemStacks(ItemStack stack1, ItemStack stack2)
    {
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }

    public Map<ItemStack, Map<ItemStack, ItemStack>> getSmeltingList()
    {
        return this.meltingMultimap;
    }

    public float getMeltingExperience(ItemStack stack)
    {
        float ret = stack.getItem().getSmeltingExperience(stack);
        if (ret != -1) return ret;

        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet())
        {
            if (this.compareItemStacks(stack, entry.getKey()))
            {
                return ((Float)entry.getValue()).floatValue();
            }
        }

        return 0.0F;
    }
}
