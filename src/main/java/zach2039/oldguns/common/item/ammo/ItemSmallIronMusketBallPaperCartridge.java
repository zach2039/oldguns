package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;

public class ItemSmallIronMusketBallPaperCartridge extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemSmallIronMusketBallPaperCartridge()
	{
		super("small_iron_musket_ball_paper_cartridge", 8);
		setAmmoDamage(20.0f);
		setProjectileSize(0.3f);
		setProjectileCount(1);
		setProjectileEffectiveRange(25.0f);
	}
}
