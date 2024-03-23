package com.zach2039.oldguns.world.item.crafting.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.zach2039.oldguns.config.OldGunsConfig;
import net.neoforged.neoforge.common.conditions.ICondition;

public class CanCraftStoneFirearmAmmoCondition implements ICondition
{
	public static final CanCraftStoneFirearmAmmoCondition INSTANCE = new CanCraftStoneFirearmAmmoCondition();
    public static Codec<CanCraftStoneFirearmAmmoCondition> CODEC = MapCodec.unit(INSTANCE).stable().codec();

    public CanCraftStoneFirearmAmmoCondition() {}


    @Override
    public boolean test(IContext context)
    {
    	return (boolean) OldGunsConfig.getServer(OldGunsConfig.SERVER.recipeSettings.firearmRecipeSettings.allowStoneFirearmAmmoCrafting);
    }

    @Override
    public String toString()
    {
        return "can_craft_stone_firearm_ammo";
    }

    @Override
    public Codec<? extends ICondition> codec() {
        return CODEC;
    }
}

