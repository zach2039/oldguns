package com.zach2039.oldguns.world.item.crafting.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.zach2039.oldguns.config.OldGunsConfig;
import net.neoforged.neoforge.common.conditions.ICondition;

public class CanCraftWheellockWeaponsCondition implements ICondition
{
	public static final CanCraftWheellockWeaponsCondition INSTANCE = new CanCraftWheellockWeaponsCondition();
    public static Codec<CanCraftWheellockWeaponsCondition> CODEC = MapCodec.unit(INSTANCE).stable().codec();

    public CanCraftWheellockWeaponsCondition() {}

    @Override
    public boolean test(IContext context)
    {
    	return (boolean) OldGunsConfig.getServer(OldGunsConfig.SERVER.recipeSettings.firearmRecipeSettings.allowMatchlockWeaponsCrafting);
    }

    @Override
    public String toString()
    {
        return "can_craft_wheellock_weapons";
    }

    @Override
    public Codec<? extends ICondition> codec() {
        return CODEC;
    }

}

