package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;
import zach2039.oldguns.common.init.ModConfigs.ConfigCategoryFirearmAmmo;

public class ItemLargeIronMusketBallPaperCartridge extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemLargeIronMusketBallPaperCartridge()
	{
		super("large_iron_musket_ball_paper_cartridge", ConfigCategoryFirearmAmmo.configLargeIronMusketBall.maxStacksize);
		setAmmoDamage(ConfigCategoryFirearmAmmo.configLargeIronMusketBall.projectileDamage);
		setProjectileSize(ConfigCategoryFirearmAmmo.configLargeIronMusketBall.projectileSize);
		setProjectileCount(ConfigCategoryFirearmAmmo.configLargeIronMusketBall.projectileCount);
		setProjectileEffectiveRange(ConfigCategoryFirearmAmmo.configLargeIronMusketBall.projectileEffectiveRange);
		setProjectileDeviationModifier(ConfigCategoryFirearmAmmo.configLargeIronMusketBall.projectileDeviationModifier);
	}
}
