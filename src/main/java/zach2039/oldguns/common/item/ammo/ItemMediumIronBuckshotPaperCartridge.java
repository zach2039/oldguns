package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;

public class ItemMediumIronBuckshotPaperCartridge extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemMediumIronBuckshotPaperCartridge()
	{
		super("medium_iron_buckshot_paper_cartridge", 6);
		setAmmoDamage(15.0f);
		setProjectileSize(0.3f);
		setProjectileCount(5);
		setProjectileEffectiveRange(30.0f);
		setProjectileDeviationModifier(1.5f);
	}
}
