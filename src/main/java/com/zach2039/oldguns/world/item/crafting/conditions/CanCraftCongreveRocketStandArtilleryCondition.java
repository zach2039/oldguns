package com.zach2039.oldguns.world.item.crafting.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.zach2039.oldguns.config.OldGunsConfig;
import net.neoforged.neoforge.common.conditions.ICondition;

public class CanCraftCongreveRocketStandArtilleryCondition implements ICondition
{
	public static final CanCraftCongreveRocketStandArtilleryCondition INSTANCE = new CanCraftCongreveRocketStandArtilleryCondition();
    public static Codec<CanCraftCongreveRocketStandArtilleryCondition> CODEC = MapCodec.unit(INSTANCE).stable().codec();

    public CanCraftCongreveRocketStandArtilleryCondition() {}

    @Override
    public boolean test(IContext context)
    {
        return (boolean) OldGunsConfig.getServer(OldGunsConfig.SERVER.recipeSettings.artilleryRecipeSettings.allowCongreveRocketStandArtilleryCrafting);
    }

    @Override
    public String toString()
    {
        return "can_craft_congreve_rocket_stand_artillery";
    }

    @Override
    public Codec<? extends ICondition> codec() {
        return CODEC;
    }
}

