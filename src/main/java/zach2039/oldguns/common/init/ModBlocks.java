package zach2039.oldguns.common.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.block.BlockGunsmithsBench;
import zach2039.oldguns.common.block.BlockMelter;

@ObjectHolder(OldGuns.MODID)
public class ModBlocks
{
	@ObjectHolder("gunsmiths_bench")
	public static final Block GUNSMITHS_BENCH = null;
	
	@ObjectHolder("melter")
	public static final Block MELTER = null;
	
	@EventBusSubscriber(modid = OldGuns.MODID)
	public static class RegistrationHandler
	{
		@SubscribeEvent
		public static void registerBlocks(Register<Block> event)
		{
			final Block[] blocks =
				{
					new BlockGunsmithsBench(),
					new BlockMelter()
				};

			event.getRegistry().registerAll(blocks);
		}
		
		@SubscribeEvent
		public static void registerBlockItems(Register<Item> event)
		{
			final Item[] items =
				{
					new ItemBlock(ModBlocks.GUNSMITHS_BENCH).setRegistryName(ModBlocks.GUNSMITHS_BENCH.getRegistryName()),
					new ItemBlock(ModBlocks.MELTER).setRegistryName(ModBlocks.MELTER.getRegistryName())
				};
			
			event.getRegistry().registerAll(items);
		}
	}
}
