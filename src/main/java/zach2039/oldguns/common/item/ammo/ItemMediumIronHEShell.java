package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IArtilleryAmmo;
import zach2039.oldguns.api.artillery.ArtilleryAmmoType;

public class ItemMediumIronHEShell extends ItemArtilleryAmmo implements IArtilleryAmmo
{
	public ItemMediumIronHEShell()
	{
		super("medium_iron_he_shell", 1);
		setAmmoDamage(25.0f);
		setAmmoType(ArtilleryAmmoType.EXPLOSIVE);
		setEffectStrength(3.0f);
		setProjectileSize(2f);
	}
}
