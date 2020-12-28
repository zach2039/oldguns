package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;

public class ItemSmallStoneBirdshot extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemSmallStoneBirdshot()
	{
		super("small_stone_birdshot", 8);
		setAmmoDamage(2.0f);
		setProjectileSize(0.1f);
		setProjectileCount(6);
		setProjectileEffectiveRange(5.0f);
		setProjectileDeviationModifier(3.0f);
	}
}
