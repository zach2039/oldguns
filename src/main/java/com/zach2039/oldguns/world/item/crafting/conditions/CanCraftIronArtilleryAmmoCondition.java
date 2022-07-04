package com.zach2039.oldguns.world.item.crafting.conditions;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import net.minecraftforge.common.crafting.conditions.ICondition.IContext;

public class CanCraftIronArtilleryAmmoCondition implements ICondition
{
	public static final CanCraftIronArtilleryAmmoCondition INSTANCE = new CanCraftIronArtilleryAmmoCondition();
    private static final ResourceLocation NAME = new ResourceLocation(OldGuns.MODID, "can_craft_iron_artillery_ammo");

    public CanCraftIronArtilleryAmmoCondition() {}

    @Override
    public ResourceLocation getID()
    {
        return NAME;
    }

    @Override
    public boolean test(IContext context)
    {
        return OldGunsConfig.SERVER.recipeSettings.artilleryRecipeSettings.allowIronArtilleryAmmoCrafting.get();
    }

    @Override
    public String toString()
    {
        return "can_craft_iron_artillery_ammo";
    }

    public static class Serializer implements IConditionSerializer<CanCraftIronArtilleryAmmoCondition>
    {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, CanCraftIronArtilleryAmmoCondition value) { }

        @Override
        public CanCraftIronArtilleryAmmoCondition read(JsonObject json)
        {
            return CanCraftIronArtilleryAmmoCondition.INSTANCE;
        }

        @Override
        public ResourceLocation getID()
        {
            return CanCraftIronArtilleryAmmoCondition.NAME;
        }
    }
}

