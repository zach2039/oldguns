package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;
import zach2039.oldguns.common.init.ModConfigs.ConfigCategoryFirearmAmmo;

public class ItemMediumStoneMusketBall extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemMediumStoneMusketBall()
	{		
		super("medium_stone_musket_ball", ConfigCategoryFirearmAmmo.configMediumStoneMusketBall.maxStacksize);
		setAmmoDamage(ConfigCategoryFirearmAmmo.configMediumStoneMusketBall.projectileDamage);
		setProjectileSize(ConfigCategoryFirearmAmmo.configMediumStoneMusketBall.projectileSize);
		setProjectileCount(ConfigCategoryFirearmAmmo.configMediumStoneMusketBall.projectileCount);
		setProjectileEffectiveRange(ConfigCategoryFirearmAmmo.configMediumStoneMusketBall.projectileEffectiveRange);
		setProjectileDeviationModifier(ConfigCategoryFirearmAmmo.configMediumStoneMusketBall.projectileDeviationModifier);	
	}
}
