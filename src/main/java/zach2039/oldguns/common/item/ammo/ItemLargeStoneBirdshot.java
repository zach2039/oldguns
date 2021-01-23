package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;
import zach2039.oldguns.common.init.ModConfigs.ConfigCategoryFirearmAmmo;

public class ItemLargeStoneBirdshot extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemLargeStoneBirdshot()
	{
		super("large_stone_birdshot", ConfigCategoryFirearmAmmo.configLargeStoneBirdshot.maxStacksize);
		setAmmoDamage(ConfigCategoryFirearmAmmo.configLargeStoneBirdshot.projectileDamage);
		setProjectileSize(ConfigCategoryFirearmAmmo.configLargeStoneBirdshot.projectileSize);
		setProjectileCount(ConfigCategoryFirearmAmmo.configLargeStoneBirdshot.projectileCount);
		setProjectileEffectiveRange(ConfigCategoryFirearmAmmo.configLargeStoneBirdshot.projectileEffectiveRange);
		setProjectileDeviationModifier(ConfigCategoryFirearmAmmo.configLargeStoneBirdshot.projectileDeviationModifier);
	}
}
