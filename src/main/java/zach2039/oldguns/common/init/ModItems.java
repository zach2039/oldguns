package zach2039.oldguns.common.init;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import zach2039.oldguns.common.OldGuns;
import zach2039.oldguns.common.item.ammo.ItemLargeIronMusketBall;
import zach2039.oldguns.common.item.ammo.ItemLargeStoneMusketBall;
import zach2039.oldguns.common.item.ammo.ItemMediumIronCannonball;
import zach2039.oldguns.common.item.ammo.ItemMediumIronHEShell;
import zach2039.oldguns.common.item.ammo.ItemMediumIronMusketBall;
import zach2039.oldguns.common.item.ammo.ItemMediumStoneMusketBall;
import zach2039.oldguns.common.item.ammo.ItemSmallIronMusketBall;
import zach2039.oldguns.common.item.ammo.ItemSmallStoneMusketBall;
import zach2039.oldguns.common.item.artillery.ItemArtilleryCannon;
import zach2039.oldguns.common.item.firearm.ItemFlintlockPistol;
import zach2039.oldguns.common.item.firearm.ItemMatchlockPistol;
import zach2039.oldguns.common.item.part.ItemFlintlockMechanism;
import zach2039.oldguns.common.item.part.ItemSmallIronBarrel;
import zach2039.oldguns.common.item.part.ItemSmallWoodenHandle;
import zach2039.oldguns.common.item.tools.ItemGunnersQuadrant;
import zach2039.oldguns.common.item.tools.ItemLargeMusketBallMold;
import zach2039.oldguns.common.item.tools.ItemLargeMusketBallMoldTool;
import zach2039.oldguns.common.item.tools.ItemLongMatch;
import zach2039.oldguns.common.item.tools.ItemMediumMusketBallMold;
import zach2039.oldguns.common.item.tools.ItemMediumMusketBallMoldTool;
import zach2039.oldguns.common.item.tools.ItemPowderCharge;
import zach2039.oldguns.common.item.tools.ItemRamRod;
import zach2039.oldguns.common.item.tools.ItemRepairKit;
import zach2039.oldguns.common.item.tools.ItemSmallMusketBallMold;
import zach2039.oldguns.common.item.tools.ItemSmallMusketBallMoldTool;

@ObjectHolder(OldGuns.MODID)
public class ModItems
{
	@ObjectHolder("artillery_cannon")
	public static final Item ARTILLERY_CANNON = null;
	
	@ObjectHolder("medium_iron_cannonball")
	public static final Item MEDIUM_IRON_CANNONBALL = null;
	@ObjectHolder("medium_iron_he_shell")
	public static final Item MEDIUM_IRON_HE_SHELL = null;
	
	@ObjectHolder("matchlock_pistol")
	public static final Item MATCHLOCK_PISTOL = null;
	
	@ObjectHolder("flintlock_pistol")
	public static final Item FLINTLOCK_PISTOL = null;
	
	@ObjectHolder("small_iron_musket_ball")
	public static final Item SMALL_IRON_MUSKET_BALL = null;
	@ObjectHolder("medium_iron_musket_ball")
	public static final Item MEDIUM_IRON_MUSKET_BALL = null;
	@ObjectHolder("large_iron_musket_ball")
	public static final Item LARGE_IRON_MUSKET_BALL = null;
	
	@ObjectHolder("small_stone_musket_ball")
	public static final Item SMALL_STONE_MUSKET_BALL = null;
	@ObjectHolder("medium_stone_musket_ball")
	public static final Item MEDIUM_STONE_MUSKET_BALL = null;
	@ObjectHolder("large_stone_musket_ball")
	public static final Item LARGE_STONE_MUSKET_BALL = null;
	
	@ObjectHolder("small_iron_barrel")
	public static final Item SMALL_IRON_BARREL = null;
	
	@ObjectHolder("small_wooden_handle")
	public static final Item SMALL_WOODEN_HANDLE = null;

	@ObjectHolder("flintlock_mechanism")
	public static final Item FLINTLOCK_MECHANISM = null;
	
	@ObjectHolder("small_musket_ball_mold")
	public static final Item SMALL_MUSKET_BALL_MOLD = null;
	@ObjectHolder("medium_musket_ball_mold")
	public static final Item MEDIUM_MUSKET_BALL_MOLD = null;
	@ObjectHolder("large_musket_ball_mold")
	public static final Item LARGE_MUSKET_BALL_MOLD = null;

	@ObjectHolder("small_musket_ball_mold_tool")
	public static final Item SMALL_MUSKET_BALL_MOLD_TOOL = null;
	@ObjectHolder("medium_musket_ball_mold_tool")
	public static final Item MEDIUM_MUSKET_BALL_MOLD_TOOL = null;
	@ObjectHolder("large_musket_ball_mold_tool")
	public static final Item LARGE_MUSKET_BALL_MOLD_TOOL = null;
	
	@ObjectHolder("repair_kit")
	public static final Item REPAIR_KIT = null;
	
	@ObjectHolder("gunners_quadrant")
	public static final Item GUNNERS_QUADRANT = null;
	@ObjectHolder("ram_rod")
	public static final Item RAM_ROD = null;
	@ObjectHolder("long_match")
	public static final Item LONG_MATCH = null;
	@ObjectHolder("powder_charge")
	public static final Item POWDER_CHARGE = null;
	
	@EventBusSubscriber(modid = OldGuns.MODID)
	public static class RegistrationHandler
	{
		@SubscribeEvent
		public static void registerItems(Register<Item> event)
		{
			final Item[] items =
				{					
					new ItemArtilleryCannon(),
					new ItemMediumIronCannonball(),
					new ItemMediumIronHEShell(),
					new ItemMatchlockPistol(),
					new ItemFlintlockPistol(),
					new ItemSmallIronMusketBall(),
					new ItemMediumIronMusketBall(),
					new ItemLargeIronMusketBall(),
					new ItemSmallStoneMusketBall(),
					new ItemMediumStoneMusketBall(),
					new ItemLargeStoneMusketBall(),
					new ItemSmallWoodenHandle(),
					new ItemFlintlockMechanism(),
					new ItemSmallIronBarrel(),
					new ItemSmallMusketBallMold(),
					new ItemMediumMusketBallMold(),
					new ItemLargeMusketBallMold(),
					new ItemSmallMusketBallMoldTool(),
					new ItemMediumMusketBallMoldTool(),
					new ItemLargeMusketBallMoldTool(),
					new ItemRepairKit(),
					new ItemGunnersQuadrant(),
					new ItemRamRod(),
					new ItemLongMatch(),
					new ItemPowderCharge()
				};

			event.getRegistry().registerAll(items);
		}
	}
}
