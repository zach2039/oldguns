package com.zach2039.oldguns.api.firearm;

import com.zach2039.oldguns.api.ammo.ProjectileType;
import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.config.OldGunsConfig.FirearmAttributes;
import com.zach2039.oldguns.config.OldGunsConfig.FirearmSettings;

public class FirearmTypes {
	
	private static final FirearmSettings FIREARM_SETTINGS = OldGunsConfig.SERVER.firearmSettings;
	
	public enum Muzzleloaders {
		// Matchlocks
		MATCHLOCK_DERRINGER(1, false, MechanismType.MATCHLOCK, ProjectileType.MUSKET_BALL, FirearmSize.SMALL, FirearmWaterResiliency.VERY_POOR, FIREARM_SETTINGS.matchlockSettings.matchlock_derringer),
		MATCHLOCK_PISTOL(1, false, MechanismType.MATCHLOCK, ProjectileType.MUSKET_BALL, FirearmSize.SMALL, FirearmWaterResiliency.VERY_POOR, FIREARM_SETTINGS.matchlockSettings.matchlock_pistol),
		MATCHLOCK_BLUNDERBUSS_PISTOL(1, false, MechanismType.MATCHLOCK, ProjectileType.BIRDSHOT, FirearmSize.SMALL, FirearmWaterResiliency.VERY_POOR, FIREARM_SETTINGS.matchlockSettings.matchlock_blunderbuss_pistol),
		MATCHLOCK_ARQUEBUS(1, false, MechanismType.MATCHLOCK, ProjectileType.MUSKET_BALL, FirearmSize.SMALL, FirearmWaterResiliency.VERY_POOR, FIREARM_SETTINGS.matchlockSettings.matchlock_arquebus),
		MATCHLOCK_CALIVER(1, false, MechanismType.MATCHLOCK, ProjectileType.MUSKET_BALL, FirearmSize.MEDIUM, FirearmWaterResiliency.VERY_POOR, FIREARM_SETTINGS.matchlockSettings.matchlock_caliver),
		MATCHLOCK_MUSKETOON(1, false, MechanismType.MATCHLOCK, ProjectileType.MUSKET_BALL, FirearmSize.MEDIUM, FirearmWaterResiliency.VERY_POOR, FIREARM_SETTINGS.matchlockSettings.matchlock_musketoon),
		MATCHLOCK_MUSKET(1, false, MechanismType.MATCHLOCK, ProjectileType.MUSKET_BALL, FirearmSize.LARGE, FirearmWaterResiliency.VERY_POOR, FIREARM_SETTINGS.matchlockSettings.matchlock_musket),
		MATCHLOCK_LONG_MUSKET(1, false, MechanismType.MATCHLOCK, ProjectileType.MUSKET_BALL, FirearmSize.LARGE, FirearmWaterResiliency.VERY_POOR, FIREARM_SETTINGS.matchlockSettings.matchlock_long_musket),
		MATCHLOCK_BLUNDERBUSS(1, false, MechanismType.MATCHLOCK, ProjectileType.BIRDSHOT, FirearmSize.LARGE, FirearmWaterResiliency.VERY_POOR, FIREARM_SETTINGS.matchlockSettings.matchlock_blunderbuss),
		// Wheellocks
		WHEELLOCK_DERRINGER(1, false, MechanismType.WHEELLOCK, ProjectileType.MUSKET_BALL, FirearmSize.SMALL, FirearmWaterResiliency.FAIR, FIREARM_SETTINGS.wheellockSettings.wheellock_derringer),
		WHEELLOCK_PISTOL(1, false, MechanismType.WHEELLOCK, ProjectileType.MUSKET_BALL, FirearmSize.SMALL, FirearmWaterResiliency.FAIR, FIREARM_SETTINGS.wheellockSettings.wheellock_pistol),
		WHEELLOCK_DOUBLEBARREL_PISTOL(2, false, MechanismType.WHEELLOCK, ProjectileType.MUSKET_BALL, FirearmSize.SMALL, FirearmWaterResiliency.FAIR, FIREARM_SETTINGS.wheellockSettings.wheellock_doublebarrel_pistol),
		WHEELLOCK_BLUNDERBUSS_PISTOL(1, false, MechanismType.WHEELLOCK, ProjectileType.BIRDSHOT, FirearmSize.SMALL, FirearmWaterResiliency.FAIR, FIREARM_SETTINGS.wheellockSettings.wheellock_blunderbuss_pistol),
		WHEELLOCK_ARQUEBUS(1, false, MechanismType.WHEELLOCK, ProjectileType.MUSKET_BALL, FirearmSize.SMALL, FirearmWaterResiliency.FAIR, FIREARM_SETTINGS.wheellockSettings.wheellock_arquebus),
		WHEELLOCK_CALIVER(1, false, MechanismType.WHEELLOCK, ProjectileType.MUSKET_BALL, FirearmSize.MEDIUM, FirearmWaterResiliency.FAIR, FIREARM_SETTINGS.wheellockSettings.wheellock_caliver),
		WHEELLOCK_MUSKETOON(1, false, MechanismType.WHEELLOCK, ProjectileType.MUSKET_BALL, FirearmSize.MEDIUM, FirearmWaterResiliency.FAIR, FIREARM_SETTINGS.wheellockSettings.wheellock_musketoon),
		WHEELLOCK_MUSKET(1, false, MechanismType.WHEELLOCK, ProjectileType.MUSKET_BALL, FirearmSize.LARGE, FirearmWaterResiliency.FAIR, FIREARM_SETTINGS.wheellockSettings.wheellock_musket),
		WHEELLOCK_LONG_MUSKET(1, false, MechanismType.WHEELLOCK, ProjectileType.MUSKET_BALL, FirearmSize.LARGE, FirearmWaterResiliency.FAIR, FIREARM_SETTINGS.wheellockSettings.wheellock_long_musket),
		WHEELLOCK_BLUNDERBUSS(1, false, MechanismType.WHEELLOCK, ProjectileType.BIRDSHOT, FirearmSize.LARGE, FirearmWaterResiliency.FAIR, FIREARM_SETTINGS.wheellockSettings.wheellock_blunderbuss),
		WHEELLOCK_HAND_MORTAR(1, false, MechanismType.WHEELLOCK, ProjectileType.CANNONBALL, FirearmSize.HUGE, FirearmWaterResiliency.FAIR, FIREARM_SETTINGS.wheellockSettings.wheellock_hand_mortar),
		// Flintlocks
		FLINTLOCK_DERRINGER(1, false, MechanismType.FLINTLOCK, ProjectileType.MUSKET_BALL, FirearmSize.SMALL, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_derringer),
		FLINTLOCK_DUCKFOOT_DERRINGER(3, true, MechanismType.FLINTLOCK, ProjectileType.MUSKET_BALL, FirearmSize.SMALL, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_duckfoot_derringer),
		FLINTLOCK_PISTOL(1, false, MechanismType.FLINTLOCK, ProjectileType.MUSKET_BALL, FirearmSize.SMALL, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_pistol),
		FLINTLOCK_PEPPERBOX_PISTOL(4, false, MechanismType.FLINTLOCK, ProjectileType.MUSKET_BALL, FirearmSize.SMALL, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_pepperbox_pistol),
		FLINTLOCK_BLUNDERBUSS_PISTOL(1, false, MechanismType.FLINTLOCK, ProjectileType.BIRDSHOT, FirearmSize.SMALL, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_blunderbuss_pistol),
		FLINTLOCK_ARQUEBUS(1, false, MechanismType.FLINTLOCK, ProjectileType.MUSKET_BALL, FirearmSize.SMALL, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_arquebus),
		FLINTLOCK_CALIVER(1, false, MechanismType.FLINTLOCK, ProjectileType.MUSKET_BALL, FirearmSize.MEDIUM, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_caliver),
		FLINTLOCK_MUSKETOON(1, false, MechanismType.FLINTLOCK, ProjectileType.MUSKET_BALL, FirearmSize.MEDIUM, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_musketoon),
		FLINTLOCK_MUSKET(1, false, MechanismType.FLINTLOCK, ProjectileType.MUSKET_BALL, FirearmSize.LARGE, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_musket),
		FLINTLOCK_LONG_MUSKET(1, false, MechanismType.FLINTLOCK, ProjectileType.MUSKET_BALL, FirearmSize.LARGE, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_long_musket),
		FLINTLOCK_NOCK_GUN(5, true, MechanismType.FLINTLOCK, ProjectileType.MUSKET_BALL, FirearmSize.HUGE, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_nock_gun),
		FLINTLOCK_BLUNDERBUSS(1, false, MechanismType.FLINTLOCK, ProjectileType.BIRDSHOT, FirearmSize.LARGE, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_blunderbuss),
		FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS(2, false, MechanismType.FLINTLOCK, ProjectileType.BIRDSHOT, FirearmSize.LARGE, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_doublebarrel_blunderbuss),
		// Caplocks
		CAPLOCK_DERRINGER(1, false, MechanismType.CAPLOCK, ProjectileType.MUSKET_BALL, FirearmSize.SMALL, FirearmWaterResiliency.GOOD, FIREARM_SETTINGS.flintlockSettings.flintlock_derringer),
		CAPLOCK_DUCKFOOT_DERRINGER(3, true, MechanismType.CAPLOCK, ProjectileType.MUSKET_BALL, FirearmSize.SMALL, FirearmWaterResiliency.GOOD, FIREARM_SETTINGS.flintlockSettings.flintlock_duckfoot_derringer),
		CAPLOCK_PISTOL(1, false, MechanismType.CAPLOCK, ProjectileType.MUSKET_BALL, FirearmSize.SMALL, FirearmWaterResiliency.GOOD, FIREARM_SETTINGS.flintlockSettings.flintlock_pistol),
		CAPLOCK_PEPPERBOX_PISTOL(4, false, MechanismType.CAPLOCK, ProjectileType.MUSKET_BALL, FirearmSize.SMALL, FirearmWaterResiliency.GOOD, FIREARM_SETTINGS.flintlockSettings.flintlock_pepperbox_pistol),
		CAPLOCK_BLUNDERBUSS_PISTOL(1, false, MechanismType.CAPLOCK, ProjectileType.BIRDSHOT, FirearmSize.SMALL, FirearmWaterResiliency.GOOD, FIREARM_SETTINGS.flintlockSettings.flintlock_blunderbuss_pistol),
		CAPLOCK_ARQUEBUS(1, false, MechanismType.CAPLOCK, ProjectileType.MUSKET_BALL, FirearmSize.SMALL, FirearmWaterResiliency.GOOD, FIREARM_SETTINGS.flintlockSettings.flintlock_arquebus),
		CAPLOCK_CALIVER(1, false, MechanismType.CAPLOCK, ProjectileType.MUSKET_BALL, FirearmSize.MEDIUM, FirearmWaterResiliency.GOOD, FIREARM_SETTINGS.flintlockSettings.flintlock_caliver),
		CAPLOCK_MUSKETOON(1, false, MechanismType.CAPLOCK, ProjectileType.MUSKET_BALL, FirearmSize.MEDIUM, FirearmWaterResiliency.GOOD, FIREARM_SETTINGS.flintlockSettings.flintlock_musketoon),
		CAPLOCK_MUSKET(1, false, MechanismType.CAPLOCK, ProjectileType.MUSKET_BALL, FirearmSize.LARGE, FirearmWaterResiliency.GOOD, FIREARM_SETTINGS.flintlockSettings.flintlock_musket),
		CAPLOCK_LONG_MUSKET(1, false, MechanismType.CAPLOCK, ProjectileType.MUSKET_BALL, FirearmSize.LARGE, FirearmWaterResiliency.GOOD, FIREARM_SETTINGS.flintlockSettings.flintlock_long_musket),
		CAPLOCK_BLUNDERBUSS(1, false, MechanismType.CAPLOCK, ProjectileType.BIRDSHOT, FirearmSize.LARGE, FirearmWaterResiliency.GOOD, FIREARM_SETTINGS.flintlockSettings.flintlock_blunderbuss),
		CAPLOCK_DOUBLEBARREL_BLUNDERBUSS(2, false, MechanismType.CAPLOCK, ProjectileType.BIRDSHOT, FirearmSize.LARGE, FirearmWaterResiliency.GOOD, FIREARM_SETTINGS.flintlockSettings.flintlock_doublebarrel_blunderbuss),		
		;
		
		Muzzleloaders(int ammoCapacity, boolean firesAllAtOnce, MechanismType mechanismType, ProjectileType defaultProjectileType, FirearmSize firearmSize, 
				FirearmWaterResiliency firearmWaterResiliency, FirearmAttributes firearmAttributes) {
			this.ammoCapacity = ammoCapacity;
			this.firesAllAtOnce = firesAllAtOnce;
			this.mechanismType = mechanismType;
			this.defaultProjectileType = defaultProjectileType;
			this.firearmSize = firearmSize;
			this.firearmWaterResiliency = firearmWaterResiliency;
			this.firearmAttributes = firearmAttributes;
		}
		
		public int getAmmoCapacity() {
			return this.ammoCapacity;
		}
		
		public boolean firesAllAtOnce() {
			return this.firesAllAtOnce;
		}
		
		public MechanismType getMechanismType() {
			return this.mechanismType;
		}
		
		public ProjectileType getDefaultProjectileType() {
			return this.defaultProjectileType;
		}
		
		public FirearmSize getFirearmSize() {
			return this.firearmSize;
		}
		
		public FirearmWaterResiliency getFirearmWaterResiliency() {
			return this.firearmWaterResiliency;
		}
		
		public FirearmReloadType getFirearmReloadType() {
			return FirearmReloadType.MUZZLELOADER;
		}

		public FirearmAttributes getAttributes() {
			return this.firearmAttributes;
		}
		
		int ammoCapacity;
		
		boolean firesAllAtOnce;
		
		MechanismType mechanismType;
		
		ProjectileType defaultProjectileType;
		
		FirearmSize firearmSize;
		
		FirearmWaterResiliency firearmWaterResiliency;
		
		FirearmAttributes firearmAttributes;
	}
	
	public enum Breechloaders {
		//FLINTLOCK_ARQUEBUS(FirearmSize.SMALL, FirearmWaterResiliency.POOR, OldGunsConfig.SERVER.firearmSettings.flintlockSettings.flintlock_arquebus)
		;
		
		Breechloaders(FirearmSize firearmSize, FirearmWaterResiliency firearmWaterResiliency, 
				FirearmAttributes breechloadingFirearmAttributes) {
			this.firearmSize = firearmSize;
			this.firearmWaterResiliency = firearmWaterResiliency;
			this.firearmAttributes = firearmAttributes;
		}
		
		public FirearmAttributes getAttributes() {
			return this.firearmAttributes;
		}
		
		public FirearmSize getFirearmSize() {
			return this.firearmSize;
		}
		
		public FirearmWaterResiliency getFirearmWaterResiliency() {
			return this.firearmWaterResiliency;
		}
		
		public FirearmReloadType getFirearmReloadType() {
			return FirearmReloadType.BREECHLOADER;
		}
		
		FirearmSize firearmSize;
		
		FirearmWaterResiliency firearmWaterResiliency;
		
		FirearmAttributes firearmAttributes;
	}
	
	public enum MechanismType {
		MATCHLOCK,
		WHEELLOCK,
		FLINTLOCK,
		CAPLOCK
	}
}


