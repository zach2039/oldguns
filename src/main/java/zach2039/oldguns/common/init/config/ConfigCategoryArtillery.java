package zach2039.oldguns.common.init.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RangeDouble;
import zach2039.oldguns.common.OldGuns;

@Config(modid = OldGuns.MODID, category = "Artillery")
public class ConfigCategoryArtillery {
	@Name("Drop artillery item on break")
	@Comment("Set true to make artillery pieces drop their item rather than their ingredients when broken.")
	public boolean dropArtilleryItemOnBreak = false;
	
	@Name("Medium Cannon")
    public ConfigCategoryArtillerySettings configMediumCannon = new ConfigCategoryArtillerySettings(
    		2.5f,	// projectile speed
    		500f,	// effective range
    		1f,		// shot deviation modifier
    		1f		// shot damage modifier
    	);
	@Name("Naval Cannon")
    public ConfigCategoryArtillerySettings configNavalCannon = new ConfigCategoryArtillerySettings(
    		2.5f,	// projectile speed         
    		500f,	// effective range          
    		1f,  	// shot deviation modifier  
    		1f   	// shot damage modifier     
    	);
	
	public class ConfigCategoryArtillerySettings {		
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
