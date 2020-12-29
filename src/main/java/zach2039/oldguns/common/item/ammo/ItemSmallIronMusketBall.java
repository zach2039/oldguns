package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;

public class ItemSmallIronMusketBall extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemSmallIronMusketBall()
	{
		super("small_iron_musket_ball", 8);
		setAmmoDamage(20.0f);
		setProjectileSize(0.3f);
		setProjectileCount(1);
		setProjectileEffectiveRange(25.0f);
	}
}
