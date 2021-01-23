package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;
import zach2039.oldguns.common.init.ModConfigs.ConfigCategoryFirearmAmmo;

public class ItemMediumIronMusketBallPaperCartridge extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemMediumIronMusketBallPaperCartridge()
	{
		super("medium_iron_musket_ball_paper_cartridge", ConfigCategoryFirearmAmmo.configMediumIronMusketBall.maxStacksize);
		setAmmoDamage(ConfigCategoryFirearmAmmo.configMediumIronMusketBall.projectileDamage);
		setProjectileSize(ConfigCategoryFirearmAmmo.configMediumIronMusketBall.projectileSize);
		setProjectileCount(ConfigCategoryFirearmAmmo.configMediumIronMusketBall.projectileCount);
		setProjectileEffectiveRange(ConfigCategoryFirearmAmmo.configMediumIronMusketBall.projectileEffectiveRange);
		setProjectileDeviationModifier(ConfigCategoryFirearmAmmo.configMediumIronMusketBall.projectileDeviationModifier);
	}
}
