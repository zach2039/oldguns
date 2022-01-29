package com.zach2039.oldguns.api.firearm;

import com.zach2039.oldguns.config.OldGunsConfig;
import com.zach2039.oldguns.config.OldGunsConfig.BreechloadingFirearmAttributes;
import com.zach2039.oldguns.config.OldGunsConfig.FirearmSettings;
import com.zach2039.oldguns.config.OldGunsConfig.MuzzleloadingFirearmAttributes;

public class FirearmType {
	
	private static final FirearmSettings FIREARM_SETTINGS = OldGunsConfig.SERVER.firearmSettings;
	
	public enum Muzzleloaders {
		// Matchlocks
		MATCHLOCK_DERRINGER(1, false, FirearmSize.SMALL, FirearmWaterResiliency.VERY_POOR, FIREARM_SETTINGS.matchlockSettings.matchlock_derringer),
		MATCHLOCK_PISTOL(1, false, FirearmSize.SMALL, FirearmWaterResiliency.VERY_POOR, FIREARM_SETTINGS.matchlockSettings.matchlock_pistol),
		MATCHLOCK_BLUNDERBUSS_PISTOL(1, false, FirearmSize.SMALL, FirearmWaterResiliency.VERY_POOR, FIREARM_SETTINGS.matchlockSettings.matchlock_blunderbuss_pistol),
		MATCHLOCK_ARQUEBUS(1, false, FirearmSize.SMALL, FirearmWaterResiliency.VERY_POOR, FIREARM_SETTINGS.matchlockSettings.matchlock_arquebus),
		MATCHLOCK_CALIVER(1, false, FirearmSize.MEDIUM, FirearmWaterResiliency.VERY_POOR, FIREARM_SETTINGS.matchlockSettings.matchlock_caliver),
		MATCHLOCK_MUSKETOON(1, false, FirearmSize.MEDIUM, FirearmWaterResiliency.VERY_POOR, FIREARM_SETTINGS.matchlockSettings.matchlock_musketoon),
		MATCHLOCK_MUSKET(1, false, FirearmSize.LARGE, FirearmWaterResiliency.VERY_POOR, FIREARM_SETTINGS.matchlockSettings.matchlock_musket),
		MATCHLOCK_LONG_MUSKET(1, false, FirearmSize.LARGE, FirearmWaterResiliency.VERY_POOR, FIREARM_SETTINGS.matchlockSettings.matchlock_long_musket),
		MATCHLOCK_BLUNDERBUSS(1, false, FirearmSize.LARGE, FirearmWaterResiliency.VERY_POOR, FIREARM_SETTINGS.matchlockSettings.matchlock_blunderbuss),
		// Wheellocks
		WHEELLOCK_DERRINGER(1, false, FirearmSize.SMALL, FirearmWaterResiliency.FAIR, FIREARM_SETTINGS.wheellockSettings.wheellock_derringer),
		WHEELLOCK_PISTOL(1, false, FirearmSize.SMALL, FirearmWaterResiliency.FAIR, FIREARM_SETTINGS.wheellockSettings.wheellock_pistol),
		WHEELLOCK_DOUBLEBARREL_PISTOL(1, false, FirearmSize.SMALL, FirearmWaterResiliency.FAIR, FIREARM_SETTINGS.wheellockSettings.wheellock_doublebarrel_pistol),
		WHEELLOCK_BLUNDERBUSS_PISTOL(1, false, FirearmSize.SMALL, FirearmWaterResiliency.FAIR, FIREARM_SETTINGS.wheellockSettings.wheellock_blunderbuss_pistol),
		WHEELLOCK_ARQUEBUS(1, false, FirearmSize.SMALL, FirearmWaterResiliency.FAIR, FIREARM_SETTINGS.wheellockSettings.wheellock_arquebus),
		WHEELLOCK_CALIVER(1, false, FirearmSize.MEDIUM, FirearmWaterResiliency.FAIR, FIREARM_SETTINGS.wheellockSettings.wheellock_caliver),
		WHEELLOCK_MUSKETOON(1, false, FirearmSize.MEDIUM, FirearmWaterResiliency.FAIR, FIREARM_SETTINGS.wheellockSettings.wheellock_musketoon),
		WHEELLOCK_MUSKET(1, false, FirearmSize.LARGE, FirearmWaterResiliency.FAIR, FIREARM_SETTINGS.wheellockSettings.wheellock_musket),
		WHEELLOCK_LONG_MUSKET(1, false, FirearmSize.LARGE, FirearmWaterResiliency.FAIR, FIREARM_SETTINGS.wheellockSettings.wheellock_long_musket),
		WHEELLOCK_BLUNDERBUSS(1, false, FirearmSize.LARGE, FirearmWaterResiliency.FAIR, FIREARM_SETTINGS.wheellockSettings.wheellock_blunderbuss),
		WHEELLOCK_HAND_MORTAR(1, false, FirearmSize.HUGE, FirearmWaterResiliency.FAIR, FIREARM_SETTINGS.wheellockSettings.wheellock_hand_mortar),
		// Flintlocks
		FLINTLOCK_DERRINGER(1, false, FirearmSize.SMALL, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_derringer),
		FLINTLOCK_DUCKFOOT_DERRINGER(3, true, FirearmSize.SMALL, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_duckfoot_derringer),
		FLINTLOCK_PISTOL(1, false, FirearmSize.SMALL, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_pistol),
		FLINTLOCK_PEPPERBOX_PISTOL(4, false, FirearmSize.SMALL, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_pepperbox_pistol),
		FLINTLOCK_BLUNDERBUSS_PISTOL(1, false, FirearmSize.SMALL, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_blunderbuss_pistol),
		FLINTLOCK_ARQUEBUS(1, false, FirearmSize.SMALL, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_arquebus),
		FLINTLOCK_CALIVER(1, false, FirearmSize.MEDIUM, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_caliver),
		FLINTLOCK_MUSKETOON(1, false, FirearmSize.MEDIUM, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_musketoon),
		FLINTLOCK_MUSKET(1, false, FirearmSize.LARGE, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_musket),
		FLINTLOCK_LONG_MUSKET(1, false, FirearmSize.LARGE, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_long_musket),
		FLINTLOCK_NOCK_GUN(5, true, FirearmSize.HUGE, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_nock_gun),
		FLINTLOCK_BLUNDERBUSS(1, false, FirearmSize.LARGE, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_blunderbuss),
		FLINTLOCK_DOUBLEBARREL_BLUNDERBUSS(2, false, FirearmSize.LARGE, FirearmWaterResiliency.POOR, FIREARM_SETTINGS.flintlockSettings.flintlock_doublebarrel_blunderbuss),
		;
		
		Muzzleloaders(int ammoCapacity, boolean firesAllAtOnce, FirearmSize firearmSize, 
				FirearmWaterResiliency firearmWaterResiliency, MuzzleloadingFirearmAttributes muzzleloadingFirearmAttributes) {
			this.ammoCapacity = ammoCapacity;
			this.firesAllAtOnce = firesAllAtOnce;
			this.firearmSize = firearmSize;
			this.firearmWaterResiliency = firearmWaterResiliency;
			this.muzzleloadingFirearmAttributes = muzzleloadingFirearmAttributes;
		}
		
		public int getAmmoCapacity() {
			return this.ammoCapacity;
		}
		
		public boolean firesAllAtOnce() {
			return this.firesAllAtOnce;
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

		public MuzzleloadingFirearmAttributes getAttributes() {
			return this.muzzleloadingFirearmAttributes;
		}
		
		int ammoCapacity;
		
		boolean firesAllAtOnce;
		
		FirearmSize firearmSize;
		
		FirearmWaterResiliency firearmWaterResiliency;
		
		MuzzleloadingFirearmAttributes muzzleloadingFirearmAttributes;
	}
	
	public enum Breechloaders {
		//FLINTLOCK_ARQUEBUS(FirearmSize.SMALL, FirearmWaterResiliency.POOR, OldGunsConfig.SERVER.firearmSettings.flintlockSettings.flintlock_arquebus)
		;
		
		Breechloaders(FirearmSize firearmSize, FirearmWaterResiliency firearmWaterResiliency, 
				BreechloadingFirearmAttributes breechloadingFirearmAttributes) {
			this.firearmSize = firearmSize;
			this.firearmWaterResiliency = firearmWaterResiliency;
			this.breechloadingFirearmAttributes = breechloadingFirearmAttributes;
		}
		
		public BreechloadingFirearmAttributes getAttributes() {
			return this.breechloadingFirearmAttributes;
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
		
		BreechloadingFirearmAttributes breechloadingFirearmAttributes;
	}
}


