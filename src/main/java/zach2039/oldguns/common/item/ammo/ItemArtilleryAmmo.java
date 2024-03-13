package zach2039.oldguns.common.item.ammo;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import zach2039.oldguns.api.ammo.IArtilleryAmmo;
import zach2039.oldguns.api.artillery.ArtilleryAmmoType;
import zach2039.oldguns.api.artillery.impl.IArtillery;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.entity.EntityArtilleryProjectile;
import zach2039.oldguns.common.entity.EntityProjectile;

public abstract class ItemArtilleryAmmo extends Item implements IArtilleryAmmo
{
	/**
	 * Ammo type of this artillery ammo item instance.
	 */
	protected ArtilleryAmmoType ammoType = ArtilleryAmmoType.SOLID;
	
	/**
	 * Damage of this artillery ammo item instance.
	 */
	protected double ammoDamage = 30.0f;
	
	/**
	 * Projectile size of this artillery ammo item instance.
	 */
	protected float projectileSize = 1.0f;
	
	/**
	 * Projectile count of this artillery ammo item instance.
	 */
	protected int projectileCount = 1;
	
	/**
	 * Effect strength of this artillery ammo item instance.
	 */
	protected float effectStrength = 1f;
	
	public ItemArtilleryAmmo(String name, int stackSize)
	{
		setRegistryName(OldGuns.MODID, name);
		//setUnlocalizedName(name);
		setMaxStackSize(stackSize);
		setCreativeTab(OldGuns.OLDGUNS_CREATIVE_TAB);
	}
	
	public ArtilleryAmmoType getAmmoType()
	{
		return ammoType;
	}

	public void setAmmoType(ArtilleryAmmoType ammoType)
	{
		this.ammoType = ammoType;
	}

	public double getAmmoDamage()
	{
		return ammoDamage;
	}

	public void setAmmoDamage(double ammoDamage)
	{
		this.ammoDamage = ammoDamage;
	}
	
	public float getProjectileSize() 
	{
		return this.projectileSize;
	}

	@Override
	public void setProjectileSize(float size) 
	{
		this.projectileSize = size;
	}

	@Override
	public float getProjectileCount() 
	{
		return this.projectileCount;
	}

	@Override
	public void setProjectileCount(int count) 
	{
		this.projectileCount = count;
	}

	@Override
	public void setEffectStrength(float strength)
	{
		this.effectStrength = strength;
	}

	@Override
	public float getEffectStrength() {
		return this.effectStrength;
	}
	
	@Override
	public List<EntityProjectile> createProjectiles(World worldIn, double posX, double posY, double posZ, ItemStack stack, IArtillery artillery, EntityLivingBase shooter)
	{
		/* Create list to hold all projectile entities that this bullet makes when fired. */
		List<EntityProjectile> projectileEntityList = new ArrayList<EntityProjectile>();
		
		float range = 1.5f;
		
		/* Get the position of the cannon's barrel using trig. */
		float barrelX = -MathHelper.sin((float) (((artillery.getBarrelYaw()) / 180F) * 3.141593F)) * MathHelper.cos((float) ((artillery.getBarrelPitch() / 180F) * 3.141593F)) * range;
		float barrelY = -MathHelper.sin((float) ((artillery.getBarrelPitch() / 180F) * 3.141593F)) * range - 0.1F;
		float barrelZ = MathHelper.cos((float) (((artillery.getBarrelYaw()) / 180F) * 3.141593F)) * MathHelper.cos((float) ((artillery.getBarrelPitch() / 180F) * 3.141593F)) * range;
		
		for (int i = 0; i < getProjectileCount(); i++) 
		{
			EntityArtilleryProjectile entityBullet = new EntityArtilleryProjectile(worldIn, posX + barrelX, posY + artillery.getBarrelHeight() + barrelY, posZ + barrelZ);
			
			entityBullet.setProjectileSize(getProjectileSize());
			entityBullet.setProjectileType(getAmmoType());
			entityBullet.setEffectStrength(3.0f);
			entityBullet.setDamage(getAmmoDamage());
			
			projectileEntityList.add(entityBullet);
		}
				
		return projectileEntityList;
	}
}
