package com.zach2039.oldguns.world.item.crafting.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.zach2039.oldguns.config.OldGunsConfig;
import net.neoforged.neoforge.common.conditions.ICondition;

public class CanCraftMatchCordFromBarkStrandsCondition implements ICondition
{
	public static final CanCraftMatchCordFromBarkStrandsCondition INSTANCE = new CanCraftMatchCordFromBarkStrandsCondition();
    public static Codec<CanCraftMatchCordFromBarkStrandsCondition> CODEC = MapCodec.unit(INSTANCE).stable().codec();

    public CanCraftMatchCordFromBarkStrandsCondition() {}

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

    @Override
    public Codec<? extends ICondition> codec() {
        return CODEC;
    }
}

