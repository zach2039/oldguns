package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;

public class ItemSmallIronBirdshot extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemSmallIronBirdshot()
	{
		super("small_iron_birdshot", 8);
		setAmmoDamage(3.0f);
		setProjectileSize(0.2f);
		setProjectileCount(6);
		setProjectileDeviation(3.0f);
	}
}
