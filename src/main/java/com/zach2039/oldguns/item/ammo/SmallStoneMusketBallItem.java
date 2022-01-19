package com.zach2039.oldguns.item.ammo;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.ammo.IFirearmAmmo;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.config.OldGunsConfig.FirearmAmmoAttributes;

public class SmallStoneMusketBallItem extends FirearmAmmoItem implements IFirearmAmmo {

	private static final FirearmAmmoAttributes ammoAttributes = OldGunsConfig.SERVER.firearmSettings.ammoSettings.small_stone_musket_ball;
	
	public SmallStoneMusketBallItem() {
		super((FirearmAmmoProperties) new FirearmAmmoProperties()
				.projectileCount(ammoAttributes.projectileCount.get())
				.projectileDamage(ammoAttributes.projectileDamage.get().floatValue())
				.projectileSize(ammoAttributes.projectileSize.get().floatValue())
				.projectileEffectiveRange(ammoAttributes.projectileEffectiveRange.get().floatValue())
				.projectileDeviationModifier(ammoAttributes.projectileDeviationModifier.get().floatValue())
				.stacksTo(ammoAttributes.maxStackSize.get())				
				.tab(OldGuns.ITEM_GROUP)
				);
	}
}
