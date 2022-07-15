package com.zach2039.oldguns.world.item.crafting.conditions;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import net.minecraftforge.common.crafting.conditions.ICondition.IContext;

public class CanCraftCaplockWeaponsCondition implements ICondition
{
	public static final CanCraftCaplockWeaponsCondition INSTANCE = new CanCraftCaplockWeaponsCondition();
    private static final ResourceLocation NAME = new ResourceLocation(OldGuns.MODID, "can_craft_caplock_weapons");

    public CanCraftCaplockWeaponsCondition() {}

    @Override
    public ResourceLocation getID()
    {
        return NAME;
    }

    @Override
    public boolean test(IContext context)
    {
    	return (boolean) OldGunsConfig.getServer(OldGunsConfig.SERVER.recipeSettings.firearmRecipeSettings.allowCaplockWeaponsCrafting);
    }

    @Override
    public String toString()
    {
        return "can_craft_caplock_weapons";
    }

    public static class Serializer implements IConditionSerializer<CanCraftCaplockWeaponsCondition>
    {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, CanCraftCaplockWeaponsCondition value) { }

        @Override
        public CanCraftCaplockWeaponsCondition read(JsonObject json)
        {
            return CanCraftCaplockWeaponsCondition.INSTANCE;
        }

        @Override
        public ResourceLocation getID()
        {
            return CanCraftCaplockWeaponsCondition.NAME;
        }
    }
}

