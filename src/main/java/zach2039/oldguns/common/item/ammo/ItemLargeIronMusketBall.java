package zach2039.oldguns.common.item.ammo;

public class ItemLargeIronMusketBall extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemLargeIronMusketBall()
	{
		super("large_iron_musket_ball", 4);
		setAmmoDamage(26.0f);
		setProjectileSize(0.5f);
		setProjectileCount(1);
	}
}
