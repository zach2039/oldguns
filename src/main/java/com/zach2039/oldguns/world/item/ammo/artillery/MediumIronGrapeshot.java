package com.zach2039.oldguns.world.item.ammo.artillery;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.ammo.ProjectileType;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.config.OldGunsConfig.ArtilleryAmmoAttributes;

public class MediumIronGrapeshot extends ArtilleryAmmoItem {

	private static final ArtilleryAmmoAttributes ammoAttributes = OldGunsConfig.SERVER.artillerySettings.artilleryAmmoSettings.medium_iron_cannonball;
	
	public MediumIronGrapeshot() {
		super((ArtilleryAmmoProperties) new ArtilleryAmmoProperties()
				.projectileCount(ammoAttributes.projectileCount.get())
				.projectileDamage(ammoAttributes.projectileDamage.get().floatValue())
				.projectileSize(ammoAttributes.projectileSize.get().floatValue())
				.projectileEffectiveRange(ammoAttributes.projectileEffectiveRange.get().floatValue())
				.projectileDeviationModifier(ammoAttributes.projectileDeviationModifier.get().floatValue())
				.effectTicks(ammoAttributes.effectTicks.get())
				.effectPotency(ammoAttributes.effectPotency.get().floatValue())
				.ammoType(ProjectileType.GRAPESHOT)
				.stacksTo(ammoAttributes.maxStackSize.get())				
				.tab(OldGuns.CREATIVE_MODE_TAB)
				);
	}

}
