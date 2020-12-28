package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;

public class ItemSmallIronBuckshotPaperCartridge extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemSmallIronBuckshotPaperCartridge()
	{
		super("small_iron_buckshot_paper_cartridge", 8);
		setAmmoDamage(15.0f);
		setProjectileSize(0.3f);
		setProjectileCount(3);
		setProjectileEffectiveRange(30.0f);
		setProjectileDeviationModifier(1.5f);
	}
}
