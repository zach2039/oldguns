package com.zach2039.oldguns.world.item.crafting.conditions;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;
import net.minecraftforge.common.crafting.conditions.ICondition.IContext;

public class CanCraftPaperCartridgesCondition implements ICondition
{
	public static final CanCraftPaperCartridgesCondition INSTANCE = new CanCraftPaperCartridgesCondition();
    private static final ResourceLocation NAME = new ResourceLocation(OldGuns.MODID, "can_craft_paper_cartridges");

    public CanCraftPaperCartridgesCondition() {}

    @Override
    public ResourceLocation getID()
    {
        return NAME;
    }

    @Override
    public boolean test(IContext context)
    {
    	return (boolean) OldGunsConfig.getServer(OldGunsConfig.SERVER.recipeSettings.firearmRecipeSettings.allowPaperCartridgeCrafting);
    }

    @Override
    public String toString()
    {
        return "can_craft_paper_cartridges";
    }

    public static class Serializer implements IConditionSerializer<CanCraftPaperCartridgesCondition>
    {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, CanCraftPaperCartridgesCondition value) { }

        @Override
        public CanCraftPaperCartridgesCondition read(JsonObject json)
        {
            return CanCraftPaperCartridgesCondition.INSTANCE;
        }

        @Override
        public ResourceLocation getID()
        {
            return CanCraftPaperCartridgesCondition.NAME;
        }
    }
}

