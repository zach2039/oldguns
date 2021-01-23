package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IFirearmAmmo;
import zach2039.oldguns.common.init.ModConfigs.ConfigCategoryFirearmAmmo;

public class ItemSmallIronBirdshotPaperCartridge extends ItemFirearmAmmo implements IFirearmAmmo
{
	public ItemSmallIronBirdshotPaperCartridge()
	{		
		super("small_iron_birdshot_paper_cartridge", ConfigCategoryFirearmAmmo.configSmallIronBirdshot.maxStacksize);
		setAmmoDamage(ConfigCategoryFirearmAmmo.configSmallIronBirdshot.projectileDamage);
		setProjectileSize(ConfigCategoryFirearmAmmo.configSmallIronBirdshot.projectileSize);
		setProjectileCount(ConfigCategoryFirearmAmmo.configSmallIronBirdshot.projectileCount);
		setProjectileEffectiveRange(ConfigCategoryFirearmAmmo.configSmallIronBirdshot.projectileEffectiveRange);
		setProjectileDeviationModifier(ConfigCategoryFirearmAmmo.configSmallIronBirdshot.projectileDeviationModifier);	
	}
}
