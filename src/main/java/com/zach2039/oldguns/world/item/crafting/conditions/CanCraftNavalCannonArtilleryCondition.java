package com.zach2039.oldguns.world.item.crafting.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.zach2039.oldguns.config.OldGunsConfig;
import net.neoforged.neoforge.common.conditions.ICondition;

public class CanCraftNavalCannonArtilleryCondition implements ICondition
{
	public static final CanCraftNavalCannonArtilleryCondition INSTANCE = new CanCraftNavalCannonArtilleryCondition();
    public static Codec<CanCraftNavalCannonArtilleryCondition> CODEC = MapCodec.unit(INSTANCE).stable().codec();

    public CanCraftNavalCannonArtilleryCondition() {}

    @Override
    public boolean test(IContext context)
    {
    	return (boolean) OldGunsConfig.getServer(OldGunsConfig.SERVER.recipeSettings.artilleryRecipeSettings.allowNavalCannonArtilleryCrafting);
    }

    @Override
    public String toString()
    {
        return "can_craft_naval_cannon_artillery";
    }

    @Override
    public Codec<? extends ICondition> codec() {
        return CODEC;
    }
}

