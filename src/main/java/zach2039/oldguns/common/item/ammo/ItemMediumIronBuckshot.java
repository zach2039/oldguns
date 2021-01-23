package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;
import zach2039.oldguns.common.init.ModConfigs.ConfigCategoryFirearmAmmo;

public class ItemMediumIronBuckshot extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemMediumIronBuckshot()
	{	
		super("medium_iron_buckshot", ConfigCategoryFirearmAmmo.configMediumIronBuckshot.maxStacksize);
		setAmmoDamage(ConfigCategoryFirearmAmmo.configMediumIronBuckshot.projectileDamage);
		setProjectileSize(ConfigCategoryFirearmAmmo.configMediumIronBuckshot.projectileSize);
		setProjectileCount(ConfigCategoryFirearmAmmo.configMediumIronBuckshot.projectileCount);
		setProjectileEffectiveRange(ConfigCategoryFirearmAmmo.configMediumIronBuckshot.projectileEffectiveRange);
		setProjectileDeviationModifier(ConfigCategoryFirearmAmmo.configMediumIronBuckshot.projectileDeviationModifier);	
	}
}
