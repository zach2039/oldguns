package zach2039.oldguns.common.item.ammo;

public class ItemLargeStoneBirdshot extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemLargeStoneBirdshot()
	{
		super("large_stone_birdshot", 4);
		setAmmoDamage(2.0f);
		setProjectileSize(0.2f);
		setProjectileCount(10);
	}
}
