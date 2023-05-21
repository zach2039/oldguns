package com.zach2039.oldguns.data;

import com.zach2039.oldguns.world.damagesource.OldGunsDamageTypes;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.damagesource.DamageType;

public class OldGunsDamageTypesProvider {

    public static void bootstrap(BootstapContext<DamageType> context) {
        context.register(OldGunsDamageTypes.FIREARM, new DamageType("firearm", 0.1F));
        context.register(OldGunsDamageTypes.ARTILLERY, new DamageType("artillery", 0.1F));
    }
}
