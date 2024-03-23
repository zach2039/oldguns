package com.zach2039.oldguns.world.item.crafting.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.zach2039.oldguns.config.OldGunsConfig;
import net.neoforged.neoforge.common.conditions.ICondition;

public class CanCraftTntFromBlastingPowderSticksCondition implements ICondition
{
	public static final CanCraftTntFromBlastingPowderSticksCondition INSTANCE = new CanCraftTntFromBlastingPowderSticksCondition();
    public static Codec<CanCraftTntFromBlastingPowderSticksCondition> CODEC = MapCodec.unit(INSTANCE).stable().codec();

    public CanCraftTntFromBlastingPowderSticksCondition() {}

    @Override
    public boolean test(IContext context)
    {
    	return (boolean) OldGunsConfig.getServer(OldGunsConfig.SERVER.recipeSettings.vanillaAlternativeRecipeSettings.allowTntFromBlastingPowderSticksCrafting);
    }

    @Override
    public String toString()
    {
        return "can_craft_tnt_from_blasting_powder_sticks";
    }

    @Override
    public Codec<? extends ICondition> codec() {
        return CODEC;
    }
}

