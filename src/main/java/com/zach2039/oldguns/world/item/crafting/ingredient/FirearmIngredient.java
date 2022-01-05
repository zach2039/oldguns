package com.zach2039.oldguns.world.item.crafting.ingredient;

import java.util.stream.Stream;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.init.ModCrafting;
import com.zach2039.oldguns.world.item.firearm.FirearmItem;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.IIngredientSerializer;

public class FirearmIngredient extends Ingredient {
	public static final FirearmIngredient INSTANCE = new FirearmIngredient();
	
    protected FirearmIngredient()
    {
        super(Stream.empty());
    }

    @Override
    public boolean test(@Nullable ItemStack input)
    {
        if (input == null)
            return false;
        
        return (input.getItem() instanceof FirearmItem);
    }

    @Override
    public boolean isSimple()
    {
        return true;
    }
    
    @Override
	public IIngredientSerializer<? extends Ingredient> getSerializer() {
		return ModCrafting.Ingredients.FIREARM;
	}

	public static class Serializer implements IIngredientSerializer<FirearmIngredient> {

		@Override
		public FirearmIngredient parse(final JsonObject json) {
			return FirearmIngredient.INSTANCE;
		}

		@Override
		public FirearmIngredient parse(final FriendlyByteBuf buffer) {
			return FirearmIngredient.INSTANCE;
		}

		@Override
		public void write(final FriendlyByteBuf buffer, final FirearmIngredient ingredient) {
			// No-op
		}
	}
}
