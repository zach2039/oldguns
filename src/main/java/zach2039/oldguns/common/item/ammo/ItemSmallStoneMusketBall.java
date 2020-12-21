package zach2039.oldguns.common.item.ammo;

public class ItemSmallStoneMusketBall extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemSmallStoneMusketBall()
	{
		super("small_stone_musket_ball", 8);
		setAmmoDamage(10.0f);
		setProjectileSize(0.3f);
		setProjectileCount(1);
	}
}
