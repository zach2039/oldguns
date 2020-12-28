package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;

public class ItemMediumIronBirdshotPaperCartridge extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemMediumIronBirdshotPaperCartridge()
	{
		super("medium_iron_birdshot_paper_cartridge", 6);
		setAmmoDamage(3.0f);
		setProjectileSize(0.2f);
		setProjectileCount(8);
		setProjectileEffectiveRange(7.0f);
		setProjectileDeviationModifier(3.0f);
	}
}
