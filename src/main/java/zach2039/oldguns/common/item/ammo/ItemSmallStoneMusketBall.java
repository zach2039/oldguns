package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;
import zach2039.oldguns.common.init.ModConfigs.ConfigCategoryFirearmAmmo;

public class ItemSmallStoneMusketBall extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemSmallStoneMusketBall()
	{
		super("small_stone_musket_ball", ConfigCategoryFirearmAmmo.configSmallStoneMusketBall.maxStacksize);
		setAmmoDamage(ConfigCategoryFirearmAmmo.configSmallStoneMusketBall.projectileDamage);
		setProjectileSize(ConfigCategoryFirearmAmmo.configSmallStoneMusketBall.projectileSize);
		setProjectileCount(ConfigCategoryFirearmAmmo.configSmallStoneMusketBall.projectileCount);
		setProjectileEffectiveRange(ConfigCategoryFirearmAmmo.configSmallStoneMusketBall.projectileEffectiveRange);
		setProjectileDeviationModifier(ConfigCategoryFirearmAmmo.configSmallStoneMusketBall.projectileDeviationModifier);
	}
}
