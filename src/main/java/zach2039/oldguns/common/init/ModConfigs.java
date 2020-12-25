package zach2039.oldguns.common.init;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import zach2039.oldguns.common.OldGuns;

public class ModConfigs {
	
	//public static ConfigCategoryGeneral configGeneral = new ConfigCategoryGeneral();
	//public static ConfigCategoryFirearms configFirearms = new ConfigCategoryFirearms();
	//public static ConfigCategoryArtillery configArtillery = new ConfigCategoryArtillery();
	
	@Config(modid = OldGuns.MODID, category = "General")
	public static class ConfigCategoryGeneral {}
	
	@Config(modid = OldGuns.MODID, category = "Firearms")
	public static class ConfigCategoryFirearms {
		@Name("Matchlock Derringer")
		public static ConfigCategoryFirearmSettings configMatchlockDerringer = new ConfigCategoryFirearmSettings(8, 3f, 3f, 1f, 1f); 
		@Name("Matchlock Pistol")
		public static ConfigCategoryFirearmSettings configMatchlockPistol = new ConfigCategoryFirearmSettings(12, 3.25f, 6f, 1f, 1f); 
		@Name("Matchlock Arquebus")
		public static ConfigCategoryFirearmSettings configMatchlockArquebus = new ConfigCategoryFirearmSettings(16, 3.5f, 9f, 1f, 1f); 
		@Name("Matchlock Caliver")
		public static ConfigCategoryFirearmSettings configMatchlockCaliver = new ConfigCategoryFirearmSettings(20, 3.75f, 12f, 1f, 1f); 
		@Name("Matchlock Musket")
		public static ConfigCategoryFirearmSettings configMatchlockMusket = new ConfigCategoryFirearmSettings(24, 4f, 15f, 1f, 1f); 
		@Name("Matchlock Long Musket")
		public static ConfigCategoryFirearmSettings configMatchlockLongMusket = new ConfigCategoryFirearmSettings(28, 4.25f, 18f, 1f, 1f); 
		@Name("Matchlock Musketoon")
		public static ConfigCategoryFirearmSettings configMatchlockMusketoon = new ConfigCategoryFirearmSettings(24, 3.75f, 14f, 1f, 1f); 
		@Name("Matchlock Blunderbus")
		public static ConfigCategoryFirearmSettings configMatchlockBlunderbus = new ConfigCategoryFirearmSettings(24, 3.25f, 6f, 1f, 1f); 
		
		@Name("Flintlock Derringer")
		public static ConfigCategoryFirearmSettings configFlintlockDerringer = new ConfigCategoryFirearmSettings(28, 3.25f, 5f, 1f, 1f); 
		@Name("Flintlock Pistol")
		public static ConfigCategoryFirearmSettings configFlintlockPistol = new ConfigCategoryFirearmSettings(32, 3.5f, 8f, 1f, 1f); 
		@Name("Flintlock Arquebus")
		public static ConfigCategoryFirearmSettings configFlintlockArquebus = new ConfigCategoryFirearmSettings(36, 3.75f, 11f, 1f, 1f); 
		@Name("Flintlock Caliver")
		public static ConfigCategoryFirearmSettings configFlintlockCaliver = new ConfigCategoryFirearmSettings(40, 4f, 14f, 1f, 1f); 
		@Name("Flintlock Musket")
		public static ConfigCategoryFirearmSettings configFlintlockMusket = new ConfigCategoryFirearmSettings(44, 4.25f, 17f, 1f, 1f); 
		@Name("Flintlock Musketoon")
		public static ConfigCategoryFirearmSettings configFlintlockMusketoon = new ConfigCategoryFirearmSettings(44, 4f, 16f, 1f, 1f); 
		@Name("Flintlock Blunderbus")
		public static ConfigCategoryFirearmSettings configFlintlockBlunderbus = new ConfigCategoryFirearmSettings(44, 3.5f, 8f, 1f, 1f); 
		
		public static class ConfigCategoryFirearmSettings {
			@Name("Enable crafting recipe")
			@Comment("Set false to disable recipe for this firearm.")
			@RequiresMcRestart
			public boolean enableRecipe = true;
			
			@Name("Firearm durability")
			@Comment("Affects the number of times a firearm can be shot/used before breaking.")
			public int durability = 32;
			
			@Name("Projectile speed")
			@Comment("Affects the speed of projectiles followed from this firearm.")
			public float projectileSpeed = 2f; 
			
			@Name("Base effective range")
			@Comment({
				"Affects the default range past which projectiles fired from this firearm do half damage.",
				"This is sometimes overriden by the type of projectile loaded."
			})
			public float baseEffectiveRange = 10f; 
			
			@Name("Base shot deviation")
			@Comment({
				"Affects the deviation of projectiles fired from this firearm.",
				"This is multiplied with the base deviation of the ammo loaded to get the final deviation."
			})
			public float baseShotDeviationModifier = 1f; 
			
			@Name("Base shot damage modifier")
			@Comment({
				"Affects the damage of projectiles fired from this firearm.",
				"This is multiplied with the base damage of the ammo loaded to get the final damage."
			})
			public float baseShotDamageModifier = 1f;
			
			public ConfigCategoryFirearmSettings(int defaultDurability, float defaultProjectileSpeed, float defaultEffectiveRange, float defaultShotDeviationModifier, float defaultShotDamageModifier) {
				this.durability = defaultDurability;
				this.projectileSpeed = defaultProjectileSpeed;
				this.baseEffectiveRange = defaultEffectiveRange;
				this.baseShotDeviationModifier = defaultShotDeviationModifier;
				this.baseShotDamageModifier = defaultShotDamageModifier;
			}
			
		}
	}
	
	@Config(modid = OldGuns.MODID, category = "Artillery")
	public static class ConfigCategoryArtillery {
		@Name("Drop artillery item on break")
		@Comment("Set true to make artillery pieces drop their item rather than their ingredients when broken.")
		public static boolean dropArtilleryItemOnBreak = false;
		
		@Name("Medium Cannon")
        public static ConfigCategoryArtillerySettings configMediumCannon = new ConfigCategoryArtillerySettings(2.5f, 500f, 1f, 1f);
		@Name("Naval Cannon")
        public static ConfigCategoryArtillerySettings configNavalCannon = new ConfigCategoryArtillerySettings(2.5f, 500f, 1f, 1f);
		
		public static class ConfigCategoryArtillerySettings {
			@Name("Enable crafting recipe")
			@Comment("Set false to disable recipe for this artillery piece.")
			@RequiresMcRestart
			public static boolean enableRecipe = true;
			
			@Name("Projectile speed")
			@Comment("Affects the speed of projectiles followed from this artillery piece.")
			public float projectileSpeed = 2.5f; 
			
			@Name("Base effective range")
			@Comment({
				"Affects the default range past which projectiles fired from this artillery piece do half damage.",
				"This is sometimes overriden by the type of projectile loaded."
			})
			public float baseEffectiveRange = 500f; 
			
			@Name("Base shot deviation")
			@Comment({
				"Affects the deviation of projectiles fired from this artillery piece.",
				"This is multiplied with the base deviation of the ammo loaded to get the final deviation."
			})
			public float baseShotDeviationModifier = 1f; 
			
			@Name("Base shot damage modifier")
			@Comment({
				"Affects the damage of projectiles fired from this artillery piece.",
				"This is multiplied with the base damage of the ammo loaded to get the final damage."
			})
			public float baseShotDamageModifier = 1f;
			
			public ConfigCategoryArtillerySettings(float defaultProjectileSpeed, float defaultEffectiveRange, float defaultShotDeviationModifier, float defaultShotDamageModifier) {
				this.projectileSpeed = defaultProjectileSpeed;
				this.baseEffectiveRange = defaultEffectiveRange;
				this.baseShotDeviationModifier = defaultShotDeviationModifier;
				this.baseShotDamageModifier = defaultShotDamageModifier;
			}
		}
	}
	
}
