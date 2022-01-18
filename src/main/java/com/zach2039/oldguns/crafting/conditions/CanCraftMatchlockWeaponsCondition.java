package com.zach2039.oldguns.crafting.conditions;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class CanCraftMatchlockWeaponsCondition implements ICondition
{
	public static final CanCraftMatchlockWeaponsCondition INSTANCE = new CanCraftMatchlockWeaponsCondition();
    private static final ResourceLocation NAME = new ResourceLocation(OldGuns.MODID, "can_craft_matchlock_weapons");

    public CanCraftMatchlockWeaponsCondition() {}

    @Override
    public ResourceLocation getID()
    {
        return NAME;
    }

    @Override
    public boolean test()
    {
        return OldGunsConfig.SERVER.recipeSettings.allowMatchlockWeaponsCrafting.get();
    }

    @Override
    public String toString()
    {
        return "can_craft_matchlock_weapons";
    }

    public static class Serializer implements IConditionSerializer<CanCraftMatchlockWeaponsCondition>
    {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, CanCraftMatchlockWeaponsCondition value) { }

        @Override
        public CanCraftMatchlockWeaponsCondition read(JsonObject json)
        {
            return CanCraftMatchlockWeaponsCondition.INSTANCE;
        }

        @Override
        public ResourceLocation getID()
        {
            return CanCraftMatchlockWeaponsCondition.NAME;
        }
    }
}

