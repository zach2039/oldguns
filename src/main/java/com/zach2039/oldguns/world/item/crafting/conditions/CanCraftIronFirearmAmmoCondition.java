package com.zach2039.oldguns.world.item.crafting.conditions;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class CanCraftIronFirearmAmmoCondition implements ICondition
{
	public static final CanCraftIronFirearmAmmoCondition INSTANCE = new CanCraftIronFirearmAmmoCondition();
    private static final ResourceLocation NAME = new ResourceLocation(OldGuns.MODID, "can_craft_iron_firearm_ammo");

    public CanCraftIronFirearmAmmoCondition() {}

    @Override
    public ResourceLocation getID()
    {
        return NAME;
    }

    @Override
    public boolean test()
    {
        return OldGunsConfig.SERVER.recipeSettings.firearmRecipeSettings.allowIronFirearmAmmoCrafting.get();
    }

    @Override
    public String toString()
    {
        return "can_craft_iron_firearm_ammo";
    }

    public static class Serializer implements IConditionSerializer<CanCraftIronFirearmAmmoCondition>
    {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, CanCraftIronFirearmAmmoCondition value) { }

        @Override
        public CanCraftIronFirearmAmmoCondition read(JsonObject json)
        {
            return CanCraftIronFirearmAmmoCondition.INSTANCE;
        }

        @Override
        public ResourceLocation getID()
        {
            return CanCraftIronFirearmAmmoCondition.NAME;
        }
    }
}

