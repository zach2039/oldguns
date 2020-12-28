package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;

public class ItemMediumIronBuckshot extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemMediumIronBuckshot()
	{
		super("medium_iron_buckshot", 6);
		setAmmoDamage(15.0f);
		setProjectileSize(0.3f);
		setProjectileCount(5);
		setProjectileDeviation(2.0f);
	}
}
