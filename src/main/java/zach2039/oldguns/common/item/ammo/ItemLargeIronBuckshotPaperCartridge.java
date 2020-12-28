package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;

public class ItemLargeIronBuckshotPaperCartridge extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemLargeIronBuckshotPaperCartridge()
	{
		super("large_iron_buckshot_paper_cartridge", 4);
		setAmmoDamage(15.0f);
		setProjectileSize(0.3f);
		setProjectileCount(7);
		setProjectileEffectiveRange(30.0f);
		setProjectileDeviationModifier(1.5f);
	}
}
