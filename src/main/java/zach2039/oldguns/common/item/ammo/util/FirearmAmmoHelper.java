package zach2039.oldguns.common.item.ammo.util;

import zach2039.oldguns.api.ammo.IFirearmAmmo;

public class FirearmAmmoHelper {

	public static void initAmmoProperties(IFirearmAmmo ammo)
	{
		ammo.setAmmoDamage(3.0f);
		ammo.setProjectileSize(0.2f);
		ammo.setProjectileCount(10);
		ammo.setProjectileEffectiveRange(7.0f);
		ammo.setProjectileDeviationModifier(3.0f);
	}
}
