package com.zach2039.oldguns.world.item.crafting.conditions;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class CanCraftWheellockWeaponsCondition implements ICondition
{
	public static final CanCraftWheellockWeaponsCondition INSTANCE = new CanCraftWheellockWeaponsCondition();
    private static final ResourceLocation NAME = new ResourceLocation(OldGuns.MODID, "can_craft_wheellock_weapons");

    public CanCraftWheellockWeaponsCondition() {}

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
        return "can_craft_wheellock_weapons";
    }

    public static class Serializer implements IConditionSerializer<CanCraftWheellockWeaponsCondition>
    {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, CanCraftWheellockWeaponsCondition value) { }

        @Override
        public CanCraftWheellockWeaponsCondition read(JsonObject json)
        {
            return CanCraftWheellockWeaponsCondition.INSTANCE;
        }

        @Override
        public ResourceLocation getID()
        {
            return CanCraftWheellockWeaponsCondition.NAME;
        }
    }
}

