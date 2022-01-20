package com.zach2039.oldguns.world.item.ammo.firearm;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.ammo.IAmmo;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.config.OldGunsConfig.FirearmAmmoAttributes;

public class LargeLeadBirdshotItem extends FirearmAmmoItem implements IAmmo {

	private static final FirearmAmmoAttributes ammoAttributes = OldGunsConfig.SERVER.firearmSettings.ammoSettings.large_lead_birdshot;
	
	public LargeLeadBirdshotItem() {
		super((FirearmAmmoProperties) new FirearmAmmoProperties()
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
