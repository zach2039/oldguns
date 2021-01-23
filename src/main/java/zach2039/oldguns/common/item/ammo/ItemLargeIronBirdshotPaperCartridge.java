package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;
import zach2039.oldguns.common.init.ModConfigs.ConfigCategoryFirearmAmmo;

public class ItemLargeIronBirdshotPaperCartridge extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemLargeIronBirdshotPaperCartridge()
	{
		super("large_iron_birdshot_paper_cartridge", ConfigCategoryFirearmAmmo.configLargeIronBirdshot.maxStacksize);
		setAmmoDamage(ConfigCategoryFirearmAmmo.configLargeIronBirdshot.projectileDamage);
		setProjectileSize(ConfigCategoryFirearmAmmo.configLargeIronBirdshot.projectileSize);
		setProjectileCount(ConfigCategoryFirearmAmmo.configLargeIronBirdshot.projectileCount);
		setProjectileEffectiveRange(ConfigCategoryFirearmAmmo.configLargeIronBirdshot.projectileEffectiveRange);
		setProjectileDeviationModifier(ConfigCategoryFirearmAmmo.configLargeIronBirdshot.projectileDeviationModifier);	
	}
}
