package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;
import zach2039.oldguns.common.init.ModConfigs.ConfigCategoryFirearmAmmo;

public class ItemSmallStoneBirdshot extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemSmallStoneBirdshot()
	{		
		super("small_stone_birdshot", ConfigCategoryFirearmAmmo.configSmallStoneBirdshot.maxStacksize);
		setAmmoDamage(ConfigCategoryFirearmAmmo.configSmallStoneBirdshot.projectileDamage);
		setProjectileSize(ConfigCategoryFirearmAmmo.configSmallStoneBirdshot.projectileSize);
		setProjectileCount(ConfigCategoryFirearmAmmo.configSmallStoneBirdshot.projectileCount);
		setProjectileEffectiveRange(ConfigCategoryFirearmAmmo.configSmallStoneBirdshot.projectileEffectiveRange);
		setProjectileDeviationModifier(ConfigCategoryFirearmAmmo.configSmallStoneBirdshot.projectileDeviationModifier);
	}
}
