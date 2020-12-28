package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;

public class ItemLargeIronMusketBallPaperCartridge extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemLargeIronMusketBallPaperCartridge()
	{
		super("large_iron_musket_ball_paper_cartridge", 4);
		setAmmoDamage(26.0f);
		setProjectileSize(0.5f);
		setProjectileCount(1);
	}
}
