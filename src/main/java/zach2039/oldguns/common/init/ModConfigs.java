package zach2039.oldguns.common.init;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RangeDouble;
import net.minecraftforge.common.config.Config.RangeInt;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import zach2039.oldguns.common.OldGuns;

public class ModConfigs {
	
	@Config(modid = OldGuns.MODID, category = "General")
	public static class ConfigCategoryGeneral {}
	
	@Config(modid = OldGuns.MODID, category = "Firearms")
	public static class ConfigCategoryFirearms {
		@Name("Print firearm debug details to console")
		@Comment("Set true to print debug messages to the console, to help tune firearm settings.")
		public static boolean printFirearmDebugMessages = false;
		
		@Name("Matchlock Derringer")
		public static ConfigCategoryFirearmSettings configMatchlockDerringer = new ConfigCategoryFirearmSettings(
				8,     	// durability              
				3f,    	// projectile speed        
				0.5f,  	// effective range modifier
				5.0f,   // shot deviation            
				1f     	// shot damage modifier    
			); 
		@Name("Matchlock Pistol")
		public static ConfigCategoryFirearmSettings configMatchlockPistol = new ConfigCategoryFirearmSettings(
				12,    	// durability              
				3.25f, 	// projectile speed        
				1.2f,  	// effective range modifier
				2.0f,   // shot deviation          
				1f     	// shot damage modifier    
			); 
		@Name("Matchlock Arquebus")
		public static ConfigCategoryFirearmSettings configMatchlockArquebus = new ConfigCategoryFirearmSettings(
				16,    	// durability              
				3.5f,  	// projectile speed        
				1.0f,  	// effective range modifier
				1.8f,   // shot deviation       
				1f     	// shot damage modifier    
			); 
		@Name("Matchlock Caliver")
		public static ConfigCategoryFirearmSettings configMatchlockCaliver = new ConfigCategoryFirearmSettings(
				20,     // durability              
				3.75f,  // projectile speed        
				1.2f,  	// effective range modifier
				1.6f,   // shot deviation        
				1f      // shot damage modifier    
			); 
		@Name("Matchlock Musket")
		public static ConfigCategoryFirearmSettings configMatchlockMusket = new ConfigCategoryFirearmSettings(
				24,     // durability              
				4f,     // projectile speed        
				1f,    	// effective range modifier
				1.4f,   // shot deviation          
				1f      // shot damage modifier    
			); 
		@Name("Matchlock Long Musket")
		public static ConfigCategoryFirearmSettings configMatchlockLongMusket = new ConfigCategoryFirearmSettings(
				28,     // durability              
				4.25f,  // projectile speed        
				1.2f,  	// effective range modifier
				1.2f,   // shot deviation         
				1f      // shot damage modifier    
			); 
		@Name("Matchlock Musketoon")
		public static ConfigCategoryFirearmSettings configMatchlockMusketoon = new ConfigCategoryFirearmSettings(
				24,     // durability              
				3.75f,  // projectile speed        
				0.8f,    // effective range modifier
				1.5f,   // shot deviation       
				1f      // shot damage modifier    
			); 
		@Name("Matchlock Blunderbuss")
		public static ConfigCategoryFirearmSettings configMatchlockBlunderbuss = new ConfigCategoryFirearmSettings(
				24,     // durability              
				3.25f,  // projectile speed        
				1f,     // effective range modifier
				1.7f,   // shot deviation      
				1f      // shot damage modifier    
			); 
		
		@Name("Flintlock Derringer")
		public static ConfigCategoryFirearmSettings configFlintlockDerringer = new ConfigCategoryFirearmSettings(
				28,     // durability              
				3.25f,  // projectile speed        
				0.5f,  	// effective range modifier
				5.0f,   // shot deviation    
				1f      // shot damage modifier    
			);
		@Name("Flintlock Pistol")
		public static ConfigCategoryFirearmSettings configFlintlockPistol = new ConfigCategoryFirearmSettings(
				32,     // durability              
				3.5f,   // projectile speed        
				1.2f,   // effective range modifier
				2.0f,   // shot deviation     
				1f      // shot damage modifier    
			); 
		@Name("Flintlock Heavy Pistol")
		public static ConfigCategoryFirearmSettings configFlintlockArquebus = new ConfigCategoryFirearmSettings(
				36,     // durability              
				3.75f,  // projectile speed        
				1.0f,   // effective range modifier
				1.8f,   // shot deviation     
				1f      // shot damage modifier    
			); 
		@Name("Flintlock Carbine")
		public static ConfigCategoryFirearmSettings configFlintlockCaliver = new ConfigCategoryFirearmSettings(
				40,     // durability              
				4f,     // projectile speed        
				1.2f,    // effective range modifier
				1.6f,   // shot deviation         
				1f      // shot damage modifier    
			); 
		@Name("Flintlock Musket")
		public static ConfigCategoryFirearmSettings configFlintlockMusket = new ConfigCategoryFirearmSettings(
				44,     // durability              
				4.25f,  // projectile speed        
				1f,   	// effective range modifier
				1.4f,   // shot deviation          
				1f      // shot damage modifier    
			); 
		@Name("Flintlock Long Musket")
		public static ConfigCategoryFirearmSettings configFlintlockLongMusket = new ConfigCategoryFirearmSettings(
				48,     // durability              
				4.5f,   // projectile speed        
				1.2f,    // effective range modifier
				1.2f,   // shot deviation    
				1f      // shot damage modifier    
			); 
		@Name("Flintlock Musketoon")
		public static ConfigCategoryFirearmSettings configFlintlockMusketoon = new ConfigCategoryFirearmSettings(
				44,     // durability              
				4f,     // projectile speed        
				0.8f,    // effective range modifier
				1.5f,   // shot deviation         
				1f      // shot damage modifier    
			); 
		@Name("Flintlock Blunderbuss")
		public static ConfigCategoryFirearmSettings configFlintlockBlunderbuss = new ConfigCategoryFirearmSettings(
				44,     // durability              
				3.5f,   // projectile speed        
				1.0f,    // effective range modifier
				1.7f,   // shot deviation          
				1f      // shot damage modifier    
			); 
		
		@Name("Flintlock Breechloading Pistol")
		public static ConfigCategoryBreechloadingFirearmSettings configFlintlockBreechloadingPistol = new ConfigCategoryBreechloadingFirearmSettings(
				24,     // durability              
				3.5f,   // projectile speed        
				1.2f,    // effective range modifier
				2.0f,   // shot deviation      
				1f,     // shot damage modifier    
				70		// reloading ticks
			); 
		@Name("Flintlock Breechloading Heavy Pistol")
		public static ConfigCategoryBreechloadingFirearmSettings configFlintlockBreechloadingArquebus = new ConfigCategoryBreechloadingFirearmSettings(
				28,     // durability              
				3.75f,  // projectile speed        
				1.0f,    // effective range modifier
				1.8f,   // shot deviation             
				1f,     // shot damage modifier    
				80		// reloading ticks
			); 
		@Name("Flintlock Breechloading Carbine")
		public static ConfigCategoryBreechloadingFirearmSettings configFlintlockBreechloadingCaliver = new ConfigCategoryBreechloadingFirearmSettings(
				32,     // durability              
				4.0f,   // projectile speed        
				1.2f,    // effective range modifier
				1.6f,   // shot deviation         
				1f,     // shot damage modifier    
				90		// reloading ticks
			); 
		@Name("Flintlock Breechloading Musket")
		public static ConfigCategoryBreechloadingFirearmSettings configFlintlockBreechloadingMusket = new ConfigCategoryBreechloadingFirearmSettings(
				36,     // durability              
				4.25f,  // projectile speed        
				1f,  	// effective range modifier
				1.4f,   // shot deviation      
				1f,     // shot damage modifier    
				100		// reloading ticks
			); 
		@Name("Flintlock Breechloading Long Musket")
		public static ConfigCategoryBreechloadingFirearmSettings configFlintlockBreechloadingLongMusket = new ConfigCategoryBreechloadingFirearmSettings(
				40,     // durability              
				4.5f,   // projectile speed        
				1.2f,    // effective range modifier
				1.2f,   // shot deviation       
				1f,     // shot damage modifier    
				110		// reloading ticks
			); 
		
		@Name("Caplock Derringer")
		public static ConfigCategoryFirearmSettings configCaplockDerringer = new ConfigCategoryFirearmSettings(
				48,     // durability              
				3.5f,   // projectile speed        
				0.5f,  	// effective range modifier
				5.0f,   // shot deviation         
				1f      // shot damage modifier    
			); 
		@Name("Caplock Pistol")
		public static ConfigCategoryFirearmSettings configCaplockPistol = new ConfigCategoryFirearmSettings(
				52,     // durability              
				3.75f,  // projectile speed        
				1.2f,    // effective range modifier
				2.0f,   // shot deviation       
				1f      // shot damage modifier    
			); 
		@Name("Caplock Heavy Pistol")
		public static ConfigCategoryFirearmSettings configCaplockArquebus = new ConfigCategoryFirearmSettings(
				56,     // durability              
				4.0f,   // projectile speed        
				1.0f,    // effective range modifier
				1.8f,   // shot deviation          
				1f      // shot damage modifier    
			); 
		@Name("Caplock Carbine")
		public static ConfigCategoryFirearmSettings configCaplockCaliver = new ConfigCategoryFirearmSettings(
				60,     // durability              
				4.25f,  // projectile speed        
				1.2f,    // effective range modifier
				1.6f,   // shot deviation          
				1f      // shot damage modifier    
			); 
		@Name("Caplock Musket")
		public static ConfigCategoryFirearmSettings configCaplockMusket = new ConfigCategoryFirearmSettings(
				64,		// durability              
				4.5f,	// projectile speed        
				1.0f,    // effective range modifier
				1.4f,   // shot deviation       
				1f		// shot damage modifier    
			); 
		@Name("Caplock Long Musket")
		public static ConfigCategoryFirearmSettings configCaplockLongMusket = new ConfigCategoryFirearmSettings(
				68,		// durability              
				4.75f,	// projectile speed        
				1.2f,    // effective range modifier
				1.2f,   // shot deviation     
				1f		// shot damage modifier    
			); 
		@Name("Caplock Musketoon")
		public static ConfigCategoryFirearmSettings configCaplockMusketoon = new ConfigCategoryFirearmSettings(
				64,		// durability
				4.25f,	// projectile speed
				0.8f,    // effective range modifier
				1.5f,   // shot deviation
				1f		// shot damage modifier
			);   
		@Name("Caplock Blunderbuss")
		public static ConfigCategoryFirearmSettings configCaplockBlunderbuss = new ConfigCategoryFirearmSettings(
				44,		// durability
				3.75f,	// projectile speed
				1.0f,	// effective range modifier
				1.7f,   // shot deviation      
				1f		// shot damage modifier
			); 
		
		@Name("Caplock Breechloading Pistol")
		public static ConfigCategoryBreechloadingFirearmSettings configCaplockBreechloadingPistol = new ConfigCategoryBreechloadingFirearmSettings(
				44,     // durability              
				3.5f,   // projectile speed        
				1.2f,	// effective range modifier
				2.0f,   // shot deviation         
				1f,     // shot damage modifier    
				50		// reloading ticks
			); 
		@Name("Caplock Breechloading Heavy Pistol")
		public static ConfigCategoryBreechloadingFirearmSettings configCaplockBreechloadingArquebus = new ConfigCategoryBreechloadingFirearmSettings(
				48,     // durability              
				3.75f,  // projectile speed        
				1.0f,	// effective range modifier
				1.8f,   // shot deviation        
				1f,     // shot damage modifier    
				60		// reloading ticks
			); 
		@Name("Caplock Breechloading Carbine")
		public static ConfigCategoryBreechloadingFirearmSettings configCaplockBreechloadingCaliver = new ConfigCategoryBreechloadingFirearmSettings(
				52,     // durability              
				4.0f,   // projectile speed        
				1.2f,	// effective range modifier
				1.6f,   // shot deviation            
				1f,     // shot damage modifier    
				70		// reloading ticks
			); 
		@Name("Caplock Breechloading Musket")
		public static ConfigCategoryBreechloadingFirearmSettings configCaplockBreechloadingMusket = new ConfigCategoryBreechloadingFirearmSettings(
				56,     // durability              
				4.25f,  // projectile speed        
				1.0f,	// effective range modifier
				1.4f,   // shot deviation          
				1f,     // shot damage modifier    
				80		// reloading ticks
			); 
		@Name("Caplock Breechloading Long Musket")
		public static ConfigCategoryBreechloadingFirearmSettings configCaplockBreechloadingLongMusket = new ConfigCategoryBreechloadingFirearmSettings(
				60,     // durability              
				4.5f,   // projectile speed        
				1.2f,	// effective range modifier
				1.2f,   // shot deviation          
				1f,     // shot damage modifier    
				90		// reloading ticks 
			); 
		
		public static class ConfigCategoryFirearmSettings {	
			@Name("Firearm durability")
			@Comment("Affects the number of times a firearm can be shot/used before breaking.")
			@RangeInt(min = 1) 
			public int durability = 32;
			
			@Name("Projectile speed")
			@Comment("Affects the speed of projectiles followed from this firearm.")
			@RangeDouble(min = 0.1f, max = 1000f) 
			public float projectileSpeed = 2f; 
			
			@Name("Base effective range")
			@Comment({
				"Affects the default range past which projectiles fired from this firearm do half damage.",
				"This is sometimes overriden by the type of projectile loaded."
			})
			@RangeDouble(min = 0.1f, max = 10000f) 
			public float baseEffectiveRange = 10f; 
			
			@Name("Base shot deviation")
			@Comment({
				"Affects the deviation of projectiles fired from this firearm.",
				"This is multiplied with the base deviation of the ammo loaded to get the final deviation."
			})
			@RangeDouble(min = 0.1f, max = 1000f) 
			public float baseShotDeviationModifier = 1f; 
			
			@Name("Base shot damage modifier")
			@Comment({
				"Affects the damage of projectiles fired from this firearm.",
				"This is multiplied with the base damage of the ammo loaded to get the final damage."
			})
			@RangeDouble(min = 0.1f, max = 1000f) 
			public float baseShotDamageModifier = 1f;
			
			public ConfigCategoryFirearmSettings(int defaultDurability, float defaultProjectileSpeed, float defaultEffectiveRange, float defaultShotDeviationModifier, float defaultShotDamageModifier) {
				this.durability = defaultDurability;
				this.projectileSpeed = defaultProjectileSpeed;
				this.baseEffectiveRange = defaultEffectiveRange;
				this.baseShotDeviationModifier = defaultShotDeviationModifier;
				this.baseShotDamageModifier = defaultShotDamageModifier;
			}
			
		}
		
		public static class ConfigCategoryBreechloadingFirearmSettings {	
			@Name("Firearm durability")
			@Comment("Affects the number of times a firearm can be shot/used before breaking.")
			@RangeInt(min = 1)
			public int durability = 32;
			
			@Name("Projectile speed")
			@Comment("Affects the speed of projectiles followed from this firearm.")
			public float projectileSpeed = 2f; 
			
			@Name("Base effective range")
			@Comment({
				"Affects the default range past which projectiles fired from this firearm do half damage.",
				"This is sometimes overriden by the type of projectile loaded."
			})
			@RangeDouble(min = 0.1f, max = 10000f) 
			public float baseEffectiveRange = 10f; 
			
			@Name("Base shot deviation")
			@Comment({
				"Affects the deviation of projectiles fired from this firearm.",
				"This is multiplied with the base deviation of the ammo loaded to get the final deviation."
			})
			@RangeDouble(min = 0.1f, max = 1000f) 
			public float baseShotDeviationModifier = 1f; 
			
			@Name("Base shot damage modifier")
			@Comment({
				"Affects the damage of projectiles fired from this firearm.",
				"This is multiplied with the base damage of the ammo loaded to get the final damage."
			})
			@RangeDouble(min = 0.1f, max = 1000f) 
			public float baseShotDamageModifier = 1f;
			
			@Name("Ticks required for reload")
			@Comment({
				"Affects the reload duration of this breechloading firearm.",
				"For reference, 20 ticks is 1 second."
			})
			@RangeInt(min = 1)
			public int requiredReloadTicks = 80;
			
			public ConfigCategoryBreechloadingFirearmSettings(int defaultDurability, float defaultProjectileSpeed, float defaultEffectiveRange, float defaultShotDeviationModifier, float defaultShotDamageModifier, int defaultReloadTicks) {
				this.durability = defaultDurability;
				this.projectileSpeed = defaultProjectileSpeed;
				this.baseEffectiveRange = defaultEffectiveRange;
				this.baseShotDeviationModifier = defaultShotDeviationModifier;
				this.baseShotDamageModifier = defaultShotDamageModifier;
				this.requiredReloadTicks = defaultReloadTicks;
			}
		}
	}
	
	@Config(modid = OldGuns.MODID, category = "FirearmAmmo")
	public static class ConfigCategoryFirearmAmmo {
		@Name("Small Stone Musket Ball")
		public static ConfigCategoryFirearmAmmoSettings configSmallStoneMusketBall = new ConfigCategoryFirearmAmmoSettings(
				8,     // max stacksize
				1,     // projectile count
				10.0f, // projectile damage
				0.3f,  // projectile size
				15.0f, // projectile effective range
				1.0f   // projectile deviation modifier
			);
		@Name("Medium Stone Musket Ball")
		public static ConfigCategoryFirearmAmmoSettings configMediumStoneMusketBall = new ConfigCategoryFirearmAmmoSettings(
				6,     // max stacksize
				1,     // projectile count
				13.0f, // projectile damage
				0.4f,  // projectile size
				30.0f, // projectile effective range
				1.0f   // projectile deviation modifier
			);
		@Name("Large Stone Musket Ball")
		public static ConfigCategoryFirearmAmmoSettings configLargeStoneMusketBall = new ConfigCategoryFirearmAmmoSettings(
				4,     // max stacksize
				1,     // projectile count
				16.0f, // projectile damage
				0.5f,  // projectile size
				55.0f, // projectile effective range
				1.0f   // projectile deviation modifier
			); 
		@Name("Small Stone Birdshot")
		public static ConfigCategoryFirearmAmmoSettings configSmallStoneBirdshot = new ConfigCategoryFirearmAmmoSettings(
				8,     // max stacksize
				6,     // projectile count
				2.0f,  // projectile damage
				0.1f,  // projectile size
				5.0f,  // projectile effective range
				3.0f   // projectile deviation modifier
			);
		@Name("Medium Stone Birdshot")
		public static ConfigCategoryFirearmAmmoSettings configMediumStoneBirdshot = new ConfigCategoryFirearmAmmoSettings(
				6,     // max stacksize
				8,     // projectile count
				2.0f,  // projectile damage
				0.1f,  // projectile size
				5.0f,  // projectile effective range
				3.0f   // projectile deviation modifier
			);
		@Name("Large Stone Birdshot")
		public static ConfigCategoryFirearmAmmoSettings configLargeStoneBirdshot = new ConfigCategoryFirearmAmmoSettings(
				6,     // max stacksize
				10,    // projectile count
				2.0f,  // projectile damage
				0.1f,  // projectile size
				5.0f,  // projectile effective range
				3.0f   // projectile deviation modifier
			);
		
		@Name("Small Iron Musket Ball")
		public static ConfigCategoryFirearmAmmoSettings configSmallIronMusketBall = new ConfigCategoryFirearmAmmoSettings(
				8,     // max stacksize
				1,     // projectile count
				20.0f, // projectile damage
				0.3f,  // projectile size
				25.0f, // projectile effective range
				1.0f   // projectile deviation modifier
			);
		@Name("Medium Iron Musket Ball")
		public static ConfigCategoryFirearmAmmoSettings configMediumIronMusketBall = new ConfigCategoryFirearmAmmoSettings(
				6,     // max stacksize
				1,     // projectile count
				23.0f, // projectile damage
				0.4f,  // projectile size
				50.0f, // projectile effective range
				1.0f   // projectile deviation modifier
			);
		@Name("Large Iron Musket Ball")
		public static ConfigCategoryFirearmAmmoSettings configLargeIronMusketBall = new ConfigCategoryFirearmAmmoSettings(
				4,     // max stacksize
				1,     // projectile count
				26.0f, // projectile damage
				0.5f,  // projectile size
				75.0f, // projectile effective range
				1.0f   // projectile deviation modifier
			); 
		@Name("Small Iron Birdshot")
		public static ConfigCategoryFirearmAmmoSettings configSmallIronBirdshot = new ConfigCategoryFirearmAmmoSettings(
				8,     // max stacksize
				6,     // projectile count
				3.0f,  // projectile damage
				0.2f,  // projectile size
				7.0f,  // projectile effective range
				3.0f   // projectile deviation modifier
			);
		@Name("Medium Iron Birdshot")
		public static ConfigCategoryFirearmAmmoSettings configMediumIronBirdshot = new ConfigCategoryFirearmAmmoSettings(
				6,     // max stacksize
				8,     // projectile count
				3.0f,  // projectile damage
				0.2f,  // projectile size
				7.0f,  // projectile effective range
				3.0f   // projectile deviation modifier
			);
		@Name("Large Iron Birdshot")
		public static ConfigCategoryFirearmAmmoSettings configLargeIronBirdshot = new ConfigCategoryFirearmAmmoSettings(
				4,     // max stacksize
				10,    // projectile count
				3.0f,  // projectile damage
				0.2f,  // projectile size
				7.0f,  // projectile effective range
				3.0f   // projectile deviation modifier
			);
		@Name("Small Iron Buckshot")
		public static ConfigCategoryFirearmAmmoSettings configSmallIronBuckshot = new ConfigCategoryFirearmAmmoSettings(
				8,     // max stacksize
				3,     // projectile count
				15.0f, // projectile damage
				0.3f,  // projectile size
				30.0f, // projectile effective range
				1.5f   // projectile deviation modifier
			);
		@Name("Medium Iron Buckshot")
		public static ConfigCategoryFirearmAmmoSettings configMediumIronBuckshot = new ConfigCategoryFirearmAmmoSettings(
				6,     // max stacksize
				5,     // projectile count
				15.0f, // projectile damage
				0.3f,  // projectile size
				30.0f, // projectile effective range
				1.5f   // projectile deviation modifier
			);
		@Name("Large Iron Buckshot")
		public static ConfigCategoryFirearmAmmoSettings configLargeIronBuckshot = new ConfigCategoryFirearmAmmoSettings(
				4,     // max stacksize
				7,     // projectile count
				15.0f, // projectile damage
				0.3f,  // projectile size
				30.0f, // projectile effective range
				1.5f   // projectile deviation modifier
			);
		
		public static class ConfigCategoryFirearmAmmoSettings {	
			@Name("Ammo Max StackSize")
			@Comment("Sets the max stack size of this ammo.")
			@RangeInt(min = 1)
			public int maxStacksize = 8;
			
			@Name("Projectile Count")
			@Comment("Affects the number of projectiles fired when using this ammo.")
			@RangeInt(min = 1)
			public int projectileCount = 1;
			
			@Name("Projectile Damage")
			@Comment("Affects the damage of projectiles when using this ammo.")
			public float projectileDamage = 23.0f; 
			
			@Name("Projectile Size")
			@Comment("Affects the size of projectiles when using this ammo.")
			@RangeDouble(min = 0.01f, max = 10f) 
			public float projectileSize = 0.4f; 			
			
			@Name("Projectile Effective Range")
			@Comment("Affects the range in meters past which projectiles do half damage when using this ammo.")
			@RangeDouble(min = 0.1f, max = 10000f)
			public float projectileEffectiveRange = 50.0f;
			
			@Name("Projectile Deviation Modifier")
			@Comment("Affects the deviation of projectiles when using this ammo.")
			@RangeDouble(min = 0.1f, max = 10000f)
			public float projectileDeviationModifier = 1.0f;
			
			public ConfigCategoryFirearmAmmoSettings(int maxStacksize, int projectileCount, float projectileDamage, float projectileSize, float projectileEffectiveRange, float projectileDeviationModifier) {
				this.maxStacksize = maxStacksize;
				this.projectileCount = projectileCount;
				this.projectileDamage = projectileDamage;
				this.projectileSize = projectileSize;
				this.projectileEffectiveRange = projectileEffectiveRange;
				this.projectileDeviationModifier = projectileDeviationModifier;
			}
		}
	}
	
	@Config(modid = OldGuns.MODID, category = "Artillery")
	public static class ConfigCategoryArtillery {
		@Name("Drop artillery item on break")
		@Comment("Set true to make artillery pieces drop their item rather than their ingredients when broken.")
		public static boolean dropArtilleryItemOnBreak = false;
		
		@Name("Medium Cannon")
        public static ConfigCategoryArtillerySettings configMediumCannon = new ConfigCategoryArtillerySettings(
        		2.5f,	// projectile speed
        		500f,	// effective range
        		1f,		// shot deviation modifier
        		1f		// shot damage modifier
        	);
		@Name("Naval Cannon")
        public static ConfigCategoryArtillerySettings configNavalCannon = new ConfigCategoryArtillerySettings(
        		2.5f,	// projectile speed         
        		500f,	// effective range          
        		1f,  	// shot deviation modifier  
        		1f   	// shot damage modifier     
        	);
		
		public static class ConfigCategoryArtillerySettings {		
			@Name("Projectile speed")
			@Comment("Affects the speed of projectiles followed from this artillery piece.")
			@RangeDouble(min = 0.1f, max = 1000f) 
			public float projectileSpeed = 2.5f; 
			
			@Name("Base effective range")
			@Comment({
				"Affects the default range past which projectiles fired from this artillery piece do half damage.",
				"This is sometimes overriden by the type of projectile loaded."
			})
			@RangeDouble(min = 0.1f, max = 10000f) 
			public float baseEffectiveRange = 500f; 
			
			@Name("Base shot deviation")
			@Comment({
				"Affects the deviation of projectiles fired from this artillery piece.",
				"This is multiplied with the base deviation of the ammo loaded to get the final deviation."
			})
			@RangeDouble(min = 0.1f, max = 1000f) 
			public float baseShotDeviationModifier = 1f; 
			
			@Name("Base shot damage modifier")
			@Comment({
				"Affects the damage of projectiles fired from this artillery piece.",
				"This is multiplied with the base damage of the ammo loaded to get the final damage."
			})
			@RangeDouble(min = 0.1f, max = 1000f) 
			public float baseShotDamageModifier = 1f;
			
			public ConfigCategoryArtillerySettings(float defaultProjectileSpeed, float defaultEffectiveRange, float defaultShotDeviationModifier, float defaultShotDamageModifier) {
				this.projectileSpeed = defaultProjectileSpeed;
				this.baseEffectiveRange = defaultEffectiveRange;
				this.baseShotDeviationModifier = defaultShotDeviationModifier;
				this.baseShotDamageModifier = defaultShotDamageModifier;
			}
		}
	}
	
	@Config(modid = OldGuns.MODID, category = "Recipes")
	public static class ConfigCategoryRecipes {

        @Name("Recipe enables")
        @Comment("Enable or disable recipes by setting the value for the recipe below as true or false.")
        @RequiresMcRestart
        public static Map<String, Boolean> recipeToggles;

        final static String[] modItemNames = {
        		"artillery_cannon",
        		"medium_iron_cannonball",
        		"medium_iron_he_shell",
        		"gunners_quadrant",
        		"ram_rod",
        		"long_match",
        		"powder_charge",
        		"large_iron_cannon_barrel",
        		"large_wooden_cannon_wheel",
        		"large_wooden_cannon_carriage",
        		"matchlock_derringer",
        		"matchlock_pistol",
        		"matchlock_arquebus",
        		"matchlock_caliver",
        		"matchlock_musket",
        		"matchlock_long_musket",
        		"matchlock_musketoon",
        		"matchlock_blunderbuss",
        		"flintlock_derringer",
        		"flintlock_pistol",
        		"flintlock_arquebus",
        		"flintlock_caliver",
        		"flintlock_musket",
        		"flintlock_long_musket",
        		"flintlock_musketoon",
        		"flintlock_blunderbuss",
        		"flintlock_breechloading_pistol",
        		"flintlock_breechloading_arquebus",
        		"flintlock_breechloading_caliver",
        		"flintlock_breechloading_musket",
        		"flintlock_breechloading_long_musket",
        		"caplock_derringer",
        		"caplock_pistol",
        		"caplock_arquebus",
        		"caplock_caliver",
        		"caplock_musket",
        		"caplock_long_musket",
        		"caplock_blunderbuss",
        		"caplock_musketoon",
        		"caplock_breechloading_derringer",
        		"caplock_breechloading_pistol",
        		"caplock_breechloading_arquebus",
        		"caplock_breechloading_caliver",
        		"caplock_breechloading_musket",
        		"caplock_breechloading_long_musket",
        		"small_stone_musket_ball",
        		"medium_stone_musket_ball",
        		"large_stone_musket_ball",
        		"large_stone_birdshot",
        		"small_iron_musket_ball",
        		"medium_iron_musket_ball",
        		"large_iron_musket_ball",
        		"large_iron_birdshot",
        		"small_iron_musket_ball_paper_cartridge",
        		"percussion_cap",
        		"percussion_powder",
        		"tiny_iron_barrel",
        		"small_iron_barrel",
        		"medium_iron_barrel",
        		"large_iron_barrel",
        		"huge_iron_barrel",
        		"tiny_stone_barrel",
        		"small_stone_barrel",
        		"medium_stone_barrel",
        		"large_stone_barrel",
        		"huge_stone_barrel",
        		"small_wooden_handle",
        		"medium_wooden_handle",
        		"large_wooden_handle",
        		"small_wooden_stock",
        		"medium_wooden_stock",
        		"large_wooden_stock",
        		"matchlock_mechanism",
        		"flintlock_mechanism",
        		"breech_block",
        		"small_musket_ball_mold",
        		"medium_musket_ball_mold",
        		"large_musket_ball_mold",
        		"small_musket_ball_mold_tool",
        		"medium_musket_ball_mold_tool",
        		"large_musket_ball_mold_tool",
        		"repair_kit",
        		"hack_saw"
        };
        
        static
        {
            recipeToggles = Maps.newHashMap();
            for (String modItemName : modItemNames)
            {
                recipeToggles.put(modItemName, true);
            }
        }
        
        /**
         * Will only allow this mods recipes to be disabled
         * @author Aeronica	
         * @param stackIn stack to be tested
         * @return recipe state
         */
        public static boolean isRecipeEnabled(ItemStack stackIn)
        {
            // strip off "item." or "tile." and "instrument." to get the raw item name without domain and item base names
        	String itemName = stackIn.getItem().getRegistryName().toString().replaceFirst(OldGuns.MODID + ":", "");
            boolean enableState = !ConfigCategoryRecipes.recipeToggles.containsKey(itemName) || (ConfigCategoryRecipes.recipeToggles.get(itemName) && !itemName.contains(":"));
            OldGuns.logger.debug(String.format("Recipe Enabled? %s %s", itemName, enableState));
            return enableState;
        }
	}
	
}
