package com.zach2039.oldguns.world.item.ammo;

import java.util.ArrayList;
import java.util.List;

import com.zach2039.oldguns.api.ammo.IFirearmAmmo;
import com.zach2039.oldguns.api.firearm.FirearmType.FirearmAmmoType;
import com.zach2039.oldguns.world.entity.BulletProjectile;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class FirearmAmmoItem extends Item implements IFirearmAmmo {
	/**
	 * Ammo type of this firearm ammo item instance.
	 */
	protected FirearmAmmoType ammoType = FirearmAmmoType.MUSKET_BALL;
	
	/**
	 * Damage of this firearm ammo item instance.
	 */
	protected double projectileDamage = 10.0f;
	
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
	protected float projectileDeviationModifier = 1.0f;
	
	/**
	 * Projectile range of this firearm ammo item instance.
	 */
	protected float projectileEffectiveRange = 10.0f;
	
	public FirearmAmmoItem(FirearmAmmoProperties builder) {
		super((Properties) builder);
		this.ammoType = builder.ammoType;
		this.projectileDamage = builder.projectileDamage;
		this.projectileSize = builder.projectileSize;
		this.projectileCount = builder.projectileCount;
		this.projectileDeviationModifier = builder.projectileDeviationModifier;
		this.projectileEffectiveRange = builder.projectileEffectiveRange;
	}
	
	public FirearmAmmoType getAmmoType()
	{
		return this.ammoType;
	}

	public double getProjectileDamage()
	{
		return this.projectileDamage;
	}
	
	public float getProjectileSize()
	{
		return this.projectileSize;
	}
	
	public float getProjectileCount()
	{
		return this.projectileCount;
	}
	
	public float getProjectileDeviationModifier()
	{
		return this.projectileDeviationModifier;
	}
	
	public float getProjectileEffectiveRange()
	{
		return this.projectileEffectiveRange;
	}
	
	public List<BulletProjectile> createProjectiles(Level worldIn, ItemStack stackIn, LivingEntity shooter)
	{
		/* Create list to hold all projectile entities that this bullet makes when fired. */
		List<BulletProjectile> projectileEntityList = new ArrayList<BulletProjectile>();
		
		for (int i = 0; i < getProjectileCount(); i++) 
		{
			BulletProjectile entityBullet = new BulletProjectile(worldIn, shooter);
			entityBullet.setDamage(getProjectileDamage());
			entityBullet.setProjectileSize(getProjectileSize());
			projectileEntityList.add(entityBullet);
		}
		
		return projectileEntityList;
	}
	
	public static class FirearmAmmoProperties extends Properties {
		/**
		 * Ammo type of this firearm ammo item instance.
		 */
		FirearmAmmoType ammoType = FirearmAmmoType.MUSKET_BALL;
		
		/**
		 * Damage of this firearm ammo item instance.
		 */
		double projectileDamage = 10.0f;
		
		/**
		 * Projectile size of this firearm ammo item instance.
		 */
		float projectileSize = 1.0f;
		
		/**
		 * Projectile count of this firearm ammo item instance.
		 */
		int projectileCount = 1;
		
		/**
		 * Projectile deviation of this firearm ammo item instance.
		 */
		float projectileDeviationModifier = 1.0f;
		
		/**
		 * Projectile range of this firearm ammo item instance.
		 */
		float projectileEffectiveRange = 10.0f;
		
		public FirearmAmmoProperties ammoType(FirearmAmmoType ammoType) {
			this.ammoType = ammoType;
			return this;
		}
		
		public FirearmAmmoProperties projectileDamage(float projectileDamage) {
			this.projectileDamage = projectileDamage;
			return this;
		}
		
		public FirearmAmmoProperties projectileSize(float projectileSize) {
			this.projectileSize = projectileSize;
			return this;
		}
		
		public FirearmAmmoProperties projectileCount(int projectileCount) {
			this.projectileCount = projectileCount;
			return this;
		}
		
		public FirearmAmmoProperties projectileDeviationModifier(float projectileDeviationModifier) {
			this.projectileDeviationModifier = projectileDeviationModifier;
			return this;
		}
		
		public FirearmAmmoProperties projectileEffectiveRange(float projectileEffectiveRange) {
			this.projectileEffectiveRange = projectileEffectiveRange;
			return this;
		}
	}
}
