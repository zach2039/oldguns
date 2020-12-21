package zach2039.oldguns.common.item.ammo;

public class ItemLargeStoneMusketBall extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemLargeStoneMusketBall()
	{
		super("large_stone_musket_ball", 4);
		setAmmoDamage(16.0f);
		setProjectileSize(0.5f);
		setProjectileCount(1);
	}
}
