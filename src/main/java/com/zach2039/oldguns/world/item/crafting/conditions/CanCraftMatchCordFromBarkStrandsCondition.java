package com.zach2039.oldguns.world.item.crafting.conditions;

import com.google.gson.JsonObject;
import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.config.OldGunsConfig;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.crafting.conditions.ICondition;
import net.neoforged.neoforge.common.crafting.conditions.ICondition.IContext;
import net.neoforged.neoforge.common.crafting.conditions.IConditionSerializer;

public class CanCraftMatchCordFromBarkStrandsCondition implements ICondition
{
	public static final CanCraftMatchCordFromBarkStrandsCondition INSTANCE = new CanCraftMatchCordFromBarkStrandsCondition();
    private static final ResourceLocation NAME = new ResourceLocation(OldGuns.MODID, "can_craft_match_cord_from_bark_strands");

    public CanCraftMatchCordFromBarkStrandsCondition() {}

    @Override
    public ResourceLocation getID()
    {
        return NAME;
    }

    @Override
    public boolean test(IContext context)
    {
    	return (boolean) OldGunsConfig.getServer(OldGunsConfig.SERVER.recipeSettings.miscRecipeSettings.allowMatchCordFromBarkStrandsCrafting);
    }

    @Override
    public String toString()
    {
        return "can_craft_match_cord_from_bark_strands";
    }

    public static class Serializer implements IConditionSerializer<CanCraftMatchCordFromBarkStrandsCondition>
    {
        public static final Serializer INSTANCE = new Serializer();

        @Override
        public void write(JsonObject json, CanCraftMatchCordFromBarkStrandsCondition value) { }

        @Override
        public CanCraftMatchCordFromBarkStrandsCondition read(JsonObject json)
        {
            return CanCraftMatchCordFromBarkStrandsCondition.INSTANCE;
        }

        @Override
        public ResourceLocation getID()
        {
            return CanCraftMatchCordFromBarkStrandsCondition.NAME;
        }
    }
}

