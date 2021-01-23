package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;
import zach2039.oldguns.common.init.ModConfigs.ConfigCategoryFirearmAmmo;

public class ItemLargeIronBuckshotPaperCartridge extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemLargeIronBuckshotPaperCartridge()
	{
		super("large_iron_buckshot_paper_cartridge", ConfigCategoryFirearmAmmo.configLargeIronBuckshot.maxStacksize);
		setAmmoDamage(ConfigCategoryFirearmAmmo.configLargeIronBuckshot.projectileDamage);
		setProjectileSize(ConfigCategoryFirearmAmmo.configLargeIronBuckshot.projectileSize);
		setProjectileCount(ConfigCategoryFirearmAmmo.configLargeIronBuckshot.projectileCount);
		setProjectileEffectiveRange(ConfigCategoryFirearmAmmo.configLargeIronBuckshot.projectileEffectiveRange);
		setProjectileDeviationModifier(ConfigCategoryFirearmAmmo.configLargeIronBuckshot.projectileDeviationModifier);
	}
}
