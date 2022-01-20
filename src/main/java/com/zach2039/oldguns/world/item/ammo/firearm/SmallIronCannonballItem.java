package com.zach2039.oldguns.world.item.ammo.firearm;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.ammo.IArtilleryAmmo;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.config.OldGunsConfig.ArtilleryAmmoAttributes;
import com.zach2039.oldguns.world.item.ammo.artillery.ArtilleryAmmoItem;

public class SmallIronCannonballItem extends ArtilleryAmmoItem implements IArtilleryAmmo {

	private static final ArtilleryAmmoAttributes ammoAttributes = OldGunsConfig.SERVER.artillerySettings.artilleryAmmoSettings.small_iron_cannonball;
	
	public SmallIronCannonballItem() {
		super((ArtilleryAmmoProperties) new ArtilleryAmmoProperties()
				.effectTicks(ammoAttributes.effectTicks.get())
				.effectPotency(ammoAttributes.effectPotency.get().floatValue())
				.projectileCount(ammoAttributes.projectileCount.get())
				.projectileDamage(ammoAttributes.projectileDamage.get().floatValue())
				.projectileSize(ammoAttributes.projectileSize.get().floatValue())
				.projectileEffectiveRange(ammoAttributes.projectileEffectiveRange.get().floatValue())
				.projectileDeviationModifier(ammoAttributes.projectileDeviationModifier.get().floatValue())
				.stacksTo(ammoAttributes.maxStackSize.get())				
				.tab(OldGuns.CREATIVE_MODE_TAB)
				);
	}
}
