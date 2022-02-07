package com.zach2039.oldguns.world.item.crafting.conditions;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class CanCraftBlastingPowderSticksCondition implements ICondition
{
	public static final CanCraftBlastingPowderSticksCondition INSTANCE = new CanCraftBlastingPowderSticksCondition();
    private static final ResourceLocation NAME = new ResourceLocation(OldGuns.MODID, "can_craft_blasting_powder_stick");

    public CanCraftBlastingPowderSticksCondition() {}

    @Override
    public ResourceLocation getID()
    {
        return NAME;
    }

    @Override
    public boolean test()
    {
        return OldGunsConfig.SERVER.recipeSettings.equipmentRecipeSettings.allowBlastingPowderStickCrafting.get();
    }

    @Override
    public String toString()
    {
        return "can_craft_blasting_powder_stick";
    }

    public static class Serializer implements IConditionSerializer<CanCraftBlastingPowderSticksCondition>
    {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, CanCraftBlastingPowderSticksCondition value) { }

        @Override
        public CanCraftBlastingPowderSticksCondition read(JsonObject json)
        {
            return CanCraftBlastingPowderSticksCondition.INSTANCE;
        }

        @Override
        public ResourceLocation getID()
        {
            return CanCraftBlastingPowderSticksCondition.NAME;
        }
    }
}

