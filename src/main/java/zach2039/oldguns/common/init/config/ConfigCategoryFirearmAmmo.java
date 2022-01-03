package zach2039.oldguns.common.init.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RangeDouble;
import net.minecraftforge.common.config.Config.RangeInt;
import zach2039.oldguns.common.OldGuns;

@Config(modid = OldGuns.MODID, category = "FirearmAmmo")
public class ConfigCategoryFirearmAmmo {
	@Name("Small Stone Musket Ball")
	public ConfigCategoryFirearmAmmoSettings configSmallStoneMusketBall = new ConfigCategoryFirearmAmmoSettings(
			8,     // max stacksize
			1,     // projectile count
			10.0f, // projectile damage
			0.3f,  // projectile size
			15.0f, // projectile effective range
			1.0f   // projectile deviation modifier
		);
	@Name("Medium Stone Musket Ball")
	public ConfigCategoryFirearmAmmoSettings configMediumStoneMusketBall = new ConfigCategoryFirearmAmmoSettings(
			6,     // max stacksize
			1,     // projectile count
			13.0f, // projectile damage
			0.4f,  // projectile size
			30.0f, // projectile effective range
			1.0f   // projectile deviation modifier
		);
	@Name("Large Stone Musket Ball")
	public ConfigCategoryFirearmAmmoSettings configLargeStoneMusketBall = new ConfigCategoryFirearmAmmoSettings(
			4,     // max stacksize
			1,     // projectile count
			16.0f, // projectile damage
			0.5f,  // projectile size
			55.0f, // projectile effective range
			1.0f   // projectile deviation modifier
		); 
	@Name("Small Stone Birdshot")
	public ConfigCategoryFirearmAmmoSettings configSmallStoneBirdshot = new ConfigCategoryFirearmAmmoSettings(
			8,     // max stacksize
			6,     // projectile count
			2.0f,  // projectile damage
			0.1f,  // projectile size
			5.0f,  // projectile effective range
			3.0f   // projectile deviation modifier
		);
	@Name("Medium Stone Birdshot")
	public ConfigCategoryFirearmAmmoSettings configMediumStoneBirdshot = new ConfigCategoryFirearmAmmoSettings(
			6,     // max stacksize
			8,     // projectile count
			2.0f,  // projectile damage
			0.1f,  // projectile size
			5.0f,  // projectile effective range
			3.0f   // projectile deviation modifier
		);
	@Name("Large Stone Birdshot")
	public ConfigCategoryFirearmAmmoSettings configLargeStoneBirdshot = new ConfigCategoryFirearmAmmoSettings(
			6,     // max stacksize
			10,    // projectile count
			2.0f,  // projectile damage
			0.1f,  // projectile size
			5.0f,  // projectile effective range
			3.0f   // projectile deviation modifier
		);
	
	@Name("Small Iron Musket Ball")
	public ConfigCategoryFirearmAmmoSettings configSmallIronMusketBall = new ConfigCategoryFirearmAmmoSettings(
			8,     // max stacksize
			1,     // projectile count
			20.0f, // projectile damage
			0.3f,  // projectile size
			25.0f, // projectile effective range
			1.0f   // projectile deviation modifier
		);
	@Name("Medium Iron Musket Ball")
	public ConfigCategoryFirearmAmmoSettings configMediumIronMusketBall = new ConfigCategoryFirearmAmmoSettings(
			6,     // max stacksize
			1,     // projectile count
			23.0f, // projectile damage
			0.4f,  // projectile size
			50.0f, // projectile effective range
			1.0f   // projectile deviation modifier
		);
	@Name("Large Iron Musket Ball")
	public ConfigCategoryFirearmAmmoSettings configLargeIronMusketBall = new ConfigCategoryFirearmAmmoSettings(
			4,     // max stacksize
			1,     // projectile count
			26.0f, // projectile damage
			0.5f,  // projectile size
			75.0f, // projectile effective range
			1.0f   // projectile deviation modifier
		); 
	@Name("Small Iron Birdshot")
	public ConfigCategoryFirearmAmmoSettings configSmallIronBirdshot = new ConfigCategoryFirearmAmmoSettings(
			8,     // max stacksize
			6,     // projectile count
			3.0f,  // projectile damage
			0.2f,  // projectile size
			7.0f,  // projectile effective range
			3.0f   // projectile deviation modifier
		);
	@Name("Medium Iron Birdshot")
	public ConfigCategoryFirearmAmmoSettings configMediumIronBirdshot = new ConfigCategoryFirearmAmmoSettings(
			6,     // max stacksize
			8,     // projectile count
			3.0f,  // projectile damage
			0.2f,  // projectile size
			7.0f,  // projectile effective range
			3.0f   // projectile deviation modifier
		);
	@Name("Large Iron Birdshot")
	public ConfigCategoryFirearmAmmoSettings configLargeIronBirdshot = new ConfigCategoryFirearmAmmoSettings(
			4,     // max stacksize
			10,    // projectile count
			3.0f,  // projectile damage
			0.2f,  // projectile size
			7.0f,  // projectile effective range
			3.0f   // projectile deviation modifier
		);
	@Name("Small Iron Buckshot")
	public ConfigCategoryFirearmAmmoSettings configSmallIronBuckshot = new ConfigCategoryFirearmAmmoSettings(
			8,     // max stacksize
			3,     // projectile count
			15.0f, // projectile damage
			0.3f,  // projectile size
			30.0f, // projectile effective range
			1.5f   // projectile deviation modifier
		);
	@Name("Medium Iron Buckshot")
	public ConfigCategoryFirearmAmmoSettings configMediumIronBuckshot = new ConfigCategoryFirearmAmmoSettings(
			6,     // max stacksize
			5,     // projectile count
			15.0f, // projectile damage
			0.3f,  // projectile size
			30.0f, // projectile effective range
			1.5f   // projectile deviation modifier
		);
	@Name("Large Iron Buckshot")
	public ConfigCategoryFirearmAmmoSettings configLargeIronBuckshot = new ConfigCategoryFirearmAmmoSettings(
			4,     // max stacksize
			7,     // projectile count
			15.0f, // projectile damage
			0.3f,  // projectile size
			30.0f, // projectile effective range
			1.5f   // projectile deviation modifier
		);
	
	public class ConfigCategoryFirearmAmmoSettings {	
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
