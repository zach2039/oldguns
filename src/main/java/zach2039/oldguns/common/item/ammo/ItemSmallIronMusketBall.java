package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;
import zach2039.oldguns.common.init.ModConfigs.ConfigCategoryFirearmAmmo;

public class ItemSmallIronMusketBall extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemSmallIronMusketBall()
	{		
		super("small_iron_musket_ball", ConfigCategoryFirearmAmmo.configSmallIronMusketBall.maxStacksize);
		setAmmoDamage(ConfigCategoryFirearmAmmo.configSmallIronMusketBall.projectileDamage);
		setProjectileSize(ConfigCategoryFirearmAmmo.configSmallIronMusketBall.projectileSize);
		setProjectileCount(ConfigCategoryFirearmAmmo.configSmallIronMusketBall.projectileCount);
		setProjectileEffectiveRange(ConfigCategoryFirearmAmmo.configSmallIronMusketBall.projectileEffectiveRange);
		setProjectileDeviationModifier(ConfigCategoryFirearmAmmo.configSmallIronMusketBall.projectileDeviationModifier);
	}
}
