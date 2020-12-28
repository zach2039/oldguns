package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;

public class ItemLargeIronBirdshotPaperCartridge extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemLargeIronBirdshotPaperCartridge()
	{
		super("large_iron_birdshot_paper_cartridge", 4);
		setAmmoDamage(3.0f);
		setProjectileSize(0.2f);
		setProjectileCount(10);
		setProjectileEffectiveRange(7.0f);
		setProjectileDeviationModifier(3.0f);
	}
}
