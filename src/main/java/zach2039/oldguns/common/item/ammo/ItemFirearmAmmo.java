package zach2039.oldguns.common.item.ammo;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.entity.EntityProjectile;
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
	
	/**
	 * Projectile size of this firearm ammo item instance.
	 */
	protected float ProjectileSize = 1.0f;
	
	/**
	 * Projectile count of this firearm ammo item instance.
	 */
	protected int ProjectileCount = 1;
	
	public ItemFirearmAmmo(String name, int stackSize)
	{
		setRegistryName(OldGuns.MODID, name);
		setUnlocalizedName(name);
		setMaxStackSize(stackSize);
		setCreativeTab(OldGuns.OLDGUNS_CREATIVE_TAB);
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
	
	public float getProjectileSize()
	{
		return ProjectileSize;
	}

	public void setProjectileSize(float projectileSize)
	{
		ProjectileSize = projectileSize;
	}
	
	public float getProjectileCount()
	{
		return ProjectileCount;
	}

	public void setProjectileCount(int projectileCount)
	{
		ProjectileCount = projectileCount;
	}
	
	public List<EntityProjectile> createProjectiles(World worldIn, ItemStack stack, EntityLivingBase shooter)
	{
		/* Create list to hold all projectile entities that this bullet makes when fired. */
		List<EntityProjectile> projectileEntityList = new ArrayList<EntityProjectile>();
		
		for (int i = 0; i < getProjectileCount(); i++) 
		{
			EntityProjectile entityBullet = new EntityProjectile(worldIn, shooter);
			entityBullet.setDamage(getAmmoDamage());
			entityBullet.setProjectileSize(getProjectileSize());
			projectileEntityList.add(entityBullet);
		}
		
		return projectileEntityList;
	}
}
