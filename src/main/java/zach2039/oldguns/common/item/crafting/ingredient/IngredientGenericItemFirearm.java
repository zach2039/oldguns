package zach2039.oldguns.common.item.crafting.ingredient;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.IIngredientFactory;
import net.minecraftforge.common.crafting.JsonContext;
import zach2039.oldguns.common.item.firearm.ItemFirearm;

public class IngredientGenericItemFirearm extends Ingredient
{
    protected IngredientGenericItemFirearm()
    {
        super(0);
    }

    @Override
    public boolean apply(@Nullable ItemStack input)
    {
        if (input == null)
            return false;
        
        return (input.getItem() instanceof ItemFirearm);
    }

    @Override
    public boolean isSimple()
    {
        return true;
    }
    
    public static class Factory implements IIngredientFactory {

		@Nonnull
		@Override
		public Ingredient parse(final JsonContext context, final JsonObject json) {
			return new IngredientGenericItemFirearm();
		}
	}
}
