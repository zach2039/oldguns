package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;
import zach2039.oldguns.common.init.ModConfigs.ConfigCategoryFirearmAmmo;

public class ItemSmallIronBuckshot extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemSmallIronBuckshot()
	{		
		super("small_iron_buckshot", ConfigCategoryFirearmAmmo.configSmallIronBuckshot.maxStacksize);
		setAmmoDamage(ConfigCategoryFirearmAmmo.configSmallIronBuckshot.projectileDamage);
		setProjectileSize(ConfigCategoryFirearmAmmo.configSmallIronBuckshot.projectileSize);
		setProjectileCount(ConfigCategoryFirearmAmmo.configSmallIronBuckshot.projectileCount);
		setProjectileEffectiveRange(ConfigCategoryFirearmAmmo.configSmallIronBuckshot.projectileEffectiveRange);
		setProjectileDeviationModifier(ConfigCategoryFirearmAmmo.configSmallIronBuckshot.projectileDeviationModifier);
	}
}
