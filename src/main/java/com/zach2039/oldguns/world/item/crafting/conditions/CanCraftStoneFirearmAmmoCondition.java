package com.zach2039.oldguns.world.item.crafting.conditions;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class CanCraftStoneFirearmAmmoCondition implements ICondition
{
	public static final CanCraftStoneFirearmAmmoCondition INSTANCE = new CanCraftStoneFirearmAmmoCondition();
    private static final ResourceLocation NAME = new ResourceLocation(OldGuns.MODID, "can_craft_stone_firearm_ammo");

    public CanCraftStoneFirearmAmmoCondition() {}

    @Override
    public ResourceLocation getID()
    {
        return NAME;
    }

    @Override
    public boolean test()
    {
        return OldGunsConfig.SERVER.recipeSettings.firearmRecipeSettings.allowStoneFirearmAmmoCrafting.get();
    }

    @Override
    public String toString()
    {
        return "can_craft_stone_firearm_ammo";
    }

    public static class Serializer implements IConditionSerializer<CanCraftStoneFirearmAmmoCondition>
    {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, CanCraftStoneFirearmAmmoCondition value) { }

        @Override
        public CanCraftStoneFirearmAmmoCondition read(JsonObject json)
        {
            return CanCraftStoneFirearmAmmoCondition.INSTANCE;
        }

        @Override
        public ResourceLocation getID()
        {
            return CanCraftStoneFirearmAmmoCondition.NAME;
        }
    }
}

