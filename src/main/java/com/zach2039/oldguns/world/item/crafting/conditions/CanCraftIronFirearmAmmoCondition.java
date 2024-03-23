package com.zach2039.oldguns.world.item.crafting.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.zach2039.oldguns.config.OldGunsConfig;
import net.neoforged.neoforge.common.conditions.ICondition;

public class CanCraftIronFirearmAmmoCondition implements ICondition
{
	public static final CanCraftIronFirearmAmmoCondition INSTANCE = new CanCraftIronFirearmAmmoCondition();
    public static Codec<CanCraftIronFirearmAmmoCondition> CODEC = MapCodec.unit(INSTANCE).stable().codec();

    public CanCraftIronFirearmAmmoCondition() {}

    @Override
    public boolean test(IContext context)
    {
        return (boolean) OldGunsConfig.getServer(OldGunsConfig.SERVER.recipeSettings.firearmRecipeSettings.allowIronFirearmAmmoCrafting);
    }

    @Override
    public String toString()
    {
        return "can_craft_iron_firearm_ammo";
    }

    @Override
    public Codec<? extends ICondition> codec() {
        return CODEC;
    }
}

