package com.zach2039.oldguns.world.item.crafting.conditions;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import net.minecraftforge.common.crafting.conditions.ICondition.IContext;

public class CanCraftLeadFirearmAmmoCondition implements ICondition
{
	public static final CanCraftLeadFirearmAmmoCondition INSTANCE = new CanCraftLeadFirearmAmmoCondition();
    private static final ResourceLocation NAME = new ResourceLocation(OldGuns.MODID, "can_craft_lead_firearm_ammo");

    public CanCraftLeadFirearmAmmoCondition() {}

    @Override
    public ResourceLocation getID()
    {
        return NAME;
    }

    @Override
    public boolean test(IContext context)
    {
    	return (boolean) OldGunsConfig.getServer(OldGunsConfig.SERVER.recipeSettings.firearmRecipeSettings.allowLeadFirearmAmmoCrafting);
    }

    @Override
    public String toString()
    {
        return "can_craft_lead_firearm_ammo";
    }

    public static class Serializer implements IConditionSerializer<CanCraftLeadFirearmAmmoCondition>
    {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, CanCraftLeadFirearmAmmoCondition value) { }

        @Override
        public CanCraftLeadFirearmAmmoCondition read(JsonObject json)
        {
            return CanCraftLeadFirearmAmmoCondition.INSTANCE;
        }

        @Override
        public ResourceLocation getID()
        {
            return CanCraftLeadFirearmAmmoCondition.NAME;
        }
    }
}

