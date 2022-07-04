package com.zach2039.oldguns.world.item.crafting.conditions;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import net.minecraftforge.common.crafting.conditions.ICondition.IContext;

public class CanCraftTntFromBlastingPowderSticksCondition implements ICondition
{
	public static final CanCraftTntFromBlastingPowderSticksCondition INSTANCE = new CanCraftTntFromBlastingPowderSticksCondition();
    private static final ResourceLocation NAME = new ResourceLocation(OldGuns.MODID, "can_craft_tnt_from_blasting_powder_sticks");

    public CanCraftTntFromBlastingPowderSticksCondition() {}

    @Override
    public ResourceLocation getID()
    {
        return NAME;
    }

    @Override
    public boolean test(IContext context)
    {
        return OldGunsConfig.SERVER.recipeSettings.vanillaAlternativeRecipeSettings.allowTntFromBlastingPowderSticksCrafting.get();
    }

    @Override
    public String toString()
    {
        return "can_craft_tnt_from_blasting_powder_sticks";
    }

    public static class Serializer implements IConditionSerializer<CanCraftTntFromBlastingPowderSticksCondition>
    {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, CanCraftTntFromBlastingPowderSticksCondition value) { }

        @Override
        public CanCraftTntFromBlastingPowderSticksCondition read(JsonObject json)
        {
            return CanCraftTntFromBlastingPowderSticksCondition.INSTANCE;
        }

        @Override
        public ResourceLocation getID()
        {
            return CanCraftTntFromBlastingPowderSticksCondition.NAME;
        }
    }
}

