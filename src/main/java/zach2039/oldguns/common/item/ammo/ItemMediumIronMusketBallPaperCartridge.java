package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;

public class ItemMediumIronMusketBallPaperCartridge extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemMediumIronMusketBallPaperCartridge()
	{
		super("medium_iron_musket_ball_paper_cartridge", 6);
		setAmmoDamage(23.0f);
		setProjectileSize(0.4f);
		setProjectileCount(1);
	}
}
