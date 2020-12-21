package zach2039.oldguns.common.item.ammo;

public class ItemMediumIronMusketBall extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemMediumIronMusketBall()
	{
		super("medium_iron_musket_ball", 6);
		setAmmoDamage(23.0f);
		setProjectileSize(0.4f);
		setProjectileCount(1);
	}
}
