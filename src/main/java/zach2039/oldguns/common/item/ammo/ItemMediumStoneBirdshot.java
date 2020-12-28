package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;

public class ItemMediumStoneBirdshot extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemMediumStoneBirdshot()
	{
		super("medium_stone_birdshot", 6);
		setAmmoDamage(2.0f);
		setProjectileSize(0.1f);
		setProjectileCount(8);
	}
}
