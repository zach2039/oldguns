package com.zach2039.oldguns.world.item.crafting.conditions;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import net.minecraftforge.common.crafting.conditions.ICondition.IContext;

public class CanCraftFlintlockWeaponsCondition implements ICondition
{
	public static final CanCraftFlintlockWeaponsCondition INSTANCE = new CanCraftFlintlockWeaponsCondition();
    private static final ResourceLocation NAME = new ResourceLocation(OldGuns.MODID, "can_craft_flintlock_weapons");

    public CanCraftFlintlockWeaponsCondition() {}

    @Override
    public ResourceLocation getID()
    {
        return NAME;
    }

    @Override
    public boolean test(IContext context)
    {
    	return (boolean) OldGunsConfig.getServer(OldGunsConfig.SERVER.recipeSettings.firearmRecipeSettings.allowFlintlockWeaponsCrafting);
    }

    @Override
    public String toString()
    {
        return "can_craft_flintlock_weapons";
    }

    public static class Serializer implements IConditionSerializer<CanCraftFlintlockWeaponsCondition>
    {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, CanCraftFlintlockWeaponsCondition value) { }

        @Override
        public CanCraftFlintlockWeaponsCondition read(JsonObject json)
        {
            return CanCraftFlintlockWeaponsCondition.INSTANCE;
        }

        @Override
        public ResourceLocation getID()
        {
            return CanCraftFlintlockWeaponsCondition.NAME;
        }
    }
}

