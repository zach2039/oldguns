package zach2039.oldguns.common.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.block.BlockGunsmithsBench;
import zach2039.oldguns.common.block.BlockMelter;
import zach2039.oldguns.common.block.BlockStationaryCannon;
import zach2039.oldguns.common.tile.TileEntityMelter;
import zach2039.oldguns.common.tile.TileEntityStationaryCannon;

@ObjectHolder(OldGuns.MODID)
public class ModBlocks
{
	@ObjectHolder("gunsmiths_bench")
	public static final Block GUNSMITHS_BENCH = null;
	
	@ObjectHolder("melter")
	public static final Block MELTER = null;
	
	@ObjectHolder("stationary_cannon")
	public static final Block STATIONARY_CANNON = null;
	
	@EventBusSubscriber(modid = OldGuns.MODID)
	public static class RegistrationHandler
	{
		@SubscribeEvent
		public static void registerBlocks(Register<Block> event)
		{
			final Block[] blocks =
				{
					new BlockGunsmithsBench(),
					new BlockMelter(),
					new BlockStationaryCannon()
				};

			event.getRegistry().registerAll(blocks);
			
			registerTileEntities();
		}
		
		@SubscribeEvent
		public static void registerBlockItems(Register<Item> event)
		{
			final Item[] items =
				{
					new ItemBlock(ModBlocks.GUNSMITHS_BENCH).setRegistryName(ModBlocks.GUNSMITHS_BENCH.getRegistryName()),
					new ItemBlock(ModBlocks.MELTER).setRegistryName(ModBlocks.MELTER.getRegistryName()),
					new ItemBlock(ModBlocks.STATIONARY_CANNON).setRegistryName(ModBlocks.STATIONARY_CANNON.getRegistryName())
				};
			
			event.getRegistry().registerAll(items);
		}
	}
	
	private static void registerTileEntities() {
		registerTileEntity(TileEntityMelter.class, "melter");
		registerTileEntity(TileEntityStationaryCannon.class, "stationary_cannon");
	}

	private static void registerTileEntity(final Class<? extends TileEntity> tileEntityClass, final String name) {
		GameRegistry.registerTileEntity(tileEntityClass, new ResourceLocation(OldGuns.MODID, name));
	}
}
