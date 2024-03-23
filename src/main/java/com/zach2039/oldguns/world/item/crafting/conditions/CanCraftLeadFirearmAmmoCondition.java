package com.zach2039.oldguns.world.item.crafting.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.zach2039.oldguns.config.OldGunsConfig;
import net.neoforged.neoforge.common.conditions.ICondition;

public class CanCraftLeadFirearmAmmoCondition implements ICondition
{
	public static final CanCraftLeadFirearmAmmoCondition INSTANCE = new CanCraftLeadFirearmAmmoCondition();
    public static Codec<CanCraftLeadFirearmAmmoCondition> CODEC = MapCodec.unit(INSTANCE).stable().codec();

    public CanCraftLeadFirearmAmmoCondition() {}

    @Override
    public boolean test(IContext context)
    {
    	return (boolean) OldGunsConfig.getServer(OldGunsConfig.SERVER.recipeSettings.firearmRecipeSettings.allowLeadFirearmAmmoCrafting);
    }

    @Override
    public String toString()
    {
        return "can_craft_lead_firearm_ammo";
    }

    @Override
    public Codec<? extends ICondition> codec() {
        return CODEC;
    }
}

