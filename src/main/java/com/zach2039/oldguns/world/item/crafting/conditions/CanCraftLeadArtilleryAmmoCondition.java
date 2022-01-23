package com.zach2039.oldguns.world.item.crafting.conditions;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class CanCraftLeadArtilleryAmmoCondition implements ICondition
{
	public static final CanCraftLeadArtilleryAmmoCondition INSTANCE = new CanCraftLeadArtilleryAmmoCondition();
    private static final ResourceLocation NAME = new ResourceLocation(OldGuns.MODID, "can_craft_lead_artillery_ammo");

    public CanCraftLeadArtilleryAmmoCondition() {}

    @Override
    public ResourceLocation getID()
    {
        return NAME;
    }

    @Override
    public boolean test()
    {
        return OldGunsConfig.SERVER.recipeSettings.artilleryRecipeSettings.allowLeadArtilleryAmmoCrafting.get();
    }

    @Override
    public String toString()
    {
        return "can_craft_lead_artillery_ammo";
    }

    public static class Serializer implements IConditionSerializer<CanCraftLeadArtilleryAmmoCondition>
    {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, CanCraftLeadArtilleryAmmoCondition value) { }

        @Override
        public CanCraftLeadArtilleryAmmoCondition read(JsonObject json)
        {
            return CanCraftLeadArtilleryAmmoCondition.INSTANCE;
        }

        @Override
        public ResourceLocation getID()
        {
            return CanCraftLeadArtilleryAmmoCondition.NAME;
        }
    }
}

