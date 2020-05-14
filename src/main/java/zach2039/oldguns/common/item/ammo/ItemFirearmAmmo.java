package zach2039.oldguns.common.item.ammo;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.item.util.FirearmType.FirearmAmmoType;

public abstract class ItemFirearmAmmo extends Item implements IFirearmAmmo
{
	/**
	 * Ammo type of this firearm ammo item instance.
	 */
	protected FirearmAmmoType AmmoType = FirearmAmmoType.MUSKET_BALL;
	
	/**
	 * Damage of this firearm ammo item instance.
	 */
	protected double AmmoDamage = 10.0f;
	
	public ItemFirearmAmmo(String name, int stackSize)
	{
		setRegistryName(OldGuns.MODID, name);
		setUnlocalizedName(name);
		setMaxStackSize(stackSize);
		setCreativeTab(CreativeTabs.COMBAT);
	}
	
	public FirearmAmmoType getAmmoType()
	{
		return AmmoType;
	}

	public void setAmmoType(FirearmAmmoType ammoType)
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
