package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;
import zach2039.oldguns.common.init.ModConfigs.ConfigCategoryFirearmAmmo;

public class ItemMediumStoneBirdshot extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemMediumStoneBirdshot()
	{		
		super("medium_stone_birdshot", ConfigCategoryFirearmAmmo.configMediumStoneBirdshot.maxStacksize);
		setAmmoDamage(ConfigCategoryFirearmAmmo.configMediumStoneBirdshot.projectileDamage);
		setProjectileSize(ConfigCategoryFirearmAmmo.configMediumStoneBirdshot.projectileSize);
		setProjectileCount(ConfigCategoryFirearmAmmo.configMediumStoneBirdshot.projectileCount);
		setProjectileEffectiveRange(ConfigCategoryFirearmAmmo.configMediumStoneBirdshot.projectileEffectiveRange);
		setProjectileDeviationModifier(ConfigCategoryFirearmAmmo.configMediumStoneBirdshot.projectileDeviationModifier);	
	}
}
