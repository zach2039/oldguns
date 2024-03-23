package com.zach2039.oldguns.world.item.crafting.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.zach2039.oldguns.config.OldGunsConfig;
import net.neoforged.neoforge.common.conditions.ICondition;

public class CanCraftPaperCartridgesCondition implements ICondition
{
	public static final CanCraftPaperCartridgesCondition INSTANCE = new CanCraftPaperCartridgesCondition();
    public static Codec<CanCraftPaperCartridgesCondition> CODEC = MapCodec.unit(INSTANCE).stable().codec();

    public CanCraftPaperCartridgesCondition() {}

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

    @Override
    public Codec<? extends ICondition> codec() {
        return CODEC;
    }
}

