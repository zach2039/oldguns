package zach2039.oldguns.common.init;

import net.minecraftforge.common.config.Config;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.init.config.ConfigCategoryArtillery;
import zach2039.oldguns.common.init.config.ConfigCategoryFirearmAmmo;
import zach2039.oldguns.common.init.config.ConfigCategoryFirearms;
import zach2039.oldguns.common.init.config.ConfigCategoryGeneral;
import zach2039.oldguns.common.init.config.ConfigCategoryRecipes;

@Config(modid = OldGuns.MODID)
public class ModConfigs {
	public static class ConfigOptions {
		public ConfigCategoryGeneral configGeneral = new ConfigCategoryGeneral();
		
		public ConfigCategoryFirearms configFirearms = new ConfigCategoryFirearms();
		
		public ConfigCategoryFirearmAmmo configFirearmAmmo = new ConfigCategoryFirearmAmmo();
		
		public ConfigCategoryArtillery configArtillery = new ConfigCategoryArtillery();
		
		public ConfigCategoryRecipes configRecipes = new ConfigCategoryRecipes();
	}
}
