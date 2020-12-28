package zach2039.oldguns.client.init;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.init.ModBlocks;
import zach2039.oldguns.common.init.ModItems;

@EventBusSubscriber(value = Side.CLIENT, modid = OldGuns.MODID)
public class ModelRegistrationHandler
{
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event)
	{
		registerModel(ModItems.ARTILLERY_CANNON, 0);
		
		registerModel(ModItems.MEDIUM_IRON_CANNONBALL, 0);
		registerModel(ModItems.MEDIUM_IRON_HE_SHELL, 0);
		
		registerModel(ModItems.GUNNERS_QUADRANT, 0);
		registerModel(ModItems.RAM_ROD, 0);
		registerModel(ModItems.LONG_MATCH, 0);
		registerModel(ModItems.POWDER_CHARGE, 0);
		
		registerModel(ModItems.LARGE_IRON_CANNON_BARREL, 0);
		registerModel(ModItems.LARGE_WOODEN_CANNON_WHEEL, 0);
		registerModel(ModItems.LARGE_WOODEN_CANNON_CARRIAGE, 0);
		
		registerModel(ModItems.MATCHLOCK_DERRINGER, 0);
		registerModel(ModItems.MATCHLOCK_PISTOL, 0);
		registerModel(ModItems.MATCHLOCK_ARQUEBUS, 0);
		registerModel(ModItems.MATCHLOCK_CALIVER, 0);
		registerModel(ModItems.MATCHLOCK_MUSKET, 0);
		registerModel(ModItems.MATCHLOCK_LONG_MUSKET, 0);
		
		registerModel(ModItems.MATCHLOCK_MUSKETOON, 0);
		registerModel(ModItems.MATCHLOCK_BLUNDERBUSS, 0);
		
		registerModel(ModItems.FLINTLOCK_DERRINGER, 0);
		registerModel(ModItems.FLINTLOCK_PISTOL, 0);
		registerModel(ModItems.FLINTLOCK_ARQUEBUS, 0);
		registerModel(ModItems.FLINTLOCK_CALIVER, 0);
		registerModel(ModItems.FLINTLOCK_MUSKET, 0);
		registerModel(ModItems.FLINTLOCK_LONG_MUSKET, 0);
		                       
		registerModel(ModItems.FLINTLOCK_MUSKETOON, 0);
		registerModel(ModItems.FLINTLOCK_BLUNDERBUSS, 0);
		
		registerModel(ModItems.FLINTLOCK_BREECHLOADING_PISTOL, 0);
		registerModel(ModItems.FLINTLOCK_BREECHLOADING_ARQUEBUS, 0);
		registerModel(ModItems.FLINTLOCK_BREECHLOADING_CALIVER, 0);
		registerModel(ModItems.FLINTLOCK_BREECHLOADING_MUSKET, 0);
		registerModel(ModItems.FLINTLOCK_BREECHLOADING_LONG_MUSKET, 0);
		
		registerModel(ModItems.CAPLOCK_DERRINGER, 0);
		registerModel(ModItems.CAPLOCK_PISTOL, 0);
		registerModel(ModItems.CAPLOCK_ARQUEBUS, 0);
		registerModel(ModItems.CAPLOCK_CALIVER, 0);
		registerModel(ModItems.CAPLOCK_MUSKET, 0);
		registerModel(ModItems.CAPLOCK_LONG_MUSKET, 0);
		
		registerModel(ModItems.CAPLOCK_MUSKETOON, 0);
		registerModel(ModItems.CAPLOCK_BLUNDERBUSS, 0);
		
		registerModel(ModItems.SMALL_STONE_MUSKET_BALL, 0);
		registerModel(ModItems.MEDIUM_STONE_MUSKET_BALL, 0);
		registerModel(ModItems.LARGE_STONE_MUSKET_BALL, 0);
		
		registerModel(ModItems.SMALL_STONE_BIRDSHOT, 0);
		registerModel(ModItems.MEDIUM_STONE_BIRDSHOT, 0);
		registerModel(ModItems.LARGE_STONE_BIRDSHOT, 0);
		
		registerModel(ModItems.SMALL_IRON_MUSKET_BALL, 0);
		registerModel(ModItems.MEDIUM_IRON_MUSKET_BALL, 0);
		registerModel(ModItems.LARGE_IRON_MUSKET_BALL, 0);

		registerModel(ModItems.SMALL_IRON_BIRDSHOT, 0);
		registerModel(ModItems.MEDIUM_IRON_BIRDSHOT, 0);
		registerModel(ModItems.LARGE_IRON_BIRDSHOT, 0);

		registerModel(ModItems.SMALL_IRON_BUCKSHOT, 0);
		registerModel(ModItems.MEDIUM_IRON_BUCKSHOT, 0);
		registerModel(ModItems.LARGE_IRON_BUCKSHOT, 0);
		
		registerModel(ModItems.SMALL_IRON_MUSKET_BALL_PAPER_CARTRIDGE, 0);
		registerModel(ModItems.MEDIUM_IRON_MUSKET_BALL_PAPER_CARTRIDGE, 0);
		registerModel(ModItems.LARGE_IRON_MUSKET_BALL_PAPER_CARTRIDGE, 0);
		
		registerModel(ModItems.PERCUSSION_CAP, 0);
		registerModel(ModItems.PERCUSSION_POWDER, 0);
		
		registerModel(ModItems.TINY_STONE_BARREL, 0);
		registerModel(ModItems.SMALL_STONE_BARREL, 0);
		registerModel(ModItems.MEDIUM_STONE_BARREL, 0);
		registerModel(ModItems.LARGE_STONE_BARREL, 0);
		registerModel(ModItems.HUGE_STONE_BARREL, 0);
		
		registerModel(ModItems.TINY_IRON_BARREL, 0);
		registerModel(ModItems.SMALL_IRON_BARREL, 0);
		registerModel(ModItems.MEDIUM_IRON_BARREL, 0);
		registerModel(ModItems.LARGE_IRON_BARREL, 0);
		registerModel(ModItems.HUGE_IRON_BARREL, 0);
		
		registerModel(ModItems.SMALL_WOODEN_HANDLE, 0);	
		registerModel(ModItems.MEDIUM_WOODEN_HANDLE, 0);	
		registerModel(ModItems.LARGE_WOODEN_HANDLE, 0);	
		
		registerModel(ModItems.SMALL_WOODEN_STOCK, 0);	
		registerModel(ModItems.MEDIUM_WOODEN_STOCK, 0);	
		registerModel(ModItems.LARGE_WOODEN_STOCK, 0);
		
		registerModel(ModItems.MATCHLOCK_MECHANISM, 0);
		registerModel(ModItems.FLINTLOCK_MECHANISM, 0);
		
		registerModel(ModItems.BREECH_BLOCK, 0);

		registerModel(ModItems.SMALL_MUSKET_BALL_MOLD, 0);
		registerModel(ModItems.MEDIUM_MUSKET_BALL_MOLD, 0);
		registerModel(ModItems.LARGE_MUSKET_BALL_MOLD, 0);
		
		registerModel(ModItems.SMALL_MUSKET_BALL_MOLD_TOOL, 0);
		registerModel(ModItems.MEDIUM_MUSKET_BALL_MOLD_TOOL, 0);
		registerModel(ModItems.LARGE_MUSKET_BALL_MOLD_TOOL, 0);
		
		registerModel(ModItems.REPAIR_KIT, 0);
		registerModel(ModItems.HACK_SAW, 0);
		
		registerModel(Item.getItemFromBlock(ModBlocks.GUNSMITHS_BENCH), 0);
		registerModel(Item.getItemFromBlock(ModBlocks.MELTER), 0);
		registerModel(Item.getItemFromBlock(ModBlocks.STATIONARY_CANNON), 0);
	}
	
	private static void registerModel(Item item, int meta)
	{
		OldGuns.logger.debug("Registering model for item " + item + " with metadata value of " + meta + ".");
		ModelLoader.setCustomModelResourceLocation(item, meta, 
				new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
