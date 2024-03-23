package com.zach2039.oldguns.world.item.crafting.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.zach2039.oldguns.config.OldGunsConfig;
import net.neoforged.neoforge.common.conditions.ICondition;

public class CanCraftCaplockWeaponsCondition implements ICondition
{
	public static final CanCraftCaplockWeaponsCondition INSTANCE = new CanCraftCaplockWeaponsCondition();
    public static Codec<CanCraftCaplockWeaponsCondition> CODEC = MapCodec.unit(INSTANCE).stable().codec();

    public CanCraftCaplockWeaponsCondition() {}

    @Override
    public boolean test(IContext context)
    {
    	return (boolean) OldGunsConfig.getServer(OldGunsConfig.SERVER.recipeSettings.firearmRecipeSettings.allowCaplockWeaponsCrafting);
    }

    @Override
    public String toString()
    {
        return "can_craft_caplock_weapons";
    }

    @Override
    public Codec<? extends ICondition> codec() {
        return CODEC;
    }
}

