package com.zach2039.oldguns.world.item.crafting.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.zach2039.oldguns.config.OldGunsConfig;
import net.neoforged.neoforge.common.conditions.ICondition;

public class CanCraftStoneArtilleryAmmoCondition implements ICondition
{
	public static final CanCraftStoneArtilleryAmmoCondition INSTANCE = new CanCraftStoneArtilleryAmmoCondition();
    public static Codec<CanCraftStoneArtilleryAmmoCondition> CODEC = MapCodec.unit(INSTANCE).stable().codec();

    public CanCraftStoneArtilleryAmmoCondition() {}

    @Override
    public boolean test(IContext context)
    {
    	return (boolean) OldGunsConfig.getServer(OldGunsConfig.SERVER.recipeSettings.artilleryRecipeSettings.allowStoneArtilleryAmmoCrafting);
    }

    @Override
    public String toString()
    {
        return "can_craft_stone_artillery_ammo";
    }

    @Override
    public Codec<? extends ICondition> codec() {
        return CODEC;
    }
}

