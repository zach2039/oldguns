package zach2039.oldguns.common.item.ammo;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import zach2039.oldguns.api.ammo.IFirearmAmmo;
import zach2039.oldguns.api.firearm.FirearmType.FirearmAmmoType;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.entity.EntityProjectile;

public abstract class ItemFirearmAmmo extends Item implements IFirearmAmmo
{
	/**
	 * Ammo type of this firearm ammo item instance.
	 */
	protected FirearmAmmoType ammoType = FirearmAmmoType.MUSKET_BALL;
	
	/**
	 * Damage of this firearm ammo item instance.
	 */
	protected double ammoDamage = 10.0f;
	
	/**
	 * Projectile size of this firearm ammo item instance.
	 */
	protected float projectileSize = 1.0f;
	
	/**
	 * Projectile count of this firearm ammo item instance.
	 */
	protected int projectileCount = 1;
	
	/**
	 * Projectile deviation of this firearm ammo item instance.
	 */
	protected float projectileDeviation = 1.0f;
	
	public ItemFirearmAmmo(String name, int stackSize)
	{
		setRegistryName(OldGuns.MODID, name);
		setUnlocalizedName(name);
		setMaxStackSize(stackSize);
		setCreativeTab(OldGuns.OLDGUNS_CREATIVE_TAB);
	}
	
	public FirearmAmmoType getAmmoType()
	{
		return this.ammoType;
	}

	public void setAmmoType(FirearmAmmoType ammoType)
	{
		this.ammoType = ammoType;
	}

	public double getAmmoDamage()
	{
		return this.ammoDamage;
	}

	public void setAmmoDamage(double ammoDamage)
	{
		this.ammoDamage = ammoDamage;
	}
	
	public float getProjectileSize()
	{
		return this.projectileSize;
	}

	public void setProjectileSize(float projectileSize)
	{
		this.projectileSize = projectileSize;
	}
	
	public float getProjectileCount()
	{
		return this.projectileCount;
	}

	public void setProjectileCount(int projectileCount)
	{
		this.projectileCount = projectileCount;
	}
	
	public float getProjectileDeviationModifier()
	{
		return this.projectileDeviation;
	}

	public void setProjectileDeviation(float projectileDeviation)
	{
		this.projectileDeviation = projectileDeviation;
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
