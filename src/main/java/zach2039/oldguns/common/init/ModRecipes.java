package zach2039.oldguns.common.init;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.item.crafting.BreechloadingReloadRecipe;
import zach2039.oldguns.common.item.crafting.ShapedGunsmithsBenchRecipe;
import zach2039.oldguns.common.item.crafting.base.GunsmithsBenchRecipe;

@EventBusSubscriber(modid = OldGuns.MODID)
public class ModRecipes
{
	public final static List<BreechloadingReloadRecipe> breechloaderReloadRecipes = new ArrayList<BreechloadingReloadRecipe>();

	public final static List<GunsmithsBenchRecipe> gunsmithsBenchRecipes = new ArrayList<GunsmithsBenchRecipe>();
	
	public static void registerOreDictEntries()
	{	
		OreDictionary.registerOre("bulletMediumIronCannonball", ModItems.MEDIUM_IRON_CANNONBALL);
		OreDictionary.registerOre("bulletMediumIronHEShell", ModItems.MEDIUM_IRON_HE_SHELL);
		OreDictionary.registerOre("powderCharge", ModItems.POWDER_CHARGE);
		
		OreDictionary.registerOre("barrelLargeIronCannon", ModItems.LARGE_IRON_CANNON_BARREL);
		OreDictionary.registerOre("wheelLargeWooden", ModItems.LARGE_WOODEN_CANNON_WHEEL);
		OreDictionary.registerOre("carriageLargeWooden", ModItems.LARGE_WOODEN_CANNON_CARRIAGE);

		OreDictionary.registerOre("bulletSmallStoneMusketBall", ModItems.SMALL_STONE_MUSKET_BALL);
		OreDictionary.registerOre("bulletMediumStoneMusketBall", ModItems.MEDIUM_STONE_MUSKET_BALL);
		OreDictionary.registerOre("bulletMediumStoneMusketBall", ModItems.LARGE_STONE_MUSKET_BALL);
				
		OreDictionary.registerOre("bulletLargeStoneBirdshot", ModItems.LARGE_STONE_BIRDSHOT);

		OreDictionary.registerOre("bulletSmallIronMusketBall", ModItems.SMALL_IRON_MUSKET_BALL);
		OreDictionary.registerOre("bulletMediumIronMusketBall", ModItems.MEDIUM_IRON_MUSKET_BALL);
		OreDictionary.registerOre("bulletLargeIronMusketBall", ModItems.LARGE_IRON_MUSKET_BALL);
		
		OreDictionary.registerOre("bulletLargeIronBirdshot", ModItems.LARGE_IRON_BIRDSHOT);
	
		OreDictionary.registerOre("barrelTinyIron", ModItems.TINY_IRON_BARREL);
		OreDictionary.registerOre("barrelSmallIron", ModItems.SMALL_IRON_BARREL);
		OreDictionary.registerOre("barrelMediumIron", ModItems.MEDIUM_IRON_BARREL);
		OreDictionary.registerOre("barrelLargeIron", ModItems.LARGE_IRON_BARREL);
		OreDictionary.registerOre("barrelHugeIron", ModItems.HUGE_IRON_BARREL);
		
		OreDictionary.registerOre("barrelTinyStone", ModItems.TINY_STONE_BARREL);
		OreDictionary.registerOre("barrelSmallStone", ModItems.SMALL_STONE_BARREL);
		OreDictionary.registerOre("barrelMediumStone", ModItems.MEDIUM_STONE_BARREL);
		OreDictionary.registerOre("barrelLargeStone", ModItems.LARGE_STONE_BARREL);
		OreDictionary.registerOre("barrelHugeStone", ModItems.HUGE_STONE_BARREL);
		
		OreDictionary.registerOre("handleSmallWooden", ModItems.SMALL_WOODEN_HANDLE);
		OreDictionary.registerOre("handleMediumWooden", ModItems.MEDIUM_WOODEN_HANDLE);
		OreDictionary.registerOre("handleLargeWooden", ModItems.LARGE_WOODEN_HANDLE);

		OreDictionary.registerOre("stockSmallWooden", ModItems.SMALL_WOODEN_STOCK);
		OreDictionary.registerOre("stockMediumWooden", ModItems.MEDIUM_WOODEN_STOCK);
		OreDictionary.registerOre("stockLargeWooden", ModItems.LARGE_WOODEN_STOCK);
		
		OreDictionary.registerOre("mechanismMatchlock", ModItems.MATCHLOCK_MECHANISM);
		OreDictionary.registerOre("mechanismFlintlock", ModItems.FLINTLOCK_MECHANISM);
		
		OreDictionary.registerOre("breechBlock", ModItems.BREECH_BLOCK);
		
		OreDictionary.registerOre("toolRepairKit", ModItems.REPAIR_KIT);
		OreDictionary.registerOre("toolSaw", ModItems.HACK_SAW);
	}
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onRecipesUpdatedEvent(RegistryEvent.Register<IRecipe> event) {
        /* Cache recipes that we need to reference during runtime, so that we don't slow things down. */
		event.getRegistry().forEach((recipe) -> {
			if (recipe instanceof BreechloadingReloadRecipe) {
				ModRecipes.breechloaderReloadRecipes.add((BreechloadingReloadRecipe) recipe);
				OldGuns.LOGGER.debug("Recipe cached : " + recipe.getRegistryName());
			}
		});

		event.getRegistry().forEach((recipe) -> {
			if (recipe instanceof GunsmithsBenchRecipe)
				ModRecipes.gunsmithsBenchRecipes.add((GunsmithsBenchRecipe) recipe);
				OldGuns.LOGGER.debug("Recipe cached : " + recipe.getRegistryName());
		});
    }
}
