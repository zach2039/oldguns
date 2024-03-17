package com.zach2039.oldguns.world.damagesource;

import com.zach2039.oldguns.OldGuns;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

public class OldGunsDamageTypes {

    public static final ResourceKey<DamageType> FIREARM = register("firearm");
    public static final ResourceKey<DamageType> ARTILLERY = register("artillery");

    private static ResourceKey<DamageType> register(String name)
    {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(OldGuns.MODID, name));
    }
}
