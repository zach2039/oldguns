package zach2039.oldguns.common.init;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.oredict.OreDictionary;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.item.ammo.ItemSmallIronMusketBall;
import zach2039.oldguns.common.item.firearm.ItemFlintlockPistol;
import zach2039.oldguns.common.item.part.ItemFlintlockMechanism;
import zach2039.oldguns.common.item.part.ItemSmallIronBarrel;
import zach2039.oldguns.common.item.part.ItemSmallWoodenHandle;
import zach2039.oldguns.common.item.tools.ItemRepairKit;
import zach2039.oldguns.common.item.tools.ItemSmallMusketBallCast;

@ObjectHolder(OldGuns.MODID)
public class ModItems
{
	@ObjectHolder("flintlock_pistol")
	public static final Item FLINTLOCK_PISTOL = null;
	
	@ObjectHolder("small_iron_musket_ball")
	public static final Item SMALL_IRON_MUSKET_BALL = null;
	
	@ObjectHolder("small_iron_barrel")
	public static final Item SMALL_IRON_BARREL = null;
	
	@ObjectHolder("small_wooden_handle")
	public static final Item SMALL_WOODEN_HANDLE = null;

	@ObjectHolder("flintlock_mechanism")
	public static final Item FLINTLOCK_MECHANISM = null;
	
	@ObjectHolder("small_musket_ball_cast")
	public static final Item SMALL_MUSKET_BALL_CAST = null;
	
	@ObjectHolder("repair_kit")
	public static final Item REPAIR_KIT = null;
	
	@EventBusSubscriber(modid = OldGuns.MODID)
	public static class RegistrationHandler
	{
		@SubscribeEvent
		public static void registerItems(Register<Item> event)
		{
			final Item[] items =
				{
					new ItemFlintlockPistol(),
					new ItemSmallIronMusketBall(),
					new ItemSmallWoodenHandle(),
					new ItemFlintlockMechanism(),
					new ItemSmallIronBarrel(),
					new ItemSmallMusketBallCast(),
					new ItemRepairKit()
				};

			event.getRegistry().registerAll(items);
		}
	}
}
