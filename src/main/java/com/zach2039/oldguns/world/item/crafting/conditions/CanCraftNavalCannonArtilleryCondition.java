package com.zach2039.oldguns.world.item.crafting.conditions;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class CanCraftNavalCannonArtilleryCondition implements ICondition
{
	public static final CanCraftNavalCannonArtilleryCondition INSTANCE = new CanCraftNavalCannonArtilleryCondition();
    private static final ResourceLocation NAME = new ResourceLocation(OldGuns.MODID, "can_craft_naval_cannon_artillery");

    public CanCraftNavalCannonArtilleryCondition() {}

    @Override
    public ResourceLocation getID()
    {
        return NAME;
    }

    @Override
    public boolean test()
    {
        return OldGunsConfig.SERVER.recipeSettings.artilleryRecipeSettings.allowNavalCannonArtilleryCrafting.get();
    }

    @Override
    public String toString()
    {
        return "can_craft_naval_cannon_artillery";
    }

    public static class Serializer implements IConditionSerializer<CanCraftNavalCannonArtilleryCondition>
    {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, CanCraftNavalCannonArtilleryCondition value) { }

        @Override
        public CanCraftNavalCannonArtilleryCondition read(JsonObject json)
        {
            return CanCraftNavalCannonArtilleryCondition.INSTANCE;
        }

        @Override
        public ResourceLocation getID()
        {
            return CanCraftNavalCannonArtilleryCondition.NAME;
        }
    }
}

