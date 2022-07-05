package com.zach2039.oldguns.world.item.crafting.conditions;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class CanCraftArtilleryPowderChargesCondition implements ICondition
{
	public static final CanCraftArtilleryPowderChargesCondition INSTANCE = new CanCraftArtilleryPowderChargesCondition();
    private static final ResourceLocation NAME = new ResourceLocation(OldGuns.MODID, "can_craft_artillery_powder_charges");

    public CanCraftArtilleryPowderChargesCondition() {}

    @Override
    public ResourceLocation getID()
    {
        return NAME;
    }

    @Override
    public boolean test(IContext context)
    {
        return (boolean) OldGunsConfig.getServer(OldGunsConfig.SERVER.recipeSettings.artilleryRecipeSettings.allowArtilleryPowderChargesCrafting);
    }

    @Override
    public String toString()
    {
        return "can_craft_artillery_powder_charges";
    }

    public static class Serializer implements IConditionSerializer<CanCraftArtilleryPowderChargesCondition>
    {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, CanCraftArtilleryPowderChargesCondition value) { }

        @Override
        public CanCraftArtilleryPowderChargesCondition read(JsonObject json)
        {
            return CanCraftArtilleryPowderChargesCondition.INSTANCE;
        }

        @Override
        public ResourceLocation getID()
        {
            return CanCraftArtilleryPowderChargesCondition.NAME;
        }
    }
}

