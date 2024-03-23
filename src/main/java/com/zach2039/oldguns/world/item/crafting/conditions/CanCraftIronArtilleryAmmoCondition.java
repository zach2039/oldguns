package com.zach2039.oldguns.world.item.crafting.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.zach2039.oldguns.config.OldGunsConfig;
import net.neoforged.neoforge.common.conditions.ICondition;

public class CanCraftIronArtilleryAmmoCondition implements ICondition
{
	public static final CanCraftIronArtilleryAmmoCondition INSTANCE = new CanCraftIronArtilleryAmmoCondition();
    public static Codec<CanCraftIronArtilleryAmmoCondition> CODEC = MapCodec.unit(INSTANCE).stable().codec();

    public CanCraftIronArtilleryAmmoCondition() {}

    @Override
    public boolean test(IContext context)
    {
    	return (boolean) OldGunsConfig.getServer(OldGunsConfig.SERVER.recipeSettings.artilleryRecipeSettings.allowIronArtilleryAmmoCrafting);
    }

    @Override
    public String toString()
    {
        return "can_craft_iron_artillery_ammo";
    }

    @Override
    public Codec<? extends ICondition> codec() {
        return CODEC;
    }
}

