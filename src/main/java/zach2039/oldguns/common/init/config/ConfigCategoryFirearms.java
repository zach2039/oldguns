package zach2039.oldguns.common.init.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RangeDouble;
import net.minecraftforge.common.config.Config.RangeInt;
import zach2039.oldguns.common.OldGuns;

@Config(modid = OldGuns.MODID, category = "Firearms")
public class ConfigCategoryFirearms {
	@Name("Print firearm debug details to console")
	@Comment("Set true to print debug messages to the console, to help tune firearm settings.")
	public boolean printFirearmDebugMessages = false;
	
	@Name("Matchlock Derringer")
	public ConfigCategoryFirearmSettings configMatchlockDerringer = new ConfigCategoryFirearmSettings(
			8,     	// durability              
			3f,    	// projectile speed        
			0.5f,  	// effective range modifier
			5.0f,   // shot deviation            
			1f     	// shot damage modifier    
		); 
	@Name("Matchlock Pistol")
	public ConfigCategoryFirearmSettings configMatchlockPistol = new ConfigCategoryFirearmSettings(
			12,    	// durability              
			3.25f, 	// projectile speed        
			1.2f,  	// effective range modifier
			2.0f,   // shot deviation          
			1f     	// shot damage modifier    
		); 
	@Name("Matchlock Arquebus")
	public ConfigCategoryFirearmSettings configMatchlockArquebus = new ConfigCategoryFirearmSettings(
			16,    	// durability              
			3.5f,  	// projectile speed        
			1.0f,  	// effective range modifier
			1.8f,   // shot deviation       
			1f     	// shot damage modifier    
		); 
	@Name("Matchlock Caliver")
	public ConfigCategoryFirearmSettings configMatchlockCaliver = new ConfigCategoryFirearmSettings(
			20,     // durability              
			3.75f,  // projectile speed        
			1.2f,  	// effective range modifier
			1.6f,   // shot deviation        
			1f      // shot damage modifier    
		); 
	@Name("Matchlock Musket")
	public ConfigCategoryFirearmSettings configMatchlockMusket = new ConfigCategoryFirearmSettings(
			24,     // durability              
			4f,     // projectile speed        
			1f,    	// effective range modifier
			1.4f,   // shot deviation          
			1f      // shot damage modifier    
		); 
	@Name("Matchlock Long Musket")
	public ConfigCategoryFirearmSettings configMatchlockLongMusket = new ConfigCategoryFirearmSettings(
			28,     // durability              
			4.25f,  // projectile speed        
			1.2f,  	// effective range modifier
			1.2f,   // shot deviation         
			1f      // shot damage modifier    
		); 
	@Name("Matchlock Musketoon")
	public ConfigCategoryFirearmSettings configMatchlockMusketoon = new ConfigCategoryFirearmSettings(
			24,     // durability              
			3.75f,  // projectile speed        
			0.8f,    // effective range modifier
			1.5f,   // shot deviation       
			1f      // shot damage modifier    
		); 
	@Name("Matchlock Blunderbuss")
	public ConfigCategoryFirearmSettings configMatchlockBlunderbuss = new ConfigCategoryFirearmSettings(
			24,     // durability              
			3.25f,  // projectile speed        
			1f,     // effective range modifier
			1.7f,   // shot deviation      
			1f      // shot damage modifier    
		); 
	
	@Name("Flintlock Derringer")
	public ConfigCategoryFirearmSettings configFlintlockDerringer = new ConfigCategoryFirearmSettings(
			28,     // durability              
			3.25f,  // projectile speed        
			0.5f,  	// effective range modifier
			5.0f,   // shot deviation    
			1f      // shot damage modifier    
		);
	@Name("Flintlock Pistol")
	public ConfigCategoryFirearmSettings configFlintlockPistol = new ConfigCategoryFirearmSettings(
			32,     // durability              
			3.5f,   // projectile speed        
			1.2f,   // effective range modifier
			2.0f,   // shot deviation     
			1f      // shot damage modifier    
		); 
	@Name("Flintlock Heavy Pistol")
	public ConfigCategoryFirearmSettings configFlintlockArquebus = new ConfigCategoryFirearmSettings(
			36,     // durability              
			3.75f,  // projectile speed        
			1.0f,   // effective range modifier
			1.8f,   // shot deviation     
			1f      // shot damage modifier    
		); 
	@Name("Flintlock Carbine")
	public ConfigCategoryFirearmSettings configFlintlockCaliver = new ConfigCategoryFirearmSettings(
			40,     // durability              
			4f,     // projectile speed        
			1.2f,    // effective range modifier
			1.6f,   // shot deviation         
			1f      // shot damage modifier    
		); 
	@Name("Flintlock Musket")
	public ConfigCategoryFirearmSettings configFlintlockMusket = new ConfigCategoryFirearmSettings(
			44,     // durability              
			4.25f,  // projectile speed        
			1f,   	// effective range modifier
			1.4f,   // shot deviation          
			1f      // shot damage modifier    
		); 
	@Name("Flintlock Long Musket")
	public ConfigCategoryFirearmSettings configFlintlockLongMusket = new ConfigCategoryFirearmSettings(
			48,     // durability              
			4.5f,   // projectile speed        
			1.2f,    // effective range modifier
			1.2f,   // shot deviation    
			1f      // shot damage modifier    
		); 
	@Name("Flintlock Musketoon")
	public ConfigCategoryFirearmSettings configFlintlockMusketoon = new ConfigCategoryFirearmSettings(
			44,     // durability              
			4f,     // projectile speed        
			0.8f,    // effective range modifier
			1.5f,   // shot deviation         
			1f      // shot damage modifier    
		); 
	@Name("Flintlock Blunderbuss")
	public ConfigCategoryFirearmSettings configFlintlockBlunderbuss = new ConfigCategoryFirearmSettings(
			44,     // durability              
			3.5f,   // projectile speed        
			1.0f,    // effective range modifier
			1.7f,   // shot deviation          
			1f      // shot damage modifier    
		); 
	
	@Name("Flintlock Breechloading Pistol")
	public ConfigCategoryBreechloadingFirearmSettings configFlintlockBreechloadingPistol = new ConfigCategoryBreechloadingFirearmSettings(
			24,     // durability              
			3.5f,   // projectile speed        
			1.2f,    // effective range modifier
			2.0f,   // shot deviation      
			1f,     // shot damage modifier    
			70		// reloading ticks
		); 
	@Name("Flintlock Breechloading Heavy Pistol")
	public ConfigCategoryBreechloadingFirearmSettings configFlintlockBreechloadingArquebus = new ConfigCategoryBreechloadingFirearmSettings(
			28,     // durability              
			3.75f,  // projectile speed        
			1.0f,    // effective range modifier
			1.8f,   // shot deviation             
			1f,     // shot damage modifier    
			80		// reloading ticks
		); 
	@Name("Flintlock Breechloading Carbine")
	public ConfigCategoryBreechloadingFirearmSettings configFlintlockBreechloadingCaliver = new ConfigCategoryBreechloadingFirearmSettings(
			32,     // durability              
			4.0f,   // projectile speed        
			1.2f,    // effective range modifier
			1.6f,   // shot deviation         
			1f,     // shot damage modifier    
			90		// reloading ticks
		); 
	@Name("Flintlock Breechloading Musket")
	public ConfigCategoryBreechloadingFirearmSettings configFlintlockBreechloadingMusket = new ConfigCategoryBreechloadingFirearmSettings(
			36,     // durability              
			4.25f,  // projectile speed        
			1f,  	// effective range modifier
			1.4f,   // shot deviation      
			1f,     // shot damage modifier    
			100		// reloading ticks
		); 
	@Name("Flintlock Breechloading Long Musket")
	public ConfigCategoryBreechloadingFirearmSettings configFlintlockBreechloadingLongMusket = new ConfigCategoryBreechloadingFirearmSettings(
			40,     // durability              
			4.5f,   // projectile speed        
			1.2f,    // effective range modifier
			1.2f,   // shot deviation       
			1f,     // shot damage modifier    
			110		// reloading ticks
		); 
	
	@Name("Caplock Derringer")
	public ConfigCategoryFirearmSettings configCaplockDerringer = new ConfigCategoryFirearmSettings(
			48,     // durability              
			3.5f,   // projectile speed        
			0.5f,  	// effective range modifier
			5.0f,   // shot deviation         
			1f      // shot damage modifier    
		); 
	@Name("Caplock Pistol")
	public ConfigCategoryFirearmSettings configCaplockPistol = new ConfigCategoryFirearmSettings(
			52,     // durability              
			3.75f,  // projectile speed        
			1.2f,    // effective range modifier
			2.0f,   // shot deviation       
			1f      // shot damage modifier    
		); 
	@Name("Caplock Heavy Pistol")
	public ConfigCategoryFirearmSettings configCaplockArquebus = new ConfigCategoryFirearmSettings(
			56,     // durability              
			4.0f,   // projectile speed        
			1.0f,    // effective range modifier
			1.8f,   // shot deviation          
			1f      // shot damage modifier    
		); 
	@Name("Caplock Carbine")
	public ConfigCategoryFirearmSettings configCaplockCaliver = new ConfigCategoryFirearmSettings(
			60,     // durability              
			4.25f,  // projectile speed        
			1.2f,    // effective range modifier
			1.6f,   // shot deviation          
			1f      // shot damage modifier    
		); 
	@Name("Caplock Musket")
	public ConfigCategoryFirearmSettings configCaplockMusket = new ConfigCategoryFirearmSettings(
			64,		// durability              
			4.5f,	// projectile speed        
			1.0f,    // effective range modifier
			1.4f,   // shot deviation       
			1f		// shot damage modifier    
		); 
	@Name("Caplock Long Musket")
	public ConfigCategoryFirearmSettings configCaplockLongMusket = new ConfigCategoryFirearmSettings(
			68,		// durability              
			4.75f,	// projectile speed        
			1.2f,    // effective range modifier
			1.2f,   // shot deviation     
			1f		// shot damage modifier    
		); 
	@Name("Caplock Musketoon")
	public ConfigCategoryFirearmSettings configCaplockMusketoon = new ConfigCategoryFirearmSettings(
			64,		// durability
			4.25f,	// projectile speed
			0.8f,    // effective range modifier
			1.5f,   // shot deviation
			1f		// shot damage modifier
		);   
	@Name("Caplock Blunderbuss")
	public ConfigCategoryFirearmSettings configCaplockBlunderbuss = new ConfigCategoryFirearmSettings(
			44,		// durability
			3.75f,	// projectile speed
			1.0f,	// effective range modifier
			1.7f,   // shot deviation      
			1f		// shot damage modifier
		); 
	
	@Name("Caplock Breechloading Pistol")
	public ConfigCategoryBreechloadingFirearmSettings configCaplockBreechloadingPistol = new ConfigCategoryBreechloadingFirearmSettings(
			44,     // durability              
			3.5f,   // projectile speed        
			1.2f,	// effective range modifier
			2.0f,   // shot deviation         
			1f,     // shot damage modifier    
			50		// reloading ticks
		); 
	@Name("Caplock Breechloading Heavy Pistol")
	public ConfigCategoryBreechloadingFirearmSettings configCaplockBreechloadingArquebus = new ConfigCategoryBreechloadingFirearmSettings(
			48,     // durability              
			3.75f,  // projectile speed        
			1.0f,	// effective range modifier
			1.8f,   // shot deviation        
			1f,     // shot damage modifier    
			60		// reloading ticks
		); 
	@Name("Caplock Breechloading Carbine")
	public ConfigCategoryBreechloadingFirearmSettings configCaplockBreechloadingCaliver = new ConfigCategoryBreechloadingFirearmSettings(
			52,     // durability              
			4.0f,   // projectile speed        
			1.2f,	// effective range modifier
			1.6f,   // shot deviation            
			1f,     // shot damage modifier    
			70		// reloading ticks
		); 
	@Name("Caplock Breechloading Musket")
	public ConfigCategoryBreechloadingFirearmSettings configCaplockBreechloadingMusket = new ConfigCategoryBreechloadingFirearmSettings(
			56,     // durability              
			4.25f,  // projectile speed        
			1.0f,	// effective range modifier
			1.4f,   // shot deviation          
			1f,     // shot damage modifier    
			80		// reloading ticks
		); 
	@Name("Caplock Breechloading Long Musket")
	public ConfigCategoryBreechloadingFirearmSettings configCaplockBreechloadingLongMusket = new ConfigCategoryBreechloadingFirearmSettings(
			60,     // durability              
			4.5f,   // projectile speed        
			1.2f,	// effective range modifier
			1.2f,   // shot deviation          
			1f,     // shot damage modifier    
			90		// reloading ticks 
		); 
	
	public class ConfigCategoryFirearmSettings {	
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
	
	public class ConfigCategoryBreechloadingFirearmSettings {	
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
