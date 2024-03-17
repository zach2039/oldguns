package com.zach2039.oldguns.world.item.crafting.conditions;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.crafting.conditions.ICondition;
import net.neoforged.neoforge.common.crafting.conditions.ICondition.IContext;
import net.neoforged.neoforge.common.crafting.conditions.IConditionSerializer;

public class CanCraftStoneArtilleryAmmoCondition implements ICondition
{
	public static final CanCraftStoneArtilleryAmmoCondition INSTANCE = new CanCraftStoneArtilleryAmmoCondition();
    private static final ResourceLocation NAME = new ResourceLocation(OldGuns.MODID, "can_craft_stone_artillery_ammo");

    public CanCraftStoneArtilleryAmmoCondition() {}

    @Override
    public ResourceLocation getID()
    {
        return NAME;
    }

    @Override
    public boolean test(IContext context)
    {
    	return (boolean) OldGunsConfig.getServer(OldGunsConfig.SERVER.recipeSettings.artilleryRecipeSettings.allowStoneArtilleryAmmoCrafting);
    }

    @Override
    public String toString()
    {
        return "can_craft_stone_artillery_ammo";
    }

    public static class Serializer implements IConditionSerializer<CanCraftStoneArtilleryAmmoCondition>
    {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, CanCraftStoneArtilleryAmmoCondition value) { }

        @Override
        public CanCraftStoneArtilleryAmmoCondition read(JsonObject json)
        {
            return CanCraftStoneArtilleryAmmoCondition.INSTANCE;
        }

        @Override
        public ResourceLocation getID()
        {
            return CanCraftStoneArtilleryAmmoCondition.NAME;
        }
    }
}

