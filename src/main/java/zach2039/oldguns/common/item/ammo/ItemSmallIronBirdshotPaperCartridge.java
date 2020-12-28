package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;

public class ItemSmallIronBirdshotPaperCartridge extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemSmallIronBirdshotPaperCartridge()
	{
		super("small_iron_birdshot_paper_cartridge", 8);
		setAmmoDamage(3.0f);
		setProjectileSize(0.2f);
		setProjectileCount(6);
		setProjectileEffectiveRange(7.0f);
		setProjectileDeviationModifier(3.0f);
	}
}
