package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;
import zach2039.oldguns.common.init.ModConfigs.ConfigCategoryFirearmAmmo;

public class ItemMediumIronBirdshot extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemMediumIronBirdshot()
	{
		super("medium_iron_birdshot", ConfigCategoryFirearmAmmo.configMediumIronBirdshot.maxStacksize);
		setAmmoDamage(ConfigCategoryFirearmAmmo.configMediumIronBirdshot.projectileDamage);
		setProjectileSize(ConfigCategoryFirearmAmmo.configMediumIronBirdshot.projectileSize);
		setProjectileCount(ConfigCategoryFirearmAmmo.configMediumIronBirdshot.projectileCount);
		setProjectileEffectiveRange(ConfigCategoryFirearmAmmo.configMediumIronBirdshot.projectileEffectiveRange);
		setProjectileDeviationModifier(ConfigCategoryFirearmAmmo.configMediumIronBirdshot.projectileDeviationModifier);
	}
}
