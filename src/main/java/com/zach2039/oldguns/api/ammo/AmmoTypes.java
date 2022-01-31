package com.zach2039.oldguns.api.ammo;

import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.config.OldGunsConfig.ArtilleryAmmoAttributes;
import com.zach2039.oldguns.config.OldGunsConfig.ArtilleryAmmoSettings;
import com.zach2039.oldguns.config.OldGunsConfig.FirearmAmmoAttributes;
import com.zach2039.oldguns.config.OldGunsConfig.FirearmAmmoSettings;

public class AmmoTypes {
	
	private static final FirearmAmmoSettings FIREARM_AMMO_SETTINGS = OldGunsConfig.SERVER.firearmSettings.ammoSettings;
	private static final ArtilleryAmmoSettings ARTILLERY_AMMO_SETTINGS = OldGunsConfig.SERVER.artillerySettings.artilleryAmmoSettings;
	
	public enum FirearmAmmo {
		// Stone
		SMALL_STONE_MUSKET_BALL(ProjectileType.MUSKET_BALL, FIREARM_AMMO_SETTINGS.small_stone_musket_ball),
		MEDIUM_STONE_MUSKET_BALL(ProjectileType.MUSKET_BALL, FIREARM_AMMO_SETTINGS.medium_stone_musket_ball),
		LARGE_STONE_MUSKET_BALL(ProjectileType.MUSKET_BALL, FIREARM_AMMO_SETTINGS.large_stone_musket_ball),
		SMALL_STONE_BIRDSHOT(ProjectileType.BIRDSHOT, FIREARM_AMMO_SETTINGS.small_stone_birdshot),
		MEDIUM_STONE_BIRDSHOT(ProjectileType.BIRDSHOT, FIREARM_AMMO_SETTINGS.medium_stone_birdshot),
		LARGE_STONE_BIRDSHOT(ProjectileType.BIRDSHOT, FIREARM_AMMO_SETTINGS.large_stone_birdshot),
		// Iron
		SMALL_IRON_MUSKET_BALL(ProjectileType.MUSKET_BALL, FIREARM_AMMO_SETTINGS.small_iron_musket_ball),
		MEDIUM_IRON_MUSKET_BALL(ProjectileType.MUSKET_BALL, FIREARM_AMMO_SETTINGS.medium_iron_musket_ball),
		LARGE_IRON_MUSKET_BALL(ProjectileType.MUSKET_BALL, FIREARM_AMMO_SETTINGS.large_iron_musket_ball),
		SMALL_IRON_BIRDSHOT(ProjectileType.MUSKET_BALL, FIREARM_AMMO_SETTINGS.small_iron_birdshot),
		MEDIUM_IRON_BIRDSHOT(ProjectileType.MUSKET_BALL, FIREARM_AMMO_SETTINGS.medium_iron_birdshot),
		LARGE_IRON_BIRDSHOT(ProjectileType.MUSKET_BALL, FIREARM_AMMO_SETTINGS.large_iron_birdshot),
		SMALL_IRON_BUCKSHOT(ProjectileType.MUSKET_BALL, FIREARM_AMMO_SETTINGS.small_iron_buckshot),
		MEDIUM_IRON_BUCKSHOT(ProjectileType.MUSKET_BALL, FIREARM_AMMO_SETTINGS.medium_iron_buckshot),
		LARGE_IRON_BUCKSHOT(ProjectileType.MUSKET_BALL, FIREARM_AMMO_SETTINGS.large_iron_buckshot),
		// Lead
		SMALL_LEAD_MUSKET_BALL(ProjectileType.MUSKET_BALL, FIREARM_AMMO_SETTINGS.small_lead_musket_ball),
		MEDIUM_LEAD_MUSKET_BALL(ProjectileType.MUSKET_BALL, FIREARM_AMMO_SETTINGS.medium_lead_musket_ball),
		LARGE_LEAD_MUSKET_BALL(ProjectileType.MUSKET_BALL, FIREARM_AMMO_SETTINGS.large_lead_musket_ball),
		SMALL_LEAD_BIRDSHOT(ProjectileType.MUSKET_BALL, FIREARM_AMMO_SETTINGS.small_lead_birdshot),
		MEDIUM_LEAD_BIRDSHOT(ProjectileType.MUSKET_BALL, FIREARM_AMMO_SETTINGS.medium_lead_birdshot),
		LARGE_LEAD_BIRDSHOT(ProjectileType.MUSKET_BALL, FIREARM_AMMO_SETTINGS.large_lead_birdshot),
		SMALL_LEAD_BUCKSHOT(ProjectileType.MUSKET_BALL, FIREARM_AMMO_SETTINGS.small_lead_buckshot),
		MEDIUM_LEAD_BUCKSHOT(ProjectileType.MUSKET_BALL, FIREARM_AMMO_SETTINGS.medium_lead_buckshot),
		LARGE_LEAD_BUCKSHOT(ProjectileType.MUSKET_BALL, FIREARM_AMMO_SETTINGS.large_lead_buckshot),
		;
		
		FirearmAmmo(ProjectileType projectileType, FirearmAmmoAttributes firearmAmmoSettings) {
			this.projectileType = projectileType;
			this.firearmAmmoAttributes = firearmAmmoSettings;
		}
		
		public ProjectileType getProjectileType() {
			return this.projectileType;
		}
		
		public FirearmAmmoAttributes getAttributes() {
			return this.firearmAmmoAttributes;
		}
		
		ProjectileType projectileType;
		
		FirearmAmmoAttributes firearmAmmoAttributes;
	}
	
	public enum ArtilleryAmmo {
		// Stone
		// Iron
		SMALL_IRON_CANNONBALL(ProjectileType.CANNONBALL, ARTILLERY_AMMO_SETTINGS.small_iron_cannonball),
		MEDIUM_IRON_CANNONBALL(ProjectileType.CANNONBALL, ARTILLERY_AMMO_SETTINGS.medium_iron_cannonball),
		LARGE_IRON_CANNONBALL(ProjectileType.CANNONBALL, ARTILLERY_AMMO_SETTINGS.large_iron_cannonball),
		SMALL_IRON_CANISTER_SHOT(ProjectileType.CANISTER, ARTILLERY_AMMO_SETTINGS.small_iron_canister_shot),
		MEDIUM_IRON_CANISTER_SHOT(ProjectileType.CANISTER, ARTILLERY_AMMO_SETTINGS.medium_iron_canister_shot),
		LARGE_IRON_CANISTER_SHOT(ProjectileType.CANISTER, ARTILLERY_AMMO_SETTINGS.large_iron_canister_shot),
		SMALL_IRON_GRAPESHOT(ProjectileType.GRAPESHOT, ARTILLERY_AMMO_SETTINGS.small_iron_grapeshot),
		MEDIUM_IRON_GRAPESHOT(ProjectileType.GRAPESHOT, ARTILLERY_AMMO_SETTINGS.medium_iron_grapeshot),
		LARGE_IRON_GRAPESHOT(ProjectileType.GRAPESHOT, ARTILLERY_AMMO_SETTINGS.large_iron_grapeshot),
		// Lead
		;
		
		ArtilleryAmmo(ProjectileType projectileType, ArtilleryAmmoAttributes artilleryAmmoSettings) {
			this.projectileType = projectileType;
			this.artilleryAmmoSettings = artilleryAmmoSettings;
		}
		
		public ProjectileType getProjectileType() {
			return this.projectileType;
		}
		
		public ArtilleryAmmoAttributes getAttributes() {
			return this.artilleryAmmoSettings;
		}
		
		ProjectileType projectileType;
		
		ArtilleryAmmoAttributes artilleryAmmoSettings;
	}
}
