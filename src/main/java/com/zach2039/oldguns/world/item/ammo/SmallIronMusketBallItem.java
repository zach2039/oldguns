package com.zach2039.oldguns.world.item.ammo;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.ammo.impl.IFirearmAmmo;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmReloadType;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmSize;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmWaterResiliency;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.config.OldGunsConfig.FirearmAmmoAttributes;
import com.zach2039.oldguns.config.OldGunsConfig.MuzzleloadingFirearmAttributes;
import com.zach2039.oldguns.world.item.firearm.FirearmItem.FirearmProperties;

public class SmallIronMusketBallItem extends FirearmAmmoItem implements IFirearmAmmo {

	private static final FirearmAmmoAttributes ammoAttributes = OldGunsConfig.COMMON.firearmSettings.small_iron_musket_ball;
	
	public SmallIronMusketBallItem() {
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
