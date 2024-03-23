package com.zach2039.oldguns.world.item.crafting.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.zach2039.oldguns.config.OldGunsConfig;
import net.neoforged.neoforge.common.conditions.ICondition;

public class CanCraftArtilleryPowderChargesCondition implements ICondition
{
    public static final CanCraftArtilleryPowderChargesCondition INSTANCE = new CanCraftArtilleryPowderChargesCondition();
    public static Codec<CanCraftArtilleryPowderChargesCondition> CODEC = MapCodec.unit(INSTANCE).stable().codec();

    public CanCraftArtilleryPowderChargesCondition() {
    }

    @Override
    public boolean test(IContext context) {

        return (boolean) OldGunsConfig.getServer(OldGunsConfig.SERVER.recipeSettings.artilleryRecipeSettings.allowArtilleryPowderChargesCrafting);
    }

    @Override
    public String toString() {
        return "can_craft_artillery_powder_charges";
    }

    @Override
    public Codec<? extends ICondition> codec() {
        return CODEC;
    }
}

