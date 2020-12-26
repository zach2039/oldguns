package zach2039.oldguns.common.init;

import java.lang.ref.Reference;
import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import zach2039.oldguns.common.OldGuns;

public class ModConfigs {
	
	@Config(modid = OldGuns.MODID, category = "General")
	public static class ConfigCategoryGeneral {}
	
	@Config(modid = OldGuns.MODID, category = "Firearms")
	public static class ConfigCategoryFirearms {
		@Name("Matchlock Derringer")
		public static ConfigCategoryFirearmSettings configMatchlockDerringer = new ConfigCategoryFirearmSettings(8, 3f, 3f, 10f, 1f); 
		@Name("Matchlock Pistol")
		public static ConfigCategoryFirearmSettings configMatchlockPistol = new ConfigCategoryFirearmSettings(12, 3.25f, 6f, 9f, 1f); 
		@Name("Matchlock Arquebus")
		public static ConfigCategoryFirearmSettings configMatchlockArquebus = new ConfigCategoryFirearmSettings(16, 3.5f, 9f, 8f, 1f); 
		@Name("Matchlock Caliver")
		public static ConfigCategoryFirearmSettings configMatchlockCaliver = new ConfigCategoryFirearmSettings(20, 3.75f, 12f, 7f, 1f); 
		@Name("Matchlock Musket")
		public static ConfigCategoryFirearmSettings configMatchlockMusket = new ConfigCategoryFirearmSettings(24, 4f, 15f, 6f, 1f); 
		@Name("Matchlock Long Musket")
		public static ConfigCategoryFirearmSettings configMatchlockLongMusket = new ConfigCategoryFirearmSettings(28, 4.25f, 18f, 5f, 1f); 
		@Name("Matchlock Musketoon")
		public static ConfigCategoryFirearmSettings configMatchlockMusketoon = new ConfigCategoryFirearmSettings(24, 3.75f, 14f, 7f, 1f); 
		@Name("Matchlock Blunderbuss")
		public static ConfigCategoryFirearmSettings configMatchlockBlunderbuss = new ConfigCategoryFirearmSettings(24, 3.25f, 6f, 10f, 1f); 
		
		@Name("Flintlock Derringer")
		public static ConfigCategoryFirearmSettings configFlintlockDerringer = new ConfigCategoryFirearmSettings(28, 3.25f, 5f, 10f, 1f); 
		@Name("Flintlock Pistol")
		public static ConfigCategoryFirearmSettings configFlintlockPistol = new ConfigCategoryFirearmSettings(32, 3.5f, 8f, 9f, 1f); 
		@Name("Flintlock Arquebus")
		public static ConfigCategoryFirearmSettings configFlintlockArquebus = new ConfigCategoryFirearmSettings(36, 3.75f, 11f, 8f, 1f); 
		@Name("Flintlock Caliver")
		public static ConfigCategoryFirearmSettings configFlintlockCaliver = new ConfigCategoryFirearmSettings(40, 4f, 14f, 7f, 1f); 
		@Name("Flintlock Musket")
		public static ConfigCategoryFirearmSettings configFlintlockMusket = new ConfigCategoryFirearmSettings(44, 4.25f, 17f, 6f, 1f); 
		@Name("Flintlock Long Musket")
		public static ConfigCategoryFirearmSettings configFlintlockLongMusket = new ConfigCategoryFirearmSettings(48, 4.5f, 20f, 5f, 1f); 
		@Name("Flintlock Musketoon")
		public static ConfigCategoryFirearmSettings configFlintlockMusketoon = new ConfigCategoryFirearmSettings(44, 4f, 16f, 7f, 1f); 
		@Name("Flintlock Blunderbuss")
		public static ConfigCategoryFirearmSettings configFlintlockBlunderbuss = new ConfigCategoryFirearmSettings(44, 3.5f, 8f, 10f, 1f); 
		@Name("Flintlock Breechloading Musket")
		public static ConfigCategoryFirearmSettings configFlintlockBreechloadingMusket = new ConfigCategoryFirearmSettings(44, 3.5f, 8f, 10f, 1f); 
		
		public static class ConfigCategoryFirearmSettings {	
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
        		"small_stone_musket_ball",
        		"medium_stone_musket_ball",
        		"large_stone_musket_ball",
        		"large_stone_birdshot",
        		"small_iron_musket_ball",
        		"medium_iron_musket_ball",
        		"large_iron_musket_ball",
        		"large_iron_birdshot",
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
        		"small_musket_ball_mold",
        		"medium_musket_ball_mold",
        		"large_musket_ball_mold",
        		"small_musket_ball_mold_tool",
        		"medium_musket_ball_mold_tool",
        		"large_musket_ball_mold_tool",
        		"repair_kit"
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
