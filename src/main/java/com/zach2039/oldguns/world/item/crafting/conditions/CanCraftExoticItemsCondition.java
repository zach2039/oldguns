package com.zach2039.oldguns.world.item.crafting.conditions;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class CanCraftExoticItemsCondition implements ICondition
{
	public static final CanCraftExoticItemsCondition INSTANCE = new CanCraftExoticItemsCondition();
    private static final ResourceLocation NAME = new ResourceLocation(OldGuns.MODID, "can_craft_exotics");

    public CanCraftExoticItemsCondition() {}

    @Override
    public ResourceLocation getID()
    {
        return NAME;
    }

    @Override
    public boolean test()
    {
        return OldGunsConfig.COMMON.recipeSettings.allowExoticFirearmCrafting.get();
    }

    @Override
    public String toString()
    {
        return "can_craft_exotics";
    }

    public static class Serializer implements IConditionSerializer<CanCraftExoticItemsCondition>
    {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, CanCraftExoticItemsCondition value) { }

        @Override
        public CanCraftExoticItemsCondition read(JsonObject json)
        {
            return CanCraftExoticItemsCondition.INSTANCE;
        }

        @Override
        public ResourceLocation getID()
        {
            return CanCraftExoticItemsCondition.NAME;
        }
    }
}

