package com.zach2039.oldguns.world.item.ammo.firearm;

import java.util.ArrayList;
import java.util.List;

import com.zach2039.oldguns.OldGuns;
import com.zach2039.oldguns.api.ammo.Ammo;
import com.zach2039.oldguns.api.ammo.AmmoTypes;
import com.zach2039.oldguns.api.ammo.FirearmAmmo;
import com.zach2039.oldguns.api.ammo.ProjectileType;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.init.ModAttributes;
import com.zach2039.oldguns.world.entity.BulletProjectile;
import com.zach2039.oldguns.world.item.equipment.MusketeerHatItem;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FirearmAmmoItem extends Item implements Ammo, FirearmAmmo {
	
	private FirearmAmmoItem(FirearmAmmoProperties builder) {
		super((Properties) builder);
		this.ammoType = builder.ammoType;
		this.projectileDamage = builder.projectileDamage;
		this.projectileSize = builder.projectileSize;
		this.projectileCount = builder.projectileCount;
		this.projectileDeviationModifier = builder.projectileDeviationModifier;
		this.projectileEffectiveRange = builder.projectileEffectiveRange;
		this.projectileArmorBypassPercentage = builder.projectileArmorBypassPercentage;
	}
	
	public FirearmAmmoItem(AmmoTypes.FirearmAmmo entry) {
		this((FirearmAmmoProperties) new FirearmAmmoProperties()
				.projectileCount(entry.getAttributes().projectileCount.get())
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
	public ProjectileType getAmmoType() {
		return this.ammoType;
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
	
	public List<BulletProjectile> createProjectiles(Level worldIn, ItemStack stackIn, LivingEntity shooter)
	{
		/* Create list to hold all projectile entities that this bullet makes when fired. */
		List<BulletProjectile> projectileEntityList = new ArrayList<BulletProjectile>();
	
		// Do special handling of shooter equipment
		float armorBypassPercentage = getProjectileArmorBypassPercentage();
		
		if (OldGunsConfig.SERVER.equipmentSettings.allowEquipmentEffects.get()) {
			for (ItemStack armorStack : shooter.getArmorSlots()) {
				if (OldGunsConfig.SERVER.equipmentSettings.musketeerHatSettings.allowEffects.get()) {
					if (armorStack.getItem() instanceof MusketeerHatItem) {
						MusketeerHatItem hatItem = (MusketeerHatItem)armorStack.getItem();
						for (AttributeModifier modifier  : hatItem.getAttributeModifiers(LivingEntity.getEquipmentSlotForItem(armorStack), armorStack).get(ModAttributes.ARMOR_PIERCE)) {
							armorBypassPercentage += (float) modifier.getAmount();
						}
					}
				}
			}
		}
		
		OldGuns.LOGGER.info(armorBypassPercentage);
		for (int i = 0; i < getProjectileCount(); i++) 
		{
			BulletProjectile entityBullet = new BulletProjectile(worldIn, shooter);
			entityBullet.setDamage(getProjectileDamage());
			entityBullet.setProjectileSize(getProjectileSize());
			entityBullet.setProjectileType(getAmmoType());
			entityBullet.setBypassArmorPercentage(armorBypassPercentage);
			projectileEntityList.add(entityBullet);
		}
		
		return projectileEntityList;
	}
	
	public static class FirearmAmmoProperties extends Properties {
		/**
		 * Ammo type of this firearm ammo item instance.
		 */
		ProjectileType ammoType = ProjectileType.MUSKET_BALL;
		
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
		
		public FirearmAmmoProperties ammoType(ProjectileType ammoType) {
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
		
		public FirearmAmmoProperties projectileArmorBypassPercentage(float projectileArmorBypassPercentage) {
			this.projectileArmorBypassPercentage = projectileArmorBypassPercentage;
			return this;
		}
	}

	/**
	 * Ammo type of this firearm ammo item instance.
	 */
	protected ProjectileType ammoType = ProjectileType.MUSKET_BALL;
	
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
	
	/**
	 * Projectile armor bypass percentage of this ammo
	 */
	protected float projectileArmorBypassPercentage = 0.0f;
}
