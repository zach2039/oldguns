package com.zach2039.oldguns.world.item.crafting.conditions;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class CanCraftCongreveRocketStandArtilleryCondition implements ICondition
{
	public static final CanCraftCongreveRocketStandArtilleryCondition INSTANCE = new CanCraftCongreveRocketStandArtilleryCondition();
    private static final ResourceLocation NAME = new ResourceLocation(OldGuns.MODID, "can_craft_congreve_rocket_stand_artillery");

    public CanCraftCongreveRocketStandArtilleryCondition() {}

    @Override
    public ResourceLocation getID()
    {
        return NAME;
    }

    @Override
    public boolean test()
    {
        return OldGunsConfig.SERVER.recipeSettings.artilleryRecipeSettings.allowCongreveRocketStandArtilleryCrafting.get();
    }

    @Override
    public String toString()
    {
        return "can_craft_congreve_rocket_stand_artillery";
    }

    public static class Serializer implements IConditionSerializer<CanCraftCongreveRocketStandArtilleryCondition>
    {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, CanCraftCongreveRocketStandArtilleryCondition value) { }

        @Override
        public CanCraftCongreveRocketStandArtilleryCondition read(JsonObject json)
        {
            return CanCraftCongreveRocketStandArtilleryCondition.INSTANCE;
        }

        @Override
        public ResourceLocation getID()
        {
            return CanCraftCongreveRocketStandArtilleryCondition.NAME;
        }
    }
}

