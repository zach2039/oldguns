package zach2039.oldguns.common.item.ammo;

import zach2039.oldguns.api.ammo.IArtilleryAmmo;
import zach2039.oldguns.api.artillery.ArtilleryAmmoType;

public class ItemMediumIronCannonball extends ItemArtilleryAmmo implements IArtilleryAmmo
{
	public ItemMediumIronCannonball()
	{
		super("medium_iron_cannonball", 1);
		setAmmoDamage(35.0f);
		setAmmoType(ArtilleryAmmoType.SOLID);
		setEffectStrength(3.0f);
		setProjectileSize(2f);
	}
}
