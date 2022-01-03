package zach2039.oldguns.common.init.config;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.init.ModConfigs.ConfigOptions;

@Config(modid = OldGuns.MODID, category = "Recipes")
public class ConfigCategoryRecipes {

    @Name("Recipe enables")
    @Comment("Enable or disable recipes by setting the value for the recipe below as true or false.")
    @RequiresMcRestart
    public Map<String, Boolean> recipeToggles;

    String[] modItemNames = {
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
    
    public ConfigCategoryRecipes() {
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
    public boolean isRecipeEnabled(ItemStack stackIn)
    {
        // strip off "item." or "tile." and "instrument." to get the raw item name without domain and item base names
    	String itemName = stackIn.getItem().getRegistryName().toString().replaceFirst(OldGuns.MODID + ":", "");
        boolean enableState = !this.recipeToggles.containsKey(itemName) || (this.recipeToggles.get(itemName) && !itemName.contains(":"));
        OldGuns.logger.debug(String.format("Recipe Enabled? %s %s", itemName, enableState));
        return enableState;
    }
}