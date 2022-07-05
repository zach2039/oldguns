package com.zach2039.oldguns.world.item.ammo.artillery;

import java.util.ArrayList;
import java.util.List;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.ammo.Ammo;
import com.zach2039.oldguns.api.ammo.AmmoTypes;
import com.zach2039.oldguns.api.ammo.ArtilleryAmmo;
import com.zach2039.oldguns.api.ammo.ProjectileType;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.world.entity.BulletProjectile;
import com.zach2039.oldguns.world.level.block.entity.StationaryArtilleryBlockEntity;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ArtilleryAmmoItem extends Item implements Ammo, ArtilleryAmmo {
	
	private ArtilleryAmmoItem(ArtilleryAmmoProperties builder) {
		super((Properties) builder);
		this.ammoType = builder.ammoType;
		this.effectTicks = builder.effectTicks;
		this.effectPotency = builder.effectPotency;
		this.projectileDamage = builder.projectileDamage;
		this.projectileSize = builder.projectileSize;
		this.projectileCount = builder.projectileCount;
		this.projectileDeviationModifier = builder.projectileDeviationModifier;
		this.projectileEffectiveRange = builder.projectileEffectiveRange;
		this.projectileArmorBypassPercentage = builder.projectileArmorBypassPercentage;
	}
	
	public ArtilleryAmmoItem(AmmoTypes.ArtilleryAmmo entry) {
		this((ArtilleryAmmoProperties) new ArtilleryAmmoProperties()
				.projectileCount((int) OldGunsConfig.getServer(entry.getAttributes().projectileCount))
				.effectTicks((int) OldGunsConfig.getServer(entry.getAttributes().effectTicks))
				.effectPotency(((Double) OldGunsConfig.getServer(entry.getAttributes().effectPotency)).floatValue())
				.projectileDamage(((Double) OldGunsConfig.getServer(entry.getAttributes().projectileDamage)).floatValue())
				.projectileSize(((Double) OldGunsConfig.getServer(entry.getAttributes().projectileSize)).floatValue())
				.projectileEffectiveRange(((Double) OldGunsConfig.getServer(entry.getAttributes().projectileEffectiveRange)).floatValue())
				.projectileDeviationModifier(((Double) OldGunsConfig.getServer(entry.getAttributes().projectileDeviationModifier)).floatValue())
				.projectileArmorBypassPercentage(((Double) OldGunsConfig.getServer(entry.getAttributes().projectileArmorBypassPercentage)).floatValue())
				.ammoType(entry.getProjectileType())
				.stacksTo((int) OldGunsConfig.getServer(entry.getAttributes().maxStackSize))				
				.tab(OldGuns.CREATIVE_MODE_TAB)
				);
	}
	
	@Override
	public List<BulletProjectile> createProjectiles(Level worldIn, double x, double y, double z, ArtilleryAmmo stack,
			StationaryArtilleryBlockEntity stationaryArtilleryEntity, LivingEntity shooter) {
		// Create list to hold all projectile entities that this bullet makes when fired
		List<BulletProjectile> projectileEntityList = new ArrayList<BulletProjectile>();
		
		float range = 1.5f;
		
		/* Get the position of the cannon's barrel using trig. */
		float barrelX = -Mth.sin((float) (((stationaryArtilleryEntity.getShotYaw()) / 180F) * 3.141593F)) * Mth.cos((float) ((stationaryArtilleryEntity.getShotPitch() / 180F) * 3.141593F)) * range;
		float barrelY = -Mth.sin((float) ((stationaryArtilleryEntity.getShotPitch() / 180F) * 3.141593F)) * range - 0.1F;
		float barrelZ = Mth.cos((float) (((stationaryArtilleryEntity.getShotYaw()) / 180F) * 3.141593F)) * Mth.cos((float) ((stationaryArtilleryEntity.getShotPitch() / 180F) * 3.141593F)) * range;
		
		for (int i = 0; i < getProjectileCount(); i++) 
		{
			BulletProjectile entityBullet = new BulletProjectile(
					worldIn, shooter, 
					x + barrelX, 
					y + barrelY + stationaryArtilleryEntity.getShotHeight(),
					z + barrelZ);
			/*BulletProjectile entityBullet = new BulletProjectile(
					worldIn, shooter);*/
			entityBullet.setDamage(getProjectileDamage());
			entityBullet.setProjectileSize(getProjectileSize());
			entityBullet.setProjectileType(getAmmoType());
			entityBullet.setBypassArmorPercentage(getProjectileArmorBypassPercentage());
			entityBullet.setEffectStrength(getEffectPotency());
			entityBullet.setEffectTicks(getEffectTicks());
			projectileEntityList.add(entityBullet);
		}
		
		return projectileEntityList;
	}
	
	@Override
	public List<BulletProjectile> createProjectiles(Level worldIn, ItemStack stackIn, LivingEntity shooter)
	{
		/* Create list to hold all projectile entities that this bullet makes when fired. */
		List<BulletProjectile> projectileEntityList = new ArrayList<BulletProjectile>();
		
		for (int i = 0; i < getProjectileCount(); i++) 
		{
			BulletProjectile entityBullet = new BulletProjectile(worldIn, shooter);
			entityBullet.setDamage(getProjectileDamage());
			entityBullet.setProjectileSize(getProjectileSize());
			entityBullet.setProjectileType(getAmmoType());
			entityBullet.setBypassArmorPercentage(getProjectileArmorBypassPercentage());
			entityBullet.setEffectStrength(getEffectPotency());
			entityBullet.setEffectTicks(getEffectTicks());
			projectileEntityList.add(entityBullet);
		}
		
		return projectileEntityList;
	}
	
	@Override
	public ProjectileType getAmmoType() {
		return this.ammoType;
	}

	@Override
	public int getEffectTicks() {
		return this.effectTicks;
	}
	
	@Override
	public float getEffectPotency() {
		return this.effectPotency;
	}
	
	@Override
	public double getProjectileDamage() {
		return this.projectileDamage;
	}
	
	@Override
	public float getProjectileSize() {
		return this.projectileSize;
	}
	
	@Override
	public float getProjectileCount() {
		return this.projectileCount;
	}
	
	@Override
	public float getProjectileDeviationModifier() {
		return this.projectileDeviationModifier;
	}
	
	@Override
	public float getProjectileEffectiveRange() {
		return this.projectileEffectiveRange;
	}
	
	@Override
	public float getProjectileArmorBypassPercentage() {
		return this.projectileArmorBypassPercentage;
	}
	
	public static class ArtilleryAmmoProperties extends Properties {
		/**
		 * Ammo type of this ammo item instance.
		 */
		ProjectileType ammoType = ProjectileType.CANNONBALL;
		
		/**
		 * Projectile effect length of this ammo item instance
		 */
		int effectTicks = 0;
		
		/**
		 * Projectile effect potency of this ammo item instance
		 */
		float effectPotency = 0;
		
		/**
		 * Damage of this ammo item instance
		 */
		double projectileDamage = 10.0f;
		
		/**
		 * Projectile size of this ammo item instance
		 */
		float projectileSize = 1.0f;
		
		/**
		 * Projectile count of this ammo item instance
		 */
		int projectileCount = 1;
		
		/**
		 * Projectile deviation of this ammo item instance
		 */
		float projectileDeviationModifier = 1.0f;
		
		/**
		 * Projectile range of this ammo item instance
		 */
		float projectileEffectiveRange = 10.0f;
		
		/**
		 * Projectile armor bypass percentage of this ammo
		 */
		protected float projectileArmorBypassPercentage = 0.0f;
		
		public ArtilleryAmmoProperties ammoType(ProjectileType ammoType) {
			this.ammoType = ammoType;
			return this;
		}
		
		public ArtilleryAmmoProperties effectTicks(int effectTicks) {
			this.effectTicks = effectTicks;
			return this;
		}
		
		public ArtilleryAmmoProperties effectPotency(float effectPotency) {
			this.effectPotency = effectPotency;
			return this;
		}
		
		public ArtilleryAmmoProperties projectileDamage(float projectileDamage) {
			this.projectileDamage = projectileDamage;
			return this;
		}
		
		public ArtilleryAmmoProperties projectileSize(float projectileSize) {
			this.projectileSize = projectileSize;
			return this;
		}
		
		public ArtilleryAmmoProperties projectileCount(int projectileCount) {
			this.projectileCount = projectileCount;
			return this;
		}
		
		public ArtilleryAmmoProperties projectileDeviationModifier(float projectileDeviationModifier) {
			this.projectileDeviationModifier = projectileDeviationModifier;
			return this;
		}
		
		public ArtilleryAmmoProperties projectileEffectiveRange(float projectileEffectiveRange) {
			this.projectileEffectiveRange = projectileEffectiveRange;
			return this;
		}
		
		public ArtilleryAmmoProperties projectileArmorBypassPercentage(float projectileArmorBypassPercentage) {
			this.projectileArmorBypassPercentage = projectileArmorBypassPercentage;
			return this;
		}
	}

	/**
	 * Ammo type of this firearm ammo item instance.
	 */
	ProjectileType ammoType = ProjectileType.CANNONBALL;
	
	/**
	 * Projectile effect length of this ammo item instance
	 */
	int effectTicks = 0;
	
	/**
	 * Projectile effect potency of this ammo item instance
	 */
	float effectPotency = 0;
	
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
	
	/**
	 * Projectile armor bypass percentage of this ammo
	 */
	float projectileArmorBypassPercentage = 0.0f;
}
