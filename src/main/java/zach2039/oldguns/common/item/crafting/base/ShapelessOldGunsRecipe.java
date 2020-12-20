package zach2039.oldguns.common.item.crafting.base;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import net.minecraft.block.Block;
import net.minecraft.client.util.RecipeItemHelper;
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
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class ShapelessOldGunsRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe
{
    @Nonnull
    protected ItemStack output = ItemStack.EMPTY;
    protected NonNullList<Ingredient> input = NonNullList.create();
    protected ResourceLocation group;
    protected boolean isSimple = true;

    public ShapelessOldGunsRecipe(ResourceLocation group, Block result, Object... recipe){ this(group, new ItemStack(result), recipe); }
    public ShapelessOldGunsRecipe(ResourceLocation group, Item  result, Object... recipe){ this(group, new ItemStack(result), recipe); }
    public ShapelessOldGunsRecipe(ResourceLocation group, NonNullList<Ingredient> input, @Nonnull ItemStack result)
    {
        this.group = group;
        output = result.copy();
        this.input = input;
        for (Ingredient i : input)
            this.isSimple &= i.isSimple();
    }
    public ShapelessOldGunsRecipe(ResourceLocation group, @Nonnull ItemStack result, Object... recipe)
    {
        this.group = group;
        output = result.copy();
        for (Object in : recipe)
        {
            Ingredient ing = CraftingHelper.getIngredient(in);
            if (ing != null)
            {
                input.add(ing);
                this.isSimple &= ing.isSimple();
            }
            else
            {
                String ret = "Invalid shapeless ore recipe: ";
                for (Object tmp :  recipe)
                {
                    ret += tmp + ", ";
                }
                ret += output;
                throw new RuntimeException(ret);
            }
        }
    }

    @Override
    @Nonnull
    public ItemStack getRecipeOutput(){ return output; }

    /**
     * Returns an Item that is the result of this recipe
     */
    @Override
    @Nonnull
    public ItemStack getCraftingResult(@Nonnull InventoryCrafting var1){ return output.copy(); }

    /**
     * Used to check if a recipe matches current crafting inventory
     */
    @Override
    public boolean matches(@Nonnull InventoryCrafting inv, @Nonnull World world)
    {
        int ingredientCount = 0;
        RecipeItemHelper recipeItemHelper = new RecipeItemHelper();
        List<ItemStack> items = Lists.newArrayList();

        for (int i = 0; i < inv.getSizeInventory(); ++i)
        {
            ItemStack itemstack = inv.getStackInSlot(i);
            if (!itemstack.isEmpty())
            {
                ++ingredientCount;
                if (this.isSimple)
                    recipeItemHelper.accountStack(itemstack, 1);
                else
                    items.add(itemstack);
            }
        }

        if (ingredientCount != this.input.size())
            return false;

        if (this.isSimple)
            return recipeItemHelper.canCraft(this, null);

        return RecipeMatcher.findMatches(items, this.input) != null;
    }

    @Override
    @Nonnull
    public NonNullList<Ingredient> getIngredients()
    {
        return this.input;
    }

    @Override
    @Nonnull
    public String getGroup()
    {
        return this.group == null ? "" : this.group.toString();
    }

    /**
     * Used to determine if this recipe can fit in a grid of the given width/height
     */
    @Override
    public boolean canFit(int width, int height)
    {
        return width * height >= this.input.size();
    }

    public static ShapelessOldGunsRecipe factory(JsonContext context, JsonObject json)
    {
        String group = JsonUtils.getString(json, "group", "");

        NonNullList<Ingredient> ings = NonNullList.create();
        for (JsonElement ele : JsonUtils.getJsonArray(json, "ingredients"))
            ings.add(CraftingHelper.getIngredient(ele, context));

        if (ings.isEmpty())
            throw new JsonParseException("No ingredients for shapeless recipe");

        ItemStack itemstack = CraftingHelper.getItemStack(JsonUtils.getJsonObject(json, "result"), context);
        return new ShapelessOldGunsRecipe(group.isEmpty() ? null : new ResourceLocation(group), ings, itemstack);
    }
}