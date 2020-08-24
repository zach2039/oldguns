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
		registerModel(ModItems.MATCHLOCK_PISTOL, 0);
		registerModel(ModItems.FLINTLOCK_PISTOL, 0);
		registerModel(ModItems.FLINTLOCK_MECHANISM, 0);
		registerModel(ModItems.SMALL_IRON_MUSKET_BALL, 0);
		registerModel(ModItems.MEDIUM_IRON_MUSKET_BALL, 0);
		registerModel(ModItems.LARGE_IRON_MUSKET_BALL, 0);
		registerModel(ModItems.SMALL_STONE_MUSKET_BALL, 0);
		registerModel(ModItems.MEDIUM_STONE_MUSKET_BALL, 0);
		registerModel(ModItems.LARGE_STONE_MUSKET_BALL, 0);
		registerModel(ModItems.SMALL_IRON_BARREL, 0);
		registerModel(ModItems.SMALL_WOODEN_HANDLE, 0);	
		registerModel(ModItems.SMALL_MUSKET_BALL_MOLD, 0);
		registerModel(ModItems.MEDIUM_MUSKET_BALL_MOLD, 0);
		registerModel(ModItems.LARGE_MUSKET_BALL_MOLD, 0);
		registerModel(ModItems.SMALL_MUSKET_BALL_MOLD_TOOL, 0);
		registerModel(ModItems.MEDIUM_MUSKET_BALL_MOLD_TOOL, 0);
		registerModel(ModItems.LARGE_MUSKET_BALL_MOLD_TOOL, 0);
		registerModel(ModItems.REPAIR_KIT, 0);
		registerModel(ModItems.GUNNERS_QUADRANT, 0);
		registerModel(Item.getItemFromBlock(ModBlocks.GUNSMITHS_BENCH), 0);
		registerModel(Item.getItemFromBlock(ModBlocks.MELTER), 0);
	}
	
	private static void registerModel(Item item, int meta)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, 
				new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
