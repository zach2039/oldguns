package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;
import zach2039.oldguns.common.init.ModConfigs.ConfigCategoryFirearmAmmo;

public class ItemLargeStoneMusketBall extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemLargeStoneMusketBall()
	{
		super("large_stone_musket_ball", ConfigCategoryFirearmAmmo.configLargeStoneMusketBall.maxStacksize);
		setAmmoDamage(ConfigCategoryFirearmAmmo.configLargeStoneMusketBall.projectileDamage);
		setProjectileSize(ConfigCategoryFirearmAmmo.configLargeStoneMusketBall.projectileSize);
		setProjectileCount(ConfigCategoryFirearmAmmo.configLargeStoneMusketBall.projectileCount);
		setProjectileEffectiveRange(ConfigCategoryFirearmAmmo.configLargeStoneMusketBall.projectileEffectiveRange);
		setProjectileDeviationModifier(ConfigCategoryFirearmAmmo.configLargeStoneMusketBall.projectileDeviationModifier);
	}
}
