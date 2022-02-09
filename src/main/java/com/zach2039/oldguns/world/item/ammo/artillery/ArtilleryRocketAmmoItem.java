package com.zach2039.oldguns.world.item.ammo.artillery;

import java.util.ArrayList;
import java.util.List;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.ammo.Ammo;
import com.zach2039.oldguns.api.ammo.AmmoTypes;
import com.zach2039.oldguns.api.ammo.ArtilleryAmmo;
import com.zach2039.oldguns.api.ammo.ProjectileType;
import com.zach2039.oldguns.api.ammo.RocketArtilleryAmmo;
import com.zach2039.oldguns.world.entity.BulletProjectile;
import com.zach2039.oldguns.world.entity.RocketProjectile;
import com.zach2039.oldguns.world.level.block.entity.StationaryArtilleryBlockEntity;
import com.zach2039.oldguns.world.level.block.entity.StationaryRocketBlockEntity;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ArtilleryRocketAmmoItem extends Item implements Ammo, RocketArtilleryAmmo {
	
	private ArtilleryRocketAmmoItem(ArtilleryAmmoProperties builder) {
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
	
	public ArtilleryRocketAmmoItem(AmmoTypes.ArtilleryAmmo entry) {
		this((ArtilleryAmmoProperties) new ArtilleryAmmoProperties()
				.projectileCount(entry.getAttributes().projectileCount.get())
				.effectTicks(entry.getAttributes().effectTicks.get())
				.effectPotency(entry.getAttributes().effectPotency.get().floatValue())
				.projectileDamage(entry.getAttributes().projectileDamage.get().floatValue())
				.projectileSize(entry.getAttributes().projectileSize.get().floatValue())
				.projectileEffectiveRange(entry.getAttributes().projectileEffectiveRange.get().floatValue())
				.projectileDeviationModifier(entry.getAttributes().projectileDeviationModifier.get().floatValue())
				.projectileArmorBypassPercentage(entry.getAttributes().projectileArmorBypassPercentage.get().floatValue())
				.ammoType(entry.getProjectileType())
				.stacksTo(entry.getAttributes().maxStackSize.get())				
				.tab(OldGuns.CREATIVE_MODE_TAB)
				);
	}
	
	@Override
	public float getFlightTime(ItemStack ammoStack) {
		return 100;
	}
	
	@Override
	public List<RocketProjectile> createProjectiles(Level worldIn, double x, double y, double z, RocketArtilleryAmmo stack,
			StationaryRocketBlockEntity stationaryRocketBlockEntity, LivingEntity shooter) {
		// Create list to hold all projectile entities that this bullet makes when fired
		List<RocketProjectile> projectileEntityList = new ArrayList<RocketProjectile>();
		
		float range = 1.5f;
		
		/* Get the position of the cannon's barrel using trig. */
		float barrelX = -Mth.sin((float) (((stationaryRocketBlockEntity.getShotYaw()) / 180F) * 3.141593F)) * Mth.cos((float) ((stationaryRocketBlockEntity.getShotPitch() / 180F) * 3.141593F)) * range;
		float barrelY = -Mth.sin((float) ((stationaryRocketBlockEntity.getShotPitch() / 180F) * 3.141593F)) * range - 0.1F;
		float barrelZ = Mth.cos((float) (((stationaryRocketBlockEntity.getShotYaw()) / 180F) * 3.141593F)) * Mth.cos((float) ((stationaryRocketBlockEntity.getShotPitch() / 180F) * 3.141593F)) * range;
		
		for (int i = 0; i < getProjectileCount(); i++) 
		{
			RocketProjectile entityRocket = new RocketProjectile(
					worldIn, shooter, 
					x + barrelX, 
					y + barrelY + stationaryRocketBlockEntity.getShotHeight(),
					z + barrelZ);
			
			entityRocket.setDamage(getProjectileDamage());
			entityRocket.setProjectileSize(getProjectileSize());
			entityRocket.setProjectileType(getAmmoType());
			entityRocket.setBypassArmorPercentage(getProjectileArmorBypassPercentage());
			entityRocket.setEffectStrength(getEffectPotency());
			entityRocket.setEffectTicks(getEffectTicks());
			projectileEntityList.add(entityRocket);
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
		ProjectileType ammoType = ProjectileType.EXPLOSIVE_SHELL;
		
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
	ProjectileType ammoType = ProjectileType.EXPLOSIVE_SHELL;
	
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
