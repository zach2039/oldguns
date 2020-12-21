package zach2039.oldguns.common.item.ammo;

public class ItemMediumStoneMusketBall extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemMediumStoneMusketBall()
	{
		super("medium_stone_musket_ball", 6);
		setAmmoDamage(13.0f);
		setProjectileSize(0.4f);
		setProjectileCount(1);
	}
}
