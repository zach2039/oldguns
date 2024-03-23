package com.zach2039.oldguns.world.item.crafting.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.zach2039.oldguns.config.OldGunsConfig;
import net.neoforged.neoforge.common.conditions.ICondition;

public class CanCraftBlastingPowderSticksCondition implements ICondition
{
	public static final CanCraftBlastingPowderSticksCondition INSTANCE = new CanCraftBlastingPowderSticksCondition();
    public static Codec<CanCraftBlastingPowderSticksCondition> CODEC = MapCodec.unit(INSTANCE).stable().codec();

    public CanCraftBlastingPowderSticksCondition() {}

    @Override
    public boolean test(IContext context)
    {
        return (boolean) OldGunsConfig.getServer(OldGunsConfig.SERVER.recipeSettings.equipmentRecipeSettings.allowBlastingPowderStickCrafting);
    }

    @Override
    public String toString()
    {
        return "can_craft_blasting_powder_stick";
    }

    @Override
    public Codec<? extends ICondition> codec() {
        return CODEC;
    }
}

