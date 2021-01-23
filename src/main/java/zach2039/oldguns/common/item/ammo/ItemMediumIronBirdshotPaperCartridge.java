package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;
import zach2039.oldguns.common.init.ModConfigs.ConfigCategoryFirearmAmmo;

public class ItemMediumIronBirdshotPaperCartridge extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemMediumIronBirdshotPaperCartridge()
	{
		super("medium_iron_birdshot_paper_cartridge", ConfigCategoryFirearmAmmo.configMediumIronBirdshot.maxStacksize);
		setAmmoDamage(ConfigCategoryFirearmAmmo.configMediumIronBirdshot.projectileDamage);
		setProjectileSize(ConfigCategoryFirearmAmmo.configMediumIronBirdshot.projectileSize);
		setProjectileCount(ConfigCategoryFirearmAmmo.configMediumIronBirdshot.projectileCount);
		setProjectileEffectiveRange(ConfigCategoryFirearmAmmo.configMediumIronBirdshot.projectileEffectiveRange);
		setProjectileDeviationModifier(ConfigCategoryFirearmAmmo.configMediumIronBirdshot.projectileDeviationModifier);	
	}
}
