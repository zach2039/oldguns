package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;

public class ItemLargeStoneBirdshot extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemLargeStoneBirdshot()
	{
		super("large_stone_birdshot", 4);
		setAmmoDamage(2.0f);
		setProjectileSize(0.1f);
		setProjectileCount(10);
		setProjectileEffectiveRange(5.0f);
		setProjectileDeviationModifier(3.0f);
	}
}
