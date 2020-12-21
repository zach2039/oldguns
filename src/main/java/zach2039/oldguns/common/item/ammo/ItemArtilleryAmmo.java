package zach2039.oldguns.common.item.ammo;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.item.util.ArtilleryType.ArtilleryAmmoType;
import zach2039.oldguns.common.item.util.FirearmType.FirearmAmmoType;

public abstract class ItemArtilleryAmmo extends Item implements IArtilleryAmmo
{
	/**
	 * Ammo type of this artillery ammo item instance.
	 */
	protected ArtilleryAmmoType AmmoType = ArtilleryAmmoType.CANNON_BALL;
	
	/**
	 * Damage of this artillery ammo item instance.
	 */
	protected double AmmoDamage = 30.0f;
	
	public ItemArtilleryAmmo(String name, int stackSize)
	{
		setRegistryName(OldGuns.MODID, name);
		setUnlocalizedName(name);
		setMaxStackSize(stackSize);
		setCreativeTab(OldGuns.OLDGUNS_CREATIVE_TAB);
	}
	
	public ArtilleryAmmoType getAmmoType()
	{
		return AmmoType;
	}

	public void setAmmoType(ArtilleryAmmoType ammoType)
	{
		AmmoType = ammoType;
	}

	public double getAmmoDamage()
	{
		return AmmoDamage;
	}

	public void setAmmoDamage(double ammoDamage)
	{
		AmmoDamage = ammoDamage;
	}
}
