package com.zach2039.oldguns.world.item.crafting.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.zach2039.oldguns.config.OldGunsConfig;
import net.neoforged.neoforge.common.conditions.ICondition;

public class CanCraftLeadArtilleryAmmoCondition implements ICondition
{
	public static final CanCraftLeadArtilleryAmmoCondition INSTANCE = new CanCraftLeadArtilleryAmmoCondition();
    public static Codec<CanCraftLeadArtilleryAmmoCondition> CODEC = MapCodec.unit(INSTANCE).stable().codec();

    public CanCraftLeadArtilleryAmmoCondition() {}

    @Override
    public boolean test(IContext context)
    {
    	return (boolean) OldGunsConfig.getServer(OldGunsConfig.SERVER.recipeSettings.artilleryRecipeSettings.allowLeadArtilleryAmmoCrafting);
    }

    @Override
    public String toString()
    {
        return "can_craft_lead_artillery_ammo";
    }

    @Override
    public Codec<? extends ICondition> codec() {
        return CODEC;
    }
}

